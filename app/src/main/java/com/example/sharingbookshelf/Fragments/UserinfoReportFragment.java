package com.example.sharingbookshelf.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingbookshelf.Models.BookreportData;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;

public class UserinfoReportFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<BookreportData> reportList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userinfo_report, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rcv_UserinfoReport);

        //recyclerViewSettings();

        return view;
    }

//    private void recyclerViewSettings() {
//        mRecyclerView.setHasFixedSize(true);
//        mLayoutManager = new LinearLayoutManager(getContext());
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        reportList = new ArrayList<>();
//        mAdapter = new UserReportAdapter(reportList);
//        mRecyclerView.setAdapter(mAdapter);
//    }

}
