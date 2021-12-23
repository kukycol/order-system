package com.aplan.service.user;


import com.aplan.bean.model.SysRuleModel;
import com.aplan.bean.model.SysUserModel;
import com.aplan.bean.model.SysUserRoleModel;
import com.aplan.mapper.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aplan.common.Oauth2Response;
import com.aplan.bean.param.PageParam;
import com.aplan.bean.dto.RouterDto;
import com.aplan.bean.dto.SysUserDto;
import com.aplan.bean.model.*;
import com.aplan.exception.custom.Oauth2Exception;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("sysUserService")
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserModel> implements SysUserService {


    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysUserDtoMapper sysUserDtoMapper;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysRuleMapper sysRuleMapper;

    @Override
    public SysUserModel queryById(String id) {
        return this.sysUserMapper.selectById(id);
    }


    @Override
    public Oauth2Response queryAllByLimit(SysUserDto sysUser, PageParam<SysUserDto> page) {
        sysUser.setDepartId(StringUtils.isNoneBlank(sysUser.getDepartId())?sysUser.getDepartId():"无");
        List<SysUserDto> records = this.sysUserDtoMapper.selectSysUserModelPage(page, sysUser);
        return Oauth2Response.pageQuerySuccess(records,page.getTotal());
    }


    @Override
    public Oauth2Response insert(SysUserDto sysUser) {

        String mobile = sysUser.getMobile();
        String username = sysUser.getUsername();

        SysUserDto usernameUserDto = sysUserDtoMapper.selectOne(new LambdaQueryWrapper<SysUserDto>().eq(SysUserDto::getUsername, username));
        if (usernameUserDto != null) {
            return Oauth2Response.dataExist(username);
        }

        SysUserDto mobileUserDto = sysUserDtoMapper.selectOne(new LambdaQueryWrapper<SysUserDto>().eq(SysUserDto::getMobile, mobile));
        if (mobileUserDto != null) {
            return Oauth2Response.dataExist(mobile);
        }

        // 默认密码
        sysUser.setPassword(new BCryptPasswordEncoder().encode("123456"));

        // 用户数据插入
        int insert = this.sysUserDtoMapper.insert(sysUser);
        if (insert == 1) {

            // 成功后插入用户角色数据
            List<String> roles = sysUser.getRoles();
            for (String role : roles) {
                SysUserRoleModel sysUserRoleModel = new SysUserRoleModel();
                sysUserRoleModel.setUserId(sysUser.getUserId());
                sysUserRoleModel.setRoleId(role);
                sysUserRoleMapper.insert(sysUserRoleModel);
            }

            // 成功后插入用户职位数据（一个用户多职位需求时用到）
//            List<String> positions = sysUser.getPositions();
//            for (String position : positions) {
//                SysUserPositionModel sysUserPositionModel = new SysUserPositionModel();
//                sysUserPositionModel.setUserId(sysUser.getUserId());
//                sysUserPositionModel.setPositionId(position);
//                sysUserPositionMapper.insert(sysUserPositionModel);
//            }
            return Oauth2Response.saveSuccess();
        }
        throw Oauth2Exception.saveException();

    }


    @Override
    public Oauth2Response update(SysUserDto sysUser) {

        String mobile = sysUser.getMobile();
        String username = sysUser.getUsername();
        String userId = sysUser.getUserId();

        SysUserDto sysUserDto = sysUserDtoMapper.selectById(userId);
        if (sysUserDto == null) {
            return Oauth2Response.dataNotExist(userId);
        }

        // username唯一判断
        if (!username.equals(sysUserDto.getUsername())) {
            SysUserDto usernameUserDto = sysUserDtoMapper.selectOne(new LambdaQueryWrapper<SysUserDto>().eq(SysUserDto::getUsername, username));
            if (usernameUserDto != null) {
                return Oauth2Response.dataExist(username);
            }
        }

        // mobile唯一判断
        if (!mobile.equals(sysUserDto.getMobile())) {
            SysUserDto mobileUserDto = sysUserDtoMapper.selectOne(new LambdaQueryWrapper<SysUserDto>().eq(SysUserDto::getMobile, mobile));
            if (mobileUserDto != null) {
                return Oauth2Response.dataExist( mobile);
            }
        }

        // 删除用户与角色的关系
        int delRow = sysUserRoleMapper.deleteByUserId(sysUser.getUserId());

        // 更新用户信息
        int updateRow = this.sysUserDtoMapper.updateById(sysUser);
        if (updateRow == 1) {
            // 成功后插入用户角色数据
            List<String> roles = sysUser.getRoles();
            if (roles.size() == 0) {
                throw Oauth2Exception.updateException();
            }
            for (String role : roles) {
                SysUserRoleModel sysUserRoleModel = new SysUserRoleModel();
                sysUserRoleModel.setUserId(sysUser.getUserId());
                sysUserRoleModel.setRoleId(role);
                sysUserRoleMapper.insert(sysUserRoleModel);
            }
            return Oauth2Response.updateSuccess();
        }
        throw Oauth2Exception.updateException();
    }


    @Override
    public Oauth2Response deleteById(String id) {
        int userDelRow = this.sysUserDtoMapper.deleteById(id);
        if (userDelRow == 1) {
            int userRoleRow = sysUserRoleMapper.deleteByUserId(id);
            if (userRoleRow != 0) {
                throw Oauth2Exception.removeException();
            }
            return Oauth2Response.removeSuccess();
        }
        throw Oauth2Exception.removeException();
    }

    @Override
    public Oauth2Response deleteByIds(List<String> ids) {
        int btcDel = sysUserDtoMapper.btcDel(ids);
        if (ids.size() == btcDel) {
            for (String id : ids) {
                int userRoleRow = sysUserRoleMapper.deleteByUserId(id);
                if (userRoleRow != 0) {
                    throw Oauth2Exception.removeException();
                }
            }
            return Oauth2Response.removeSuccess();
        }
        throw Oauth2Exception.removeException();
    }

    @Override
    public Oauth2Response updateStatus(SysUserDto sysUser) {
        int update = sysUserDtoMapper.updateById(sysUser);
        if (update == 1) {
            return Oauth2Response.updateSuccess();
        }
        throw Oauth2Exception.updateException();
    }

    @Override
    public Oauth2Response updatePwd(SysUserUpdatePassword sysUser) {
        SysUserModel principal = (SysUserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean matches = new BCryptPasswordEncoder().matches(sysUser.getOldPassword(), principal.getPassword());
        if (!matches){
            return new Oauth2Response(10000, "旧密码不正确");
        }


        if (!sysUser.getNewPassword().equals(sysUser.getConfirmPassword())){
            return new Oauth2Response(10000, "输入的两次密码不一样，请重新输入");
        }

        if (principal.getUserId().equals("1")){
            return new Oauth2Response(10000, "不允许修改超级管理员账号的密码");
        }


        SysUserDto sysUserDto = new SysUserDto();
        sysUserDto.setUserId(principal.getUserId());
        sysUserDto.setPassword(new BCryptPasswordEncoder().encode(sysUser.getNewPassword()));
        int update = sysUserDtoMapper.updateById(sysUserDto);
        if (update == 1) {
            return Oauth2Response.updateSuccess();
        }
        throw Oauth2Exception.updateException();
    }

    @Override
    public Oauth2Response updateRestPwd(SysUserDto sysUser) {
        sysUser.setPassword(new BCryptPasswordEncoder().encode("123456"));
        int update = sysUserDtoMapper.updateById(sysUser);
        if (update == 1) {
            return Oauth2Response.updateSuccess();
        }
        throw Oauth2Exception.updateException();
    }

    @Override
    public Map getNav(SysUserModel principal) {
        List<RouterDto> routerDtos = sysRuleMapper.selectMenus(principal.getUserId().equals("1")?null:principal.getUserId());

        Map<String, Object> map = new HashMap<>();
//        map.put("menus", getUserMenus(principal.getUserId()));
        map.put("menus", routerDtos);
        map.put("rules", getUserPermissions(principal.getUserId()));
        return map;
    }




    // 获取当前登录用户的权限
    private List<String> getUserPermissions(String userId) {
        List<String> permsList;

        //系统管理员，拥有最高权限
        if ("1".equals(userId)) {
            permsList = sysRuleMapper.selectByUserId(null);
        } else {
            permsList = sysRuleMapper.selectByUserId(userId);
        }
        return permsList;
    }

    // 获取当前登录用户的菜单
    private List<SysRuleModel> getUserMenus(String userId) {
//        List<String> ids = sysRoleMapper.selectSysRuleModelByUserId2(userId);

        List<SysRuleModel> sysRuleModels = null;
        if (userId.equals("1")) {
            sysRuleModels = sysRuleMapper.selectByRoleIds(null);
        } else {
            sysRuleModels = sysRuleMapper.selectByRoleIds(userId);
        }

//        List<SysRuleModel> objects = new ArrayList<>();
//        List<SysRuleModel> finalSysRuleModels = sysRuleModels;
//        sysRuleModels.stream().filter(depart->depart.getParentId().equals("0")).forEach(depart->{
//            //找到根节点菜单的时候，寻找这个根节点菜单下的子节点菜单。
//            findChilds(depart, finalSysRuleModels);
//            //添加到根节点的列表中
//            objects.add(depart);
//        });
        return sysRuleModels;
    }

    // 无限树形数据结构
    private void findChilds(SysRuleModel root, List<SysRuleModel> list) {
        List<SysRuleModel> childlist = new ArrayList<>();

        //遍历所有数据，找到是入参父节点的子节点的数据，然后加到childlist集合中。
        list.stream().filter((menu) -> root.getId().equals(menu.getParentId())).forEach((menu) -> {
            childlist.add(menu);
        });

        //若子节点不存在，那么就不必再遍历子节点中的子节点了 直接返回。
        if (childlist.size() == 0) {
            return;
        }
        //设置父节点的子节点列表
        root.setChildren(childlist);

        List<SysRuleModel> finalList = list;
        childlist.forEach((childs) -> {
            findChilds(childs, finalList);
        });


    }



    // 无限树形数据结构
    private void findChilds2(RouterDto root, List<RouterDto> list) {
        List<RouterDto> childlist = new ArrayList<>();

        //遍历所有数据，找到是入参父节点的子节点的数据，然后加到childlist集合中。
        list.stream().filter((menu) -> root.getId().equals(menu.getParentId())).forEach((menu) -> {
            childlist.add(menu);
        });

        //若子节点不存在，那么就不必再遍历子节点中的子节点了 直接返回。
        if (childlist.size() == 0) {
            return;
        }
        //设置父节点的子节点列表
        root.setChildren(childlist);

        List<RouterDto> finalList = list;
        childlist.forEach((childs) -> {
            findChilds2(childs, finalList);
        });


    }

}