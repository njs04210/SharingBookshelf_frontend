package com.example.sharingbookshelf.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sharingbookshelf.Activities.MainActivity;
import com.example.sharingbookshelf.HttpRequest.RetrofitClient;
import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.CommonResponse;
import com.example.sharingbookshelf.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sharingbookshelf.Activities.HomeActivity.setHasShelfcode;

public class EmptyShelfFragment extends Fragment {

    private Button btn_makeBookshelf;
    private RetrofitServiceApi retrofitServiceApi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_empty_shelf, container, false);
        btn_makeBookshelf = v.findViewById(R.id.btn_makeBookshelf);

        btn_makeBookshelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofitServiceApi = RetrofitClient.createService(RetrofitServiceApi.class, MainActivity.getJWT());
                Call<CommonResponse> call = retrofitServiceApi.createShelf();
                call.enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                        setHasShelfcode(response.body().getCode());
                        Log.d(MainActivity.MAIN_TAG, response.body().getMsg());
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.bookshelf, new NoEmptyShelfFragment()).commit();
                    }

                    @Override
                    public void onFailure(Call<CommonResponse> call, Throwable t) {

                    }
                });
            }
        });

        return v;
    }
}