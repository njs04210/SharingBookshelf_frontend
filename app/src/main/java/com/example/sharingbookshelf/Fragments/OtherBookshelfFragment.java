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
import com.example.sharingbookshelf.Models.BookData;
import com.example.sharingbookshelf.Models.BookshelfInfoData;
import com.example.sharingbookshelf.R;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

import static com.example.sharingbookshelf.Activities.HomeActivity.getHasShelfcode;

public class OtherBookshelfFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private OthersBookshelfAdapter mAdapter;
    private ArrayList<BookshelfInfoData> bookshelfList = new ArrayList<>();
    private ArrayList<BookData> bookList;
    private Context context;

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

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        setBookshelf();

        if (getHasShelfcode() == 0) {
            openDialog();
        }

        return v;
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

    private void setBookshelf() {
        for (int i = 1; i < 10; i++) {
            //회원 책장 객체 생성
            BookshelfInfoData bookshelfInfoData = new BookshelfInfoData();
            bookshelfInfoData.setNickname("회원 " + i);
            bookshelfInfoData.setProfile(R.drawable.icon_logo);

            // 책장 속 책 받을 arraylist 객체 생성
            bookList = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                //책정보 세팅
                BookData bookData = new BookData();
                bookData.setThumbnail("http://image.kyobobook.co.kr/images/book/xlarge/923/x9791164137923.jpg");
                bookList.add(bookData);
            }

            //책장에 책리스트 넣기
            bookshelfInfoData.setBookList(bookList);
            //책장목록에 책 넣기
            bookshelfList.add(bookshelfInfoData);
        }
        mAdapter.notifyDataSetChanged();
    }

    public void openDialog() {
        AccessWarningFragment accessWarningFragment = new AccessWarningFragment();
        accessWarningFragment.setCancelable(false);
        accessWarningFragment.show(
                getChildFragmentManager(), "abc");
    }
}