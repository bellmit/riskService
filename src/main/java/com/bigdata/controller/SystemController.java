package com.bigdata.controller;

import com.alibaba.fastjson.JSON;
import com.bigdata.security.AccessToken;
import com.bigdata.security.AuthorizationServerConfiguration;
import com.bigdata.service.UserService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Desciption 登录处理
 * Create By  li.bo
 * CreateTime 2018/3/13 10:36
 * UpdateTime 2018/3/13 10:36
 */
@Slf4j
@RestController
public class SystemController {

    public static Map<String, String> userTokenMap = Maps.newHashMap();
    public static Map<String, String> userSessionMap = Maps.newHashMap();

    private static OkHttpClient client = new OkHttpClient();

    @Value("${token.url}")
    private String tokenUrl;

    @Autowired
    private UserService userService;

    @RequestMapping("/cors")
    public ResponseEntity<?> corsHandler(HttpServletRequest request, HttpServletResponse response) {

        return ResponseEntity.ok("success");
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");

        String usernameAndPassword = AuthorizationServerConfiguration.clientName.concat(":").concat(AuthorizationServerConfiguration.clientSecret);
        String basicOauthString = new BASE64Encoder().encode(usernameAndPassword.getBytes());

        try {
            RequestBody formBody = new FormBody.Builder()
                    .add("grant_type", "password")
                    .add("username", username)
                    .add("password", password)
                    .build();

            Request request = new Request.Builder()
                    .url(tokenUrl)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Basic ".concat(basicOauthString))
                    .post(formBody)
                    .build();

            Response response = client.newCall(request).execute();
            String result = response.body().string();
            if (response.isSuccessful()) {
                // record the token, then bind with user together
                String sessionId = httpServletRequest.getSession().getId();
                AccessToken accessToken = JSON.parseObject(result, AccessToken.class);
                userTokenMap.put(accessToken.getAccess_token(), username);
                userSessionMap.put(accessToken.getAccess_token(), sessionId);

                // record the user
                try {
                    int loginRecord = userService.saveUserLoginRecord(userService.getOne(username).getId(), getIpAddress(httpServletRequest));
                    log.info("write user login record {}", loginRecord > 0 ? "success!" : "fail!");
                } catch (Exception e) {
                    log.error("write user login record fail!");
                    e.printStackTrace();
                }
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "true";
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
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
