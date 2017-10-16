package com.artsam.dailysadhana.db;

import android.arch.persistence.room.TypeConverter;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Artem on 16.10.17.
 */

public class Converters {
    @TypeConverter
    public static Long dtToLong(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static Date dtFromLong(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long tshToLong(Timestamp timestamp) {
        return timestamp == null ? null : timestamp.getTime();
    }

    @TypeConverter
    public static Timestamp tshFromLong(Long value) {
        return value == null ? null : new Timestamp(value);
    }
}
