package com.aplan.service.system.param;

import com.aplan.bean.dto.EmployeeScoreDto;
import com.aplan.bean.dto.StatisticsDto;
import com.aplan.bean.model.ScoreModel;
import com.aplan.bean.model.SysParamModel;
import com.aplan.bean.param.PageParam;
import com.aplan.common.Oauth2Response;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ScoreService extends IService<ScoreModel> {


    Oauth2Response queryAllByLimit(ScoreModel sp, PageParam<ScoreModel> page);

    Oauth2Response insert(ScoreModel sp);

    Oauth2Response updateScore(ScoreModel sp);

    Oauth2Response deleteBtc(List<String> ids);

    Oauth2Response getDepartEmployees(String departId, String userId);

    Oauth2Response submitDepartEmployees(StatisticsDto scores);

    Oauth2Response updateStatus();

    Oauth2Response getActivityStatus();


}
