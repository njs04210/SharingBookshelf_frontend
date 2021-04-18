package com.example.sharingbookshelf.Activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingbookshelf.Models.NeighborShelfInfoData;
import com.example.sharingbookshelf.Models.UserInfoData;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;

public class NeighborShelfAdapter extends RecyclerView.Adapter<NeighborShelfAdapter.NSViewHolder>{

    private ArrayList<NeighborShelfInfoData> NeighborInfoData;

    public NeighborShelfAdapter(ArrayList<NeighborShelfInfoData> myinfoData){
         this.NeighborInfoData = myinfoData;
    }

    public class NSViewHolder extends RecyclerView.ViewHolder{

        private TextView nickname;
        private ImageView profile;

        public NSViewHolder(View view){
            super(view);
            nickname = view.findViewById(R.id.nickname);
            profile = view.findViewById(R.id.profile);
        }
    }

    @NonNull
    @Override //layoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킨다.
    public NSViewHolder onCreateViewHolder(@NonNull ViewGroup viewgroup, int viewType) {
        View view = LayoutInflater.from(viewgroup.getContext())
                .inflate(R.layout.layout_neighbor_shelf, viewgroup, false);
        NSViewHolder NSviewholder = new NSViewHolder(view);
        return NSviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull NSViewHolder holder, int position) {
        holder.profile.setImageResource(NeighborInfoData.get(position).getProfile());
        holder.nickname.setText(NeighborInfoData.get(position).getNickname());
    }

    @Override
    public int getItemCount() {
        return NeighborInfoData.size();
    }
}
