package ch.sowatec.pg.notenapp.Activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import ch.sowatec.pg.notenapp.Database.Data.Subject;
import ch.sowatec.pg.notenapp.Database.Data.Teacher;
import ch.sowatec.pg.notenapp.Database.DatabaseAdapter;
import ch.sowatec.pg.notenapp.Database.DatabaseClient;
import ch.sowatec.pg.notenapp.R;

public class CreateSubjectActivity extends AppCompatActivity {

    private Spinner spinner_teachers;
    private EditText editText_name;

    private List<Teacher> teacherList = new ArrayList<>();
    private Subject subject;
    private DatabaseAdapter insertAdapter = new DatabaseAdapter(new Runnable() {
        @Override
        public void run() {
            DatabaseClient.getInstance(CreateSubjectActivity.this).getAppDatabase().subjectDao().insert(subject);
        }
    }, new Runnable() {
        @Override
        public void run() {
            finish();
        }
    });
    private DatabaseAdapter teachersAdapter = new DatabaseAdapter(new Runnable() {
        @Override
        public void run() {
            teacherList = DatabaseClient.getInstance(CreateSubjectActivity.this).getAppDatabase().teacherDao().getAll();
        }
    }, new Runnable() {
        @Override
        public void run() {
            fillSpinner();
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_subject);
        spinner_teachers = findViewById(R.id.spinner_teachers);
        editText_name = findViewById(R.id.editText_name);
        teachersAdapter.execute();
    }

    private void fillSpinner() {
        ArrayAdapter<Teacher> arrayAdapter = new ArrayAdapter<Teacher>(CreateSubjectActivity.this, R.layout.support_simple_spinner_dropdown_item, teacherList);
        spinner_teachers.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.done_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item_add) {
            buildSubject();
            insertAdapter.execute();

        }

        return super.onOptionsItemSelected(item);
    }

    private void buildSubject() {
        String name = editText_name.getText().toString();
        Teacher teacher = (Teacher) spinner_teachers.getSelectedItem();
        subject = new Subject(name, teacher);
    }

}