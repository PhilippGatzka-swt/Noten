package ch.sowatec.pg.notenapp.Database.Data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Teacher {
    @PrimaryKey(autoGenerate = true)
    public int teacher_id;
    @ColumnInfo(name = "firstname")
    public String firstname;
    @ColumnInfo(name = "lastname")
    public String lastname;

    public Teacher(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @NonNull
    @Override
    public String toString() {
        return firstname + " " + lastname;
    }
}
