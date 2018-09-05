package com.example.android.newsapp;

import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.app.LoaderManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsStory>> {

    public static final String LOG_TAG = MainActivity.class.getName();

    private static final String GUARDIAN_URL = "https://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2018-09-03&api-key=test&show-tags=contributor";

    private TextView mEmptyStateTextView;
    private ProgressBar mProgressBar;

    private static final int NEWS_LOADER_ID = 1;

    private NewsStoryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView newsStoryListView = (ListView) findViewById(R.id.list);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        newsStoryListView.setEmptyView(mEmptyStateTextView);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAdapter = new NewsStoryAdapter(this, new ArrayList<NewsStory>());

        newsStoryListView.setAdapter(mAdapter);

        newsStoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                NewsStory currentNewsStory = mAdapter.getItem(position);

                Uri newsStoryuri = Uri.parse(currentNewsStory.getStoryUrl());

                // create new intent to view the news story URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsStoryuri);

                startActivity(websiteIntent);
            }
        });

        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (isConnected) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {
            mProgressBar.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet);
        }
    }

    @Override
    public Loader<List<NewsStory>> onCreateLoader(int i, Bundle args) {
        // Create a new loader for the given URL
        Log.i(LOG_TAG, "In onCreateLoader");

        return new NewsStoryLoader(this, GUARDIAN_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<NewsStory>> loader, List<NewsStory> newsStories) {
        // Clear the adapter of previous earthquake data
        mAdapter.clear();
        // Set empty state text to display "No News story found."
        mEmptyStateTextView.setText(R.string.no_newstory);


        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (newsStories != null && !newsStories.isEmpty()) {
            Log.i(LOG_TAG, "In onLoadFinished adding earthquakes");

            mAdapter.addAll(newsStories);
        }
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(Loader<List<NewsStory>> loader) {
        Log.i(LOG_TAG, "In onLoaderReset clearing adapter");

        mAdapter.clear();
    }
}
