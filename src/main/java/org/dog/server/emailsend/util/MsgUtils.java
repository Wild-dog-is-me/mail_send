package org.dog.server.emailsend.util;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author Odin
 * @Date 2022/11/20 17:35
 * @Description:邮件发送信息处理
 */

@Component
public class MsgUtils {

    @Resource
    private HttpUtils httpUtils;

    /**
     * 根据返回格式化的天气信息
     * @param cityId 根据不同用户而定
     * 返回格式如下:
     *               下面为播报近三日天气情况
     *                  2022-11-20
     *                  白天天气:多云		夜间天气:多云
     *                  白天气温:17°C	夜间气温:12°C
     *                  2022-11-21
     *                  白天天气:小雨		夜间天气:中雨
     *                  白天气温:16°C	夜间气温:12°C
     *                  2022-11-22
     *                  白天天气:小雨		夜间天气:小雨
     *                  白天气温:15°C	夜间气温:7°C
     */
    public String weatherMsg(String cityId) {
        LinkedHashMap<String, String> weatherInfoMap = httpUtils.getWeatherInfo(cityId);
        StringBuilder msg = new StringBuilder("近三日天气情况:\n");
        String key = null;
        String value = null;
        for (String s : weatherInfoMap.keySet()) {
            key = s;
            value = weatherInfoMap.get(key);
            msg.append(key+"\n").append(value);
        }
        return msg.toString();
    }

    public String getTomorrowWeather(String cityId) {
        LinkedHashMap<String, String> weatherInfoMap = httpUtils.getTomorrowWeatherInfo(cityId);
        StringBuilder msg = new StringBuilder("明日天气情况:\n");
        String key = null;
        String value = null;
        for (String s : weatherInfoMap.keySet()) {
            key = s;
            value = weatherInfoMap.get(key);
            msg.append(key).append("\n").append(value);
        }
        return String.valueOf(msg);
    }

    public String getOneWord() {
        LinkedHashMap<String, String> oneDoc = httpUtils.getOneDoc();
        return oneDoc.get("word");
    }

    public String getOneImgUrl() {
        LinkedHashMap<String, String> oneDoc = httpUtils.getOneDoc();
        return oneDoc.get("imgUrl");
    }

    public String getNight() {
        LinkedHashMap<String, String> nightDoc = httpUtils.getNightDoc();
        return nightDoc.get("content");
    }

}
