package com.example.sharingbookshelf.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.sharingbookshelf.Activities.HomeActivity;
import com.example.sharingbookshelf.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AccessWarningFragment extends DialogFragment {

    private Button btn_backhome;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_access_warning_popup, container, false);

        btn_backhome = v.findViewById(R.id.btn_backhome);

        btn_backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivity.btnNav.setSelectedItemId(R.id.myBookShelf);
            }

        });
        return v;
    }

}
