package com.example.btvn_buoi7;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements  ISumListener{
    private ProgressBar progressBar;
    private TextView tvResult;
    private LinearLayout llLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.pbLoading);
        tvResult = findViewById(R.id.tvResult);
        llLoading = findViewById(R.id.llLoading);

        SumAsyncTask sumAsyncTask = new SumAsyncTask(progressBar,tvResult);
        sumAsyncTask.setISumListener(this);
        sumAsyncTask.execute();
        llLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadSumSuccess(Long aLong) {
        llLoading.setVisibility(View.GONE);
    }

    @Override
    public void onLoadSumError(String message) {
        llLoading.setVisibility(View.GONE);
    }
}