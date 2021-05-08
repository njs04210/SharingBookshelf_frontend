package com.example.sharingbookshelf.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingbookshelf.Models.BookData;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {

    private ArrayList<BookData> hasBookList;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iv_book;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_book = (ImageView) itemView.findViewById(R.id.iv_book);
        }
    }

    public BooksAdapter(ArrayList<BookData> dataSet) {
        hasBookList = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder booksViewHolder, int position) {
        BookData bookData = hasBookList.get(position);
//        booksViewHolder.iv_book.setImageResource(bookData.getIv_book());
    }

    @Override
    public int getItemCount() {
        return (null != hasBookList ? hasBookList.size() : 0);
    }
}
