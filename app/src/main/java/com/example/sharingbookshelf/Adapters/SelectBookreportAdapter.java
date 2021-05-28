package com.example.sharingbookshelf.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingbookshelf.Models.BookData;
import com.example.sharingbookshelf.Models.SelectBookReportResponse;
import com.example.sharingbookshelf.Models.SelectReportData;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;

public class SelectBookreportAdapter extends RecyclerView.Adapter<SelectBookreportAdapter.ViewHolder> {

    private ArrayList<SelectBookReportResponse.SelectBookReportData> selectbookList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_title;
        private final TextView tv_num;

        public ViewHolder(@NonNull View view) {
            super(view);
            this.tv_title = view.findViewById(R.id.tv_title);
            this.tv_num = view.findViewById(R.id.tv_num);
        }
    }

    public SelectBookreportAdapter(ArrayList<SelectBookReportResponse.SelectBookReportData> dataSet) {
        selectbookList = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_select_book_report, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        BookData book = selectbookList.get(position).getBook();
        viewHolder.tv_num.setText(position + 1 + " - ");
        viewHolder.tv_title.setText(book.getTitle());
    }

    @Override
    public int getItemCount() {
        return (null != selectbookList ? selectbookList.size() : 0);
    }
}