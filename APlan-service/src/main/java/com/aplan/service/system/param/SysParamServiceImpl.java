package com.aplan.service.system.param;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aplan.common.Oauth2Response;
import com.aplan.bean.param.PageParam;
import com.aplan.bean.model.SysParamModel;
import com.aplan.exception.custom.Oauth2Exception;
import com.aplan.mapper.SysParamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


@RefreshScope
@Service
@Transactional
public class SysParamServiceImpl extends ServiceImpl<SysParamMapper, SysParamModel> implements SysParamService {

    @Autowired
    private SysParamMapper sysParamMapper;
    @Autowired
    private ContextRefresher contextRefresher;
    @Autowired
    private ConfigurableEnvironment environment;

    @Override
    public Oauth2Response queryAllByLimit(SysParamModel sp, PageParam<SysParamModel> page) {
        List<SysParamModel> records = this.sysParamMapper.selectSysParamPage(page, sp);
        return Oauth2Response.pageQuerySuccess(records, page.getTotal());
    }

    @Override
    public Oauth2Response insert(SysParamModel sp) {
        sp.setCreateTime(new Date());
        int row = this.sysParamMapper.insert(sp);
        if (row == 1) {
            refresh(sp.getParamKey(),sp.getParamValue());
            return Oauth2Response.saveSuccess();
        }
        throw Oauth2Exception.removeException();
    }

    @Override
    public Oauth2Response updateParam(SysParamModel sp) {
        int row = this.sysParamMapper.updateById(sp);
        if (row == 1) {
            refresh(sp.getParamKey(),sp.getParamValue());
            return Oauth2Response.updateSuccess();
        }
        throw Oauth2Exception.updateException();
    }

    @Override
    public Oauth2Response deleteBtc(List<String> ids) {
        int row = this.sysParamMapper.btcDel(ids);
        if (row == ids.size()) {
            return Oauth2Response.removeSuccess();
        }
        throw Oauth2Exception.removeException();
    }


    public void refresh(String key,String value){
        //???????????????????????????
        HashMap<String, Object> map = new HashMap<>();
        map.put(key,value);
        MapPropertySource propertySource=new MapPropertySource("dynamic",map);
        //??????????????????????????????environment???
        environment.getPropertySources().addFirst(propertySource);
        //????????????refresh??????????????????????????????????????????
        new Thread(() -> contextRefresher.refresh()).start();
    }


}
