package com.example.myproject.GiaoDien;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.R;

public class ThemTienIch extends AppCompatActivity {
    ImageView imageView;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_tien_ich);
        setCantrol();
        setEvent();
    }

    private void setCantrol() {
        imageView = findViewById(R.id.conbao);
        frameLayout = findViewById(R.id.hoatcanh);

    }

    private void setEvent() {
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationDrawable frameAnimation = (AnimationDrawable) imageView.getDrawable(); // nạp ảnh
                frameAnimation.start();
            }
        });


    }
}