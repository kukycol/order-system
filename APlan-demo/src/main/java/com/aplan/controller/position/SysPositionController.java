package com.aplan.controller.position;

import com.aplan.bean.dto.DepartPositionDto;
import com.aplan.bean.model.SysDepartPositionModel;
import com.aplan.bean.param.PageParam;
import com.aplan.bean.request.PositionRequest;
import com.aplan.common.Oauth2Response;
import com.aplan.service.system.position.SysDepartPositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ma.glasnost.orika.MapperFacade;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "职位接口组")
@RestController
@RequestMapping("/sys/depart/position")
public class SysPositionController {


    @Resource
    private SysDepartPositionService sysDepartPositionService;
    @Resource
    private MapperFacade mapperFacade;

    @ApiOperation(value = "分页查询")
    @GetMapping("/page")
    public Oauth2Response page(PositionRequest sq) {
        PageParam<DepartPositionDto> page = new PageParam<>();
        page.setCurrent(sq.getCurrent());
        page.setSize(sq.getSize());

        DepartPositionDto sp = mapperFacade.map(sq, DepartPositionDto.class);
        return this.sysDepartPositionService.queryAllByLimit(sp,page);
    }


    @ApiOperation(value = "获取批量数据")
    @GetMapping("/list/{departId}")
    public Oauth2Response list(
            @ApiParam(value = "职位id",required = true)@PathVariable("departId")String departId) {
        return this.sysDepartPositionService.queryAll(departId);
    }


    @ApiOperation(value = "新增")
    @PostMapping()
    public Oauth2Response save(
            @ApiParam(value = "职位model",required = true) @RequestBody SysDepartPositionModel sp) {
        return this.sysDepartPositionService.insert(sp);
    }


    @ApiOperation(value = "更新")
    @PutMapping()
    public Oauth2Response update(
            @ApiParam(value = "职位model",required = true) @RequestBody SysDepartPositionModel sp) {
        return this.sysDepartPositionService.updateDepartPosition(sp);
    }


    @ApiOperation(value = "批量删除")
    @DeleteMapping("/btc")
    public Oauth2Response additiveBtc(
            @ApiParam(value = "职位ids数组",required = true) @RequestParam("ids") List<String> ids) {
        return this.sysDepartPositionService.deleteBtc(ids);
    }



}