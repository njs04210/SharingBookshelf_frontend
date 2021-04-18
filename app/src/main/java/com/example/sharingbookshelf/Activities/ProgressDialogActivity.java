package com.example.sharingbookshelf.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.sharingbookshelf.R;

public class ProgressDialogActivity extends Activity {

    ProgressBar pb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_progressbar);


        task.execute();
    }








