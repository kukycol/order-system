package com.aplan.task;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.aplan.bean.model.SysUserOnlineModel;
import com.aplan.mapper.SysUserOnlineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component("onlineTask")
public class UserOnlineTask {

    @Autowired
    private SysUserOnlineMapper sysUserOnlineMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    public static final String REDIS_AUTH_KEY = "auth:";


    public void checkOnline(){
        // 获取全部登录成功的用户
        List<SysUserOnlineModel> sysUserOnlineModels = sysUserOnlineMapper.selectList(new LambdaQueryWrapper<SysUserOnlineModel>().eq(SysUserOnlineModel::getStatus, 0));
        for (SysUserOnlineModel sysUserOnlineModel : sysUserOnlineModels) {

            // 从redis获取全部token
            Set<String> keysSet = redisTemplate.keys(REDIS_AUTH_KEY + "*");
            List<String> keysList = new ArrayList<>();
            for (String key : keysSet) {
                String token = key.substring(key.indexOf(":") + 1);
                keysList.add(token);
            }

            // 匹配不上，token已过期
            if (!keysList.contains(sysUserOnlineModel.getToken())){
                SysUserOnlineModel sysUserOnlineModel1 = new SysUserOnlineModel();
                sysUserOnlineModel1.setOnlineId(sysUserOnlineModel.getOnlineId());
                sysUserOnlineModel1.setStatus(1);
                sysUserOnlineMapper.updateById(sysUserOnlineModel1);
            }
        }
    }

}
