package com.example.sharingbookshelf.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
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
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sharingbookshelf.Activities.HomeActivity;
import com.example.sharingbookshelf.Activities.MainActivity;
import com.example.sharingbookshelf.Adapters.OthersBookshelfAdapter;
import com.example.sharingbookshelf.HttpRequest.RetrofitClient;
import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.OtherBookshelfResponse;
import com.example.sharingbookshelf.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static com.example.sharingbookshelf.Activities.HomeActivity.getHasShelfcode;

public class OtherBookshelfFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private OthersBookshelfAdapter mAdapter;
    private ArrayList<OtherBookshelfResponse.OtherShelfData> bookshelfList;
    private Context context;
    private RetrofitServiceApi retrofitServiceApi;
    private ImageButton ib_selectAge;
    private ChipGroup chipGroup;
    private TextView tv_result;
    private Button btn_recommend;
    private Chip chip_age, chip_study, chip_fairy, chip_etc, chip_cartoon;
    private Map<String, ArrayList<Integer>> requestData;
    ArrayList<Integer> ageTag;

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

        btn_recommend = v.findViewById(R.id.btn_recommend);
        chip_age = v.findViewById(R.id.chip_age);
        chip_study = v.findViewById(R.id.chip_study);
        chip_fairy = v.findViewById(R.id.chip_fairy);
        chip_etc = v.findViewById(R.id.chip_etc);
        chip_cartoon = v.findViewById(R.id.chip_cartoon);

        mRecyclerView = v.findViewById(R.id.rv_bookshelves);
        ib_selectAge = v.findViewById(R.id.ib_selectAge);
        chipGroup = v.findViewById(R.id.chipGroup_shelves);
        tv_result = v.findViewById(R.id.tv_result);

        chip_age.setText(HomeActivity.getMyData().getKids().getAge() + "세");
        ageTag = new ArrayList<>();
        ageTag.add(HomeActivity.getMyData().getKids().getAge());
        chip_age.setTag(ageTag);

        recyclerViewSettings();
        getAllShelfView();

        if (getHasShelfcode() == 0) {
            openDialog();
        }

        ib_selectAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectAgeFragment selectAgeFragment = new SelectAgeFragment();
                Fragment targetFragment = getFragmentManager().findFragmentByTag("OtherBookshelfFragment");
                selectAgeFragment.setTargetFragment(targetFragment, 2);
                selectAgeFragment.show(getFragmentManager(), "SelectAgeFragment");
            }
        });

        btn_recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Integer> checkedChipIds = chipGroup.getCheckedChipIds();
                if (checkedChipIds.size() != 0) {
                    // requestData = new HashMap<>();
                    ArrayList<Integer> ageData = new ArrayList<>();
                    ArrayList<Integer> categoryData = new ArrayList<>();
                    for (int i = 0; i < checkedChipIds.size(); i++) {
                        switch (checkedChipIds.get(i)) {
                            case R.id.chip_age:
                                ageData = (ArrayList<Integer>) chip_age.getTag();
                                break;
                            case R.id.chip_study:
                                categoryData.add((Integer.parseInt((String) chip_study.getTag())));
                                break;
                            case R.id.chip_fairy:
                                categoryData.add((Integer.parseInt((String) chip_fairy.getTag())));
                                break;
                            case R.id.chip_cartoon:
                                categoryData.add((Integer.parseInt((String) chip_cartoon.getTag())));
                                break;
                            case R.id.chip_etc:
                                categoryData.add((Integer.parseInt((String) chip_etc.getTag())));
                                break;

                        }
                    }

                    getAllShelfViewFiltered(ageData, categoryData);

                } else {
                    Toast.makeText(getContext(), "하나 이상의 검색조건을 선택해주세요!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }

    private void getAllShelfViewFiltered(ArrayList<Integer> ageData, ArrayList<Integer> categoryData) {
        String newAgeData = ageData.toString().replace(" ", "");
        String newCategoryData = categoryData.toString().replace(" ", "");
        retrofitServiceApi = RetrofitClient.createService(RetrofitServiceApi.class, MainActivity.getJWT());
        Call<OtherBookshelfResponse> call = retrofitServiceApi.getOtherShelfFiltered(newAgeData, newCategoryData);
        call.enqueue(new Callback<OtherBookshelfResponse>() {
            @Override
            public void onResponse(Call<OtherBookshelfResponse> call, Response<OtherBookshelfResponse> response) {
                if (response.body() != null) {
                    Log.d("책장받아오기 테스트", "성공");
                    tv_result.setVisibility(View.GONE);
                    setAllShelfView(response.body().getResult());
                } else {
                    // Toast.makeText(getContext(), "검색 결과가 없습니다!", Toast.LENGTH_SHORT).show();
                    setAllShelfView(null);
                    tv_result.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<OtherBookshelfResponse> call, Throwable t) {
                Log.d("책장받아오기 테스트", "실패");
            }
        });
    }

    private void recyclerViewSettings() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        bookshelfList = new ArrayList<>();
        mAdapter = new OthersBookshelfAdapter(getActivity(), bookshelfList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    ageTag = new ArrayList<>();
                    if (extras.size() == 1) {
                        int selectedAge = extras.getInt("selectedAge");
                        chip_age.setText(selectedAge + "세");
                        ageTag.add(selectedAge);
                        chip_age.setTag(ageTag);
                    } else {
                        int selectedAge1 = extras.getInt("selectedAge1");
                        int selectedAge2 = extras.getInt("selectedAge2");
                        chip_age.setText(selectedAge1 + "세 ~ " + selectedAge2 + "세");

                        ageTag.add(selectedAge1);
                        ageTag.add(selectedAge2);
                        chip_age.setTag(ageTag);
                    }

                }
            }
        }
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