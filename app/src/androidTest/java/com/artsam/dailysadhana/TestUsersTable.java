package com.artsam.dailysadhana;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.artsam.dailysadhana.db.AppDatabase;
import com.artsam.dailysadhana.db.dao.UserDao;
import com.artsam.dailysadhana.models.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;

/**
 * Created by artem on 16.10.17.
 */
@RunWith(Parameterized.class)
public class TestUsersTable {

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, "Artem", "Samoshkin"}
        });
    }

    @Parameterized.Parameter(value = 0)
    private int userId;
    @Parameterized.Parameter(value = 1)
    private String firstName;
    @Parameterized.Parameter(value = 2)
    private String secondName;

    private AppDatabase mDb;
    private UserDao mUserDao;
    private User user = new User(userId, firstName, secondName);
    ;

    @Before
    public void init() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(appContext, AppDatabase.class)
                .build();
        mUserDao = mDb.userDao();
        mUserDao.insertUser(user);
    }

    @Test
    public void userShouldBeSaved() throws Exception {
        assertEquals(1, mUserDao.getCount());
    }

    @Test
    public void checkUserData() throws Exception {
        assertEquals(firstName, mUserDao.getUsersById(userId).getFirstName());
    }

    @Test
    public void userShouldBeDeleted() throws Exception {
        mUserDao.removeAll();
        assertEquals(0, mUserDao.getCount());
    }

    @Test
    public void updateUser() throws Exception {
        user.setUserId(1);
        user.setFirstName("Mira");
        user.setLastName("Samoshkina");
        mUserDao.updateUser(user);
        assertEquals(1, mUserDao.getCount());
    }

    @After
    public void closeDb() {
        mDb.close();
    }
}
