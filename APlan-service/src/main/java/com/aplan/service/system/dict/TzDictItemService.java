package com.aplan.service.system.dict;

import com.baomidou.mybatisplus.extension.service.IService;
import com.aplan.common.Oauth2Response;
import com.aplan.bean.param.PageParam;
import com.aplan.bean.model.TzDictItemModel;

import java.util.List;

public interface TzDictItemService extends IService<TzDictItemModel>{

    //获取字典项信息
    Oauth2Response queryById(String dictItemId);

    //字典项分页查询
    Oauth2Response queryAllByLimit(TzDictItemModel tzDictItem, PageParam<TzDictItemModel> page);

    //插入字典项
    Oauth2Response insert(TzDictItemModel tzDictItem);

    //更新字典项
    Oauth2Response update(TzDictItemModel tzDictItem);

    //批量删除字典项
    Oauth2Response deleteBtc(List<String> ids);
    
    //删除字典项信息
    Oauth2Response deleteById(String dictItemId);
    

}