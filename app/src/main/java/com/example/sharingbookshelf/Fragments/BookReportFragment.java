package com.example.sharingbookshelf.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.sharingbookshelf.Activities.SelectAgeAreaActivity;
import com.example.sharingbookshelf.R;

public class BookReportFragment extends Fragment implements View.OnClickListener {

    private Button btn_write, btn_list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_book_report, container, false);
        Button btn_createReport = v.findViewById(R.id.btn_noForm);

        btn_createReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateBookReportFragment f = CreateBookReportFragment.getInstance();
                f.show(getActivity().getSupportFragmentManager(), "CreateBookReportFragment");
            }
        });

        btn_write = v.findViewById(R.id.btn_Form);
        btn_write.setOnClickListener(this);

        btn_list = v.findViewById(R.id.btn_list);
        btn_list.setOnClickListener(this);

        return v;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_list:
                BookReportBoxFragment r = new BookReportBoxFragment();
                r.show(getActivity().getSupportFragmentManager(), "Abc");
                break;

        }
    }

}