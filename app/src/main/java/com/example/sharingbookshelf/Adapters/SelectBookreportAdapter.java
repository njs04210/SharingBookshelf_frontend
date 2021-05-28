package com.example.sharingbookshelf.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingbookshelf.Activities.CreateBookReportActivity;
import com.example.sharingbookshelf.Fragments.SelectBookReportPopupFragment;
import com.example.sharingbookshelf.Models.BookData;
import com.example.sharingbookshelf.Models.SelectBookReportResponse;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;

public class SelectBookreportAdapter extends RecyclerView.Adapter<SelectBookreportAdapter.ViewHolder> {

    private ArrayList<SelectBookReportResponse.SelectBookReportData> selectbookList;
    private SelectBookReportPopupFragment selectBookReportPopupFragment;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_title;
        private final TextView tv_num;

        public ViewHolder(@NonNull View view) {
            super(view);
            this.tv_title = view.findViewById(R.id.tv_title);
            this.tv_num = view.findViewById(R.id.tv_num);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        int item_id = selectbookList.get(position).getItem_id();
                        String title = selectbookList.get(position).getBook().getTitle();
                        String thumbnailUri = selectbookList.get(position).getBook().getThumbnail();
                        Intent intent = new Intent(v.getContext(), CreateBookReportActivity.class);
                        intent.putExtra("item_id", item_id);
                        intent.putExtra("title", title);
                        intent.putExtra("thumbnailUri", thumbnailUri);
                        selectBookReportPopupFragment.dismiss();
                        v.getContext().startActivity(intent);
                    }
                }
            });
        }
    }

    public SelectBookreportAdapter(SelectBookReportPopupFragment selectBookReportPopupFragment,
                                   Context context, ArrayList<SelectBookReportResponse.SelectBookReportData> dataSet) {
        this.selectBookReportPopupFragment = selectBookReportPopupFragment;
        this.selectbookList = dataSet;
        this.context = context;
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