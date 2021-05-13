package com.example.sharingbookshelf.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.sharingbookshelf.Activities.MainActivity;
import com.example.sharingbookshelf.HttpRequest.RetrofitClient;
import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.GetShelfStatusResponse;
import com.example.sharingbookshelf.Models.GetUserInfoResponse;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyBookshelfFragment extends Fragment {

    private CircleImageView civ_profile;
    private TextView tv_nickname;
    private RetrofitServiceApi retrofitServiceApi;
    public RequestManager mGlideRequestManager;
    public ArrayList<Map<String, Object>> books;

    private static int shelf_statusCode;

    public static void setShelf_statusCode(int shelf_statusCode) {
        MyBookshelfFragment.shelf_statusCode = shelf_statusCode;
    }

    public static int getShelf_statusCode() {
        return shelf_statusCode;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGlideRequestManager = Glide.with(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mybookshelf, container, false);

        civ_profile = v.findViewById(R.id.civ_profile);
        tv_nickname = v.findViewById(R.id.tv_nickname);
        setUserView(MainActivity.getMemId()); //사용자화면 구성
        setShelfView(MainActivity.getMemId());

        return v;
    }

    private void setUserView(int memId) {
        retrofitServiceApi = RetrofitClient.createService(RetrofitServiceApi.class, MainActivity.getJWT());
        Call<GetUserInfoResponse> call = retrofitServiceApi.getUserInfo(memId);
        call.enqueue(new Callback<GetUserInfoResponse>() {
            @Override
            public void onResponse(Call<GetUserInfoResponse> call, Response<GetUserInfoResponse> response) {
                GetUserInfoResponse result = response.body();
                Log.d(MainActivity.MAIN_TAG, "현재사용자 : " + result.getNickname() + " 프로필 : " + result.getProfileImg());
                String nickname = result.getNickname() + "의 책바구니";
                String profileImg = result.getProfileImg();
                tv_nickname.setText(nickname);
                if (profileImg != null) {
                    mGlideRequestManager.load(profileImg).into(civ_profile);
                }
            }

            @Override
            public void onFailure(Call<GetUserInfoResponse> call, Throwable t) {
                Log.e(MainActivity.MAIN_TAG, "사용자 정보 가져오기 실패", t);
            }
        });
    }

    private void setShelfView(int memId) {
        retrofitServiceApi = RetrofitClient.createService(RetrofitServiceApi.class, MainActivity.getJWT());
        Call<GetShelfStatusResponse> call = retrofitServiceApi.getShelfStatus(memId);
        call.enqueue(new Callback<GetShelfStatusResponse>() {
            @Override
            public void onResponse(Call<GetShelfStatusResponse> call, Response<GetShelfStatusResponse> response) {

                setShelf_statusCode(response.body().getCode());
                String msg = response.body().getMsg();
                books = response.body().getHasBooks();

                Log.d(MainActivity.MAIN_TAG, msg);

                if (getShelf_statusCode() == 0) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.bookshelf, new EmptyShelfFragment()).commit();
                } else if (getShelf_statusCode() == 1) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.bookshelf, new NoEmptyShelfFragment(books)).commit();
                }

            }

            @Override
            public void onFailure(Call<GetShelfStatusResponse> call, Throwable t) {
                Log.e(MainActivity.MAIN_TAG, "GetstatusCode 받아오기 실패", t);
            }
        });
    }
}