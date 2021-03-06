package com.example.rnztx.popularmovies.modules.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;

import com.example.rnztx.popularmovies.BuildConfig;
import com.example.rnztx.popularmovies.R;

import java.io.File;

/**
 * Created by rnztx on 14/4/16.
 */
public class Utilities {
    // Constants for URI builder
    final static String URL_SCHEME = "http";
    final static String BASE_URL_API = "api.themoviedb.org";
    final static String BASE_API_VERSION = "3";
    final static String BASE_PATH_MOVIE = "movie";
    final static String BASE_PATH_REVIEWS = "reviews";
    final static String BASE_PATH_VIDEOS = "videos";
    final static String KEY_API = "api_key";
    final static String VAL_API_KEY = BuildConfig.TMDB_API_KEY;


    public static void storeMovieSortChoice(FragmentActivity activity, String choice){
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
            String key = activity.getString(R.string.key_movie_sort_choice);
            editor.putString(key,choice);
        editor.apply();
    }

    public static String getMovieSortChoice(FragmentActivity activity){
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        String key = activity.getString(R.string.key_movie_sort_choice);
        String defaultValue = activity.getString(R.string.value_movie_sort_topRated);
        return sharedPreferences.getString(key,defaultValue);
    }

    public static String getYoutubeVideoThumbnailUrl(String videoKey){
        return String.format("http://img.youtube.com/vi/%s/mqdefault.jpg",videoKey);
    }
    public static String buildUrlForMovieVideos(String movie_id){

        Uri.Builder movieUriBuilder = new Uri.Builder();
        movieUriBuilder.scheme(URL_SCHEME)
                .authority(BASE_URL_API)
                .appendPath(BASE_API_VERSION)
                .appendPath(BASE_PATH_MOVIE)
                .appendPath(movie_id)
                .appendPath(BASE_PATH_VIDEOS)
                .appendQueryParameter(KEY_API,VAL_API_KEY);

        return movieUriBuilder.build().toString();
    }
    public static String buildUrlForMovieReviews(String movie_id){
        Uri.Builder movieUriBuilder = new Uri.Builder();
        movieUriBuilder.scheme(URL_SCHEME)
                .authority(BASE_URL_API)
                .appendPath(BASE_API_VERSION)
                .appendPath(BASE_PATH_MOVIE)
                .appendPath(movie_id)
                .appendPath(BASE_PATH_REVIEWS)
                .appendQueryParameter(KEY_API,VAL_API_KEY);

        return movieUriBuilder.build().toString();
    }

    // get Image Storage Path
    public static File getAbsoluteImageStoragePath(String imageName){
        File sdCardDir = Environment.getExternalStorageDirectory();
        return new File(sdCardDir+"/"+Constants.POPULAR_MOVIES_DIR,imageName);
    }
}
