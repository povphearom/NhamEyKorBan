package com.borama.zesb.nhameykorban.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by phearom on 4/8/16.
 */
public class SessionManager {
    private static SessionManager instance;
    private SharedPreferences mShare = null;
    private SharedPreferences.Editor mEditor = null;

    private SessionManager(Context context) {
        mShare = context.getSharedPreferences("nhameykorban", Context.MODE_PRIVATE);
        mEditor = mShare.edit();
    }

    public static SessionManager init(Context context) {
        if (null == instance)
            instance = new SessionManager(context);
        return instance;
    }

    public void saveUserData(String key, boolean value) {
        mEditor.putBoolean(key, value);
        mEditor.apply();
    }

    public void saveUserData(String key, String value) {
        mEditor.putString(key, value);
        mEditor.apply();
    }

    public void saveUserData(String key, int value) {
        mEditor.putInt(key, value);
        mEditor.apply();
    }

    public String getUserData(String key, String def) {
        return mShare.getString(key, def);
    }

    public boolean getUserData(String key, boolean def) {
        return mShare.getBoolean(key, def);
    }

    public int getUserData(String key, int def) {
        return mShare.getInt(key, def);
    }

    public void clear() {
        mEditor.clear();
        mEditor.apply();
    }
}
