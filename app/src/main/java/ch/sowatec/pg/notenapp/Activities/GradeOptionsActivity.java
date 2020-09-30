package ch.sowatec.pg.notenapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import ch.sowatec.pg.notenapp.R;

public class GradeOptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_options);
    }

    public void buttonactionStartAddNote(View view) {
        Intent i = new Intent(this, CreateGradeActivity.class);
        startActivity(i);
    }

    public void buttonactionStartViewNote(View view) {
        Intent i = new Intent(this, GradeListActivity.class);
        startActivity(i);
    }

    public void buttonactionStartExport(View view) {
    }

    public void buttonactionStartImport(View view) {
    }
}