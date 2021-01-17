package com.example.runningappv3;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class RunViewModel extends AndroidViewModel {

    private MyRepository repository;

    private final LiveData<List<Run>> allRuns;

    public RunViewModel(Application application) {
        super(application);
        repository = new MyRepository(application);
        allRuns = repository.getAllRuns();
    }

    LiveData<List<Run>> getAllRuns() { return allRuns; }

    public void insert(Run run) { repository.insert(run); }
}

