package com.aplan.mapper;


import com.aplan.bean.model.TzDictItemModel;
import com.aplan.bean.param.PageParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

@Repository
public interface TzDictItemMapper extends BaseMapper<TzDictItemModel> {


    List<TzDictItemModel> selectTzDictItemModelPage(PageParam<TzDictItemModel> page, @Param("sp") TzDictItemModel sp);

    int btcDel(@Param("ids") List<String> ids);

}