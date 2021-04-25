package com.example.sharingbookshelf.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingbookshelf.Adapters.BookshelfAdapter;
import com.example.sharingbookshelf.Models.NeighborInfoData;
import com.example.sharingbookshelf.Models.NeighborShelfData;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;

public class OtherBookshelvesActivity extends AppCompatActivity {

    private RecyclerView rv_Bookshelves;
    private LinearLayoutManager linearLayoutManager;
    private BookshelfAdapter bookshelfAdapter;
    private ArrayList<NeighborInfoData> neighborList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbor_shelf);

        linearLayoutManager = new LinearLayoutManager(this);
        neighborList = new ArrayList<>();
        bookshelfAdapter = new BookshelfAdapter(this, neighborList);

        /* RecyclerView Settings */
        rv_Bookshelves = (RecyclerView) findViewById(R.id.rv_bookshelves);
        rv_Bookshelves.setHasFixedSize(true);
        rv_Bookshelves.setLayoutManager(linearLayoutManager);
        rv_Bookshelves.setAdapter(bookshelfAdapter);

        setData();
    }

    private void setData(){
        for(int i = 1; i < 10; i++){
            NeighborInfoData neighborInfoData = new NeighborInfoData();
            neighborInfoData.setNickname("회원 " + i);
            neighborInfoData.setProfile(R.drawable.ic_profile_default);

            // 한 회원의 책장 속 책세팅
            ArrayList<NeighborShelfData> bookList = new ArrayList<>();
            for(int j = 0; j < 7; j++){
                NeighborShelfData neighborShelfData = new NeighborShelfData();
                neighborShelfData.setIv_book(R.drawable.icon_book2);
                bookList.add(neighborShelfData);
            }
            neighborInfoData.setBookList(bookList);

            neighborList.add(neighborInfoData);
        }
        bookshelfAdapter.notifyDataSetChanged();
    }
}
