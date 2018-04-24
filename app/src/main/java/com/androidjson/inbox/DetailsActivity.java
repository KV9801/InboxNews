package com.androidjson.inbox;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DetailsActivity extends BaseActivity {
    WebView webView;
    ProgressBar loader;
    String url = "";
    boolean bookmarked;
    MenuItem bmitem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        loader = (ProgressBar) findViewById(R.id.loader);
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.loadUrl(url);


        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    loader.setVisibility(View.GONE);
                } else {
                    loader.setVisibility(View.VISIBLE);
                }
            }
        });

        SharedPreferences mSharedPreference = getSharedPreferences("bookmarks", MODE_PRIVATE);
        //Get current string
        String myUrls = mSharedPreference.getString("myurls","");
        //Check if url already present
        String[] itemsWords = myUrls.split(",", -1);
        bookmarked = Arrays.asList(itemsWords).contains(url);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.share:
                //Get sharing intent
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "InboxNews : Your no. 1 source of info");
                intent.putExtra(Intent.EXTRA_TEXT, url);
                startActivity(Intent.createChooser(intent, "Share using"));
                return true;
            case R.id.bookmark:
                SharedPreferences mSharedPreference = getSharedPreferences("bookmarks", MODE_PRIVATE);
                //Get current string
                String myUrls = mSharedPreference.getString("myurls","");
                //Check if url already present
                String[] itemsWords = myUrls.split(",", -1);
                if(!Arrays.asList(itemsWords).contains(url) && url!="null") {    //Add url
                    myUrls += url;
                    myUrls += ",";
                    Toast.makeText(getApplicationContext(), "Bookmarked!", Toast.LENGTH_SHORT).show();
                } else {                // Remove url
                    Toast.makeText(getApplicationContext(), "Un-Bookmarked", Toast.LENGTH_SHORT).show();
                    //Remove from prefs
                    String[] result = removeFromArray(itemsWords, url);
                    myUrls = TextUtils.join(",",result);
                }

                //Update and put string
                SharedPreferences.Editor mEditor = mSharedPreference.edit();
                mEditor.putString("myurls", myUrls);
                mEditor.apply();

                //Get current string
                myUrls = mSharedPreference.getString("myurls","");
                //Check if url already present
                itemsWords = myUrls.split(",", -1);
                bookmarked = Arrays.asList(itemsWords).contains(url);
                if(bmitem!=null && bookmarked)
                    bmitem.setTitle("Un-Bookmark");
                else if(bmitem!=null && !bookmarked)
                    bmitem.setTitle("Bookmark");
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_menu, menu);
        bmitem = menu.findItem(R.id.bookmark);
        if(bmitem!=null && bookmarked)
            bmitem.setTitle("Un-Bookmark");
        else if(bmitem!=null && !bookmarked)
            bmitem.setTitle("Bookmark");
        return super.onCreateOptionsMenu(menu);
    }

    public static String[] removeFromArray(String[] arr, String toRemove)
    {
        int newLength = 0;
        int newInd = 0;
        for(int i = 0; i < arr.length; i++)
        {
            if(arr[i].contains(toRemove))
            {
                newLength++;
            }
        }
        String[] result = new String[arr.length-newLength];
        for(int i = 0; i < (arr.length); i++)
        {
            if(arr[i].contains(toRemove))
            {

            }
            else
            {
                result[newInd++] = arr[i];
            }
        }
        return result;
    }
}

