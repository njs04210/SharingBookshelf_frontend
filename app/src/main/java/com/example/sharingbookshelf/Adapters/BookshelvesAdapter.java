package com.example.sharingbookshelf.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sharingbookshelf.Models.NeighborShelfData;
import com.example.sharingbookshelf.R;
import java.util.ArrayList;

public class BookshelvesAdapter extends RecyclerView.Adapter<BookshelvesAdapter.NeighborShelfViewHolder> {

    Context context;
    private ArrayList<NeighborShelfData> arrayList;

    /* Initialize the dateset of the Adapter. */
    public BookshelvesAdapter(Context context, ArrayList<NeighborShelfData> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public BookshelvesAdapter.NeighborShelfViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_neighborshelf, parent, false);

        return new NeighborShelfViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookshelvesAdapter.NeighborShelfViewHolder holder, int position) {
        NeighborShelfData neighborShelfData = arrayList.get(position);
        holder.iv_book.setImageResource(neighborShelfData.getIv_book());
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class NeighborShelfViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_book;

        public NeighborShelfViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_book = (ImageView)itemView.findViewById(R.id.iv_book);
        }
    }
}
