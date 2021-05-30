package com.example.sharingbookshelf.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingbookshelf.Activities.MainActivity;
import com.example.sharingbookshelf.Adapters.BookreportsAdapter;
import com.example.sharingbookshelf.Adapters.BooksAdapter;
import com.example.sharingbookshelf.Adapters.MyBookshelfAdapter;
import com.example.sharingbookshelf.Adapters.UserShelfAdapter;
import com.example.sharingbookshelf.HttpRequest.RetrofitClient;
import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.BookData;
import com.example.sharingbookshelf.Models.BookreportData;
import com.example.sharingbookshelf.Models.GetShelfStatusResponse;
import com.example.sharingbookshelf.Models.ThumbnailData;
import com.example.sharingbookshelf.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserinfoShelfFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RetrofitServiceApi retrofitServiceApi;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<ThumbnailData> thumbnailList;
    private ImageButton iv_categorySelect;
    private int mem_id;
    private ArrayList<BookData> thumbnailSet;

    public UserinfoShelfFragment(int mem_id) {
        this.mem_id = mem_id;
    }

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
                FilterCategoryFragment filterCategoryFragment = new FilterCategoryFragment
                        (UserinfoShelfFragment.this, mem_id);
                filterCategoryFragment.show(getFragmentManager(), "FilterCategoryFragment");

            }
        });

        mRecyclerView = view.findViewById(R.id.rcv_UserinfoShelf);

        recyclerViewSettings();

        setShelfView(mem_id); // 책장 채워넣기
        return view;
    }

    public void setShelfView(int memId) {
        retrofitServiceApi = RetrofitClient.createService(RetrofitServiceApi.class, MainActivity.getJWT());
        Call<GetShelfStatusResponse> call = retrofitServiceApi.getShelfStatus(memId);
        call.enqueue(new Callback<GetShelfStatusResponse>() {
            @Override
            public void onResponse(Call<GetShelfStatusResponse> call, Response<GetShelfStatusResponse> response) {

                String msg = response.body().getMsg();
                Log.d(MainActivity.MAIN_TAG, msg);
                getThumbnail(response.body().getHasBooks());

            }

            @Override
            public void onFailure(Call<GetShelfStatusResponse> call, Throwable t) {
            }
        });
    }

    public void setShelfViewCategory(int memId, int categoryNum) {
        retrofitServiceApi = RetrofitClient.createService(RetrofitServiceApi.class, MainActivity.getJWT());
        Call<GetShelfStatusResponse> call = retrofitServiceApi.getShelfStatusCategory(memId, categoryNum);
        call.enqueue(new Callback<GetShelfStatusResponse>() {
            @Override
            public void onResponse(Call<GetShelfStatusResponse> call, Response<GetShelfStatusResponse> response) {

                String msg = response.body().getMsg();
                Log.d(MainActivity.MAIN_TAG, msg);
                getThumbnail(response.body().getHasBooks());

            }

            @Override
            public void onFailure(Call<GetShelfStatusResponse> call, Throwable t) {

            }
        });
    }

    private void getThumbnail(ArrayList<BookData> myDataset) {

        mAdapter = new MyBookshelfAdapter(UserinfoShelfFragment.this, getActivity(), myDataset);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);

        this.thumbnailSet = myDataset;

    }

    private void recyclerViewSettings() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getActivity(), 3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        thumbnailList = new ArrayList<>();
        mAdapter = new UserShelfAdapter(thumbnailList);
        mRecyclerView.setAdapter(mAdapter);
    }

}
