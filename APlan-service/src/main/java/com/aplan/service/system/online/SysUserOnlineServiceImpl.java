package com.aplan.service.system.online;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aplan.common.Oauth2Response;
import com.aplan.bean.param.PageParam;
import com.aplan.bean.dto.UserOnlineDto;
import com.aplan.bean.model.SysUserOnlineModel;
import com.aplan.mapper.SysUserOnlineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserOnlineServiceImpl extends ServiceImpl<SysUserOnlineMapper, SysUserOnlineModel> implements SysUserOnlineService {

    @Autowired
    private SysUserOnlineMapper sysUserOnlineMapper;

    @Override
    public Oauth2Response queryAllByLimit(UserOnlineDto sp, PageParam<UserOnlineDto> page) {
        List<UserOnlineDto> records = this.sysUserOnlineMapper.selectSysUserOnlinePage(page, sp);
        return Oauth2Response.pageQuerySuccess(records, page.getTotal());
    }




}
