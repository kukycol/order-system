
package com.aplan.service.quartz;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aplan.common.Oauth2Response;
import com.aplan.bean.param.PageParam;
import com.aplan.config.quartz.ScheduleManager;
import com.aplan.bean.model.quartz.ScheduleJob;
import com.aplan.enums.ScheduleStatus;
import com.aplan.exception.custom.Oauth2Exception;
import com.aplan.mapper.ScheduleJobMapper;
import org.quartz.CronTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobMapper, ScheduleJob> implements ScheduleJobService {
	
	@Autowired
	private ScheduleJobMapper scheduleJobMapper;
	@Autowired
	private ScheduleManager scheduleManager;


	// 初始化
	@PostConstruct
	public void init(){
		list().forEach(scheduleJob -> {
			CronTrigger trigger = scheduleManager.getCronTrigger(scheduleJob);
			// 如果定时任务不存在，则创建定时任务
			if (trigger == null) {
				scheduleManager.createScheduleJob(scheduleJob);
			} else if (ScheduleStatus.NORMAL.getType().equals(scheduleJob.getStatus())) {
				scheduleManager.resumeJob(scheduleJob);
			} else if (ScheduleStatus.PAUSE.getType().equals(scheduleJob.getStatus())) {
				scheduleManager.pauseJob(scheduleJob);
			}
		});
	}

	// 新增
	@Override
	public Oauth2Response saveAndStart(ScheduleJob scheduleJob) {
		scheduleJob.setCreateTime(new Date());
		scheduleJob.setStatus(ScheduleStatus.NORMAL.getType());
		scheduleJobMapper.insert(scheduleJob);

		// 新增定时任务
        scheduleManager.createScheduleJob(scheduleJob);

		return Oauth2Response.saveSuccess();
	}

	// 立即运行
	@Override
    public void run(Long[] jobIds) {
    	for(Long jobId : jobIds){
    		scheduleManager.run(scheduleJobMapper.selectById(jobId));
    	}
    }

	// 分页查询
	@Override
	public Oauth2Response queryAllByLimit(ScheduleJob scheduleJob, PageParam<ScheduleJob> page) {
		List<ScheduleJob> records = this.scheduleJobMapper.selectSchedulePage(page, scheduleJob);
		return Oauth2Response.pageQuerySuccess(records,page.getTotal());
	}

	// 批量删除
	@Override
	public Oauth2Response deleteByIds(List<Integer> ids) {
		for (Integer id : ids) {
			ScheduleJob scheduleJob1 = scheduleJobMapper.selectById(id);
			System.out.println(scheduleJob1);
			scheduleManager.deleteScheduleJob(scheduleJob1);

		}
		int row = scheduleJobMapper.delBtc(ids);


		if (row == ids.size()){
			return Oauth2Response.removeSuccess();
		}
		throw Oauth2Exception.removeException();
	}

	// 更新
	@Override
	public Oauth2Response updateScheduleJob(ScheduleJob scheduleJob) {
		int row = scheduleJobMapper.updateById(scheduleJob);
		if (row == 1){
			ScheduleJob scheduleJob1 = scheduleJobMapper.selectById(scheduleJob.getJobId());
			scheduleManager.updateScheduleJob(scheduleJob1);
			return Oauth2Response.updateSuccess();
		}
		throw Oauth2Exception.updateException();
	}

	// 更新状态
	@Override
	public Oauth2Response updateStatus(ScheduleJob scheduleJob) {

		int row = scheduleJobMapper.updateById(scheduleJob);

		ScheduleJob scheduleJob1 = scheduleJobMapper.selectById(scheduleJob.getJobId());
		if (scheduleJob.getStatus() == 0){
			scheduleManager.resumeJob(scheduleJob1);
		}else {
			scheduleManager.pauseJob(scheduleJob1);
		}

		if (row == 1){
			return Oauth2Response.updateSuccess();
		}
		throw Oauth2Exception.updateException();
	}


}
