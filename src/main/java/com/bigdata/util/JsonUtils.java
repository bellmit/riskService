package com.bigdata.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Desciption json解析工具类
 * Create By  li.bo
 * CreateTime 2018/1/24 15:43
 * UpdateTime 2018/1/24 15:43
 */
@Slf4j
public class JsonUtils {

    public static JSONObject parseJSONObject(String s) {

        if (s == null || s.length() < 0) {
            return new JSONObject();
        }

        log.info("s1:{}", s);
        s = s.replace("\"{", "{").replace("}\"", "}");
        s = StringEscapeUtils.unescapeJava(s);
        log.info("s2:{}", s);

        return JSON.parseObject(s);
    }
}
