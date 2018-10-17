package com.zhskg.bag.enums;

public enum FileSourceEnum {
    GENERA_BAG("generaBag/", 0),
    APP_HEAD_IMG("appHead/", 1),
    REPORT_IMG("reportImg/", 2);

    private int value;
    private String name;

    FileSourceEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

}
