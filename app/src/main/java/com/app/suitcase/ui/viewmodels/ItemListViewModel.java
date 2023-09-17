package com.app.suitcase.ui.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.app.suitcase.data.entities.ItemEntity;
import com.app.suitcase.data.entities.UserEntity;
import com.app.suitcase.data.repository.ItemRepository;
import com.app.suitcase.data.repository.UserRepository;

import java.util.List;

public class ItemListViewModel extends AndroidViewModel {
    private ItemRepository itemRepository;

    public ItemListViewModel(Application application) {
        super(application);
        itemRepository = new ItemRepository(application);
    }

    // Methods to interact with the repository
    public void createItem(ItemEntity item) {
        itemRepository.createNewItem(item);
    }

    public LiveData<List<ItemEntity>> getItems(long uid) {
        return itemRepository.getItems(uid);
    }

    public LiveData<ItemEntity> getItem(long item_id) {
        return itemRepository.getItem(item_id);
    }

    public void updateItem(ItemEntity item) { itemRepository.updateItem(item); }

    public void deleteItem(ItemEntity item) { itemRepository.deleteItem(item); }

    public void deleteAllItem(ItemEntity item) { itemRepository.deleteAllItem(); }
}
