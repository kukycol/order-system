package com.aplan.controller.system;

import com.aplan.annotation.SysLog;
import com.aplan.bean.dto.UserOnlineDto;
import com.aplan.bean.model.SysUserOnlineModel;
import com.aplan.bean.param.PageParam;
import com.aplan.common.Oauth2Response;
import com.aplan.service.system.online.SysUserOnlineService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "在线用户接口组")
@RestController
@RequestMapping("/sys/user/online")
public class SysUserOnlineController {


    @Resource
    private SysUserOnlineService sysUserOnlineService;

    @Autowired
    @Qualifier("consumerTokenServices")
    private ConsumerTokenServices consumerTokenServices;

    @ApiOperation("分页查询")
    @SysLog("查询用户分页数据")
    @GetMapping("/page")
    @PreAuthorize("@pms.hasPermission('sys:user:search') || @pms.hasPermission('sys:user:refresh')")
    public Oauth2Response page(
            @ApiParam(value = "在线用户dto", required = true) UserOnlineDto userOnlineDto,
            @ApiParam(value = "在线用户分页查询", required = true) PageParam<UserOnlineDto> page) {
        return this.sysUserOnlineService.queryAllByLimit(userOnlineDto, page);
    }


    @ApiOperation("更新")
//    @SysLog("强制退出")
    @DeleteMapping("/quit")
    @PreAuthorize("@pms.hasPermission('sys:user:search') || @pms.hasPermission('sys:user:refresh')")
    public Oauth2Response quit(
            @ApiParam(value = "token数组", required = true) @RequestParam("keys") List<String> keys) {
        for (String token : keys) {
//            SecurityContextHolder.clearContext();
                consumerTokenServices.revokeToken(token);
                SysUserOnlineModel sysUserOnlineModel1 = new SysUserOnlineModel();
                sysUserOnlineModel1.setStatus(1);
                sysUserOnlineService.update(sysUserOnlineModel1, new LambdaQueryWrapper<SysUserOnlineModel>().eq(SysUserOnlineModel::getToken, token));
        }

        Oauth2Response result = new Oauth2Response();
        result.setCode(20000);
        result.setMessage("强退成功");
        return result;
    }


}