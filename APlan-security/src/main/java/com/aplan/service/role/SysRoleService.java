package com.aplan.service.role;

import com.baomidou.mybatisplus.extension.service.IService;
import com.aplan.common.Oauth2Response;
import com.aplan.bean.param.PageParam;
import com.aplan.bean.model.SysRoleModel;

import java.util.List;

public interface SysRoleService extends IService<SysRoleModel> {




    Oauth2Response queryAllByLimit(SysRoleModel sysRole, PageParam<SysRoleModel> page);

    Oauth2Response insert(SysRoleModel sysRole);

    Oauth2Response update(SysRoleModel sysRole);

    Oauth2Response deleteById(String id);

    Oauth2Response deleteByIds(List<String> ids);

    Oauth2Response roleList();

}