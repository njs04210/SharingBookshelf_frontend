package com.example.sharingbookshelf.Fragments;

import android.graphics.Color;
import android.graphics.Insets;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;

import com.example.sharingbookshelf.Adapters.MailboxAdapter;
import com.example.sharingbookshelf.Models.MailData;
import com.example.sharingbookshelf.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MailboxFragment extends DialogFragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<MailData> mailList = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public static MailboxFragment getInstance() {
        MailboxFragment mailboxFragment = new MailboxFragment();
        return mailboxFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mailbox, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_mail);

        mLayoutManager = new LinearLayoutManager(getContext());
        setMailbox();

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MailboxAdapter(mailList);
        mRecyclerView.setAdapter(mAdapter);


        return view;
    }

    private void setMailbox() { //얼레벌레 만들어놓은 데이터
        for (int i = 0; i < 5; i++) {

            MailData mailData = new MailData();

            mailData.setProfileImage(R.drawable.icon_logo);
            mailData.setUserName("진주맘");
            mailData.setLastMessage("독후감이 좋네요^^");
            mailData.setMessageTime("날짜,시간");
            /*시간받아오는 거였는데 이게 맞는지도 모르겠음
            Date now = new Date();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            // Model Data
            String dateTimeString = df.format(now);
            // Show in VIEW
            mailData.setMessageTime(dateTimeString);
*/

            mailList.add(mailData);
        }
        //mAdapter.notifyDataSetChanged();
    }

/*
    @Override
    public void onResume() {   //여긴모지 일단복붙
        super.onResume();

//      dialog fragment custom width
        try {
            WindowMetrics windowMetrics = getActivity().getWindowManager().getCurrentWindowMetrics();
            Insets insets = windowMetrics.getWindowInsets().getInsetsIgnoringVisibility(
                    WindowInsets.Type.systemBars());

            Window window = getDialog().getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
            params.width = windowMetrics.getBounds().width() - 50;
            getDialog().getWindow().setAttributes(params);
        } catch (Exception e) {
            // regardless
            e.printStackTrace();
        }
    }
    */

}