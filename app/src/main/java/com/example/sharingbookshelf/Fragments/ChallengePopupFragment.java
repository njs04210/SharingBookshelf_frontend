package com.example.sharingbookshelf.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import com.example.sharingbookshelf.R;
import java.util.Calendar;

public class ChallengePopupFragment extends DialogFragment {

    public static final String TAG_EVENT_DIALOG = "dialog event";

    private TextView tv_name, tv_cal;
    private EditText messageField, goalField;
    private Calendar cal;
    private Button btn_ok;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_challenge_popup, container, false);
        tv_cal = v.findViewById(R.id.challenge_calender);
        cal = Calendar.getInstance();
        tv_cal.setText(cal.get(Calendar.YEAR) +"-"+ (cal.get(Calendar.MONTH)+1) +"-"+ cal.get(Calendar.DATE));
        tv_cal.setOnClickListener(this::mOnClick_DatePick);
        tv_name=v.findViewById(R.id.challenge_name);
        goalField = v.findViewById(R.id.goal_text);
        messageField = v.findViewById(R.id.leave_text);
        btn_ok = v.findViewById(R.id.btn_request);
        return v;
    }
    DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
            // Date Picker에서 선택한 날짜를 TextView에 설정
            tv_cal.setText(String.format("%d-%d-%d", yy, mm + 1, dd));
        }
    };
    public void mOnClick_DatePick(View view){
        // DATE Picker가 처음 떴을 때, 오늘 날짜가 보이도록 설정.
        new DatePickerDialog(getActivity(), mDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                cal.get(Calendar.DATE)).show();
    }
}