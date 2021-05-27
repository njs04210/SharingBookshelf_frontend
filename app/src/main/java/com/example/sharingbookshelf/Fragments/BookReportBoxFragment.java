package com.example.sharingbookshelf.Fragments;

import android.graphics.Color;
import android.graphics.Insets;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowMetrics;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingbookshelf.Adapters.BookreportsAdapter;
import com.example.sharingbookshelf.Models.BookreportData;
import com.example.sharingbookshelf.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BookReportBoxFragment extends DialogFragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<BookreportData> reportList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_report_box, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_bookreport);

        recyclerViewSettings();
        setReport();

        return view;
    }

    private void recyclerViewSettings() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        reportList = new ArrayList<>();
        mAdapter = new BookreportsAdapter(reportList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setReport() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        Date time = new Date();
        String date = dateFormat.format(time);
        for (int i = 0; i < 10; i++) {
            BookreportData bookreportData = new BookreportData();
            bookreportData.setCanvas_uri("https://firebasestorage.googleapis.com/v0/b/ibookshare--login.appspot.com/o/BookReportImg%2F38%2F1.jpg?alt=media&token=6a449e38-2a59-4799-bb1f-4a5716541eb7");
            bookreportData.setTitle("책 제목이 길어도 그렇게 얄쌍하게 나오나");
            bookreportData.setCreated(time);
            reportList.add(bookreportData);
        }

        mAdapter = new BookreportsAdapter(reportList);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            WindowMetrics windowMetrics = getActivity().getWindowManager().getCurrentWindowMetrics();
            Window window = getDialog().getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
            params.width = windowMetrics.getBounds().width();
            getDialog().getWindow().setAttributes(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
