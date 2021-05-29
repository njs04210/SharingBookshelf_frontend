package com.example.sharingbookshelf.Fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingbookshelf.Activities.MainActivity;
import com.example.sharingbookshelf.Adapters.BookreportsAdapter;
import com.example.sharingbookshelf.HttpRequest.RetrofitClient;
import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.BookReportResponse;
import com.example.sharingbookshelf.Models.BookreportData;
import com.example.sharingbookshelf.R;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookReportBoxFragment extends DialogFragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<BookreportData> reportList;
    private RetrofitServiceApi retrofitServiceApi;

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
        getAllReports();

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

    private void getAllReports() {

        retrofitServiceApi = RetrofitClient.createService(RetrofitServiceApi.class, MainActivity.getJWT());
        Call<BookReportResponse> call = retrofitServiceApi.getAllBookReports();
        call.enqueue(new Callback<BookReportResponse>() {
            @Override
            public void onResponse(Call<BookReportResponse> call, Response<BookReportResponse> response) {
                if (response.code() == 404) {
                    try {
                        Log.d("아이북쉐어/독후감", response.errorBody().string());
                        Toast.makeText(getContext(), "작성된 독후감이 없습니다!", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (response.body().getCode() == 72) {
                    setAllReportsBox(response.body().getBookReports());
                }

            }

            @Override
            public void onFailure(Call<BookReportResponse> call, Throwable t) {
                Log.e(MainActivity.MAIN_TAG, "GetstatusCode 받아오기 실패", t);
            }
        });

    }

    private void setAllReportsBox(ArrayList<BookreportData> dataSet) {
        mAdapter = new BookreportsAdapter(dataSet);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);

        this.reportList = dataSet;
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
            params.height = (int) (windowMetrics.getBounds().height() * 0.9);
            getDialog().getWindow().setAttributes(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
