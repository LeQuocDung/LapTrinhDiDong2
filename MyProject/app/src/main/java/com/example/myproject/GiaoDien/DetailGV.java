package com.example.myproject.GiaoDien;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.DataBase.DBChamBai;
import com.example.myproject.ThuVien.SGiaoVien;
import com.example.myproject.R;

import java.util.ArrayList;

public class DetailGV extends AppCompatActivity{


    ImageView imageGVDetail, imBack;
    TextView maGVDetail, hoTenGVDetail, SDTDetail;
    ArrayList<SGiaoVien> dataGV = new ArrayList<>();

    public static final String EXTRA_MOUNTAIN = "extra_mountain";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_g_v);
        setControl();
        setEvent();
    }

    private void setEvent() {
        String magv = getIntent().getExtras().getString("magv");

        DBChamBai dbChamBai  =new DBChamBai(this);
        dataGV = dbChamBai.LayDL(magv);
        maGVDetail.setText(dataGV.get(0).getsMaGV());
        hoTenGVDetail.setText(dataGV.get(0).getsHoTenGV());
        SDTDetail.setText(dataGV.get(0).getsSDTGV());

        imBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailGV.super.onBackPressed();
            }
        });
    }

    private void setControl() {
        imageGVDetail = findViewById(R.id.imageGVDetail);
        maGVDetail = findViewById(R.id.tvMaGVDetail);
        hoTenGVDetail = findViewById(R.id.tvHoTenGVDetail);
        SDTDetail = findViewById(R.id.tvSDTGVDetail);
        imBack = findViewById(R.id.btnBack);
    }
}