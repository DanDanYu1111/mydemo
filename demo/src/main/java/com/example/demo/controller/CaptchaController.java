package com.example.demo.controller;

import com.example.demo.service.CaptchaServiceImpl;
import com.wf.captcha.*;
import com.wf.captcha.base.Captcha;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/captcha")
public class CaptchaController {
    @Autowired
    CaptchaServiceImpl captchaService;
    /**
     * 验证码
     */
    @GetMapping("/digit")
    public Map<String, String> captcha() {
        return captchaService.getCaptcha();
    }

    public static void main(String[] args) {
        UUID uuid= UUID.randomUUID();
        System.out.println(uuid);
    }

//    @GetMapping("/graphics")
//    public void captcha(HttpServletRequest request, HttpServletResponse response,
//                        @RequestParam String uuid,
//                        @RequestParam(defaultValue = "arithmeti", required = false) String type) throws Exception {
//        // 设置请求头为输出图片类型
//        response.setContentType("image/gif");
//        response.setHeader("Pragma", "No-cache");
//        response.setHeader("Cache-Control", "no-cache");
//        response.setDateHeader("Expires", 0);
//
//        Captcha captcha = null;
//        switch (type) {
//            case "png":
//                captcha = new SpecCaptcha(130, 48);
//                break;
//            case "gif":
//                // gif类型
//                captcha = new GifCaptcha(130, 48);
//                break;
//            case "cn":
//                // 中文类型
//                captcha = new ChineseCaptcha(130, 48, 5, new Font("楷体", Font.PLAIN, 28));
//                break;
//            case "cngif":
//                // 中文gif类型
//                captcha = new ChineseGifCaptcha(130, 48, 5, new Font("楷体", Font.PLAIN, 28));
//                break;
//            case "arithmeti":
//                // 算术类型
//                ArithmeticCaptcha arithmeticCaptcha = new ArithmeticCaptcha(130, 48);
//                arithmeticCaptcha.setLen(3);  // 几位数运算，默认是两位
//                arithmeticCaptcha.getArithmeticString();  // 获取运算的公式：3+2=?
//                arithmeticCaptcha.text();  // 获取运算的结果：5
//                captcha = arithmeticCaptcha;
//                break;
//            default:
//                new SpecCaptcha(130, 48);
//                break;
//        }
//        // 设置字体
////        captcha.setFont(new Font("Verdana", Font.PLAIN, 32));  // 有默认字体，可以不用设置
//        // 设置类型，纯数字、纯字母、字母数字混合
//        captcha.setCharType(Captcha.TYPE_DEFAULT);
//
//        //缓存验证码
//        //redisService.set(AuthKeys.AUTH_CAPTCHA, uuid, captcha.text().toLowerCase());
//        // 输出图片流
//        captcha.out(response.getOutputStream());
//    }





}


