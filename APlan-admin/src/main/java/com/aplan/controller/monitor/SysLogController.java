package com.aplan.controller.monitor;

import com.aplan.annotation.SysLog;
import com.aplan.bean.model.SysLogModel;
import com.aplan.bean.param.PageParam;
import com.aplan.bean.request.LogRequest;
import com.aplan.common.Oauth2Response;
import com.aplan.service.monitor.log.SysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "操作日志接口组")
@RestController
@RequestMapping("/sys/log")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;
    @Resource
    private MapperFacade mapperFacade;

    @ApiOperation("分页查询")
    @SysLog("查询操作日志")
    @GetMapping("/page")
//    @PreAuthorize("@pms.hasPermission('sys:user:search') || @pms.hasPermission('sys:user:refresh')")
    public Oauth2Response page(LogRequest sq) {

        PageParam<SysLogModel> page = new PageParam<>();
        page.setCurrent(sq.getCurrent());
        page.setSize(sq.getSize());

        SysLogModel sp = mapperFacade.map(sq, SysLogModel.class);
        return this.sysLogService.queryAllByLimit(sp, page);
    }

}
