package com.example.sharingbookshelf.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.sharingbookshelf.R;

public class BookReportFragment extends Fragment implements View.OnClickListener {

    private AppCompatButton btn_showReportBox;
    private AppCompatButton btn_createReport;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_book_report, container, false);

        btn_createReport = v.findViewById(R.id.btn_createReport);
        btn_showReportBox = v.findViewById(R.id.btn_showReportBox);

        btn_showReportBox.setOnClickListener(this);
        btn_createReport.setOnClickListener(this);

        return v;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_createReport:
                SelectBookReportPopupFragment selectBookReportPopupFragment = new SelectBookReportPopupFragment();
                selectBookReportPopupFragment.show(getFragmentManager(), "SelectBookReportPopupFragment");
                break;

            case R.id.btn_showReportBox:
                BookReportBoxFragment bookReportBoxFragment = new BookReportBoxFragment();
                bookReportBoxFragment.show(getFragmentManager(), "BookReportBoxFragment");
                break;
        }
    }

}