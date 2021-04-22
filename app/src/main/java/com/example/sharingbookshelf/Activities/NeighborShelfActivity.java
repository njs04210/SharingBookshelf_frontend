package com.example.sharingbookshelf.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingbookshelf.Adapters.NeighborAdapter;
import com.example.sharingbookshelf.Models.NeighborInfoData;
import com.example.sharingbookshelf.Models.NeighborShelfData;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;

public class NeighborShelfActivity extends AppCompatActivity {

    private RecyclerView NeighborRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private NeighborAdapter neighborAdapter;
    private ArrayList<NeighborInfoData> neighborList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbor_shelf);

        neighborList = new ArrayList<>();

        NeighborRecyclerView = (RecyclerView) findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(this);

        NeighborRecyclerView.setHasFixedSize(true);
        NeighborRecyclerView.setLayoutManager(linearLayoutManager);

        neighborAdapter = new NeighborAdapter(this, neighborList);

        NeighborRecyclerView.setAdapter(neighborAdapter);

        setData();
    }
    private void setData(){
        for(int i = 1; i < 10; i++){
            NeighborInfoData neighborInfoData = new NeighborInfoData();
            neighborInfoData.setNickname("kjs: + i");
            ArrayList<NeighborShelfData> bookerList = new ArrayList<>();

            for(int j = 0; j < 7; j++){
                NeighborShelfData neighborShelfData = new NeighborShelfData();
                bookerList.add(neighborShelfData);
            }
            neighborInfoData.setArrayList(bookerList);

            neighborList.add(neighborInfoData);
        }
        neighborAdapter.notifyDataSetChanged();
    }
}
