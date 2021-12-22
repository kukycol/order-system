
package com.aplan.bean.model.quartz;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("tz_schedule_job")
public class ScheduleJob  implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 9150018026309004044L;

	/**
     * 任务id
     */
    @TableId
    private Long jobId;

    private String beanName;

    private String jobName;

    /**
     * 方法名
     */

    private String methodName;

    /**
     * 参数
     */
    private String params;

    /**
     * cron表达式
     */

    private String cronExpression;

    /**
     * 任务状态  0：正常  1：暂停
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */

    private Date createTime;
}