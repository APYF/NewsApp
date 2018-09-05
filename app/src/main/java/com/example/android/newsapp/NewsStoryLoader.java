package com.example.android.newsapp;

import android.content.Context;
import android.content.AsyncTaskLoader;

import java.util.List;

public class NewsStoryLoader extends AsyncTaskLoader<List<NewsStory>> {
    /** Query URL */
    private String mUrl;

    public NewsStoryLoader(Context context, String url) {
        super(context);
        mUrl =  url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * Load list of NewsStory in the background
     * @return list of NewsSTory
     */
    @Override
    public List<NewsStory> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        List<NewsStory> newsStories = QueryUtils.fetchNewsStories(mUrl);
        return newsStories;
    }

}
