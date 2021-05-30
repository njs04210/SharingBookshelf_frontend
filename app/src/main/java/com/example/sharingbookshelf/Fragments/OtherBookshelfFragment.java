package com.example.sharingbookshelf.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import com.example.sharingbookshelf.Activities.HomeActivity;
import com.example.sharingbookshelf.Activities.MainActivity;
import com.example.sharingbookshelf.Adapters.OthersBookshelfAdapter;
import com.example.sharingbookshelf.HttpRequest.RetrofitClient;
import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.BookData;
import com.example.sharingbookshelf.Models.BookshelfInfoData;
import com.example.sharingbookshelf.Models.GetUserInfoResponse;
import com.example.sharingbookshelf.Models.OtherBookshelfResponse;
import com.example.sharingbookshelf.R;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sharingbookshelf.Activities.HomeActivity.getHasShelfcode;

public class OtherBookshelfFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private OthersBookshelfAdapter mAdapter;
    private ArrayList<BookshelfInfoData> bookshelfList;
    private ArrayList<BookData> bookList;
    private Context context;
    private RetrofitServiceApi retrofitServiceApi;

    public ArrayList<BookshelfInfoData> getBookshelfList() {
        return bookshelfList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_otherbookshelf, container, false);

        context = container.getContext();

        mRecyclerView = v.findViewById(R.id.rv_bookshelves);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new OthersBookshelfAdapter(bookshelfList);
        mRecyclerView.setAdapter(mAdapter);

        getAllShelfView();

        if (getHasShelfcode() == 0) {
            openDialog();
        }

        return v;
    }

    private void getAllShelfView() {
        retrofitServiceApi = RetrofitClient.createService(RetrofitServiceApi.class, MainActivity.getJWT());
        Call<OtherBookshelfResponse> call = retrofitServiceApi.getOtherShelf();
        call.enqueue(new Callback<OtherBookshelfResponse>() {
            @Override
            public void onResponse(Call<OtherBookshelfResponse> call, Response<OtherBookshelfResponse> response) {
                Log.d("책장받아오기 테스트", "성공");

                List<OtherBookshelfResponse.OtherShelfData> result = response.body().getResult();
                bookshelfList = new ArrayList<>();

                for (int i = 0; i < result.size(); i++) {
                    BookshelfInfoData bookshelfInfoData = new BookshelfInfoData();
                    GetUserInfoResponse member = result.get(i).getMember();
                    List<BookData> hasBookList = result.get(i).getHasBookList();

                    bookshelfInfoData.setNickname(member.getNickname());
                    bookshelfInfoData.setProfile(member.getPhotoURL());
                    bookList = new ArrayList<>();
                    for (int j = 0; j < hasBookList.size(); j++) {
                        //책정보 세팅
                        BookData bookData = new BookData();
                        bookData.setThumbnail(hasBookList.get(j).getThumbnail());
                        bookList.add(bookData);
                    }

                    //책장에 책리스트 넣기
                    bookshelfInfoData.setBookList(bookList);
                    //책장목록에 책 넣기
                    bookshelfList.add(bookshelfInfoData);

                }
                mAdapter = new OthersBookshelfAdapter(bookshelfList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<OtherBookshelfResponse> call, Throwable t) {
                Log.d("책장받아오기 테스트", "실패");
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.top_app_bar, menu);
        MenuItem item = menu.findItem(R.id.search);
        //SearchView searchView = new SearchView(((HomeActivity) context).getSupportActionBar().getThemedContext());
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItem.SHOW_AS_ACTION_IF_ROOM);
        SearchView searchView = (SearchView) item.getActionView();
        // These lines are deprecated in API 26 use instead
        item.setActionView(searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {

                                          }
                                      }
        );
    }

    public void openDialog() {
        AccessWarningFragment accessWarningFragment = new AccessWarningFragment();
        accessWarningFragment.setCancelable(false);
        accessWarningFragment.show(
                getChildFragmentManager(), "abc");
    }
}