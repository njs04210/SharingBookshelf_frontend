package com.example.sharingbookshelf.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.sharingbookshelf.HttpRequest.RetrofitClient;
import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.BookreportDetailData;
import com.example.sharingbookshelf.Models.OneBookreportResponse;
import com.example.sharingbookshelf.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OneBookReportActivity extends Activity {

    private int item_id;
    private String title;
    private ImageView iv_canvas;
    private TextView tv_created;
    private TextView tv_contents;
    private TextView tv_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_book_report);

        tv_title = findViewById(R.id.tv_title);
        iv_canvas = findViewById(R.id.iv_canvas);
        tv_created = findViewById(R.id.tv_date);
        tv_contents = findViewById(R.id.tv_contents);

        Intent intent = getIntent();
        item_id = intent.getExtras().getInt("item_id");
        title = intent.getExtras().getString("title");

        loadReportData(item_id);
    }

    private void loadReportData(int item_id) {

        RetrofitServiceApi retrofitServiceApi = RetrofitClient.createService(RetrofitServiceApi.class, MainActivity.getJWT());
        Call<OneBookreportResponse> call = retrofitServiceApi.getOneBookReport(item_id);
        call.enqueue(new Callback<OneBookreportResponse>() {
            @Override
            public void onResponse(Call<OneBookreportResponse> call, Response<OneBookreportResponse> response) {
                if (response.body().getCode() == 75) {
                    setReportView(response.body().getBookreportDetailData());
                }
            }

            @Override
            public void onFailure(Call<OneBookreportResponse> call, Throwable t) {

            }
        });
    }

    private void setReportView(BookreportDetailData bookreportDetailData) {
        String canvasUri = bookreportDetailData.getCanvas_uri();
        String contents = bookreportDetailData.getContents();
        String created = bookreportDetailData.getCreated();

        Glide
                .with(iv_canvas.getContext())
                .load(canvasUri)
                .fitCenter()
                .placeholder(R.drawable.icon_book2)
                .into(iv_canvas);
        tv_contents.setText(contents);
        tv_created.setText(created);
        tv_title.setText(title);
        contentsViewSettings();
    }

    private void contentsViewSettings() {
        //tv_contents.setLetterSpacing(1.0f);
        tv_contents.setLineSpacing(0, 1.5f);
        tv_contents.clearComposingText();
        tv_contents.setLetterSpacing((float) 1.05);
        /*for (int i = 0; i < tv_contents.getText().length(); i++) {
            char c = tv_contents.getText().charAt(i);
            if (c >= 32 && c <= 126) {
                tv_contents.setLetterSpacing((float) 1.05);
                if (c >= 65 && c <= 90 || c>=97 && c <= 122) {
                    tv_contents.setLetterSpacing((float) 0.8);
                    tv_contents.setLineSpacing(0,5);
                    tv_contents.setTextSize(20);
                }
            }*/
            /*if ((s.charAt(tv_contents.length() - 1) >= 32 && s.charAt(tv_contents.length() - 1) <= 126)) {
                tv_contents.getText().setSpan(new TextAppearanceSpan(getApplicationContext(), R.style.SpecialTextAppearance)
                        , tv_contents.length() - 1, tv_contents.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                if ((s.charAt(tv_contents.length() - 1) >= 65 && s.charAt(tv_contents.length() - 1) <= 90)
                        || (s.charAt(tv_contents.length() - 1) >= 97 && s.charAt(tv_contents.length() - 1) <= 122)) {
                    s.setSpan(new TextAppearanceSpan(getApplicationContext(), R.style.SpecialTextAppearance_alpha)
                            , tv_contents.length() - 1, tv_contents.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }*/
        }

        /*tv_contents.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (tv_contents.getLineCount() > 6) {
                    s.delete(s.length() - 1, s.length());
                    Toast.makeText(getApplicationContext(), "60자 이내로 입력해주세요!", Toast.LENGTH_SHORT).show();
                }
                if (s.length() >= 1) {

                    if ((s.charAt(tv_contents.length() - 1) >= 32 && s.charAt(tv_contents.length() - 1) <= 126)) {
                        s.setSpan(new TextAppearanceSpan(getApplicationContext(), R.style.SpecialTextAppearance)
                                , tv_contents.length() - 1, tv_contents.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        if ((s.charAt(tv_contents.length() - 1) >= 65 && s.charAt(tv_contents.length() - 1) <= 90)
                                || (s.charAt(tv_contents.length() - 1) >= 97 && s.charAt(tv_contents.length() - 1) <= 122)) {
                            s.setSpan(new TextAppearanceSpan(getApplicationContext(), R.style.SpecialTextAppearance_alpha)
                                    , tv_contents.length() - 1, tv_contents.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        }
                    }
                }

            }
        });*/
    }
//}
