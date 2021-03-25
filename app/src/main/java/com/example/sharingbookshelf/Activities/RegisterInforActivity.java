package com.example.sharingbookshelf.Activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sharingbookshelf.R;

public class RegisterInforActivity extends AppCompatActivity {

    final CharSequence[] oItems = {"하나", "둘", "셋", "넷", "다셋"};


    AlertDialog.Builder oDialog = new AlertDialog.Builder(this,
            android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);

    oDialog.setTitle("색상을 선택하세요")
        .setSingleChoiceItems(oItems, -1, new DialogInterface.OnClickListener()
    {
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            nSelectItem = which;
        }
    })
            .setNeutralButton("선택", new DialogInterface.OnClickListener()
    {
        public void onClick(DialogInterface dialog, int which)
        {
            if (which >= 0)
                Toast.makeText(getApplicationContext(),
                        oItems[nSelectItem], Toast.LENGTH_LONG).show();
        }
    })
            .setCancelable(false).show();

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_reginfor);
//    }

    // 최초 로그인 후 register infor 화면으로 이동

    // 카메라로 전환

    // 홈 화면으로 전환
}
