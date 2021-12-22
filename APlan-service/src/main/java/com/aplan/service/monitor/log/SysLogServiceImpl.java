package com.aplan.service.monitor.log;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aplan.common.Oauth2Response;
import com.aplan.bean.param.PageParam;
import com.aplan.bean.model.SysLogModel;
import com.aplan.mapper.SysLogMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("sysLogService")
@Transactional
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLogModel> implements SysLogService {


    @Resource
    private SysLogMapper sysLogMapper;


    @Override
    public Oauth2Response queryAllByLimit(SysLogModel sysLogModel, PageParam<SysLogModel> page) {
//        if (sysLogModel.getValue2().length > 0  && sysLogModel.getValue2() != null) {

//                sysLogModel.setStartTime(sysLogModel.getValue2()[0]);
//                sysLogModel.setEndTime(sysLogModel.getValue2()[1]);

//        }
        List<SysLogModel> sysLogModels = sysLogMapper.queryAllByLimit(sysLogModel, page);
        return Oauth2Response.pageQuerySuccess(sysLogModels, page.getTotal());
    }
}