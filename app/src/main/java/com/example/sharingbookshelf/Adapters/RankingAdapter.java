package com.example.sharingbookshelf.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingbookshelf.Fragments.ClickBookDetailsFragment;
import com.example.sharingbookshelf.Fragments.RankingBookInfoPopupFragment;
import com.example.sharingbookshelf.Models.RankingData;
import com.example.sharingbookshelf.R;

import org.w3c.dom.Text;

import java.net.CookieHandler;
import java.util.ArrayList;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.ViewHolder> {

    private ArrayList<RankingData> rankingList;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_grade;
        private final TextView tv_booktitle;

        public ViewHolder(@NonNull View view) {
            super(view);
            tv_grade = view.findViewById(R.id.tv_grade);
            tv_booktitle = view.findViewById(R.id.tv_booktitle);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        RankingData item = rankingList.get(pos);
                        Log.d("책", String.valueOf(item.getBookId()));

                        Bundle bundle = new Bundle();
                        bundle.putInt("bookId", item.getBookId());
                        FragmentManager fm = ((AppCompatActivity) context).getSupportFragmentManager();
                        RankingBookInfoPopupFragment dialog = new RankingBookInfoPopupFragment();
                        dialog.setArguments(bundle);
                        dialog.show(fm, "abc");
                    }
                }
            });
        }
    }

    public RankingAdapter(Context context, ArrayList<RankingData> dataSet) {
        rankingList = dataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_ranking, viewGroup, false);

        //context = viewGroup.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder rankingViewHolder, int position) {
        rankingViewHolder.tv_grade.setText(Integer.toString(rankingList.get(position).getGrade()));
        rankingViewHolder.tv_booktitle.setText(rankingList.get(position).getBooktitle());
    }

    @Override
    public int getItemCount() {
        return (null != rankingList ? rankingList.size() : 0);
    }
}
