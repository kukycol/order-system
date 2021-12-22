package com.aplan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.aplan.bean.model.SysPathWhiteModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysPathWhiteMapper extends BaseMapper<SysPathWhiteModel> {


    List<String> selectByPath();

}