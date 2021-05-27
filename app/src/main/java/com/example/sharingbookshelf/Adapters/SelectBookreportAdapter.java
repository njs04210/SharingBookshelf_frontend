package com.example.sharingbookshelf.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingbookshelf.Models.SelectReportData;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;

public class SelectBookreportAdapter extends RecyclerView.Adapter<SelectBookreportAdapter.ViewHolder> {
    private ArrayList<SelectReportData> selectbookList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final RecyclerView mRecyclerView;
        private final TextView tv_booktitle;

        public ViewHolder(@NonNull View view) {
            super(view);
            this.mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_selectbookreport);
            this.tv_booktitle = (TextView) view.findViewById(R.id.tv_selectbooktitle);
        }
    }

    public SelectBookreportAdapter(ArrayList<SelectReportData> dataSet) {
        selectbookList = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_select_book_report, parent, false);

        return new SelectBookreportAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder selectbookreportHolder, int position) {
        selectbookreportHolder.tv_booktitle.setText(selectbookList.get(position).getBooktitle());
    }

    @Override
    public int getItemCount() {
        return (null != selectbookList ? selectbookList.size() : 0);
    }
}