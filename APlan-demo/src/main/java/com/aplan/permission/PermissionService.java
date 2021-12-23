
package com.aplan.permission;

import cn.hutool.core.util.StrUtil;
import com.aplan.bean.model.SysUserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 接口权限判断工具
 */
@Slf4j
@Component("pms")
public class PermissionService {

    /**
     * 判断接口是否有xxx:xxx权限
     *
     * @param permission 权限
     * @return {boolean}
     */
    public boolean hasPermission(String permission) {
        if (StrUtil.isBlank(permission)) {
            return false;
        }
        SysUserModel authentication = (SysUserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (authentication == null) {
            return false;
        }
        List<String> rules = authentication.getRules();
        //多个权限
        String[] split = permission.split(",");
        boolean contains = false;
        for (String s : split) {
            if (rules.contains(s)){
                contains = true;
            }
        }
        return contains;
    }


}