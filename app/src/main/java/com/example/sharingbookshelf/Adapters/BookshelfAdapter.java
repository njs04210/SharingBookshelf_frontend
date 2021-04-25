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

import com.example.sharingbookshelf.Models.BookshelfInfoData;
import com.example.sharingbookshelf.Models.BookData;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;

public class BookshelfAdapter extends RecyclerView.Adapter<BookshelfAdapter.BookshelfViewHolder> {

    private ArrayList<BookshelfInfoData> bookshelfList;
    private Context context;

    public BookshelfAdapter(ArrayList<BookshelfInfoData> bookshlefList) {
        this.bookshelfList = bookshlefList;
    }

    public static class BookshelfViewHolder extends RecyclerView.ViewHolder {
        private final RecyclerView rv_bookshelf;
        private final ImageView iv_profileImg;
        private final TextView tv_nickname;

        public BookshelfViewHolder(@NonNull View itemView) {
            super(itemView); // Define click listener for the ViewHolder's View
            this.rv_bookshelf = (RecyclerView) itemView.findViewById(R.id.rv_bookshelf);
            this.iv_profileImg = (ImageView) itemView.findViewById(R.id.iv_profileImg);
            this.tv_nickname = (TextView) itemView.findViewById(R.id.tv_nickname);
        }
    }

    @NonNull
    @Override
    public BookshelfViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bookshelf, parent, false);

        return new BookshelfViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookshelfViewHolder bookshelfViewHolder, final int position) {
        BookshelfInfoData bookshelfInfoData = bookshelfList.get(position);
        bookshelfViewHolder.iv_profileImg.setImageResource(bookshelfInfoData.getProfile());
        bookshelfViewHolder.tv_nickname.setText(bookshelfInfoData.getNickname());

        ArrayList<BookData> bookList = bookshelfInfoData.getBookList();
        BooksAdapter booksAdapter = new BooksAdapter(bookList);

        bookshelfViewHolder.rv_bookshelf.setAdapter(booksAdapter);
        bookshelfViewHolder.rv_bookshelf.setHasFixedSize(true);
        bookshelfViewHolder.rv_bookshelf.setLayoutManager(new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false));

    }

    @Override
    public int getItemCount() {
        return (null != bookshelfList ? bookshelfList.size() : 0);
    }
}
