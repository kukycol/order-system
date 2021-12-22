package com.aplan.controller;

import com.aplan.annotation.SysLog;
import com.aplan.bean.dto.SysRuleDto;
import com.aplan.bean.model.SysRuleModel;
import com.aplan.bean.param.PageParam;
import com.aplan.bean.request.RuleRequest;
import com.aplan.common.Oauth2Response;
import com.aplan.service.rule.SysRuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ma.glasnost.orika.MapperFacade;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "权限接口组")
@RestController
@RequestMapping("/sys/rule")
public class SysRuleController {
    
    
    @Resource
    private SysRuleService sysRuleService;
    @Resource
    private MapperFacade mapperFacade;

    @ApiOperation("获取数据")
    @SysLog("获取权限数据")
    @GetMapping("/info")
    @PreAuthorize("@pms.hasPermission('sys:rule:info')")
    public SysRuleModel info(
            @ApiParam(value = "权限id",required = true) String id) {
        return this.sysRuleService.queryById(id);
    }


    @ApiOperation("分页查询")
    @SysLog("获取权限分页数据")
    @GetMapping("/page")
    @PreAuthorize("@pms.hasPermission('sys:rule:search')")
    public Oauth2Response page(RuleRequest sq) {
        PageParam<SysRuleModel> page = new PageParam<>();
        page.setCurrent(sq.getCurrent());
        page.setSize(sq.getSize());

        SysRuleModel sp = mapperFacade.map(sq, SysRuleModel.class);
        return this.sysRuleService.queryAllByLimit(sp,page);
    }


    @ApiOperation("获取条件批量数据（树型数据）")
    @SysLog("获取树型权限数据")
    @GetMapping("/treeTable")
    @PreAuthorize("@pms.hasPermission('sys:rule:tree:table')")
    public Oauth2Response treeTable(
            @ApiParam(value = "权限model",required = true) SysRuleModel sysRuleModel) {
        return this.sysRuleService.treeTable(sysRuleModel);
    }


    @ApiOperation("获取条件批量数据（父级树型数据）")
    @SysLog("获取父级权限数据")
    @GetMapping("/parentTreeTableList")
    @PreAuthorize("@pms.hasPermission('sys:rule:tree:table:list')")
    public Oauth2Response parentTreeTableList(
            @ApiParam(value = "权限model",required = true) SysRuleDto sysRuleModel) {
        return this.sysRuleService.parentTreeTableList(sysRuleModel);
    }


    @ApiOperation("新增")
    @SysLog("添加权限数据")
    @PostMapping()
    @PreAuthorize("@pms.hasPermission('sys:rule:save')")
    public Oauth2Response save(
            @ApiParam(value = "权限model",required = true) @RequestBody SysRuleModel sysRule) {
        return this.sysRuleService.insert(sysRule);
    }


    @ApiOperation("更新")
    @SysLog("更新权限数据")
    @PutMapping()
    @PreAuthorize("@pms.hasPermission('sys:rule:update')")
    public Oauth2Response update(
            @ApiParam(value = "权限model",required = true) @RequestBody SysRuleModel sysRule) {
        return this.sysRuleService.update(sysRule);
    }


    @ApiOperation("删除")
    @SysLog("删除权限数据")
    @DeleteMapping("{id}")
    @PreAuthorize("@pms.hasPermission('sys:rule:del')")
    public Oauth2Response del(
            @ApiParam(value = "权限id",required = true) @PathVariable("id") String id) {
         return this.sysRuleService.deleteById(id);
    }
    
}