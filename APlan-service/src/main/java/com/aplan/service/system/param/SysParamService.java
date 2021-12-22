package com.aplan.service.system.param;

import com.baomidou.mybatisplus.extension.service.IService;
import com.aplan.common.Oauth2Response;
import com.aplan.bean.param.PageParam;
import com.aplan.bean.model.SysParamModel;

import java.util.List;

public interface SysParamService extends IService<SysParamModel> {


    Oauth2Response queryAllByLimit(SysParamModel sp, PageParam<SysParamModel> page);

    Oauth2Response insert(SysParamModel sp);

    Oauth2Response updateParam(SysParamModel sp);

    Oauth2Response deleteBtc(List<String> ids);

}
