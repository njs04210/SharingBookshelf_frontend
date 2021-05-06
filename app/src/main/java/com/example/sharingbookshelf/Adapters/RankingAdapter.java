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

    public RankingAdapter(ArrayList<RankingData> rankingList) { this.rankingList = rankingList; }

    public static class RankingViewHolder extends RecyclerView.ViewHolder {
        private final RecyclerView rv_ranking;
        private final TextView tv_grade;
        private final TextView tv_booktitle;

        public RankingViewHolder(@NonNull View itemView) {
            super(itemView);
            this.rv_ranking = (RecyclerView) itemView.findViewById(R.id.rv_ranking);
            this.tv_grade = (TextView) itemView.findViewById(R.id.tv_grade);
            this.tv_booktitle = (TextView) itemView.findViewById(R.id.tv_booktitle);
        }
    }

    @NonNull
    @Override
    public RankingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ranking, parent, false);

        return new RankingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RankingViewHolder rankingViewHolder, int position) {
        RankingData rankingData = rankingList.get(position);
        rankingViewHolder.tv_grade.setText(rankingData.getGrade());
        rankingViewHolder.tv_booktitle.setText(rankingData.getBooktitle());
    }

    @Override
    public int getItemCount() { return (null != rankingList ? rankingList.size() : 0); }
}
