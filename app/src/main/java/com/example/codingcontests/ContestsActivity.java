package com.example.codingcontests;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ContestsActivity extends AppCompatActivity {

    private final String baseUrl = "https://kontests.net/api";

    private final String codechefUrl = "/v1/code_chef";
    private final String codeforcesUrl = "/v1/codeforces";
    private final String leetcodeUrl = "/v1/leet_code";
    private final String hackerrankUrl = "/v1/hacker_rank";
    private final String hackerearthUrl = "/v1/hacker_earth";
    private final String atcoderUrl = "/v1/at_coder";
    private final String topcoderUrl = "/v1/top_coder";
    private final String googlekickstartUrl = "/v1/kick_start";


    private RecyclerView contestsRecyclerView;
    private ArrayList<ContestsModel> contests;
    private ContestsAdapter contestsAdapter;
    private ProgressBar progressBar;

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contests);

        getSupportActionBar().setTitle(getIntent().getStringExtra("site") + " Contests");

        contestsRecyclerView = findViewById(R.id.contestsRecyclerView);
        progressBar = findViewById(R.id.progressBar);
        contests = new ArrayList<>();

        queue = Volley.newRequestQueue(this);

        getContests(getIntent().getStringExtra("site"));
    }

    private void getContests(String site) {
        String url = baseUrl;

        switch (site) {
            case "CodeChef": {
                url = url + codechefUrl;
                break;
            }
            case "Codeforces": {
                url = url + codeforcesUrl;
                break;
            }
            case "LeetCode": {
                url = url + leetcodeUrl;
                break;
            }
            case "HackerRank": {
                url = url + hackerrankUrl;
                break;
            }
            case "HackerEarth": {
                url = url + hackerearthUrl;
                break;
            }
            case "AtCoder": {
                url = url + atcoderUrl;
                break;
            }
            case "TopCoder": {
                url = url + topcoderUrl;
                break;
            }
            case "Google Kickstart": {
                url = url + googlekickstartUrl;
                break;
            }
        }

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null,  new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        progressBar.setVisibility(View.GONE);
                        Log.d("response", response.toString());
                        String name, url, start_time, end_time, duration, in_24_hours, status;
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                name = response.getJSONObject(i).getString("name");
                                url = response.getJSONObject(i).getString("url");
                                start_time = formatDate(response.getJSONObject(i).getString("start_time"));
                                end_time = formatDate(response.getJSONObject(i).getString("end_time"));
                                duration = formatDuration(response.getJSONObject(i).getString("duration"));
                                in_24_hours = response.getJSONObject(i).getString("in_24_hours");
                                status = response.getJSONObject(i).getString("status");

                                contests.add(new ContestsModel(name, url, start_time, end_time, duration, in_24_hours, status));
                            } catch (JSONException e) {
                                Toast.makeText(ContestsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        contestsAdapter = new ContestsAdapter(contests, ContestsActivity.this);

                        contestsRecyclerView.setLayoutManager(new LinearLayoutManager(ContestsActivity.this));
                        contestsRecyclerView.setAdapter(contestsAdapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError e) {
                        Toast.makeText(ContestsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonArrayRequest);
    }

    public String formatDate(String dateString) {
        if (dateString.length() == 24) {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = null;
            try {
                date = inputFormat.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String formattedDate = outputFormat.format(date) + " UTC";

            return formattedDate;
        }
        return dateString;
    }

    public String formatDuration(String duration) {
        String res = "";

        long n = (long) Double.parseDouble(duration);
        int days = (int) (n / (24 * 3600));

        n = n % (24 * 3600);
        int hour = (int) (n / 3600);

        n %= 3600;
        int minutes = (int) (n / 60);

        n %= 60;
        int seconds = (int) n;

        if (days != 0) {
            res = res + days + " Days ";
        }
        if (hour != 0) {
            res = res + hour + " Hr ";
        }
        if (minutes != 0) {
            res = res + minutes + " Min ";
        }
        if (seconds != 0) {
            res = res + seconds + " Sec ";
        }

        return res;
    }
}