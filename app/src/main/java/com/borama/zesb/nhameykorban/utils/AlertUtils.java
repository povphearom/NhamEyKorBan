package com.borama.zesb.nhameykorban.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.borama.zesb.nhameykorban.R;

/**
 * Created by phearom on 3/30/16.
 */
public class AlertUtils {
    public static void info(Context context, String message) {
        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, Res.getString(alertDialog.getContext(), R.string.alert_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}
