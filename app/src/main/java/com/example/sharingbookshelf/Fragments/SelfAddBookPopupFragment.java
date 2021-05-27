package com.example.sharingbookshelf.Fragments;

import android.content.Intent;
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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.sharingbookshelf.Activities.MainActivity;
import com.example.sharingbookshelf.HttpRequest.BookApiRetrofitClient;
import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.BookApiResponse;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class SelfAddBookPopupFragment extends DialogFragment {

    private EditText isbn_field;
    private Button btn_findBook;

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
                    Intent intent = new Intent();
                    intent.putExtra("ISBN", isbn);
                    getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
                    getDialog().dismiss();
                }
            }
        });

        return v;
    }
}
