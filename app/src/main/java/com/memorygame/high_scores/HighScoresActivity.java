package com.memorygame.high_scores;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.memorygame.R;
import com.memorygame.high_scores.adapter.HighScoreRecyclerViewAdapter;
import com.memorygame.mvp.BaseActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sarah on 7/15/18.
 */

public class HighScoresActivity extends BaseActivity {

    @BindView(R.id.high_scores_recycler_view)
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        ButterKnife.bind(this);

        Gson gson = new Gson();
        SharedPreferences prefs = getSharedPreferences("pref", Context.MODE_PRIVATE);
        String jsonText = prefs.getString("scores", null);
        List<Double> scores = gson.fromJson(jsonText, List.class);
        if (scores == null)
            scores = new ArrayList<>();
        Collections.sort(scores,Collections.reverseOrder());
        HighScoreRecyclerViewAdapter adapter = new HighScoreRecyclerViewAdapter(this, scores);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

}
