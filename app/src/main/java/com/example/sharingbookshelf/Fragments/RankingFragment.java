package com.example.sharingbookshelf.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.sharingbookshelf.Adapters.RankingAdapter;
import com.example.sharingbookshelf.Models.RankingData;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RankingFragment extends Fragment implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<RankingData> rankingList = new ArrayList<>();
    private ImageView iv_1stbook, iv_2ndbook, iv_3rdbook;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking, container, false);

        context = container.getContext();

        iv_1stbook = view.findViewById(R.id.iv_1stbook);
        iv_1stbook.setOnClickListener(this);

        iv_2ndbook = view.findViewById(R.id.iv_2ndbook);
        iv_2ndbook.setOnClickListener(this);

        iv_3rdbook = view.findViewById(R.id.iv_3rdbook);
        iv_3rdbook.setOnClickListener(this);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_ranking);

        mLayoutManager = new LinearLayoutManager(getContext());
        setRanking();

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RankingAdapter(context, rankingList);
        mRecyclerView.setAdapter(mAdapter);


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_1stbook:
                FragmentManager fm1 = ((AppCompatActivity) context).getSupportFragmentManager();
                RankingBookInfoPopupFragment dialog1 = new RankingBookInfoPopupFragment();
                dialog1.show(fm1, "abc");
                break;

            case R.id.iv_2ndbook:
                FragmentManager fm2 = ((AppCompatActivity) context).getSupportFragmentManager();
                RankingBookInfoPopupFragment dialog2 = new RankingBookInfoPopupFragment();
                dialog2.show(fm2, "abc");
                break;

            case R.id.iv_3rdbook:
                FragmentManager fm3 = ((AppCompatActivity) context).getSupportFragmentManager();
                RankingBookInfoPopupFragment dialog3 = new RankingBookInfoPopupFragment();
                dialog3.show(fm3, "abc");
                break;
        }
    }

    private void setRanking() {
        for (int i = 4; i < 11; i++) {
            RankingData rankingData = new RankingData();
            rankingData.setGrade(i);
            rankingData.setBookId(i);
            rankingData.setBooktitle("누가 내 머리에 똥쌌어?");

            rankingList.add(rankingData);
        }
        //mAdapter.notifyDataSetChanged();
    }

}