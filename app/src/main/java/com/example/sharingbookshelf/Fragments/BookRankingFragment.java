package com.example.sharingbookshelf.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookRankingFragment extends Fragment implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private ImageView iv_first, iv_second, iv_third;
    private TextView tv_count1, tv_count2, tv_count3;
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
        tv_count1 = view.findViewById(R.id.tv_count1);
        tv_count2 = view.findViewById(R.id.tv_count2);
        tv_count3 = view.findViewById(R.id.tv_count3);

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

        ImageView selectedIv;
        TextView selectedTv;

        for (int i = 0; i < 3; i++) {
            BookData book = dataSet.get(0).getBook();
            List<Object> TagSet = new ArrayList<>(); // book_id, total 저장

            if (i == 0) {
                selectedIv = iv_first;
                selectedTv = tv_count1;
            } else if (i == 1) {
                selectedIv = iv_second;
                selectedTv = tv_count2;
            } else {
                selectedIv = iv_third;
                selectedTv = tv_count3;
            }

            Glide
                    .with(selectedIv.getContext())
                    .load(book.getThumbnail())
                    .fitCenter()
                    .placeholder(R.drawable.icon_book2)
                    .into(selectedIv);
            selectedTv.setText(dataSet.get(0).getTotal() + "권");

            TagSet.add(book.getBook_id());
            TagSet.add(dataSet.get(0).getTotal());
            selectedIv.setTag(TagSet);

            dataSet.remove(0);
        }

        mAdapter = new RankingAdapter(getActivity(), dataSet);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);

        this.rankingList = dataSet;
    }

    @Override
    public void onClick(View v) {

        ArrayList<Object> tag = (ArrayList<Object>) v.getTag();

        Bundle bundle = new Bundle();
        bundle.putInt("book_id", (int) tag.get(0));
        bundle.putInt("total", (int) tag.get(1));

        FragmentManager fm = ((AppCompatActivity) context).getSupportFragmentManager();
        RankingBookInfoPopupFragment rankingBookInfoPopupFragment = new RankingBookInfoPopupFragment();
        rankingBookInfoPopupFragment.setArguments(bundle);
        rankingBookInfoPopupFragment.show(fm, "RankinBookInfoPopupFragment");

    }

}
