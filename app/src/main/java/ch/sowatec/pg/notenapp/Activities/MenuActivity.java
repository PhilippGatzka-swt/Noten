package ch.sowatec.pg.notenapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import ch.sowatec.pg.notenapp.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void buttonactionStartFaecher(View view) {
        Intent i = new Intent(this, SubjectOptionsActivity.class);
        startActivity(i);
    }

    public void buttonactionStartNoten(View view) {

    }

    public void buttonactionStartLehrpersonen(View view) {
        Intent i = new Intent(this, TeacherOptionsActivity.class);
        startActivity(i);
    }
}