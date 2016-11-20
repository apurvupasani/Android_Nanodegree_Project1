package com.udacity.apurv.android_nanodegree_project1.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.udacity.apurv.android_nanodegree_project1.adapter.MovieArrayAdapter;
import com.udacity.apurv.android_nanodegree_project1.entities.MovieRecord;
import com.udacity.apurv.android_nanodegree_project1.util.HttpConnectionUtils;
import com.udacity.apurv.android_nanodegree_project1.util.MovieDBJsonUtils;
import com.udacity.apurv.android_nanodegree_project1.util.MovieDBUtils;

import java.net.URL;
import java.util.List;

import lombok.NonNull;

public class FetchMoviesTask extends AsyncTask<String, Void, List<MovieRecord>> {

    private MovieArrayAdapter movieArrayAdapter;
    private Context context;

    public FetchMoviesTask(@NonNull final MovieArrayAdapter movieArrayAdapter, Context context) {
        this.movieArrayAdapter = movieArrayAdapter;
        this.context = context;
    }
    private String LOG_TAG = FetchMoviesTask.class.getSimpleName();

    @Override
    protected List<MovieRecord> doInBackground(String... params) {
        String url = MovieDBUtils.getPopularMoviesURL(params[0]);
        Log.v(LOG_TAG, url);
        try {
            String popularMovieJson = HttpConnectionUtils.getAPIData(new URL(url));
            Log.v(LOG_TAG, popularMovieJson);
            List<MovieRecord> recordList = MovieDBJsonUtils.parseMovieDBJSON(popularMovieJson);
            Log.v(LOG_TAG, recordList.toString());
            return recordList;
        } catch (Exception e) {
            Log.e(LOG_TAG, "Exception while parsing message", e);
            return null;
        }

    }

    @Override
    protected void onPostExecute(List<MovieRecord> result) {
        if (result != null) {
            movieArrayAdapter.clear();
            for (MovieRecord record: result) {
                movieArrayAdapter.add(record);
            }
        } else {
            Toast.makeText(context, "Unable to show the movie information", Toast.LENGTH_LONG).show();
        }
    }
}