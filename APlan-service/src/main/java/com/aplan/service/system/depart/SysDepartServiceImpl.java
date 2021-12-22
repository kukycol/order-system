package com.aplan.service.system.depart;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aplan.common.Oauth2Response;
import com.aplan.bean.dto.SysDepartDto;
import com.aplan.bean.dto.SysRuleDto;
import com.aplan.bean.model.SysDepartModel;
import com.aplan.exception.custom.Oauth2Exception;
import com.aplan.mapper.SysDepartMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SysDepartServiceImpl extends ServiceImpl<SysDepartMapper, SysDepartModel> implements SysDepartService {

    @Autowired
    private SysDepartMapper sysDepartMapper;

    
    /**
     * @Description:
     * @Author: kuky
     * @Date: 2021/12/20 17:01
     * @param: sysDepartDto
     * @Return Oauth2Response
     * @Version: 0.0.1
     */
    @Override
    public Oauth2Response treeTable(SysDepartDto sysDepartDto) {
        
        Oauth2Response result = new Oauth2Response();
        List<SysDepartDto> sysDepartModels = sysDepartMapper.selectTree(sysDepartDto);


        if (StringUtils.isNotBlank(sysDepartDto.getDepartName())){
            result.setData(sysDepartModels);
        }else {
            List<SysDepartDto> rootlist = new ArrayList<>();
            sysDepartModels.stream().filter(rule -> "0".equals(rule.getParentId())).forEach(rule -> {
                //找到根节点菜单的时候，寻找这个根节点菜单下的子节点菜单。
                findChildren(rule, sysDepartModels);
                //添加到根节点的列表中
                rootlist.add(rule);
            });
            result.setData(rootlist);

        }

        result.setCode(20000);
        result.setMessage("查询成功");
        return result;
    }

    @Override
    public Oauth2Response deleteById(String id) {
        int deleteRow = sysDepartMapper.deleteById(id);
        if (deleteRow == 1){
            return Oauth2Response.removeSuccess();
        }
        throw Oauth2Exception.removeException();
    }

    @Override
    public Oauth2Response insert(SysDepartModel sysDepartModel) {
        sysDepartModel.setCreateTime(new Date());
        sysDepartModel.setCreate_by("当前登录的用户名");
        int insertRow = sysDepartMapper.insert(sysDepartModel);
        if (insertRow == 1){
            return Oauth2Response.saveSuccess();
        }
        throw Oauth2Exception.saveException();
    }

    @Override
    public Oauth2Response SysDepartModelUpdate(SysDepartModel sysDepartModel) {
        int updateRow = sysDepartMapper.updateById(sysDepartModel);
        if (updateRow == 1){
            return Oauth2Response.updateSuccess();
        }
        throw Oauth2Exception.updateException();
    }

    @Override
    public Oauth2Response parentTreeTableList(SysDepartDto sysDepartDto) {
        List<SysRuleDto> sysDepartModels = sysDepartMapper.selectParentTree(sysDepartDto);
        List<SysRuleDto> rootlist = new ArrayList<>();

        if (StringUtils.isNotBlank(sysDepartDto.getDepartName())){
            return Oauth2Response.querySuccess(sysDepartModels);
        }else {
            sysDepartModels.stream().filter(rule -> "0".equals(rule.getParentId())).forEach(rule -> {
                //找到根节点菜单的时候，寻找这个根节点菜单下的子节点菜单。
                SysRuleDtofindChildren(rule, sysDepartModels);
                //添加到根节点的列表中
                rootlist.add(rule);
            });
        }


        SysRuleDto sysRuleDto = new SysRuleDto();
        sysRuleDto.setLabel("总部");
        sysRuleDto.setId("0");
        sysRuleDto.setChildren(rootlist);
        List<SysRuleDto> rootlist2 = new ArrayList<>();
        rootlist2.add(sysRuleDto);
        return Oauth2Response.querySuccess(rootlist2);
    }


    // 无限下级递归
    private void findChildren(SysDepartDto root, List<SysDepartDto> list) {
        List<SysDepartDto> childlist = new ArrayList<>();


        //遍历所有数据，找到是入参父节点的子节点的数据，然后加到childlist集合中。
        list.stream().filter((menu) -> root.getDepartId().equals(menu.getParentId())).forEach((menu) -> {
            childlist.add(menu);
        });

        //若子节点不存在，那么就不必再遍历子节点中的子节点了 直接返回。
        if (childlist.size() == 0) {
            return;
        }
        //设置父节点的子节点列表
        root.setChildren(childlist);

        List<SysDepartDto> finalList = list;
        childlist.forEach((childs) -> {
            findChildren(childs, finalList);
        });

    }


    // 无限下级递归
    private void SysRuleDtofindChildren(SysRuleDto root, List<SysRuleDto> list) {
        List<SysRuleDto> childlist = new ArrayList<>();


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

        List<SysRuleDto> finalList = list;
        childlist.forEach((childs) -> {
            SysRuleDtofindChildren(childs, finalList);
        });

    }

    // 无限下级递归
    private void SysRuleDtofindChildren2(SysRuleDto root, List<SysRuleDto> list) {
        List<SysRuleDto> childlist = new ArrayList<>();


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

        List<SysRuleDto> finalList = list;
        childlist.forEach((childs) -> {
            SysRuleDtofindChildren(childs, finalList);
        });

    }


}
