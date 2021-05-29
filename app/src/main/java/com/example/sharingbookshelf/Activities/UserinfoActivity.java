package com.example.sharingbookshelf.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.sharingbookshelf.Fragments.ChallengePopupFragment;
import com.example.sharingbookshelf.Fragments.UserinfoReportFragment;
import com.example.sharingbookshelf.Fragments.UserinfoShelfFragment;
import com.example.sharingbookshelf.R;
import com.google.android.material.tabs.TabLayout;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserinfoActivity extends FragmentActivity {
    private TabLayout mtabLayout;
    private UserinfoReportFragment userinforeportFragment;
    private UserinfoShelfFragment userinfoshelfFragment;

    private CircleImageView civ_profile;
    private TextView tv_nickname;
    private Button btn_viewProfile, btn_fightUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);

        civ_profile = findViewById(R.id.civ_user_profile);
        tv_nickname = findViewById(R.id.tv_user_nickname);

        btn_fightUser = findViewById(R.id.btn_fightUser);
        btn_fightUser.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChallengePopupFragment c = ChallengePopupFragment.getInstance();
                c.show(getSupportFragmentManager(), ChallengePopupFragment.TAG_EVENT_DIALOG);
            }
        });

        btn_viewProfile = findViewById(R.id.btn_viewProfile);
        btn_viewProfile.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : click event
            }
        });

        userinforeportFragment = new UserinfoReportFragment();
        userinfoshelfFragment = new UserinfoShelfFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.usershelf, userinforeportFragment).commit();

        mtabLayout = findViewById(R.id.tabLayout_userinfo);
        mtabLayout.addTab(mtabLayout.newTab().setText("책장"));
        mtabLayout.addTab(mtabLayout.newTab().setText("독후감"));

        mtabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment selected = null;
                if (position == 0)
                    selected = userinfoshelfFragment;
                else if (position == 1)
                    selected = userinforeportFragment;

                getSupportFragmentManager().beginTransaction().replace(R.id.usershelf, selected).commit();
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
