package com.example.sharingbookshelf.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sharingbookshelf.Fragments.ClickBookDetailsFragment;
import com.example.sharingbookshelf.Models.ThumbnailData;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;

public class MyBookshelfAdapter extends RecyclerView.Adapter<MyBookshelfAdapter.ViewHolder> {

    private ArrayList<ThumbnailData> localDataSet;
    private Context context;


    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mimageView;

        public ViewHolder(View view) {
            super(view);
            mimageView = view.findViewById(R.id.iv_book);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        ThumbnailData item = localDataSet.get(pos);
                        Log.d("책", item.getIsbn());

                        Bundle bundle = new Bundle();
                        bundle.putString("isbn", item.getIsbn());
                        FragmentManager fm = ((AppCompatActivity) context).getSupportFragmentManager();
                        ClickBookDetailsFragment dialog = new ClickBookDetailsFragment();
                        dialog.setArguments(bundle);
                        dialog.show(fm, "abc");

                    }
                }
            });
        }

    }

    public MyBookshelfAdapter(ArrayList<ThumbnailData> dataSet) {
        localDataSet = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_book_mybookshelf, viewGroup, false);

        context = viewGroup.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        Glide
                .with(viewHolder.mimageView.getContext())
                .load(localDataSet.get(position).getThumbnail())
                .fitCenter()
                .placeholder(R.drawable.icon_book2)
                .into(viewHolder.mimageView);

        //viewHolder.mimageView.setTag(localDataSet.get(position).getIsbn());
    }

    @Override
    public int getItemCount() {
        return (null != localDataSet ? localDataSet.size() : 0);
    }
}
