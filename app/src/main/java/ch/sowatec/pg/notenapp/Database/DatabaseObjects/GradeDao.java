package ch.sowatec.pg.notenapp.Database.DatabaseObjects;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ch.sowatec.pg.notenapp.Database.Data.Grade;

@Dao
public interface GradeDao {
    @Insert
    void insert(Grade grade);

    @Query("SELECT * FROM Grade WHERE grade_id == :key")
    Grade get(int key);

    @Query("SELECT * FROM Grade")
    List<Grade> getAll();

    @Delete
    void delete(Grade grade);

    @Query("UPDATE Grade SET grade = :grade, date = :datum WHERE grade_id == :key")
    void update(int key, float grade, String datum);
}
