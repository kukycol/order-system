package com.aplan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.aplan.bean.dto.SysDepartDto;
import com.aplan.bean.dto.SysRuleDto;
import com.aplan.bean.model.SysDepartModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysDepartMapper extends BaseMapper<SysDepartModel> {


    List<SysDepartDto> selectTree(SysDepartDto sysDepartModel);

    List<SysRuleDto> selectParentTree(SysDepartDto sysDepartDto);

}