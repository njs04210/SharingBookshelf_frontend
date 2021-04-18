package com.example.sharingbookshelf.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.EditText;

import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.R;

public class BookInformationPopupActivity extends Activity {

    private EditText BookMemo_field;
    private RetrofitServiceApi retrofitServiceApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_book_information_popup);

        //UI 객체생성
        BookMemo_field = (EditText)findViewById(R.id.BookMemo_field);

    }
    // 뒤로 가기

    // 메모 입력

    // 메모 등
}
