package com.yang.user.myapptestdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yang.user.myapptestdemo.R;
import com.yang.user.myapptestdemo.bean.City;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YangJie on 2017/4/14.
 */
public class SearchCityAdapter extends RecyclerView.Adapter implements Filterable {
    LayoutInflater layoutInflater = null;
    List<City> mAllCityList;
    List<City> mResultCity;
    Context context;
    private OnClickItemLinsener onClickItemLinsener;

    public SearchCityAdapter(Context context, List<City> mAllCityList) {
        this.context = context;
        this.mAllCityList = mAllCityList;
        layoutInflater = LayoutInflater.from(context);
        mResultCity = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.seach_city_letter_recyle, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyViewHolder myViewHolder = (MyViewHolder) holder;
        ((MyViewHolder) holder).province.setText(mResultCity.get(position).getProvince());
        ((MyViewHolder) holder).title.setText(mResultCity.get(position).getCity());
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.line1);
                if (onClickItemLinsener != null) {
                    onClickItemLinsener.ClickItem(position, myViewHolder.title);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mResultCity.size();
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String str = constraint.toString().toUpperCase();
                FilterResults results = new FilterResults();
                ArrayList<City> cityList = new ArrayList<City>();
                if (mAllCityList != null && mAllCityList.size() != 0) {
                    for (City cb : mAllCityList) {
                        // 匹配全屏、首字母、和城市名中文
                        if (cb.getAllFristPY().indexOf(str) > -1
                                || cb.getAllPY().indexOf(str) > -1
                                || cb.getCity().indexOf(str) > -1) {
                            cityList.add(cb);
                        }
                    }
                }
                results.values = cityList;
                results.count = cityList.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mResultCity = (ArrayList<City>) results.values;
                if (results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetChanged();
                }
            }
        };
        return filter;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.search_province)
        TextView province;
        @BindView(R.id.column_title)
        TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnClickItemLinsener {
        void ClickItem(int position, TextView textView);
    }

    public void setOnClickItemLinsener(OnClickItemLinsener onClickItemLinsener) {
        this.onClickItemLinsener = onClickItemLinsener;
    }

}
