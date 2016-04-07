package com.borama.zesb.nhameykorban.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

/**
 * Created by phearom on 3/30/16.
 */
public class SpinsViewModel extends BaseObservable {
    @Bindable
    public ObservableList<SpinViewModel> items;

    public SpinsViewModel() {
        items = new ObservableArrayList<>();
    }

    public void addItem(SpinViewModel item) {
        items.add(item);
    }

    public void removeItem(SpinViewModel item) {
        items.remove(item);
    }
}
