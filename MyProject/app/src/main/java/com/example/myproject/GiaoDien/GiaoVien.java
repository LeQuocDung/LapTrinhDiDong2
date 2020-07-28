package com.example.myproject.GiaoDien;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.Adapter.MyRecyclerViewAdapter;
import com.example.myproject.DataBase.DBChamBai;
import com.example.myproject.ThuVien.SGiaoVien;
import com.example.myproject.R;

import java.util.ArrayList;

public class GiaoVien extends AppCompatActivity {
    SGiaoVien sGiaoVien = new SGiaoVien();
    ImageView imBack;
    ImageButton btnWindowAdd;
    EditText txtMaGV2, txtHoTenGV2, txtSDTGV2;
    RecyclerView recDanhSachGV;
    MyRecyclerViewAdapter apdapter;
    SearchView svSearchTC;
    ArrayList<SGiaoVien> dataGV = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_vien);
        getControl();
        getEvent();
        registerForContextMenu(recDanhSachGV);
        CapnhapDL();
    }

    //xu ly cac su kien
    private void getEvent() {
        imBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GiaoVien.super.onBackPressed();
            }
        });
        //su kien hien thi cua so them gv
        btnWindowAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogThem();
            }
        });

        //su kien tim kiem giao vien
        svSearchTC.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                apdapter.filter(newText);
                return true;
            }
        });

    }

    //anh xa
    private void getControl() {
        imBack = findViewById(R.id.btnBack);
        btnWindowAdd = findViewById(R.id.btnWThemgv);
        //getBtnWindowEdit = findViewById(R.id.btnWSuagv);
        //btnXoa = findViewById(R.id.btnWXoagv);
        recDanhSachGV = findViewById(R.id.gvDanhSachGV);
        svSearchTC = findViewById(R.id.svSearchGV);

    }

    //dialog them
    private void DialogThem() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.them_gv);

        txtMaGV2 = dialog.findViewById(R.id.txtMaGV2);
        txtHoTenGV2 = dialog.findViewById(R.id.txtHoTenGV2);
        txtSDTGV2 = dialog.findViewById(R.id.txtSDTGV2);

        Button btnHuy2 = dialog.findViewById(R.id.btnHuy2);
        Button btnThem2 = dialog.findViewById(R.id.btnThem2);

        Button btnClear1 = dialog.findViewById(R.id.btnClear2);


        btnThem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String magv = txtMaGV2.getText().toString();
                String tengv = txtHoTenGV2.getText().toString();
                String sdtgv = txtSDTGV2.getText().toString();
                if (magv.equals("") || tengv.equals(" ") || sdtgv.equals("")) {
                    Toast.makeText(GiaoVien.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                } else {
                    SGiaoVien sGiaoVien = getGiaoVien();
                    DBChamBai dbChamBai = new DBChamBai(getApplicationContext());
                    dbChamBai.Them(sGiaoVien);
                    Toast.makeText(GiaoVien.this, "Đã thêm.", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    CapnhapDL();
                }
            }
        });

        //su kien clear
        btnClear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtMaGV2.setText("");
                txtHoTenGV2.setText("");
                txtSDTGV2.setText("");
            }
        });

        //su kien huy
        btnHuy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //dialog sua
    public void DialogSua() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.sua_gv);


        Button btnSHuy = dialog.findViewById(R.id.btnSHuy);
        Button btnSClear = dialog.findViewById(R.id.btnSClear);

        txtMaGV2 = dialog.findViewById(R.id.txtMaGV2);
        txtHoTenGV2 = dialog.findViewById(R.id.txtHoTenGV2);
        txtSDTGV2 = dialog.findViewById(R.id.txtSDTGV2);

        Button btnSSua = dialog.findViewById(R.id.tbnSSua);

        //String magv = getIntent().getExtras().getString("magv");
        //DBChamBai dbChamBai  =new DBChamBai(this);
        // dataGV = dbChamBai.LayDL(magv);
        txtMaGV2.setText(dataGV.get(0).getsMaGV());
        txtHoTenGV2.setText(dataGV.get(0).getsHoTenGV());
        txtSDTGV2.setText(dataGV.get(0).getsSDTGV());

        btnSClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtMaGV2.setText("");
                txtHoTenGV2.setText("");
                txtSDTGV2.setText("");
            }
        });


        btnSSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String magv = txtMaGV2.getText().toString();
                String tengv = txtHoTenGV2.getText().toString();
                String sdtgv = txtSDTGV2.getText().toString();
                if (magv.equals("") || tengv.equals(" ") || sdtgv.equals("")) {
                    Toast.makeText(GiaoVien.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                } else {
                    SGiaoVien sGiaoVien = getGiaoVien();
                    DBChamBai dbChamBai = new DBChamBai(getApplicationContext());
                    dbChamBai.Sua(sGiaoVien);
                    Toast.makeText(GiaoVien.this, "Sửa thành công!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    CapnhapDL();
                }
            }
        });


        btnSHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    //dialog xoa
    public void DialogXoa(String tengv1) {
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Bán có chắc muốn xóa giáo viên " + tengv1 + " không?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                SGiaoVien sGiaoVien = getGiaoVien();
                DBChamBai dbChamBai = new DBChamBai(getApplicationContext());
                dbChamBai.Xoa(sGiaoVien);
                CapnhapDL();
            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        dialogXoa.show();
    }


    private SGiaoVien getGiaoVien() {
        SGiaoVien sGiaoVien = new SGiaoVien();
        sGiaoVien.setsMaGV(txtMaGV2.getText().toString());
        sGiaoVien.setsHoTenGV(txtHoTenGV2.getText().toString());
        sGiaoVien.setsSDTGV(txtSDTGV2.getText().toString());
        return sGiaoVien;
    }

    ///
    public void CapnhapDL() {
        try {
            DBChamBai db = new DBChamBai(this);
            dataGV = db.LayDL();
            recDanhSachGV.setHasFixedSize(true);
            recDanhSachGV.setLayoutManager(new GridLayoutManager(this, 1));
            apdapter = new MyRecyclerViewAdapter(R.layout.cardview_item, dataGV, this);
            recDanhSachGV.setAdapter(apdapter);
        } catch (Exception ex) {
            recDanhSachGV.setVisibility(View.GONE);
            Toast.makeText(this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
        }

    }
}