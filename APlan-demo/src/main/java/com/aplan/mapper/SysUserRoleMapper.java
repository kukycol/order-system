package com.aplan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.aplan.bean.model.SysUserRoleModel;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserRoleMapper extends BaseMapper<SysUserRoleModel> {


        int deleteByUserId(String userId);
}