package com.artsam.dailysadhana;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.artsam.dailysadhana.db.AppDatabase;
import com.artsam.dailysadhana.db.dao.ActionDao;
import com.artsam.dailysadhana.db.dao.UserDao;
import com.artsam.dailysadhana.models.Action;
import com.artsam.dailysadhana.models.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.Timestamp;
import java.sql.Date;

import static org.junit.Assert.assertEquals;


/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TestActionsTable {

    private static AppDatabase mDb;
    private static ActionDao mActionDao;
    private static UserDao mUserDao;

    private User user = new User(0, "Artem", "Samoshkina");
    Action action = new Action(0, "work", new Date(System.currentTimeMillis()),
            Timestamp.valueOf("2017-10-16 22:55:00"), Timestamp.valueOf("2017-10-16 22:65:00"));

    @Before
    public void initDatabase() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(appContext, AppDatabase.class)
                .build();
        mActionDao = mDb.actionDao();
        mUserDao = mDb.userDao();
        mUserDao.insertUser(user);
    }

    @Test
    public void shouldBeCorrectCount() {
        mActionDao.insertAction(action);
        mActionDao.removeAll();
        assertEquals(0, mActionDao.getCount());
    }

    @Test
    public void saveAction() {
        mActionDao.insertAction(action);
        assertEquals(1, mActionDao.getCount());
    }

    @After
    public void closeDb() {
        mDb.close();
    }
}
