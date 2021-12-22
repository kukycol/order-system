
package com.aplan.service.quartz;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aplan.bean.model.quartz.ScheduleJobLog;
import com.aplan.mapper.ScheduleJobLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lgh
 */
@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogMapper, ScheduleJobLog> implements ScheduleJobLogService {

	@Autowired
	private ScheduleJobLogMapper scheduleJobLogMapper;



}
