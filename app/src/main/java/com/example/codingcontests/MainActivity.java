package com.example.codingcontests;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView sitesRecyclerView;
    private ArrayList<SitesModel> sites;
    private SitesAdapter sitesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sitesRecyclerView = findViewById(R.id.sitesRecyclerView);
        sites = new ArrayList<>();

        sites.add(new SitesModel(R.drawable.ic_codechef, "CodeChef"));
        sites.add(new SitesModel(R.drawable.ic_codeforces, "Codeforces"));
        sites.add(new SitesModel(R.drawable.ic_leetcode, "LeetCode"));
        sites.add(new SitesModel(R.drawable.ic_hackerrank, "HackerRank"));
        sites.add(new SitesModel(R.drawable.ic_hackerearth, "HackerEarth"));
        sites.add(new SitesModel(R.drawable.ic_atcoder, "AtCoder"));
        sites.add(new SitesModel(R.drawable.ic_topcoder, "TopCoder"));
        sites.add(new SitesModel(R.drawable.ic_googlekickstart, "Google Kickstart"));

        sitesAdapter = new SitesAdapter(sites, MainActivity.this);

        sitesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        sitesRecyclerView.setAdapter(sitesAdapter);
    }
}