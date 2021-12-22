package com.aplan.service.system.dict;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aplan.common.Oauth2Response;
import com.aplan.bean.param.PageParam;
import com.aplan.bean.model.TzDictItemModel;
import com.aplan.exception.custom.Oauth2Exception;
import com.aplan.mapper.TzDictItemMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("tzDictItemService")
@Transactional
public class TzDictItemServiceImpl extends ServiceImpl<TzDictItemMapper, TzDictItemModel> implements TzDictItemService {


    @Resource
    private TzDictItemMapper tzDictItemMapper;


    /**
     * 获取字典项信息
     *
     * @param dictItemId
     */
    @Override
    public Oauth2Response queryById(String dictItemId) {
        TzDictItemModel record = this.tzDictItemMapper.selectById(dictItemId);
        return Oauth2Response.querySuccess(record);

    }


    /**
     * 字典项分页查询
     *
     * @param sp
     * @param page
     */
    @Override
    public Oauth2Response queryAllByLimit(TzDictItemModel sp, PageParam<TzDictItemModel>
            page) {
        List<TzDictItemModel> records = this.tzDictItemMapper.selectTzDictItemModelPage(page, sp);
        return Oauth2Response.pageQuerySuccess(records, page.getTotal());
    }

    /**
     * 插入字典项
     *
     * @param sp
     */
    @Override
    public Oauth2Response insert(TzDictItemModel sp) {

        //
        TzDictItemModel itemText = tzDictItemMapper.selectOne(new LambdaQueryWrapper<TzDictItemModel>().eq(TzDictItemModel::getDictId, sp.getDictId()).eq(TzDictItemModel::getDictItemText, sp.getDictItemText()));
        if (ObjectUtils.allNotNull(itemText)) {
            return Oauth2Response.dataExist(sp.getDictItemText() );
        }

        //
        TzDictItemModel itemValue = tzDictItemMapper.selectOne(new LambdaQueryWrapper<TzDictItemModel>().eq(TzDictItemModel::getDictId, sp.getDictId()).eq(TzDictItemModel::getDictItemValue, sp.getDictItemValue()));
        if (ObjectUtils.allNotNull(itemValue)) {
            return Oauth2Response.dataExist( sp.getDictItemText() );
        }


        int row = this.tzDictItemMapper.insert(sp);
        if (row == 1) {
            return Oauth2Response.saveSuccess();
        }
        throw new Oauth2Exception("删除数据异常");
    }


    /**
     * 更新字典项
     *
     * @param sp
     */
    @Override
    public Oauth2Response update(TzDictItemModel sp) {

        String dictItemId = sp.getDictItemId();
        TzDictItemModel tzDictItemModel = tzDictItemMapper.selectById(dictItemId);
        if (ObjectUtils.allNull(tzDictItemModel)) {
            return Oauth2Response.dataNotExist(dictItemId);
        }


        //
        if (!tzDictItemModel.getDictItemText().equals(sp.getDictItemText())) {
            TzDictItemModel itemText = tzDictItemMapper.selectOne(new LambdaQueryWrapper<TzDictItemModel>().eq(TzDictItemModel::getDictId, sp.getDictId()).eq(TzDictItemModel::getDictItemText, sp.getDictItemText()));
            if (ObjectUtils.allNotNull(itemText)) {
                return Oauth2Response.dataExist(sp.getDictItemText() );
            }
        }


        //
        if (!tzDictItemModel.getDictItemValue().equals(sp.getDictItemValue())) {
            TzDictItemModel itemValue = tzDictItemMapper.selectOne(new LambdaQueryWrapper<TzDictItemModel>().eq(TzDictItemModel::getDictId, sp.getDictId()).eq(TzDictItemModel::getDictItemValue, sp.getDictItemValue()));
            if (ObjectUtils.allNotNull(itemValue)) {
                return Oauth2Response.dataExist( sp.getDictItemText());
            }
        }


        int row = this.tzDictItemMapper.updateById(sp);
        if (row == 1) {
            return Oauth2Response.saveSuccess();
        }
        throw new Oauth2Exception("更新数据异常");
    }


    /**
     * 批量删除字典项
     *
     * @param ids
     */
    @Override
    public Oauth2Response deleteBtc(List<String> ids) {
        int row = this.tzDictItemMapper.btcDel(ids);
        if (row == ids.size()) {
            return Oauth2Response.removeSuccess();
        }
        throw new Oauth2Exception("删除数据异常");
    }


    /**
     * 删除字典项信息
     *
     * @param dictItemId
     */
    @Override
    public Oauth2Response deleteById(String dictItemId) {
        int row = this.tzDictItemMapper.deleteById(dictItemId);
        if (row == 1) {
            return Oauth2Response.saveSuccess();
        }
        throw new Oauth2Exception("删除数据异常");
    }


}