package com.example.runningappv3;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MyRepository {
    private RunDao mRunDao;
    private LiveData<List<Run>> allRuns;

    MyRepository(Application application) {
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        mRunDao = db.runDao();
        allRuns = mRunDao.getRunById();
    }

    LiveData<List<Run>> getAllRuns() {
        return allRuns;
    }


    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    public void insert (Run run) {
        new insertAsyncTask(mRunDao).execute(run);
    }

    private static class insertAsyncTask extends AsyncTask<Run, Void, Void> {

        private RunDao mAsyncTaskDao;

        insertAsyncTask(RunDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Run... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
