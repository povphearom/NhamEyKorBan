package com.borama.zesb.nhameykorban.viewmodels;

import android.databinding.BaseObservable;

import com.borama.zesb.api.core.listener.ClickHandler;

/**
 * Created by phearom on 3/30/16.
 */
public class BaseViewModel<T> extends BaseObservable {
    private ClickHandler<T> removeHandler;
    private ClickHandler<T> checkHandler;

    public ClickHandler<T> getRemoveHandler() {
        return removeHandler;
    }

    public void setRemoveHandler(ClickHandler<T> removeHandler) {
        this.removeHandler = removeHandler;
    }

    public ClickHandler<T> getCheckHandler() {
        return checkHandler;
    }

    public void setCheckHandler(ClickHandler<T> checkHandler) {
        this.checkHandler = checkHandler;
    }
}
