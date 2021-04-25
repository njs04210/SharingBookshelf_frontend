package com.example.sharingbookshelf.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sharingbookshelf.Fragments.ChattingFragment;
import com.example.sharingbookshelf.Fragments.MyBookshelfFragment;
import com.example.sharingbookshelf.Fragments.MyPageFragment;
import com.example.sharingbookshelf.Fragments.OtherBookshelfFragment;
import com.example.sharingbookshelf.Fragments.RankingFragment;
import com.example.sharingbookshelf.HttpRequest.BookApiRetrofitClient;
import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.BookApiResponse;
import com.example.sharingbookshelf.Models.GetUserInfoResponse;
import com.example.sharingbookshelf.R;
import com.example.sharingbookshelf.HttpRequest.RetrofitClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private static final int BARCODE_ACTIVITY = 10000;
    private static final int ADDSELF_ACTIVITY = 10001;
    private BottomNavigationView btnNav;

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
                        selectedFragment = new ChattingFragment();
                    } else if (itemId == R.id.myPage) {
                        selectedFragment = new MyPageFragment();
                    }

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_layout, selectedFragment).commit();

                    return true;
                }

            };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == BARCODE_ACTIVITY) { //바코드 인식 결과
            if (resultCode == RESULT_OK) {
                Fragment myBookshelfFragment = getSupportFragmentManager()
                        .findFragmentById(R.id.myBookShelf);
                myBookshelfFragment.onActivityResult(requestCode, resultCode, intent);
            }
        }
        if (requestCode == ADDSELF_ACTIVITY) { //직접 추가 결과
            if (resultCode == RESULT_OK) {
                Fragment myBookshelfFragment = getSupportFragmentManager()
                        .findFragmentById(R.id.myBookShelf);
                myBookshelfFragment.onActivityResult(requestCode, resultCode, intent);
            }
        }
    }


}