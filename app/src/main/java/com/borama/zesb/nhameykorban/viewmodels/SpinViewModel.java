package com.borama.zesb.nhameykorban.viewmodels;

import android.graphics.Color;

/**
 * Created by phearom on 3/30/16.
 */
public class SpinViewModel extends BaseViewModel {
    private final int url;
    private final String name;
    private int color = Color.WHITE;

    public SpinViewModel(int url, String name) {
        this.url = url;
        this.name = name;
    }

    public SpinViewModel(int url, String name, int color) {
        this.url = url;
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public int getUrl() {
        return url;
    }

    public int getColor() {
        return this.color;
    }
}
