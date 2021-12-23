package com.aplan.mapper;

import com.aplan.bean.param.PageParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.aplan.bean.model.SysRuleModel;
import com.aplan.bean.dto.RouterDto;
import com.aplan.bean.dto.SysRuleDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SysRuleMapper extends BaseMapper<SysRuleModel> {

    List<SysRuleModel> selectByParentId(String parentId);

    List<String> selectRoleModelList(String ruleId);

    List<String> selectByUserId(String userId);

    List<String> selectAll();

    List<SysRuleModel> selectByRuleLevelThree();

    List<String> selectByRoleId(@Param("roleId") String roleId,@Param("ruleLevel") Integer ruleLevel);

    List<SysRuleModel> selectByRoleIds(String userId);

    List<SysRuleModel> selectSysRuleModelPage(PageParam<SysRuleModel> page, @Param("sp") SysRuleModel sp);

    List<SysRuleModel> selectSysRuleModelList(SysRuleModel sysRuleModel);

    List<SysRuleDto> selectSysRuleModelList2(SysRuleDto sysRuleModel);

    List<RouterDto> selectMenus(String userId);

}