package com.aplan.service.rule;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aplan.common.Oauth2Response;
import com.aplan.bean.param.PageParam;
import com.aplan.bean.dto.SysRuleDto;
import com.aplan.bean.model.SysRuleModel;
import com.aplan.exception.custom.Oauth2Exception;
import com.aplan.mapper.SysRuleMapper;
import com.aplan.mapper.SysRuleRoleMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service("sysRuleService")
@Transactional
public class SysRuleServiceImpl extends ServiceImpl<SysRuleMapper, SysRuleModel> implements SysRuleService {


    @Resource
    private SysRuleMapper sysRuleMapper;
    @Resource
    private SysRuleRoleMapper sysRuleRoleMapper;


    @Override
    public SysRuleModel queryById(String id) {
        SysRuleModel sysRuleModel = this.sysRuleMapper.selectById(id);
        return sysRuleModel;
    }


    @Override
    public Oauth2Response queryAllByLimit(SysRuleModel sysRule, PageParam<SysRuleModel> page) {
        List<SysRuleModel> records = this.sysRuleMapper.selectSysRuleModelPage(page, sysRule);
        return Oauth2Response.pageQuerySuccess(records, page.getTotal());
    }


    @Override
    public Oauth2Response insert(SysRuleModel sysRule) {


        if (sysRule.getRuleType() == 1) {
            if (StringUtils.isBlank(sysRule.getParentId())) {
                return new Oauth2Response(30000, "菜单不可以没有父级哦");
            }

            if (StringUtils.isBlank(sysRule.getRuleIcon())) {
                return new Oauth2Response(30000, "菜单不可以没有图标哦");
            }
        }

        if (sysRule.getRuleType() == 0) {
            if (StringUtils.isBlank(sysRule.getRuleIcon())) {
                return new Oauth2Response(30000, "目录不可以没有图标哦");
            }
        }


        if (StringUtils.isNotBlank(sysRule.getRuleUrl())) {
            SysRuleModel sysRuleModel = sysRuleMapper.selectOne(new LambdaQueryWrapper<SysRuleModel>().eq(SysRuleModel::getRuleUrl, sysRule.getRuleUrl()));
            if (sysRuleModel != null) {
                return Oauth2Response.dataExist(sysRule.getRuleUrl());
            }
        }

        if (StringUtils.isNotBlank(sysRule.getPerms())) {
            SysRuleModel sysRuleModel = sysRuleMapper.selectOne(new LambdaQueryWrapper<SysRuleModel>().eq(SysRuleModel::getPerms, sysRule.getPerms()));
            if (sysRuleModel != null) {
                return Oauth2Response.dataExist(sysRule.getPerms());
            }
        }


        sysRule.setRuleLevel(sysRule.getRuleType() + 1);
        int insertRow = this.sysRuleMapper.insert(sysRule);
        if (insertRow == 1) {
            return Oauth2Response.saveSuccess();
        }
        throw Oauth2Exception.saveException();

    }


    @Override
    public Oauth2Response update(SysRuleModel sysRule) {

        String id = sysRule.getId();
        SysRuleModel sysRuleModel1 = sysRuleMapper.selectById(id);
        if (sysRuleModel1 == null) {
            return Oauth2Response.dataNotExist(id);
        }


        if (StringUtils.isNotBlank(sysRule.getRuleUrl())) {
            if (!sysRuleModel1.getRuleUrl().equals(sysRule.getRuleUrl())) {
                SysRuleModel sysRuleModel = sysRuleMapper.selectOne(new LambdaQueryWrapper<SysRuleModel>().eq(SysRuleModel::getRuleUrl, sysRule.getRuleUrl()));
                if (sysRuleModel != null) {
                    return Oauth2Response.dataExist(sysRule.getRuleUrl());
                }
            }
        }


        if (StringUtils.isNotBlank(sysRule.getPerms())) {
            if (!sysRuleModel1.getPerms().equals(sysRule.getPerms())) {
                SysRuleModel sysRuleModel = sysRuleMapper.selectOne(new LambdaQueryWrapper<SysRuleModel>().eq(SysRuleModel::getPerms, sysRule.getPerms()));
                if (sysRuleModel != null) {
                    return Oauth2Response.dataExist(sysRule.getPerms());
                }
            }
        }


        sysRule.setRuleLevel(sysRule.getRuleType() + 1);

        int updateRow = this.sysRuleMapper.updateById(sysRule);
        if (updateRow == 1) {
            return Oauth2Response.updateSuccess();
        }
        throw Oauth2Exception.updateException();
    }


    // 删除菜单或目录
    @Override
    public Oauth2Response deleteById(String id) {
        // 删除id数据
        int delete = this.sysRuleMapper.deleteById(id);
        // 删除子级数据
        int parentRow = this.sysRuleMapper.delete(new LambdaQueryWrapper<SysRuleModel>().eq(SysRuleModel::getParentId, id));
        if (delete == 1) {
            // 删除与角色关联的数据
            int row = sysRuleRoleMapper.deleteByRuleId(id);
            return Oauth2Response.removeSuccess();

        }
        throw Oauth2Exception.removeException();
    }

    @Override
    public List<SysRuleModel> ruleList() {
        List<SysRuleModel> sysRuleModels = sysRuleMapper.selectList(new LambdaQueryWrapper<>());
        for (SysRuleModel sysRuleModel : sysRuleModels) {
            sysRuleModel.setRoleModelList(sysRuleMapper.selectRoleModelList(sysRuleModel.getId()));
        }
        return sysRuleModels;
    }

