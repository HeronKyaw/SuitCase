package com.app.suitcase.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.app.suitcase.R;
import com.app.suitcase.ui.fragments.CallBackFragment;
import com.app.suitcase.ui.fragments.auth.LoginFragment;
import com.app.suitcase.ui.fragments.auth.SignUpFragment;

public class AuthActivity extends AppCompatActivity implements CallBackFragment {

    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        loginFragment();
    }

    private void loginFragment() {
        LoginFragment fragment = new LoginFragment();
        fragment.setCallBackFragment(this);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.auth_fragment, fragment);
        fragmentTransaction.commit();
    }

    private void regFragment() {
        SignUpFragment fragment = new SignUpFragment();
        fragment.setCallBackFragment(this);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.auth_fragment, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void NavToRegFragment() {
        regFragment();
    }

    @Override
    public void NavToLoginFragment() {
        loginFragment();
    }
}