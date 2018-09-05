package com.example.android.newsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
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
     * @param newsStoriesJSON
     * @return a list of {@link NewsStory} objects that has been built up from parsing
     * the given JSON response.
     */

    public static List<NewsStory> extractNewStoriesFromJson(String newsStoriesJSON) {
        // Create an empty ArrayList that we can start adding newStory to
        ArrayList<NewsStory> newsStories = new ArrayList<>();

        try {
            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(newsStoriesJSON);
            // Extract the JSONArray associated with the key called "results",
            // which represents a list of news stories
            JSONObject jsonResponse = baseJsonResponse.getJSONObject("response");
            JSONArray newStoriesArray = jsonResponse.getJSONArray("results");

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
                String author = "";
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

    /**
     * Query the Guardian API and return a list of {@link NewsStory} objects.
     */
    public static List<NewsStory> fetchNewsStories(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;

        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        List<NewsStory> newsStories = extractNewStoriesFromJson(jsonResponse);

        // Return the list of {@link Earthquake}s
        return newsStories;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }


}
