package com.yang.user.myapptestdemo.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yang.user.myapptestdemo.R;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by YangJie on 2017/4/17.
 */

public class Contens {
    public static String CITY_WEATHER = "a209d035364f400d8deb0bc0386955e0";

    private static final String[] WEEK = {"天", "一", "二", "三", "四", "五", "六"};
    public static final String XING_QI = "星期";
    public static final String ZHOU = "周";

    public static String getWeek(int num, String format) {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        int weekNum = c.get(Calendar.DAY_OF_WEEK) + num;
        if (weekNum > 7)
            weekNum = weekNum - 7;
        return format + WEEK[weekNum - 1];
    }
    public static String WEATHER_SUNNY = "晴";
    public static String WEATHER_CLOUDY = "阴";
    public static String WEATHER_MORE_CLOUDY = "多云";
    public static String WEATHER_MICROMETEOROLOGY = "扬沙";
    public static String WEATHER_LESS_CLOUDY = "少云";
    public static String WEATHER_SUNNY_OR_CLOUDY = "晴间多云";
    public static String WEATHER_WIND = "有风";
    public static String WEATHER_CALM = "平静";
    public static String WEATHER_SMALL_WIND = "微风";
    public static String WEATHER_HE_WIND = "和风";
    public static String WEATHER_QING_WIND = "清风";
    public static String WEATHER_STRONG_WIND = "强风/劲风";
    public static String WEATHER_SPRINT_WIND = "疾风";
    public static String WEATHER_BIG_WIND = "大风";
    public static String WEATHER_SEVER_WIND = "烈风";
    public static String WEATHER_STORM_WIND = "风暴";
    public static String WEATHER_CRAZY_WIND = "狂爆风";
    public static String WEATHER_JUFENG_WIND = "飓风";
    public static String WEATHER_TORNADO_WIND = "龙卷风";
    public static String WEATHER_HOT_STORM = "热带风暴";
    public static String WEATHER_SHOWER_RAIN = "阵雨";
    public static String WEATHER_STRONG_RAIN = "强阵雨";
    public static String WEATHER_THUNDER_RAIN = "雷阵雨";
    public static String WEATHER_STRONG_THUNDER_RAIN = "强雷阵雨";
    public static String WEATHER_THUNDER_RAIN_AND_HAIL = "雷阵雨伴有冰雹";
    public static String WEATHER_LIGHT_RAIN = "小雨";
    public static String WEATHER_MODERATE_RAIN = "中雨";
    public static String WEATHER_HEAVY_RAIN = "大雨";
    public static String WEATHER_STRONG_HEAVY_RAIN = "极端降雨";
    public static String WEATHER_DRIZZLE = "毛毛雨/细雨";
    public static String WEATHER_BAOYU_RAIN = "暴雨";
    public static String WEATHER_BIG_BAOYU_RAIN = "大暴雨";
    public static String WEATHER_BIG_TEDABAOYU_RAIN = "特大暴雨";
    public static String WEATHER_FREEZING_RAIN = "冻雨";
    public static String WEATHER_LIGHT_SNOW = "小雪";
    public static String WEATHER_MODERATE_SNOW = "中雪";
    public static String WEATHER_BIG_SNOW = "大雪";
    public static String WEATHER_BLIZZARD_SNOW = "暴雪";
    public static String WEATHER_SLEET = "雨夹雪";
    public static String WEATHER_RAIN_AND_SNOW_WEATHER = "雨雪天气";
    public static String WEATHER_ARRAY_SLEET_SNOW = "阵雨夹雪";
    public static String WEATHER_SNOW_SHOWER = "阵雪";
    public static String WEATHER_THE_MIST = "薄雾";
    public static String WEATHER_FOG = "雾";
    public static String WEATHER_HAZE = "霾";
    public static String WEATHER_FLY_ASH = "浮尘";
    public static String WEATHER_DUST_STORMS = "沙尘暴";
    public static String WEATHER_STRONG_SANDSTORMS = "强沙尘暴";
    public static String WEATHER_HOT = "热";
    public static String WEATHER_COOL = "冷";
    public static String WEATHER_THE_UNKNOWN = "未知";

