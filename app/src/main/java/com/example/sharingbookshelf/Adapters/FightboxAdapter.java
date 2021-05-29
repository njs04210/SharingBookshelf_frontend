package com.example.sharingbookshelf.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sharingbookshelf.Models.fightData;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;

public class FightboxAdapter extends RecyclerView.Adapter<FightboxAdapter.ViewHolder> {
    private ArrayList<fightData> fightlist;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iv_profileImage;
        private final TextView tv_nickname;
        private final TextView tv_fightmail;
        private final TextView tv_time;
        private final TextView tv_aim;

        public ViewHolder(@NonNull View view) {
            super(view);
            iv_profileImage = view.findViewById(R.id.iv_profileImg);
            tv_nickname = view.findViewById(R.id.tv_nickname);
            tv_fightmail = view.findViewById(R.id.tv_mailtext);
            tv_time = view.findViewById(R.id.tv_time);
            tv_aim = view.findViewById(R.id.tv_aim);
        }
    }

    public FightboxAdapter(ArrayList<fightData> dataSet) {
        fightlist = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_fight, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        fightData fightData = fightlist.get(position);
        Glide
                .with(viewHolder.iv_profileImage.getContext())
                .load(fightData.getProfileImage())
                .fitCenter()
                .placeholder(R.drawable.icon_logo)
                .into(viewHolder.iv_profileImage);

        viewHolder.tv_nickname.setText(fightData.getUserName());
        viewHolder.tv_fightmail.setText(fightData.getFightMessage());
        viewHolder.tv_time.setText(fightData.getFightTime());
        viewHolder.tv_aim.setText(fightData.getFightaim());
    }

    @Override
    public int getItemCount() {
        return (null != fightlist ? fightlist.size() : 0);
    }
}
