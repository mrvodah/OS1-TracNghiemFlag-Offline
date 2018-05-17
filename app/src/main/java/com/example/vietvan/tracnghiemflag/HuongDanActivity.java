package com.example.vietvan.tracnghiemflag;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HuongDanActivity extends AppCompatActivity {

    @BindView(R.id.tv_hd_tieude)
    TextView tvHdTieude;
    @BindView(R.id.tv_hd_11)
    TextView tvHd11;
    @BindView(R.id.tv_hd_12)
    TextView tvHd12;
    @BindView(R.id.tv_hd_1)
    TextView tvHd1;
    @BindView(R.id.tv_hd_2)
    TextView tvHd2;
    @BindView(R.id.tv_hd_21)
    TextView tvHd21;
    @BindView(R.id.tv_hd_22)
    TextView tvHd22;
    @BindView(R.id.tv_hd_23)
    TextView tvHd23;
    @BindView(R.id.btn_hd_tl)
    Button btnHdTl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huong_dan);
        ButterKnife.bind(this);

        Model.loadfont(this, tvHdTieude, "UTM Aurora.ttf");
        Model.loadfont(this, tvHd2, "UTM Avo.ttf");
        Model.loadfont(this, tvHd1, "UTM Avo.ttf");

    }

    @OnClick(R.id.btn_hd_tl)
    public void onViewClicked() {
        onBackPressed();
    }
}
