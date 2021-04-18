package com.example.sharingbookshelf.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.R;

public class BookInformationPopupActivity extends Activity {

    private ImageView iv_thumbNail;
    private TextView tv_title, tv_authors, tv_publisher, tv_sharingstate;
    private EditText BookMemo_field;
    private Button btn_addBook, btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //타이틀바 없애기
        setContentView(R.layout.activity_book_information_popup);

        //UI 객체생성
        BookMemo_field = (EditText)findViewById(R.id.BookMemo_field);

    }
    // 썸네일, 제목, 저자, 출판사, 정가 불러오기

    // 메모 등록

    // 판매중, 공유중, 공유불가 상태
}
