package com.example.sharingbookshelf.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sharingbookshelf.Adapters.RankingAdapter;
import com.example.sharingbookshelf.Models.RankingData;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RankingFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<RankingData> rankingList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_ranking);

        mLayoutManager = new LinearLayoutManager(getContext());
        setRanking();

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RankingAdapter(rankingList);
        mRecyclerView.setAdapter(mAdapter);



        return view;
    }

    private void setRanking() {
        for (int i = 4; i < 11; i++) {
            RankingData rankingData = new RankingData();
            rankingData.setGrade(i);
            rankingData.setBooktitle("누가 내 머리에 똥쌌어?");

            rankingList.add(rankingData);
        }
        //mAdapter.notifyDataSetChanged();
    }
}