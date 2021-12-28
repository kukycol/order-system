package com.aplan.grab.baidu.image;

public enum BaiduImageColorEnum {

    BLACKANDWHITE(2048),//黑白
    WHITE(1024),//白色
    BLACK(512),//黑色
    BROWN(128),//棕色
    PINK(64),//粉色
    VIOLET(32),//紫色
    BLUE(16),//蓝色
    CYAN(8),//青色
    GREEN(4),//绿色
    YELLOW(2),//黄色
    RED(1),//红色
    NONE(0),//无色
    ;

    private int color;

    BaiduImageColorEnum(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }


}
