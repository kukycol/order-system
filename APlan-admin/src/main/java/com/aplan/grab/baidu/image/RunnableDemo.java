package com.aplan.grab.baidu.image;

import cn.hutool.core.date.SystemClock;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.aplan.utils.FileUtil;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.UUID;

class RunnableDemo implements Runnable {
    private Thread t;
    private String threadName;
    private int threadStartPage;
    private int threadEndPage;

    RunnableDemo( String name,int startPage,int endtPage) {
        threadName = name;
        threadStartPage = startPage;
        threadEndPage = endtPage;
        System.out.println("Creating " +  threadName );
    }

    @SneakyThrows
    @Override
    public void run() {
        String urlencodeKewy = "婚纱照";
        int page = 1;
        int size = 50;
        long beginTime = SystemClock.now();
        for (int i = threadStartPage; i <= threadEndPage; i++) {
            String url = "https://image.baidu.com/search/acjson?tn=resultjson_com&logid=11024440026886552183&ipn=rj&ct=201326592&is=&fp=result&fr=&" +
                    "word=" + urlencodeKewy + "&cg=head&" +
                    "queryWord=" + urlencodeKewy + "&cl=2&lm=-1&ie=utf-8&oe=utf-8&adpicid=&st=-1&z=&ic=0&hd=&latest=&copyright=&s=&se=&tab=&width=&height=&face=0&istype=2&qc=&nc=1&expermode=&nojc=&isAsync=&" +
                    "pn=" + i + "&" +
                    "rn=" + size + "&gsm=3c&1640678118468=";
            String s = HttpUtil.get(url);
            BaiduImagesBean baiduImagesBean = JSONObject.parseObject(s, BaiduImagesBean.class);
            List<BaiduImageBean> data = baiduImagesBean.getData();
            for (BaiduImageBean datum : data) {
                System.out.println(datum);
                if (StringUtils.isNotBlank(datum.getHoverURL())) {
                    FileUtil.downloadImage("D:/images/", datum.getHoverURL(), UUID.randomUUID().toString().replace("-", ""), datum.getType());
                }
            }
        }
        long time = SystemClock.now() - beginTime;
        System.out.println(time/1000);
    }

    public void start () {
        System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
}
