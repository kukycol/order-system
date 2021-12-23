package com.aplan.service.system.dict;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aplan.common.Oauth2Response;
import com.aplan.bean.param.PageParam;
import com.aplan.bean.model.TzDictItemModel;
import com.aplan.bean.model.TzDictModel;
import com.aplan.exception.custom.Oauth2Exception;
import com.aplan.mapper.TzDictItemMapper;
import com.aplan.mapper.TzDictMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("tzDictService")
@Transactional
public class TzDictServiceImpl extends ServiceImpl<TzDictMapper, TzDictModel> implements TzDictService {


    @Resource
    private TzDictMapper tzDictMapper;
    @Resource
    private TzDictItemMapper tzDictItemMapper;


    @Override
    public Oauth2Response queryByDictCode(String dictCode) {
        TzDictModel record = this.tzDictMapper.selectOne(new LambdaQueryWrapper<TzDictModel>().eq(TzDictModel::getDictCode
        ,dictCode));
        List<TzDictItemModel> dictItemModels =
                tzDictItemMapper.selectList(new LambdaQueryWrapper<TzDictItemModel>().eq(TzDictItemModel::getDictId,
                record.getDictId()));
        record.setDictItemModels(dictItemModels);
        return Oauth2Response.querySuccess(record);
    }

    /**
     * 获取字典信息
     *
     * @param dictId
     */
    @Override
    public Oauth2Response queryById(String dictId) {
        TzDictModel record = this.tzDictMapper.selectById(dictId);
        return Oauth2Response.querySuccess(record);
    }


    /**
     * 字典分页查询
     *
     * @param sp
     * @param page
     */
    @Override
    public Oauth2Response queryAllByLimit(TzDictModel sp, PageParam<TzDictModel>
            page) {
        List<TzDictModel> records = this.tzDictMapper.selectTzDictModelPage(page, sp);
        return Oauth2Response.pageQuerySuccess(records,page.getTotal());
    }


    /**
     * 插入字典
     *
     * @param sp
     */
    @Override
    public Oauth2Response insert(TzDictModel sp) {

        // 字典类型唯一性
        String dictCode = sp.getDictCode();
        List<TzDictModel> dictCodes = tzDictMapper.selectTzDictModel(TzDictModel.builder().dictCode(dictCode).build());
        if (dictCodes.size() > 0) {
            return Oauth2Response.dataExist(dictCode);
        }

        // 字典名称唯一性
        String dictName = sp.getDictName();
        List<TzDictModel> dictNames = tzDictMapper.selectTzDictModel(TzDictModel.builder().dictName(dictName).build());
        if (dictNames.size() > 0) {
            return Oauth2Response.dataExist(dictName);
        }

        // 默认值
        sp.setUpdateBy("当前登录的用户名");
        sp.setUpdateTime(new Date());
        sp.setCreateBy("当前登录的用户名");
        sp.setCreateTime(new Date());
        int row = this.tzDictMapper.insert(sp);
        if (row == 1) {
            return Oauth2Response.saveSuccess();
        }
        throw Oauth2Exception.saveException();
    }


    /**
     * 更新字典
     *
     * @param sp
     */
    @Override
    public Oauth2Response update(TzDictModel sp) {

        TzDictModel record = tzDictMapper.selectById(sp.getDictId());
        if (ObjectUtils.allNull(record)) {
            return  Oauth2Response.dataNotExist(sp.getDictId());
        }

        // 字典类型唯一性
        if (!record.getDictCode().equals(sp.getDictCode())) {
            String dictCode = sp.getDictCode();
            List<TzDictModel> dictCodes = tzDictMapper.selectTzDictModel(TzDictModel.builder().dictCode(dictCode).build());
            if (dictCodes.size() > 0) {
                return Oauth2Response.dataExist(sp.getDictCode());
            }
        }


        // 字典名称唯一性
        if (!record.getDictName().equals(sp.getDictName())) {
            String dictName = sp.getDictName();
            List<TzDictModel> dictNames = tzDictMapper.selectTzDictModel(TzDictModel.builder().dictName(dictName).build());
            if (dictNames.size() > 0) {
                return Oauth2Response.dataExist(sp.getDictName());
            }
        }


        sp.setUpdateBy("当前登录的用户名");
        sp.setUpdateTime(new Date());
        int row = this.tzDictMapper.updateById(sp);
        if (row == 1) {
            return Oauth2Response.updateSuccess();
        }
        throw Oauth2Exception.updateException();
    }


    /**
     * 批量删除字典
     *
     * @param ids
     */
    @Override
    public Oauth2Response deleteBtc(List<String> ids) {
        int row = this.tzDictMapper.deleteBatchIds(ids);
        if (row == ids.size()) {
            for (String id : ids) {
                int itemRow = tzDictItemMapper.deleteById(id);
                if (itemRow!=0){
                    throw Oauth2Exception.removeException();
                }
            }
            return Oauth2Response.removeSuccess();
        }
        throw Oauth2Exception.removeException();
    }


    /**
     * 删除字典信息
     *
     * @param dictId
     */
    @Override
    public Oauth2Response deleteById(String dictId) {
        int row = this.tzDictMapper.deleteById(dictId);
        if (row == 1) {
            int itemRow = tzDictItemMapper.deleteById(dictId);
            if (itemRow!=0){
                throw Oauth2Exception.removeException();
            }
            return Oauth2Response.removeSuccess();
        }
        throw Oauth2Exception.removeException();
    }


}