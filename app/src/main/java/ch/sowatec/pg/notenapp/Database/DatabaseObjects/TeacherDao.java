package ch.sowatec.pg.notenapp.Database.DatabaseObjects;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import ch.sowatec.pg.notenapp.Database.Data.Teacher;

@Dao
public interface TeacherDao {
    @Insert
    void insert(Teacher teacher);

    @Query("SELECT * FROM Teacher WHERE teacher_id == :key")
    Teacher get(int key);

    @Delete
    void delete(Teacher teacher);

}
