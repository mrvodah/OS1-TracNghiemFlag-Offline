package com.example.vietvan.tracnghiemflag;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.vietvan.tracnghiemflag.Model;
import com.example.vietvan.tracnghiemflag.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChonCheDoActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    @BindView(R.id.btn_luulai)
    Button btnLuulai;
    @BindView(R.id.rb_l1_hinhanh)
    RadioButton rbL1Hinhanh;
    @BindView(R.id.rb_l1_tenquocgia)
    RadioButton rbL1Tenquocgia;
    @BindView(R.id.rg_loai1)
    RadioGroup rgLoai1;
    @BindView(R.id.rb_l2_de)
    RadioButton rbL2De;
    @BindView(R.id.rb_l2_tb)
    RadioButton rbL2Tb;
    @BindView(R.id.rb_l2_kho)
    RadioButton rbL2Kho;
    @BindView(R.id.rg_loai2)
    RadioGroup rgLoai2;
    @BindView(R.id.tv_ccd_tieude)
    TextView tvCcdTieude;
    @BindView(R.id.tv_ccd_loai1)
    TextView tvCcdLoai1;
    @BindView(R.id.tv_ccd_loai2)
    TextView tvCcdLoai2;

    public static String loai1 = "LOAI1";
    public static String loai2 = "LOAI2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_che_do);
        ButterKnife.bind(this);

        // Lấy thông tin sharepreferences
        SharedPreferences sharedPreferences = getSharedPreferences("STORE", MODE_PRIVATE);
        rgLoai1.check(R.id.rb_l1_hinhanh);
        rgLoai2.check(R.id.rb_l2_de);

        int l1 = sharedPreferences.getInt(loai1, 0);
        int l2 = sharedPreferences.getInt(loai2, 0);

        // Kiểm tra thông tin
        switch (l1){
            case 1:
                rgLoai1.check(R.id.rb_l1_hinhanh);
                break;
            case 2:
                rgLoai1.check(R.id.rb_l1_tenquocgia);
                break;
        }

        // Kiểm tra thông tin
        switch (l2){
            case 1:
                rgLoai2.check(R.id.rb_l2_de);
                break;
            case 2:
                rgLoai2.check(R.id.rb_l2_tb);
                break;
            case 3:
                rgLoai2.check(R.id.rb_l2_kho);
                break;
        }

        Model.loadfont(this, tvCcdTieude, "UTM Aurora.ttf");

    }

    // Lưu thông tin đã chọn
    @OnClick(R.id.btn_luulai)
    public void onViewClicked() {

        SharedPreferences sharedPreferences = getSharedPreferences("STORE", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(rbL1Hinhanh.isChecked())
            editor.putInt(loai1, 1);
        else if(rbL1Tenquocgia.isChecked())
            editor.putInt(loai1, 2);

        if(rbL2De.isChecked())
            editor.putInt(loai2, 1);
        else if(rbL2Tb.isChecked())
            editor.putInt(loai2, 2);
        else if(rbL2Kho.isChecked())
            editor.putInt(loai2, 3);

        editor.commit();

        onBackPressed();

    }

}
