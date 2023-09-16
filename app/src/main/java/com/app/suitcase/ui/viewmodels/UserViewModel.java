package com.app.suitcase.ui.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.app.suitcase.data.entities.UserEntity;
import com.app.suitcase.data.repository.UserRepository;

public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepository;

    public UserViewModel(Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    // Methods to interact with the repository
    public void createUser(UserEntity user) {
        userRepository.createUser(user);
    }

    public LiveData<UserEntity> authenticateUser(String username, String password) {
        return userRepository.authenticateUser(username, password);
    }
}