    public static void getCityWeather(String Str, Context contens, ImageView weather_img) {
        if (Str.equals(WEATHER_SUNNY)) {
            Glide.with(contens).load("https://cdn.heweather.com/cond_icon/100.png").crossFade(1000).into(weather_img);
        } else if (Str.equals(WEATHER_MORE_CLOUDY)) {
            Glide.with(contens).load("https://cdn.heweather.com/cond_icon/101.png").crossFade(1000).into(weather_img);
        } else if (Str.equals(WEATHER_MODERATE_RAIN)) {
            Glide.with(contens).load("https://cdn.heweather.com/cond_icon/306.png").crossFade(1000).into(weather_img);
        } else if (Str.equals(WEATHER_LIGHT_RAIN)) {
            Glide.with(contens).load("https://cdn.heweather.com/cond_icon/305.png").crossFade(1000).into(weather_img);
        } else if (Str.equals(WEATHER_LESS_CLOUDY)) {
            Glide.with(contens).load("https://cdn.heweather.com/cond_icon/102.png").crossFade(1000).into(weather_img);
        } else if (Str.equals(WEATHER_SUNNY_OR_CLOUDY)) {
            Glide.with(contens).load("https://cdn.heweather.com/cond_icon/103.png").crossFade(1000).into(weather_img);
        } else if (Str.equals(WEATHER_CLOUDY)) {
            Glide.with(contens).load("https://cdn.heweather.com/cond_icon/104.png").crossFade(1000).into(weather_img);
        } else if (Str.equals(WEATHER_WIND)) {
            Glide.with(contens).load("https://cdn.heweather.com/cond_icon/200.png").crossFade(1000).into(weather_img);
        } else if (Str.equals(WEATHER_CALM)) {
            Glide.with(contens).load("https://cdn.heweather.com/cond_icon/201.png").crossFade(1000).into(weather_img);
        } else if (Str.equals(WEATHER_SMALL_WIND)) {
            Glide.with(contens).load("https://cdn.heweather.com/cond_icon/202.png").crossFade(1000).into(weather_img);
        } else if (Str.equals(WEATHER_HE_WIND)) {
            Glide.with(contens).load("https://cdn.heweather.com/cond_icon/203.png").crossFade(1000).into(weather_img);
        } else if (Str.equals(WEATHER_QING_WIND)) {
            Glide.with(contens).load("https://cdn.heweather.com/cond_icon/204.png").crossFade(1000).into(weather_img);
        } else if (Str.equals(WEATHER_STRONG_WIND)) {
            Glide.with(contens).load("https://cdn.heweather.com/cond_icon/205.png").crossFade(1000).into(weather_img);
        } else if (Str.equals(WEATHER_SPRINT_WIND)) {
            Glide.with(contens).load("https://cdn.heweather.com/cond_icon/206.png").crossFade(1000).into(weather_img);
        } else if (Str.equals(WEATHER_BIG_WIND)) {
            Glide.with(contens).load("https://cdn.heweather.com/cond_icon/207.png").crossFade(1000).into(weather_img);
        } else if (Str.equals(WEATHER_SEVER_WIND)) {
            Glide.with(contens).load("https://cdn.heweather.com/cond_icon/208.png").crossFade(1000).into(weather_img);
        } else if (Str.equals(WEATHER_STORM_WIND)) {
            Glide.with(contens).load("https://cdn.heweather.com/cond_icon/209.png").crossFade(1000).into(weather_img);
        } else if (Str.equals(WEATHER_CRAZY_WIND)) {
            Glide.with(contens).load("https://cdn.heweather.com/cond_icon/210.png").crossFade(1000).into(weather_img);
        } else if (Str.equals(WEATHER_JUFENG_WIND)) {
            Glide.with(contens).load("https://cdn.heweather.com/cond_icon/211.png").crossFade(1000).into(weather_img);
        } else if (Str.equals(WEATHER_TORNADO_WIND)) {
            Glide.with(contens).load("https://cdn.heweather.com/cond_icon/212.png").crossFade(1000).into(weather_img);
        } else if (Str.equals(WEATHER_HOT_STORM)) {
            Glide.with(contens).load("https://cdn.heweather.com/cond_icon/213.png").crossFade(1000).into(weather_img);
        } else if (Str.equals(WEATHER_SHOWER_RAIN)) {
            Glide.with(contens).load("https://cdn.heweather.com/cond_icon/300.png").crossFade(1000).into(weather_img);
        } else if (Str.equals(WEATHER_STRONG_RAIN)) {
            Glide.with(contens).load("https://cdn.heweather.com/cond_icon/301.png").crossFade(1000).into(weather_img);
        } else if (Str.equals(WEATHER_THUNDER_RAIN)) {
            Glide.with(contens).load("https://cdn.heweather.com/cond_icon/302.png").crossFade(1000).into(weather_img);
        } else if (Str.equals(WEATHER_STRONG_THUNDER_RAIN)) {
            Glide.with(contens).load("https://cdn.heweather.com/cond_icon/303.png").crossFade(1000).into(weather_img);
        } else if (Str.equals(WEATHER_THUNDER_RAIN_AND_HAIL)) {
            Glide.with(contens).load("https://cdn.heweather.com/cond_icon/304.png").crossFade(1000).into(weather_img);
        } else if (Str.equals(WEATHER_HEAVY_RAIN)) {
            Glide.with(contens).load("https://cdn.heweather.com/cond_icon/307.png").crossFade(1000).into(weather_img);
        } else if (Str.equals(WEATHER_STRONG_HEAVY_RAIN)) {
            Glide.with(contens).load("https://cdn.heweather.com/cond_icon/308.png").crossFade(1000).into(weather_img);
        } else {
            Glide.with(contens).load(R.mipmap.biz_plugin_weather_qing).crossFade(1000).into(weather_img);
        }
    }
}
