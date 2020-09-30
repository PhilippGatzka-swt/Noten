package ch.sowatec.pg.notenapp;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
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
        if (item.getItemId() == R.id.item_delete) {
            Log.e("onOptionsItemSelected", "Function has not been implemented yet");
        }
        return super.onOptionsItemSelected(item);
    }


}