package com.memorygame.board;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.Toast;

import com.google.gson.Gson;
import com.memorygame.DifficultyActivity;
import com.memorygame.R;
import com.memorygame.model.Card;
import com.memorygame.mvp.BaseActivityPresenter;
import com.memorygame.util.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by Sarah on 7/15/18.
 */

public class BoardActivityPresenter extends BaseActivityPresenter<BoardActivity> {


    private static final int BOARD_SIZE = 16;
    private static final int LOCAL_IMAGES_NUMBER = 27;

    private static final int SCORE_MOVE_SUCCESS_VALUE = 10;
    List<Card> cardList;
    private int flippedCardId = -1;
    private int flippedCardPosition = -1;
    private int doneCardsCount = 0;
    private CountDownTimer timer;
    private double points = 0;
    protected double highScore;
    private SharedPreferences prefs;
    private int timerValue = 120 * 1000;


    public BoardActivityPresenter(BoardActivity view) {
        prefs = view.getSharedPreferences("pref", Context.MODE_PRIVATE);
        this.view = view;
        selectRandomImages();
        loadTimer();
        startTimer();
        getHighScore();
    }

    private void loadTimer() {
        if (prefs.contains(DifficultyActivity.TIMER_KEY)) {
            int timeInMinutes = prefs.getInt(DifficultyActivity.TIMER_KEY, 0);
            timerValue = timeInMinutes * 60 * 1000;
        }
    }

    private void getHighScore() {
        Gson gson = new Gson();
        String jsonText = prefs.getString("scores", null);
        List<Double> scores = gson.fromJson(jsonText, List.class);
        if (scores != null) {
            highScore = Collections.max(scores);
        } else
            highScore = 0;
    }

    public void selectRandomImages() {

        cardList = new ArrayList<>();
        Set<Integer> imagesNumbers = Utils.generateRandomNumbers(BOARD_SIZE / 2, LOCAL_IMAGES_NUMBER);

        imagesNumbers.forEach(i -> {
            int resourceId = this.view.getResources().getIdentifier("img_" + i, "drawable", this.view.getPackageName());

            Card card = new Card();
            card.setImageResourceId(resourceId);
            card.setId(i);
            cardList.add(card);
            card = new Card();
            card.setImageResourceId(resourceId);
            card.setId(i);
            cardList.add(card);
        });

        Collections.shuffle(cardList);
    }

    private void startTimer() {
        timer = new CountDownTimer(timerValue, 1000) {

            public void onTick(long millisUntilFinished) {
                view.updateTimer(millisUntilFinished);
            }

            public void onFinish() {
                Toast.makeText(view, view.getString(R.string.time_up), Toast.LENGTH_SHORT).show();
                view.endGame();
            }

        }.start();
    }

    public void endGame() {
        stopTimer();
        saveScore();
        view.endGame();
    }

    private void stopTimer() {
        timer.cancel();
    }

    private void saveScore() {
        double score = points;
        if (score == 0)
            return;
        Gson gson = new Gson();
        String jsonText = prefs.getString("scores", null);
        List<Double> scores = gson.fromJson(jsonText, List.class);
        if (scores == null)
            scores = new ArrayList<>();
        scores.add(score);
        if (scores.size() > 10) {
            Collections.sort(scores, Collections.reverseOrder());
            scores.remove(10);
        }
        jsonText = gson.toJson(scores);
        prefs.edit().putString("scores", jsonText).commit();
    }

    public void onCardClick(Card card, int position) {
        int currentClickCardId = card.getId();


        if (flippedCardId != -1) {
            if (flippedCardId == currentClickCardId && position != flippedCardPosition) {
                Handler mHandler = new Handler();
                int position1 = flippedCardPosition;
                int position2 = position;
                mHandler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        view.markPairCardsDone(position1, position2);
                    }
                }, 500);
                updatePoints();

                doneCardsCount++;
                if (doneCardsCount == BOARD_SIZE / 2)
                    endGame();

            } else if (position != flippedCardPosition) {

                Handler mHandler = new Handler();
                int position1 = flippedCardPosition;
                int position2 = position;
                mHandler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        view.flipPairCards(position1, position2);
                    }
                }, 1000);

            }
            view.updatePoints(points);

        }

        flippedCardPosition = position;
        flippedCardId = currentClickCardId;
    }

    private void updatePoints() {
        points = points + SCORE_MOVE_SUCCESS_VALUE;

    }
}
