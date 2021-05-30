package com.example.sharingbookshelf.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sharingbookshelf.Activities.MainActivity;
import com.example.sharingbookshelf.Adapters.RankingAdapter;
import com.example.sharingbookshelf.HttpRequest.RetrofitClient;
import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.BookData;
import com.example.sharingbookshelf.Models.RankingData;
import com.example.sharingbookshelf.Models.RankingResponse;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookRankingFragment extends Fragment implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private ImageView iv_first, iv_second, iv_third;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private RetrofitServiceApi retrofitServiceApi;
    private ArrayList<RankingData> rankingList;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_ranking, container, false);

        context = container.getContext();

        mRecyclerView = view.findViewById(R.id.rv_ranking);
        iv_first = view.findViewById(R.id.iv_first);
        iv_second = view.findViewById(R.id.iv_second);
        iv_third = view.findViewById(R.id.iv_third);

        iv_first.setOnClickListener(this);
        iv_second.setOnClickListener(this);
        iv_third.setOnClickListener(this);

        recyclerViewSettings();
        loadRankingData();

        return view;
    }

    private void recyclerViewSettings() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RankingAdapter(getActivity(), rankingList);
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
            } else {
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

        mAdapter = new RankingAdapter(getActivity(), dataSet);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);

        this.rankingList = dataSet;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_first:
                FragmentManager fm1 = ((AppCompatActivity) context).getSupportFragmentManager();
                RankingBookInfoPopupFragment dialog1 = new RankingBookInfoPopupFragment();
                dialog1.show(fm1, "abc");
                break;

            case R.id.iv_second:
                FragmentManager fm2 = ((AppCompatActivity) context).getSupportFragmentManager();
                RankingBookInfoPopupFragment dialog2 = new RankingBookInfoPopupFragment();
                dialog2.show(fm2, "abc");
                break;

            case R.id.iv_third:
                FragmentManager fm3 = ((AppCompatActivity) context).getSupportFragmentManager();
                RankingBookInfoPopupFragment dialog3 = new RankingBookInfoPopupFragment();
                dialog3.show(fm3, "abc");
                break;
        }
    }

}
