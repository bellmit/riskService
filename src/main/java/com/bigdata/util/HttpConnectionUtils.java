package com.bigdata.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Copyright (c) by adinnet information technology Co., Ltd.
 * @All right reserved.
 * @Create Date: 2017/7/28 16:25
 * @Create Author: wangweizhen
 * @File Name: buytoday
 * @Last version: 1.0
 */
public class HttpConnectionUtils {
    private static final String charset = "utf-8";
    private static final String contentType = "application/json";

    /**
     * 请求工具类
     *
     * @param openUrl
     * @param json
     * @return
     * @author:wangweizhen
     */
    public static String setConnection1(String openUrl, JSONObject json) {
        String sTotalString = "";
        try {
            URL url = new URL(openUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            //connection.addRequestProperty("Content-type", contentType);
            connection.connect();
            OutputStream outputStream = connection.getOutputStream();
            OutputStreamWriter ostrem = new OutputStreamWriter(outputStream, charset);
            ostrem.write(json.toString());
            ostrem.flush();
            ostrem.close();
            int result = connection.getResponseCode();
            if (result == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset));
                String sCurrentLine = "";
                while ((sCurrentLine = bufferedReader.readLine()) != null) {
                    if (sCurrentLine.length() > 0) {
                        sTotalString += sCurrentLine.trim();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sTotalString;
    }

    public static String setConnection(String url, JSONObject jsonObject) {
        String renResult = "";
        try {
            renResult = doHttpPost(jsonObject, url);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return renResult;
    }

    public static String doHttpPost(JSONObject jsonObject, String URL) {
        String param = jsonObject.toString();
        System.out.println("发起的数据:" + param);
        byte[] data = param.getBytes();
        InputStream instr = null;
        ByteArrayOutputStream out = null;
        try {
            URL url = new URL(URL);
            HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
            urlCon.setDoOutput(true);
            urlCon.setDoInput(true);
            urlCon.setUseCaches(false);
            urlCon.setRequestProperty("Content-Type", "text/plain");
            urlCon.setRequestProperty("Content-length", String.valueOf(data.length));
            DataOutputStream printout = new DataOutputStream(urlCon.getOutputStream());
            printout.write(data);
            printout.flush();
            printout.close();
            instr = urlCon.getInputStream();
            int responseCode = urlCon.getResponseCode();
            byte[] bis = IOUtils.toByteArray(instr);
            String ResponseString = new String(bis, "UTF-8");
            if ((ResponseString == null) || ("".equals(ResponseString.trim()))) {
                System.out.println("返回空");
            }
            JSONObject json = JSONObject.parseObject(ResponseString);
            json.put("responseCode", responseCode);
            ResponseString = json.toString();
            System.out.println("返回数据为:" + ResponseString);
            return ResponseString;
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (instr != null) {
                    instr.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return "0";
            }
        }
    }

    public static String doPost(String url, JSONObject jsonObject) {
        String renResult = "";
        try {
            renResult = post(jsonObject.toJSONString(), url);
        } catch (Exception e) {

        }
        return renResult;

    }

    public static String post(String param, String URL) {
        System.out.println("发起的数据:" + param);
        InputStream instr = null;
        ByteArrayOutputStream out = null;
        try {
            byte[] data = param.getBytes("utf-8");

            URL url = new URL(URL);
            URLConnection urlCon = url.openConnection();
            urlCon.setDoOutput(true);
            urlCon.setDoInput(true);
            urlCon.setUseCaches(false);
            urlCon.setRequestProperty("Content-Type", "text/plain");
            urlCon.setRequestProperty("Content-length", String.valueOf(data.length));
            urlCon.setRequestProperty("Accept-Charset", "utf-8");
            urlCon.setRequestProperty("contentType", "utf-8");

            DataOutputStream printout = new DataOutputStream(urlCon.getOutputStream());
            printout.write(data);
            printout.flush();
            printout.close();
            instr = urlCon.getInputStream();
            byte[] bis = IOUtils.toByteArray(instr);
            String ResponseString = new String(bis, "UTF-8");
            if ((ResponseString == null) || ("".equals(ResponseString.trim()))) {
                System.out.println("返回空");
            }
            System.out.println("返回数据为:" + ResponseString);
            return ResponseString;
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                instr.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                return "0";
            }
        }
    }

    public static String HuanXinConnection(String url, JSONObject jsonObject) {
        String renResult = "";
        try {
            renResult = huanXindoHttpPost(jsonObject, url);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return renResult;
    }

    public static String huanXindoHttpPost(JSONObject jsonObject, String URL) {
        String param = jsonObject.toString();
        System.out.println("发起的数据:" + param);
        byte[] data = param.getBytes();
        InputStream instr = null;
        ByteArrayOutputStream out = null;
        try {
            URL url = new URL(URL);
            HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
            urlCon.setDoOutput(true);
            urlCon.setDoInput(true);
            urlCon.setUseCaches(false);
            urlCon.setRequestProperty("Content-Type", "text/plain");
            if (jsonObject.getString("access_token") != null) {
                urlCon.setRequestProperty("Authorization", jsonObject.getString("access_token"));
            }
            urlCon.setRequestProperty("Content-length", String.valueOf(data.length));
            DataOutputStream printout = new DataOutputStream(urlCon.getOutputStream());
            printout.write(data);
            printout.flush();
            printout.close();
            int responseCode = urlCon.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST || responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                jsonObject.put("responseCode", responseCode);
                return jsonObject.toString();
            }
            instr = urlCon.getInputStream();
            byte[] bis = IOUtils.toByteArray(instr);
            String ResponseString = new String(bis, "UTF-8");
            if ((ResponseString == null) || ("".equals(ResponseString.trim()))) {
                System.out.println("返回空");
            }
            JSONObject json = JSONObject.parseObject(ResponseString);
            json.put("responseCode", responseCode);
            ResponseString = json.toString();
            System.out.println("返回数据为:" + ResponseString);
            return ResponseString;
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (instr != null) {
                    instr.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return "0";
            }
        }
    }

    public static void main(String[] args) {
        // post();
    }

}
