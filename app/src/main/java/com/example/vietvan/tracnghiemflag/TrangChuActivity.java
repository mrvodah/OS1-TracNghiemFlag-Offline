package com.example.vietvan.tracnghiemflag;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TrangChuActivity extends AppCompatActivity {

    @BindView(R.id.btnBatdau)
    Button btnBatdau;
    @BindView(R.id.btnChonchedo)
    Button btnChonchedo;
    @BindView(R.id.btnDiemcao)
    Button btnDiemcao;
    @BindView(R.id.btnHuongdan)
    Button btnHuongdan;
    @BindView(R.id.btnThoat)
    Button btnThoat;
    @BindView(R.id.constrain)
    ConstraintLayout constrain;
    @BindView(R.id.relative)
    RelativeLayout relative;
    @BindView(R.id.tv_tc_tieude)
    TextView tvTcTieude;

    private static final String TAG = "TAG";
    public static List<FlagResponse> flagResponseList;
    public static int loai1, loai2;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        ButterKnife.bind(this);

        getList();

        Model.loadfont(this, tvTcTieude, "SnackerComic_PerosnalUseOnly.ttf");
        Model.loadfontButton(this, btnBatdau, "UTM Amerika Sans.ttf");
        Model.loadfontButton(this, btnChonchedo, "UTM Amerika Sans.ttf");
        Model.loadfontButton(this, btnDiemcao, "UTM Amerika Sans.ttf");
        Model.loadfontButton(this, btnHuongdan, "UTM Amerika Sans.ttf");
        Model.loadfontButton(this, btnThoat, "UTM Amerika Sans.ttf");

        prepareMusic();

    }

    private void prepareMusic() {
        mediaPlayer = MediaPlayer.create(this, R.raw.start);
    }

    @OnClick({R.id.btnBatdau, R.id.btnChonchedo, R.id.btnDiemcao, R.id.btnHuongdan, R.id.btnThoat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBatdau:
                SharedPreferences sharedPreferences = getSharedPreferences("STORE", MODE_PRIVATE);
                if (sharedPreferences == null)
                    Log.d(TAG, "onViewClicked: ");
                else {
                    loai1 = sharedPreferences.getInt(ChonCheDoActivity.loai1, 1);
                    loai2 = sharedPreferences.getInt(ChonCheDoActivity.loai2, 1);

                    mediaPlayer.start();

                    if (loai1 == 1) {
                        startActivity(new Intent(TrangChuActivity.this, ManHinh1Activity.class));
                    } else {
                        startActivity(new Intent(TrangChuActivity.this, ManHinh2Activity.class));
                    }

                }
                break;
            case R.id.btnChonchedo:
                startActivity(new Intent(TrangChuActivity.this, ChonCheDoActivity.class));
                break;
            case R.id.btnDiemcao:
                startActivity(new Intent(TrangChuActivity.this, DiemCaoActivity.class));
                break;
            case R.id.btnHuongdan:
                startActivity(new Intent(TrangChuActivity.this, HuongDanActivity.class));
                break;
            case R.id.btnThoat:
                View v = LayoutInflater.from(this).inflate(R.layout.thongbao_thoat, null);
                ImageView chapnhan = v.findViewById(R.id.btn_chapnhan);
                ImageView huy = v.findViewById(R.id.btn_huy);
                TextView thongbao = v.findViewById(R.id.tv_thongbao);

                Model.loadfont(this, thongbao, "UTM Amerika Sans.ttf");

                final Dialog dialog = new Dialog(this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(v);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                chapnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

                huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                break;
        }
    }

    private void getList() {
        flagResponseList = new ArrayList<>();

        DatabaseManager db = DatabaseManager.newInstance(this);

        flagResponseList.addAll(db.getListFlag());

    }

    public void load(boolean is) {
        if (is) {
            relative.setVisibility(View.INVISIBLE);
            constrain.setVisibility(View.VISIBLE);
        } else {
            relative.setVisibility(View.VISIBLE);
            constrain.setVisibility(View.INVISIBLE);
        }
    }

}
