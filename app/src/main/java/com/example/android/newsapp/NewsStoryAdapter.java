package com.example.android.newsapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class NewsStoryAdapter extends ArrayAdapter<NewsStory> {
    public static final String LOG_TAG = NewsStoryAdapter.class.getName();


    public NewsStoryAdapter(Activity context, ArrayList<NewsStory> newsStories) {
        super(context, 0, newsStories);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        final NewsStory currentNewsStory = getItem(position);

        TextView sectionTextView = (TextView) listItemView.findViewById(R.id.story_section);
        sectionTextView.setText(currentNewsStory.getSectionName());

        TextView storyTitleTextView = (TextView) listItemView.findViewById(R.id.story_title);
        storyTitleTextView.setText(currentNewsStory.getStoryTitle());

        TextView storyContributorsTextView = (TextView) listItemView.findViewById(R.id.story_contributors);

        // if the author name is "" then hide the storyContributorTextView
        if (currentNewsStory.getAuthor().equals("")) {
            storyContributorsTextView.setVisibility(View.GONE);

        } else {
            storyContributorsTextView.setText(currentNewsStory.getAuthor());
        }

        // format the date time into something nicer
        TextView storyPublishDateTextView = (TextView) listItemView.findViewById(R.id.story_publish_date);
        TextView storyPublishTimeTextView = (TextView) listItemView.findViewById(R.id.story_publish_time);
        try {
            storyPublishDateTextView.setText(formatDate(currentNewsStory.getPublishedDate()));
            storyPublishTimeTextView.setText(formatTime(currentNewsStory.getPublishedDate()));
        } catch (ParseException e) {
            Log.e(LOG_TAG, "Error parsing the date or time");
        }

        return listItemView;
    }

    private String formatDate(String date) throws ParseException {
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
        originalFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat newFormat = new SimpleDateFormat("MMM dd yyyy");

        String reformattedString = newFormat.format(originalFormat.parse(date));
        return reformattedString;
    }

    private String formatTime(String date) throws ParseException {
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
        originalFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat newFormat = new SimpleDateFormat("hh:mm z");

        String reformattedString = newFormat.format(originalFormat.parse(date));
        return reformattedString;
    }

}
