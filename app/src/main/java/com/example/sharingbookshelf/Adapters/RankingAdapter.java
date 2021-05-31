package com.example.sharingbookshelf.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingbookshelf.Models.BookData;
import com.example.sharingbookshelf.Models.RankingData;
import com.example.sharingbookshelf.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.sharingbookshelf.Fragments.RankingBookInfoPopupFragment;

import java.util.ArrayList;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.ViewHolder> {

    private ArrayList<RankingData> rankingList;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_grade;
        private final TextView tv_title;

        public ViewHolder(@NonNull View view) {
            super(view);
            tv_grade = view.findViewById(R.id.tv_grade);
            tv_title = view.findViewById(R.id.tv_title);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        RankingData item = rankingList.get(pos);
                        Log.d("책", String.valueOf(item.getBook().getBook_id()));

                        Bundle bundle = new Bundle();
                        bundle.putInt("book_id", item.getBook().getBook_id());
                        bundle.putInt("total", item.getTotal());

                        FragmentManager fm = ((AppCompatActivity) context).getSupportFragmentManager();
                        RankingBookInfoPopupFragment rankingBookInfoPopupFragment = new RankingBookInfoPopupFragment();
                        rankingBookInfoPopupFragment.setArguments(bundle);
                        rankingBookInfoPopupFragment.show(fm, "RankingBookInfoPopupFragment");
                    }
                }
            });

        }
    }

    public RankingAdapter(Context context, ArrayList<RankingData> dataSet) {
        this.rankingList = dataSet;
        this.context = context;
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
