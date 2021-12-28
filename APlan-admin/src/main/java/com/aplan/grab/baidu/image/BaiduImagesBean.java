package com.aplan.grab.baidu.image;

import lombok.Data;

import java.util.List;

@Data
public class BaiduImagesBean {

    private String bdFmtDispNum;
    private String bdIsClustered;
    private String bdSearchTime;
    private Integer displayNum;
    private String gsm;
    private String isNeedAsyncRequest;
    private Integer listNum;
    private String queryEnc;
    private String queryExt;
    private List<BaiduImageBean> data;
}
