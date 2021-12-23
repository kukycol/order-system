package com.aplan.controller;

import com.aplan.annotation.SysLog;
import com.aplan.bean.model.SysRoleModel;
import com.aplan.bean.param.PageParam;
import com.aplan.bean.request.RoleRequest;
import com.aplan.common.Oauth2Response;
import com.aplan.service.role.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ma.glasnost.orika.MapperFacade;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "角色接口组")
@RestController
@RequestMapping("/sys/role")
public class SysRoleController {
    
    
    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private MapperFacade mapperFacade;

    @ApiOperation("分页查询")
    @SysLog("获取角色分页数据")
    @GetMapping("/page")
    @PreAuthorize("@pms.hasPermission('sys:role:search') || @pms.hasPermission('sys:role:refresh')")
    public Oauth2Response page(RoleRequest sq) {
        PageParam<SysRoleModel> page = new PageParam<>();
        page.setCurrent(sq.getCurrent());
        page.setSize(sq.getSize());

        SysRoleModel sp = mapperFacade.map(sq, SysRoleModel.class);
        return this.sysRoleService.queryAllByLimit(sp, page);
    }


    @ApiOperation("获取批量数据")
    @SysLog("获取角色列表数据")
    @GetMapping("/roleList")
    @PreAuthorize("@pms.hasPermission('sys:role:list')")
    public Oauth2Response roleList() {
        return this.sysRoleService.roleList();
    }


    @ApiOperation("新增")
    @SysLog("添加角色数据")
    @PostMapping()
    @PreAuthorize("@pms.hasPermission('sys:role:save')")
    public Oauth2Response save(
            @ApiParam(value = "角色model",required = true) @RequestBody SysRoleModel sysRole) {
        return this.sysRoleService.insert(sysRole);
    }


    @ApiOperation("更新")
    @SysLog("更新角色数据")
    @PutMapping()
    @PreAuthorize("@pms.hasPermission('sys:role:update')")
    public Oauth2Response update(
            @ApiParam(value = "角色model",required = true)@RequestBody SysRoleModel sysRole) {
        return this.sysRoleService.update(sysRole);
    }


    @ApiOperation("删除")
    @SysLog("删除角色数据")
    @DeleteMapping("{id}")
    @PreAuthorize("@pms.hasPermission('sys:role:del')")
    public Oauth2Response del(
            @ApiParam(value = "角色id",required = true)@PathVariable("id") String id) {
        return this.sysRoleService.deleteById(id);
    }


    @ApiOperation("批量删除")
    @SysLog("批量删除角色数据")
    @DeleteMapping("/btcDel")
    @PreAuthorize("@pms.hasPermission('sys:role:btc')")
    public Oauth2Response btcDel(
            @ApiParam(value = "角色id数组",required = true)@RequestParam("ids") List<String> ids) {
        return sysRoleService.deleteByIds(ids);
    }


}