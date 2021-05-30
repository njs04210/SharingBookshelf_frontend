package com.example.sharingbookshelf.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
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
import com.example.sharingbookshelf.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RankingBookInfoPopupFragment extends DialogFragment {

    private ImageView iv_thumbNail;
    private TextView tv_ISBN, tv_title, tv_authors, tv_publisher, tv_number;
    private BookData bookData;
    private int book_id, total;

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

        Bundle bundle = getArguments();
        book_id = bundle.getInt("book_id");
        total = bundle.getInt("total");

        callBook(book_id);

        return v;
    }

    private void callBook(int bookId) {
        RetrofitServiceApi retrofitServiceApi = RetrofitClient
                .createService(RetrofitServiceApi.class, MainActivity.getJWT());
        Call<BookData> call = retrofitServiceApi.getBookDetails(bookId);
        call.enqueue(new Callback<BookData>() {
            @Override
            public void onResponse(Call<BookData> call, Response<BookData> response) {
                bookData = response.body();
                setView(bookData, total);
            }

            @Override
            public void onFailure(Call<BookData> call, Throwable t) {

            }
        });
    }

    private void setView(BookData bookData, int total) {
        String title = bookData.getTitle();
        String ISBN = bookData.getISBN();
        String author = bookData.getPublisher();
        String thumbnailUri = bookData.getThumbnail();
        String publisher = bookData.getPublisher();

        Glide.with(this).load(thumbnailUri).into(iv_thumbNail);
        tv_title.setText(title);
        tv_ISBN.setText(ISBN);
        tv_authors.setText(author);
        tv_publisher.setText(publisher);
        tv_number.setText(String.valueOf(total));
    }

    @Override
    public void onResume() {
        super.onResume();

        try {
            Window window = getDialog().getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
