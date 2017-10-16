package com.artsam.dailysadhana.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.artsam.dailysadhana.models.Action;
import com.artsam.dailysadhana.db.dao.ActionDao;
import com.artsam.dailysadhana.models.User;
import com.artsam.dailysadhana.db.dao.UserDao;

/**
 * Created by Artem on 13.10.17.
 */
@Database(entities = {User.class, Action.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public static final String DB_NAME = "daily_sadhana_db";
    public static final String TABLE_NAME_USERS = "users";
    public static final String TABLE_NAME_ACTIONS = "actions";

    private static AppDatabase INSTANCE;

    public abstract ActionDao actionDao();
    public abstract UserDao userDao();

    public static AppDatabase getDatabase(Context ctx) {
        if (INSTANCE == null) {
            INSTANCE = Room
                    .databaseBuilder(ctx, AppDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
