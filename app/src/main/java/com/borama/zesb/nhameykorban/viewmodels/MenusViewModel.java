package com.borama.zesb.nhameykorban.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

/**
 * Created by phearom on 3/30/16.
 */
public class MenusViewModel extends BaseObservable {
    @Bindable
    public ObservableList<MenuViewModel> items;

    public MenusViewModel() {
        items = new ObservableArrayList<>();
    }

    public void addItem(MenuViewModel item) {
        items.add(item);
    }
}
