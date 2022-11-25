package org.dog.server.emailsend.util;

import org.dog.server.emailsend.entity.SendInfo;
import org.dog.server.emailsend.mapper.SendInfoMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Odin
 * @Date 2022/11/20 18:54
 * @Description:
 */

@Component
public class SendMailUtil {

    public static final String SENDER = "1427774041@qq.com";

    public static final String MORNINGTITLE = "今日早报，请查收";

    public static final String NIGHTTITLE = "夜深了，早些休息吧";

    @Resource
    private  JavaMailSender javaMailSender;

    @Resource
    private SendInfoMapper sendInfoMapper;

    @Resource
    private  MsgUtils msgUtils;

    /**
     * 早安
     * @param sendInfo
     */
    public void sendMorningMail(SendInfo sendInfo) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(SENDER);

        mailMessage.setTo(sendInfo.getSenderMail());

        mailMessage.setSubject(MORNINGTITLE);

        StringBuilder text = new StringBuilder();
        text.append("<html><head></head>");
        text.append("<body><h2>").append(sendInfo.getSender()).append("早上好鸭!</h2>");
        text.append("<body><h3>").append(msgUtils.getOneWord()).append("</h3>");
        text.append(msgUtils.weatherMsg(sendInfo.getCityId()));
        text.append("<img src='").append(msgUtils.getOneImgUrl()).append("'/></body>");
        text.append("</html>");

        mailMessage.setText(String.valueOf(text));
        javaMailSender.send(mailMessage);
    }

    /**
     * 晚安
     */
    public void sendNightMail(SendInfo sendInfo) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(SENDER);

        mailMessage.setTo(sendInfo.getSenderMail());

        mailMessage.setSubject(NIGHTTITLE);

        StringBuilder text = new StringBuilder();
        text.append("<html><head></head>");
        text.append("<body><h2>").append(sendInfo.getSender()).append("晚上好～</h2>");
        text.append("<body><h3>").append(msgUtils.getNight()).append("</h3>");
        text.append(msgUtils.getTomorrowWeather(sendInfo.getCityId()));
        text.append("<body><h2>").append(sendInfo.getSender()+"早些睡哦，晚安!</h2>");
        text.append("<img src='").append(msgUtils.getOneImgUrl()).append("'/></body>");
        text.append("</html>");

        mailMessage.setText(String.valueOf(text));
        javaMailSender.send(mailMessage);
    }


    public void sendMorning() {
        List<SendInfo> sendInfos = sendInfoMapper.selectList(null);
        for (SendInfo sendInfo : sendInfos) {
            sendMorningMail(sendInfo);
        }
    }

    public void sendNight() {
        List<SendInfo> sendInfos = sendInfoMapper.selectList(null);
        for (SendInfo sendInfo : sendInfos) {
            sendNightMail(sendInfo);
        }
    }

}
