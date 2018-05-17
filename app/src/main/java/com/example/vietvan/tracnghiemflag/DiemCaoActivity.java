package com.example.vietvan.tracnghiemflag;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.vietvan.tracnghiemflag.Model;
import com.example.vietvan.tracnghiemflag.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DiemCaoActivity extends AppCompatActivity {

    @BindView(R.id.tv_ten2)
    TextView tvTen2;
    @BindView(R.id.tv_diem2)
    TextView tvDiem2;
    @BindView(R.id.btn_dc_luulai)
    Button btnDcLuulai;
    @BindView(R.id.tv_dc_tieude)
    TextView tvDcTieude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diem_cao);
        ButterKnife.bind(this);

        // Lấy thông tin sharepreferences
        SharedPreferences sharedPreferences = getSharedPreferences("SCORE", MODE_PRIVATE);
        String t = sharedPreferences.getString("time", "");
        if(t != "")
            tvTen2.setText(t);
        int p = sharedPreferences.getInt("point", -1);
        if(p != -1 && t != "")
            tvDiem2.setText(p + "");

        // Load font
        Model.loadfont(this, tvDcTieude, "UTM Aurora.ttf");
        Model.loadfont(this, tvDiem2, "UTM Amerika Sans.ttf");

    }

    @OnClick(R.id.btn_dc_luulai)
    public void onViewClicked() {
        onBackPressed();
    }
}
