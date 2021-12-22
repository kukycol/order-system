package com.aplan.service.middle;


import com.aplan.bean.model.SysRuleRoleModel;
import com.aplan.bean.param.PageParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SysRuleRoleService extends IService<SysRuleRoleModel> {




    IPage<SysRuleRoleModel> queryAllByLimit(SysRuleRoleModel sysRuleRole, PageParam<SysRuleRoleModel> page);

 
    void insert(SysRuleRoleModel sysRuleRole);


    void update(SysRuleRoleModel sysRuleRole);

 

}