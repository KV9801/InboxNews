package com.androidjson.inbox;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class Bookmark extends Fragment {
    RecyclerView recyclerView;
    ListView listNews;
    ProgressBar loader;
    String myUrls;
    ListNewsAdapter adapter;
    static final String KEY_AUTHOR = "author";
    static final String KEY_TITLE = "title";
    static final String KEY_DESCRIPTION = "description";
    static final String KEY_URL = "url";
    static final String KEY_URLTOIMAGE = "urlToImage";
    static final String KEY_PUBLISHEDAT = "publishedAt";
    ArrayList<HashMap<String, String>> dataList;

    public Bookmark() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("On Start called", "Start");

        dataList = new ArrayList<HashMap<String, String>>();
        SharedPreferences mSharedPreference = this.getActivity().getSharedPreferences("bookmarks", MODE_PRIVATE);
        myUrls = mSharedPreference.getString("myurls","");
        if (myUrls != null && myUrls.length() > 0 && myUrls.charAt(myUrls.length() - 1) == ',') {
            myUrls = myUrls.substring(0, myUrls.length() - 1);
        }
        String[] itemsWords = myUrls.split(",");

        for(int i = 0; i< itemsWords.length; i++){
            //Dataset builder!
            Log.i("Value of element "+i,itemsWords[i]);
            HashMap<String, String> map = new HashMap<>();
            map.put(KEY_AUTHOR, itemsWords[i]);
            map.put(KEY_TITLE, "Bookmark "+(i+1));
            map.put(KEY_DESCRIPTION, "");
            map.put(KEY_URL, "");
            map.put(KEY_URLTOIMAGE, "");
            map.put(KEY_PUBLISHEDAT, "");
            if(itemsWords[i]!=null && itemsWords[i]!="" && itemsWords[i]!="null")
                dataList.add(map);
        }

        listNews.setAdapter(null);
        adapter = new ListNewsAdapter(this.getActivity(), dataList);
        adapter.notifyDataSetChanged();
        listNews.setAdapter(adapter);

        listNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Fetch ChromeView or whatever
                Intent i = new Intent(getActivity(), DetailsActivity.class);
                i.putExtra("url", dataList.get(+position).get(KEY_AUTHOR));
                startActivity(i);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("On Resume called", "Resume");

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            Log.d("MyFragment", "Fragment is visible.");
            this.onStart();
        }
        else {
            Log.d("MyFragment", "Fragment is not visible.");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listNews = (ListView) getView().findViewById(R.id.listNews);
        loader = (ProgressBar) getView().findViewById(R.id.loader);
        loader.setVisibility(View.GONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_api, container, false);
    }
}
