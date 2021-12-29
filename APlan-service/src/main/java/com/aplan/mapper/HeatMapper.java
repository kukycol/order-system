
package com.aplan.mapper;

import com.aplan.bean.model.HeatModel;
import com.aplan.bean.model.quartz.ScheduleJobLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeatMapper extends BaseMapper<HeatModel> {

    HeatModel selectByTypeAndPlatFormTypeAndTitle(HeatModel heatModel);

}