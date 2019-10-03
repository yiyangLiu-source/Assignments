package com.assignment.infs3634;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvCN;
    private TextView tvUS;
    private TextView tvKR;
    private TextView tvJP;
    private TextView tvRU;

    private ConstraintLayout clCN;
    private ConstraintLayout clUS;
    private ConstraintLayout clKR;
    private ConstraintLayout clJP;
    private ConstraintLayout clRU;

    private Button btn12;
    private Button btn24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        tvCN = findViewById(R.id.tv_time_cn);
        tvUS = findViewById(R.id.tv_time_us);
        tvKR = findViewById(R.id.tv_time_kr);
        tvJP = findViewById(R.id.tv_time_jp);
        tvRU = findViewById(R.id.tv_time_ru);

        clCN = findViewById(R.id.cl_cn);
        clRU = findViewById(R.id.cl_ru);
        clUS = findViewById(R.id.cl_us);
        clJP = findViewById(R.id.cl_jp);
        clKR = findViewById(R.id.cl_kr);

        btn12 = findViewById(R.id.btn_12);
        btn24 = findViewById(R.id.btn_24);
        btn12.setOnClickListener(this);
        btn24.setOnClickListener(this);

        setTime(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.check, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.check_cn:
                if (item.isChecked()) {
                    clCN.setVisibility(View.GONE);
                } else {
                    clCN.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.check_us:
                if (item.isChecked()) {
                    clUS.setVisibility(View.GONE);
                } else {
                    clUS.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.check_ru:
                if (item.isChecked()) {
                    clRU.setVisibility(View.GONE);
                } else {
                    clRU.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.check_kr:
                if (item.isChecked()) {
                    clKR.setVisibility(View.GONE);
                } else {
                    clKR.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.check_jp:
                if (item.isChecked()) {
                    clJP.setVisibility(View.GONE);
                } else {
                    clJP.setVisibility(View.VISIBLE);
                }
                break;
        }
        item.setChecked(!item.isChecked());
        return super.onOptionsItemSelected(item);
    }

    private void setTime(boolean is12) {
        SimpleDateFormat sdf = null;
        Date date = new Date();
        if (is12) {
            sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.ENGLISH);
        } else {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        }

        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        tvCN.setText(sdf.format(date));
        sdf.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        tvUS.setText(sdf.format(date));
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
        tvJP.setText(sdf.format(date));
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        tvKR.setText(sdf.format(date));
        sdf.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
        tvRU.setText(sdf.format(date));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_12:
                setTime(true);
                break;
            case R.id.btn_24:
                setTime(false);
                break;
        }
    }
}
