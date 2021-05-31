package com.example.sharingbookshelf.Fragments;

import android.content.Context;
import android.os.Bundle;

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
import android.widget.ImageButton;
import android.widget.SearchView;

import com.example.sharingbookshelf.Activities.HomeActivity;
import com.example.sharingbookshelf.Activities.MainActivity;
import com.example.sharingbookshelf.Adapters.OthersBookshelfAdapter;
import com.example.sharingbookshelf.HttpRequest.RetrofitClient;
import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.OtherBookshelfResponse;
import com.example.sharingbookshelf.R;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sharingbookshelf.Activities.HomeActivity.getHasShelfcode;

public class OtherBookshelfFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private OthersBookshelfAdapter mAdapter;
    private ArrayList<OtherBookshelfResponse.OtherShelfData> bookshelfList;
    private Context context;
    private RetrofitServiceApi retrofitServiceApi;
    private ImageButton ib_selectAge;
    private Chip chip_age;

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

        chip_age = v.findViewById(R.id.chip_age);
        mRecyclerView = v.findViewById(R.id.rv_bookshelves);
        ib_selectAge = v.findViewById(R.id.ib_selectAge);

        chip_age.setText(HomeActivity.getMyData().getKids().getAge() + "세");
        recyclerViewSettings();
        getAllShelfView();

        if (getHasShelfcode() == 0) {
            openDialog();
        }

        ib_selectAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectAgeFragment selectAgeFragment = new SelectAgeFragment();
               // getFragmentManager().executePendingTransactions();
                Fragment targetFragment = getFragmentManager().findFragmentByTag("OtherBookshelfFragment");
                selectAgeFragment.setTargetFragment(targetFragment, 2);
                selectAgeFragment.show(getFragmentManager(), "SelectAgeFragment");
            }
        });

        return v;
    }

    private void recyclerViewSettings() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        bookshelfList = new ArrayList<>();
        mAdapter = new OthersBookshelfAdapter(getActivity(), bookshelfList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void getAllShelfView() {
        retrofitServiceApi = RetrofitClient.createService(RetrofitServiceApi.class, MainActivity.getJWT());
        Call<OtherBookshelfResponse> call = retrofitServiceApi.getOtherShelf();
        call.enqueue(new Callback<OtherBookshelfResponse>() {
            @Override
            public void onResponse(Call<OtherBookshelfResponse> call, Response<OtherBookshelfResponse> response) {
                Log.d("책장받아오기 테스트", "성공");
                setAllShelfView(response.body().getResult());
            }

            @Override
            public void onFailure(Call<OtherBookshelfResponse> call, Throwable t) {
                Log.d("책장받아오기 테스트", "실패");
            }
        });
    }

    private void setAllShelfView(ArrayList<OtherBookshelfResponse.OtherShelfData> result) {

        mAdapter = new OthersBookshelfAdapter(getActivity(), result);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);

        this.bookshelfList = result;

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