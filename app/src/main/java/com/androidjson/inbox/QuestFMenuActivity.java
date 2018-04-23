package com.androidjson.inbox;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.androidjson.inbox.databinding.ActivityQuestFMenuBinding;

public class QuestFMenuActivity extends BaseActivity
{
    ActivityQuestFMenuBinding binding;
    CardView btnTriviaCV, btnMathCV, btnDateCV, btnYearCV;
    TextView btnTriviaTV, btnMathTV, btnDateTV, btnYearTV;
    Toolbar toolbar;
    String toolbar_title;
    ActivityOptionsCompat options;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_quest_f_menu);
        assignIds();
        //setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.quest_facts);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            getSupportActionBar().setElevation(4);
        } else
        {
            binding.toolbarShadow.setVisibility(View.VISIBLE);
        }
        binding.btnTrivia.btnHolder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                toolbar_title = getString(R.string.trivia);
                startNextActivity();
            }
        });
        binding.btnMath.btnHolder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                toolbar_title = getString(R.string.math);
                startNextActivity();
            }
        });
        binding.btnDate.btnHolder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                toolbar_title = getString(R.string.date);
                startNextActivity();
            }
        });
        binding.btnYear.btnHolder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                toolbar_title = getString(R.string.year);
                startNextActivity();
            }
        });
    }

    private void startNextActivity()
    {
        startActivity(new Intent(this, QuestFActivity.class).putExtra("toolbar_title", toolbar_title));
    }

    private void assignIds()
    {


        CardView btnTriviaCV = binding.btnTrivia.btnHolder;
        CardView btnMathCV = binding.btnMath.btnHolder;
        CardView btnDateCV = binding.btnDate.btnHolder;
        CardView btnYearCV = binding.btnYear.btnHolder;


        TextView btnTriviaTV = binding.btnTrivia.btnLabel;
        TextView btnMathTV = binding.btnMath.btnLabel;
        TextView btnDateTV = binding.btnDate.btnLabel;
        TextView btnYearTV = binding.btnYear.btnLabel;


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            btnTriviaCV.setElevation(4);
            btnMathCV.setElevation(4);
            btnDateCV.setElevation(4);
            btnYearCV.setElevation(4);
        }


        btnTriviaTV.setText(R.string.trivia);
        btnMathTV.setText(R.string.math);
        btnDateTV.setText(R.string.date);
        btnYearTV.setText(R.string.year);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

}
