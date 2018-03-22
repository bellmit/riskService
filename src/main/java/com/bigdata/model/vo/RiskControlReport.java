package com.bigdata.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Desciption
 * Create By  li.bo
 * CreateTime 2018/3/22 11:16
 * UpdateTime 2018/3/22 11:16
 */
@NoArgsConstructor
@Accessors(chain = true)
@Setter
@Getter
@ToString
public class RiskControlReport {

    private String type;
    private String subType;
    private String monitorContent;
    private String threshold;
    private String value;
    private int trigger;
}
