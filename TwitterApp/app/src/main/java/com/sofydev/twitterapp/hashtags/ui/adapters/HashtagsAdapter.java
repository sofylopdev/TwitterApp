package com.sofydev.twitterapp.hashtags.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sofydev.twitterapp.R;
import com.sofydev.twitterapp.entities.Hashtag;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HashtagsAdapter extends RecyclerView.Adapter<HashtagsAdapter.HashViewHolder> {

    private List<Hashtag> items;
    private OnItemClickListener listener;

    public HashtagsAdapter(List<Hashtag> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HashViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_hashtags, parent, false);
        return new HashViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull HashViewHolder holder, int position) {
        Hashtag tweet = items.get(position);
        holder.setOnClickListener(tweet, listener);
        holder.txtTweet.setText(tweet.getTweetText());
        holder.setItems(tweet.getHashtags());
    }

    public void setItems(List<Hashtag> newItems) {
        this.items.addAll(newItems);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class HashViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtTweet)
        TextView txtTweet;
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;

        private View view;
        private HashtagListAdapter adapter;
        private List<String> hastags;

        public HashViewHolder(View itemView, Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;

            hastags = new ArrayList<>();
            adapter = new HashtagListAdapter(hastags);

            CustomGridLayoutManager layoutManager = new CustomGridLayoutManager(context, 3);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }

        public void setItems(List<String> items) {
            hastags.clear();
            hastags.addAll(items);
            notifyDataSetChanged();
        }

        public void setOnClickListener(final Hashtag hashtag, final OnItemClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(hashtag);
                }
            });
        }
    }
}
