package org.dog.server.emailsend.scheduled;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.dog.server.emailsend.util.SendMailUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author Odin
 * @Date 2022/11/20 19:17
 * @Description:
 */

@Component
public class MailSend {

    @Resource
    private SendMailUtil sendMailUtil;

    @XxlJob(value = "morningMail")
    public ReturnT sendMorningMail() {
        sendMailUtil.sendMorning();
        return ReturnT.SUCCESS;
    }

    @XxlJob(value = "nightMail")
    public ReturnT sendNightMail() {
        sendMailUtil.sendNight();
        return ReturnT.SUCCESS;
    }

}
