package com.aplan.service.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.aplan.bean.model.SysUserModel;
import com.aplan.mapper.SysRoleMapper;
import com.aplan.mapper.SysRuleMapper;
import com.aplan.service.middle.SysUserRoleService;
import com.aplan.service.role.SysRoleService;
import com.aplan.service.user.SysUserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lgsh
 * @Description:
 * @date 2021/8/10 15:21
 */
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private SysUserService userService;
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private SysUserRoleService userRoleService;
    @Autowired
    private SysRuleMapper sysRuleMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;


    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中取出用户信息
        SysUserModel user = userService.getOne(new LambdaQueryWrapper<SysUserModel>().eq(SysUserModel::getUsername,username));

        // 判断用户是否存在
        if(user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        List<String> roles = sysRoleMapper.selectSysRuleModelByUserId(user.getUserId());
        List<String> rules = getUserPermissions(user.getUserId());

        user.setRoles(roles);
        user.setRules(rules);
        return user;
    }


    // 获取当前登录用户的权限
    private List<String> getUserPermissions(String userId) {
        List<String> permsList;

        //系统管理员，拥有最高权限
        if("1".equals(userId)){
            permsList = sysRuleMapper.selectByUserId(null);
        }else{
            permsList = sysRuleMapper.selectByUserId(userId);
        }
        return permsList;
    }

}
