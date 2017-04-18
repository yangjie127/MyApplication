package com.yang.user.myapptestdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yang.user.myapptestdemo.adapter.CityAdapter;
import com.yang.user.myapptestdemo.adapter.SearchCityAdapter;
import com.yang.user.myapptestdemo.app.Application;
import com.yang.user.myapptestdemo.base.BaseActivity;
import com.yang.user.myapptestdemo.bean.City;
import com.yang.user.myapptestdemo.db.CityDB;
import com.yang.user.myapptestdemo.utils.NetUtil;
import com.yang.user.myapptestdemo.utils.T;
import com.yang.user.myapptestdemo.view.BladeView;
import com.yang.user.myapptestdemo.view.PinnedHeaderListView;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class SelectCtiyActivity extends BaseActivity implements Application.EventHandler, CityAdapter.OnClickItemListener, TextWatcher {
    // private Button mCancelSearchBtn;
    private ImageButton mClearSearchBtn;
    private View mCityContainer;
    private View mSearchContainer;
    private PinnedHeaderListView mCityListView;
    @BindView(R.id.citys_bladeview)
     BladeView mLetter;
    @BindView(R.id.search_list)
    RecyclerView mSearchListView;
    private List<City> mCities;
    //    private SearchCityAdapter mSearchCityAdapter;
//    private CityAdapter mCityAdapter;
    // 首字母集
    private List<String> mSections;
    // 根据首字母存放数据
    private Map<String, List<City>> mMap;
    // 首字母位置集
    private List<Integer> mPositions;
    // 首字母对应的位置
    private Map<String, Integer> mIndexer;
    private CityDB mCityDB;
    private InputMethodManager mInputMethodManager;
    private SearchCityAdapter searchCityAdapter;
    private TextView mTitleTextView;
    @BindView(R.id.search_edit)
    EditText mSearchEditText;

    @OnClick(R.id.title_back)
    public void GoToWeatherActivity() {
        finish();
    }

    @OnClick(R.id.ib_clear_text)
    public void ClearText() {
        if (!TextUtils.isEmpty(mSearchEditText.getText().toString())) {
            mSearchEditText.setText("");
            mInputMethodManager.hideSoftInputFromWindow(
                    mSearchEditText.getWindowToken(), 0);
        }
    }

    private ProgressBar mTitleProgressBar;
    RecyclerView mRecyclerView;
    CityAdapter adapter;

    Application mApplication;

    @BindView(R.id.title_name)
    TextView title_name;
    Bundle bundle;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_ctiy;
    }

    @Override
    protected void initData() {
        mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        mApplication = Application.getInstance();
        if (mApplication.isCityListComplite()) {

            mSections = mApplication.getSections();
            mPositions = mApplication.getPositions();
            mMap = mApplication.getMap();
            mCities = mApplication.getCityList();

            adapter = new CityAdapter(mPositions, mSections, mCities, mMap, SelectCtiyActivity.this);
            mRecyclerView.setAdapter(adapter);
            adapter.setOnClickItemListener(this);

            mLetter.setOnItemClickListener(new BladeView.OnItemClickListener() {

                @Override
                public void onItemClick(String s) {
                    if (mIndexer.get(s) != null) {
                    }
                }
            });


        }
//        Log.e("yangjie" , "----------------"+ mCities.size() + "" + mCities.toString());
    }

    @Override
    protected void initView() {
        bundle = this.getIntent().getExtras();
        String city = bundle.getString("cityname");
        mSearchEditText.addTextChangedListener(this);
        title_name.setText(city);
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView_select);
        LinearLayoutManager layoutManager = new LinearLayoutManager(SelectCtiyActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mSearchListView.setLayoutManager(new LinearLayoutManager(SelectCtiyActivity.this));
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
    }

    @Override
    public void onCityComplite() {
        Application.mListeners.add(this);
        mApplication = Application.getInstance();
        mSections = mApplication.getSections();
        mPositions = mApplication.getPositions();
        mMap = mApplication.getMap();
        mCities = mApplication.getCityList();
        adapter = new CityAdapter(mPositions, mSections, mCities, mMap, SelectCtiyActivity.this);
        mRecyclerView.setAdapter(adapter);
        searchCityAdapter = new SearchCityAdapter(SelectCtiyActivity.this, mCities);
        mSearchListView.setAdapter(searchCityAdapter);
    }
    @Override
    public void onNetChange() {
        if (NetUtil.getNetworkState(this) == NetUtil.NETWORN_NONE) {
            T.showLong(this, "网络无连接");
        }
    }
    @Override
    public void OnClickItem(int position, TextView view) {
        Log.e("yangjie", "" + position);
        title_name.setText(view.getText());
        startActivity(view.getText().toString());
    }
    private void startActivity(String city) {
        Intent i = new Intent();
        i.putExtra("city", city);
        setResult(RESULT_OK, i);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //-----------input is EditText  hide and show Recylerview
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        searchCityAdapter = new SearchCityAdapter(SelectCtiyActivity.this, mCities);
        mSearchListView.setAdapter(searchCityAdapter);
        if (mCities.size() < 1 || TextUtils.isEmpty(s)) {
//            mCityContainer.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.VISIBLE);
            mSearchListView.setVisibility(View.INVISIBLE);
//            mClearSearchBtn.setVisibility(View.GONE);
        } else {
            mSearchListView.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.INVISIBLE);
//            mCityContainer.setVisibility(View.INVISIBLE);
//            mSearchContainer.setVisibility(View.VISIBLE);
            searchCityAdapter.getFilter().filter(s);
        }
        searchCityAdapter.setOnClickItemLinsener(new SearchCityAdapter.OnClickItemLinsener() {
            @Override
            public void ClickItem(int position, TextView textView) {
                title_name.setText(textView.getText());
                startActivity(textView.getText().toString());
            }
        });
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
