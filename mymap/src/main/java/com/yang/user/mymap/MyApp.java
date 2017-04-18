package com.yang.user.mymap;

import android.app.Application;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;


/**
 * Created by YangJie on 2017/4/14.
 */

public class MyApp extends Application{
    public static LocationClient mLocClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    @Override
    public void onCreate() {
        super.onCreate();
        mLocClient = new LocationClient(getApplicationContext());
        mLocClient.registerLocationListener( myListener );
    }
}
