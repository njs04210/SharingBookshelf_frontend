package com.example.sharingbookshelf.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.sharingbookshelf.Activities.LeaveIdActivity;
import com.example.sharingbookshelf.Activities.LikeListActivity;
import com.example.sharingbookshelf.Activities.MainActivity;
import com.example.sharingbookshelf.R;
import com.google.firebase.auth.FirebaseAuth;

public class MyPageFragment extends Fragment implements View.OnClickListener {


    private FirebaseAuth mAuth;

    private Button btn_editProfile;
    private Button btn_dealList;
    private Button btn_likeList;
    private Button btn_memoList;
    private Button btn_logout;
    private Button btn_leaveId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_my_page, container, false);

        btn_editProfile = v.findViewById(R.id.btn_editProfile);
        btn_editProfile.setOnClickListener(this);

        btn_dealList = v.findViewById(R.id.btn_dealList);
        btn_dealList.setOnClickListener(this);

        btn_likeList = v.findViewById(R.id.btn_likeList);
        btn_likeList.setOnClickListener(this);

        btn_memoList = v.findViewById(R.id.btn_memoList);
        btn_memoList.setOnClickListener(this);

        btn_logout = v.findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(this);

        btn_leaveId = v.findViewById(R.id.btn_leaveId);
        btn_leaveId.setOnClickListener(this);

        return v;

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_editProfile:
                EditProfileFragment e = EditProfileFragment.getInstance();
                e.show(getActivity().getSupportFragmentManager(), "Abc");
                /*intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.anim_slide_in_bottom, R.anim.anim_slide_out_top);*/
                break;

            case R.id.btn_dealList:
                //추가 예정
                Toast.makeText(getActivity().getApplicationContext(), "거래목록", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_likeList:
                LikelistFragment l = LikelistFragment.getInstance();
                l.show(getActivity().getSupportFragmentManager(), "Abc");
                /*intent = new Intent(getActivity(), LikeListActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.anim_slide_in_bottom, R.anim.anim_slide_out_top);*/
                break;

            case R.id.btn_memoList:
                //추가 예정
                Toast.makeText(getActivity().getApplicationContext(), "메모목록", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_logout:
                //추가 예정
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

