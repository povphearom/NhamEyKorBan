package com.borama.zesb.nhameykorban.ui.fragment;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.borama.zesb.nhameykorban.R;
import com.borama.zesb.nhameykorban.utils.Res;


public class SlashScreenFragment extends DialogFragment {
    private static SlashScreenFragment instance;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.Theme_AppCompat_NoActionBar);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setStatusBarColorIfPossible(Res.getColor(getContext(), R.color.colorPrimaryDark));
        return inflater.inflate(R.layout.fragment_slash_screen, container, false);
    }

    public void setStatusBarColorIfPossible(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getDialog().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getDialog().getWindow().setStatusBarColor(color);
        }
    }

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow()
                .getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    public static SlashScreenFragment showDialog(FragmentManager supportFragmentManager, String ll) {
        if (null == instance)
            instance = new SlashScreenFragment();
        instance.show(supportFragmentManager, ll);
        return instance;
    }

    public static void dismissDialog() {
        if (null != instance)
            instance.dismiss();
        instance = null;
    }
}
