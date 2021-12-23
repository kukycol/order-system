package com.aplan.mapper;

import com.aplan.bean.param.PageParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.aplan.bean.dto.SysUserDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserDtoMapper extends BaseMapper<SysUserDto> {

    List<SysUserDto> selectSysUserModelPage(PageParam<SysUserDto> page, @Param("sp") SysUserDto sp);

    int btcDel(@Param("ids") List<String> ids);

    SysUserDto selectByUserId(String userId);
}