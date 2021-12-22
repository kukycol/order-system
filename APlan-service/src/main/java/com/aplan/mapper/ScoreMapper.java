
package com.aplan.mapper;

import com.aplan.bean.dto.EmployeeScoreDto;
import com.aplan.bean.model.ScoreModel;
import com.aplan.bean.model.quartz.ScheduleJobLog;
import com.aplan.bean.param.PageParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface ScoreMapper extends BaseMapper<ScoreModel> {

    int btcDel(List<String> ids);

    List<ScoreModel> selectScorePage(PageParam<ScoreModel> page, @Param("sp") ScoreModel sp);

    List<EmployeeScoreDto> selectByDepartId(@Param("departId")String departId, @Param("userId")String userId);

    Map selectStatus();

    int updateStatus(@Param("status") int status, @Param("createTime")Date createTime);
}