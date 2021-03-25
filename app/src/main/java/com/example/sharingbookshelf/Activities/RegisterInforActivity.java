package com.example.sharingbookshelf.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sharingbookshelf.R;

public class RegisterInforActivity extends Activity {

    Button btn_age, btn_camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reginfor);
        btn_age = (Button)findViewById(R.id.reg_childage);
        btn_age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showDialog(1);
            }
        });

        // 카메라로 전환
        btn_camera = (Button)findViewById(R.id.camera_button);
        btn_camera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TakingPhotoActivity.class); //1번 파라미터 : 메인 액티비티 자신, 2번 : 호출할 클래스
                startActivity(intent); //intent => Activity끼리 서로 호출하기 위해서 필요한 통신장치.
            }
        });

        // 홈 화면으로 전환
    }

    // 자녀 나이 버튼 선택하면 선택하는 팝업
    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        final String [] items = {"사과", "딸기", "참외", "바나나", "두리안"};
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterInforActivity.this);
        builder.setTitle("자녀 나이를 선택하세요");
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Toast.makeText(RegisterInforActivity.this, items[which], Toast.LENGTH_SHORT).show();
                dialog.dismiss(); // 누르면 바로 닫히는 형태
            }
        });
        return builder.create();
    }


}



//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_reginfor);
//    }

    // 최초 로그인 후 register infor 화면으로 이동

//}
