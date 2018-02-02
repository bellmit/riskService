package com.bigdata.util.Enum;

/**
 * Desciption 接口返回枚举
 * Create By  li.bo
 * CreateTime 2017/10/29 19:58
 * UpdateTime 2017/10/29 19:58
 */
public enum ResponseResultEnum {

    NOT_FOUND("0000", "0000", "未查到数据"),
    SUCCESS("0001", "0001", "成功"),
    RETRY("0002", "0002", "网络出错，请重试！"),
    PARAM_ERROR("0003", "0003", "参数错误！");


    /**
     * 类型
     */
    private String featureType;
    /**
     * 名称(数据库featrues存储名)
     */
    private String featureName;
    /**
     * 描叙
     */
    private String description;

    /**
     * 初始化
     *
     * @param featureType
     * @param featureName
     * @param description
     */
    ResponseResultEnum(String featureType, String featureName, String description) {
        this.featureType = featureType;
        this.featureName = featureName;
        this.description = description;
    }

    public static ResponseResultEnum getValueByType(String featureType) {
        for (ResponseResultEnum enums : values()) {
            if (enums.getFeatureType() == featureType) {
                return enums;
            }
        }
        return null;
    }

    public static ResponseResultEnum getValueByName(String featureName) {
        for (ResponseResultEnum enums : values()) {
            // 不区分大小写返回
            if (enums.getFeatureName().equalsIgnoreCase(featureName)) {
                return enums;
            }
        }
        return null;
    }

    /**
     * @return the featureType
     */
    public String getFeatureType() {
        return featureType;
    }

    /**
     * @return the featureName
     */
    public String getFeatureName() {
        return featureName;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
}

