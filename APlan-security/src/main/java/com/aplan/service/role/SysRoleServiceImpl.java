package com.aplan.service.role;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aplan.common.Oauth2Response;
import com.aplan.bean.param.PageParam;
import com.aplan.bean.model.SysRoleModel;
import com.aplan.bean.model.SysRuleModel;
import com.aplan.bean.model.SysRuleRoleModel;
import com.aplan.bean.model.SysUserRoleModel;
import com.aplan.exception.custom.Oauth2Exception;
import com.aplan.mapper.SysRoleMapper;
import com.aplan.mapper.SysRuleMapper;
import com.aplan.mapper.SysRuleRoleMapper;
import com.aplan.mapper.SysUserRoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("sysRoleService")
@Transactional
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleModel> implements SysRoleService {


    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysRuleMapper sysRuleMapper;
    @Resource
    private SysRuleRoleMapper sysRuleRoleMapper;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;
    


    @Override
    public Oauth2Response queryAllByLimit(SysRoleModel sysRole, PageParam<SysRoleModel> page) {
        List<SysRoleModel> records = this.sysRoleMapper.selectSysRoleModelPage(page, sysRole);
        for (SysRoleModel record : records) {
            List<String> strings = sysRuleMapper.selectByRoleId(record.getId(),3);
            List<String> twoStr = sysRuleMapper.selectByRoleId(record.getId(),2);
            for (String two : twoStr) {
                List<SysRuleModel> sysRuleModels = sysRuleMapper.selectByParentId(two);
                if (sysRuleModels.size()==0){
                    strings.add(two);
                }
            }

            record.setRuleList(strings);
        }


        return Oauth2Response.pageQuerySuccess(records,page.getTotal());
    }

    @Transactional
    @Override
    public Oauth2Response insert(SysRoleModel sysRole) {

        // 角色唯一性
        String role = sysRole.getRole();
        SysRoleModel sysRoleModel = sysRoleMapper.selectOne(new LambdaQueryWrapper<SysRoleModel>().eq(SysRoleModel::getRole, role));
        if (sysRoleModel!=null){
            return  Oauth2Response.dataExist(role);
        }


        List<String> rules = sysRole.getRuleList();
        if (rules.size()==0){
            return new Oauth2Response(10000,"请选择权限");
        }

        // 保存角色信息
        int insert = this.sysRoleMapper.insert(sysRole);
        if (insert == 1) {

            // 添加角色权限
            for (String ruleId : rules) {
                SysRuleRoleModel sysRuleRoleModel = new SysRuleRoleModel();
                sysRuleRoleModel.setRuleId(ruleId);
                sysRuleRoleModel.setRoleId(sysRole.getId());
                this.sysRuleRoleMapper.insert(sysRuleRoleModel);
            }
            return Oauth2Response.saveSuccess();
        }
        throw new Oauth2Exception("添加数据异常");
    }


    @Override
    public Oauth2Response update(SysRoleModel sysRole) {

        String roleId = sysRole.getId();
        SysRoleModel sysRoleModel1 = sysRoleMapper.selectById(roleId);
        if (sysRoleModel1==null){
            return  Oauth2Response.dataNotExist(roleId);
        }

        // 角色唯一性
        String role = sysRole.getRole();
        if (!role.equals(sysRoleModel1.getRole())){
            SysRoleModel sysRoleModel = sysRoleMapper.selectOne(new LambdaQueryWrapper<SysRoleModel>().eq(SysRoleModel::getRole, role));
            if (sysRoleModel!=null){
                return  Oauth2Response.dataExist(role);
            }
        }

        List<String> rules = sysRole.getRuleList();
        if (rules.size()==0){
            return new Oauth2Response(30000,"请选择权限");
        }

        int update = this.sysRoleMapper.updateById(sysRole);
        if (update == 1) {

            // 先删除角色的权限
            sysRuleRoleMapper.deleteByRoleId(sysRole.getId());
            // 再添加角色权限
            for (String ruleId : rules) {
                SysRuleRoleModel sysRuleRoleModel = new SysRuleRoleModel();
                sysRuleRoleModel.setRuleId(ruleId);
                sysRuleRoleModel.setRoleId(sysRole.getId());
                this.sysRuleRoleMapper.insert(sysRuleRoleModel);
            }

            return Oauth2Response.updateSuccess();
        }
        throw Oauth2Exception.updateException();
    }


    @Override
    public Oauth2Response deleteById(String id) {

        List<SysUserRoleModel> sysUserRoleModels = sysUserRoleMapper.selectList(new LambdaQueryWrapper<SysUserRoleModel>().eq(SysUserRoleModel::getRoleId, id));
        if (sysUserRoleModels.size()>0){
            return new Oauth2Response(30000,"此角色已经关联着用户，请解除关联再进行删除");
        }

        int delete = this.sysRoleMapper.deleteById(id);
        if (delete == 1) {
            int roleRuleRow = sysRuleRoleMapper.deleteByRoleId(id);
            if (roleRuleRow != 0) {
                throw Oauth2Exception.removeException();
            }
            return Oauth2Response.removeSuccess();
        }
        throw Oauth2Exception.removeException();
    }


    @Override
    public Oauth2Response deleteByIds(List<String> ids) {
        for (String id : ids) {
            List<SysUserRoleModel> sysUserRoleModels = sysUserRoleMapper.selectList(new LambdaQueryWrapper<SysUserRoleModel>().eq(SysUserRoleModel::getRoleId, id));
            if (sysUserRoleModels.size()>0){
                SysRoleModel sysRoleModel = sysRoleMapper.selectById(id);
                return new Oauth2Response(30000,"角色为"+sysRoleModel.getRole()+"已经关联着用户，请解除关联再进行删除");
            }
        }


        int btcRow = sysRoleMapper.btcDel(ids);
        if (ids.size() == btcRow) {
            for (String roleId : ids) {
                int roleRuleRow = sysRuleRoleMapper.delete(new LambdaQueryWrapper<SysRuleRoleModel>().eq(SysRuleRoleModel::getRoleId,roleId));
                if (roleRuleRow != 0) {
                    throw Oauth2Exception.removeException();
                }
            }
            return Oauth2Response.removeSuccess();
        }
        throw Oauth2Exception.removeException();
    }

    @Override
    public Oauth2Response roleList() {
        List<SysRoleModel> labelValues = sysRoleMapper.selectSysRoleList();
        return Oauth2Response.querySuccess(labelValues);
    }

}