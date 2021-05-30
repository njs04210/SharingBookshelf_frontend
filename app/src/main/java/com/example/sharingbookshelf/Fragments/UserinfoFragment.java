package com.example.sharingbookshelf.Fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.sharingbookshelf.Activities.MainActivity;
import com.example.sharingbookshelf.HttpRequest.RetrofitClient;
import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.GetUserInfoResponse;
import com.example.sharingbookshelf.R;
import com.google.android.material.tabs.TabLayout;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserinfoFragment extends DialogFragment implements View.OnClickListener {

    private TabLayout mtabLayout;
    private UserinfoReportFragment userinforeportFragment;
    private UserinfoShelfFragment userinfoshelfFragment;
    private RetrofitServiceApi retrofitServiceApi;
    public RequestManager mGlideRequestManager;

    private CircleImageView civ_profile;
    private TextView tv_nickname;
    private Button btn_showFight, btn_viewProfile;
    private int mem_id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
        mGlideRequestManager = Glide.with(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_userinfo, container, false);

        civ_profile = v.findViewById(R.id.civ_user_profile);
        tv_nickname = v.findViewById(R.id.tv_user_nickname);
        btn_showFight = v.findViewById(R.id.btn_showfight);
        btn_viewProfile = v.findViewById(R.id.btn_showprofile);
        mtabLayout = v.findViewById(R.id.tabLayout_userinfo);

        btn_showFight.setOnClickListener(this);
        btn_viewProfile.setOnClickListener(this);

        Bundle bundle = getArguments();
        mem_id = bundle.getInt("mem_id");

        if (mem_id == MainActivity.getMemId()) {
            btn_viewProfile.setVisibility(View.GONE);
            btn_showFight.setVisibility(View.GONE);
            setMargins(tv_nickname, 0, 50, 0, 0);
        }

        setUserView(mem_id);
        SettingsTabLayout();

        return v;
    }

    private void setMargins (View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }

    private void setUserView(int memId) {
        retrofitServiceApi = RetrofitClient.createService(RetrofitServiceApi.class, MainActivity.getJWT());
        Call<GetUserInfoResponse> call = retrofitServiceApi.getUserInfo(memId);
        call.enqueue(new Callback<GetUserInfoResponse>() {
            @Override
            public void onResponse(Call<GetUserInfoResponse> call, Response<GetUserInfoResponse> response) {
                GetUserInfoResponse result = response.body();
                String nickname = result.getUser().getNickname() + "의 책바구니";
                String profileImg = result.getUser().getPhotoURL();
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


    private void SettingsTabLayout() {

        userinforeportFragment = new UserinfoReportFragment(mem_id);
        userinfoshelfFragment = new UserinfoShelfFragment(mem_id);

        getChildFragmentManager().beginTransaction().add(R.id.usershelf, userinforeportFragment).commit();

        mtabLayout.addTab(mtabLayout.newTab().setText("책장"));
        mtabLayout.addTab(mtabLayout.newTab().setText("독후감"));
        mtabLayout.getTabAt(1).select(); // 독후감 탭으로 초기화

        mtabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                Fragment selected = null;
                if (position == 0) {
                    selected = userinfoshelfFragment;
                    getChildFragmentManager().beginTransaction().replace(R.id.usershelf, selected
                            , "UserinfoShelfFragment").commit();
                }
                else if (position == 1) {
                    selected = userinforeportFragment;
                    getChildFragmentManager().beginTransaction().replace(R.id.usershelf, selected
                            , "UserinfoReportFragment").commit();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        try {
            WindowMetrics windowMetrics = getActivity().getWindowManager().getCurrentWindowMetrics();

            Window window = getDialog().getWindow();
            window.setGravity(Gravity.BOTTOM);

            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
            params.width = windowMetrics.getBounds().width();
            params.height = (int) (windowMetrics.getBounds().height() * 0.9);
            params.horizontalMargin = 0.0f;
            getDialog().getWindow().setAttributes(params);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_showprofile:
                break;

            case R.id.btn_showfight:
                ChallengePopupFragment challengePopupFragment = new ChallengePopupFragment();
                challengePopupFragment.show(getChildFragmentManager(), ChallengePopupFragment.TAG_EVENT_DIALOG);
                break;

        }
    }
}
