package ch.sowatec.pg.notenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import kotlin.NotImplementedError;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void buttonactionStartFaecher(View view) {
        Log.e("buttonactionStartFaecher", "Function has not been implemented yet");
    }

    public void buttonactionStartNoten(View view) {
        Log.e("buttonactionStartNoten", "Function has not been implemented yet");
    }

    public void buttonactionStartLehrpersonen(View view) {
        Intent i = new Intent(this, TeacherOptionsActivity.class);
        startActivity(i);
    }
}