package com.aplan.mapper;

import com.aplan.bean.param.PageParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.aplan.bean.model.SysLogModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysLogMapper extends BaseMapper<SysLogModel> {


    List<SysLogModel> queryAllByLimit(@Param("sp") SysLogModel sp, PageParam<SysLogModel> page);

    SysLogModel getUserNewestLogin(String userId);
}