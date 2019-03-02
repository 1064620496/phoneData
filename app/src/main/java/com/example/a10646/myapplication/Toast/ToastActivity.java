package com.example.a10646.myapplication.Toast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a10646.myapplication.R;
import com.example.a10646.myapplication.datepicker.CustomDatePicker;
import com.example.a10646.myapplication.datepicker.DateFormatUtils;

/**
 * 说明：Demo
 * 作者：liuwan1992
 * 添加时间：2016/9/28
 * 修改人：liuwan1992
 * 修改时间：2018/12/21
 */
public class ToastActivity extends Activity implements View.OnClickListener {

    private TextView mTvSelectedBeginTime, mTvSelectedTime;
    private CustomDatePicker mBeginTimerPicker, mTimerPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);

        findViewById(R.id.ll_date).setOnClickListener(this);
        mTvSelectedBeginTime = findViewById(R.id.tv_selected_date);
        initBeginTimerPicker();

        findViewById(R.id.ll_time).setOnClickListener(this);
        mTvSelectedTime = findViewById(R.id.tv_selected_time);
        initTimerPicker();
        Button cancelbutton=findViewById(R.id.cancelTime);
        cancelbutton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentTemp = new Intent();
                        intentTemp.putExtra("startTime",mTvSelectedBeginTime.getText().toString());
                        intentTemp.putExtra("endTime",mTvSelectedTime.getText().toString());
                        setResult(0,intentTemp);
                        finish();
                    }
                }
        );
        Button confirmbutton=findViewById(R.id.confirmTime);
        confirmbutton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentTemp = new Intent();
                        intentTemp.putExtra("startTime",mTvSelectedBeginTime.getText().toString());
                        intentTemp.putExtra("endTime",mTvSelectedTime.getText().toString());
                        setResult(1,intentTemp);
                        finish();
                    }
                }
        );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_date:
                // 日期格式为yyyy-MM-dd
                mBeginTimerPicker.show(mTvSelectedBeginTime.getText().toString());
                break;

            case R.id.ll_time:
                // 日期格式为yyyy-MM-dd HH:mm
                mTimerPicker.show(mTvSelectedTime.getText().toString());
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBeginTimerPicker.onDestroy();
    }

    private void initBeginTimerPicker() {
        String beginTime = "2018-10-17 18:00";
        String endTime = DateFormatUtils.long2Str(System.currentTimeMillis(), true);

        mTvSelectedBeginTime.setText(endTime);

        // 通过日期字符串初始化日期，格式请用：yyyy-MM-dd HH:mm
        mBeginTimerPicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                mTvSelectedTime.setText(DateFormatUtils.long2Str(timestamp, true));
            }
        }, beginTime, endTime);
        // 允许点击屏幕或物理返回键关闭
        mBeginTimerPicker.setCancelable(true);
        // 显示时和分
        mBeginTimerPicker.setCanShowPreciseTime(true);
        // 允许循环滚动
        mBeginTimerPicker.setScrollLoop(true);
        // 允许滚动动画
        mBeginTimerPicker.setCanShowAnim(true);
    }

    private void initTimerPicker() {
        String beginTime = "2018-10-17 18:00";
        String endTime = DateFormatUtils.long2Str(System.currentTimeMillis(), true);

        mTvSelectedTime.setText(endTime);

        // 通过日期字符串初始化日期，格式请用：yyyy-MM-dd HH:mm
        mTimerPicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                mTvSelectedTime.setText(DateFormatUtils.long2Str(timestamp, true));
            }
        }, beginTime, endTime);
        // 允许点击屏幕或物理返回键关闭
        mTimerPicker.setCancelable(true);
        // 显示时和分
        mTimerPicker.setCanShowPreciseTime(true);
        // 允许循环滚动
        mTimerPicker.setScrollLoop(true);
        // 允许滚动动画
        mTimerPicker.setCanShowAnim(true);
    }

}
