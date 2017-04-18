package com.yang.user.myapptestdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.yang.user.myapptestdemo.R;
import com.yang.user.myapptestdemo.bean.City;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YangJie on 2017/4/13.
 */

public class CityAdapter extends RecyclerView.Adapter implements SectionIndexer {
    List<City> list;
    private Map<String, List<City>> mMap;
    Context context;
    private List<Integer> mPositions;
    LayoutInflater layoutInflater = null;
    private List<String> mSections;
    private OnClickItemListener onClickItemListener;

    public CityAdapter(List<Integer> mPositions, List<String> mSections, List<City> list, Map<String, List<City>> map, Context context) {
        this.list = list;
        this.mMap = map;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.mPositions = mPositions;
        this.mSections = mSections;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.select_city_recycler, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        int section = getSectionForPosition(position);

        MyViewHolder myViewHolder = (MyViewHolder) holder;
        if (getPositionForSection(section) == position) {
            myViewHolder.word.setVisibility(View.VISIBLE);
            myViewHolder.word.setText(mSections.get(section));
        } else {
            myViewHolder.word.setVisibility(View.GONE);
        }
        City city = mMap.get(mSections.get(section)).get(position - getPositionForSection(section));
        myViewHolder.title.setText(city.getCity());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickItemListener != null){
                    onClickItemListener.OnClickItem(holder.getAdapterPosition() , ((MyViewHolder) holder).title);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.city_recy_word)
        TextView word;
        @BindView(R.id.city_recy_title)
        TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    @Override
    public Object[] getSections() {
        return mPositions.toArray();
    }
    @Override
    public int getPositionForSection(int sectionIndex) {
        if (sectionIndex < 0 || sectionIndex >= mPositions.size()) {
            return -1;
        }
        return mPositions.get(sectionIndex);
    }
    @Override
    public int getSectionForPosition(int position) {
        if (position < 0 || position >= getItemCount()) {
            return -1;
        }
        int index = Arrays.binarySearch(mPositions.toArray(), position);
        return index >= 0 ? index : -index - 2;
    }
    public interface OnClickItemListener {
        void OnClickItem(int position, TextView textView);
    }
    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

}
