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
import androidx.lifecycle.ViewModelProvider;

import com.app.suitcase.R;
import com.app.suitcase.data.entities.UserEntity;
import com.app.suitcase.ui.viewmodels.UserViewModel;

public class SignUpFragment extends Fragment {

    Button btnSignUp, navigateToLogin;
    EditText etUsername, etPassword, etConfirmPassword, etEmail;
    String username, pass, cpass, email;
    AuthCallBackFragment callBackFragment;
    private UserViewModel mUserViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_sign_up, container, false);

        btnSignUp = view.findViewById(R.id.reg_btn);
        etUsername = view.findViewById(R.id.et_username);
        etPassword = view.findViewById(R.id.et_password);
        etConfirmPassword = view.findViewById(R.id.et_confirm_password);
        etEmail = view.findViewById(R.id.et_email);
        navigateToLogin = view.findViewById(R.id.nav_to_login_btn);
        mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        navigateToLogin.setOnClickListener(v -> {
            if (callBackFragment != null) {
                callBackFragment.NavToLoginFragment();
            }
        });

        btnSignUp.setOnClickListener(v -> {
            username = etUsername.getText().toString();
            pass = etPassword.getText().toString();
            cpass = etConfirmPassword.getText().toString();
            email = etEmail.getText().toString();

            if (pass.equals(cpass)) {
                UserEntity newUser = new UserEntity(username, pass, email);

                mUserViewModel.createUser(newUser);

                Toast.makeText(requireContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Password do not macth", Toast.LENGTH_SHORT).show();
            }

        });

        return view;
    }
    public void setCallBackFragment(AuthCallBackFragment callBackFragment) {
        this.callBackFragment = callBackFragment;
    }

}