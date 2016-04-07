package com.borama.zesb.nhameykorban.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.borama.zesb.api.core.listener.ClickHandler;


/**
 * Created by phearom on 3/30/16.
 */
public class PeopleViewModel extends BaseObservable {
    @Bindable
    public ObservableList<PersonViewModel> items;
    private ClickHandler<PersonViewModel> removeHandler;
    private OnItemChangedCallback mCallback;

    public PeopleViewModel(ClickHandler<PersonViewModel> removeHandler, OnItemChangedCallback mCallback) {
        this.mCallback = mCallback;
        items = new ObservableArrayList<>();
        this.removeHandler = removeHandler;
    }

    public void addItem(PersonViewModel item) {
        item.setRemoveHandler(removeHandler);
        if (items.size() > 0)
            items.add(items.size() - 1, item);
        else
            items.add(item);

        if (null != mCallback)
            mCallback.onItemChange(items.size());
    }

    public void removeItem(PersonViewModel item) {
        items.remove(item);
        if (null != mCallback)
            mCallback.onItemChange(items.size());
    }

    public interface OnItemChangedCallback {
        void onItemChange(int size);
    }
}
