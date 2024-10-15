package com.Aaron.web.controller;

import com.Aaron.entity.dto.CaptchaDto;
import com.Aaron.entity.queryvo.LoginVo;
import com.Aaron.entity.queryvo.RegisterVo;
import com.Aaron.service.IUserInfoService;
import com.Aaron.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author Aaron
 * @since 2024-10-15
 */
@RestController
@RequestMapping("/userInfo")
@Tag(name = "用户信息")
public class UserInfoController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private IUserInfoService userInfoService;

    @GetMapping("getCaptcha")
    @Operation(summary = "获取图片验证码")
    public Result<CaptchaDto> getCaptcha() {
        return Result.ok(userInfoService.getCaptcha());
    }

    @PostMapping("sendemail")
    @Operation(summary = "发送邮箱验证码")
    public Result<String> sendEmail(@RequestParam String email){
        return Result.ok(userInfoService.sendEmail(email));
    }

    @PostMapping("register")
    @Operation(summary = "用户注册")
    public Result<String> register(@RequestBody RegisterVo registerVo, HttpServletRequest request){
        return Result.ok(userInfoService.register(registerVo,request));
    }

    @PostMapping("login")
    @Operation(summary = "用户登录")
    public Result<String> Login(@RequestBody LoginVo loginVo,HttpServletRequest request){
        String login = userInfoService.login(loginVo,request);
        if(login.equals("fail")){
            return Result.error(201,"账号或密码错误");
        }
        return Result.ok(login);
    }

    @GetMapping("logout")
    @Operation(summary = "用户登出")
    public Result<String> logout(@RequestParam String userId){
        return Result.ok(userInfoService.logout(userId));
    }


}
