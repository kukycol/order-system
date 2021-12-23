package com.aplan.service.system.position;

import com.baomidou.mybatisplus.extension.service.IService;
import com.aplan.common.Oauth2Response;
import com.aplan.bean.param.PageParam;
import com.aplan.bean.dto.DepartPositionDto;
import com.aplan.bean.model.SysDepartPositionModel;

import java.util.List;

public interface SysDepartPositionService extends IService<SysDepartPositionModel> {


    Oauth2Response queryAllByLimit(DepartPositionDto sp, PageParam<DepartPositionDto> page);

    Oauth2Response insert(SysDepartPositionModel sp);

    Oauth2Response updateDepartPosition(SysDepartPositionModel sp);

    Oauth2Response deleteBtc(List<String> ids);

    Oauth2Response queryAll(String departId);
}
