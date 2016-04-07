package com.borama.zesb.nhameykorban.viewmodels;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by phearom on 3/30/16.
 */
public class MenuViewModel extends BaseViewModel {
    private final int icon;
    private final String title;
    private final int color;
    private final Class<?> mClazz;

    public MenuViewModel(int icon, String title, int color, Class<?> clazz) {
        this.icon = icon;
        this.title = title;
        this.color = color;
        this.mClazz = clazz;
    }

    public String getTitle() {
        return title;
    }

    public int getColor() {
        return color;
    }

    public void openActivity(Activity activity) {
        Intent intent = new Intent(activity, mClazz);
        activity.startActivity(intent);
    }

    public int getIcon() {
        return icon;
    }
}
