package com.aplan.service.rule;

import com.baomidou.mybatisplus.extension.service.IService;
import com.aplan.common.Oauth2Response;
import com.aplan.bean.param.PageParam;
import com.aplan.bean.model.SysRuleModel;
import com.aplan.bean.dto.SysRuleDto;

import java.util.List;

public interface SysRuleService extends IService<SysRuleModel> {


    SysRuleModel queryById(String id);

    Oauth2Response queryAllByLimit(SysRuleModel sysRule, PageParam<SysRuleModel> page);

    Oauth2Response insert(SysRuleModel sysRule);

    Oauth2Response update(SysRuleModel sysRule);

    Oauth2Response deleteById(String id);

    List<SysRuleModel> ruleList();

    Oauth2Response treeTable(SysRuleModel sysRuleModel);

    Oauth2Response parentTreeTableList(SysRuleDto sysRuleModel);
}