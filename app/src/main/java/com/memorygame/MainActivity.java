package com.memorygame;

import android.content.Intent;
import android.os.Bundle;

import com.memorygame.board.BoardActivity;
import com.memorygame.high_scores.HighScoresActivity;
import com.memorygame.mvp.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sarah on 7/15/18.
 */

public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.new_game_btn)
    public void onNewGamePressed() {
        startActivity(new Intent(this, BoardActivity.class));
    }


    @OnClick(R.id.difficulty_btn)
    public void onDifficultyPressed() {
        startActivity(new Intent(this, DifficultyActivity.class));
    }


    @OnClick(R.id.high_scores_btn)
    public void onHighScoresPressed() {
        startActivity(new Intent(this, HighScoresActivity.class));
    }
}
