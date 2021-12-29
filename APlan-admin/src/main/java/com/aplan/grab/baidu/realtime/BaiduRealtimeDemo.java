package com.aplan.grab.baidu.realtime;

import cn.hutool.core.date.SystemClock;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aplan.bean.model.HeatModel;
import sun.plugin.javascript.JSObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class BaiduRealtimeDemo {


    public static void main(String[] args) {
        List<HeatModel> hotData = getHotData();
        for (HeatModel hotDatum : hotData) {
            System.out.println(hotDatum);
        }
    }


    public static List<HeatModel> getHotData(){
        // String[] realtime = {"realtime", "novel", "movie", "teleplay", "cartoon", "variety", "documentary", "car", "game"};
        String[] realtime = {"realtime", "novel", "movie", "teleplay", "variety", "car", "game"};
        List<HeatModel> list = new ArrayList<>();
        for (int i = 0; i < realtime.length; i++) {
            String url = "https://top.baidu.com/board?tab=" + realtime[i];
            String body = HttpUtil.get(url);
            String startStr = "<!--s-data:";
            String endStr = "-->";
            int startIndexOf = body.indexOf(startStr);
            int endIndexOf = body.indexOf(endStr);
            String substring = body.substring(startIndexOf + startStr.length(), endIndexOf);
            JSONObject dateJson = JSON.parseObject(substring).getJSONObject("data");
            JSONArray cards = dateJson.getJSONArray("cards");
            if (cards.size() > 0) {
                JSONObject cardsJson = cards.getJSONObject(0);
                JSONArray content = cardsJson.getJSONArray("content");
                List<BaiduRealtimeBean> baiduRealtimeBeans = content.toJavaList(BaiduRealtimeBean.class);
                baiduRealtimeBeans.stream().sorted(Comparator.comparing(BaiduRealtimeBean::getIndex));
                for (BaiduRealtimeBean baiduRealtimeBean : baiduRealtimeBeans) {
                    HeatModel heatModel = new HeatModel();
                    heatModel.setDesc(baiduRealtimeBean.getDesc());
                    heatModel.setCreateTime(new Date());
                    heatModel.setIndex(baiduRealtimeBean.getIndex()+1);
                    heatModel.setPlatformType(0);
                    heatModel.setTitle(baiduRealtimeBean.getQuery());
                    heatModel.setUrl(baiduRealtimeBean.getUrl());
                    heatModel.setType(realtime[i]);
                    heatModel.setImg(baiduRealtimeBean.getImg());
                    heatModel.setTag(baiduRealtimeBean.getHotTag());
                    heatModel.setHeatNumber(baiduRealtimeBean.getHotScore());
                    list.add(heatModel);
                }
            }
        }
        return list;
    }


}
