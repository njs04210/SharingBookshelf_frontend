package com.example.sharingbookshelf.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.sharingbookshelf.R;

public class ReportRankingFragment extends Fragment implements View.OnClickListener {
    private ImageButton cb_profile1, cb_profile2, cb_profile3;
    private TextView tv_age1, tv_age2, tv_age3, tv_gender1, tv_gender2, tv_gender3,
            tv_nickname1, tv_nickname2, tv_nickname3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_report_ranking, container, false);

        cb_profile1 = v.findViewById(R.id.cb_profile1);
        cb_profile1.setOnClickListener(this);

        cb_profile2 = v.findViewById(R.id.cb_profile2);
        cb_profile2.setOnClickListener(this);

        cb_profile3 = v.findViewById(R.id.cb_profile3);
        cb_profile3.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.cb_profile1:
                ReportRankingPopupFragment r = ReportRankingPopupFragment.getInstance();
                r.show(getActivity().getSupportFragmentManager(), "ReportRankingShelfFragment");
                break;

            case R.id.cb_profile2:
                ReportRankingPopupFragment s = ReportRankingPopupFragment.getInstance();
                s.show(getActivity().getSupportFragmentManager(), "ReportRankingShelfFragment");
                break;

            case R.id.cb_profile3:
                ReportRankingPopupFragment f = ReportRankingPopupFragment.getInstance();
                f.show(getActivity().getSupportFragmentManager(), "ReportRankingShelfFragment");
                break;
        }
    }
}
