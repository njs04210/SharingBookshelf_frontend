package com.example.sharingbookshelf.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sharingbookshelf.Models.BookData;
import com.example.sharingbookshelf.Models.LikelistData;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;

public class LikelistAdapter extends RecyclerView.Adapter<LikelistAdapter.ViewHolder> {

    private ArrayList<LikelistData> likelist;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mimageView;

        public ViewHolder(@NonNull View view) {
            super(view);
            mimageView = (ImageView) view.findViewById(R.id.iv_book);
        }
    }

    public LikelistAdapter(ArrayList<LikelistData> dataset) {
        likelist = dataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_book_mybookshelf, viewGroup, false);

        context = viewGroup.getContext();

        return new LikelistAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        Glide
                .with(viewHolder.mimageView.getContext())
                .load(likelist.get(position).getThumbnail())
                .fitCenter()
                .placeholder(R.drawable.icon_book2)
                .into(viewHolder.mimageView);

    }

    @Override
    public int getItemCount() {
        return (null != likelist ? likelist.size() : 0);
    }
}
