package ch.sowatec.pg.notenapp.Activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import ch.sowatec.pg.notenapp.Database.Data.Teacher;
import ch.sowatec.pg.notenapp.Database.DatabaseAdapter;
import ch.sowatec.pg.notenapp.Database.DatabaseClient;
import ch.sowatec.pg.notenapp.R;

public class CreateTeacherActivity extends AppCompatActivity {
    /* XML VIEW COMPONENTS */
    private EditText editText_vorname;
    private EditText editText_nachname;

    /* VARIABLES */
    private Teacher teacher;
    private DatabaseAdapter databaseAdapter = new DatabaseAdapter(new Runnable() {
        @Override
        public void run() {
            DatabaseClient.getInstance(CreateTeacherActivity.this).getAppDatabase().teacherDao().insert(teacher);
        }
    }, new Runnable() {
        @Override
        public void run() {
            finish();
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_teacher);
        editText_vorname = findViewById(R.id.editText_vorname);
        editText_nachname = findViewById(R.id.editText_nachname);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.done_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item_add) {
            buildTeacher();
            databaseAdapter.execute();
        }
        return super.onOptionsItemSelected(item);
    }

    private void buildTeacher() {
        String firstname = editText_vorname.getText().toString();
        String lastname = editText_nachname.getText().toString();
        /* VALIDATION */

        teacher = new Teacher(firstname, lastname);
    }
}