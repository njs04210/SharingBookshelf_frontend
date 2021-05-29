package com.example.sharingbookshelf.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingbookshelf.Models.BookData;
import com.example.sharingbookshelf.Models.RankingData;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.ViewHolder> {

    private ArrayList<RankingData> rankingList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_grade;
        private final TextView tv_title;

        public ViewHolder(@NonNull View view) {
            super(view);
            tv_grade = (TextView) view.findViewById(R.id.tv_grade);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
        }
    }

    public RankingAdapter(ArrayList<RankingData> dataSet) {
        rankingList = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_ranking, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        BookData book = rankingList.get(position).getBook();
        int ranking = rankingList.get(position).getRanking();
        viewHolder.tv_title.setText(book.getTitle());
        viewHolder.tv_grade.setText(String.valueOf(ranking));

    }

    @Override
    public int getItemCount() {
        return (null != rankingList ? rankingList.size() : 0);
    }
}
