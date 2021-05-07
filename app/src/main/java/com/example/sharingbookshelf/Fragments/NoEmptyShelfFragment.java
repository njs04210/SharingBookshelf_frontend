package com.example.sharingbookshelf.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.CallSuper;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListPopupWindow;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.sharingbookshelf.Activities.BarcodeActivity;
import com.example.sharingbookshelf.Activities.BookInfoPopupActivity;
import com.example.sharingbookshelf.Activities.MainActivity;
import com.example.sharingbookshelf.Activities.SelfAddBookPopupActivity;
import com.example.sharingbookshelf.Adapters.MyBookshelfAdapter;
import com.example.sharingbookshelf.HttpRequest.BookApiRetrofitClient;
import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.BookApiResponse;
import com.example.sharingbookshelf.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class NoEmptyShelfFragment extends Fragment {

    private static final int BARCODE_ACTIVITY = 10000;
    private static final int ADDSELF_ACTIVITY = 10001;

    private String[] popUpList = {"바코드 스캔", "ISBN 직접 입력", "세부 등록"};
    private FloatingActionButton fab_addBook;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private RetrofitServiceApi retrofitServiceApi;
    private List<Map<String, Object>> myDataset;
    private ArrayList<String> thumbnailSet = new ArrayList<>();

    public NoEmptyShelfFragment(ArrayList<Map<String, Object>> myDataset) {
        this.myDataset = myDataset;
        if (myDataset != null) {
            getThumbnail(myDataset);
        }
    }

    private void getThumbnail(ArrayList<Map<String, Object>> myDataset) {
        for (int i = 0; i < myDataset.size(); i++) {
            thumbnailSet.add((String) myDataset.get(i).get("thumbnail"));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_no_empty_shelf, container, false);

        //btn_addBook = v.findViewById(R.id.btn_AddBook);
        fab_addBook = v.findViewById(R.id.floating_action_button);
        mRecyclerView = v.findViewById(R.id.rcv_myBookShelf);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(getActivity(), 3);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyBookshelfAdapter(thumbnailSet);
        mRecyclerView.setAdapter(mAdapter);

        ListPopupWindow listPopupWindow = new ListPopupWindow(getActivity());
        listPopupWindow.setAnchorView(fab_addBook);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity()
                , R.layout.list_popup_window_item, popUpList);
        listPopupWindow.setAdapter(arrayAdapter);
        listPopupWindow.setContentWidth(measureContentWidth(arrayAdapter));
        listPopupWindow.setModal(true); //선택해도 자동으로 팝업 안닫히게
        listPopupWindow.setHorizontalOffset(-200);
        listPopupWindow.setVerticalOffset(100);

        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(getActivity(), BarcodeActivity.class);
                    getActivity().startActivityForResult(intent, BARCODE_ACTIVITY);
                }
                if (position == 1) {
                    Intent intent = new Intent(getActivity(), SelfAddBookPopupActivity.class);
                    getActivity().startActivityForResult(intent, ADDSELF_ACTIVITY);
                }
                if (position == 2) {
                    Toast.makeText(getActivity(), "수동으로 정보 입력", Toast.LENGTH_SHORT);
                }
            }

        });

        fab_addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listPopupWindow.show();
            }
        });

        return v;
    }

    private int measureContentWidth(ListAdapter listAdapter) {
        ViewGroup mMeasureParent = null;
        int maxWidth = 0;
        View itemView = null;
        int itemType = 0;

        final ListAdapter adapter = listAdapter;
        final int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int count = adapter.getCount();
        for (int i = 0; i < count; i++) {
            final int positionType = adapter.getItemViewType(i);
            if (positionType != itemType) {
                itemType = positionType;
                itemView = null;
            }

            if (mMeasureParent == null) {
                mMeasureParent = new FrameLayout(getActivity());
            }

            itemView = adapter.getView(i, itemView, mMeasureParent);
            itemView.measure(widthMeasureSpec, heightMeasureSpec);

            final int itemWidth = itemView.getMeasuredWidth();

            if (itemWidth > maxWidth) {
                maxWidth = itemWidth;
            }
        }

        return maxWidth;
    }


    @Override
    @CallSuper
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == BARCODE_ACTIVITY) { //바코드 인식 결과
            if (resultCode == RESULT_OK) {
                String data = intent.getExtras().getString("ISBN");
                if (data != null) {
                    Log.d(MainActivity.MAIN_TAG, data);
                    callBookResponse(data);
                }
            }
        }
        if (requestCode == ADDSELF_ACTIVITY) { //직접 추가 결과
            if (resultCode == RESULT_OK) {
                String data = intent.getExtras().getString("ISBN");
                if (data != null) {
                    Log.d(MainActivity.MAIN_TAG, data);
                    callBookResponse(data);
                }
            }
        }
    }

    /* Kakao Book search API 통신 */
    private void callBookResponse(String ISBN) {
        retrofitServiceApi = BookApiRetrofitClient.createService(RetrofitServiceApi.class);
        Call<BookApiResponse> call = retrofitServiceApi.setBookApiResponse(ISBN, "isbn");
        call.enqueue(new Callback<BookApiResponse>() {
            @Override
            public void onResponse(Call<BookApiResponse> call, Response<BookApiResponse> response) {
                BookApiResponse result = response.body();
                Log.d(MainActivity.MAIN_TAG, "책 api 통신 성공");
                if (result != null) {
                    getBookDetails(result);
                }
            }

            @Override
            public void onFailure(Call<BookApiResponse> call, Throwable t) {
                Log.e(MainActivity.MAIN_TAG, "책 api 통신 실패", t);
            }
        });
    }

    private void getBookDetails(BookApiResponse books) {
        ArrayList<BookApiResponse.Document> documentList = books.documents;
        BookApiResponse.Meta meta = books.metas;
        Intent intent = new Intent(getActivity(), BookInfoPopupActivity.class);
        intent.putExtra("documentList", documentList);
        intent.putExtra("meta", meta);
        intent.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
        startActivity(intent);
    }
}