package com.aplan.mapper;

import com.aplan.bean.param.LabelValue;
import com.aplan.bean.param.PageParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


import com.aplan.bean.model.SysRoleModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleMapper extends BaseMapper<SysRoleModel> {

    List<String> selectSysRuleModelByUserId(String userId);

    List<String> selectSysRuleModelByUserId2(String userId);

    List<LabelValue> selectUserRoleByUserId(String userId);

    List<SysRoleModel> selectSysRoleModelPage(PageParam<SysRoleModel> page, @Param("sp") SysRoleModel sp);

    int btcDel(List<String> ids);

    List<SysRoleModel> selectSysRoleList();
}