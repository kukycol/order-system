package com.aplan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.aplan.bean.model.SysRuleRoleModel;

public interface SysRuleRoleMapper extends BaseMapper<SysRuleRoleModel> {


    int deleteByRoleId(String roleId);

    int deleteByRuleId(String ruleId);
}