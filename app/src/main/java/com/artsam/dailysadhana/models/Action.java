package com.artsam.dailysadhana.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.artsam.dailysadhana.db.AppDatabase;

import java.sql.Timestamp;
import java.sql.Date;

/**
 * Created by Artem on 13.10.17.
 */

@Entity(tableName = AppDatabase.TABLE_NAME_ACTIONS,
        foreignKeys = {
                @ForeignKey(
                        entity = User.class,
                        parentColumns = "userId",
                        childColumns = "userId",
                        onDelete = ForeignKey.CASCADE)},
        indices = {
                @Index("actId"),
                @Index(value = {"date", "startTime", "endTime"}, unique = true)})
public class Action {

    @PrimaryKey(autoGenerate = true)
    private int actId;
    private int userId;
    private String name;
    private Date date;
    private Timestamp startTime;
    private Timestamp endTime;
    private String comment;

    public Action(int userId, String name, Date date, Timestamp startTime, Timestamp endTime) {
        this.userId = userId;
        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
