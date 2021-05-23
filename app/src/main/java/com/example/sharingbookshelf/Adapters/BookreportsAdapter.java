package com.example.sharingbookshelf.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingbookshelf.Models.BookreportData;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;

public class BookreportsAdapter extends RecyclerView.Adapter<BookreportsAdapter.ViewHolder> {


    private ArrayList<BookreportData> reportList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iv_painting;
        private final TextView tv_bookreporttitle;
        private final TextView tv_date;

        public ViewHolder(@NonNull View view) {
            super(view);
            iv_painting = (ImageView) view.findViewById(R.id.iv_painting);
            tv_bookreporttitle = (TextView) view.findViewById(R.id.tv_bookreporttitle);
            tv_date = (TextView) view.findViewById(R.id.tv_date);
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
    public void onBindViewHolder(@NonNull ViewHolder reportViewHolder, int position) {
        reportViewHolder.iv_painting.setImageResource(reportList.get(position).getPainting());
        reportViewHolder.tv_bookreporttitle.setText(reportList.get(position).getBooktitle());
        reportViewHolder.tv_date.setText(reportList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return (null != reportList ? reportList.size() : 0);
    }
}
