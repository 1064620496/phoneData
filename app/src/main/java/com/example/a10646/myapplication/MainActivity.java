package com.example.a10646.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.a10646.myapplication.Fragment.CountFragment;
import com.example.a10646.myapplication.Fragment.HintFragment;
import com.example.a10646.myapplication.Fragment.MeFragment;
import com.example.a10646.myapplication.Util.BottomBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomBar bottomBar = findViewById(R.id.bottom_bar);
        bottomBar.setContainer(R.id.fl_container)
                .setTitleBeforeAndAfterColor("#999999", "#00574B")
                .addItem(CountFragment.class,
                        "统计",
                        R.drawable.sta_unchecked,
                        R.drawable.sta_checked)
                .addItem(HintFragment.class,
                        "提醒",
                        R.drawable.plan_unchecked,
                        R.drawable.plan_checked)
                .addItem(MeFragment.class,
                        "我的",
                        R.drawable.me_unchecked,
                        R.drawable.me_checked)
                .build();
    }
}
