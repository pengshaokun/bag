package com.zhskg.bag.enums;

/**
 * @author zhangshengyue
 * @date 2018/10/7
 * desc: 短信项目名称
 */
public enum SmsProjectName {

    BAG_APP(1,"BAG_APP"),
    BAG_WEB(2,"BAG_WEB"),
    CART_APP(3,"CART_APP"),
    CART_WEB(4,"CART_WEB")

    ;


    private int value;

    private String desc;

    SmsProjectName(int value, String desc) {
        this.value = value;
        this.desc = desc;

    }


    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static SmsProjectName enumOf(int value) {
        for (SmsProjectName thisEnum : SmsProjectName.values()) {
            if (thisEnum.getValue() == value) {
                return thisEnum;
            }
        }
        return null;
    }
}
