package com.example.sharingbookshelf.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sharingbookshelf.Models.ThumbnailData;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;

public class UserShelfAdapter extends RecyclerView.Adapter<UserShelfAdapter.ViewHolder> {

    private ArrayList<ThumbnailData> thumbnailDataList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mimageView;

        public ViewHolder(@NonNull View view) {
            super(view);
            mimageView = view.findViewById(R.id.iv_book);
        }
    }

    @NonNull
    @Override
    public UserShelfAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_book_mybookshelf, viewGroup, false);

        return new UserShelfAdapter.ViewHolder(view);
    }

    public UserShelfAdapter(ArrayList<ThumbnailData> dataSet) {
        thumbnailDataList = dataSet;
    }

    @Override
    public void onBindViewHolder(@NonNull UserShelfAdapter.ViewHolder viewHolder, int position) {
        ThumbnailData thumbnailData = thumbnailDataList.get(position);
        Glide
                .with(viewHolder.mimageView.getContext())
                .load(thumbnailData.getThumbnail())
                .fitCenter()
                .placeholder(R.drawable.icon_logo)
                .into(viewHolder.mimageView);
    }

    @Override
    public int getItemCount() {
        return (null != thumbnailDataList ? thumbnailDataList.size() : 0);
    }
}
