package com.bigdata.test;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.CIDResult;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.SMS;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Desciption 极光推送
 * Create By  li.bo
 * CreateTime 2017/11/9 14:29
 * UpdateTime 2017/11/9 14:29
 */
public class JpushClient {

    private static Logger logger = LoggerFactory.getLogger(JpushClient.class);

    //在极光注册的APPKEY和MASTERSECRET    必填
    private static final String APPKEY ="7eac22f63fec5ea129c9865d";
    private static final String MASTERSECRET ="91d69695d3218d7c376c14a5";
    private static JPushClient jpushClient = null;
    //保存离线的时长，最多支持10天     （Ps：不填写时，默认是保存一天的离线消息     0：代表不保存离线消息）
    private static int timeToLive = 60 * 60 * 24 ;

    /**
     * 构建推送对象
     * @param alias
     * @param nickname
     * @return
     */
    public static PushPayload buildPushObject_alias_followUser(String alias , String nickname) {
        return PushPayload.newBuilder().setPlatform(Platform.android_ios())
//                .setAudience(Audience.alias(alias))
                .setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .addExtra("type", "infomation")
                                .setAlert(nickname+" 关注了你！")
                                .build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .addExtra("type", "infomation")
                                .setAlert(nickname+" 关注了你！")
                                .build())
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(false)//true-推送生产环境 false-推送开发环境（测试使用参数）
                        .setTimeToLive(timeToLive)
                        .build())
                .build();
    }

    /**
     * 发送推送消息
     * @param alias
     * @param nickname
     */
    public static void sendPushAfterFollow(String alias ,String nickname){
        try {
            jpushClient = new JPushClient(MASTERSECRET, APPKEY, null, ClientConfig.getInstance());
            //生成推送的内容
            PushPayload payload = buildPushObject_alias_followUser(alias,nickname);
//            PushPayload payload = PushPayload.alertAll(ALERT);
            payload.resetOptionsTimeToLive(timeToLive);
            PushResult result = jpushClient.sendPush(payload);
            logger.info("Got result - " + result);
        } catch (APIConnectionException e) {
            // Connection error, should retry later
            logger.error("Connection error, should retry later", e);

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            logger.error("Should review the error, and fix the request", e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
        }

    }

    public static void testSendWithSMS() {
        JPushClient jpushClient = new JPushClient(MASTERSECRET, APPKEY);
        try {
            SMS sms = SMS.content("Test SMS", 10);
            PushResult result = jpushClient.sendAndroidMessageWithAlias("Test SMS", "test sms", sms, "13476026420");
            logger.info("Got result - " + result);
        } catch (APIConnectionException e) {
            logger.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            logger.error("Error response from JPush server. Should review and fix it. ", e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
        }
    }

    public static void testGetCidList() {
        JPushClient jPushClient = new JPushClient(MASTERSECRET, APPKEY);
        try {
            CIDResult result = jPushClient.getCidList(3, "push");
            logger.info("Got result - " + result);
        } catch (APIConnectionException e) {
            logger.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            logger.error("Error response from JPush server. Should review and fix it. ", e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
        }
    }

    public static void main(String[] args) {

        sendPushAfterFollow("13476026420", "13476026420");
//        testSendWithSMS();
//        testGetCidList();
    }
}
