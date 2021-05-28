package com.example.sharingbookshelf.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.example.sharingbookshelf.Activities.MainActivity;
import com.example.sharingbookshelf.HttpRequest.RetrofitClient;
import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.BookreportData;
import com.example.sharingbookshelf.Models.CommonResponse;
import com.example.sharingbookshelf.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckReportPopupFragment extends DialogFragment {

    private ImageView iv_canvas;
    private ImageView iv_thumbnail;
    private Button btn_addReport;
    private FirebaseStorage storage;
    private String file;
    private String contents;
    private String downloadUri;
    private String thumbnailUri;
    private int item_id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_check_report_popup, container, false);

        iv_canvas = v.findViewById(R.id.iv_canvas);
        iv_thumbnail = v.findViewById(R.id.iv_thumbnail);
        btn_addReport = v.findViewById(R.id.btn_addReport);

        Bundle arguments = getArguments();
        Bitmap bitmap = arguments.getParcelable("bitmap");
        file = arguments.getString("file");
        contents = arguments.getString("contents");
        item_id = arguments.getInt("item_id");
        thumbnailUri = arguments.getString("thumbnailUri");

        iv_canvas.setImageBitmap(bitmap);
        Glide.with(this)
                .load(thumbnailUri)
                .fitCenter()
                .placeholder(R.drawable.icon_book2)
                .into(iv_thumbnail);

        btn_addReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasToFirebaseStorage();
            }
        });

        return v;
    }

    private void canvasToFirebaseStorage() {
        storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference mountainImagesRef = storageRef.child("BookReportImg/"
                + MainActivity.getMemId() + "/" + file);

        mountainImagesRef.getName().equals(mountainImagesRef.getName()); //중복 안되게

        // Get the data from an ImageView as bytes
        iv_canvas.setDrawingCacheEnabled(true);
        iv_canvas.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) iv_canvas.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = mountainImagesRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                getDownloadUri();
            }

            private void getDownloadUri() {

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        return mountainImagesRef.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            downloadUri = task.getResult().toString();
                            addReportDataRetrofit();
                        } else {
                            Log.e("Firebase", "canvas 등록한 Uri 받아오기 실패");
                        }
                    }
                });

            }
        });
    }

    private void addReportDataRetrofit() {
        BookreportData.BookreportDetailData bookreportDetailData = new BookreportData.BookreportDetailData();
        bookreportDetailData.setCanvas_uri(downloadUri);
        bookreportDetailData.setContents(contents);
        bookreportDetailData.setItem_id(item_id);

        RetrofitServiceApi retrofitServiceApi = RetrofitClient.createService(RetrofitServiceApi.class, MainActivity.getJWT());
        Call<CommonResponse> call = retrofitServiceApi.addBookReport(bookreportDetailData);
        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                Toast.makeText(getContext(), "독후감 등록 성공!\n독서짱을 노려보세요 +_+"
                        , Toast.LENGTH_LONG).show();

                getFragmentManager().executePendingTransactions();
                getDialog().dismiss();
                getActivity().finish();
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                Log.e(MainActivity.MAIN_TAG, "회원정보 등록 응답 못받음", t);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            Window window = getDialog().getWindow();
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
