package com.xxl.job.admin.core.alarm.impl;

import cn.hutool.core.util.StrUtil;
import com.xxl.job.admin.core.alarm.JobAlarm;
import com.xxl.job.admin.core.conf.XxlJobAdminConfig;
import com.xxl.job.admin.core.model.XxlJobInfo;
import com.xxl.job.admin.core.model.XxlJobLog;
import com.xxl.job.admin.core.util.LarkUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Component
public class LarkJobAlarm implements JobAlarm {

    @Resource
    LarkUtil larkUtil;

    /**
     * lark msg
     * @param info
     * @param jobLog
     * @return
     */
    @Override
    public boolean doAlarm(XxlJobInfo info, XxlJobLog jobLog) {
        String larkBotUrl = XxlJobAdminConfig.getAdminConfig().getLarkBotUrl();
        if (StringUtils.hasLength(larkBotUrl)) {
            larkUtil.send(larkBotUrl, StrUtil.format("执行器[{}] 任务[{}] \n {}", jobLog.getTitle(), info.getJobDesc(), jobLog.getHandleMsg()));
        }
        return false;
    }
}
