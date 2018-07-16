package com.memorygame.high_scores.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.memorygame.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sarah on 7/15/18.
 */

public class HighScoreRecyclerViewAdapter extends RecyclerView.Adapter<HighScoreRecyclerViewAdapter.ViewHolder> {

    private List<Double> mData;
    private LayoutInflater mInflater;


    public HighScoreRecyclerViewAdapter(Context context, List<Double> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.high_score_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.highScoreTv.setText(String.valueOf(mData.get(position)));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.high_score_tv)
        TextView highScoreTv;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}