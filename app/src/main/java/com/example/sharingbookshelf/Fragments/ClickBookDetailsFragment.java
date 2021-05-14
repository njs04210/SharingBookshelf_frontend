package com.example.sharingbookshelf.Fragments;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.example.sharingbookshelf.Activities.MainActivity;
import com.example.sharingbookshelf.HttpRequest.RetrofitClient;
import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.BookData;
import com.example.sharingbookshelf.Models.CommonResponse;
import com.example.sharingbookshelf.Models.MemoData;
import com.example.sharingbookshelf.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClickBookDetailsFragment extends DialogFragment {


    private ImageView iv_thumbNail;
    private TextView tv_title, tv_authors, tv_publisher;
    private EditText memoField;
    private BookData bookData;
    private String isbn;
    private final MemoData memoData = new MemoData();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_click_bookdetails, container, false);

        iv_thumbNail = v.findViewById(R.id.iv_thumbnail);
        tv_title = v.findViewById(R.id.tv_title);
        tv_authors = v.findViewById(R.id.tv_authors);
        tv_publisher = v.findViewById(R.id.tv_publisher);
        memoField = v.findViewById(R.id.et_memo);

        Bundle bundle = getArguments();
        isbn = bundle.getString("isbn");
        callBook(isbn);

        return v;
    }

    private void callBook(String isbn) {
        RetrofitServiceApi retrofitServiceApi = RetrofitClient
                .createService(RetrofitServiceApi.class, MainActivity.getJWT());
        Call<BookData> call = retrofitServiceApi.getBookDetails(isbn);
        call.enqueue(new Callback<BookData>() {
            @Override
            public void onResponse(Call<BookData> call, Response<BookData> response) {
                bookData = response.body();
                callMemo(isbn);
            }

            @Override
            public void onFailure(Call<BookData> call, Throwable t) {

            }
        });
    }

    private void callMemo(String isbn) {
        RetrofitServiceApi retrofitServiceApi = RetrofitClient
                .createService(RetrofitServiceApi.class, MainActivity.getJWT());
        Call<MemoData> call = retrofitServiceApi.getBookMemo(isbn);
        call.enqueue(new Callback<MemoData>() {
            @Override
            public void onResponse(Call<MemoData> call, Response<MemoData> response) {

                if (response.body() != null) {
                    Log.d(MainActivity.MAIN_TAG, response.body().getContent());
                    String content = response.body().getContent();
                    memoData.setContent(content);
                }
                setView();
            }

            @Override
            public void onFailure(Call<MemoData> call, Throwable t) {

            }
        });
    }

    private void setView() {
        String title = bookData.getTitle();
        String author = bookData.getAuthor();
        String publisher = bookData.getPublisher();
        String thumbnail = bookData.getThumbnail();

        Glide.with(this).load(thumbnail).into(iv_thumbNail);
        tv_title.setText(title);
        tv_authors.setText(author);
        tv_publisher.setText(publisher);
        if (memoData.getContent() != null) {
            memoField.setText(memoData.getContent());
        }
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        String content = memoField.getText().toString();

        memoData.setISBN(isbn);
        memoData.setContent(content);

        RetrofitServiceApi retrofitServiceApi = RetrofitClient
                .createService(RetrofitServiceApi.class, MainActivity.getJWT());
        Call<CommonResponse> call = retrofitServiceApi.addMemo(memoData);
        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                Log.d(MainActivity.MAIN_TAG, response.body().getMsg());
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        try {
            WindowMetrics windowMetrics = getActivity().getWindowManager().getCurrentWindowMetrics();

            Window window = getDialog().getWindow();
            window.setGravity(Gravity.BOTTOM);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
            params.width = windowMetrics.getBounds().width();
            params.horizontalMargin = 0.0f;
            getDialog().getWindow().setAttributes(params);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
