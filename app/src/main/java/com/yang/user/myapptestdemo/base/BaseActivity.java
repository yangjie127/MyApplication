package com.yang.user.myapptestdemo.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;

import butterknife.ButterKnife;

/**
 * Created by YangJie on 2017/4/13.
 */

public abstract class BaseActivity extends FragmentActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView();
        initData();
    }
    protected abstract int getLayoutId();
    protected abstract void  initView();
    protected abstract void initData();
}
