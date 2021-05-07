package com.example.sharingbookshelf.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.sharingbookshelf.Fragments.MyBookshelfFragment;
import com.example.sharingbookshelf.Fragments.NoEmptyShelfFragment;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;

public class MyBookshelfAdapter extends RecyclerView.Adapter<MyBookshelfAdapter.ViewHolder> {

    private ArrayList<String> localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mimageView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            mimageView = view.findViewById(R.id.iv_book);
        }

    }

    public MyBookshelfAdapter(ArrayList<String> dataSet) {
        localDataSet = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_book_mybookshelf, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        Glide
                .with(viewHolder.mimageView.getContext())
                .load(localDataSet.get(position))
                .fitCenter()
                .placeholder(R.drawable.icon_book)
                .into(viewHolder.mimageView);
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
