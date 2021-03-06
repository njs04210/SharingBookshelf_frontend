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
import com.example.sharingbookshelf.Activities.HomeActivity;
import com.example.sharingbookshelf.Activities.MainActivity;
import com.example.sharingbookshelf.HttpRequest.RetrofitClient;
import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.GetShelfStatusResponse;
import com.example.sharingbookshelf.R;
import com.google.android.material.tabs.TabLayout;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sharingbookshelf.Activities.HomeActivity.getHasShelfcode;
import static com.example.sharingbookshelf.Activities.HomeActivity.setHasShelfcode;

public class MyBookshelfFragment extends Fragment {

    private CircleImageView civ_profile;
    private TextView tv_nickname;
    private TabLayout mtabLayout;
    private RetrofitServiceApi retrofitServiceApi;
    public RequestManager mGlideRequestManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGlideRequestManager = Glide.with(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mybookshelf, container, false);

        civ_profile = v.findViewById(R.id.civ_profile);
        tv_nickname = v.findViewById(R.id.tv_nickname);
        mtabLayout = v.findViewById(R.id.tabLayout);

        setUserView();

        if (HomeActivity.getHasShelfcode() == -1) { // 처음 들어왔을때 -1
            checkShelfStatus(MainActivity.getMemId()); // 책장 상태 결정할 메소드
        } else changeInnerFragment(); // 처음 들어온거 아니면 바로 바꿔주면 됨

        mtabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) {
                    if (getHasShelfcode() == 1) {
                        getChildFragmentManager().beginTransaction()
                                .replace(R.id.bookshelf, new NoEmptyShelfFragment(), "NoEmptyShelfFragment").commit();
                    } else if (getHasShelfcode() == 0) {
                        getChildFragmentManager().beginTransaction()
                                .replace(R.id.bookshelf, new EmptyShelfFragment(), "EmptyShelfFragment").commit();
                    }
                }
                if (position == 1) {
                    getChildFragmentManager().beginTransaction()
                            .replace(R.id.bookshelf, new BookReportFragment(), "BookReportFragment").commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return v;
    }

    private void setUserView() {
        String nickname = HomeActivity.getMyData().getUser().getNickname() + "의 책바구니";
        String profileImg = HomeActivity.getMyData().getUser().getPhotoURL();
        tv_nickname.setText(nickname);
        if (profileImg != null) {
            mGlideRequestManager.load(profileImg).into(civ_profile);
        }
    }

    public void checkShelfStatus(int memId) {
        retrofitServiceApi = RetrofitClient.createService(RetrofitServiceApi.class, MainActivity.getJWT());
        Call<GetShelfStatusResponse> call = retrofitServiceApi.getShelfStatus(memId);
        call.enqueue(new Callback<GetShelfStatusResponse>() {
            @Override
            public void onResponse(Call<GetShelfStatusResponse> call, Response<GetShelfStatusResponse> response) {
                setHasShelfcode(response.body().getCode());

                Log.d(MainActivity.MAIN_TAG, response.body().getMsg());
                changeInnerFragment();
            }

            @Override
            public void onFailure(Call<GetShelfStatusResponse> call, Throwable t) {
                Log.e(MainActivity.MAIN_TAG, "GetstatusCode 받아오기 실패", t);
            }
        });
    }

    private void changeInnerFragment() {
        if (getHasShelfcode() == 0) { // 책장 생성 안한 상태
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.bookshelf, new EmptyShelfFragment(), "EmptyShelfFragment").commit();
        } else if (getHasShelfcode() == 1) { // 책장 생성되어있는 상태
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.bookshelf, new NoEmptyShelfFragment(), "NoEmptyShelfFragment").commit();
        }
    }

}