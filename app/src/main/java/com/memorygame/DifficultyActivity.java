package com.memorygame;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.memorygame.mvp.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sarah on 7/15/18.
 */

public class DifficultyActivity extends BaseActivity {

    public static final String TIMER_KEY = "timer";

    private SharedPreferences prefs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);
        ButterKnife.bind(this);
        prefs = getSharedPreferences("pref", Context.MODE_PRIVATE);
    }

    @OnClick(R.id.easy_btn)
    public void onEasyPressed() {
        prefs.edit().putInt(TIMER_KEY, 3).commit();
        finish();
    }


    @OnClick(R.id.medium_btn)
    public void onMediumPressed() {
        prefs.edit().putInt(TIMER_KEY, 2).commit();
        finish();
    }


    @OnClick(R.id.hard_btn)
    public void onHardPressed() {
        prefs.edit().putInt(TIMER_KEY, 1).commit();
        finish();
    }
}
