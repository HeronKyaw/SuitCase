package com.app.suitcase.ui.fragments.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.app.suitcase.data.entities.ItemEntity;
import com.app.suitcase.ui.viewholder.ItemViewHolder;

public class ItemListAdapter extends ListAdapter<ItemEntity, ItemViewHolder> {

    public ItemListAdapter(@NonNull DiffUtil.ItemCallback<ItemEntity> diffCallback) {
        super(diffCallback);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ItemViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        ItemEntity current = getItem(position);
        holder.bind(current.getItem());
    }

    public static class ItemDiff extends DiffUtil.ItemCallback<ItemEntity> {

        @Override
        public boolean areItemsTheSame(@NonNull ItemEntity oldItem, @NonNull ItemEntity newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ItemEntity oldItem, @NonNull ItemEntity newItem) {
            return oldItem.getItemName().equals(newItem.getItemName());
        }
    }
}
