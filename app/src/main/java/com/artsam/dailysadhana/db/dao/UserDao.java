package com.artsam.dailysadhana.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.artsam.dailysadhana.db.AppDatabase;
import com.artsam.dailysadhana.models.User;

import java.util.List;

/**
 * Created by Artem on 13.10.17.
 */

@Dao
public interface UserDao {
    String USER_ID = "userId";

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Query("SELECT * FROM "
            + AppDatabase.TABLE_NAME_USERS)
    List<User> getAll();

    @Query("SELECT * FROM "
            + AppDatabase.TABLE_NAME_USERS
            + " WHERE " + USER_ID + "=:userId")
    User getUsersById(int userId);

    @Query("SELECT * FROM "
            + AppDatabase.TABLE_NAME_USERS
            + " WHERE first_name LIKE :first " +
            "AND last_name LIKE :last LIMIT 1")
    User getByName(String first, String last);

    @Query("SELECT COUNT(" + USER_ID + ") FROM " + AppDatabase.TABLE_NAME_USERS)
    int getCount();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateUser(User user);

    @Delete
    public void removeUsers(List<User> users);

    @Query("DELETE FROM "
            + AppDatabase.TABLE_NAME_USERS
            + " WHERE " + USER_ID + " IN (:userIds)")
    int removeUsersByIds(int[] userIds);

    @Query("DELETE FROM "
            + AppDatabase.TABLE_NAME_USERS)
    int removeAll();
}
