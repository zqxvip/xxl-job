package com.xxl.job.admin.core.alarm.impl;

import cn.hutool.core.util.StrUtil;
import com.xxl.job.admin.controller.JobLogController;
import com.xxl.job.admin.core.alarm.JobAlarm;
import com.xxl.job.admin.core.conf.XxlJobAdminConfig;
import com.xxl.job.admin.core.model.XxlJobInfo;
import com.xxl.job.admin.core.model.XxlJobLog;
import com.xxl.job.admin.core.util.LarkUtil;
import com.xxl.job.core.biz.model.LogResult;
import com.xxl.job.core.biz.model.ReturnT;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Component
public class LarkJobAlarm implements JobAlarm {

    @Resource
    LarkUtil larkUtil;
    @Resource
    JobLogController jobLogController;

    /**
     * lark msg
     */
    @Override
    public boolean doAlarm(XxlJobInfo info, XxlJobLog jobLog) {
        String larkBotUrl = XxlJobAdminConfig.getAdminConfig().getLarkBotUrl();
        ReturnT<LogResult> res = jobLogController.logDetailCat(jobLog.getExecutorAddress(), jobLog.getTriggerTime().getTime(), jobLog.getId(), 1);
        if (StringUtils.hasLength(larkBotUrl)) {
            larkUtil.send(larkBotUrl, StrUtil.format("执行器[{}] 任务[{}] \n {}", jobLog.getTitle(), info.getJobDesc(), res.getContent().getLogContent()));
        }
        return false;
    }
}
