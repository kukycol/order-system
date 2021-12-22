
package com.aplan.service.quartz;


import com.baomidou.mybatisplus.extension.service.IService;
import com.aplan.common.Oauth2Response;
import com.aplan.bean.param.PageParam;
import com.aplan.bean.model.quartz.ScheduleJob;

import java.util.List;

/**
 * 
 * @author lgh
 *
 */
public interface ScheduleJobService extends IService<ScheduleJob> {

	// 新增
	Oauth2Response saveAndStart(ScheduleJob scheduleJob);

	// 立即执行
	void run(Long[] jobIds);

	// 分页查询
	Oauth2Response queryAllByLimit(ScheduleJob scheduleJob, PageParam<ScheduleJob> page);

	// 批量删除
	Oauth2Response deleteByIds(List<Integer> ids);

	// 更新
	Oauth2Response updateScheduleJob(ScheduleJob scheduleJob);

	// 更新状态
	Oauth2Response updateStatus(ScheduleJob scheduleJob);

}
