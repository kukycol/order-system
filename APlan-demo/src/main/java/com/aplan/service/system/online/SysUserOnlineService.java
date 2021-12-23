package com.aplan.service.system.online;

import com.baomidou.mybatisplus.extension.service.IService;
import com.aplan.common.Oauth2Response;
import com.aplan.bean.param.PageParam;
import com.aplan.bean.dto.UserOnlineDto;
import com.aplan.bean.model.SysUserOnlineModel;

public interface SysUserOnlineService extends IService<SysUserOnlineModel> {


    Oauth2Response queryAllByLimit(UserOnlineDto sp, PageParam<UserOnlineDto> page);


}
