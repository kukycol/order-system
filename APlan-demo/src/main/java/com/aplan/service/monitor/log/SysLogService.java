package com.aplan.service.monitor.log;

import com.baomidou.mybatisplus.extension.service.IService;
import com.aplan.common.Oauth2Response;
import com.aplan.bean.param.PageParam;
import com.aplan.bean.model.SysLogModel;

public interface SysLogService extends IService<SysLogModel>{


    Oauth2Response queryAllByLimit(SysLogModel sysLogModel, PageParam<SysLogModel> page);
}