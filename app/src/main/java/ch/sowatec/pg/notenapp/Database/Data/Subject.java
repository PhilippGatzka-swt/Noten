package ch.sowatec.pg.notenapp.Database.Data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Subject {
    @PrimaryKey(autoGenerate = true)
    public int subject_id;
    @ColumnInfo(name = "name")
    public String name;
    @Embedded
    public Teacher teacher;

    public Subject(String name, Teacher teacher) {
        this.name = name;
        this.teacher = teacher;
    }

    @NonNull
    @Override
    public String toString() {
        return name + " " + teacher;
    }
}
