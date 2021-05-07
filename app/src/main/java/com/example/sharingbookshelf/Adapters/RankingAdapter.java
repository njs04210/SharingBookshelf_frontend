package com.example.sharingbookshelf.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingbookshelf.Models.RankingData;
import com.example.sharingbookshelf.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.RankingViewHolder> {

    private ArrayList<RankingData> rankingList;

    public static class RankingViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_grade;
        private final TextView tv_booktitle;

        public RankingViewHolder(@NonNull View view) {
            super(view);
            tv_grade = (TextView) view.findViewById(R.id.tv_grade);
            tv_booktitle = (TextView) view.findViewById(R.id.tv_booktitle);
        }
    }

    public RankingAdapter(ArrayList<RankingData> dataSet) {
        rankingList = dataSet;
    }

    @NonNull
    @Override
    public RankingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_ranking, viewGroup, false);

        return new RankingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RankingViewHolder rankingViewHolder, int position) {
        rankingViewHolder.tv_grade.setText(Integer.toString(rankingList.get(position).getGrade()));
        rankingViewHolder.tv_booktitle.setText(rankingList.get(position).getBooktitle());
    }

    @Override
    public int getItemCount() { return (null != rankingList ? rankingList.size() : 0); }
}
