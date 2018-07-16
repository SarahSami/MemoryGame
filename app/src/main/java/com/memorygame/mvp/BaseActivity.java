package com.memorygame.mvp;

import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<T extends BaseActivityPresenter> extends AppCompatActivity {

    protected T presenter;

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter = null;
    }

}