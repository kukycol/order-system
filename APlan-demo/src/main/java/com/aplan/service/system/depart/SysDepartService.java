package com.aplan.service.system.depart;

import com.baomidou.mybatisplus.extension.service.IService;
import com.aplan.common.Oauth2Response;
import com.aplan.bean.dto.SysDepartDto;
import com.aplan.bean.model.SysDepartModel;

public interface SysDepartService extends IService<SysDepartModel> {

    // 树型表格数据
    Oauth2Response treeTable(SysDepartDto sysDepartDto);

    Oauth2Response deleteById(String id);

    Oauth2Response insert(SysDepartModel sysDepartModel);

    Oauth2Response SysDepartModelUpdate(SysDepartModel sysDepartModel);

    Oauth2Response parentTreeTableList(SysDepartDto sysDepartDto);
}
