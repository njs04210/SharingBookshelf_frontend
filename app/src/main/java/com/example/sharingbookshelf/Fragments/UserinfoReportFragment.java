package com.example.sharingbookshelf.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
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

public class UserinfoReportFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<BookreportData> reportList;
    private RetrofitServiceApi retrofitServiceApi;
    private int mem_id;
    private TextView tv_warning;

    public UserinfoReportFragment(int mem_id) {
        this.mem_id = mem_id;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userinfo_report, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rcv_UserinfoReport);
        tv_warning = view.findViewById(R.id.tv_warning);

        recyclerViewSettings();
        getAllOtherReports();

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

    private void getAllOtherReports() {

        retrofitServiceApi = RetrofitClient.createService(RetrofitServiceApi.class, MainActivity.getJWT());
        Call<BookReportResponse> call = retrofitServiceApi.getAllOtherBookReports(mem_id);
        call.enqueue(new Callback<BookReportResponse>() {
            @Override
            public void onResponse(Call<BookReportResponse> call, Response<BookReportResponse> response) {
                if (response.code() == 404) {
                    try {
                        Log.d("아이북쉐어/독후감", response.errorBody().string());
//                        Toast.makeText(getContext(), "작성된 독후감이 없습니다!", Toast.LENGTH_SHORT).show();
                        tv_warning.setVisibility(View.VISIBLE);
                        setAllReportsBox(null);

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

}
