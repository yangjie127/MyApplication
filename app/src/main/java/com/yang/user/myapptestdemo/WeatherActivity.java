package com.yang.user.myapptestdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yang.user.myapptestdemo.adapter.CityWeatherAdapter;
import com.yang.user.myapptestdemo.api.CityWeatherApis;
import com.yang.user.myapptestdemo.app.Application;
import com.yang.user.myapptestdemo.base.BaseActivity;
import com.yang.user.myapptestdemo.bean.CityWeatherBean;
import com.yang.user.myapptestdemo.utils.Contens;
import com.yang.user.myapptestdemo.utils.L;
import com.yang.user.myapptestdemo.utils.NetUtil;
import com.yang.user.myapptestdemo.utils.T;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherActivity extends BaseActivity implements Application.EventHandler {
    /**
     * Title
     */
    @BindView(R.id.title_city_manager)
    ImageView city_manger; //城市管理器
    @BindView(R.id.title_location)
    ImageView title_location;  //定位
    @BindView(R.id.title_share)
    ImageView title_share;          //分享
    @BindView(R.id.title_update_btn)
    ImageView title_update_btn; //刷新
    @BindView(R.id.title_update_progress)
    ProgressBar mProgressbar;
    @BindView(R.id.title_city_name)
    TextView cityTextView;
    @BindView(R.id.temperature)
    TextView temperature; //温度
    @BindView(R.id.climate)
    TextView climate; //气候
    @BindView(R.id.wind)
    TextView wind;
    @BindView(R.id.pm_data)
    TextView pm_data;

    /**
     * Content
     */
    @BindView(R.id.city)
    TextView city;                 //城市
    @BindView(R.id.time)
    TextView time;                 //时间
    @BindView(R.id.week_today)
    TextView week_today;    //星期
    @BindView(R.id.weather_img)
    ImageView weather_img;  //天气图标

    /**
     * ViewPager
     */
    @BindView(R.id.viewpage)
    ViewPager viewPager;
    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    CityWeatherAdapter cityadapter;

    private LocationClient mLocationClient;
    private Application mApplication;
    private static final int LOACTION_OK = 0;

    /**
     * OnClick
     * 添加城市
     * List
     */
    @OnClick(R.id.title_city_manager)
    public void setCity_Manger() {
        Intent intent = new Intent(WeatherActivity.this, SelectCtiyActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("cityname", cityTextView.getText().toString());
        intent.putExtras(bundle);
        startActivityForResult(intent, 0);
    }

    /**
     * OnClick
     * 定位
     */
    @OnClick(R.id.title_location)
    public void setCity_Location() {
        getLocation();
    }

    /**
     *
     */
    @OnClick(R.id.title_update_progress)
    public void onProgress() {
        initData();
        mProgressbar.setVisibility(View.INVISIBLE);
    }

    /**
     * 定位
     */
    public void getLocation() {
        if (NetUtil.getNetworkState(this) != NetUtil.NETWORN_NONE) {
            if (!mLocationClient.isStarted())
                mLocationClient.start();
            mLocationClient.requestLocation();
            initData();
            T.showShort(this, "正在定位...");
        } else {
            T.showShort(this, "网络不给力");
        }
    }

    public static final int CITY_CODE = 1;
    String mNewIntentCity = "北京";
    List<Fragment> fragments = new ArrayList<Fragment>();
    String[] tabTitle = new String[]{"周日", "周一", "周二", "周三", "周四", "周五", "周六"};

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == RESULT_OK) {
            mNewIntentCity = (String) data.getSerializableExtra("city");
            Log.e("yangjie", "" + mNewIntentCity);
            city.setText(mNewIntentCity);
            cityTextView.setText(mNewIntentCity + "天气");
            mHandler.sendEmptyMessage(CITY_CODE);
            initData();
        }
    }
    boolean click = false;
    @OnClick(R.id.title_update_btn)
    public void onUpDataCilck() {
        if (NetUtil.getNetworkState(this) != NetUtil.NETWORN_NONE) {
//            T.showShort(this, "请先选择城市或定位！");
            click = true;
            initData();
            if(click ){

                mProgressbar.setVisibility(View.INVISIBLE);
                click = false;
            }
        } else {
            T.showShort(this, "网络不给力");
        }
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_weather;
    }

    @Override
    protected void initView() {
        Application.mListeners.add(this);
        mApplication = Application.getInstance();
        mLocationClient = mApplication.getLocationClient();
        mLocationClient.registerLocationListener(new MyLocationListener());
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
        mProgressbar.setVisibility(View.VISIBLE);
        getLocation();
        week_today.setText("今天 " + Contens.getWeek(0, Contens.XING_QI));
        initCity();
    }

    private void initCity() {
        fragments.add(new WeatherCityFragment());
        fragments.add(new WeatherCityFragment());
        fragments.add(new WeatherCityFragment());
        fragments.add(new WeatherCityFragment());
        fragments.add(new WeatherCityFragment());
        fragments.add(new WeatherCityFragment());
        fragments.add(new WeatherCityFragment());
        cityadapter = new CityWeatherAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(cityadapter);
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[1]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[2]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[3]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[4]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[5]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[6]));

        mTabLayout.setupWithViewPager(viewPager);

        mTabLayout.getTabAt(0).setText(tabTitle[0]);
        mTabLayout.getTabAt(1).setText(tabTitle[1]);
        mTabLayout.getTabAt(2).setText(tabTitle[2]);
        mTabLayout.getTabAt(3).setText(tabTitle[3]);
        mTabLayout.getTabAt(4).setText(tabTitle[4]);
        mTabLayout.getTabAt(5).setText(tabTitle[5]);
        mTabLayout.getTabAt(6).setText(tabTitle[6]);
    }

    class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            String cityName = location.getCity();
