package com.aplan.service.system.dict;

import com.baomidou.mybatisplus.extension.service.IService;
import com.aplan.common.Oauth2Response;
import com.aplan.bean.param.PageParam;
import com.aplan.bean.model.TzDictModel;

import java.util.List;

public interface TzDictService extends IService<TzDictModel>{

    //获取字典信息
    Oauth2Response queryByDictCode(String dictCode);

    //获取字典信息
    Oauth2Response queryById(String dictId);

    //字典分页查询
    Oauth2Response queryAllByLimit(TzDictModel tzDict, PageParam<TzDictModel> page);

    //插入字典
    Oauth2Response insert(TzDictModel tzDict);

    //更新字典
    Oauth2Response update(TzDictModel tzDict);

    //批量删除字典
    Oauth2Response deleteBtc(List<String> ids);
    
    //删除字典信息
    Oauth2Response deleteById(String dictId);


}