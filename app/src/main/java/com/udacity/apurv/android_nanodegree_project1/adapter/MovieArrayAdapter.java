package com.udacity.apurv.android_nanodegree_project1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.udacity.apurv.android_nanodegree_project1.R;
import com.udacity.apurv.android_nanodegree_project1.constants.MovieDBAPIConstants;
import com.udacity.apurv.android_nanodegree_project1.entities.MovieRecord;

import java.util.List;

/**
 * Created by Apurv on 11/15/2016.
 */

public class MovieArrayAdapter extends ArrayAdapter<MovieRecord> {
    private static final String LOG_TAG = MovieArrayAdapter.class.getSimpleName();

    public MovieArrayAdapter(Context context, int layout, int id, List<MovieRecord> movieRecordList) {
        super(context, layout, id, movieRecordList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieRecord movieRecord = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item_movie, parent, false);
        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.grid_item_movie_imageview);
        Picasso.with(getContext()).load(MovieDBAPIConstants.MOVIE_DB_IMAGE_BASE_URL_MAIN + movieRecord.getMovieImageThumbnailPath()).into(imageView);
        return convertView;
    }
}
