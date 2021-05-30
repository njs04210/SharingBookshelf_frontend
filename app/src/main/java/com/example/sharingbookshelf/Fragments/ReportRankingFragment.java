package com.example.sharingbookshelf.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.sharingbookshelf.Activities.MainActivity;
import com.example.sharingbookshelf.HttpRequest.RetrofitClient;
import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.KidsData;
import com.example.sharingbookshelf.Models.RankingData;
import com.example.sharingbookshelf.Models.RankingResponse;
import com.example.sharingbookshelf.Models.UserData;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportRankingFragment extends Fragment implements View.OnClickListener {

    private RetrofitServiceApi retrofitServiceApi;
    private ImageView iv_profile1, iv_profile2, iv_profile3;
    private TextView tv_age1, tv_age2, tv_age3, tv_gender1, tv_gender2, tv_gender3,
            tv_nickname1, tv_nickname2, tv_nickname3, tv_count1, tv_count2, tv_count3;

    private ArrayList<TextView> tv_age;
    private ArrayList<TextView> tv_gender;
    private ArrayList<TextView> tv_nickname;
    private ArrayList<TextView> tv_count;
    private ArrayList<ImageView> iv_profile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_report_ranking, container, false);

        tv_age = new ArrayList<>();
        tv_gender = new ArrayList<>();
        tv_nickname = new ArrayList<>();
        tv_count = new ArrayList<>();
        iv_profile = new ArrayList<>();

        tv_age1 = v.findViewById(R.id.tv_1st_age);
        tv_age2 = v.findViewById(R.id.tv_2nd_age);
        tv_age3 = v.findViewById(R.id.tv_3rd_age);
        tv_age.add(tv_age1);
        tv_age.add(tv_age2);
        tv_age.add(tv_age3);

        tv_gender1 = v.findViewById(R.id.tv_1st_gender);
        tv_gender2 = v.findViewById(R.id.tv_2nd_gender);
        tv_gender3 = v.findViewById(R.id.tv_3rd_gender);
        tv_gender.add(tv_gender1);
        tv_gender.add(tv_gender2);
        tv_gender.add(tv_gender3);

        tv_nickname1 = v.findViewById(R.id.tv_1st_nickname);
        tv_nickname2 = v.findViewById(R.id.tv_2nd_nickname);
        tv_nickname3 = v.findViewById(R.id.tv_3rd_nickname);
        tv_nickname.add(tv_nickname1);
        tv_nickname.add(tv_nickname2);
        tv_nickname.add(tv_nickname3);

        iv_profile1 = v.findViewById(R.id.cb_profile1);
        iv_profile1.setOnClickListener(this);

        iv_profile2 = v.findViewById(R.id.cb_profile2);
        iv_profile2.setOnClickListener(this);

        iv_profile3 = v.findViewById(R.id.cb_profile3);
        iv_profile3.setOnClickListener(this);

        iv_profile.add(iv_profile1);
        iv_profile.add(iv_profile2);
        iv_profile.add(iv_profile3);

        tv_count1 = v.findViewById(R.id.tv_count1);
        tv_count2 = v.findViewById(R.id.tv_count2);
        tv_count3 = v.findViewById(R.id.tv_count3);

        tv_count.add(tv_count1);
        tv_count.add(tv_count2);
        tv_count.add(tv_count3);

        getReportRankingData();

        return v;
    }

    private void getReportRankingData() {
        retrofitServiceApi = RetrofitClient.createService(RetrofitServiceApi.class, MainActivity.getJWT());
        Call<RankingResponse> call = retrofitServiceApi.getReportsRanking();
        call.enqueue(new Callback<RankingResponse>() {
            @Override
            public void onResponse(Call<RankingResponse> call, Response<RankingResponse> response) {
                if (response.body() != null) {
                    if (response.body().getCode() == 82) {
                        setView(response.body().getRankingData());
                    }
                }
            }

            @Override
            public void onFailure(Call<RankingResponse> call, Throwable t) {

            }
        });
    }

    private void setView(ArrayList<RankingData> rankingData) {
        for (int i = 0; i < 3; i++) {
            int total = rankingData.get(i).getTotal();
            KidsData kids = rankingData.get(i).getKids();
            UserData user = rankingData.get(i).getUser();

            String sex = null;
            if (kids.getSex() == 1) {
                sex = "남자";
            } else if (kids.getSex() == 2) {
                sex = "여자";
            }

            tv_age.get(i).setText(kids.getAge() + "세");
            tv_gender.get(i).setText(sex);
            tv_nickname.get(i).setText(user.getNickname());
            tv_count.get(i).setText("독후감 개수 : " + total + "개");
            Glide
                    .with(iv_profile.get(i).getContext())
                    .load(user.getPhotoURL())
                    .centerCrop()
                    .placeholder(R.drawable.icon_logo)
                    .into(iv_profile.get(i));
        }
    }

    @Override
    public void onClick(View v) {

        UserinfoFragment userinfoFragment = new UserinfoFragment();
        userinfoFragment.show(getActivity().getSupportFragmentManager(), "UserinfoFragment");

    }
}
