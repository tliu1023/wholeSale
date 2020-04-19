package com.example.wholesale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager mainViewPager;
    private ArrayList<View> mainViewList;
    private MainPagerAdapter mainViewAdapter;

    final ArrayList<LinearLayout> buttonList = new ArrayList<>();
    final ArrayList<ImageButton> imagebuttonList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("username");

        //TextView textview_greeting = findViewById(R.id.textview_greeting);
        //textview_greeting.setText("Hello "+ userName + "!");

        initButton();
        initViewPage();
    }

    private void initButton() {
        buttonList.add((LinearLayout) findViewById(R.id.button_main_home));
        buttonList.add((LinearLayout) findViewById(R.id.button_main_items));
        buttonList.add((LinearLayout) findViewById(R.id.button_main_cart));
        buttonList.add((LinearLayout) findViewById(R.id.button_main_me));

        imagebuttonList.add((ImageButton) findViewById(R.id.image_main_home));
        imagebuttonList.add((ImageButton) findViewById(R.id.image_main_items));
        imagebuttonList.add((ImageButton) findViewById(R.id.image_main_cart));
        imagebuttonList.add((ImageButton) findViewById(R.id.image_main_me));

        for(int i = 0; i < buttonList.size(); i++){
            final int finalI = i;
            buttonList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(int j = 0; j < imagebuttonList.size(); j++){
                        if(j == finalI){
                            imagebuttonList.get(j).setAlpha(1.0f);
                            mainViewPager.setCurrentItem(finalI, true);
                        }else{
                            imagebuttonList.get(j).setAlpha(0.3f);
                        }
                    }
                }
            });
        }
    }

    private void initViewPage() {
        mainViewPager = findViewById(R.id.viewpager_main);

        mainViewList = new ArrayList<>();
        LayoutInflater li = getLayoutInflater();
        mainViewList.add(li.inflate(R.layout.layout_main_home,null,false));
        mainViewList.add(li.inflate(R.layout.layout_main_items,null,false));
        mainViewList.add(li.inflate(R.layout.layout_main_cart,null,false));
        mainViewList.add(li.inflate(R.layout.layout_main_me,null,false));
        mainViewAdapter = new MainPagerAdapter(mainViewList);
        mainViewPager.setAdapter(mainViewAdapter);

        mainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for(int j = 0; j < imagebuttonList.size(); j++){
                    if(j == position){
                        imagebuttonList.get(j).setAlpha(1.0f);
                    }else{
                        imagebuttonList.get(j).setAlpha(0.3f);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
