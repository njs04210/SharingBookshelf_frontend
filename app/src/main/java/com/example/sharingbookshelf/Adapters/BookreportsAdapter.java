package com.example.sharingbookshelf.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sharingbookshelf.Activities.OneBookReportActivity;
import com.example.sharingbookshelf.Models.BookreportData;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;

public class BookreportsAdapter extends RecyclerView.Adapter<BookreportsAdapter.ViewHolder> {


    private ArrayList<BookreportData> reportList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iv_thumbnail;
        private final TextView tv_title;
        private final TextView tv_created;
        private final TextView tv_num;

        public ViewHolder(@NonNull View view) {
            super(view);
            tv_num = view.findViewById(R.id.tv_num);
            iv_thumbnail = view.findViewById(R.id.iv_thumbnail);
            tv_title = view.findViewById(R.id.tv_title);
            tv_created = view.findViewById(R.id.tv_created);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        BookreportData bookreportData = reportList.get(position);
                        int item_id = bookreportData.getBookreports().getItem_id();
                        String title = bookreportData.getBook().getTitle();
                        Intent intent = new Intent(v.getContext(), OneBookReportActivity.class);
                        intent.putExtra("item_id", item_id);
                        intent.putExtra("title", title);
                        v.getContext().startActivity(intent);
                    }
                }
            });
        }
    }

    public BookreportsAdapter(ArrayList<BookreportData> dataSet) {
        reportList = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_book_report, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        BookreportData bookreportData = reportList.get(position);
        String thumbnailUri = bookreportData.getBook().getThumbnail();
        String title = bookreportData.getBook().getTitle();
        String created = bookreportData.getBookreports().getCreated();

        viewHolder.tv_num.setText(String.valueOf(position + 1));
        Glide
                .with(viewHolder.iv_thumbnail.getContext())
                .load(thumbnailUri)
                .fitCenter()
                .placeholder(R.drawable.ic_blank4)
                .into(viewHolder.iv_thumbnail);
        viewHolder.tv_title.setText(title);
        viewHolder.tv_created.setText(created);
    }

    @Override
    public int getItemCount() {
        return (null != reportList ? reportList.size() : 0);
    }
}
