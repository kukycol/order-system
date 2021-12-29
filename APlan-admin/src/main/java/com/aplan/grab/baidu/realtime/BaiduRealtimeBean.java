package com.aplan.grab.baidu.realtime;

import lombok.Data;

@Data
public class BaiduRealtimeBean {

    private int index;
    private String query;
    private int hotTag;
    private long hotScore;
    private String img;
    private String desc;
    private String url;

}
