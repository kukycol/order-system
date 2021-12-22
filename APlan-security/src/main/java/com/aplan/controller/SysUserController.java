package com.aplan.controller;

import com.aplan.annotation.ControllerSetter;
import com.aplan.annotation.SysLog;
import com.aplan.bean.dto.SysUserDto;
import com.aplan.bean.model.SysUserModel;
import com.aplan.bean.model.SysUserUpdatePassword;
import com.aplan.bean.param.PageParam;
import com.aplan.bean.request.UserRequest;
import com.aplan.common.Oauth2Response;
import com.aplan.service.user.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ma.glasnost.orika.MapperFacade;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "用户接口组")
@RestController
@RequestMapping("/sys/user")
public class SysUserController {


    @Resource
    private SysUserService sysUserService;
    @Resource
    private MapperFacade mapperFacade;

    @ApiOperation("获取登录数据（用户信息）")
    @SysLog("获取当前登录的用户信息")
    @GetMapping("/getInfo")
    public Oauth2Response getInfo() {
        SysUserModel principal = (SysUserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Oauth2Response.querySuccess(principal);
    }


    @ApiOperation("获取登录数据（菜单和权限信息）")
    @SysLog("查询菜单和权限信息")
    @GetMapping("/getNav")
    public Oauth2Response getNav() {
        SysUserModel principal = (SysUserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Oauth2Response.querySuccess(sysUserService.getNav(principal));
    }


    @ApiOperation("获取信息")
    @SysLog("获取用户信息")
    @GetMapping("/info/{id}")
    @PreAuthorize("@pms.hasPermission('sys:user:info')")
    public SysUserModel info(
            @ApiParam(value = "用户id",required = true)@PathVariable("id") String id) {
        return this.sysUserService.queryById(id);
    }


    @ApiOperation("分页查询")
    @SysLog("查询用户分页数据")
    @GetMapping("/page")
    @PreAuthorize("@pms.hasPermission('sys:user:search')")
    public Oauth2Response page(UserRequest sq) {
        PageParam<SysUserDto> page = new PageParam<>();
        page.setCurrent(sq.getCurrent());
        page.setSize(sq.getSize());

        SysUserDto sp = mapperFacade.map(sq, SysUserDto.class);
        return this.sysUserService.queryAllByLimit(sp, page);
    }


    @ApiOperation("新增")
    @SysLog("添加用户数据")
    @PostMapping()
    @ControllerSetter
    @PreAuthorize("@pms.hasPermission('sys:user:save')")
    public Oauth2Response save(
            @ApiParam(value = "用户dto",required = true)@Valid @RequestBody SysUserDto sysUser) {
        return this.sysUserService.insert(sysUser);
    }


    @ApiOperation("更新")
    @SysLog("更新用户数据")
    @PutMapping()
    @ControllerSetter
    @PreAuthorize("@pms.hasPermission('sys:user:update')")
    public Oauth2Response update(
            @ApiParam(value = "用户dto",required = true)@RequestBody SysUserDto sysUser) {
        return this.sysUserService.update(sysUser);
    }


    @ApiOperation("更新密码")
    @SysLog("用户密码重置")
    @PutMapping("/updateRestPwd")
    @PreAuthorize("@pms.hasPermission('sys:user:reset:password')")
    public Oauth2Response updateRestPwd(
            @ApiParam(value = "用户dto",required = true) @RequestBody SysUserDto sysUser) {
        return this.sysUserService.updateRestPwd(sysUser);
    }


    @ApiOperation("更新密码")
    @SysLog("用户密码修改")
    @PutMapping("/updatePwd")
    @PreAuthorize("@pms.hasPermission('sys:user:update:password')")
    public Oauth2Response updatePwd(
            @ApiParam(value = "用户密码dto",required = true)@RequestBody SysUserUpdatePassword sysUser) {
        return this.sysUserService.updatePwd(sysUser);
    }


    @ApiOperation("更新状态")
    @SysLog("用户的启用和禁用")
    @PutMapping("/updateStatus")
    @PreAuthorize("@pms.hasPermission('sys:user:status')")
    public Oauth2Response updateStatus(
            @ApiParam(value = "用户dto",required = true) @RequestBody SysUserDto sysUser) {
        return this.sysUserService.updateStatus(sysUser);
    }


    @ApiOperation("删除")
    @SysLog("删除用户数据")
    @DeleteMapping("{userId}")
    @PreAuthorize("@pms.hasPermission('sys:user:del')")
    public Oauth2Response del(
            @ApiParam(value = "用户id",required = true) @PathVariable("userId") String userId) {
        List<String> ids = new ArrayList<>();
        ids.add(userId);
        return this.sysUserService.deleteByIds(ids);
    }


    @ApiOperation("批量删除")
    @SysLog("批量删除用户数据")
    @DeleteMapping("/btcDel")
    @PreAuthorize("@pms.hasPermission('sys:user:btc')")
    public Oauth2Response btcDel(
            @ApiParam(value = "用户id数组",required = true)@RequestParam("ids") List<String> ids) {
        return sysUserService.deleteByIds(ids);
    }


}