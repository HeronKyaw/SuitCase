package com.app.suitcase.ui.viewholder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.suitcase.R;
import com.app.suitcase.data.entities.ItemEntity;

import java.io.File;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    private final TextView itemTitleView, itemSubTitleView;
    private final ImageView itemImageView; // Add ImageView

    private ItemViewHolder(View itemView) {
        super(itemView);
        itemTitleView = itemView.findViewById(R.id.titleTextView);
        itemSubTitleView = itemView.findViewById(R.id.subtitleTextView);
        itemImageView = itemView.findViewById(R.id.image); // Add ImageView
    }

    public void bind(ItemEntity item) {
        itemTitleView.setText(item.getItemName());
        itemSubTitleView.setText(String.valueOf(item.getPrice()));
        if (item.getImagePath() != null || item.getImagePath().isEmpty()) {
            // Assuming item.getImagePath() returns the file path as a String
            File imgFile = new File(item.getImagePath());
            if (imgFile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                itemImageView.setImageBitmap(myBitmap);
            } else {
                itemImageView.setImageResource(R.drawable.item_image);
            }
        } else {
            itemImageView.setImageResource(R.drawable.item_image);
        }
    }

    public static ItemViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }
}