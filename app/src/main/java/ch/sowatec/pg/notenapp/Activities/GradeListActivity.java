package ch.sowatec.pg.notenapp.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import ch.sowatec.pg.notenapp.Database.Data.Grade;
import ch.sowatec.pg.notenapp.Database.Data.Subject;
import ch.sowatec.pg.notenapp.Database.DatabaseAdapter;
import ch.sowatec.pg.notenapp.Database.DatabaseClient;
import ch.sowatec.pg.notenapp.R;

public class GradeListActivity extends AppCompatActivity {

    private LinearLayout listview_grades;
    private Grade grade;
    private float note;
    private String date;
    private DatabaseAdapter deleteAdapter = new DatabaseAdapter(new Runnable() {
        @Override
        public void run() {
            Log.e("TAG", "DELETED TEACHER");
            DatabaseClient.getInstance(GradeListActivity.this).getAppDatabase().gradeDao().delete(grade);
        }
    });
    private DatabaseAdapter updateAdapter = new DatabaseAdapter(new Runnable() {
        @Override
        public void run() {
            DatabaseClient.getInstance(GradeListActivity.this).getAppDatabase().gradeDao().update(grade.grade_id, note, date);
        }
    });
    private List<Grade> gradesList;
    private DatabaseAdapter databaseAdapter = new DatabaseAdapter(new Runnable() {
        @Override
        public void run() {
            gradesList = DatabaseClient.getInstance(GradeListActivity.this).getAppDatabase().gradeDao().getAll();
        }
    }, new Runnable() {
        @Override
        public void run() {
            fillList();
        }
    });

    private List<Subject> subjectList;
    private DatabaseAdapter subjectsAdapter = new DatabaseAdapter(new Runnable() {
        @Override
        public void run() {
            subjectList = DatabaseClient.getInstance(GradeListActivity.this).getAppDatabase().subjectDao().getAll();
        }
    });


    private void fillList() {
        for (final Grade grade2 : gradesList) {
            TextView listItem = new TextView(this);
            listItem.setText(grade2.toString());
            listItem.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            listItem.setPadding(10, 10, 10, 10);
            listview_grades.addView(listItem);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_list);
        listview_grades = findViewById(R.id.listview_grades);
        subjectsAdapter.execute();
        databaseAdapter.execute();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        ArrayAdapter<Grade> arrayAdapter = new ArrayAdapter<Grade>(GradeListActivity.this, R.layout.support_simple_spinner_dropdown_item, gradesList);
        final Spinner spinner = new Spinner(GradeListActivity.this);
        spinner.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        spinner.setAdapter(arrayAdapter);
        if (item.getItemId() == R.id.item_delete) {
            AlertDialog.Builder builder = new AlertDialog.Builder(GradeListActivity.this)
                    .setView(spinner)
                    .setTitle("Löschen")
                    .setMessage("Welchen Eintrag wollen sie löschen?")
                    .setPositiveButton("Löschen", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            grade = (Grade) spinner.getSelectedItem();
                            deleteAdapter.execute();
                            refresh();
                        }
                    });
            builder.create().show();
        } else if (item.getItemId() == R.id.item_edit) {
            LinearLayout container = new LinearLayout(this);
            LinearLayout line1 = new LinearLayout(this);
            LinearLayout line2 = new LinearLayout(this);
            TextView _number = new TextView(this);
            TextView _date = new TextView(this);
            final EditText number = new EditText(this);
            final DatePicker datePicker = new DatePicker(this);
            _number.setText("Note");
            _number.setPadding(30, 0, 20, 0);
            _number.setMinWidth(450);
            number.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            line1.addView(_number);
            line1.addView(number);
            line2.addView(_date);
            line2.addView(datePicker);
            container.setOrientation(LinearLayout.VERTICAL);
            container.setPadding(10, 10, 10, 10);
            container.addView(spinner);
            container.addView(line1);
            container.addView(line2);
            AlertDialog.Builder builder = new AlertDialog.Builder(GradeListActivity.this)
                    .setView(container)
                    .setTitle("Bearbeiten")
                    .setMessage("Welchen Eintrag wollen sie bearbeiten?")
                    .setPositiveButton("Speichern", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            grade = (Grade) spinner.getSelectedItem();
                            date = datePicker.getYear() + "¢" + datePicker.getMonth() + "¢" + datePicker.getDayOfMonth();
                            note = Float.parseFloat(number.getText().toString());
                            updateAdapter.execute();
                            refresh();
                        }
                    });
            builder.create().show();
        } else if (item.getItemId() == R.id.item_email) {
            AlertDialog alertDialog = new AlertDialog.Builder(GradeListActivity.this)
                    .setTitle("Email")
                    .setMessage("Welche Note wollen sie verschicken?")
                    .setView(spinner)
                    .setPositiveButton("Mail", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Grade g = (Grade) spinner.getSelectedItem();
                            Intent intent = new Intent(Intent.ACTION_SEND);
                            intent.setType("message/rfc822");
                            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"peter@sowatec.com"});
                            intent.putExtra(Intent.EXTRA_SUBJECT, "gatzka58@gmail.com");
                            String extraText = "Hallo Kai \n Ich habe gerade eine neue Note eingetragen:\n\n";
                            String greetings = "\n\nLiebe Grüsse \n Philipp Gatzka";
                            intent.putExtra(Intent.EXTRA_TEXT, extraText + "Note: " + g.grade + "\nFach: " + g.subject.name + "\nDatum: " + g.getDate() + greetings);
                            intent.setPackage("com.google.android.gm");
                            startActivity(intent);
                        }
                    })
                    .create();
            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);

    }

    private void refresh() {
        finish();
        Intent i = new Intent(this, GradeListActivity.class);
        startActivity(i);
    }
}