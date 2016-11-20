package com.udacity.apurv.android_nanodegree_project1.util;

import android.net.Uri;
import android.util.Log;

import com.google.common.collect.ImmutableMap;
import com.udacity.apurv.android_nanodegree_project1.BuildConfig;
import com.udacity.apurv.android_nanodegree_project1.constants.MovieDBAPIConstants;

import java.util.Map;

import lombok.NonNull;

/**
 * Created by Apurv on 11/18/2016.
 */

public class MovieDBUtils {
    private static String LOG_TAG = MovieDBUtils.class.getSimpleName();

    private static final Map<String, String> apiMap = ImmutableMap.of(MovieDBAPIConstants.POPULAR_MOVIES_OPTION, MovieDBAPIConstants.POPULAR_MOVIES_API,
                                                                      MovieDBAPIConstants.TOP_RATED_MOVIES_OPTION, MovieDBAPIConstants.TOP_RATED_MOVIES_API);

    /**
     * This method returns the
     * @param option one of the criteria to fetch the movie
     * @return String Url string to fetch popular movie information
     * @throws IllegalArgumentException This is thrown in case the option does not match one of the existing movie type options
     */
    public static String getPopularMoviesURL(@NonNull final String option) throws IllegalArgumentException {
        if (!apiMap.containsKey(option)) {
            throw new IllegalArgumentException("Invalid option " + option);
        }
        Uri uri = Uri.parse(MovieDBAPIConstants.MOVIE_DB_BASE_URL + apiMap.get(option))
                .buildUpon()
                .appendQueryParameter(MovieDBAPIConstants.API_KEY, BuildConfig.MOVIE_DB_API_KEY)
                .build();
        Log.v(LOG_TAG, "URL is" + uri.toString());
        return uri.toString();



    }
}
