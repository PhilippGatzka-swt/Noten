package ch.sowatec.pg.notenapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import ch.sowatec.pg.notenapp.R;
import ch.sowatec.pg.notenapp.SubjectListActivity;

public class SubjectOptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_options);
    }

    public void buttonactionStartAddFach(View view) {
        Intent i = new Intent(this, CreateSubjectActivity.class);
        startActivity(i);
    }

    public void buttonactionStartViewFach(View view) {
        Intent i = new Intent(this, SubjectListActivity.class);
        startActivity(i);
    }

    public void buttonactionStartExport(View view) {
    }

    public void buttonactionStartImport(View view) {
    }
}