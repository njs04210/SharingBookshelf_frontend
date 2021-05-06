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

public class RankingFragment extends Fragment {

    private RecyclerView rv_ranking;
    private LinearLayoutManager linearLayoutManager;
    private RankingAdapter rankingAdapter;
    private ArrayList<RankingData> rankingList;

    public ArrayList<RankingData> getRankingList() { return rankingList; }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking, container, false);

        rv_ranking = (RecyclerView) view.findViewById(R.id.rv_ranking);

        if (rankingList == null) {
            rankingList = new ArrayList<>();
        } else {
            rankingList = getRankingList();
        }

        linearLayoutManager = new LinearLayoutManager(getContext());
        rankingAdapter = new RankingAdapter(rankingList);

        rv_ranking.setHasFixedSize(true);
        rv_ranking.setLayoutManager(linearLayoutManager);
        rv_ranking.setAdapter(rankingAdapter);

        setRanking();

        return view;
    }

    private void setRanking() {
        for (int i = 3; i < 11; i++) {
            RankingData rankingData = new RankingData();
            rankingData.setGrade(i);
            rankingData.setBooktitle("누가 내 머리에 똥쌌어? " + i);

            rankingList.add(rankingData);
        }
        rankingAdapter.notifyDataSetChanged();
    }
}