package com.app.suitcase.ui.fragments.main;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.suitcase.R;
import com.app.suitcase.data.entities.ItemEntity;
import com.app.suitcase.ui.activities.MainActivity;
import com.app.suitcase.ui.activities.NewItemActivity;
import com.app.suitcase.ui.fragments.adapter.ItemListAdapter;
import com.app.suitcase.ui.viewmodels.ItemListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ItemListFragment extends Fragment {

    private MainCallBackFragment callBackFragment;
    private ItemListViewModel itemListViewModel;
    private NewItemActivity newItemActivity;
    public static final int NEW_ITEM_ACTIVITY_REQUEST_CODE = 1;
    public SharedPreferences mPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        mPreferences = getActivity().getSharedPreferences("SuitCasePref", Context.MODE_PRIVATE);

        itemListViewModel = new ViewModelProvider(this).get(ItemListViewModel.class);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        final ItemListAdapter adapter = new ItemListAdapter(new ItemListAdapter.ItemDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        //Get data
        LiveData<List<ItemEntity>> getItems = itemListViewModel.getItems(mPreferences.getLong("userId", 0));

        getItems.observe(getViewLifecycleOwner(), items -> {
            adapter.submitList(items);
            Toast.makeText(requireContext(), "Data Loaded Successfully", Toast.LENGTH_SHORT).show();
        });

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener( v -> {
            Intent intent = new Intent(requireContext(), NewItemActivity.class);
            resultLauncher.launch(intent);
        });

        return view;
    }

    public void setCallBackFragment(MainCallBackFragment callBackFragment) {
        this.callBackFragment = callBackFragment;
    }

    private final ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        result -> {
            switch (result.getResultCode()) {
                case RESULT_OK:
                    ItemEntity item = new ItemEntity(
                        result.getData().getStringExtra(newItemActivity.ITEM_NAME),
                        result.getData().getIntExtra(newItemActivity.ITEM_PRICE, 0),
                        result.getData().getStringExtra(newItemActivity.ITEM_DESC),
                        result.getData().getStringExtra(newItemActivity.ITEM_IMAGE),
                        mPreferences.getLong("userId", 0)
                    );
                    itemListViewModel.createItem(item);
                    break;
                default:
                    Toast.makeText(
                            requireContext(),
                            "Item not saved",
                            Toast.LENGTH_SHORT
                    ).show();
            }
        }
    );

    private void goToMainScreen() {
        Intent switchToMainActivityIntent = new Intent(requireContext(), MainActivity.class);
        switchToMainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(switchToMainActivityIntent);

        if (getActivity() != null) {
            getActivity().finish();
        }
    }
}