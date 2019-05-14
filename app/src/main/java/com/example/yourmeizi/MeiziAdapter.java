package com.example.yourmeizi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MeiziAdapter extends RecyclerView.Adapter<MeiziAdapter.ViewHolder> {
    private List<Meizi> MeiziList;
    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;

        public ViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.img_view);
        }
    }

    public MeiziAdapter(List<Meizi> meiziList) {
        MeiziList = meiziList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.img_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Meizi meizi = MeiziList.get(position);
        Glide.with(context).load(meizi.getImgId()).into(holder.img);
    }

    public int getItemCount() {
        return MeiziList.size();
    }
}


