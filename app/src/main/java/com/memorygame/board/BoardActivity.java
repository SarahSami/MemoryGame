package com.memorygame.board;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.widget.TextView;

import com.memorygame.R;
import com.memorygame.board.adapter.BoardRecyclerViewAdapter;
import com.memorygame.model.Card;
import com.memorygame.mvp.BaseActivity;
import com.memorygame.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sarah on 7/15/18.
 */

public class BoardActivity extends BaseActivity<BoardActivityPresenter> implements BoardRecyclerViewAdapter.CardClickListener {

    private static final int NUMBER_COLUMNS = 4;
    @BindView(R.id.board)
    RecyclerView boardRecyclerView;
    @BindView(R.id.timer_tv)
    TextView timerTv;
    @BindView(R.id.score_tv)
    TextView scoreTv;
    @BindView(R.id.high_score_tv)
    TextView highScoreTv;
    BoardRecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_board);
        ButterKnife.bind(this);

        presenter = new BoardActivityPresenter(this);
        adapter = new BoardRecyclerViewAdapter(this, presenter.cardList, this);


        boardRecyclerView.setLayoutManager(new GridLayoutManager(this, NUMBER_COLUMNS));
        boardRecyclerView.setAdapter(adapter);
        updatePoints(0);
        updateHighScore(presenter.highScore);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        presenter.endGame();
    }

    @Override
    public void onCardClick(Card card, int position) {
        presenter.onCardClick(card, position);
    }

    public void updateTimer(long millis) {
        timerTv.setText(Utils.timerFormat(millis));
    }

    public void endGame() {
        finish();
    }

    public void updatePoints(double points) {
        scoreTv.setText(getString(R.string.points) + " " + points);
    }

    public void updateHighScore(double score) {
        highScoreTv.setText(getString(R.string.high_score) + " " + score);
    }

    public void markPairCardsDone(int position1, int position2) {
        adapter.removeAt(position1);
        adapter.removeAt(position2);
    }

    public void flipPairCards(int position1, int position2) {
        adapter.flipPairCardsBack((BoardRecyclerViewAdapter.ViewHolder) boardRecyclerView.
                        findViewHolderForAdapterPosition(position2),
                (BoardRecyclerViewAdapter.ViewHolder) boardRecyclerView.findViewHolderForAdapterPosition(position1));

    }
}
