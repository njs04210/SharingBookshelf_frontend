package com.example.sharingbookshelf.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sharingbookshelf.Adapters.OthersBookshelfAdapter;
import com.example.sharingbookshelf.Models.BookData;
import com.example.sharingbookshelf.Models.BookshelfInfoData;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;

public class OtherBookshelfFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private OthersBookshelfAdapter mAdapter;
    private ArrayList<BookshelfInfoData> bookshelfList = new ArrayList<>();
    private ArrayList<BookData> bookList;

    public ArrayList<BookshelfInfoData> getBookshelfList() {
        return bookshelfList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_otherbookshelf, container, false);

        mRecyclerView = v.findViewById(R.id.rv_bookshelves);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new OthersBookshelfAdapter(bookshelfList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        setBookshelf();

        return v;
    }

    private void setBookshelf() {
        for (int i = 1; i < 10; i++) {
            //회원 책장 객체 생성
            BookshelfInfoData bookshelfInfoData = new BookshelfInfoData();
            bookshelfInfoData.setNickname("회원 " + i);
            bookshelfInfoData.setProfile(R.drawable.icon_logo);

            // 책장 속 책 받을 arraylist 객체 생성
            bookList = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                //책정보 세팅
                BookData bookData = new BookData();
                bookData.setThumbnail("http://image.kyobobook.co.kr/images/book/xlarge/157/x9791158362157.jpg");
                bookList.add(bookData);
            }

            //책장에 책리스트 넣기
            bookshelfInfoData.setBookList(bookList);
            //책장목록에 책 넣기
            bookshelfList.add(bookshelfInfoData);
        }
        mAdapter.notifyDataSetChanged();
    }
}