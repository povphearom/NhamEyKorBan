package com.borama.zesb.nhameykorban.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

/**
 * Created by phearom on 3/30/16.
 */
public class BasesViewModel extends BaseObservable {
    @Bindable
    public ObservableList<BaseViewModel> items;

    public BasesViewModel() {
        items = new ObservableArrayList<>();
    }

    public void addItem(BaseViewModel item) {
        items.add(item);
    }

    public void removeItem(BaseViewModel item) {
        items.remove(item);
    }
}
