package ch.sowatec.pg.notenapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import ch.sowatec.pg.notenapp.Database.Data.Teacher;
import ch.sowatec.pg.notenapp.Database.DatabaseAdapter;
import ch.sowatec.pg.notenapp.Database.DatabaseClient;

public class TeacherListActivity extends AppCompatActivity {
    /* XML VIEW COMPONENTS */
    private LinearLayout listView_teachers;

    /* VARIABLES */
    private List<Teacher> teacherList;
    private Teacher teacher;
    private String fname, lname;
    private DatabaseAdapter databaseAdapter = new DatabaseAdapter(new Runnable() {
        @Override
        public void run() {
            teacherList = DatabaseClient.getInstance(TeacherListActivity.this).getAppDatabase().teacherDao().getAll();
        }
    }, new Runnable() {
        @Override
        public void run() {
            fillList();
        }
    });
    private DatabaseAdapter deleteAdapter = new DatabaseAdapter(new Runnable() {
        @Override
        public void run() {
            Log.e("TAG", "DELETED TEACHER");
            DatabaseClient.getInstance(TeacherListActivity.this).getAppDatabase().teacherDao().delete(teacher);
        }
    });
    private DatabaseAdapter updateAdapter = new DatabaseAdapter(new Runnable() {
        @Override
        public void run() {
            DatabaseClient.getInstance(TeacherListActivity.this).getAppDatabase().teacherDao().update(teacher.teacher_id, fname, lname);
        }
    });


    private void fillList() {
        for (Teacher teacher : teacherList) {
            TextView listItem = new TextView(this);
            listItem.setText(teacher.toString());
            listItem.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            listItem.setPadding(10, 10, 10, 10);
            listView_teachers.addView(listItem);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_list);
        listView_teachers = findViewById(R.id.listview_teachers);
        databaseAdapter.execute();
        listView_teachers.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final Teacher[] teachers = new Teacher[teacherList.size()];
        for (int i = 0; i < teachers.length; i++) {
            teachers[i] = teacherList.get(i);
        }
        ArrayAdapter<Teacher> arrayAdapter = new ArrayAdapter<Teacher>(TeacherListActivity.this, R.layout.support_simple_spinner_dropdown_item, teachers);
        final Spinner spinner = new Spinner(TeacherListActivity.this);
        spinner.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        spinner.setAdapter(arrayAdapter);
        if (item.getItemId() == R.id.item_delete) {
            AlertDialog.Builder builder = new AlertDialog.Builder(TeacherListActivity.this)
                    .setView(spinner)
                    .setTitle("Löschen")
                    .setMessage("Welchen Eintrag wollen sie löschen?")
                    .setPositiveButton("Löschen", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            teacher = (Teacher) spinner.getSelectedItem();
                            deleteAdapter.execute();
                            refresh();
                        }
                    });
            builder.create().show();
        } else if (item.getItemId() == R.id.item_edit) {
            LinearLayout container = new LinearLayout(this);
            LinearLayout line1 = new LinearLayout(this);
            LinearLayout line2 = new LinearLayout(this);
            TextView _vorname = new TextView(this);
            TextView _nachname = new TextView(this);
            final EditText firstname = new EditText(this);
            final EditText lastname = new EditText(this);
            _vorname.setText("Vorname");
            _nachname.setText("Nachname");
            _vorname.setPadding(30, 0, 20, 0);
            _nachname.setPadding(30, 0, 20, 0);
            _vorname.setMinWidth(450);
            _nachname.setMinWidth(450);
            firstname.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            lastname.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            line1.addView(_vorname);
            line1.addView(firstname);
            line2.addView(_nachname);
            line2.addView(lastname);
            container.setOrientation(LinearLayout.VERTICAL);
            container.setPadding(10, 10, 10, 10);
            container.addView(spinner);
            container.addView(line1);
            container.addView(line2);
            AlertDialog.Builder builder = new AlertDialog.Builder(TeacherListActivity.this)
                    .setView(container)
                    .setTitle("Bearbeiten")
                    .setMessage("Welchen Eintrag wollen sie bearbeiten?")
                    .setPositiveButton("Speichern", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            teacher = (Teacher) spinner.getSelectedItem();
                            fname = firstname.getText().toString();
                            lname = lastname.getText().toString();
                            updateAdapter.execute();
                            refresh();
                        }
                    });
            builder.create().show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void refresh() {
        finish();
        Intent i = new Intent(this, TeacherListActivity.class);
        startActivity(i);
    }


}