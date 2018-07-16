package com.memorygame.board.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.memorygame.R;
import com.memorygame.model.Card;
import com.memorygame.util.views.FlipAnimation;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sarah on 7/15/18.
 */

public class BoardRecyclerViewAdapter extends RecyclerView.Adapter<BoardRecyclerViewAdapter.ViewHolder> {

    private List<Card> mData;
    private LayoutInflater mInflater;
    private CardClickListener mCardClickListener;


    public BoardRecyclerViewAdapter(Context context, List<Card> data, CardClickListener cardClickListener) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mCardClickListener = cardClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.board_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Card card = mData.get(position);
        if (card.isMarkedDone())
            holder.cardLayout.setVisibility(View.INVISIBLE);
        else
            holder.cardImageView.setImageResource(card.getImageResourceId());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_card)
        ImageView cardImageView;

        @BindView(R.id.image_bg)
        ImageView imageBackground;

        @BindView(R.id.card_layout)
        FrameLayout cardLayout;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Card card = mData.get(position);
                    if (card.isMarkedDone())
                        return;

                    flip(itemView, imageBackground, cardImageView);

                    if (mCardClickListener != null)
                        mCardClickListener.onCardClick(card, position);
                }
            });
        }

    }


    private void flip(View v, View imageTop, View imageBottom) {
        FlipAnimation flipAnimation = new FlipAnimation(imageTop, imageBottom);
        if (imageTop.getVisibility() == View.GONE) {
            flipAnimation.reverse();
        }
        v.startAnimation(flipAnimation);
    }

    public void flipPairCardsBack(ViewHolder viewHolder1, ViewHolder viewHolder2) {
        if (viewHolder1.imageBackground.getVisibility() == View.GONE &&
                viewHolder2.imageBackground.getVisibility() == View.GONE
                && viewHolder1.cardLayout.getVisibility() == View.VISIBLE
                && viewHolder2.cardLayout.getVisibility() == View.VISIBLE) {
            flip(viewHolder1.itemView, viewHolder1.cardImageView, viewHolder1.imageBackground);
            flip(viewHolder2.itemView, viewHolder2.cardImageView, viewHolder2.imageBackground);

        }
    }

    public void removeAt(int position) {
        mData.get(position).setMarkedDone(true);
        notifyItemChanged(position);
    }

    public interface CardClickListener {
        void onCardClick(Card card, int position);
    }
}