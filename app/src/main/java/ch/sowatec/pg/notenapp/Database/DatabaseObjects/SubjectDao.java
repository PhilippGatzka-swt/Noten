package ch.sowatec.pg.notenapp.Database.DatabaseObjects;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ch.sowatec.pg.notenapp.Database.Data.Subject;

@Dao
public interface SubjectDao {
    @Insert
    void insert(Subject subject);

    @Query("SELECT * FROM Subject WHERE subject_id == :key")
    Subject get(int key);

    @Query("SELECT * FROM Subject")
    List<Subject> getAll();

    @Delete
    void delete(Subject subject);

    @Query("UPDATE Subject SET name = :name WHERE subject_id == :key")
    void update(int key, String name);
}
