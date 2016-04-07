package com.borama.zesb.nhameykorban.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by phearom on 3/30/16.
 */
public class RedirectUriUtils {
    public static void goPlayStore(Context context) {
        final String appPackageName = "com.borama.zesb";
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (Exception e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }
}
