package com.example.sharingbookshelf.Fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowMetrics;

import com.example.sharingbookshelf.Adapters.FightboxAdapter;
import com.example.sharingbookshelf.Models.fightData;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;

public class FightBoxFragment extends DialogFragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<fightData> fightlist = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }


    public static FightBoxFragment getInstance() {
        FightBoxFragment fightBoxFragment = new FightBoxFragment();
        return fightBoxFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fightbox, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_mail);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        setMessageBox();

        mAdapter = new FightboxAdapter(fightlist);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    private void setMessageBox() {
        for (int i = 0; i < 5; i++) {

            fightData fightData = new fightData();

            fightData.setProfileImage(R.drawable.icon_logo);
            fightData.setUserName("진주맘");
            fightData.setFightMessage("독서대결 펼쳐요!");
            fightData.setFightTime("~yyyy.MM.dd");
            fightData.setFightaim("5권");

            fightlist.add(fightData);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onResume() {
        super.onResume();

        try {
            WindowMetrics windowMetrics = getActivity().getWindowManager().getCurrentWindowMetrics();

            Window window = getDialog().getWindow();
            window.setGravity(Gravity.BOTTOM);

            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

            WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
            params.width = windowMetrics.getBounds().width();
            params.height = (int) (windowMetrics.getBounds().height() * 0.9);
            getDialog().getWindow().setAttributes(params);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}