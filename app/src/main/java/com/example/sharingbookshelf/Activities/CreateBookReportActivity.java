package com.example.sharingbookshelf.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sharingbookshelf.Fragments.CheckReportPopupFragment;
import com.example.sharingbookshelf.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateBookReportActivity extends AppCompatActivity
        implements View.OnClickListener {

    private ImageButton btn_eraser;
    private Button btn_save;
    private Button btn_penWidth;
    private Button btn_penBlack;
    private Button btn_penBlue;
    private Button btn_penRed;
    private EditText et_paper;
    private TextView tv_date;
    private TextView tv_title;
    private boolean widthFlag = false;
    MyCanvas canvas;
    private int item_id;
    private String title;
    String thumbnailUri;
    String file_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bookreport);

        btn_eraser = findViewById(R.id.btn_eraser);
        btn_penWidth = findViewById(R.id.btn_penWidth);
        btn_penBlack = findViewById(R.id.btn_penBlack);
        btn_penBlue = findViewById(R.id.btn_penBlue);
        btn_penRed = findViewById(R.id.btn_penRed);
        btn_save = findViewById(R.id.btn_save);
        tv_date = findViewById(R.id.tv_date);
        canvas = findViewById(R.id.canvas);
        et_paper = findViewById(R.id.et_paper);
        tv_title = findViewById(R.id.tv_title);

        Intent intent = getIntent();
        title = intent.getExtras().getString("title");
        tv_title.setText(title);

        item_id = intent.getExtras().getInt("item_id");
        thumbnailUri = intent.getExtras().getString("thumbnailUri");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        Date time = new Date();
        String date = dateFormat.format(time);
        tv_date.setText(date);

        editTextSettings();

        btn_eraser.setOnClickListener(this);
        btn_penWidth.setOnClickListener(this);
        btn_penBlack.setOnClickListener(this);
        btn_penBlue.setOnClickListener(this);
        btn_penRed.setOnClickListener(this);
        btn_save.setOnClickListener(this);

    }

    private void editTextSettings() {
        et_paper.setLetterSpacing(1.0f);
        et_paper.setLineSpacing(0, 1.5f);
        et_paper.clearComposingText();
        et_paper.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (et_paper.getLineCount() > 6) {
                    s.delete(s.length() - 1, s.length());
                    Toast.makeText(getApplicationContext(), "60자 이내로 입력해주세요!", Toast.LENGTH_SHORT).show();
                }
                if (s.length() >= 1) {

                    if ((s.charAt(et_paper.length() - 1) >= 32 && s.charAt(et_paper.length() - 1) <= 126)) {
                        s.setSpan(new TextAppearanceSpan(getApplicationContext(), R.style.SpecialTextAppearance)
                                , et_paper.length() - 1, et_paper.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        if ((s.charAt(et_paper.length() - 1) >= 65 && s.charAt(et_paper.length() - 1) <= 90)
                                || (s.charAt(et_paper.length() - 1) >= 97 && s.charAt(et_paper.length() - 1) <= 122)) {
                            s.setSpan(new TextAppearanceSpan(getApplicationContext(), R.style.SpecialTextAppearance_alpha)
                                    , et_paper.length() - 1, et_paper.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        }
                    }
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        int selectedButton = v.getId();

        if (selectedButton == R.id.btn_eraser) {
            canvas.setOperationType((String) v.getTag());
        }
        if (selectedButton == R.id.btn_penWidth) {
            if (!widthFlag) {
                widthFlag = true;
                btn_penWidth.setText("얇게");
                canvas.setPenWidth(true);
            } else {
                widthFlag = false;
                btn_penWidth.setText("굵게");
                canvas.setPenWidth(false);
            }
        }
        if (selectedButton == R.id.btn_penBlack) {
            canvas.setPenColor(0);
        }
        if (selectedButton == R.id.btn_penBlue) {
            canvas.setPenColor(1);
        }
        if (selectedButton == R.id.btn_penRed) {
            canvas.setPenColor(2);
        }
        if (selectedButton == R.id.btn_save) {
            Bitmap bitmap = canvas.getBitmap();
            String contents = et_paper.getText().toString();
            file_name = item_id + ".jpg";

            CheckReportPopupFragment checkReportPopupFragment = new CheckReportPopupFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("bitmap", bitmap);
            bundle.putString("contents", contents);
            bundle.putString("file", file_name);
            bundle.putInt("item_id", item_id);
            bundle.putString("thumbnailUri", thumbnailUri);
            checkReportPopupFragment.setArguments(bundle);

            checkReportPopupFragment.show((CreateBookReportActivity.this).getSupportFragmentManager()
                    ,"CheckReportPopupFragment");

        }
    }
}