    @Override
    public Oauth2Response treeTable(SysRuleModel sysRuleModel) {


        Oauth2Response result = new Oauth2Response();
        List<SysRuleModel> rootlist2 = null;
        String ruleName = sysRuleModel.getRuleName();
        if (StringUtils.isNotBlank(ruleName)) {
            rootlist2 = sysRuleMapper.selectSysRuleModelList(sysRuleModel);
            result.setData(rootlist2);

        } else {
            List<SysRuleModel> sysRuleModels = sysRuleMapper.selectSysRuleModelList(sysRuleModel);

            sysRuleModels = sysRuleModels.stream().sorted(Comparator.comparing(SysRuleModel::getRuleOrder).reversed()).collect(Collectors.toList());

            List<SysRuleModel> rootlist = new ArrayList<>();
            List<SysRuleModel> finalSysRuleModels = sysRuleModels;
            sysRuleModels.stream().filter(rule -> rule.getParentId().equals("0")).forEach(rule -> {

                // 找到根节点菜单的时候，寻找这个根节点菜单下的子节点菜单。
                findChilds(rule, finalSysRuleModels);

                // 添加到根节点的列表中
                rootlist.add(rule);
            });
            result.setData(rootlist);
        }

        result.setCode(20000);
        result.setMessage("查询成功");
        return result;
    }

    @Override
    public Oauth2Response parentTreeTableList(SysRuleDto ruleModel) {
        if (ruleModel.getRuleLevel() == 0) {
            ruleModel.setRuleLevel(1);
        }

        List<SysRuleDto> sysRuleModels =
                sysRuleMapper.selectSysRuleModelList2(ruleModel);

        List<SysRuleDto> rootlist = new ArrayList<>();

        sysRuleModels.stream().filter(rule -> rule.getParentId().equals("0")).forEach(rule -> {
//            if (ruleModel.getRuleLevel() == rule.getRuleLevel()) {
//                rule.setDisabled(false);
//            }
            //找到根节点菜单的时候，寻找这个根节点菜单下的子节点菜单。
            findChilds2(rule, sysRuleModels, ruleModel.getRuleLevel());
            //添加到根节点的列表中
            rootlist.add(rule);
        });

        SysRuleDto sysRuleDto = new SysRuleDto();
        sysRuleDto.setLabel("顶级类目");
        sysRuleDto.setId("0");
        sysRuleDto.setChildren(rootlist);
        List<SysRuleDto> rootlist2 = new ArrayList<>();
        rootlist2.add(sysRuleDto);
        return Oauth2Response.querySuccess(rootlist2);
    }


    private void findChilds(SysRuleModel root, List<SysRuleModel> list) {
        List<SysRuleModel> childlist = new ArrayList<>();

        // 降序
        list = list.stream().sorted(Comparator.comparing(SysRuleModel::getRuleOrder).reversed()).collect(Collectors.toList());

        //遍历所有数据，找到是入参父节点的子节点的数据，然后加到childlist集合中。
        list.stream().filter((menu) -> root.getId().equals(menu.getParentId())).forEach((menu) -> {
            childlist.add(menu);
        });

        //若子节点不存在，那么就不必再遍历子节点中的子节点了 直接返回。
        if (childlist.size() == 0) {
            return;
        }
        //设置父节点的子节点列表
        root.setChildren(childlist);

        List<SysRuleModel> finalList = list;
        childlist.forEach((childs) -> {
            findChilds(childs, finalList);
        });


    }

    private void findChilds3(SysRuleModel root, List<SysRuleModel> list, List<SysRuleModel> ruleModels) {
        List<SysRuleModel> childlist = new ArrayList<>();

        // 降序
        list = list.stream().sorted(Comparator.comparing(SysRuleModel::getRuleOrder).reversed()).collect(Collectors.toList());

        //遍历所有数据，找到是入参父节点的子节点的数据，然后加到childlist集合中。
        list.stream().filter((menu) -> root.getId().equals(menu.getParentId())).forEach((menu) -> {

            if (ruleModels.contains(menu)) {
                menu.setDisabled2(true);
            }

            childlist.add(menu);
        });

        //若子节点不存在，那么就不必再遍历子节点中的子节点了 直接返回。
        if (childlist.size() == 0) {
            return;
        }
        //设置父节点的子节点列表
        root.setChildren(childlist);

        List<SysRuleModel> finalList = list;
        childlist.forEach((childs) -> {
            findChilds(childs, finalList);
        });


    }

    private void findChilds2(SysRuleDto root, List<SysRuleDto> list, int level) {
        List<SysRuleDto> childlist = new ArrayList<>();
        //遍历所有数据，找到是入参父节点的子节点的数据，然后加到childlist集合中。
        list.stream().filter((menu) -> root.getId().equals(menu.getParentId())).forEach((menu) -> {
//            if (level == menu.getRuleLevel()) {
//                menu.setDisabled(false);
//            }
            childlist.add(menu);
        });

        //若子节点不存在，那么就不必再遍历子节点中的子节点了 直接返回。
        if (childlist.size() == 0) {
            return;
        }
        //设置父节点的子节点列表
        root.setChildren(childlist);

        childlist.forEach((childs) -> {
            findChilds2(childs, list, level);
        });


    }
}