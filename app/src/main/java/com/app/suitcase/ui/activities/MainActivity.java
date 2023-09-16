package com.app.suitcase.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.app.suitcase.R;
import com.app.suitcase.ui.fragments.main.ItemListFragment;
import com.app.suitcase.ui.fragments.main.MainCallBackFragment;

public class MainActivity extends AppCompatActivity implements MainCallBackFragment {

    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the Toolbar
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        itemListFragment();
    }

    private void itemListFragment() {
        ItemListFragment fragment = new ItemListFragment();
        fragment.setCallBackFragment(this);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.main_fragment, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void NavToItemListFragment() {
        itemListFragment();
    }

    @Override
    public void NavToItemDetailFragment() {

    }
}