package com.aplan.mapper;


import com.aplan.bean.model.TzDictModel;
import com.aplan.bean.param.PageParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

@Repository
public interface TzDictMapper extends BaseMapper<TzDictModel> {


    List<TzDictModel> selectTzDictModelPage(PageParam<TzDictModel> page, @Param("sp") TzDictModel sp);

    List<TzDictModel> selectTzDictModel( @Param("sp") TzDictModel sp);


}