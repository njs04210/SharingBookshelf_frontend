package com.example.sharingbookshelf.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.text.GetChars;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.GetUserInfoResponse;
import com.example.sharingbookshelf.R;
import com.example.sharingbookshelf.RetrofitClient;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private Button btn_book;
    private CircleImageView civ_profile;
    private TextView tv_nickname;
    private RetrofitServiceApi retrofitServiceApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initializeView();
        setUserView(MainActivity.getMemId()); //사용자화면 구성

        btn_book = findViewById(R.id.btn_book);

        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final PopupMenu popupMenu = new PopupMenu(getApplicationContext(),view);
                getMenuInflater().inflate(R.menu.menu_register_book,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.scanBarcode){ // 메뉴 홈페이지 만들고 intent로 수정
                            Toast.makeText(HomeActivity.this, "카메라로 바코드 스캔", Toast.LENGTH_SHORT).show();
                        }else if (menuItem.getItemId() == R.id.searchBookName){
                            Toast.makeText(HomeActivity.this, "ISBN 혹은 책 이름으로 검색", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(HomeActivity.this, "수동으로 정보 입력", Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    private void initializeView() {
        civ_profile = findViewById(R.id.civ_profile);
        tv_nickname = findViewById(R.id.tv_nickname);
    }

    private void setUserView(int memId) {
        retrofitServiceApi = RetrofitClient.createService(RetrofitServiceApi.class, MainActivity.getJWT());
        Call<GetUserInfoResponse> call = retrofitServiceApi.getUserInfo(memId);
        call.enqueue(new Callback<GetUserInfoResponse>() {
            @Override
            public void onResponse(Call<GetUserInfoResponse> call, Response<GetUserInfoResponse> response) {
                GetUserInfoResponse result = response.body();
                Log.d(MainActivity.MAIN_TAG, "현재사용자 : " + result.getNickname() + " 프로필 : " + result.getProfileImg());
                String nickname = result.getNickname();
                String profileImg = result.getProfileImg();
                tv_nickname.setText(nickname);
                if (profileImg != null) {
                    Glide.with(HomeActivity.this).load(profileImg).into(civ_profile);
                }
            }

            @Override
            public void onFailure(Call<GetUserInfoResponse> call, Throwable t) {
                Log.e(MainActivity.MAIN_TAG, "사용자 정보 가져오기 실패", t);
            }
        });
    }

}