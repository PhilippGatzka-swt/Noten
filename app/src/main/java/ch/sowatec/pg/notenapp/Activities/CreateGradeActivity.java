package ch.sowatec.pg.notenapp.Activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import ch.sowatec.pg.notenapp.Database.Data.Grade;
import ch.sowatec.pg.notenapp.Database.Data.Subject;
import ch.sowatec.pg.notenapp.Database.DatabaseAdapter;
import ch.sowatec.pg.notenapp.Database.DatabaseClient;
import ch.sowatec.pg.notenapp.R;

public class CreateGradeActivity extends AppCompatActivity {

    private EditText editText_note;
    private Spinner spinner_subjects;
    private DatePicker datePicker_date;

    private Grade grade;
    private DatabaseAdapter databaseAdapter = new DatabaseAdapter(new Runnable() {
        @Override
        public void run() {
            DatabaseClient.getInstance(CreateGradeActivity.this).getAppDatabase().gradeDao().insert(grade);
        }
    }, new Runnable() {
        @Override
        public void run() {
            finish();
        }
    });
    private List<Subject> subjectList;
    private DatabaseAdapter subjectsAdapter = new DatabaseAdapter(new Runnable() {
        @Override
        public void run() {
            subjectList = DatabaseClient.getInstance(CreateGradeActivity.this).getAppDatabase().subjectDao().getAll();
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
        setContentView(R.layout.activity_create_grade);
        editText_note = findViewById(R.id.editText_note);
        spinner_subjects = findViewById(R.id.spinner_subjects);
        datePicker_date = findViewById(R.id.datepicker_date);
        subjectsAdapter.execute();
    }

    private void fillSpinner() {
        ArrayAdapter<Subject> arrayAdapter = new ArrayAdapter<Subject>(CreateGradeActivity.this, R.layout.support_simple_spinner_dropdown_item, subjectList);
        spinner_subjects.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.done_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item_add) {
            buildGrade();
            databaseAdapter.execute();
        }
        return super.onOptionsItemSelected(item);
    }


    private void buildGrade() {
        String date = datePicker_date.getYear() + "¢" + datePicker_date.getMonth() + "¢" + datePicker_date.getDayOfMonth();

        float number = Float.parseFloat(editText_note.getText().toString());
        grade = new Grade(number, (Subject) spinner_subjects.getSelectedItem(), date);
    }
}