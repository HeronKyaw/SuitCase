package com.app.suitcase.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.app.suitcase.data.dao.UserDao;
import com.app.suitcase.data.database.SuitCaseDatabase;
import com.app.suitcase.data.entities.UserEntity;

public class UserRepository {
    private UserDao userDao;

    public UserRepository(Application application) {
        SuitCaseDatabase db = SuitCaseDatabase.getDatabase(application);
        userDao = db.userDao();
    }

    public void createUser(UserEntity user) {
        SuitCaseDatabase.databaseWriteExecutor.execute(() -> {
            userDao.createUser(user);
        });
    }

    public LiveData<UserEntity> authenticateUser(String username, String password) {
        return userDao.authenticateUser(username, password);
    }

}
