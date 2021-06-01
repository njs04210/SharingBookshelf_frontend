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
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;

import com.example.sharingbookshelf.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class SelectAgeFragment extends DialogFragment implements CompoundButton.OnCheckedChangeListener {

    private AppCompatButton btn_apply;
    private Chip age_1, age_2, age_3, age_4, age_5, age_6, age_7, age_8, age_9, age_10, age_11, age_12, age_13;
    private ChipGroup chipGroup;
    private TextView tv_selectedAge;
    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_select_age, container, false);

        this.view = v;

        btn_apply = v.findViewById(R.id.btn_apply);
        chipGroup = v.findViewById(R.id.chipGroup_age);
        tv_selectedAge = v.findViewById(R.id.tv_selectedAge);
        age_1 = v.findViewById(R.id.age_1);
        age_2 = v.findViewById(R.id.age_2);
        age_3 = v.findViewById(R.id.age_3);
        age_4 = v.findViewById(R.id.age_4);
        age_5 = v.findViewById(R.id.age_5);
        age_6 = v.findViewById(R.id.age_6);
        age_7 = v.findViewById(R.id.age_7);
        age_8 = v.findViewById(R.id.age_8);
        age_9 = v.findViewById(R.id.age_9);
        age_10 = v.findViewById(R.id.age_10);
        age_11 = v.findViewById(R.id.age_11);
        age_12 = v.findViewById(R.id.age_12);
        age_13 = v.findViewById(R.id.age_13);

        age_1.setOnCheckedChangeListener(this);
        age_2.setOnCheckedChangeListener(this);
        age_3.setOnCheckedChangeListener(this);
        age_4.setOnCheckedChangeListener(this);
        age_5.setOnCheckedChangeListener(this);
        age_6.setOnCheckedChangeListener(this);
        age_7.setOnCheckedChangeListener(this);
        age_8.setOnCheckedChangeListener(this);
        age_9.setOnCheckedChangeListener(this);
        age_10.setOnCheckedChangeListener(this);
        age_11.setOnCheckedChangeListener(this);
        age_12.setOnCheckedChangeListener(this);
        age_13.setOnCheckedChangeListener(this);

        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Integer> checkedChipIds = chipGroup.getCheckedChipIds();
                Intent intent = new Intent();
                if (checkedChipIds.size() != 0) {
                    if (checkedChipIds.size() == 1) {
                        int age = Integer.parseInt((String) view.findViewById(checkedChipIds.get(0)).getTag());
                        intent.putExtra("selectedAge", age);
                    } else if (checkedChipIds.size() == 2) {
                        int a = Integer.parseInt((String) view.findViewById(checkedChipIds.get(0)).getTag());
                        int b = Integer.parseInt((String) view.findViewById(checkedChipIds.get(1)).getTag());
                        int age1, age2;
                        if (a < b) {
                            age1 = a;
                            age2 = b;
                        } else {
                            age1 = b;
                            age2 = a;
                        }
                        intent.putExtra("selectedAge1", age1);
                        intent.putExtra("selectedAge2", age2);
                    }
                    getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
                    dismiss();
                } else {
                    Toast.makeText(getContext(), "나이를 설정해주세요!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        List<Integer> ids = chipGroup.getCheckedChipIds();
        if (isChecked) {

            if (ids.size() > 2) {
                buttonView.setChecked(false);  //force to unchecked the chip
            } else if (ids.size() == 1) {
                int age = Integer.parseInt((String) view.findViewById(ids.get(0)).getTag());
                tv_selectedAge.setText(age + "세");
            } else {
                int first = Integer.parseInt((String) view.findViewById(ids.get(0)).getTag());
                int second = Integer.parseInt((String) view.findViewById(ids.get(1)).getTag());
                int a, b;
                if (first < second) {
                    a = first;
                    b = second;
                } else {
                    a = second;
                    b = first;
                }
                tv_selectedAge.setText(a + "세 ~ " + b + "세");

            }
        } else {
            if (ids.size() > 0) {
                int age = Integer.parseInt((String) view.findViewById(ids.get(0)).getTag());
                tv_selectedAge.setText(age + "세");
            }
        }
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
