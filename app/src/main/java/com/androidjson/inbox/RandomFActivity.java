package com.androidjson.inbox;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidjson.inbox.data.FactResponse;
import com.androidjson.inbox.database.DbHelper;
import com.androidjson.inbox.databinding.ActivityRandomFBinding;
import com.androidjson.inbox.retrofit.ApiClient;
import com.androidjson.inbox.retrofit.ApiInterface;
import com.androidjson.inbox.util.Utils;
import com.robinhood.ticker.TickerUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RandomFActivity extends BaseActivity
{
    //Toolbar toolbar;
    ApiInterface apiService;
    Call<FactResponse> apiCall;
    ActivityRandomFBinding binding;
    boolean firstTimeCall = true;
    SQLiteDatabase db;
    ContentValues values;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_random_f);
        binding.fabShare.hide();
        assignIds();
        values = new ContentValues();
        db = new DbHelper(this).getWritableDatabase();
        //setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.random_facts);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        apiService = ApiClient.getClient().create(ApiInterface.class);


        binding.btnTrivia.btnHolder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (Utils.isNetworkAvailable(RandomFActivity.this))
                {
                    if (firstTimeCall)
                    {
                        binding.resultNumber.setText("Loading...");
                        firstTimeCall = false;
                    }
                    callApi("trivia");
                }
                else
                {

                    Toast.makeText(RandomFActivity.this, R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.btnMath.btnHolder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (Utils.isNetworkAvailable(RandomFActivity.this))
                {
                    if (firstTimeCall)
                    {

                        binding.resultNumber.setText("Loading...");
                        firstTimeCall = false;
                    }
                    callApi("math");
                }
                else
                {

                    Toast.makeText(RandomFActivity.this, R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.btnDate.btnHolder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (Utils.isNetworkAvailable(RandomFActivity.this))
                {
                    if (firstTimeCall)
                    {

                        binding.resultNumber.setText("Loading...");
                        firstTimeCall = false;
                    }
                    callApi("date");
                }
                else
                {
                    ;
                    Toast.makeText(RandomFActivity.this, R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.btnYear.btnHolder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (Utils.isNetworkAvailable(RandomFActivity.this))
                {
                    if (firstTimeCall)
                    {

                        binding.resultNumber.setText("Loading...");
                        firstTimeCall = false;
                    }
                    callApi("year");
                }
                else
                {

                    Toast.makeText(RandomFActivity.this, R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
                }
            }

        });


        binding.fabShare.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String shareFact = "Do you know ?\n\n" + binding.resultText.getText().toString();
                Intent shareIntent = new Intent(Intent.ACTION_SEND)
                        .setType("text/plain")
                        .putExtra(Intent.EXTRA_TEXT, shareFact);
                startActivity(Intent.createChooser(shareIntent, "Share with"));
            }
        });

    }

    private void hideFab()
    {
        if (binding.fabShare.isShown())
        {
            binding.fabShare.hide();
        }
    }

    private void callApi(final String type)
    {
        hideHintImageView();
        apiCall = apiService.getRandomFact(type);
        apiCall.enqueue(new Callback<FactResponse>()
        {
            @Override
            public void onResponse(Call<FactResponse> call, Response<FactResponse> response)
            {
                Log.d("response", response.isSuccessful() + "\t\t" + response.raw());
                if (response != null && response.isSuccessful())
                {
                    Log.d("response", response.isSuccessful() + "");
                    if (type.equals("year"))
                    {
                        String yearStr;
                        int year = (int) response.body().getNumber();
                        if (year < 0)
                        {
                            yearStr = Math.abs(year) + " BC";
                        }
                        else
                        {
                            yearStr = year + " BC";
                        }
                        binding.resultNumber.setText(yearStr);
                        binding.fabShare.show();
                        values.put(DbHelper.COLUMN_NAME_NUMBER, yearStr);

                    }
                    else if (type.equals("date"))
                    {

                        String date = response.body().getText().split(" ")[0] + " " + response.body().getText().split(" ")[1];
                        binding.fabShare.show();
                        values.put(DbHelper.COLUMN_NAME_NUMBER, date);
                        binding.resultNumber.setText(date);
                    }
                    else
                    {

                        if (response.body().getNumber() == Math.round(response.body().getNumber()))
                        {
                            binding.resultNumber.setText((long) response.body().getNumber() + "");
                            values.put(DbHelper.COLUMN_NAME_NUMBER, (long) response.body().getNumber() + "");
                        }
                        else
                        {
                            binding.resultNumber.setText(response.body().getNumber() + "");
                            values.put(DbHelper.COLUMN_NAME_NUMBER, response.body().getNumber() + "");
                        }
                        binding.fabShare.show();

                    }
                    binding.resultText.setText(response.body().getText());

                    values.put(DbHelper.COLUMN_NAME_FACT, response.body().getText());
                    values.put(DbHelper.COLUMN_NAME_TYPE, type);
                    db.insert(DbHelper.TABLE_NAME_RANDOM, null, values);
                }
            }

            @Override
            public void onFailure(Call<FactResponse> call, Throwable t)
            {
                Toast.makeText(RandomFActivity.this, "" + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void assignIds()
    {

        binding.resultNumber.setCharacterList(TickerUtils.getDefaultListForUSCurrency());
        binding.resultNumber.setAnimationDuration(550);
        binding.resultNumber.setGravity(Gravity.CENTER);

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


    private void hideHintImageView()
    {
        binding.resultText.setVisibility(View.VISIBLE);
        binding.clickHint.setVisibility(View.GONE);
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
