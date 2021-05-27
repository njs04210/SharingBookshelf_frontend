package com.example.sharingbookshelf.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

public class FilterCategoryFragment extends DialogFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_filter_category, container, false);

        Button button = v.findViewById(R.id.btn_setCategory_f);
        ChipGroup chipGroup = v.findViewById(R.id.chipGroup_category_f);
        Chip allChip = v.findViewById(R.id.chip_all);
        Chip studyChip = v.findViewById(R.id.chip_study_f);
        Chip fairyChip = v.findViewById(R.id.chip_fairy_f);
        Chip cartoonChip = v.findViewById(R.id.chip_cartoon_f);
        Chip etcChip = v.findViewById(R.id.chip_etc_f);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedChipId = chipGroup.getCheckedChipId();
                if (checkedChipId != -1) {

                    Intent intent = new Intent();
                    BookInfoPopupFragment bookInfoPopupFragment = BookInfoPopupFragment.getInstance();
                    String category = null;

                    switch (checkedChipId) {

                        case R.id.chip_all:
                            category = allChip.getText().toString();
                            intent.putExtra("category", category);
                            break;

                        case R.id.chip_study_f:
                            category = studyChip.getText().toString();
                            intent.putExtra("category", category);
                            break;

                        case R.id.chip_fairy_f:
                            category = fairyChip.getText().toString();
                            intent.putExtra("category", category);
                            break;

                        case R.id.chip_cartoon_f:
                            category = cartoonChip.getText().toString();
                            intent.putExtra("category", category);
                            break;

                        case R.id.chip_etc_f:
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
        if (checkedChipId == R.id.chip_all) return 1;
        if (checkedChipId == R.id.chip_study_f) return 2;
        if (checkedChipId == R.id.chip_fairy_f) return 3;
        if (checkedChipId == R.id.chip_cartoon_f) return 4;
        if (checkedChipId == R.id.chip_etc_f) return 4;

        else return 0;
    }

}
