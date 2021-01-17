package com.example.runningappv3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.runningappv3.R;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ExampleBottomSheetDialog.BottomSheetListener{
    private EditText mEditTextView;
    private RunViewModel mRunViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRunViewModel = new RunViewModel(getApplication());
        mEditTextView = findViewById(R.id.DistanceTimeSpeedEditText);
        ExampleBottomSheetDialog.mListener=this::onButtonClicked;

        Button buttonOpenBottomSheet = findViewById(R.id.setGoalButton);
        Button runButton = findViewById(R.id.runButton);
        Button activityButton = findViewById(R.id.pastRunsButton);

        buttonOpenBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExampleBottomSheetDialog bottomSheet = new ExampleBottomSheetDialog();
                bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");
            }
        });

        runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapsActivityCurrentPlace.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });

        activityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pastRunsIntent = new Intent(MainActivity.this, PastRuns.class);
                startActivity(pastRunsIntent);
            }
        });




    }


    @Override
    public void onButtonClicked(String text) {

        mEditTextView.setHint(text);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Run run = data.getParcelableExtra(MapsActivityCurrentPlace.EXTRA_REPLY);
            mRunViewModel.insert(run);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.run_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}