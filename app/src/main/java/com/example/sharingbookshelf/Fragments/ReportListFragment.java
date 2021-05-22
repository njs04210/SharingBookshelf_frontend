package com.example.sharingbookshelf.Fragments;

import android.graphics.Color;
import android.graphics.Insets;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingbookshelf.Adapters.BookreportsAdapter;
import com.example.sharingbookshelf.Models.BookreportData;
import com.example.sharingbookshelf.R;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReportListFragment extends DialogFragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<BookreportData> reportList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_book_report, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_bookreport);

        mLayoutManager = new LinearLayoutManager(getContext());
        setReport();

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new BookreportsAdapter(reportList);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    private void setReport() {
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 1; i < 11; i++) {
            BookreportData bookreportData = new BookreportData();
            bookreportData.setPainting(R.drawable.icon_book2);
            bookreportData.setBooktitle("책 제목이 길어도 그렇게 얄쌍하게 나오나");
            long now = System.currentTimeMillis();
            Date mDate = new Date(now);
            String getDate = mFormat.format(mDate);
            bookreportData.setDate(getDate);

            reportList.add(bookreportData);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//      dialog fragment custom width
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
            // regardless
            e.printStackTrace();
        }
    }
}