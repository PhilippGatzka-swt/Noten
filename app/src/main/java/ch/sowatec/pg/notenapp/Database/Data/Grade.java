package ch.sowatec.pg.notenapp.Database.Data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Grade {
    @PrimaryKey(autoGenerate = true)
    public int grade_id;
    @ColumnInfo(name = "grade")
    public float grade;
    @Embedded
    public Subject subject;
    @ColumnInfo(name = "date")
    public String date;

    public Grade(float grade, Subject subject, String date) {
        this.grade = grade;
        this.subject = subject;
        this.date = date;
    }

    public String getDate() {
        String[] x = date.split("¢");
        return x[2] + "." + x[1] + "." + x[0];
    }


    @NonNull
    @Override
    public String toString() {
        return grade + " " + subject + " " + getDate();
    }
}
