package com.aplan.mapper;

import com.aplan.bean.param.PageParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.aplan.bean.model.SysParamModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysParamMapper extends BaseMapper<SysParamModel> {

    String selectByParamKey(String paramKey);

    List<SysParamModel> selectSysParamPage(PageParam<SysParamModel> page, @Param("sp") SysParamModel sp);

    int btcDel(@Param("ids") List<String> ids);

}