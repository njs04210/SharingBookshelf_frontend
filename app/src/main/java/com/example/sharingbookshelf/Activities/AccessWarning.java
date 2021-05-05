package com.example.sharingbookshelf.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sharingbookshelf.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AccessWarning extends AppCompatActivity {

    private BottomNavigationView btnNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_warning_popup);

        Button btn_back = findViewById(R.id.btn_backhome);
        View EmptyView = findViewById(R.id.empty_shelf);
        View NoEmptyView = findViewById(R.id.no_empty_shelf);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccessWarning.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // empty book인지 no empty book인지 검사하고
        // empty book이라면 팝업창 발생

    }
}
