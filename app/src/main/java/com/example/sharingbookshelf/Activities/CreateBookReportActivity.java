package com.example.sharingbookshelf.Activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.TextAppearanceSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sharingbookshelf.R;

public class CreateBookReportActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private Button btn_eraser;
    private Button btn_pen;
    private EditText editText;
    boolean isChecked = false;
    MyCanvas canvas;
    String file_name = "sample.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bookreport);

        btn_eraser = findViewById(R.id.btn_eraser);
        btn_pen = findViewById(R.id.btn_pen);
        canvas = findViewById(R.id.canvas);
        editText = findViewById(R.id.et_paper);

        editTextSettings();

        btn_eraser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.setOperationType((String) v.getTag());
            }
        });

    }

    private void editTextSettings() {
        editText.setLineSpacing(0, 1.5f);
        editText.setLetterSpacing(1.0f);
        editText.clearComposingText();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editText.getLineCount() > 6) {
                    s.delete(s.length() - 1, s.length());
                    Toast.makeText(getApplicationContext(), "60자 이내로 입력해주세요!", Toast.LENGTH_SHORT).show();
                }
                if (s.length() >= 1) {

                    if ((s.charAt(editText.length() - 1) >= 32 && s.charAt(editText.length() - 1) <= 126)) {
                        s.setSpan(new TextAppearanceSpan(getApplicationContext(), R.style.SpecialTextAppearance)
                                , editText.length() - 1, editText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        if ((s.charAt(editText.length() - 1) >= 65 && s.charAt(editText.length() - 1) <= 90)
                                || (s.charAt(editText.length() - 1) >= 97 && s.charAt(editText.length() - 1) <= 122)) {
                            s.setSpan(new TextAppearanceSpan(getApplicationContext(), R.style.SpecialTextAppearance_alpha)
                                    , editText.length() - 1, editText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        }
                    }
                }

            }
        });
    }

    public void showMenu(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        // This activity implements OnMenuItemClickListener
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.draw_menu);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (isChecked) item.setChecked(true);
        else item.setChecked(false);
        switch (item.getItemId()) {
            case R.id.penWidth:
                if (item.isChecked()) {
                    item.setChecked(false);
                    canvas.setPenWidth(false);
                    isChecked = false;
                } else {
                    item.setChecked(true);
                    canvas.setPenWidth(item.isChecked());
                    isChecked = true;
                }
                return true;

            case R.id.pen_RED:
                canvas.setPenColor(true);
                return true;

            case R.id.pen_BLUE:
                canvas.setPenColor(false);
                return true;

            default:
                return false;
        }
    }

}
