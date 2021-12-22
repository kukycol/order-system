package com.aplan.controller.depart;

import com.aplan.annotation.SysLog;
import com.aplan.bean.dto.SysDepartDto;
import com.aplan.bean.model.SysDepartModel;
import com.aplan.common.Oauth2Response;
import com.aplan.service.system.depart.SysDepartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "部门")
@RestController
@RequestMapping("/sys/depart")
public class DepartController {

    @Resource
    private SysDepartService sysDepartService;

    @ApiOperation("获取树型数据")
    @SysLog("获取树型数据")
    @GetMapping("/treeTable")
    @PreAuthorize("@pms.hasPermission('sys:depart:tree:table')")
    public Oauth2Response treeTable(SysDepartDto sysDepartDto) {
        return this.sysDepartService.treeTable(sysDepartDto);
    }


    @ApiOperation("获取父级数据")
    @SysLog("获取父级数据")
    @GetMapping("/parent/TableList")
    @PreAuthorize("@pms.hasPermission('sys:depart:tree:table:list')")
    public Oauth2Response parentTreeTableList(SysDepartDto sysDepartDto) {
        return this.sysDepartService.parentTreeTableList(sysDepartDto);
    }

    @ApiOperation("添加数据")
    @SysLog("添加数据")
    @PostMapping()
    @PreAuthorize("@pms.hasPermission('sys:depart:tree:save')")
    public Oauth2Response save(@RequestBody SysDepartModel sysDepartModel) {
        return this.sysDepartService.insert(sysDepartModel);
    }

    @ApiOperation("更新数据")
    @SysLog("更新数据")
    @PutMapping()
    @PreAuthorize("@pms.hasPermission('sys:depart:tree:update')")
    public Oauth2Response update(@RequestBody SysDepartModel sysDepartModel) {
        return this.sysDepartService.SysDepartModelUpdate(sysDepartModel);
    }

    @ApiOperation("删除数据")
    @SysLog("删除数据")
    @DeleteMapping("{id}")
    @PreAuthorize("@pms.hasPermission('sys:depart:tree:delete')")
    public Oauth2Response del(@PathVariable("id") String id) {
        return this.sysDepartService.deleteById(id);
    }

}
