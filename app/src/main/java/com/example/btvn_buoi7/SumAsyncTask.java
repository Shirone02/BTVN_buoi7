package com.example.btvn_buoi7;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SumAsyncTask extends AsyncTask<Void, Integer, Long> {
    private ProgressBar progressBar;
    private TextView tvResult;
    private ISumListener ISumListener;

    public SumAsyncTask(ProgressBar progressBar, TextView tvResult) {
        this.progressBar = progressBar;
        this.tvResult = tvResult;
    }

    public void setISumListener(ISumListener ISumListener) {
        this.ISumListener = ISumListener;
    }

    @Override
    protected Long doInBackground(Void... voids) {
        long sum = 0;
        int n = 3000000;
        for (int i = 1; i <= n; i++) {
            sum+=i;
            // Cập nhật tiến trình tính toán
            int progress = (int) ((i / (float) n) * 100);
            publishProgress(progress); // truyền dữ liệu vào onProgressUpdate
        }
        return sum;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Khởi tạo giá trị ban đầu cho ProgressBar
        progressBar.setProgress(0);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        // Cập nhật giá trị tiến trình của ProgressBar
        progressBar.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);
        if(aLong > 0){
            progressBar.setProgress(100);
            tvResult.setText("Tổng của các số từ 1 đến 3 triệu là: " + aLong);
            ISumListener.onLoadSumSuccess(aLong);
        }
       else{
           ISumListener.onLoadSumError("Tính toán lỗi");
        }

    }
}
