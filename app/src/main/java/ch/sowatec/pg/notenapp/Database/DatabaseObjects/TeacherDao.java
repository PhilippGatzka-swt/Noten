package ch.sowatec.pg.notenapp.Database.DatabaseObjects;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ch.sowatec.pg.notenapp.Database.Data.Teacher;

@Dao
public interface TeacherDao {
    @Insert
    void insert(Teacher teacher);

    @Query("SELECT * FROM Teacher WHERE teacher_id == :key")
    Teacher get(int key);

    @Query("SELECT * FROM Teacher")
    List<Teacher> getAll();

    @Delete
    void delete(Teacher teacher);

}
