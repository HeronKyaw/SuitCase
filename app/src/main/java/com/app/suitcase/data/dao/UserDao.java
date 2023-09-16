package com.app.suitcase.data.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.app.suitcase.data.entities.UserEntity;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users_table WHERE username = :username AND password = :password")
    LiveData<UserEntity> authenticateUser(String username, String password);

    @Insert
    void createUser(UserEntity user);

    @Query("DELETE FROM users_table")
    void deleteAllUser();

    @Query("UPDATE users_table SET username = :username, email = :email, password = :password WHERE uid = :userId")
    void updateUserInfo(long userId, String username, String email, String password);
}
