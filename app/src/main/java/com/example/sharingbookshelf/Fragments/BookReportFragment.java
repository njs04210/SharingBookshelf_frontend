package com.example.sharingbookshelf.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.sharingbookshelf.Activities.EditProfileActivity;
import com.example.sharingbookshelf.Activities.LeaveIdActivity;
import com.example.sharingbookshelf.Activities.LikeListActivity;
import com.example.sharingbookshelf.Activities.MainActivity;
import com.example.sharingbookshelf.R;
import com.google.firebase.auth.FirebaseAuth;

public class BookReportFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_book_report, container, false);

        return v;

    }


}