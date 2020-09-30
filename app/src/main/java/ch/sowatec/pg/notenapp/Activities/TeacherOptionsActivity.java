package ch.sowatec.pg.notenapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import ch.sowatec.pg.notenapp.R;

public class TeacherOptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_options);
    }

    public void buttonactionStartAddLehrperson(View view) {
        Intent i = new Intent(this, CreateTeacherActivity.class);
        startActivity(i);
    }

    public void buttonactionStartImport(View view) {
        Log.e("buttonactionStartAddLehrperson","Function has not been implemented yet");
    }

    public void buttonactionStartExport(View view) {
        Log.e("buttonactionStartAddLehrperson","Function has not been implemented yet");
    }

    public void buttonactionStartViewLehrperson(View view) {
        Intent i = new Intent(this, TeacherListActivity.class);
        startActivity(i);
    }
}