package com.example.sharingbookshelf.Fragments;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.Insets;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingbookshelf.Models.BookreportData;
import com.example.sharingbookshelf.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReportRankingPopupFragment extends DialogFragment {

    private CircleImageView civ_profile;
    private TextView tv_nickname;
    private TabLayout mtabLayout;
    private Button btn_viewProfile, btn_fightUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    public static ReportRankingPopupFragment getInstance() {
        ReportRankingPopupFragment reportrankingshelfFragment = new ReportRankingPopupFragment();
        return reportrankingshelfFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reportranking_popup, container, false);

        civ_profile = view.findViewById(R.id.civ_profile);
        tv_nickname = view.findViewById(R.id.tv_nickname);
        mtabLayout = view.findViewById(R.id.tabLayout_reportranking);

        mtabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) {
                    getChildFragmentManager().beginTransaction()
                            .replace(R.id.reportshelf, new ReportRankingShelfFragment(), "ReportRankingShelfFragment").commit();
                }
                if (position == 1) {
                    getChildFragmentManager().beginTransaction()
                            .replace(R.id.reportshelf, new ReportRankingReportFragment(), "ReportRankingReportFragment").commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            WindowMetrics windowMetrics = getActivity().getWindowManager().getCurrentWindowMetrics();
            Insets insets = windowMetrics.getWindowInsets().getInsetsIgnoringVisibility(
                    WindowInsets.Type.systemBars());
            Window window = getDialog().getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
            params.width = windowMetrics.getBounds().width();
            getDialog().getWindow().setAttributes(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
