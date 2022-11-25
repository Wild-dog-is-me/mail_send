package org.dog.server.emailsend.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import org.dog.server.emailsend.mapper.SendInfoMapper;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author Odin
 * @Date 2022/11/20 15:52
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        LinkedHashMap<String, String> weatherInfoMap = new LinkedHashMap<>();
        String weatherResponse = HttpRequest.get("https://restapi.amap.com/v3/weather/weatherInfo?key=a3f2b25a01cda68246fb31d9bee2b3b7&&extensions=all&city=320305").body();
        JSONObject weatherJson = JSONObject.parseObject(weatherResponse);
        JSONArray weatherJsonArray = weatherJson.getJSONArray("forecasts");
        JSONArray weatherArray = weatherJsonArray.getJSONObject(0).getJSONArray("casts");
        weatherInfoMap.put((String) weatherArray.getJSONObject(1).get("date"), AnalysisWeatherJson1(weatherArray.getJSONObject(1)));
        System.out.println(weatherInfoMap);
    }

    public static String AnalysisWeatherJson1(JSONObject weatherInfoJson) {
        String dayWeather = "白天天气:" + (String) weatherInfoJson.get("dayweather") + "｜";
        String nightWeather = "夜间天气:" + (String) weatherInfoJson.get("nightweather") + "\n";
        String dayTemp = "白天气温:" + (String) weatherInfoJson.get("daytemp") + "°C｜";
        String nightTemp = "夜间气温:" + (String) weatherInfoJson.get("nighttemp") + "°C\n";
        return dayWeather + nightWeather + dayTemp + nightTemp;
    }
}
