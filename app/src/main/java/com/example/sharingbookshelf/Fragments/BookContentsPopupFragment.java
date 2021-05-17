package com.example.sharingbookshelf.Fragments;

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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.sharingbookshelf.R;

public class BookContentsPopupFragment extends DialogFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_book_contents_popup, container, false);

        Window window = getDialog().getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
        params.x = 220;
        params.y = 50;
        getDialog().getWindow().setAttributes(params);

        TextView tv_contents = v.findViewById(R.id.tv_bookContents);

        Bundle bundle = getArguments();
        String contents = bundle.getString("contents");
        if (!contents.equals("")) {
            tv_contents.setText(contents + "...");
        } else {
            tv_contents.setText("책 내용 정보가 없어요!");
        }

        return v;
    }


}
