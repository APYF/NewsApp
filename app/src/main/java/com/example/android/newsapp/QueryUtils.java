package com.example.android.newsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     *
     * @param newsStoriesJSON
     * @return a list of {@link NewsStory} objects that has been built up from parsing
     * the given JSON response.
     */

    public static List<NewsStory> extractNewStoriesFromJson(String newsStoriesJSON) {
        // Create an empty ArrayList that we can sart adding newStory to
        ArrayList<NewsStory> newsStories = new ArrayList<>();

        try {
            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(newsStoriesJSON);
            // Extract the JSONArray associated with the key called "results",
            // which represents a list of news stories
            JSONArray newStoriesArray = baseJsonResponse.getJSONArray("results");

            // for each newStory in the newStoriesArray, create an {@link NewsStory} object
            for (int i = 0; i < newStoriesArray.length(); i++) {
                // Get a single news story at position i within the list of stories
                JSONObject currentNewsStory = newStoriesArray.getJSONObject(i);
                // Extract the story title
                String sectionName = currentNewsStory.getString("sectionName");
                String title = currentNewsStory.getString("webTitle");

                // contributors to articles are stored in tags
                // there can be multiple contributors so extra them and put them into author
                JSONArray storyTagsArray = currentNewsStory.getJSONArray("tags");
                String author = null;
                for (int j = 0; j < storyTagsArray.length(); j++) {
                    if (j > 0) {
                        author.concat(", ");
                    }
                    JSONObject currentAuthor = storyTagsArray.getJSONObject(j);
                    author.concat(currentAuthor.getString("webTitle"));
                }

                String publishDate = currentNewsStory.getString("webPublicationDate");
                String url = currentNewsStory.getString("webUrl");

                // Create a new {@Link NewsStory} object
                NewsStory newsStory = new NewsStory(sectionName, title, author, publishDate, url);
                newsStories.add(newsStory);
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the news JSON results.", e);
        }

        return newsStories;
    }


}