//                Log.e("yangjie","" + location.getCity());
            mLocationClient.stop();
            Message msg = mHandler.obtainMessage();
            msg.what = LOACTION_OK;
            msg.obj = cityName;
            mHandler.sendMessage(msg);// 更新天气
        }
    }

    List<CityWeatherBean.HeWeatherBean.BasicBean> list = new ArrayList<>();
    @Override
    protected void initData() {

        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(CityWeatherApis.Host).addConverterFactory(GsonConverterFactory.create(gson)).build();
        CityWeatherApis cityWeatherApis = retrofit.create(CityWeatherApis.class);
        String star = cityTextView.getText().toString();
        Log.e("yangjie", "" + star);
        Call<CityWeatherBean> getCityevent = cityWeatherApis.getCityWeatherBean(mNewIntentCity, Contens.CITY_WEATHER);
        getCityevent.enqueue(new Callback<CityWeatherBean>() {
            @Override
            public void onResponse(Call<CityWeatherBean> call, Response<CityWeatherBean> response) {
                if (response.body().getHeWeather().get(0).getDaily_forecast() != null) {
                    Log.e("yangjie", "" + response.body().getHeWeather().get(0).getDaily_forecast());
                    String Str = response.body().getHeWeather().get(0).getDaily_forecast().get(0).getCond().getTxt_d();
                    wind.setText(response.body().getHeWeather().get(0).getDaily_forecast().get(0).getCond().getTxt_d());

                    temperature.setText(response.body().getHeWeather().get(0).getDaily_forecast().get(0).getTmp().getMin() + " ~ " + response.body().getHeWeather().get(0).getDaily_forecast().get(0).getTmp().getMax());
                    climate.setText(response.body().getHeWeather().get(0).getDaily_forecast().get(0).getWind().getSc());

                    pm_data.setText(response.body().getHeWeather().get(0).getNow().getTmp() + "℃");
                    list.add(response.body().getHeWeather().get(0).getBasic());
                    Log.e("yangjie", "" + Str);

                    Contens.getCityWeather(Str, WeatherActivity.this, weather_img);
                }

            }

            @Override
            public void onFailure(Call<CityWeatherBean> call, Throwable t) {
                Log.e("yangjie", "" + t.getMessage());
            }
        });
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case CITY_CODE:
                    cityTextView.setText(mNewIntentCity + "天气");
                    break;
                case LOACTION_OK:
                    String cityName = (String) msg.obj;
                    city.setText(cityName);
                    cityTextView.setText(cityName + "天气");
                    L.e("cityName = " + cityName);
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
    }

    @Override
    public void onCityComplite() {

    }

    @Override
    public void onNetChange() {
        if (NetUtil.getNetworkState(this) == NetUtil.NETWORN_NONE) {
            T.showLong(this, "网络无连接");
        }
    }
}
