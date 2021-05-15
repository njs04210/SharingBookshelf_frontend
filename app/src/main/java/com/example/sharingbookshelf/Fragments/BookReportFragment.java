package com.example.sharingbookshelf.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.sharingbookshelf.R;

public class BookReportFragment extends Fragment {

    private Button btn_write;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_book_report, container, false);

         btn_write = v.findViewById(R.id.btn_noForm);

         btn_write.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 BookReportFragment2 e = new BookReportFragment2();
                 e.show(getActivity().getSupportFragmentManager(), "Abc");
             }
         });

        return v;

    }


}