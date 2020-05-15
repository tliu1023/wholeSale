package com.example.wholesale;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainRecycleViewAdapter extends RecyclerView.Adapter<MainRecycleViewAdapter.MainRecycleViewHolder>{

    private ArrayList<String> itemCategories;
    private LayoutInflater mInflater;

    public MainRecycleViewAdapter(Context context, ArrayList<String> itemCategories){
        this.itemCategories = itemCategories;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MainRecycleViewAdapter.MainRecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_main_itemcategory, parent, false);
        MainRecycleViewHolder vh = new MainRecycleViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MainRecycleViewHolder holder, int position) {
        holder.textView_itemCategory.setText(itemCategories.get(position));
    }

    @Override
    public int getItemCount() {
        return itemCategories.size();
    }

    public class MainRecycleViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        public TextView textView_itemCategory;

        public MainRecycleViewHolder(View v) {
            super(v);
            textView_itemCategory = v.findViewById(R.id.textview_categoryName);
            textView_itemCategory.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            // ViewPager vp = v.getParent().findViewById(R.id.recycleview_main);
            LinearLayout p = (LinearLayout)((ViewGroup) v.getParent()).getParent().getParent();
            Toast.makeText(v.getContext(),
                    "position : " + getLayoutPosition() + " text : " + this.textView_itemCategory.getText(),
                    Toast.LENGTH_SHORT).show();
            Activity temp = (Activity) p.getContext();

            // when click on a item
            // change back to home page, change the text
            ViewPager vp = temp.findViewById(R.id.viewpager_main);
            TextView text = vp.findViewWithTag(R.id.tag_textview_home);
            vp.setCurrentItem(0);
            text.setText(itemCategories.get(getLayoutPosition()));
        }
    }

}
