package ch.sowatec.pg.notenapp.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ch.sowatec.pg.notenapp.Database.Data.Grade;
import ch.sowatec.pg.notenapp.Database.Data.Subject;
import ch.sowatec.pg.notenapp.Database.Data.Teacher;
import ch.sowatec.pg.notenapp.Database.DatabaseObjects.GradeDao;
import ch.sowatec.pg.notenapp.Database.DatabaseObjects.SubjectDao;
import ch.sowatec.pg.notenapp.Database.DatabaseObjects.TeacherDao;

@Database(entities = {Teacher.class, Subject.class, Grade.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TeacherDao teacherDao();

    public abstract SubjectDao subjectDao();

    public abstract GradeDao gradeDao();
}
