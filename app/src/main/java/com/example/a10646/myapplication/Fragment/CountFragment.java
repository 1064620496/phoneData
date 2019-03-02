package com.example.a10646.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a10646.myapplication.R;
import com.example.a10646.myapplication.Toast.ToastActivity;

public class CountFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_count,null);
        setHasOptionsMenu(true);
        return view;

    }
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
