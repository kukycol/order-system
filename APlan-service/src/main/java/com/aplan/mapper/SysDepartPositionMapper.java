package com.aplan.mapper;

import com.aplan.bean.param.PageParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.aplan.bean.dto.DepartPositionDto;
import com.aplan.bean.model.SysDepartPositionModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysDepartPositionMapper extends BaseMapper<SysDepartPositionModel> {

    List<DepartPositionDto> selectDepartPositionPage(PageParam<DepartPositionDto> page, @Param("sp") DepartPositionDto sp);

    int btcDel(@Param("ids") List<String> ids);

}