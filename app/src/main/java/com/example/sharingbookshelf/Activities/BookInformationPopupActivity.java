package com.example.sharingbookshelf.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.BookApiResponse;
import com.example.sharingbookshelf.R;

import java.util.List;

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

    private void initializeView() {
        iv_thumbNail = findViewById(R.id.iv_thumbnail);
        tv_title = findViewById(R.id.tv_title);
        tv_authors = findViewById(R.id.tv_authors);
        tv_publisher = findViewById(R.id.tv_publisher);
        tv_sharingstate = findViewById(R.id.tv_sharingstate);
        btn_back = findViewById(R.id.btn_back);
    }

    // 썸네일, 제목, 저자, 출판사, 정가 불러오기
    private void setView(BookApiResponse.Document book) {
        String isbn = book.getIsbn();
        String title = book.getTitle();
        List<String> authors = book.getAuthors();
        String publisher = book.getPublisher();
        String thumbnail = book.getThumbnail();
        Glide.with(this).load(thumbnail).into(iv_thumbNail);

        tv_title.setText(title);
        tv_authors.setText(authors.get(0));
        tv_publisher.setText(publisher);
    }

    // 메모 등록

    // 판매중, 공유중, 공유불가 상태

}

