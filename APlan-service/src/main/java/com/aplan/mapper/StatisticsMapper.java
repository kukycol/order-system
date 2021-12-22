
package com.aplan.mapper;

import com.aplan.bean.dto.EmployeeScoreDto;
import com.aplan.bean.model.ScoreModel;
import com.aplan.bean.model.StatisticsModel;
import com.aplan.bean.param.PageParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StatisticsMapper extends BaseMapper<StatisticsModel> {


    List<StatisticsModel> selectByCreateTimeAndUserId(@Param("userId") String userId,@Param("createTime") Date createTime);

}