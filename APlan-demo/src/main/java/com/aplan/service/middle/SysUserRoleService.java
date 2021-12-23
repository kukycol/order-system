package com.aplan.service.middle;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.aplan.bean.param.PageParam;
import com.aplan.bean.model.SysUserRoleModel;

public interface SysUserRoleService extends IService<SysUserRoleModel> {




    IPage<SysUserRoleModel> queryAllByLimit(SysUserRoleModel sysUserRole, PageParam<SysUserRoleModel> page);

 
    void insert(SysUserRoleModel sysUserRole);


    void update(SysUserRoleModel sysUserRole);

 

}