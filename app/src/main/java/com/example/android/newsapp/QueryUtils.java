package com.example.android.newsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private static final String SAMPLE_URL="https://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2018-09-03&api-key=test&show-tags=contributor";

    private static final String SAMPLE_JSON_RESPONSE ="{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":9,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":1,\"orderBy\":\"relevance\",\"results\":[{\"id\":\"politics/2018/sep/03/crucial-week-for-labour-and-jeremy-corbyn-in-antisemitism-row\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2018-09-03T16:33:57Z\",\"webTitle\":\"Crucial week for Labour and Jeremy Corbyn in antisemitism row | Letters\",\"webUrl\":\"https://www.theguardian.com/politics/2018/sep/03/crucial-week-for-labour-and-jeremy-corbyn-in-antisemitism-row\",\"apiUrl\":\"https://content.guardianapis.com/politics/2018/sep/03/crucial-week-for-labour-and-jeremy-corbyn-in-antisemitism-row\",\"tags\":[],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"commentisfree/2018/sep/03/no-deal-brexit-wont-happen-uk-eu-negotiations\",\"type\":\"article\",\"sectionId\":\"commentisfree\",\"sectionName\":\"Opinion\",\"webPublicationDate\":\"2018-09-03T10:53:12Z\",\"webTitle\":\"Relax, everyone, a no-deal Brexit (probably) won’t happen | Anand Menon\",\"webUrl\":\"https://www.theguardian.com/commentisfree/2018/sep/03/no-deal-brexit-wont-happen-uk-eu-negotiations\",\"apiUrl\":\"https://content.guardianapis.com/commentisfree/2018/sep/03/no-deal-brexit-wont-happen-uk-eu-negotiations\",\"tags\":[{\"id\":\"profile/anand-menon\",\"type\":\"contributor\",\"webTitle\":\"Anand Menon\",\"webUrl\":\"https://www.theguardian.com/profile/anand-menon\",\"apiUrl\":\"https://content.guardianapis.com/profile/anand-menon\",\"references\":[],\"bio\":\"<p>Anand Menon is director of <a href=\\\"http://ukandeu.ac.uk/\\\">The UK in a Changing Europe</a> and professor of European politics and foreign affairs at King’s College London</p>\",\"firstName\":\"Anand\",\"lastName\":\"Menon\"}],\"isHosted\":false,\"pillarId\":\"pillar/opinion\",\"pillarName\":\"Opinion\"},{\"id\":\"politics/2018/sep/03/clean-your-fox-with-running-water-for-three-days-before-cooking\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2018-09-03T16:31:39Z\",\"webTitle\":\"Clean your fox with running water for three days before cooking | Brief letters\",\"webUrl\":\"https://www.theguardian.com/politics/2018/sep/03/clean-your-fox-with-running-water-for-three-days-before-cooking\",\"apiUrl\":\"https://content.guardianapis.com/politics/2018/sep/03/clean-your-fox-with-running-water-for-three-days-before-cooking\",\"tags\":[],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"politics/2018/sep/03/perspective-needed-on-labours-woes\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2018-09-03T16:33:46Z\",\"webTitle\":\"Perspective needed on Labour’s woes | Letters\",\"webUrl\":\"https://www.theguardian.com/politics/2018/sep/03/perspective-needed-on-labours-woes\",\"apiUrl\":\"https://content.guardianapis.com/politics/2018/sep/03/perspective-needed-on-labours-woes\",\"tags\":[],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"politics/2018/sep/03/labour-ruling-body-decide-antisemitism-definition\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2018-09-03T19:25:36Z\",\"webTitle\":\"Labour's ruling body to decide on new antisemitism definition\",\"webUrl\":\"https://www.theguardian.com/politics/2018/sep/03/labour-ruling-body-decide-antisemitism-definition\",\"apiUrl\":\"https://content.guardianapis.com/politics/2018/sep/03/labour-ruling-body-decide-antisemitism-definition\",\"tags\":[{\"id\":\"profile/dan-sabbagh\",\"type\":\"contributor\",\"webTitle\":\"Dan Sabbagh\",\"webUrl\":\"https://www.theguardian.com/profile/dan-sabbagh\",\"apiUrl\":\"https://content.guardianapis.com/profile/dan-sabbagh\",\"references\":[],\"bio\":\"<p>Dan Sabbagh is the Guardian's associate editor. He is a reporter based in Westminster with a roving brief across politics and power.</p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2014/4/17/1397749330599/DanSabbagh.jpg\",\"bylineLargeImageUrl\":\"https://uploads.guim.co.uk/2017/10/06/Dan-Sabbagh,-L.png\",\"firstName\":\"Dan\",\"lastName\":\"Sabbagh\"},{\"id\":\"profile/rajeev-syal\",\"type\":\"contributor\",\"webTitle\":\"Rajeev Syal\",\"webUrl\":\"https://www.theguardian.com/profile/rajeev-syal\",\"apiUrl\":\"https://content.guardianapis.com/profile/rajeev-syal\",\"references\":[],\"bio\":\"<p>Rajeev Syal covers Whitehall and works on off-diary stories from the lobby for the Guardian</p>\",\"bylineImageUrl\":\"https://uploads.guim.co.uk/2017/12/27/Rajeev-Syal.jpg\",\"bylineLargeImageUrl\":\"https://uploads.guim.co.uk/2017/12/27/Rajeev_Syal,_R.png\",\"firstName\":\"Rajeev\",\"lastName\":\"Syal\"}],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"education/2018/sep/04/life-lessons-by-melissa-benn-review\",\"type\":\"article\",\"sectionId\":\"education\",\"sectionName\":\"Education\",\"webPublicationDate\":\"2018-09-04T06:15:19Z\",\"webTitle\":\"Life Lessons by Melissa Benn book review – clarion call to hearten the weariest teacher | Peter Wilby\",\"webUrl\":\"https://www.theguardian.com/education/2018/sep/04/life-lessons-by-melissa-benn-review\",\"apiUrl\":\"https://content.guardianapis.com/education/2018/sep/04/life-lessons-by-melissa-benn-review\",\"tags\":[{\"id\":\"profile/peterwilby\",\"type\":\"contributor\",\"webTitle\":\"Peter Wilby\",\"webUrl\":\"https://www.theguardian.com/profile/peterwilby\",\"apiUrl\":\"https://content.guardianapis.com/profile/peterwilby\",\"references\":[],\"bio\":\"<p>Peter Wilby was for many years an education correspondent on various newspapers. He later became editor of the Independent on Sunday and, from 1998 to 2005, editor of the New Statesman</p>\",\"bylineImageUrl\":\"https://uploads.guim.co.uk/2018/01/11/Peter-Wilby.jpg\",\"bylineLargeImageUrl\":\"https://uploads.guim.co.uk/2018/01/11/Peter_Wilby,_L.png\",\"firstName\":\"Peter\",\"lastName\":\"Wilby\"}],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"commentisfree/2018/sep/03/ihra-antisemitism-labour-palestine\",\"type\":\"article\",\"sectionId\":\"commentisfree\",\"sectionName\":\"Opinion\",\"webPublicationDate\":\"2018-09-03T17:25:23Z\",\"webTitle\":\"The IHRA definition of antisemitism is a threat to free expression | Ash Sarkar\",\"webUrl\":\"https://www.theguardian.com/commentisfree/2018/sep/03/ihra-antisemitism-labour-palestine\",\"apiUrl\":\"https://content.guardianapis.com/commentisfree/2018/sep/03/ihra-antisemitism-labour-palestine\",\"tags\":[{\"id\":\"profile/ash-sarkar\",\"type\":\"contributor\",\"webTitle\":\"Ash Sarkar\",\"webUrl\":\"https://www.theguardian.com/profile/ash-sarkar\",\"apiUrl\":\"https://content.guardianapis.com/profile/ash-sarkar\",\"references\":[],\"bio\":\"<p>Ash Sarkar is a senior editor at Novara Media, and lectures in political theory at Anglia Ruskin and the Sandberg Instituut<br><br></p>\",\"bylineImageUrl\":\"https://uploads.guim.co.uk/2018/01/12/Ash-Sarkar.jpg\",\"firstName\":\"Ash\",\"lastName\":\"Sarkar\"}],\"isHosted\":false,\"pillarId\":\"pillar/opinion\",\"pillarName\":\"Opinion\"},{\"id\":\"politics/2018/sep/03/labour-must-reconnect-with-its-roots-to-heal-uks-rifts-says-stephen-kinnock\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2018-09-03T06:00:45Z\",\"webTitle\":\"Labour must reconnect with its roots to heal UK's rifts says Stephen Kinnock\",\"webUrl\":\"https://www.theguardian.com/politics/2018/sep/03/labour-must-reconnect-with-its-roots-to-heal-uks-rifts-says-stephen-kinnock\",\"apiUrl\":\"https://content.guardianapis.com/politics/2018/sep/03/labour-must-reconnect-with-its-roots-to-heal-uks-rifts-says-stephen-kinnock\",\"tags\":[{\"id\":\"profile/jessica-elgot\",\"type\":\"contributor\",\"webTitle\":\"Jessica Elgot\",\"webUrl\":\"https://www.theguardian.com/profile/jessica-elgot\",\"apiUrl\":\"https://content.guardianapis.com/profile/jessica-elgot\",\"references\":[],\"bio\":\"<p>Jessica Elgot is a Guardian political correspondent. Twitter:&nbsp;<a href=\\\"https://twitter.com/jessicaelgot\\\">@jessicaelgot</a></p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Guardian/Pix/contributor/2015/6/26/1435313697913/Jessica-Elgot.jpg\",\"bylineLargeImageUrl\":\"https://uploads.guim.co.uk/2017/10/06/Jessica-Elgot,-R.png\",\"firstName\":\"Jessica\",\"lastName\":\"Elgot\",\"twitterHandle\":\"jessicaelgot\"}],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"politics/blog/live/2018/sep/03/brexit-boris-johnson-branded-great-charlatan-by-senior-tory-after-he-ridicules-chequers-plan-politics-live\",\"type\":\"liveblog\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2018-09-03T16:44:47Z\",\"webTitle\":\"Rees-Mogg claims Barnier agrees with him about PM's Chequers plan being 'rubbish' - Politics live\",\"webUrl\":\"https://www.theguardian.com/politics/blog/live/2018/sep/03/brexit-boris-johnson-branded-great-charlatan-by-senior-tory-after-he-ridicules-chequers-plan-politics-live\",\"apiUrl\":\"https://content.guardianapis.com/politics/blog/live/2018/sep/03/brexit-boris-johnson-branded-great-charlatan-by-senior-tory-after-he-ridicules-chequers-plan-politics-live\",\"tags\":[{\"id\":\"profile/andrewsparrow\",\"type\":\"contributor\",\"webTitle\":\"Andrew Sparrow\",\"webUrl\":\"https://www.theguardian.com/profile/andrewsparrow\",\"apiUrl\":\"https://content.guardianapis.com/profile/andrewsparrow\",\"references\":[],\"bio\":\"<p>Andrew Sparrow is a political correspondent at the Guardian. He writes the Guardian's daily live blog, <a href=\\\"http://www.theguardian.com/politics/series/politics-live-with-andrew-sparrow\\\">Politics live with Andrew Sparrow</a>.</p>\",\"bylineImageUrl\":\"https://uploads.guim.co.uk/2017/10/06/Andrew-Sparrow.jpg\",\"bylineLargeImageUrl\":\"https://uploads.guim.co.uk/2017/10/06/Andrew_Sparrow,_R.png\",\"firstName\":\"sparrow\",\"lastName\":\"andrew\",\"twitterHandle\":\"AndrewSparrow\"},{\"id\":\"profile/severincarrell\",\"type\":\"contributor\",\"webTitle\":\"Severin Carrell\",\"webUrl\":\"https://www.theguardian.com/profile/severincarrell\",\"apiUrl\":\"https://content.guardianapis.com/profile/severincarrell\",\"references\":[],\"bio\":\"<p>Severin Carrell is <a href=\\\"http://www.guardian.co.uk/politics/scotland\\\">Scotland</a> editor for the <a href=\\\"http://www.guardian.co.uk/theguardian\\\">Guardian</a>. He was previously the Guardian's Scotland correspondent. He has worked as a home affairs, environment and politics correspondent for the Scotsman and Scotland on Sunday, and as a senior reporter with the Independent and Independent on Sunday</p><p>• <a href=\\\"http://static.guim.co.uk/ni/1418406968273/Severin-Carrell-PGP-key.txt\\\"> Severin Carrell's public key</a></p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Guardian/Pix/contributor/2007/09/28/severin_carrell_140x140.jpg\",\"firstName\":\"carrell\",\"lastName\":\"severin\"},{\"id\":\"profile/libbybrooks\",\"type\":\"contributor\",\"webTitle\":\"Libby Brooks\",\"webUrl\":\"https://www.theguardian.com/profile/libbybrooks\",\"apiUrl\":\"https://content.guardianapis.com/profile/libbybrooks\",\"references\":[],\"bio\":\"<p>Libby Brooks is the Guardian's Scotland correspondent, based in Glasgow. She previously worked as the Scotland reporter, and before that as editor and columnist on Comment. She joined the paper in 1998 as a feature writer and subsequently became women's editor. Her book <a href=\\\"http://www.bloomsbury.com/Books/Details.aspx?isbn=9780747583431\\\">The Story of Childhood: Growing Up in Modern Britain</a> is published by Bloomsbury</p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Guardian/Pix/contributor/2014/5/5/1399294269797/Libby-Brooks.jpg\",\"bylineLargeImageUrl\":\"https://uploads.guim.co.uk/2017/10/06/Libby-Brooks,-L.png\",\"firstName\":\"brooks\",\"lastName\":\"\"}],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"}]}}";

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
