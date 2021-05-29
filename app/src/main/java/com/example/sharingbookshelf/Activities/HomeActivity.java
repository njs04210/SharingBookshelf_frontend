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
import com.example.sharingbookshelf.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView btnNav;

    private static int hasShelfcode = -1; // 서버에서 값 할당 받기 전에 -1로 초기화

    public static void setHasShelfcode(int hasShelfcode) {
        HomeActivity.hasShelfcode = hasShelfcode;
    }

    public static int getHasShelfcode() {
        return hasShelfcode;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnNav = findViewById(R.id.bottomNavigationView);
        btnNav.setOnNavigationItemSelectedListener(navListener);

        // Setting MyBookshelf Fragment as main fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_layout, new MyBookshelfFragment()).commit();

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