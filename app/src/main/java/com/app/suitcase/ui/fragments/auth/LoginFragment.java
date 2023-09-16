package com.app.suitcase.ui.fragments.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.app.suitcase.R;
import com.app.suitcase.data.entities.UserEntity;
import com.app.suitcase.ui.fragments.CallBackFragment;
import com.app.suitcase.ui.viewmodels.UserViewModel;

public class LoginFragment extends Fragment {

    Button btnLogin, navigateToSignUp;
    EditText etUsername, etPassword;
    private String username, password;
    private CallBackFragment callBackFragment;
    private UserViewModel mUserViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        btnLogin = view.findViewById(R.id.login_btn);
        navigateToSignUp = view.findViewById(R.id.nav_to_reg_btn);
        etUsername = view.findViewById(R.id.et_username);
        etPassword = view.findViewById(R.id.et_password);
        mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        navigateToSignUp.setOnClickListener(v -> {
            if (callBackFragment != null) {
                callBackFragment.NavToRegFragment();
            }
        });

        btnLogin.setOnClickListener(v -> {
            username = etUsername.getText().toString();
            password = etPassword.getText().toString();

            LiveData<UserEntity> authenticateUser = mUserViewModel.authenticateUser(username, password);

            authenticateUser.observe(getViewLifecycleOwner(), userEntity -> {
                if (userEntity != null) {
                    Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                }
            });
        });

        return view;
    }

    public void setCallBackFragment(CallBackFragment callBackFragment) {
        this.callBackFragment = callBackFragment;
    }
}