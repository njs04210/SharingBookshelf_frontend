package com.example.sharingbookshelf.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.sharingbookshelf.Activities.MainActivity;
import com.example.sharingbookshelf.HttpRequest.BookApiRetrofitClient;
import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.BookApiResponse;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelfAddBookPopupFragment extends DialogFragment {

    private EditText isbn_field;
    private Button btn_findBook;
    private RetrofitServiceApi retrofitServiceApi;

    public static SelfAddBookPopupFragment getInstance() {
        return new SelfAddBookPopupFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_self_add_book_popup, container, false);

        isbn_field = v.findViewById(R.id.ISBN_field);
        btn_findBook = v.findViewById(R.id.btn_findBook);

        btn_findBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(isbn_field.getText().toString()).equals("")) {
                    String isbn = isbn_field.getText().toString();
                    callBookResponse(isbn);
                }
            }
        });

        return v;
    }

    private void callBookResponse(String isbn) {
        retrofitServiceApi = BookApiRetrofitClient.createService(RetrofitServiceApi.class);
        Call<BookApiResponse> call = retrofitServiceApi.setBookApiResponse(isbn, "isbn");
        call.enqueue(new Callback<BookApiResponse>() {
            @Override
            public void onResponse(Call<BookApiResponse> call, Response<BookApiResponse> response) {
                BookApiResponse result = response.body();
                Log.d(MainActivity.MAIN_TAG, "책 api 통신 성공");
                if (result != null) {
                    getBookDetails(result);
                }
            }

            @Override
            public void onFailure(Call<BookApiResponse> call, Throwable t) {
                Log.e(MainActivity.MAIN_TAG, "책 api 통신 실패", t);
            }
        });
    }

    private void getBookDetails(BookApiResponse books) {
        ArrayList<BookApiResponse.Document> documentList = books.documents;
        BookApiResponse.Meta meta = books.metas;

        Bundle bundle = new Bundle();
        bundle.putSerializable("documentList", documentList);
        bundle.putSerializable("meta", meta);

        BookInfoPopupFragment bookInfoPopupFragment = BookInfoPopupFragment.getInstance();
        bookInfoPopupFragment.setArguments(bundle);

        bookInfoPopupFragment.show(getActivity().getSupportFragmentManager(), "Abc");
        getFragmentManager().executePendingTransactions();
        getDialog().dismiss();


    }

}
