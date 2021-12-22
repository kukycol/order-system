package com.aplan.mapper;

import com.aplan.bean.param.PageParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.aplan.bean.dto.UserOnlineDto;
import com.aplan.bean.model.SysUserOnlineModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserOnlineMapper extends BaseMapper<SysUserOnlineModel> {


    List<UserOnlineDto> selectSysUserOnlinePage(PageParam<UserOnlineDto> page, @Param("sp") UserOnlineDto sp);

}