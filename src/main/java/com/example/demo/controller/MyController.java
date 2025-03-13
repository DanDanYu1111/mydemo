package com.example.demo.controller;

import com.example.demo.service.RabbitMQProducer;
import com.example.demo.service.TableService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/input")
@Slf4j
@RefreshScope
public class MyController {
    @Value("${param.aaa}")
    private String param;
    @Autowired
    RabbitMQProducer rabbitMQProducer;
    @Autowired
    TableService tableService;

    @GetMapping("/table")
    public String table(@RequestParam String table) {
        return tableService.getTableColumns(table);
    }

    @GetMapping("/get")
    public String get(){
        return param;
    }

    @GetMapping("/get2")
    public String get2(@RequestParam String param){
        log.info("param:{}",param);
        return param;
    }

    @GetMapping("/get3")
    public String get3(){
        String now=LocalDateTime.now().toString();
        log.info("now:{}",now);
        return now;
    }


    public static void main(String[] args) {
        String urlString = "http://121.196.207.12:7070/input/get"; // 替换为你要请求的地址

        try {
            // 创建URL对象
            URL url = new URL(urlString);
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置请求方法为GET
            connection.setRequestMethod("GET");

            // 获取响应码
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // 如果响应码为200，则读取响应内容
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                // 逐行读取响应内容
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // 打印响应内容
                System.out.println("Response Body: " + response.toString());
            } else {
                System.out.println("GET request failed");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
