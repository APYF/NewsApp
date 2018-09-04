package com.example.android.newsapp;

public class NewsStory {
    private String mSectionName;
    private String mTitle;
    private String mAuthor;
    private String mDate;
    private String mURL;

    /**
     *  Create a News story object
     *
     * @param SectionName
     * @param Title
     * @param Author
     * @param Date
     * @param Url
     */

    public NewsStory(String SectionName, String Title, String Author, String Date, String Url) {
        mSectionName = SectionName;
        mTitle = Title;
        mAuthor = Author;
        mDate = Date;
        mURL = Url;
    }

    public String getSectionName() { return mSectionName; }

    public String getStoryTitle() { return mTitle; }

    public String getAuthor() { return mAuthor; }

    public String getPublishedDate() { return mDate; }

    public String getStoryUrl() { return mURL; }
}
