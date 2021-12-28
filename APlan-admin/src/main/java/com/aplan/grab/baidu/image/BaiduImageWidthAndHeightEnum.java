package com.aplan.grab.baidu.image;

public enum BaiduImageWidthAndHeightEnum {

    PC1920X1200(1920,1200),
    PC1920X1080(1920,1080),
    PC1680X1050(1680,1050),
    PC1600X900(1600,900),
    PC1360X900(1360,900),
    PC1366X768(1366,768),
    PC1360X768(1360,768),
    PC1280X1024(1280,1024),
    PC1280X960(1280,960),
    PC1280X800(1280,800),
    PC1280X768(1280,768),
    PC1280X720(1280,720),
    PC1152X864(1152,864),
    PC1090X1080(1090,1080),
    PC1080X960(1080,960),
    PC1024X768(1024,768),

    APP1680X1050(2560,1440),
    APP1920X1080(1920,1080),
    APP1334X750(1334,750),
    APP1280X720(1280,720),
    APP1136X640(1136,640),
    APP960X640(960,640),
    APP960X540(960,540),
    APP854X480(854,480),
    APP800X480(800,480),
    APP480X320(480,320),
    APP0X0(0,0),
    ;

    private int width;
    private int height;

    BaiduImageWidthAndHeightEnum(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
