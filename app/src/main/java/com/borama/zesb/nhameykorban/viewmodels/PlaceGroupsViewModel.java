package com.borama.zesb.nhameykorban.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.borama.zesb.api.core.listener.ClickHandler;

/**
 * Created by phearom on 3/30/16.
 */
public class PlaceGroupsViewModel extends BaseObservable {
    private ClickHandler<PlaceGroupViewModel> removeHandler;
    @Bindable
    public ObservableList<PlaceGroupViewModel> items;

    public PlaceGroupsViewModel(ClickHandler<PlaceGroupViewModel> removeHandler) {
        items = new ObservableArrayList<>();
        this.removeHandler = removeHandler;
    }

    public void addItem(PlaceGroupViewModel item) {
        item.setRemoveHandler(removeHandler);
        items.add(item);
    }

    public void removeItem(PlaceGroupViewModel item) {
        items.remove(item);
    }
}
