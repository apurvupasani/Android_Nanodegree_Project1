package com.udacity.apurv.android_nanodegree_project1.util;

import com.udacity.apurv.android_nanodegree_project1.entities.MovieRecord;

import org.json.JSONException;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static junit.framework.Assert.assertTrue;

/**
 * Created by Apurv on 11/18/2016.
 */

public class MovieDBJsonUtilsTest {

    @Test
    public void validPopularMovieDBJsonUtilsTest() throws IOException, JSONException {
        MovieDBJsonUtils movieDBJsonUtils = new MovieDBJsonUtils();
        List<MovieRecord> records = movieDBJsonUtils.parseMovieDBJSON(readInputString("validpopular.json"));
        System.out.println(records);
        assertTrue(1==1);
    }

    @Test
    public void validTopRatedMovieDBJsonUtilsTest() throws IOException, JSONException {
        MovieDBJsonUtils movieDBJsonUtils = new MovieDBJsonUtils();
        List<MovieRecord> records = movieDBJsonUtils.parseMovieDBJSON(readInputString("validtoprated.json"));
        System.out.println(records);
        assertTrue(1==1);
    }

    private String readInputString(String filename) throws IOException {
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(filename), "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();

        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);
        System.out.println(responseStrBuilder.toString());
        return responseStrBuilder.toString();
    }
}
