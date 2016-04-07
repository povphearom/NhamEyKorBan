package com.borama.zesb.nhameykorban.viewmodels;

import android.databinding.Bindable;
import android.view.View;

import com.borama.zesb.nhameykorban.BR;
import com.borama.zesb.nhameykorban.models.Place;

/**
 * Created by phearom on 3/30/16.
 */
public class PlaceViewModel extends BaseViewModel {
    private boolean checked;
    private Place model;

    public PlaceViewModel(boolean checked, Place model) {
        this.checked = checked;
        this.model = model;
    }

    public PlaceViewModel(Place model) {
        this.model = model;
    }

    public Place getModel() {
        return this.model;
    }

    @Bindable
    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
        notifyPropertyChanged(BR.checked);
    }

    public int getPhoto() {
        return model.getPhoto();
    }

    public String getName() {
        return model.getName();
    }

    public View.OnClickListener onCheck() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != getCheckHandler())
                    getCheckHandler().onClick(PlaceViewModel.this, v);
            }
        };
    }
}
