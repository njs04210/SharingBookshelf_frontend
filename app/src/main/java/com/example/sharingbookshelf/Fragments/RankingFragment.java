package com.example.sharingbookshelf.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.sharingbookshelf.Activities.MainActivity;
import com.example.sharingbookshelf.Adapters.MyBookshelfAdapter;
import com.example.sharingbookshelf.Adapters.RankingAdapter;
import com.example.sharingbookshelf.HttpRequest.RetrofitClient;
import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.BookData;
import com.example.sharingbookshelf.Models.RankingData;
import com.example.sharingbookshelf.Models.RankingResponse;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RankingFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ImageView iv_first, iv_second, iv_third;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private RetrofitServiceApi retrofitServiceApi;
    private ArrayList<RankingData> rankingList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_ranking);
        iv_first = view.findViewById(R.id.iv_first);
        iv_second = view.findViewById(R.id.iv_second);
        iv_third = view.findViewById(R.id.iv_third);

        recyclerViewSettings();
        loadRankingData();

        return view;
    }

    private void recyclerViewSettings() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        rankingList = new ArrayList<>();
        mAdapter = new RankingAdapter(rankingList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void loadRankingData() {
        retrofitServiceApi = RetrofitClient.createService(RetrofitServiceApi.class, MainActivity.getJWT());
        Call<RankingResponse> call = retrofitServiceApi.getRanking();
        call.enqueue(new Callback<RankingResponse>() {
            @Override
            public void onResponse(Call<RankingResponse> call, Response<RankingResponse> response) {
                if (response.body() != null) {
                    setRankingView(response.body().getRankingData());
                }
            }

            @Override
            public void onFailure(Call<RankingResponse> call, Throwable t) {

            }
        });

    }

    private void setRankingView(ArrayList<RankingData> dataSet) {

        ImageView selectedView;

        for (int i = 0; i < 3; i++) {
            BookData book = dataSet.get(0).getBook();

            selectedView = null;
            if (i == 0) {
                selectedView = iv_first;
            } else if (i == 1) {
                selectedView = iv_second;
            } else if (i == 2) {
                selectedView = iv_third;
            }

            Glide
                    .with(selectedView.getContext())
                    .load(book.getThumbnail())
                    .fitCenter()
                    .placeholder(R.drawable.icon_book2)
                    .into(selectedView);

            dataSet.remove(0);
        }

        mAdapter = new RankingAdapter(dataSet);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);

        this.rankingList = dataSet;
    }


}