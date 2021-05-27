package com.example.sharingbookshelf.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.sharingbookshelf.Fragments.BookInfoPopupFragment;
import com.example.sharingbookshelf.Fragments.MyBookshelfFragment;
import com.example.sharingbookshelf.Fragments.NoEmptyShelfFragment;
import com.example.sharingbookshelf.HttpRequest.BookApiRetrofitClient;
import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.BookApiResponse;
import com.example.sharingbookshelf.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BarcodeActivity extends AppCompatActivity {

    private RetrofitServiceApi retrofitServiceApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intentIntegratorSettings();
    }

    private void intentIntegratorSettings() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(ZxingActivity.class); //세로모드
        integrator.setOrientationLocked(false);
        integrator.setPrompt("등록할 책의 바코드를 읽어주세요.");
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                finish();
            } else {
                String ISBN = result.getContents();
                Intent intent = new Intent();
                intent.putExtra("ISBN", ISBN);
                setResult(RESULT_OK, intent);
                finish();

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}