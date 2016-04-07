package com.borama.zesb.nhameykorban.viewmodels;

import android.databinding.Bindable;
import android.view.View;

import com.borama.zesb.nhameykorban.BR;

/**
 * Created by phearom on 3/30/16.
 */
public class PersonViewModel extends BaseViewModel {
    private boolean viewable = false;
    private int color;
    private final int icon;
    private final String name;

    public PersonViewModel(int icon, String name, int color) {
        this.icon = icon;
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public int getIcon() {
        return icon;
    }

    public int getColor() {
        return color;
    }

    public View.OnClickListener onRemove() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != getRemoveHandler())
                    getRemoveHandler().onClick(PersonViewModel.this, v);
            }
        };
    }

    @Bindable
    public boolean isViewable() {
        return viewable;
    }

    public void setViewable(boolean viewable) {
        this.viewable = viewable;
        notifyPropertyChanged(BR.viewable);
    }
}
