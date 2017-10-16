package com.artsam.dailysadhana.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.artsam.dailysadhana.db.AppDatabase;
import com.artsam.dailysadhana.models.Action;

import java.util.List;

/**
 * Created by Artem on 13.10.17.
 */

@Dao
public interface ActionDao {
    String ACTION_ID = "actId";

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAction(Action action);

    @Query("SELECT * FROM "
            + AppDatabase.TABLE_NAME_ACTIONS
            + " WHERE userId=:userId")
    List<Action> findActionsForUser(int userId);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateAction(Action action);

    @Query("DELETE FROM "
            + AppDatabase.TABLE_NAME_ACTIONS
            + " WHERE actId=:actId")
    void deleteActionById(long actId);

    @Query("DELETE FROM "
            + AppDatabase.TABLE_NAME_ACTIONS)
    int removeAll();

    @Query("SELECT COUNT(" + ACTION_ID + ") FROM " + AppDatabase.TABLE_NAME_ACTIONS)
    int getCount();
}
