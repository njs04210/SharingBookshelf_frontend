package com.example.sharingbookshelf.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingbookshelf.Adapters.BookshelfAdapter;
import com.example.sharingbookshelf.Models.BookshelfInfoData;
import com.example.sharingbookshelf.Models.BookData;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;

public class OtherBookshelvesActivity extends AppCompatActivity {

    private RecyclerView rv_Bookshelves;
    private LinearLayoutManager linearLayoutManager;
    private BookshelfAdapter bookshelfAdapter;
    private ArrayList<BookshelfInfoData> bookshelfList;
    private ArrayList<BookData> bookList;

    public ArrayList<BookshelfInfoData> getBookshelfList() {
        return bookshelfList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otherbookshelves);

        rv_Bookshelves = (RecyclerView) findViewById(R.id.rv_bookshelves);

        // bookshelfList 는 싱글톤으로
        if (bookshelfList == null) {
            bookshelfList = new ArrayList<>();
        } else {
            bookshelfList = getBookshelfList();
        }

        linearLayoutManager = new LinearLayoutManager(this);
        bookshelfAdapter = new BookshelfAdapter(bookshelfList);

        // RecyclerView Settings
        rv_Bookshelves.setHasFixedSize(true);
        rv_Bookshelves.setLayoutManager(linearLayoutManager);
        rv_Bookshelves.setAdapter(bookshelfAdapter);

        setBookshelf();
    }

    private void setBookshelf() {
        for (int i = 1; i < 10; i++) {
            //회원 책장 객체 생성
            BookshelfInfoData bookshelfInfoData = new BookshelfInfoData();
            bookshelfInfoData.setNickname("회원 " + i);
            bookshelfInfoData.setProfile(R.drawable.ic_profile_default);

            // 책장 속 책 받을 arraylist 객체 생성
            bookList = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                //책정보 세팅
                BookData bookData = new BookData();
                bookData.setIv_book(R.drawable.icon_book2);
                bookList.add(bookData);
            }

            //책장에 책리스트 넣기
            bookshelfInfoData.setBookList(bookList);
            //책장목록에 책 넣기
            bookshelfList.add(bookshelfInfoData);
        }
        bookshelfAdapter.notifyDataSetChanged();
    }
}
