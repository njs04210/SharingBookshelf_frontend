package com.example.sharingbookshelf.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;


import com.example.sharingbookshelf.Fragments.MyBookshelfFragment;
import com.example.sharingbookshelf.Fragments.MyPageFragment;
import com.example.sharingbookshelf.Fragments.OtherBookshelfFragment;
import com.example.sharingbookshelf.Fragments.RankingFragment;
import com.example.sharingbookshelf.Fragments.ReportRankingFragment;
import com.example.sharingbookshelf.HttpRequest.RetrofitClient;
import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.GetUserInfoResponse;
import com.example.sharingbookshelf.Models.KidsData;
import com.example.sharingbookshelf.Models.UserData;
import com.example.sharingbookshelf.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView btnNav;

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

      /*  // Setting MyBookshelf Fragment as main fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_layout, new MyBookshelfFragment()).commit();*/

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
               /* myData = new GetUserInfoResponse();
                UserData currentUser = response.body().getUser();
                KidsData currentKids = response.body().getKids();
                myData.setKids(currentKids);
                myData.setUser(currentUser);*/
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

                    int itemId = item.getItemId();

                    if (itemId == R.id.myBookShelf) {
                        selectedFragment = new MyBookshelfFragment();
                    } else if (itemId == R.id.otherBookshelf) {
                        selectedFragment = new OtherBookshelfFragment();
                    } else if (itemId == R.id.ranking) {
                        selectedFragment = new RankingFragment();
                    } else if (itemId == R.id.chatting) {
                        selectedFragment = new ReportRankingFragment();
                    } else if (itemId == R.id.myPage) {
                        selectedFragment = new MyPageFragment();
                    }

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_layout, selectedFragment).commit();

                    return true;
                }

            };
}