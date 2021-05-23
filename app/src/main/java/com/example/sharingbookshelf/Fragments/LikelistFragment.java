package com.example.sharingbookshelf.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingbookshelf.Adapters.LikelistAdapter;
import com.example.sharingbookshelf.Adapters.OthersBookshelfAdapter;
import com.example.sharingbookshelf.Adapters.RankingAdapter;
import com.example.sharingbookshelf.Models.BookData;
import com.example.sharingbookshelf.Models.BookshelfInfoData;
import com.example.sharingbookshelf.Models.LikelistData;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;

import static com.example.sharingbookshelf.Activities.HomeActivity.getHasShelfcode;

public class LikelistFragment extends DialogFragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<LikelistData> likedataList = new ArrayList<>();

    public static LikelistFragment getInstance() {
        LikelistFragment likelistFragment = new LikelistFragment();
        return likelistFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_likelist, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.rv_likelist);

        mLayoutManager = new LinearLayoutManager(getContext());
        setLikelist();

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getActivity(), 3);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new LikelistAdapter(likedataList);
        mRecyclerView.setAdapter(mAdapter);

        return v;
    }

    private void setLikelist() {
        for (int i = 1; i < 10; i++) {
            LikelistData likelistData = new LikelistData();
            likelistData.setBooknumber(i);
            likelistData.setThumbnail("http://image.kyobobook.co.kr/images/book/xlarge/923/x9791164137923.jpg");

            //책장목록에 책 넣기
            likedataList.add(likelistData);
        }
        //mAdapter.notifyDataSetChanged();
    }
}
