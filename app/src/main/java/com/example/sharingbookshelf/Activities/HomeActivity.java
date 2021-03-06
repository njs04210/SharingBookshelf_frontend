package com.example.sharingbookshelf.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.sharingbookshelf.Fragments.AnalysisFragment;
import com.example.sharingbookshelf.Fragments.MyBookshelfFragment;
import com.example.sharingbookshelf.Fragments.MyPageFragment;
import com.example.sharingbookshelf.Fragments.OtherBookshelfFragment;
import com.example.sharingbookshelf.Fragments.RankingFragment;
import com.example.sharingbookshelf.HttpRequest.RetrofitClient;
import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.GetUserInfoResponse;
import com.example.sharingbookshelf.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    public static BottomNavigationView btnNav;

    private static int hasShelfcode = -1; // 서버에서 값 할당 받기 전에 -1로 초기화

    public static void setHasShelfcode(int hasShelfcode) {
        HomeActivity.hasShelfcode = hasShelfcode;
    }

    public static int getHasShelfcode() {
        return hasShelfcode;
    }

    private static GetUserInfoResponse myData;

    public static GetUserInfoResponse getMyData() {
        return myData;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnNav = findViewById(R.id.bottomNavigationView);
        btnNav.setOnNavigationItemSelectedListener(navListener);

        setUserSettings(MainActivity.getMemId());

    }

    private void setUserSettings(int memId) {
        RetrofitServiceApi retrofitServiceApi = RetrofitClient.createService(RetrofitServiceApi.class, MainActivity.getJWT());
        Call<GetUserInfoResponse> userInfo = retrofitServiceApi.getUserInfo(memId);
        userInfo.enqueue(new Callback<GetUserInfoResponse>() {
            @Override
            public void onResponse(Call<GetUserInfoResponse> call, Response<GetUserInfoResponse> response) {
                myData = response.body();
                // Setting MyBookshelf Fragment as main fragment
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_layout, new MyBookshelfFragment()).commit();
            }

            @Override
            public void onFailure(Call<GetUserInfoResponse> call, Throwable t) {

            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    String tag = null;

                    int itemId = item.getItemId();

                    if (itemId == R.id.myBookShelf) {
                        selectedFragment = new MyBookshelfFragment();
                        tag = "MyBookshelfFragment";
                    } else if (itemId == R.id.otherBookshelf) {
                        selectedFragment = new OtherBookshelfFragment();
                        tag = "OtherBookshelfFragment";
                    } else if (itemId == R.id.ranking) {
                        selectedFragment = new RankingFragment();
                        tag = "RankingFragment";
                    } else if (itemId == R.id.chart) {
                        selectedFragment = new AnalysisFragment();
                        tag = "AnalysisFragment";
                    } else if (itemId == R.id.myPage) {
                        selectedFragment = new MyPageFragment();
                        tag = "MyPageFragment";
                    }

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_layout, selectedFragment, tag).commit();

                    return true;
                }

            };
}