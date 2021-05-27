package com.example.sharingbookshelf.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.sharingbookshelf.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import static android.app.Activity.RESULT_OK;

public class SelectCategoryFragment extends DialogFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_select_category, container, false);

        Button button = v.findViewById(R.id.btn_setCategory);
        ChipGroup chipGroup = v.findViewById(R.id.chipGroup_category);
        Chip studyChip = v.findViewById(R.id.chip_study);
        Chip fairyChip = v.findViewById(R.id.chip_fairy);
        Chip cartoonChip = v.findViewById(R.id.chip_cartoon);
        Chip etcChip = v.findViewById(R.id.chip_etc);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedChipId = chipGroup.getCheckedChipId();
                if (checkedChipId != -1) {

                    Intent intent = new Intent();
                    BookInfoPopupFragment bookInfoPopupFragment = BookInfoPopupFragment.getInstance();
                    String category = null;

                    switch (checkedChipId) {
                        case R.id.chip_study:
                            category = studyChip.getText().toString();
                            intent.putExtra("category", category);
                            break;

                        case R.id.chip_fairy:
                            category = fairyChip.getText().toString();
                            intent.putExtra("category", category);
                            break;

                        case R.id.chip_cartoon:
                            category = cartoonChip.getText().toString();
                            intent.putExtra("category", category);
                            break;

                        case R.id.chip_etc:
                            category = etcChip.getText().toString();
                            intent.putExtra("category", category);
                            break;
                    }

                    int categoryNum = classifyCategory(checkedChipId);
                    intent.putExtra("categoryNum", categoryNum);
                    getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
                    dismiss();

                } else {
                    Toast.makeText(getContext(), "장르를 선택해주세요!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }

    private int classifyCategory(int checkedChipId) {
        if (checkedChipId == R.id.chip_study) return 1;
        if (checkedChipId == R.id.chip_fairy) return 2;
        if (checkedChipId == R.id.chip_cartoon) return 3;
        if (checkedChipId == R.id.chip_etc) return 4;
        else return 0;
    }

    @Override
    public void onResume() {
        super.onResume();

        try {
            WindowMetrics windowMetrics = getActivity().getWindowManager().getCurrentWindowMetrics();

            Window window = getDialog().getWindow();
            window.setGravity(Gravity.BOTTOM);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
            params.width = windowMetrics.getBounds().width();
            params.horizontalMargin = 0.0f;
            getDialog().getWindow().setAttributes(params);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
