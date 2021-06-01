package com.example.sharingbookshelf.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingbookshelf.Activities.MainActivity;
import com.example.sharingbookshelf.Adapters.SelectBookreportAdapter;
import com.example.sharingbookshelf.HttpRequest.RetrofitClient;
import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.SelectBookReportResponse;
import com.example.sharingbookshelf.R;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectBookReportPopupFragment extends DialogFragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private RetrofitServiceApi retrofitServiceApi;
    private ArrayList<SelectBookReportResponse.SelectBookReportData> selectbookList;
    private TextView tv_warning;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_select_book_report_popup, container, false);

        mRecyclerView = v.findViewById(R.id.rv_selectbookreport);
        tv_warning = v.findViewById(R.id.tv_warning);

        recyclerViewSettings();
        getAvailableReportList();

        return v;
    }

    private void recyclerViewSettings() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        selectbookList = new ArrayList<>();
        mAdapter = new SelectBookreportAdapter(SelectBookReportPopupFragment.this, getActivity(), selectbookList);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void getAvailableReportList() {
        retrofitServiceApi = RetrofitClient.createService(RetrofitServiceApi.class, MainActivity.getJWT());
        Call<SelectBookReportResponse> call = retrofitServiceApi.getAllBookReports(true);
        call.enqueue(new Callback<SelectBookReportResponse>() {
            @Override
            public void onResponse(Call<SelectBookReportResponse> call, Response<SelectBookReportResponse> response) {
                if (response.code() == 404) {
                    try {
                        Log.d("아이북쉐어/독후감", response.errorBody().string());
                        tv_warning.setVisibility(View.VISIBLE);
//                        Toast.makeText(getContext(), "작성 가능한 독후감이 없습니다!", Toast.LENGTH_SHORT).show();
                        showAllAvailableReports(null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (response.body().getCode() == 74) {
                    showAllAvailableReports(response.body().getBooks_NoReport());
                }
            }

            @Override
            public void onFailure(Call<SelectBookReportResponse> call, Throwable t) {

            }
        });
    }

    private void showAllAvailableReports(ArrayList<SelectBookReportResponse.SelectBookReportData> dataSet) {
        mAdapter = new SelectBookreportAdapter(SelectBookReportPopupFragment.this, getActivity(), dataSet);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);

        this.selectbookList = dataSet;
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            WindowMetrics windowMetrics = getActivity().getWindowManager().getCurrentWindowMetrics();

            Window window = getDialog().getWindow();
            window.setGravity(Gravity.BOTTOM);

            WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
            params.width = windowMetrics.getBounds().width();
            getDialog().getWindow().setAttributes(params);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}