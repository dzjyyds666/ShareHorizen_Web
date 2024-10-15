package com.Aaron.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.Aaron.Exection.MyException;
import com.Aaron.entity.dto.CaptchaDto;
import com.Aaron.entity.po.UserInfo;
import com.Aaron.entity.queryvo.LoginVo;
import com.Aaron.entity.queryvo.RegisterVo;
import com.Aaron.mapper.UserInfoMapper;
import com.Aaron.service.IUserInfoService;
import com.Aaron.utils.JwtUtils;
import com.Aaron.utils.ResultCodeEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author Aaron
 * @since 2024-10-15
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Value("${spring.mail.username}")
    private String from;

    private static final String register_header = "注册验证码:";
    private static final String token_header = "token:";


    @Override
    public CaptchaDto getCaptcha() {
        Captcha captcha = new SpecCaptcha(120, 40, 5);
        String code = captcha.text();
        String img = captcha.toBase64();
        return new CaptchaDto(img, code);
    }

    @Override
    public String sendEmail(String email) {
        try {
            int code = 10000 + new Random().nextInt(90000);
            SimpleMailMessage message = new SimpleMailMessage();
            String displayname = "共享视界" + "<" + from + ">";
            message.setFrom(displayname);
            message.setTo(email);
            message.setSubject("欢迎注册共享视界");
            message.setText("欢迎注册共享视界，您的验证码为：" + code);
            redisTemplate.opsForValue().set(register_header+email, String.valueOf(code),5, TimeUnit.MINUTES);
            mailSender.send(message);
            return "发送成功";
        } catch (Exception e) {
            throw new MyException(ResultCodeEnum.EMAIL_SEND_ERROR);
        }
    }

    @Override
    @Transactional
    public String register(RegisterVo registerVo, HttpServletRequest request) {
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(registerVo.getEmail());
        String password = DigestUtils.md5Hex(registerVo.getPassword());
        userInfo.setUserPassword(password);
        userInfo.setSex(Boolean.parseBoolean(registerVo.getSex()));
        userInfo.setLastLoginIp(getIp(request));
        userInfo.setNickName("user:" + registerVo.getEmail().substring(0, 6));
        userInfo.setUserId(generateRandomString(12));
        return userInfoMapper.insert(userInfo) > 0 ? "注册成功" : "注册失败";
    }

    @Override
    public String login(LoginVo loginVo,HttpServletRequest request) {
        String code = redisTemplate.opsForValue().get(register_header);
        if (code != null && !code.equals(loginVo.getEmailCode())) {
            throw new MyException(ResultCodeEnum.EMAIL_CODE_ERROR);
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(loginVo.getEmail());
        userInfo.setUserPassword(DigestUtils.md5Hex(loginVo.getPassword()));

        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getEmail, userInfo.getEmail());
        UserInfo user = userInfoMapper.selectOne(queryWrapper);
        if(user != null){
            if(user.getUserPassword().equals(userInfo.getUserPassword())){
                // 修改判断用户IP地址，如果变化则修改
                String ip = getIp(request);
                if(!user.getLastLoginIp().equals(ip)){
                    UserInfo updateUser = new UserInfo();
                    updateUser.setLastLoginIp(ip);
                    userInfoMapper.updateById(updateUser);
                }
                String token = JwtUtils.createToken(user.getUserId());
                redisTemplate.opsForValue().set(token_header+user.getUserId(),token);
                return token;
            }
        }
        return "fail";
    }

    @Override
    public String logout(String userId) {
        //TODO 退出登录，获取Thearlocal内容

        redisTemplate.delete(token_header+userId);
        return "退出成功";
    }

    // 生成随机字符串
    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    public String getIp(HttpServletRequest request){
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
