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
    private Button btn_mailList;
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

        btn_mailList = v.findViewById(R.id.btn_mailList);
        btn_mailList.setOnClickListener(this);

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
                break;

            case R.id.btn_mailList:
                MessageBoxFragment m = MessageBoxFragment.getInstance();
                m.show(getActivity().getSupportFragmentManager(), "쪽지목록");
                break;

            case R.id.btn_likeList:
                intent = new Intent(getActivity(), LikeListActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.anim_slide_in_bottom, R.anim.anim_slide_out_top);
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

