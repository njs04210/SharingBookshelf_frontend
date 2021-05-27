package com.example.sharingbookshelf.Fragments;

import android.graphics.Color;
import android.graphics.Insets;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingbookshelf.Adapters.BookreportsAdapter;
import com.example.sharingbookshelf.Models.BookreportData;
import com.example.sharingbookshelf.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReportRankingDetailsFragment extends DialogFragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<BookreportData> reportList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    public static ReportRankingDetailsFragment getInstance() {
        ReportRankingDetailsFragment reportrankingdetailsFragment = new ReportRankingDetailsFragment();
        return reportrankingdetailsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report_ranking_details, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_reportdetails);

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
        Date mDate = new Date();
        for (int i = 0; i < 10; i++) {
            BookreportData bookreportData = new BookreportData();
            bookreportData.setCanvas_uri("https://firebasestorage.googleapis.com/v0/b/ibookshare--login.appspot.com/o/BookReportImg%2F40%2F1.jpg?alt=media&token=2366fa8f-d04c-4e48-9386-fdc7bf20f9b9");
            bookreportData.setTitle("책 제목이 길어도 그렇게 얄쌍하게 나오나");
            bookreportData.setCreated(mDate);

            reportList.add(bookreportData);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            WindowMetrics windowMetrics = getActivity().getWindowManager().getCurrentWindowMetrics();
            Insets insets = windowMetrics.getWindowInsets().getInsetsIgnoringVisibility(
                    WindowInsets.Type.systemBars());
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
