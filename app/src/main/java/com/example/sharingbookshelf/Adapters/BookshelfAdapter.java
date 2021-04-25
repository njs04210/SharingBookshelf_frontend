package com.example.sharingbookshelf.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharingbookshelf.Models.NeighborInfoData;
import com.example.sharingbookshelf.Models.NeighborShelfData;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;

public class BookshelfAdapter extends RecyclerView.Adapter<BookshelfAdapter.BookshelfViewHolder> {

    private final Context context;
    private ArrayList<NeighborInfoData> arrayList;

    /* Initialize the dateset of the Adapter. */
    public BookshelfAdapter(Context context, ArrayList<NeighborInfoData> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    public static class BookshelfViewHolder extends RecyclerView.ViewHolder {

        private final RecyclerView recyclerView;
        private final ImageView iv_profileImg;
        private final TextView tv_nickname;

        public BookshelfViewHolder(@NonNull View itemView) {
            super(itemView); // Define click listener for the ViewHolder's View
            this.recyclerView = (RecyclerView) itemView.findViewById(R.id.rv_bookshelf);
            this.iv_profileImg = (ImageView) itemView.findViewById(R.id.iv_profileImg);
            this.tv_nickname = (TextView) itemView.findViewById(R.id.tv_nickname);
        }
    }

    /**
     * RecyclerView는 ViewHolder를 새로 만들어야 할 때마다 onCreateViewHolder를 호출합니다.
     * 이 메서드는 ViewHolder와 그에 연결된 View를 생성하고 초기화하지만 뷰의 콘텐츠를 채우지는 않습니다.
     * ViewHolder가 아직 특정 데이터에 바인딩된 상태가 아니기 때문입니다.
     */
    @NonNull
    @Override
    public BookshelfViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_neighbor, parent, false);

        return new BookshelfViewHolder(view);
    }

    /**
     * ViewHolder와 데이터와 연결할 때 호출하는 메소드. 적절한 데이터를 가져와서 그 데이터를 사용해
     * 뷰 홀더의 레이아웃을 채운다.
     */
    @Override
    public void onBindViewHolder(@NonNull BookshelfViewHolder bookshelfViewHolder, final int position) {
        NeighborInfoData neighborInfoData = arrayList.get(position);
        bookshelfViewHolder.iv_profileImg.setImageResource(neighborInfoData.getProfile());
        bookshelfViewHolder.tv_nickname.setText(neighborInfoData.getNickname());

        ArrayList<NeighborShelfData> singleItem = neighborInfoData.getBookList();
        BookshelvesAdapter bookshelvesAdapter = new BookshelvesAdapter(context, singleItem);

        bookshelfViewHolder.recyclerView.setHasFixedSize(true);
        bookshelfViewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

        bookshelfViewHolder.recyclerView.setAdapter(bookshelvesAdapter);
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }
}
