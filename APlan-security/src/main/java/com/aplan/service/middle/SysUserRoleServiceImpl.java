package com.aplan.service.middle;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aplan.bean.param.PageParam;
import com.aplan.bean.model.SysUserRoleModel;
import com.aplan.mapper.SysUserRoleMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("sysUserRoleService")
@Transactional
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper,SysUserRoleModel> implements SysUserRoleService {


    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    


    
    @Override
    public IPage<SysUserRoleModel> queryAllByLimit(SysUserRoleModel sysUserRole, PageParam<SysUserRoleModel> page) {
        PageParam<SysUserRoleModel> pageParam = this.sysUserRoleMapper.selectPage(page, 
                new LambdaQueryWrapper<SysUserRoleModel>()
                                        .eq(StringUtils.isNotBlank(sysUserRole.getRoleId()),SysUserRoleModel::getRoleId,sysUserRole.getRoleId())
                                        .eq(StringUtils.isNotBlank(sysUserRole.getUserId()),SysUserRoleModel::getUserId,sysUserRole.getUserId())
                        );
        return pageParam;
    }

    
    @Override
    public void insert(SysUserRoleModel sysUserRole) {
        this.sysUserRoleMapper.insert(sysUserRole);
    }

    
    @Override
    public void update(SysUserRoleModel sysUserRole) {
        this.sysUserRoleMapper.updateById(sysUserRole);
    }

    

}