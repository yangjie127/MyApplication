package com.yang.user.myapptestdemo.api;

import com.yang.user.myapptestdemo.bean.CityWeatherBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by YangJie on 2017/4/17.
 */

public interface CityWeatherApis {
    String Host = "http://guolin.tech/api/";
    /**
     * http://guolin.tech/api/weather?cityid=北京&key=a209d035364f400d8deb0bc0386955e0
     */
    @GET("weather?")
    Call<CityWeatherBean> getCityWeatherBean (@Query("cityid") String cityid,@Query("key") String key);
}
