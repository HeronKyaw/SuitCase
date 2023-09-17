package com.app.suitcase.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.app.suitcase.data.dao.ItemDao;
import com.app.suitcase.data.database.SuitCaseDatabase;
import com.app.suitcase.data.entities.ItemEntity;

import java.util.List;

public class ItemRepository {
    private ItemDao itemDao;

    public ItemRepository(Application application) {
        SuitCaseDatabase db = SuitCaseDatabase.getDatabase(application);
        itemDao = db.itemDao();
    }

    public LiveData<List<ItemEntity>> getItems(long uid) {
        return itemDao.getAllItemsById(uid);
    }

    public LiveData<ItemEntity> getItem(long item_id) {
        return itemDao.getItemById(item_id);
    }

    public void createNewItem(ItemEntity itemEntity) {
        SuitCaseDatabase.databaseWriteExecutor.execute(() -> {
            itemDao.createItem(itemEntity);
        });
    }

    public void updateItem(ItemEntity itemEntity) {
        SuitCaseDatabase.databaseWriteExecutor.execute(() -> {
            itemDao.updateItem(itemEntity);
        });
    }

    public void deleteItem(ItemEntity itemEntity) {
        SuitCaseDatabase.databaseWriteExecutor.execute(() -> {
            itemDao.deleteItem(itemEntity);
        });
    }

    public void deleteAllItem() {
        SuitCaseDatabase.databaseWriteExecutor.execute(() -> {
            itemDao.deleteAllItem();
        });
    }

}
