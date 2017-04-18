package com.yang.user.mymap;

import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.Poi;

import java.util.List;

/**
 * Created by YangJie on 2017/4/14.
 */

public class MyLocationListener implements BDLocationListener {
    @Override
    public void onReceiveLocation(BDLocation location) {

        Log.i("yangjie", "" + location.getCity() );
    }
}
