package com.aplan.controller.param;

import com.aplan.bean.model.SysParamModel;
import com.aplan.bean.param.PageParam;
import com.aplan.bean.request.ParamRequest;
import com.aplan.common.Oauth2Response;
import com.aplan.service.system.param.SysParamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ma.glasnost.orika.MapperFacade;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "参数接口组")
@RestController
@RequestMapping("/sys/param")
public class SysParamController {


    @Resource
    private SysParamService sysParamService;
    @Resource
    private MapperFacade mapperFacade;

    @ApiOperation(value = "分页查询")
    @GetMapping("/page")
    public Oauth2Response page(ParamRequest sq) {
        PageParam<SysParamModel> page = new PageParam<>();
        page.setCurrent(sq.getCurrent());
        page.setSize(sq.getSize());

        SysParamModel sp = mapperFacade.map(sq, SysParamModel.class);
        return this.sysParamService.queryAllByLimit(sp, page);
    }


    @ApiOperation(value = "新增")
    @PostMapping()
    public Oauth2Response save(
            @ApiParam(value = "参数model", required = true) @RequestBody SysParamModel sp) {
        return this.sysParamService.insert(sp);
    }


    @ApiOperation(value = "更新")
    @PutMapping()
    public Oauth2Response update(
            @ApiParam(value = "参数model", required = true) @RequestBody SysParamModel sp) {
        return this.sysParamService.updateParam(sp);
    }


    @ApiOperation(value = "批量删除")
    @DeleteMapping("/btc")
    public Oauth2Response additiveBtc(
            @ApiParam(value = "参数id数组", required = true) @RequestParam("ids") List<String> ids) {
        return this.sysParamService.deleteBtc(ids);
    }


}