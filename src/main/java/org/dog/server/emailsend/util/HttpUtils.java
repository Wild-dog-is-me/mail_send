package org.dog.server.emailsend.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @Author Odin
 * @Date 2022/11/20 13:01
 * @Description:通过http调用各类接口，实现功能。
 */
@Component
public class HttpUtils {

    /**
     * 高德地图获取天气预报请求api
     * 文档：https://lbs.amap.com/api/webservice/guide/api/weatherinfo/#t1
     *
     * @Pararm:key apiKey（a3f2b25a01cda68246fb31d9bee2b3b7）
     * @Pararm:extensions 气象类型 (可选值：base/all | base:返回实况天气 | all:返回预报天气)
     * @Pararm:city 城市编码 (https://lbs.amap.com/api/webservice/download)
     */
    private final static String API_WEATHER =
            "https://restapi.amap.com/v3/weather/weatherInfo?key=a3f2b25a01cda68246fb31d9bee2b3b7&&extensions=all&city=";

    /**
     * 天行数据One api
     * 文档：https://www.tianapi.com/apiview/129
     *
     * @Param:key apiKey (5843a787a883e339cf269beb2c7d6c9a)
     * @Param:rand 0/1 	是否随机
     */
    private final static String API_ONE =
            "https://apis.tianapi.com/one/index?key=5843a787a883e339cf269beb2c7d6c9a&rand=1";

    /**
     * 天行数据 晚安心语api
     * 文档
     *
     * @Param:key apiKey (5843a787a883e339cf269beb2c7d6c9a)
     */
    private final static String API_NIGHT =
            "https://apis.tianapi.com/wanan/index?key=5843a787a883e339cf269beb2c7d6c9a";


    public LinkedHashMap<String, String> getWeatherInfo(String cityId) {
        LinkedHashMap<String, String> weatherInfoMap = new LinkedHashMap<>();
        String weatherResponse = HttpRequest.get(API_WEATHER + cityId).body();
        JSONObject weatherJson = JSONObject.parseObject(weatherResponse);
        JSONArray weatherJsonArray = weatherJson.getJSONArray("forecasts");
        JSONArray weatherArray = weatherJsonArray.getJSONObject(0).getJSONArray("casts");

        for (int day = 0; day < 3; day++) {
            weatherInfoMap.put((String) weatherArray.getJSONObject(day).get("date"), AnalysisWeatherJson(weatherArray.getJSONObject(day)));
        }
        return weatherInfoMap;
    }

    public LinkedHashMap<String, String> getTomorrowWeatherInfo(String cityId) {
        LinkedHashMap<String, String> weatherInfoMap = new LinkedHashMap<>();
        String weatherResponse = HttpRequest.get(API_WEATHER + cityId).body();
        JSONObject weatherJson = JSONObject.parseObject(weatherResponse);
        JSONArray weatherJsonArray = weatherJson.getJSONArray("forecasts");
        JSONArray weatherArray = weatherJsonArray.getJSONObject(0).getJSONArray("casts");
        weatherInfoMap.put((String) weatherArray.getJSONObject(1).get("date"), AnalysisWeatherJson(weatherArray.getJSONObject(1)));
        return weatherInfoMap;
    }

    public LinkedHashMap<String, String> getOneDoc() {
        LinkedHashMap<String, String> oneDocMap = new LinkedHashMap<>();
        String oneResponse = HttpRequest.get(API_ONE).body();
        JSONObject oneJson = JSONObject.parseObject(oneResponse);
        JSONObject result = oneJson.getJSONObject("result");
        oneDocMap.put("word", String.valueOf(result.get("word")));
        oneDocMap.put("imgUrl", String.valueOf(result.get("imgurl")));
        return oneDocMap;
    }

    public LinkedHashMap<String, String> getNightDoc() {
        LinkedHashMap<String, String> nightDocMap = new LinkedHashMap<>();
        String nightResponse = HttpRequest.get(API_NIGHT).body();
        JSONObject nightJson = JSONObject.parseObject(nightResponse);
        JSONObject result = nightJson.getJSONObject("result");
        nightDocMap.put("content", (String) result.get("content"));
        return nightDocMap;
    }


    /**
     * 用于解析得到的每日天气，并返回语句
     * <p>
     * 样例：
     * 白天天气:小雨  夜间天气:小雨
     * 白天气温:15°C	夜间气温:7°C
     *
     * @param weatherInfoJson 传入的天气信息json
     */
    public String AnalysisWeatherJson(JSONObject weatherInfoJson) {
        String dayWeather = "白天天气:" + (String) weatherInfoJson.get("dayweather") + "｜";
        String nightWeather = "夜间天气:" + (String) weatherInfoJson.get("nightweather") + "\n";
        String dayTemp = "白天气温:" + (String) weatherInfoJson.get("daytemp") + "°C｜";
        String nightTemp = "夜间气温:" + (String) weatherInfoJson.get("nighttemp") + "°C\n";
        return dayWeather + nightWeather + dayTemp + nightTemp;
    }

}
