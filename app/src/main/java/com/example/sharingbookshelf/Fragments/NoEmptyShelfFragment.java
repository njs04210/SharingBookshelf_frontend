package com.example.sharingbookshelf.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListPopupWindow;
import android.widget.Toast;

import com.example.sharingbookshelf.Activities.BarcodeActivity;
import com.example.sharingbookshelf.Activities.MainActivity;
import com.example.sharingbookshelf.Adapters.MyBookshelfAdapter;
import com.example.sharingbookshelf.HttpRequest.BookApiRetrofitClient;
import com.example.sharingbookshelf.HttpRequest.RetrofitClient;
import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.BookApiResponse;
import com.example.sharingbookshelf.Models.GetShelfStatusResponse;
import com.example.sharingbookshelf.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class NoEmptyShelfFragment extends Fragment {

    public static final int BARCODE_ACTIVITY = 10000;
    public static final int SELFADD_FRAGMENT = 10001;

    private String[] popUpList = {"바코드 스캔", "ISBN 직접 입력", "세부 등록"};
    private FloatingActionButton fab_addBook;
    private ImageButton iv_categorySelect;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private RetrofitServiceApi retrofitServiceApi;
    private ArrayList<Map<String, Object>> thumbnailSet;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_no_empty_shelf, container, false);

        context = container.getContext();

        fab_addBook = v.findViewById(R.id.floating_action_button);

        iv_categorySelect = v.findViewById(R.id.iv_selectCategory);

        mRecyclerView = v.findViewById(R.id.rcv_myBookShelf);

        recyclerViewSettings();
        ListPopupWindow listPopupWindow = getListPopupWindow();
        setShelfView(MainActivity.getMemId()); // 책장 채워넣기

        fab_addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listPopupWindow.show();
            }
        });

        iv_categorySelect.setOnClickListener(new View.OnClickListener() { //필터 버튼 액티비티
            public void onClick(View v) {
                FilterCategoryFragment filterCategoryFragment = new FilterCategoryFragment();
                filterCategoryFragment.show(getFragmentManager(), "FilterCategoryFragment");

            }
        });

        return v;
    }

    private void recyclerViewSettings() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getActivity(), 3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        thumbnailSet = new ArrayList<>();
        mAdapter = new MyBookshelfAdapter(thumbnailSet);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void setShelfView(int memId) {
        retrofitServiceApi = RetrofitClient.createService(RetrofitServiceApi.class, MainActivity.getJWT());
        Call<GetShelfStatusResponse> call = retrofitServiceApi.getShelfStatus(memId);
        call.enqueue(new Callback<GetShelfStatusResponse>() {
            @Override
            public void onResponse(Call<GetShelfStatusResponse> call, Response<GetShelfStatusResponse> response) {

                String msg = response.body().getMsg();
                Log.d(MainActivity.MAIN_TAG, msg);
                getThumbnail(response.body().getHasBooks());

            }

            @Override
            public void onFailure(Call<GetShelfStatusResponse> call, Throwable t) {
                Log.e(MainActivity.MAIN_TAG, "GetstatusCode 받아오기 실패", t);
            }
        });
    }

    private void getThumbnail(ArrayList<Map<String, Object>> myDataset) {

        mAdapter = new MyBookshelfAdapter(myDataset);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);

        this.thumbnailSet = myDataset;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == BARCODE_ACTIVITY || requestCode == SELFADD_FRAGMENT) {
            if (resultCode == RESULT_OK) {
                String data = intent.getExtras().getString("ISBN");
                if (data != null) {
                    Log.d(MainActivity.MAIN_TAG, data);
                    callBookResponse(data);
                }
            }
        }
    }

    // Kakao Book search API 통신
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
        BookInfoPopupFragment bookInfoPopupFragment = new BookInfoPopupFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("documentList", documentList);
        bundle.putSerializable("meta", meta);

        bookInfoPopupFragment.setArguments(bundle);
        bookInfoPopupFragment.show(getChildFragmentManager()
                , "BookInfoPopupFragment");
    }

    private ListPopupWindow getListPopupWindow() {
        ListPopupWindow listPopupWindow = new ListPopupWindow(getActivity());
        listPopupWindow.setAnchorView(fab_addBook);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity()
                , R.layout.list_popup_window_item, popUpList);

        listPopupWindow.setAdapter(arrayAdapter);
        listPopupWindow.setContentWidth(measureContentWidth(arrayAdapter));
        //listPopupWindow.setModal(true); //선택해도 자동으로 팝업 안닫히게
        listPopupWindow.setHorizontalOffset(-230);
        listPopupWindow.setVerticalOffset(100);
        listPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(getActivity(), BarcodeActivity.class);
                    startActivityForResult(intent, BARCODE_ACTIVITY);
                    listPopupWindow.dismiss();
                }
                if (position == 1) {
                    SelfAddBookPopupFragment selfAddBookPopupFragment = new SelfAddBookPopupFragment();
                    getFragmentManager().executePendingTransactions();
                    Fragment targetFragment = getFragmentManager().findFragmentByTag("NoEmptyShelfFragment");
                    selfAddBookPopupFragment.setTargetFragment(targetFragment, SELFADD_FRAGMENT);
                    selfAddBookPopupFragment.show(getFragmentManager(), "SelfAddBookPopupFragment");
                    listPopupWindow.dismiss();
                }
                if (position == 2) {
                    Toast.makeText(getActivity(), "수동으로 정보 입력", Toast.LENGTH_SHORT);
                    listPopupWindow.dismiss();
                }
            }

        });
        return listPopupWindow;
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

}