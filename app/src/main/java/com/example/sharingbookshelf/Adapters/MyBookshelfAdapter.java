package com.example.sharingbookshelf.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sharingbookshelf.Fragments.ClickBookDetailsFragment;
import com.example.sharingbookshelf.Fragments.RankingBookInfoPopupFragment;
import com.example.sharingbookshelf.Models.BookData;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;
import java.util.Map;

public class MyBookshelfAdapter extends RecyclerView.Adapter<MyBookshelfAdapter.ViewHolder> {

    private ArrayList<BookData> localDataSet;
    private Context context;
    private Fragment selectedFragment;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mimageView;

        public ViewHolder(View view) {
            super(view);
            mimageView = view.findViewById(R.id.iv_book);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {

                        int book_id = localDataSet.get(position).getBook_id();
                        Bundle bundle = new Bundle();
                        bundle.putInt("book_id", book_id);
                        FragmentManager fm = ((AppCompatActivity) context).getSupportFragmentManager();

                        if (selectedFragment.getTag().equals("NoEmptyShelfFragment")) {
                            ClickBookDetailsFragment clickBookDetailsFragment = new ClickBookDetailsFragment();
                            clickBookDetailsFragment.setArguments(bundle);
                            clickBookDetailsFragment.show(fm, "MyBookshelfAdapter");

                        } else if (selectedFragment.getTag().equals("UserinfoShelfFragment")) {
                            RankingBookInfoPopupFragment rankingBookInfoPopupFragment = new RankingBookInfoPopupFragment();
                            rankingBookInfoPopupFragment.setArguments(bundle);
                            rankingBookInfoPopupFragment.show(fm, "MyBookshelfAdapter");
                        }
                    }
                }
            });
        }

    }

    public MyBookshelfAdapter(Fragment fragment, Context context, ArrayList<BookData> dataSet) {
        this.selectedFragment = fragment;
        this.context = context;
        this.localDataSet = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_book_mybookshelf, viewGroup, false);

        //  context = viewGroup.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        BookData data = localDataSet.get(position);
        String thumbnailUri = (String) data.getThumbnail();
        Glide
                .with(viewHolder.mimageView.getContext())
                .load(thumbnailUri)
                .fitCenter()
                .placeholder(R.drawable.icon_book2)
                .into(viewHolder.mimageView);

    }

    @Override
    public int getItemCount() {
        return (null != localDataSet ? localDataSet.size() : 0);
    }
}
