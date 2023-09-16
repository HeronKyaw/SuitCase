package com.app.suitcase.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.app.suitcase.data.entities.ItemEntity;

import java.util.List;

@Dao
public interface ItemDao {
    @Query("SELECT * FROM items_table WHERE user_id = :user_id")
    LiveData<List<ItemEntity>> getAllItemsById(long user_id);

    @Query("SELECT * FROM items_table WHERE id = :item_id")
    LiveData<ItemEntity> getItemById(long item_id);

    @Insert
    void createItem(ItemEntity item);

    @Delete
    void deleteItem(ItemEntity item);

    @Update
    void updateItem(ItemEntity item);

    @Query("DELETE FROM items_table")
    void deleteAllItem();
}
