package com.example.a10646.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a10646.myapplication.R;
import com.example.a10646.myapplication.Toast.ToastActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class CountFragment extends Fragment {
    private SwipeRefreshLayout swipeRefreshLayout;
    private LineChart lineChart;
    private BarChart barChart;
    private PieChart pieChart;
    private List<ILineDataSet> lineDataSets = new ArrayList<>();
    int changeTimes=0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_count,null);
        setHasOptionsMenu(true);
        swipeRefreshLayout = view.findViewById(R.id.swipeLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,
                R.color.colorPrimary,
                R.color.colorPrimaryDark,
                R.color.current_time_bg);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);;
        swipeRefreshLayout.setProgressBackgroundColor(R.color.current_time_text);
        //swipeRefreshLayout.setPadding(20, 20, 20, 20);
        //swipeRefreshLayout.setProgressViewOffset(true, 100, 200);
        //swipeRefreshLayout.setDistanceToTriggerSync(50);
        swipeRefreshLayout.setProgressViewEndTarget(true, 100);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        /*data.clear();
                        for(int i=0;i<20;i++){
                            data.add("SwipeRefreshLayout下拉刷新"+i);
                        }*/
                        //以下获取数据
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mHandler.sendEmptyMessage(1);
                    }
                }).start();
            }
        });
        //handler
        //linechart、barchart、piechart初始化
        lineChart=view.findViewById(R.id.lineChart);
        lineChart.setBackgroundColor(0xff00ff00);
        Description des=new Description();
        des.setText("ltyforeveralive");
        des.setTextColor(0xff5500ff);
        des.setTextSize(10f);
        lineChart.setDescription(des);
        lineChart.setDrawBorders(true);//边框
        lineChart.setBorderColor(0xffff0000);
        lineChart.invalidate(); // refresh
        barChart=view.findViewById(R.id.chart1);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);//值显示在上方或内部
        barChart.setDescription(des);
        barChart.setPinchZoom(true);//可二指缩放
        barChart.setDrawGridBackground(false);//不显示表格背景颜色
        //设置柱状图x轴
        XAxis xAxis = barChart.getXAxis();
        //设置X轴显示文字旋转角度-60意为逆时针旋转60度
        xAxis.setLabelRotationAngle(-60);
        //设置X轴显示位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //X轴纵向分割线，一般不设置显示
        xAxis.setDrawGridLines(false);
        // X轴显示Value值的精度，与自定义X轴返回的Value值精度一致
        xAxis.setGranularity(1f);
        //X轴横坐标显示的数量
        xAxis.setLabelCount(7);
        //X轴最大坐标
        xAxis.setAxisMaximum(6f);
        //X轴最小坐标
        xAxis.setAxisMinimum(0.5f);

        //Y左边轴

        YAxis leftAxis = barChart.getAxisLeft();

        //设置Y左边轴显示的值 label 数量

        leftAxis.setLabelCount(8, false);

        //设置值显示的位置，我们这里设置为显示在Y轴外面

        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);

        //设置Y轴 与值的空间空隙 这里设置30f意为30%空隙，默认是10%

        leftAxis.setSpaceTop(30f);

        //设置Y轴最小坐标和最大坐标

        leftAxis.setAxisMinimum(0f);

        leftAxis.setAxisMaximum(80f);



        //Y轴右边轴的设置，跟左边轴类似

        YAxis rightAxis = barChart.getAxisRight();

        rightAxis.setDrawGridLines(false);

        rightAxis.setLabelCount(8, false);

        rightAxis.setSpaceTop(30f);

        rightAxis.setAxisMinimum(0f);

        rightAxis.setAxisMaximum(80f);

        pieChart=view.findViewById(R.id.pieChart);
        pieChart.setDescription(des);
        //中心圆
        pieChart.setHoleRadius(5f);
        //去掉半透明
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setDrawEntryLabels(true);


        new Thread(new Runnable() {
            @Override
            public void run() {
                        /*data.clear();
                        for(int i=0;i<20;i++){
                            data.add("SwipeRefreshLayout下拉刷新"+i);
                        }*/
                //以下获取数据
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler.sendEmptyMessage(1);
            }
        }).start();
        return view;

    }
    private void getPiechartData(int changeTimes)
    {
        PieEntry pieEntry1 = new PieEntry(10f,"北京");
        PieEntry pieEntry2 = new PieEntry(40f,"上海");
        PieEntry pieEntry3 = new PieEntry(15f,"杭州");
        PieEntry pieEntry4 = new PieEntry(35f,"深圳");

        List<PieEntry>list=new ArrayList<>();
        list.add(pieEntry1);list.add(pieEntry2);list.add(pieEntry3);list.add(pieEntry4);


        PieDataSet pieDataSet = new PieDataSet(list, "");

        //一般有多少项数据，就配置多少个颜色的，少的话会复用最后一个颜色，多的话无大碍
        pieDataSet.setColors(0xfffeb64d,0xffff7c7c,0xff9287e7,0xff60acfc);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        //显示值格式化，这里使用Api,添加百分号
        pieData.setValueFormatter(new PercentFormatter());
        //设置值得颜色
        pieData.setValueTextColor(0xffFFFFFF);
        //设置值得大小
        pieData.setValueTextSize(10f);
        pieChart.invalidate();

    }
    private void getBarchartData(int changeTimes)
    {

        //模拟数据

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        yVals1.add(new BarEntry(1.2f, 10));

        yVals1.add(new BarEntry(2.2f, 20));

        yVals1.add(new BarEntry(3.2f, 30));

        yVals1.add(new BarEntry(4.2f, 40));

        yVals1.add(new BarEntry(5.2f, 50));



        BarDataSet set1;

        if (barChart.getData() != null &&

                barChart.getData().getDataSetCount() > 0) {

            set1 = (BarDataSet) barChart.getData().getDataSetByIndex(0);

            set1.setValues(yVals1);

            barChart.getData().notifyDataChanged();

            barChart.notifyDataSetChanged();

        } else {

            set1 = new BarDataSet(yVals1, "号码联系次数统计");

            //设置有四种颜色

            set1.setColors(ColorTemplate.MATERIAL_COLORS);

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();

            dataSets.add(set1);

            BarData data = new BarData(dataSets);

            data.setValueTextSize(10f);

            data.setBarWidth(0.6f);

            //data.setValueFormatter(new CallCountValueFormatter());

            //设置数据

            barChart.setData(data);

        }
        barChart.invalidate();

    }
    public void getLinechartData(int changeTimes)
    {
        lineDataSets.clear();
        List<Entry> entries = new ArrayList<Entry>();//Entry 为mpandroid中对象，放置xy坐标数据
        for(int i=0;i<10;i++)
        {
            entries.add(new Entry(i*1, i*changeTimes));//(i,i)代表一个点
        }
        LineDataSet dataSet = new LineDataSet(entries, "first"); // add entries to dataset
        dataSet.setColor(R.color.colorAccent);
        dataSet.setValueTextColor(R.color.current_time_text);
        List<Entry> entries2 = new ArrayList<Entry>();//Entry 为mpandroid中对象，放置xy坐标数据
        for(int i=0;i<10;i++)
        {
            entries2.add(new Entry(i*1, i*2));//(i,i)代表一个点
        }
        LineDataSet dataSet2 = new LineDataSet(entries2, "second"); // add entries to dataset
        dataSet2.setColor(R.color.colorAccent);
        dataSet2.setValueTextColor(R.color.current_time_text);
        lineDataSets.add(dataSet);
        lineDataSets.add(dataSet2);
    }
    private void drawLinechart(LineChart lineChart)
    {
        LineData lineData = new LineData(lineDataSets);
        lineChart.setData(lineData);
        lineChart.invalidate(); // refresh
    }
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:

                    swipeRefreshLayout.setRefreshing(false);
                    getLinechartData(++changeTimes);
                    drawLinechart(lineChart);
                    getBarchartData(++changeTimes);
                    getPiechartData(++changeTimes);
                    //adapter.notifyDataSetChanged();
                    //swipeRefreshLayout.setEnabled(false);
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.sta, menu);
        MenuItem refreshItem=menu.findItem(R.id.refresh);
        MenuItem dayItem=menu.findItem(R.id.day);
        MenuItem weekItem=menu.findItem(R.id.week);
        MenuItem yearItem=menu.findItem(R.id.year);
        MenuItem selfItem=menu.findItem(R.id.self);
        selfItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(getActivity(), ToastActivity.class);
                startActivityForResult(intent,1);
                return false;
            }
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        TextView tvStart=getActivity().findViewById(R.id.startTime);
        TextView tvEnd=getActivity().findViewById(R.id.endTime);
        if(requestCode == 1){
            tvStart.setText(data.getStringExtra("startTime"));
            tvEnd.setText(data.getStringExtra("endTime"));
        }
    }

}
