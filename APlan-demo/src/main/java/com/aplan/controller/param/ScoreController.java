package com.aplan.controller.param;

import com.aplan.bean.dto.StatisticsDto;
import com.aplan.bean.model.ScoreModel;
import com.aplan.bean.model.SysUserModel;
import com.aplan.bean.param.PageParam;
import com.aplan.common.Oauth2Response;
import com.aplan.service.system.param.ScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "评分接口组")
@RestController
@RequestMapping("/sys/score")
public class ScoreController {


    @Resource
    private ScoreService scoreService;


    @ApiOperation(value = "分页查询")
    @GetMapping("/page")
    public Oauth2Response page(
            @ApiParam(value = "评分model", required = true) ScoreModel sp,
            @ApiParam(value = "评分分页评分", required = true) PageParam<ScoreModel> page) {
        return this.scoreService.queryAllByLimit(sp, page);
    }

    @ApiOperation(value = "获取数据")
    @GetMapping("/getDepartEmployees")
    public Oauth2Response getDepartEmployees() {
        SysUserModel sysUserModel = (SysUserModel)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String departId = sysUserModel.getDepartId();
        String userId = sysUserModel.getUserId();
        return this.scoreService.getDepartEmployees(departId, userId);
    }


    @ApiOperation(value = "新增（员工评分）")
    @PostMapping("/submitDepartEmployees")
    public Oauth2Response submitDepartEmployees(@RequestBody StatisticsDto statisticsDto) {
        SysUserModel sysUserModel = (SysUserModel)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        statisticsDto.setUserId(sysUserModel.getUserId());
        return this.scoreService.submitDepartEmployees(statisticsDto);
    }



    @ApiOperation(value = "更新状态")
    @PutMapping("/updateStatus")
    public Oauth2Response updateStatus() {
        return this.scoreService.updateStatus();
    }


    @ApiOperation(value = "获取活动状态")
    @GetMapping("/getActivityStatus")
    public Oauth2Response getActivityStatus() {
        return this.scoreService.getActivityStatus();
    }


    @ApiOperation(value = "新增")
    @PostMapping()
    public Oauth2Response save(
            @ApiParam(value = "评分model", required = true) @RequestBody ScoreModel sp) {
        return this.scoreService.insert(sp);
    }


    @ApiOperation(value = "更新")
    @PutMapping()
    public Oauth2Response update(
            @ApiParam(value = "评分model", required = true) @RequestBody ScoreModel sp) {
        return this.scoreService.updateScore(sp);
    }


    @ApiOperation(value = "批量删除")
    @DeleteMapping("/btc")
    public Oauth2Response additiveBtc(
            @ApiParam(value = "评分id数组", required = true) @RequestParam("ids") List<String> ids) {
        return this.scoreService.deleteBtc(ids);
    }


}