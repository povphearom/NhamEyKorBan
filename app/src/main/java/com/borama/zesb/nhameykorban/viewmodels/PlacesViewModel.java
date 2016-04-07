package com.borama.zesb.nhameykorban.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.borama.zesb.api.core.listener.ClickHandler;
import com.borama.zesb.nhameykorban.BR;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phearom on 3/30/16.
 */
public class PlacesViewModel extends BaseObservable {
    private boolean checkedAll = false;
    private ClickHandler<PlaceViewModel> checkHandler;
    @Bindable
    public ObservableList<PlaceViewModel> items;

    public PlacesViewModel() {
        items = new ObservableArrayList<>();
    }

    public void addItem(PlaceViewModel item) {
        item.setCheckHandler(checkHandler);
        items.add(0, item);
    }

    public void removeItem(PlaceViewModel item) {
        items.remove(item);
    }

    public boolean hasCheckedAll() {
        for (PlaceViewModel base : items) {
            PlaceViewModel place = (PlaceViewModel) base;
            if (!place.isChecked())
                return false;
        }
        return true;
    }

    public void setCheckAll(boolean checkAll) {
        for (PlaceViewModel place : items) {
            place.setChecked(checkAll);
        }
    }

    @Bindable
    public boolean isCheckedAll() {
        return checkedAll;
    }

    public List<PlaceViewModel> getCheckAll() {
        List<PlaceViewModel> list = new ArrayList<>();
        for (PlaceViewModel place : items) {
            if (place.isChecked()) {
                list.add(place);
            }
        }
        return list;
    }

    public void setCheckedAll(boolean checkedAll) {
        this.checkedAll = checkedAll;
        notifyPropertyChanged(BR.checkedAll);
    }

    public void setCheckHandler(ClickHandler<PlaceViewModel> checkHandler) {
        this.checkHandler = checkHandler;
    }
}
