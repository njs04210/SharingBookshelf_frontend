package com.example.sharingbookshelf.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sharingbookshelf.Models.BookData;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {

    private ArrayList<BookData> bookDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mimageView;

        public ViewHolder(@NonNull View view) {
            super(view);
            mimageView = (ImageView) view.findViewById(R.id.iv_book);
        }
    }

    public BooksAdapter(ArrayList<BookData> dataSet) {
        bookDataSet = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        BookData bookData = bookDataSet.get(position);
        Glide
                .with(viewHolder.mimageView.getContext())
                .load(bookData.getThumbnail())
                .fitCenter()
                .placeholder(R.drawable.icon_book)
                .into(viewHolder.mimageView);
    }

    @Override
    public int getItemCount() {
        return (null != bookDataSet ? bookDataSet.size() : 0);
    }
}
