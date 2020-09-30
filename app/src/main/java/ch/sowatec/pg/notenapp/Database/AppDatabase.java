package ch.sowatec.pg.notenapp.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ch.sowatec.pg.notenapp.Database.Data.Teacher;
import ch.sowatec.pg.notenapp.Database.DatabaseObjects.TeacherDao;

@Database(entities = {Teacher.class}, version = 1)


public abstract class AppDatabase extends RoomDatabase {
    public abstract TeacherDao teacherDao();
}
