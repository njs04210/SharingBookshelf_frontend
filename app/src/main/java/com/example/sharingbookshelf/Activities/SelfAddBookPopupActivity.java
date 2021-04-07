package com.example.sharingbookshelf.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.example.sharingbookshelf.R;

public class SelfAddBookPopupActivity extends Activity {

    private EditText ISBN_field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_book_details_popup);

        //UI 객체생성
        ISBN_field = (EditText)findViewById(R.id.ISBN_field);

    }

    public void mOnClose(View view) {
        //데이터 전달하기
        String res = ISBN_field.getText().toString();
        Intent intent = new Intent(getApplicationContext(), TakingPhotoActivity.class);
        intent.putExtra("ISBN", res);
        setResult(RESULT_OK, intent);
        finish();

    }
}