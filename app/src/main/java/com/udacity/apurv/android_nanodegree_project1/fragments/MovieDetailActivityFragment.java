package com.udacity.apurv.android_nanodegree_project1.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.apurv.android_nanodegree_project1.R;
import com.udacity.apurv.android_nanodegree_project1.constants.ActivityConstants;
import com.udacity.apurv.android_nanodegree_project1.constants.MovieDBAPIConstants;
import com.udacity.apurv.android_nanodegree_project1.entities.MovieRecord;

import static com.udacity.apurv.android_nanodegree_project1.constants.MovieDBAPIConstants.MOVIE_DB_ERROR_MESSAGE;
import static com.udacity.apurv.android_nanodegree_project1.util.MovieDBJsonUtils.convertDateToProperFormat;

/**
 * This fragment is used to populate the detail view on load.
 */
public class MovieDetailActivityFragment extends Fragment {

    private static final String LOG_TAG = MovieDetailActivityFragment.class.getSimpleName();

    private static final String VOTE_AVERAGE_MAX_STR = " / 10";

    public MovieDetailActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_movie_detail, container, false);

        Intent intent = getActivity().getIntent();
        Log.v(LOG_TAG, "In movie fragment view");
        if (intent != null && intent.hasExtra(ActivityConstants.MOVIE_RECORD_INTENT)) {
            MovieRecord movieRecord = (MovieRecord) intent.getSerializableExtra(ActivityConstants.MOVIE_RECORD_INTENT);

            //Set the information in appropriate fields
            ((TextView) rootView.findViewById(R.id.original_title)).setText(movieRecord.getOriginalTitle());
            ((TextView) rootView.findViewById(R.id.overview)).setText(movieRecord.getOverview());
            ((TextView) rootView.findViewById(R.id.release_date)).setText(convertDateToProperFormat(movieRecord.getReleaseDate()));
            ((TextView) rootView.findViewById(R.id.user_rating)).setText(movieRecord.getUserRating() + VOTE_AVERAGE_MAX_STR);
            ImageView imageView = (ImageView) rootView.findViewById(R.id.poster_image);
            Picasso.with(getContext()).load(MovieDBAPIConstants.MOVIE_DB_IMAGE_BASE_URL_DETAIL + movieRecord.getMovieImageThumbnailPath()).into(imageView);

        } else {
            Toast.makeText(getContext(), MOVIE_DB_ERROR_MESSAGE, Toast.LENGTH_LONG).show();
        }

        return rootView;
    }
}
