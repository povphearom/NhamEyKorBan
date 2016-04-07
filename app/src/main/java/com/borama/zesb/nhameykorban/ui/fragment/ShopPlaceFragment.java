package com.borama.zesb.nhameykorban.ui.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.borama.zesb.api.core.binder.CompositeItemBinder;
import com.borama.zesb.api.core.binder.ItemBinder;
import com.borama.zesb.api.core.listener.ClickHandler;
import com.borama.zesb.api.repositories.RealmHelper;
import com.borama.zesb.api.urils.SpacesItemDecoration;
import com.borama.zesb.nhameykorban.BR;
import com.borama.zesb.nhameykorban.R;
import com.borama.zesb.nhameykorban.binder.BaseBinder;
import com.borama.zesb.nhameykorban.databinding.FragmentShopPlaceBinding;
import com.borama.zesb.nhameykorban.mapper.PlaceMapper;
import com.borama.zesb.nhameykorban.realmmodels.PlaceRealm;
import com.borama.zesb.nhameykorban.viewmodels.BaseViewModel;
import com.borama.zesb.nhameykorban.viewmodels.PlaceViewModel;
import com.borama.zesb.nhameykorban.viewmodels.PlacesViewModel;

import java.util.List;

public class ShopPlaceFragment extends Fragment {
    private FragmentShopPlaceBinding mBinding;
    private PlacesViewModel mPlacesModel;

    public ShopPlaceFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shop_place, container, false);

        mPlacesModel = new PlacesViewModel();
        mPlacesModel.setCheckHandler(checkHandler());
        mBinding.setPlaces(mPlacesModel);
        mBinding.setView(this);
        mBinding.recyclerPlace.addItemDecoration(new SpacesItemDecoration(8));
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.chkPlaceAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlacesModel.setCheckedAll(!mPlacesModel.isCheckedAll());
                mPlacesModel.setCheckAll(mPlacesModel.isCheckedAll());
            }
        });

        for (PlaceRealm placeRealm : RealmHelper.init(getContext()).getObject(PlaceRealm.class)) {
            mPlacesModel.addItem(new PlaceViewModel(false, PlaceMapper.toPlace(placeRealm)));
        }
    }

    public List<PlaceViewModel> getAllCheckPlaces() {
        return mPlacesModel.getCheckAll();
    }

    public ItemBinder<BaseViewModel> itemViewBinder() {
        return new CompositeItemBinder<>(new BaseBinder(BR.place, R.layout.item_place));
    }

    public ClickHandler<PlaceViewModel> checkHandler() {
        return new ClickHandler<PlaceViewModel>() {
            @Override
            public void onClick(PlaceViewModel place, View v) {
                place.setChecked(!place.isChecked());
                mPlacesModel.setCheckedAll(mPlacesModel.hasCheckedAll());
            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPlacesModel.items.clear();
        mPlacesModel = null;
        mBinding.unbind();
        mBinding = null;
    }
}
