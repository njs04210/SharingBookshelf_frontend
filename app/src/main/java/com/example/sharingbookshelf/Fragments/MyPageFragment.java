package com.example.sharingbookshelf.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sharingbookshelf.Activities.HomeActivity;
import com.example.sharingbookshelf.Activities.LeaveIdActivity;
import com.example.sharingbookshelf.Activities.MainActivity;
import com.example.sharingbookshelf.R;
import com.google.firebase.auth.FirebaseAuth;

public class MyPageFragment extends Fragment implements View.OnClickListener {

    private Button btn_editProfile;
    private Button btn_mailList;
    private Button btn_likeList;
    private Button btn_logout;
    private Button btn_leaveId;
    private TextView tv_nickname;
    private TextView tv_kidsInfo;
    private ImageView iv_profileImg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_my_page, container, false);

        btn_editProfile = v.findViewById(R.id.btn_editProfile);
        btn_mailList = v.findViewById(R.id.btn_fightbox);
        btn_likeList = v.findViewById(R.id.btn_likeList);
        btn_logout = v.findViewById(R.id.btn_logout);
        btn_leaveId = v.findViewById(R.id.btn_leaveId);
        tv_nickname = v.findViewById(R.id.tv_nickname);
        tv_kidsInfo = v.findViewById(R.id.tv_kidsInfo);
        iv_profileImg = v.findViewById(R.id.iv_profileImg);

        btn_editProfile.setOnClickListener(this);
        btn_mailList.setOnClickListener(this);
        btn_likeList.setOnClickListener(this);
        btn_logout.setOnClickListener(this);
        btn_leaveId.setOnClickListener(this);

        setUserView();

        return v;

    }

    private void setUserView() {
        String nickname = HomeActivity.getMyData().getUser().getNickname();
        String photoURL = HomeActivity.getMyData().getUser().getPhotoURL();
        String sex = HomeActivity.getMyData().getKids().getSex() == 1 ? "남자" : "여자";
        int age = HomeActivity.getMyData().getKids().getAge();

        tv_kidsInfo.setText(age + "세 / " + sex);
        tv_nickname.setText(nickname);
        Glide
                .with(iv_profileImg.getContext())
                .load(photoURL)
                .fitCenter()
                .placeholder(R.drawable.icon_logo)
                .into(iv_profileImg);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_editProfile:
                EditProfileFragment e = EditProfileFragment.getInstance();
                e.show(getActivity().getSupportFragmentManager(), "EditProfileFragment");
                break;

            case R.id.btn_fightbox:
                FightBoxFragment m = FightBoxFragment.getInstance();
                m.show(getActivity().getSupportFragmentManager(), "FightBoxFragment");
                break;

            case R.id.btn_logout:
                Toast.makeText(getActivity().getApplicationContext(), "로그아웃", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;

            case R.id.btn_leaveId:
                intent = new Intent(getActivity(), LeaveIdActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.anim_slide_in_bottom, R.anim.anim_slide_out_top);
                break;
        }
    }
}

