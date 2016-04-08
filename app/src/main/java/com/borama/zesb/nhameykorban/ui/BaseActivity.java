package com.borama.zesb.nhameykorban.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.borama.zesb.nhameykorban.utils.Res;

/**
 * Created by phearom on 3/30/16.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Res.changeLanguage(this, "km");
        super.onCreate(savedInstanceState);
    }
}
