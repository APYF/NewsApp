package com.example.android.newsapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsStoryAdapter extends ArrayAdapter<NewsStory> {

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

        TextView storyPublishDateTextView = (TextView) listItemView.findViewById(R.id.story_publish_data);
        storyPublishDateTextView.setText(currentNewsStory.getPublishedDate());

        return listItemView;
    }
}
