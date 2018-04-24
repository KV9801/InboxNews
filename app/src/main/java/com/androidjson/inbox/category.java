package com.androidjson.inbox;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidjson.inbox.util.Utils;

public class category extends BaseActivity implements OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    TextView NameView, EmailView;
    String Name, Email;

    TabLayout tablayout;
    ViewPager viewpager;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Intent intent = getIntent();
        Email = intent.getStringExtra("email");
        Name = intent.getStringExtra("name");

        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewpager = (ViewPager) findViewById(R.id.viewpager);

        //Adding fragments
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Mainfrag(), "Categories");
        adapter.addFragment(new Bookmark(), "Bookmarks");

        viewpager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewpager);

        tablayout.getTabAt(0).setIcon(R.drawable.showmastertab4categories);
        tablayout.getTabAt(1).setIcon(R.drawable.ribbon2512);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        NameView = (TextView) findViewById(R.id.menu_name);
        EmailView = (TextView) findViewById(R.id.menu_email);

        NameView.setText(Name);
        EmailView.setText(Email);

        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void recreateActivity()
    {
        Intent intent = getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.aboutus :
                Intent i9 = new Intent(category.this, AboutActivity.class);
                startActivity(i9);
                break;
            case R.id.logout :
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(category.this);
                mBuilder.setTitle("Sign out");
                mBuilder.setMessage(R.string.confirm);
                mBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //End Activity
                                Intent main = new Intent(category.this, MainActivity.class);
                                startActivity(main);
                            }
                        });
                mBuilder.setNegativeButton("No", null);
                AlertDialog ad = mBuilder.create();
                ad.setIcon(R.drawable.ic_sentiment_dissatisfied_black_24dp);
                ad.show();
                break;
            case R.id.facts :
                Intent i8 = new Intent(category.this, FactsActivity.class);
                startActivity(i8);
                break;
            case R.id.contactus :

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:diyap@outlook.com"));
                try {
                    startActivity(emailIntent);
                } catch (ActivityNotFoundException e) {
                    //TODO: Handle case where no email app is available
                }

                break;
            case R.id.change_theme:
                int currentTheme = Utils.getTheme(category.this);
                if (currentTheme <= 1)
                {
                    Utils.setTheme(category.this, 2);
                }
                else if (currentTheme == 2)
                {
                    Utils.setTheme(category.this, 1);
                }

                recreateActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}



