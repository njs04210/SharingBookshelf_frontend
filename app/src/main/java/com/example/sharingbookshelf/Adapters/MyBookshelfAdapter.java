package com.example.sharingbookshelf.Adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sharingbookshelf.Activities.HomeActivity;
import com.example.sharingbookshelf.Activities.MainActivity;
import com.example.sharingbookshelf.Fragments.ClickBookDetailsFragment;
import com.example.sharingbookshelf.Fragments.MyBookshelfFragment;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;
import java.util.Map;

public class MyBookshelfAdapter extends RecyclerView.Adapter<MyBookshelfAdapter.ViewHolder> {

    private ArrayList<Map<String, Object>> localDataSet;
    private Context context;

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
                        String isbn = (String) localDataSet.get(position).get("ISBN");
                        Double d = (Double) localDataSet.get(position).get("book_id"); // book_id를 double -> int 로 형변환 해야함
                        int book_id = d.intValue();

                        Log.d("책", isbn);

                        Bundle bundle = new Bundle();
                        bundle.putInt("bookId", book_id);
                        FragmentManager fm = ((AppCompatActivity)context).getSupportFragmentManager();
                        ClickBookDetailsFragment dialog = new ClickBookDetailsFragment();
                        dialog.setArguments(bundle);
                        dialog.show(fm, "abc");

                    }
                }
            });
        }

    }

    public MyBookshelfAdapter(Context context, ArrayList<Map<String, Object>> dataSet) {
        localDataSet = dataSet;
        this.context = context;
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

        Map<String, Object> data = localDataSet.get(position);
        String thumbnailUri = (String) data.get("thumbnail");
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
