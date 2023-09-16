package com.app.suitcase.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users_table")
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    public long uid;

    @ColumnInfo(name = "username")
    public String username;

    @ColumnInfo(name = "password")
    public String password;

    @ColumnInfo(name = "email")
    public String email;

    /*
        * Constructor
        * @param username: String
        * @param password: String
        * @param email: String
     */

    public UserEntity(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
