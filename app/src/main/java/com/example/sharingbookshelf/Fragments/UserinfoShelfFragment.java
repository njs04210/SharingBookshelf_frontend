package com.example.sharingbookshelf.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingbookshelf.Adapters.MyBookshelfAdapter;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;
import java.util.Map;

public class UserinfoShelfFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Map<String, Object>> thumbnailSet;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userinfo_shelf, container, false);

        mRecyclerView = view.findViewById(R.id.rcv_ReportRankingShelf);
        recyclerViewSettings();

        return view;
    }

    private void recyclerViewSettings() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getActivity(), 3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        thumbnailSet = new ArrayList<>();
        mAdapter = new MyBookshelfAdapter(thumbnailSet);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void getThumbnail(ArrayList<Map<String, Object>> myDataset) {

        mAdapter = new MyBookshelfAdapter(myDataset);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);

        this.thumbnailSet = myDataset;

    }

}
