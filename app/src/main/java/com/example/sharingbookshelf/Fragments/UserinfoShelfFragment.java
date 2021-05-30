package com.example.sharingbookshelf.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingbookshelf.Adapters.BookreportsAdapter;
import com.example.sharingbookshelf.Adapters.BooksAdapter;
import com.example.sharingbookshelf.Adapters.MyBookshelfAdapter;
import com.example.sharingbookshelf.Adapters.UserShelfAdapter;
import com.example.sharingbookshelf.Models.BookData;
import com.example.sharingbookshelf.Models.BookreportData;
import com.example.sharingbookshelf.Models.ThumbnailData;
import com.example.sharingbookshelf.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class UserinfoShelfFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<ThumbnailData> thumbnailList;
    private ImageButton iv_categorySelect;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userinfo_shelf, container, false);

        iv_categorySelect = view.findViewById(R.id.iv_selectCategory);
        iv_categorySelect.setOnClickListener(new View.OnClickListener() { //필터 버튼 액티비티
            public void onClick(View v) {
                FilterCategoryFragment filterCategoryFragment = new FilterCategoryFragment(UserinfoShelfFragment.this);
                filterCategoryFragment.show(getFragmentManager(), "FilterCategoryFragment");

            }
        });

        mRecyclerView = view.findViewById(R.id.rcv_UserinfoShelf);

        recyclerViewSettings();
        setThumbnail();
        return view;
    }

    private void recyclerViewSettings() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getActivity(), 3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        thumbnailList = new ArrayList<>();
        mAdapter = new UserShelfAdapter(thumbnailList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setThumbnail() {
        for (int i = 0; i < 10; i++) {
            ThumbnailData thumbnailData = new ThumbnailData();
            thumbnailData.setThumbnail("http://image.kyobobook.co.kr/images/book/xlarge/923/x9791164137923.jpg");
            thumbnailList.add(thumbnailData);
        }
        mAdapter = new UserShelfAdapter(thumbnailList);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);
    }

}
