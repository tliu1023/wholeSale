package com.example.wholesale;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class MainPagerAdapter extends PagerAdapter {
    private ArrayList<View> viewLists;

    public MainPagerAdapter() {
    }

    public MainPagerAdapter(ArrayList<View> viewLists) {
        super();
        this.viewLists = viewLists;
    }

    @Override
    public int getCount() {
        return viewLists.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if(position == 0){
            View view;
            LayoutInflater l = LayoutInflater.from(container.getContext());
            view = l.inflate(R.layout.layout_main_home, null);
            TextView tvRecord = view.findViewById(R.id.textview_home);
            tvRecord.setTag(R.id.tag_textview_home);
            container.addView(view);
            return view;
        }else{
            container.addView(viewLists.get(position));
            return viewLists.get(position);
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewLists.get(position));
    }
}
