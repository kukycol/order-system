package com.aplan.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.aplan.common.Oauth2Response;
import com.aplan.bean.param.PageParam;
import com.aplan.bean.dto.SysUserDto;
import com.aplan.bean.model.SysUserModel;
import com.aplan.bean.model.SysUserUpdatePassword;

import java.util.List;
import java.util.Map;

public interface SysUserService extends IService<SysUserModel> {


    SysUserModel queryById(String id);

    Oauth2Response queryAllByLimit(SysUserDto sysUser, PageParam<SysUserDto> page);

    Oauth2Response insert(SysUserDto sysUser);

    Oauth2Response update(SysUserDto sysUser);

    Oauth2Response deleteById(String id);

    Oauth2Response deleteByIds(List<String> ids);

    Oauth2Response updateStatus(SysUserDto sysUser);

    Oauth2Response updatePwd(SysUserUpdatePassword sysUser);

    Oauth2Response updateRestPwd(SysUserDto sysUser);

    Map getNav(SysUserModel principal);
}