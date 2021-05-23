package com.example.sharingbookshelf.Fragments;

import android.graphics.Color;
import android.graphics.Insets;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.LeadingMarginSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;

import com.example.sharingbookshelf.R;

import java.util.regex.Pattern;

public class CreateBookReportFragment extends DialogFragment {

    private EditText editText;

    public static CreateBookReportFragment getInstance() {
        CreateBookReportFragment createBookReportFragment = new CreateBookReportFragment();
        return createBookReportFragment;
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
        View v = inflater.inflate(R.layout.fragment_create_bookreport, container, false);
        editText = v.findViewById(R.id.et_paper);
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
                    Toast.makeText(getContext(), "60자 이내로 입력해주세요!", Toast.LENGTH_SHORT).show();
                }
                if (s.length() >= 1) {
                    /*if ((s.charAt(editText.length() - 1) >= 32 && s.charAt(editText.length() - 1) <= 47)
                            || (s.charAt(editText.length() - 1) >= 58 && s.charAt(editText.length() - 1) <= 64)
                            || (s.charAt(editText.length() - 1) >= 91 && s.charAt(editText.length() - 1) <= 96)
                            || (s.charAt(editText.length() - 1) >= 123 && s.charAt(editText.length() - 1) <= 126)) {
                        s.setSpan(new TextAppearanceSpan(getContext(), R.style.SpecialTextAppearance), editText.length() - 1, editText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                    s c = editText.getText().charAt(editText.length() - 1);
                    }*/
                    if ((s.charAt(editText.length() - 1) >= 32 && s.charAt(editText.length() - 1) <= 126)) {
                        s.setSpan(new TextAppearanceSpan(getContext(), R.style.SpecialTextAppearance), editText.length() - 1, editText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                    s c = editText.getText().charAt(editText.length() - 1);
                        if ((s.charAt(editText.length() - 1) >= 65 && s.charAt(editText.length() - 1) <= 90)
                                || (s.charAt(editText.length() - 1) >= 97 && s.charAt(editText.length() - 1) <= 122)) {
                            s.setSpan(new TextAppearanceSpan(getContext(), R.style.SpecialTextAppearance_alpha), editText.length() - 1, editText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        }
                    }
                }

            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

//      dialog fragment custom width
        try {
            WindowMetrics windowMetrics = getActivity().getWindowManager().getCurrentWindowMetrics();
            Insets insets = windowMetrics.getWindowInsets().getInsetsIgnoringVisibility(
                    WindowInsets.Type.systemBars());

            Window window = getDialog().getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
            getDialog().getWindow().setAttributes(params);
        } catch (Exception e) {
            // regardless
            e.printStackTrace();
        }
    }
}
