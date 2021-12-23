
package com.aplan.mapper;

import com.aplan.bean.param.PageParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.aplan.bean.model.quartz.ScheduleJob;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleJobMapper extends BaseMapper<ScheduleJob> {


    List<ScheduleJob> selectSchedulePage(PageParam<ScheduleJob> page, @Param("sp") ScheduleJob scheduleJob);

    int delBtc(@Param("ids") List<Integer> ids);

}