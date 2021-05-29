package com.example.sharingbookshelf.Fragments;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.example.sharingbookshelf.Activities.MainActivity;
import com.example.sharingbookshelf.HttpRequest.RetrofitClient;
import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.BookData;
import com.example.sharingbookshelf.Models.CommonResponse;
import com.example.sharingbookshelf.Models.MemoData;
import com.example.sharingbookshelf.Models.RankingData;
import com.example.sharingbookshelf.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RankingBookInfoPopupFragment extends DialogFragment {


    private static SelectBookReportPopupFragment selectBookReportPopupFragment = null;
    private ImageView iv_thumbNail;
    private TextView tv_ISBN, tv_title, tv_authors, tv_publisher, tv_number;

    public static SelectBookReportPopupFragment getInstance() {
        if (selectBookReportPopupFragment == null) {
            selectBookReportPopupFragment = new SelectBookReportPopupFragment();
        }
        return selectBookReportPopupFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ranking_bookinfo_popup, container, false);

        iv_thumbNail = v.findViewById(R.id.iv_thumbnail);
        tv_ISBN = v.findViewById(R.id.tv_ISBN);
        tv_title = v.findViewById(R.id.tv_title);
        tv_authors = v.findViewById(R.id.tv_authors);
        tv_publisher = v.findViewById(R.id.tv_publisher);
        tv_number = v.findViewById(R.id.tv_number);

        setView();
        return v;
    }

    private void setView() {
        String title = "책이름";
        String ISBN = "1234567890";
        String author = "작가";
        String publisher = "출판사";
        String thumbnail = ("http://image.kyobobook.co.kr/images/book/xlarge/923/x9791164137923.jpg");
        String number = ("17");

        Glide.with(this).load(thumbnail).into(iv_thumbNail);
        tv_title.setText(title);
        tv_ISBN.setText(ISBN);
        tv_authors.setText(author);
        tv_publisher.setText(publisher);
        tv_number.setText(number);
    }

    @Override
    public void onResume() {
        super.onResume();

        try {
            Window window = getDialog().getWindow();
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
