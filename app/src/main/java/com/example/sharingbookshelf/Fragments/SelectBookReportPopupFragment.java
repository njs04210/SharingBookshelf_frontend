package com.example.sharingbookshelf.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingbookshelf.Activities.CreateBookReportActivity;
import com.example.sharingbookshelf.Adapters.SelectBookreportAdapter;
import com.example.sharingbookshelf.Models.SelectReportData;
import com.example.sharingbookshelf.Models.MessageData;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;
import java.util.Objects;

public class SelectBookReportPopupFragment extends DialogFragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<SelectReportData> selectbookList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_select_book_report_popup, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.rv_selectbookreport);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        setTitlelist();

        mAdapter = new SelectBookreportAdapter(selectbookList);
        mRecyclerView.setAdapter(mAdapter);

        Button btn_confirm = v.findViewById(R.id.btn_confirm);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateBookReportActivity.class);
                startActivity(intent);

                Objects.requireNonNull(getDialog()).dismiss();
            }
        });
        return v;
    }

    private void setTitlelist() {
        for (int i = 0; i < 10; i++) {
            SelectReportData selectreportData = new SelectReportData();
            selectreportData.setBooktitle("얼마나 길어도 계속 나올까요오옹");

            selectbookList.add(selectreportData);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        try {
            WindowMetrics windowMetrics = getActivity().getWindowManager().getCurrentWindowMetrics();

            Window window = getDialog().getWindow();
            window.setGravity(Gravity.BOTTOM);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
            params.width = windowMetrics.getBounds().width();
            params.horizontalMargin = 0.0f;
            getDialog().getWindow().setAttributes(params);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}