package com.example.sharingbookshelf.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingbookshelf.Adapters.UserReportAdapter;
import com.example.sharingbookshelf.Models.BookreportData;
import com.example.sharingbookshelf.Models.MessageData;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;

public class UserinfoReportFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<BookreportData> reportList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userinfo_report, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rcv_UserinfoReport);

        recyclerViewSettings();
        setReports();

        return view;
    }

    private void recyclerViewSettings() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        reportList = new ArrayList<>();
        mAdapter = new UserReportAdapter(reportList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setReports() {
        for (int i = 0; i < 8; i++) {
            BookreportData userReportData = new BookreportData();

            userReportData.setProfileImage(R.drawable.icon_logo);
            userReportData.setUserName("진주맘");
            userReportData.setLastMessage("독후감이 좋네요^^");
            userReportData.setMessageTime("날짜,시간");

            reportList.add(userReportData);
        }
    }

}
