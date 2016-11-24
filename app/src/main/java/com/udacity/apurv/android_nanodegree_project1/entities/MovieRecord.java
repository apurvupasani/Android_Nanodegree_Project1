package com.udacity.apurv.android_nanodegree_project1.entities;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * POJO for MovieRecord. Implements serializable as it needs to be passed around via Intents.
 */
@Getter
@Setter
public class MovieRecord implements Serializable {
    private String originalTitle;
    private String movieImageThumbnailPath;
    private String overview;
    private Double userRating;
    private String releaseDate;

    @Override
    public String toString() {
        return "MovieRecord{" +
                "originalTitle='" + originalTitle + '\'' +
                ", movieImageThumbnailPath='" + movieImageThumbnailPath + '\'' +
                ", overview='" + overview + '\'' +
                ", userRating='" + userRating + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }
}
