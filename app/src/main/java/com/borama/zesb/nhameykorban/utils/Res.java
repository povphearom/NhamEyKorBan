package com.borama.zesb.nhameykorban.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

/**
 * Created by phearom on 3/30/16.
 */
public class Res {
    public static int getColor(Context context, int id) {
        return ContextCompat.getColor(context, id);
    }

    public static String getString(Context context, int id) {
        return context.getString(id);
    }

    public static Drawable getDrawable(Context context, int id) {
        return ContextCompat.getDrawable(context, id);
    }
}
