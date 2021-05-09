package com.example.sharingbookshelf.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sharingbookshelf.R;

public class MyPageFragment extends Fragment {

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
        btn_dealList = v.findViewById(R.id.btn_dealList);
        btn_likeList = v.findViewById(R.id.btn_likeList);
        btn_memoList = v.findViewById(R.id.btn_memoList);
        btn_logout = v.findViewById(R.id.btn_logout);
        btn_leaveId = v.findViewById(R.id.btn_leaveId);


        return v;

    }
}