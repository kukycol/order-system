
package com.aplan.config.quartz;


import com.aplan.bean.model.quartz.ScheduleJob;
import com.aplan.event.ScheduleJobEvent;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

/**
 * 该类将会被org.springframework.scheduling.quartz.SpringBeanJobFactory 实例化
 * 并使@Autowired 生效
 */
@Slf4j
@DisallowConcurrentExecution
public class QuartzJob implements Job {

	@Autowired
	private ApplicationEventPublisher publisher;

	/**
	 * 任务调度参数key
	 */
	public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";

	@Override

	public void execute(JobExecutionContext jobExecutionContext) {
		ScheduleJob sysJob = (ScheduleJob) jobExecutionContext.getMergedJobDataMap().get(JOB_PARAM_KEY);
		publisher.publishEvent(new ScheduleJobEvent(sysJob));
	}

}
