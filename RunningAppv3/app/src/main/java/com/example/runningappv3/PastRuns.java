package com.example.runningappv3;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PastRuns extends AppCompatActivity {
    private RunViewModel mRunViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        RecyclerView recyclerView = findViewById(R.id.RunsRecyclerView);
        final PastRunsAdapter adapter = new PastRunsAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get a new or existing ViewModel from the ViewModelProvider.
        mRunViewModel = ViewModelProviders.of(this).get(RunViewModel.class);

        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mRunViewModel.getAllRuns().observe(this, new Observer<List<Run>>() {
            @Override
            public void onChanged(@Nullable final List<Run> runs) {
                adapter.setRuns(runs);
            }
        });
    }


}
