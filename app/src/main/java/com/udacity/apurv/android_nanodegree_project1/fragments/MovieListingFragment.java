package com.udacity.apurv.android_nanodegree_project1.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.udacity.apurv.android_nanodegree_project1.R;
import com.udacity.apurv.android_nanodegree_project1.activities.MovieDetailActivity;
import com.udacity.apurv.android_nanodegree_project1.adapter.MovieArrayAdapter;
import com.udacity.apurv.android_nanodegree_project1.constants.ActivityConstants;
import com.udacity.apurv.android_nanodegree_project1.entities.MovieRecord;
import com.udacity.apurv.android_nanodegree_project1.task.FetchMoviesTask;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieListingFragment extends Fragment {

    private static final String LOG_TAG = MovieListingFragment.class.getSimpleName();
    private MovieArrayAdapter movieArrayAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Create some dummy data for the ListView.  Here's a sample weekly forecast

        final List<MovieRecord> movieRecords = new ArrayList<MovieRecord>();
        movieArrayAdapter = new MovieArrayAdapter(getActivity(), R.layout.movie_listing_fragment, R.id.grid_item_movie_imageview, movieRecords);

        View rootView = inflater.inflate(R.layout.movie_listing_fragment, container, false);
        GridView gridView = (GridView) rootView.findViewById(R.id.gridview_movies);
        if (gridView != null && movieArrayAdapter != null) {
            gridView.setAdapter(movieArrayAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    MovieRecord record = movieArrayAdapter.getItem(position);
                    Intent intent = new Intent(getActivity(), MovieDetailActivity.class)
                            .putExtra(ActivityConstants.MOVIE_RECORD_INTENT, record);
                    startActivity(intent);
                }
            });
        }
        return rootView;
    }
    @Override
    public void onStart() {
        super.onStart();
        performPopularMovieTaskExecution();
    }
    private void performPopularMovieTaskExecution() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String sortOrder = preferences.getString(getString(R.string.pref_sort_order_key), "");
        FetchMoviesTask task = new FetchMoviesTask(movieArrayAdapter, getContext());
        task.execute(sortOrder);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_movielistingfragment, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}