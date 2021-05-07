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

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BooksViewHolder> {

    private ArrayList<BookData> hasBookList;

    public BooksAdapter(ArrayList<BookData> hasBookList) {
        this.hasBookList = hasBookList;
    }

    public static class BooksViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iv_book;

        public BooksViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_book = (ImageView) itemView.findViewById(R.id.iv_book);
        }
    }

    @NonNull
    @Override
    public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false);

        return new BooksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksViewHolder booksViewHolder, int position) {
        BookData bookData = hasBookList.get(position);
//        booksViewHolder.iv_book.setImageResource(bookData.getIv_book());
    }

    @Override
    public int getItemCount() {
        return (null != hasBookList ? hasBookList.size() : 0);
    }
}
