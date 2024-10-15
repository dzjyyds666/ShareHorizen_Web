package com.Aaron.service;

import com.Aaron.entity.dto.CaptchaDto;
import com.Aaron.entity.po.UserInfo;
import com.Aaron.entity.queryvo.LoginVo;
import com.Aaron.entity.queryvo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author Aaron
 * @since 2024-10-15
 */
public interface IUserInfoService extends IService<UserInfo> {

    CaptchaDto getCaptcha();

    String sendEmail(String email);

    String register(RegisterVo registerVo, HttpServletRequest request);

    String login(LoginVo loginVo,HttpServletRequest request);

    String logout(String userId);
}
