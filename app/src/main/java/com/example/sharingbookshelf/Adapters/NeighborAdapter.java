package com.example.sharingbookshelf.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingbookshelf.Models.NeighborInfoData;
import com.example.sharingbookshelf.Models.NeighborShelfData;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;

public class NeighborAdapter extends RecyclerView.Adapter<NeighborAdapter.NeighborViewHolder> {

    Context context;
    ArrayList<NeighborInfoData> arrayList;

    public NeighborAdapter(Context context, ArrayList<NeighborInfoData> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public NeighborAdapter.NeighborViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_neighbor, parent, false);
        NeighborViewHolder holder = new NeighborViewHolder(view);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull NeighborAdapter.NeighborViewHolder holder, int position) {
        NeighborInfoData neighborInfoData = arrayList.get(position);
        holder.iv_profile.setImageResource(arrayList.get(position).getProfile());
        holder.tv_nickname.setText(arrayList.get(position).getNickname());
        ArrayList<NeighborShelfData> singleItem = neighborInfoData.getArrayList();

        NeighborShelfAdapter neighborShelfAdapter = new NeighborShelfAdapter(context, singleItem);

        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));

        holder.recyclerView.setAdapter(neighborShelfAdapter);
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class NeighborViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;
        protected ImageView iv_profile;
        protected TextView tv_nickname;

        public NeighborViewHolder(@NonNull View itemView) {
            super(itemView);
            this.recyclerView = (RecyclerView)itemView.findViewById(R.id.rv_shelf);
            this.iv_profile = (ImageView)itemView.findViewById(R.id.iv_profile);
            this.tv_nickname = (TextView)itemView.findViewById(R.id.tv_nickname);
        }
    }
}
