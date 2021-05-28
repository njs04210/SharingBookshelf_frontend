package com.example.sharingbookshelf.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.sharingbookshelf.Fragments.ReportRankingReportFragment;
import com.example.sharingbookshelf.Fragments.ReportRankingShelfFragment;
import com.example.sharingbookshelf.R;
import com.google.android.material.tabs.TabLayout;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReportRankingPopupActivity extends FragmentActivity {
    private TabLayout mtabLayout;
    private ReportRankingReportFragment reportRankingReportFragment;
    private ReportRankingShelfFragment reportrankingShelfFragement;

    private CircleImageView civ_profile;
    private TextView tv_nickname;
    private Button btn_viewProfile, btn_fightUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportranking_popup);

        reportRankingReportFragment = new ReportRankingReportFragment();
        reportrankingShelfFragement = new ReportRankingShelfFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.reportshelf, reportRankingReportFragment).commit();

        mtabLayout = findViewById(R.id.tabLayout_reportranking);
        mtabLayout.addTab(mtabLayout.newTab().setText("책장"));
        mtabLayout.addTab(mtabLayout.newTab().setText("독후감"));

        mtabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment selected = null;
                if (position == 0)
                    selected = reportrankingShelfFragement;
                else if (position == 1)
                    selected = reportRankingReportFragment;

                getSupportFragmentManager().beginTransaction().replace(R.id.reportshelf, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
