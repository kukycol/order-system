package com.aplan.task;

import com.aplan.bean.model.HeatModel;
import com.aplan.grab.baidu.realtime.BaiduRealtimeDemo;
import com.aplan.grab.weibo.WeiboRealtimeDemo;
import com.aplan.mapper.HeatMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component("heatTask")
public class HeatTask {

    @Resource
    private HeatMapper heatMapper;

    /**
     * @Description: 百度热度排名
     * @Author: kuky
     * @Date: 2021/12/29 15:27
     * @param: 
     * @Return void        
     * @Version: 0.0.1      
     */
    public void baiduGrab() {
        List<HeatModel> hotData = BaiduRealtimeDemo.getHotData();
        for (HeatModel hotDatum : hotData) {
            HeatModel heatModels = heatMapper.selectByTypeAndPlatFormTypeAndTitle(hotDatum);
            if (heatModels != null){
                heatModels.setHeatNumber(hotDatum.getHeatNumber());
                heatModels.setUrl(hotDatum.getUrl());
                heatModels.setUpdateTime(new Date());
                heatMapper.updateById(heatModels);
                continue;
            }
            heatMapper.insert(hotDatum);
        }
    }

    /**
     * @Description: 微博热度排名
     * @Author: kuky
     * @Date: 2021/12/29 15:27
     * @param:
     * @Return void
     * @Version: 0.0.1
     */
    public void weiboGrab() {
        List<HeatModel> hotData = WeiboRealtimeDemo.weiboGrab();
        for (HeatModel hotDatum : hotData) {
            HeatModel heatModels = heatMapper.selectByTypeAndPlatFormTypeAndTitle(hotDatum);
            if (heatModels != null){
                heatModels.setHeatNumber(hotDatum.getHeatNumber());
                heatModels.setUrl(hotDatum.getUrl());
                heatModels.setUpdateTime(new Date());
                heatMapper.updateById(heatModels);
                continue;
            }
            heatMapper.insert(hotDatum);
        }
    }

}
