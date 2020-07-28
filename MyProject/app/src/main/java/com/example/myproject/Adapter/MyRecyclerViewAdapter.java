package com.example.myproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.GiaoDien.DetailGV;
import com.example.myproject.GiaoDien.GiaoVien;
import com.example.myproject.R;
import com.example.myproject.ThuVien.SGiaoVien;

import java.util.ArrayList;
import java.util.Locale;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private int layoutID;
    private ArrayList<SGiaoVien> data;
    private ArrayList<SGiaoVien> data_DS;

    //private SGiaoVien sGiaoVien = new SGiaoVien();
    public MyRecyclerViewAdapter(int layoutID, ArrayList<SGiaoVien> data, Context context) {
        this.layoutID = layoutID;
        this.data = data;
        this.context = context;

        this.data_DS = new ArrayList<SGiaoVien>();
        this.data_DS.addAll(data);
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgGV;
        private ImageView imgDelete, imgedit;
        private TextView tvMaGV;
        private TextView tvHoTen;
        private TextView tvSDT;
        private LinearLayout itemIcon;
        private CardView cardView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgGV = (ImageView) itemView.findViewById(R.id.imageGV);
            imgDelete = (ImageView) itemView.findViewById(R.id.deleteImage);
            imgedit = (ImageView) itemView.findViewById(R.id.editImage);
            tvMaGV = (TextView) itemView.findViewById(R.id.tvMaGV);
            tvHoTen = (TextView) itemView.findViewById(R.id.tvHoTenGV);
            tvSDT = (TextView) itemView.findViewById(R.id.tvSDTGV);
            itemIcon = (LinearLayout) itemView.findViewById(R.id.itemlive);
            cardView =(CardView) itemView.findViewById(R.id.card_view);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CardView itemView = (CardView) inflater.inflate(layoutID, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final SGiaoVien sGiaoVien = data.get(position);
        //final Drawable drawable = holder.imageView.getResources().getDrawable(cardViewModel.getImageResourceId());
        //holder.imgGV.setImageDrawable(drawable);
        holder.tvMaGV.setText(sGiaoVien.getsMaGV());
        holder.tvHoTen.setText(sGiaoVien.getsHoTenGV());
        holder.tvSDT.setText(sGiaoVien.getsSDTGV());
        holder.itemIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailGV.class);
                Bundle bundle = new Bundle();
                bundle.putString("magv", sGiaoVien.getsMaGV());
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });
        holder.imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GiaoVien giaoVien = (GiaoVien) context;
                giaoVien.DialogSua();
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GiaoVien giaoVien = (GiaoVien) context;
                giaoVien.DialogXoa(sGiaoVien.getsHoTenGV());
            }
        });

        //hieu ung thu nho
        //Animation animation = AnimationUtils.loadAnimation(context,R.anim.scale_list    );
        //holder.cardView.startAnimation(animation);
        holder.cardView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.animotion_fade));


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //tim kiem theo ten
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        data.clear();
        if (charText.length() == 0) {
            data.addAll(data_DS);
        } else {
            for (SGiaoVien model : data_DS) {
                if (model.getsHoTenGV().toLowerCase(Locale.getDefault()).contains(charText)) {
                    data.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }


}
