//package com.example.sharingbookshelf.Fragments;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.DialogFragment;
//import android.app.DatePickerDialog;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.TextView;
//import com.example.sharingbookshelf.R;
//import java.util.Calendar;
//
//public class ChallengePopupFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
//    public static final String TAG_EVENT_DIALOG = "dialog event";
//
//    private static ChallengePopupFragment challengePopupFragment = null;
//    private TextView tv_name, tv_cal;
//    private EditText messageField, goalField;
//    private Calendar cal;
//    private Button btn_ok;
//
//    public static ChallengePopupFragment getInstance() {
//        if (challengePopupFragment == null) {
//            challengePopupFragment = new ChallengePopupFragment();
//        }
//        return challengePopupFragment;
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        this.InitializeListener();
//    }
//
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.fragment_challenge_popup, container, false);
//        cal = Calendar.getInstance();
//        tv_cal.setText(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE));
//        tv_name = v.findViewById(R.id.challenge_name);
//        goalField = v.findViewById(R.id.goal_text);
//        messageField = v.findViewById(R.id.leave_text);
//        btn_ok = v.findViewById(R.id.btn_request);
//
//        tv_cal = v.findViewById(R.id.challenge_calender);
//        tv_cal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                /* DATE Picker가 처음 떴을 때, 오늘 날짜가 보이도록 설정. */
//                new DatePickerDialog(getActivity(), mDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
//                        cal.get(Calendar.DATE)).show();
//            }
//        });
//        return v;
//    }
//
//    DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//            tv_cal.setText(String.format("%d-%d-%d", year, month + 1, dayOfMonth));
//        }
//    }
//
//    @Override
//    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//
//    }
//
////    @Override
////    public void onClick(View v) {
////
////    }
//
//
//}