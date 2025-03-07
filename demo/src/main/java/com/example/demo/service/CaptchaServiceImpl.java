package com.example.demo.service;


import com.google.code.kaptcha.Producer;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class CaptchaServiceImpl {
    @Autowired
    private Producer producer;

    public Map<String,String> getCaptcha(){
        String uuid= UUID.randomUUID().toString();
        BufferedImage image=getCaptchaImg(uuid);
        String imgBase64 = bufferedImageToBase64(image);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("captchaId", uuid);
        resultMap.put("base64Img", imgBase64);
        return resultMap;
    }

    private BufferedImage getCaptchaImg(String uuid) {
        if (StringUtils.isEmpty(uuid)) {
            throw new RuntimeException("uuid不能为空");
        }
        //生成文字验证码
        String code = producer.createText();
        log.info("uuid:{},验证码：{}",uuid,code);
        //缓存验证码
      //  redisService.set(AuthKeys.AUTH_CAPTCHA, uuid, code);

        return producer.createImage(code);
    }

    /**
     * BufferedImage 编码转换为 base64
     * @param bufferedImage
     * @return
     */
    private static String bufferedImageToBase64(BufferedImage bufferedImage) {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", bao);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = Base64.getEncoder().encode(bao.toByteArray());
        String base64 = new String(bytes);
        base64 = base64.replaceAll("\n", "").replaceAll("\r", "");
        return "data:image/png;base64," + base64;
    }

//    public boolean validate(String uuid, String code) {
//
//
//        String cacheCode = redisService.get(AuthKeys.AUTH_CAPTCHA, uuid, String.class);
//        if (StringUtils.isEmpty(cacheCode)) {
//            return false;
//        }
//        //删除缓存验证码
//        redisService.delete(AuthKeys.AUTH_CAPTCHA, uuid);
//        if (cacheCode.equalsIgnoreCase(code)) {
//            return true;
//        }
//        return false;
//    }

}



