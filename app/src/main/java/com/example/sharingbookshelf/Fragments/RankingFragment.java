package com.example.sharingbookshelf.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.sharingbookshelf.Activities.MainActivity;
import com.example.sharingbookshelf.Adapters.RankingAdapter;
import com.example.sharingbookshelf.HttpRequest.RetrofitClient;
import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.BookData;
import com.example.sharingbookshelf.Models.RankingData;
import com.example.sharingbookshelf.Models.RankingResponse;
import com.example.sharingbookshelf.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sharingbookshelf.Activities.HomeActivity.getHasShelfcode;

public class RankingFragment extends Fragment {


    private TabLayout mtabLayout;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking, container, false);

        context = container.getContext();

        mtabLayout = view.findViewById(R.id.tabLayout);

        getFragmentManager().beginTransaction()
                .replace(R.id.ranking, new BookRankingFragment(), "BookRankingFragment").commit();

        mtabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.ranking, new BookRankingFragment(), "BookRankingFragment").commit();
                } else if (position == 1) {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.ranking, new ReportRankingFragment(), "ReportRankingFragment").commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }
}