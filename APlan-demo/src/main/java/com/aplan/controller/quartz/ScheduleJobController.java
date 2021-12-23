
package com.aplan.controller.quartz;

import com.aplan.annotation.SysLog;
import com.aplan.bean.model.quartz.ScheduleJob;
import com.aplan.bean.param.PageParam;
import com.aplan.bean.request.JobRequest;
import com.aplan.common.Oauth2Response;
import com.aplan.service.quartz.ScheduleJobService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@Api(tags = "定时任务接口组")
@RestController
@RequestMapping("/sys/schedule")
public class ScheduleJobController {


    @Autowired
    private ScheduleJobService scheduleJobService;
    @Resource
    private MapperFacade mapperFacade;

    @ApiOperation("新增")
    @PostMapping
    public Oauth2Response save(
            @ApiParam(value = "定时任务model", required = true) @RequestBody ScheduleJob scheduleJob) {

        long dbAlikeCount = scheduleJobService.count(new LambdaQueryWrapper<ScheduleJob>()
                .eq(ScheduleJob::getBeanName, scheduleJob.getBeanName())
                .eq(ScheduleJob::getMethodName, scheduleJob.getMethodName())
        );
        if (dbAlikeCount > 0) {
            return Oauth2Response.saveSuccess();
        }

        return scheduleJobService.saveAndStart(scheduleJob);
    }


    @ApiOperation("运行")
    @PostMapping("/run")
    public Oauth2Response run(
            @ApiParam(value = "定时任务id数组", required = true) @RequestBody Long[] jobIds) {
        scheduleJobService.run(jobIds);
        return Oauth2Response.saveSuccess();
    }

    @ApiOperation("分页数据")
    @SysLog("分页数据")
    @GetMapping("/page")
    public Oauth2Response page(JobRequest sq) {
        PageParam<ScheduleJob> page = new PageParam<>();
        page.setCurrent(sq.getCurrent());
        page.setSize(sq.getSize());

        ScheduleJob sp = mapperFacade.map(sq, ScheduleJob.class);
        return this.scheduleJobService.queryAllByLimit(sp, page);
    }

    @ApiOperation("批量删除")
    @SysLog("批量删除")
    @DeleteMapping("/btc")
//	@PreAuthorize("@pms.hasPermission('sys:user:btc')")
    public Oauth2Response btcDel(@RequestParam("ids") List<Integer> ids) {
        return scheduleJobService.deleteByIds(ids);
    }

    @ApiOperation("更新")
    @SysLog("更新")
    @PutMapping()
//	@PreAuthorize("@pms.hasPermission('sys:user:update')")
    public Oauth2Response update(
            @ApiParam(value = "定时任务model", required = true) @RequestBody ScheduleJob scheduleJob) {
        return this.scheduleJobService.updateScheduleJob(scheduleJob);
    }

    @ApiOperation("更新状态")
    @SysLog("暂停和恢复")
    @PutMapping("/updateStatus")
//	@PreAuthorize("@pms.hasPermission('sys:user:update')")
    public Oauth2Response stopAndRegain(
             @RequestBody ScheduleJob scheduleJob) {
        return this.scheduleJobService.updateStatus(scheduleJob);
    }


}
