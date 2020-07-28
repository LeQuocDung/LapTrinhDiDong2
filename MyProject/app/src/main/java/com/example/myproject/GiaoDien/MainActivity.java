package com.example.myproject.GiaoDien;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.myproject.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    static final float END_SCALE = 0.2f;
    //Drawer menu
    DrawerLayout drawer;
    NavigationView navigationView;
    ImageView iconmenu, tienich;
    RelativeLayout timkiem;
    LinearLayout contentView;
    LinearLayout btnGVien, btnMhoc, btnTTCBai, btnPCBai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
        navigationDrawer();
    }

    private void navigationDrawer() {
        //Navigation drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        iconmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerVisible(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        //animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {
        drawer.setScrimColor(getResources().getColor(R.color.colorPrimary));
        drawer.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 4;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);

            }

        });
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerVisible(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                onBackPressed();
                return true;
            //startActivity(new Intent(this, MainActivity.class));
            //break;
            case R.id.nav_giaovien:
                startActivity(new Intent(getApplicationContext(), GiaoVien.class));
                break;
            case R.id.nav_thoat:
                AlertDialog.Builder thoat = new AlertDialog.Builder(MainActivity.this);
                thoat.setTitle("Thông báo!");
                thoat.setMessage("Bán có chắc muốn thoát ứng dụng?");
                thoat.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                thoat.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                thoat.show();
            case R.id.nav_mh:
                startActivity(new Intent(getApplicationContext(), MonHoc.class));
                break;
            case R.id.nav_pcb:
                startActivity(new Intent(getApplicationContext(), PhieuChamBai.class));
                break;
            case R.id.nav_ttcb:
                startActivity(new Intent(getApplicationContext(), ThongTinChamBai.class));
                break;
            case R.id.nav_login:
                startActivity(new Intent(getApplicationContext(), DangNhap.class));
                break;
        }
        return true;
    }

    private void setEvent() {
        //mo activity giao vien
        btnGVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), GiaoVien.class));
            }
        });
        btnMhoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MonHoc.class));
            }
        });
        btnTTCBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ThongTinChamBai.class));
            }
        });
        btnPCBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PhieuChamBai.class));
            }
        });
        timkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TimKiem.class));
            }
        });
        tienich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ThemTienIch.class));
            }
        });

    }

    private void setControl() {
        //menu hooks
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_menu);
        iconmenu = findViewById(R.id.menuicon);
        contentView = findViewById(R.id.content);
        btnGVien = findViewById(R.id.btnGiaoVien);
        btnMhoc = findViewById(R.id.btnMonHoc);
        btnTTCBai = findViewById(R.id.btnTTCB);
        btnPCBai = findViewById(R.id.btnPCB);
        timkiem = findViewById(R.id.timkiem);
        tienich = findViewById(R.id.tienich);


    }


}