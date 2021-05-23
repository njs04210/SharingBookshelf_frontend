package com.example.sharingbookshelf.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sharingbookshelf.Models.MessageData;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;

public class MailboxAdapter extends RecyclerView.Adapter<MailboxAdapter.ViewHolder> {

    private ArrayList<MessageData> mailList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iv_profileImage;
        private final TextView tv_nickname;
        private final TextView tv_mailtext;
        private final TextView tv_time;

        public ViewHolder(@NonNull View view) {
            super(view);
            iv_profileImage = view.findViewById(R.id.iv_profileImg);
            tv_nickname = view.findViewById(R.id.tv_nickname);
            tv_mailtext = view.findViewById(R.id.tv_mailtext);
            tv_time = view.findViewById(R.id.tv_time);
        }
    }

    public MailboxAdapter(ArrayList<MessageData> dataSet) {
        mailList = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_message, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        MessageData messageData = mailList.get(position);
        Glide
                .with(viewHolder.iv_profileImage.getContext())
                .load(messageData.getProfileImage())
                .fitCenter()
                .placeholder(R.drawable.icon_logo)
                .into(viewHolder.iv_profileImage);

        viewHolder.tv_nickname.setText(messageData.getUserName());
        viewHolder.tv_mailtext.setText(messageData.getLastMessage());
        viewHolder.tv_time.setText(messageData.getMessageTime());
    }

    @Override
    public int getItemCount() {
        return (null != mailList ? mailList.size() : 0);
    }
}
