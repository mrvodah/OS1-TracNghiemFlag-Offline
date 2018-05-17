package com.example.vietvan.tracnghiemflag;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ManHinh2Activity extends AppCompatActivity {

    private static final String TAG = "TAG";
    @BindView(R.id.ivAnh1)
    ImageView ivAnh1;
    @BindView(R.id.ivAnh2)
    ImageView ivAnh2;
    @BindView(R.id.ivAnh3)
    ImageView ivAnh3;
    @BindView(R.id.ivAnh4)
    ImageView ivAnh4;
    @BindView(R.id.tvTenquocgia)
    TextView tvTenquocgia;
    @BindView(R.id.tvDiem2)
    TextView tvDiem2;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv_socau2)
    TextView tvSocau2;
    @BindView(R.id.iv_back2)
    ImageView ivBack2;

    List<FlagResponse> list;
    List<Bitmap> flagResponseList;
    int position = 1, point = 0;
    int max = 10;
    FlagResponse flag;
    public boolean isClick = false;
    MediaPlayer mediaPlayerRight, mediaPlayerWrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh2);
        ButterKnife.bind(this);

        if (TrangChuActivity.loai2 == 1) {
            list = Model.getListLevel1(TrangChuActivity.flagResponseList);
        } else if (TrangChuActivity.loai2 == 2) {
            list = Model.getListLevel2(TrangChuActivity.flagResponseList);
        } else if (TrangChuActivity.loai2 == 3) {
            list = Model.getListLevel3(TrangChuActivity.flagResponseList);
        }

        Collections.shuffle(list);
        Log.d(TAG, "onCreate: " + list.size() + "/");

        loadData(position);
        point = 0;
        position = 1;

        Model.loadfont(this, tvTenquocgia, "UTM Aurora.ttf");

        prepareMusic();

    }

    public void loadData(int pos) {
        if (pos > max) {
            loadThongBao();
            tvDiem2.setText(point + "");
        } else {
            flag = list.get(pos - 1);
            flagResponseList = Model.getAnswerImage(list, pos - 1);
            Collections.shuffle(flagResponseList);
            Log.d(TAG, "loadData: " + flagResponseList);

            ivAnh1.setImageBitmap(flagResponseList.get(0));
            ivAnh2.setImageBitmap(flagResponseList.get(1));
            ivAnh3.setImageBitmap(flagResponseList.get(2));
            ivAnh4.setImageBitmap(flagResponseList.get(3));

            tvTenquocgia.setText(flag.getName());
            tvSocau2.setText(pos + "/" + max);
            tvDiem2.setText(point + "");
        }
        isClick = false;

    }

    public void loadThongBao() {
        View v = LayoutInflater.from(this).inflate(R.layout.thongbao, null);
        TextView diem = v.findViewById(R.id.tv_tb_diem);
        TextView sodiem = v.findViewById(R.id.tv_tb_sodiem);
        TextView kethuc = v.findViewById(R.id.tv_ketthuc);
        ImageView menu = v.findViewById(R.id.iv_menu);
        ImageView reload = v.findViewById(R.id.iv_reload);
        ImageView next = v.findViewById(R.id.iv_next);

        Model.loadfont(this, kethuc, "UTM Avo.ttf");
        Model.loadfont(this, diem, "UTM Aurora.ttf");
        Model.loadfont(this, sodiem, "UTM Aurora.ttf");


        diem.setText(point + "");

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(v);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(ManHinh2Activity.this, TrangChuActivity.class));
            }
        });

        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Collections.shuffle(list);

                point = 0;
                position = 1;

                loadData(position);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM.dd.yyyy - HH:mm");
                Date date = new Date();

                SharedPreferences sharedPreferences = getSharedPreferences("SCORE", MODE_PRIVATE);
                int p = sharedPreferences.getInt("point", -1);

                if(p != -1){

                    if(point >= p){

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("point", point);
                        editor.putString("time", simpleDateFormat.format(date));

                        editor.commit();

                    }

                }
                else{

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("point", point);
                    editor.putString("time", simpleDateFormat.format(date));

                    editor.commit();

                }

                finish();

            }
        });


    }

    public void prepareMusic(){

        mediaPlayerRight = MediaPlayer.create(this, R.raw.right);

        mediaPlayerWrong = MediaPlayer.create(this, R.raw.wrong);

    }

    public void loadMusic(boolean is){

        if(is){

            mediaPlayerRight.start();
            mediaPlayerRight.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    loadData(position);
                }
            });

        }
        else{

            mediaPlayerWrong.start();
            mediaPlayerWrong.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    loadData(position);
                }
            });

        }

    }

    @OnClick({R.id.ivAnh1, R.id.ivAnh2, R.id.ivAnh3, R.id.ivAnh4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivAnh1:
                xuly(0);
                break;
            case R.id.ivAnh2:
                xuly(1);
                break;
            case R.id.ivAnh3:
                xuly(2);
                break;
            case R.id.ivAnh4:
                xuly(3);
                break;
        }
    }

    public void xuly(int pos){
        if(!isClick){

            isClick = true;
            ++position;
            if (flag.getFlag().equals(flagResponseList.get(pos))) {
                loadDiem();
                loadMusic(true);
            }
            else{
                loadMusic(false);
            }

        }
    }

    public void loadDiem(){
        switch (TrangChuActivity.loai2){
            case 1:
                point += 10;
                break;
            case 2:
                point += 20;
                break;
            case 3:
                point += 30;
                break;
        }
    }

    @OnClick(R.id.iv_back2)
    public void onViewClicked() {
        onBackPressed();
    }
}
