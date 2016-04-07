package com.borama.zesb.nhameykorban.viewmodels;

import android.app.Activity;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.view.View;

import com.borama.zesb.api.core.binder.CompositeItemBinder;
import com.borama.zesb.api.core.binder.ItemBinder;
import com.borama.zesb.nhameykorban.BR;
import com.borama.zesb.nhameykorban.R;
import com.borama.zesb.nhameykorban.application.App;
import com.borama.zesb.nhameykorban.binder.BaseBinder;
import com.borama.zesb.nhameykorban.models.Place;
import com.borama.zesb.nhameykorban.models.PlaceGroup;
import com.borama.zesb.nhameykorban.ui.RandomPlaceActivity;
import com.borama.zesb.nhameykorban.utils.AlertUtils;
import com.borama.zesb.nhameykorban.utils.Res;

import java.util.List;

/**
 * Created by phearom on 3/30/16.
 */
public class PlaceGroupViewModel extends BaseViewModel<PlaceGroupViewModel> {
    private Activity mContext;
    private final PlaceGroup model;
    @Bindable
    public final ObservableArrayList<PlaceViewModel> places;

    public PlaceGroupViewModel(Activity context, PlaceGroup model) {
        this.mContext = context;
        this.model = model;
        places = new ObservableArrayList<>();
        setPlaces(model.getPlaces());
    }

    public void setPlaces(List<Place> list) {
        for (Place p : list) {
            this.places.add(new PlaceViewModel(p));
        }
    }

    public PlaceGroup getModel() {
        return this.model;
    }

    public String getName() {
        return model.getName();
    }

    public ItemBinder<BaseViewModel> itemViewBinder() {
        return new CompositeItemBinder<>(new BaseBinder(BR.place, R.layout.item_place_no));
    }

    public View.OnClickListener onRemove() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != getRemoveHandler())
                    getRemoveHandler().onClick(PlaceGroupViewModel.this, v);
            }
        };
    }

    public View.OnClickListener onSpin() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (places.size() > 1) {
                    App<PlaceViewModel> app = (App) mContext.getApplication();
                    app.setListData(places);
                    RandomPlaceActivity.launch(mContext);
                } else {
                    AlertUtils.info(mContext, Res.getString(mContext, R.string.alert_invalid));
                }

            }
        };
    }
}
