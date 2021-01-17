package com.example.runningappv3;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "run_table")
public class Run implements Parcelable {


    protected Run(Parcel in) {
        id = in.readLong();
        distance = in.readDouble();
        time = in.readLong();
        speed = in.readDouble();
    }

    public static final Creator<Run> CREATOR = new Creator<Run>() {
        @Override
        public Run createFromParcel(Parcel in) {
            return new Run(in);
        }

        @Override
        public Run[] newArray(int size) {
            return new Run[size];
        }
    };

    public long getId() {
        return id;
    }

    @PrimaryKey (autoGenerate=true)
    @NonNull
    private long id;
    private double distance;
    private long time;
    private double speed;

    public Run(double distance, long time, double speed) {
        this.distance = distance;
        this.time = time;
        this.speed = speed;
    }

    public void setId(long id) {
        this.id = id;
    }
    public double getDistance() {
        return distance;
    }
    public long getTime() {
        return time;
    }
    public double getSpeed() {return speed; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeDouble(distance);
        dest.writeLong(time);
        dest.writeDouble(speed);
    }
}