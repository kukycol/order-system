package com.aplan.service.middle;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aplan.bean.param.PageParam;
import com.aplan.bean.model.SysRuleRoleModel;
import com.aplan.mapper.SysRuleRoleMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("sysRuleRoleService")
@Transactional
public class SysRuleRoleServiceImpl extends ServiceImpl<SysRuleRoleMapper,SysRuleRoleModel> implements SysRuleRoleService {


    @Resource
    private SysRuleRoleMapper sysRuleRoleMapper;

    


    
    @Override
    public IPage<SysRuleRoleModel> queryAllByLimit(SysRuleRoleModel sysRuleRole, PageParam<SysRuleRoleModel> page) {
        PageParam<SysRuleRoleModel> pageParam = this.sysRuleRoleMapper.selectPage(page, 
                new LambdaQueryWrapper<SysRuleRoleModel>()
                                        .eq(StringUtils.isNotBlank(sysRuleRole.getRuleId()),SysRuleRoleModel::getRuleId,sysRuleRole.getRuleId())
                                        .eq(StringUtils.isNotBlank(sysRuleRole.getRoleId()),SysRuleRoleModel::getRoleId,sysRuleRole.getRoleId())
                        );
        return pageParam;
    }

    
    @Override
    public void insert(SysRuleRoleModel sysRuleRole) {
        this.sysRuleRoleMapper.insert(sysRuleRole);
    }

    
    @Override
    public void update(SysRuleRoleModel sysRuleRole) {
        this.sysRuleRoleMapper.updateById(sysRuleRole);
    }

    

}