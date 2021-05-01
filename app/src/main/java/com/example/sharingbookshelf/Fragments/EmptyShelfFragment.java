package com.example.sharingbookshelf.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sharingbookshelf.R;

public class EmptyShelfFragment extends Fragment {

    private Button btn_makeBookshelf;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_empty_shelf, container, false);
        btn_makeBookshelf = v.findViewById(R.id.btn_makeBookshelf);
        btn_makeBookshelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.bookshelf, new NoEmptyShelfFragment()).commit();
                MyBookshelfFragment.sample = 0;
            }
        });

        return v;
    }
}