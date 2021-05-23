package com.example.sharingbookshelf.Fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

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

import com.example.sharingbookshelf.Adapters.MailboxAdapter;
import com.example.sharingbookshelf.Models.MessageData;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;

public class MessageBoxFragment extends DialogFragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<MessageData> mailList = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }


    public static MessageBoxFragment getInstance() {
        MessageBoxFragment messageBoxFragment = new MessageBoxFragment();
        return messageBoxFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messagebox, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_mail);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        setMessageBox();

        mAdapter = new MailboxAdapter(mailList);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    private void setMessageBox() {
        for (int i = 0; i < 5; i++) {

            MessageData messageData = new MessageData();

            messageData.setProfileImage(R.drawable.icon_logo);
            messageData.setUserName("진주맘");
            messageData.setLastMessage("독후감이 좋네요^^");
            messageData.setMessageTime("날짜,시간");

            mailList.add(messageData);
        }
    }

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