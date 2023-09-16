package com.app.suitcase.data.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.app.suitcase.data.dao.ItemDao;
import com.app.suitcase.data.dao.UserDao;
import com.app.suitcase.data.entities.ItemEntity;
import com.app.suitcase.data.entities.UserEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ItemEntity.class, UserEntity.class}, version = 1, exportSchema = false)
public abstract class SuitCaseDatabase extends RoomDatabase {
    public abstract ItemDao itemDao();
    public abstract UserDao userDao();

    private static volatile SuitCaseDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static SuitCaseDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SuitCaseDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    SuitCaseDatabase.class, "suitcase_db")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onCreate method to populate the database.
     * For this sample, we clear the database every time it is created.
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                ItemDao itemDao = INSTANCE.itemDao();
                UserDao userDao = INSTANCE.userDao();

                itemDao.deleteAllItem();
                userDao.deleteAllUser();

                UserEntity user = new UserEntity("admin", "admin", "");
                userDao.createUser(user);

                ItemEntity item = new ItemEntity("iPhone 15 pro", 999, "Latest iPhone generation", "", 1);
                itemDao.createItem(item);
            });
        }
    };
}
