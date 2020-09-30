package ch.sowatec.pg.notenapp.Activities;

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

import ch.sowatec.pg.notenapp.Database.Data.Subject;
import ch.sowatec.pg.notenapp.Database.Data.Teacher;
import ch.sowatec.pg.notenapp.Database.DatabaseAdapter;
import ch.sowatec.pg.notenapp.Database.DatabaseClient;
import ch.sowatec.pg.notenapp.R;

public class SubjectListActivity extends AppCompatActivity {

    private LinearLayout listview_subjects;
    private Subject subject;
    private String newName;
    private DatabaseAdapter deleteAdapter = new DatabaseAdapter(new Runnable() {
        @Override
        public void run() {
            Log.e("TAG", "DELETED TEACHER");
            DatabaseClient.getInstance(SubjectListActivity.this).getAppDatabase().subjectDao().delete(subject);
        }
    });
    private DatabaseAdapter updateAdapter = new DatabaseAdapter(new Runnable() {
        @Override
        public void run() {
            DatabaseClient.getInstance(SubjectListActivity.this).getAppDatabase().subjectDao().update(subject.subject_id, newName);
        }
    });
    private List<Subject> subjectList;
    private DatabaseAdapter databaseAdapter = new DatabaseAdapter(new Runnable() {
        @Override
        public void run() {
            subjectList = DatabaseClient.getInstance(SubjectListActivity.this).getAppDatabase().subjectDao().getAll();
        }
    }, new Runnable() {
        @Override
        public void run() {
            fillList();
        }
    });
    private List<Teacher> teacherList;
    private DatabaseAdapter teachersAdapter = new DatabaseAdapter(new Runnable() {
        @Override
        public void run() {
            teacherList = DatabaseClient.getInstance(SubjectListActivity.this).getAppDatabase().teacherDao().getAll();
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list);
        listview_subjects = findViewById(R.id.listview_subjects);
        teachersAdapter.execute();
        databaseAdapter.execute();
    }

    private void fillList() {
        for (final Subject subject : subjectList) {
            TextView listItem = new TextView(this);
            listItem.setText(subject.toString());
            listItem.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            listItem.setPadding(10, 10, 10, 10);
            listview_subjects.addView(listItem);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        ArrayAdapter<Subject> arrayAdapter = new ArrayAdapter<Subject>(SubjectListActivity.this, R.layout.support_simple_spinner_dropdown_item, subjectList);
        final Spinner spinner = new Spinner(SubjectListActivity.this);
        spinner.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        spinner.setAdapter(arrayAdapter);
        if (item.getItemId() == R.id.item_delete) {
            AlertDialog.Builder builder = new AlertDialog.Builder(SubjectListActivity.this)
                    .setView(spinner)
                    .setTitle("Löschen")
                    .setMessage("Welchen Eintrag wollen sie löschen?")
                    .setPositiveButton("Löschen", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            subject = (Subject) spinner.getSelectedItem();
                            deleteAdapter.execute();
                            refresh();
                        }
                    });
            builder.create().show();
        } else if (item.getItemId() == R.id.item_edit) {
            LinearLayout container = new LinearLayout(this);
            LinearLayout line1 = new LinearLayout(this);
            TextView _name = new TextView(this);
            final EditText name = new EditText(this);
            _name.setText("Fach");
            _name.setPadding(30, 0, 20, 0);
            _name.setMinWidth(450);
            name.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            line1.addView(_name);
            line1.addView(name);
            container.setOrientation(LinearLayout.VERTICAL);
            container.setPadding(10, 10, 10, 10);
            container.addView(spinner);
            container.addView(line1);
            AlertDialog.Builder builder = new AlertDialog.Builder(SubjectListActivity.this)
                    .setView(container)
                    .setTitle("Bearbeiten")
                    .setMessage("Welchen Eintrag wollen sie bearbeiten?")
                    .setPositiveButton("Speichern", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            subject = (Subject) spinner.getSelectedItem();
                            newName = name.getText().toString();
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
        Intent i = new Intent(this, SubjectListActivity.class);
        startActivity(i);
    }


}