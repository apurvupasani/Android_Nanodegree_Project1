package com.udacity.apurv.android_nanodegree_project1.util;

import android.support.annotation.NonNull;

import com.udacity.apurv.android_nanodegree_project1.constants.MovieDBAPIConstants;
import com.udacity.apurv.android_nanodegree_project1.constants.MovieDBAPIResultConstants;
import com.udacity.apurv.android_nanodegree_project1.entities.MovieRecord;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Apurv on 11/18/2016.
 */

public class MovieDBJsonUtils {

    public static List<MovieRecord> parseMovieDBJSON(@NonNull final String jsonString) throws JSONException {
        List<MovieRecord> movieRecords = new ArrayList<>();

        JSONObject movieListObject = new JSONObject(jsonString);
        JSONArray results = movieListObject.getJSONArray(MovieDBAPIResultConstants.RESULTS_FIELD);

        for(int i = 0; i < results.length(); i++) {
            JSONObject result = results.getJSONObject(i);
            MovieRecord movieRecord = new MovieRecord();
            movieRecord.setMovieImageThumbnailPath(result.getString(MovieDBAPIResultConstants.THUMBNAIL_FIELD));
            movieRecord.setOriginalTitle(result.getString(MovieDBAPIResultConstants.ORIGINAL_TITLE_FIELD));
            movieRecord.setOverview(result.getString(MovieDBAPIResultConstants.OVERVIEW_FIELD));
            movieRecord.setReleaseDate(result.getString(MovieDBAPIResultConstants.RELEASE_DATE_FIELD));
            movieRecord.setUserRating(result.getDouble(MovieDBAPIResultConstants.USER_RATING_FIELD));
            movieRecords.add(movieRecord);
        }

        return movieRecords;
    }
}
