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
import com.borama.zesb.nhameykorban.databinding.FragmentCreatedPlaceBinding;
import com.borama.zesb.nhameykorban.mapper.PlaceGroupMapper;
import com.borama.zesb.nhameykorban.models.PlaceGroup;
import com.borama.zesb.nhameykorban.realmmodels.PlaceGroupRealm;
import com.borama.zesb.nhameykorban.viewmodels.BaseViewModel;
import com.borama.zesb.nhameykorban.viewmodels.PlaceGroupViewModel;
import com.borama.zesb.nhameykorban.viewmodels.PlaceGroupsViewModel;

import io.realm.RealmResults;

public class CreatedPlaceFragment extends Fragment {
    private static CreatedPlaceFragment instance;
    private FragmentCreatedPlaceBinding mBinding;
    private PlaceGroupsViewModel mPlaceGroups;

    public static CreatedPlaceFragment init() {
        if (null == instance)
            instance = new CreatedPlaceFragment();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_created_place, container, false);
        mPlaceGroups = new PlaceGroupsViewModel(removeHandler());
        mBinding.setPlaceGroups(mPlaceGroups);
        mBinding.setView(this);
        mBinding.recyclerPlace.addItemDecoration(new SpacesItemDecoration(8));
        return mBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    public void initData() {
        mPlaceGroups.items.clear();
        for (PlaceGroupRealm placeGroupRealm : RealmHelper.init(getContext()).getObject(PlaceGroupRealm.class)) {
            PlaceGroup placeGroup = PlaceGroupMapper.toPlaceGroup(placeGroupRealm);
            mPlaceGroups.addItem(new PlaceGroupViewModel(getActivity(), placeGroup));
        }
    }

    public ItemBinder<BaseViewModel> itemViewBinder() {
        return new CompositeItemBinder<>(new BaseBinder(BR.placeGroup, R.layout.item_group_places));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        instance = null;
        mPlaceGroups.items.clear();
        mPlaceGroups = null;
        mBinding.unbind();
        mBinding = null;
    }

    private ClickHandler<PlaceGroupViewModel> removeHandler() {
        return new ClickHandler<PlaceGroupViewModel>() {
            @Override
            public void onClick(PlaceGroupViewModel viewModel, View v) {
                RealmResults<PlaceGroupRealm> placeGroupRealms = RealmHelper.init(getContext()).getObject(PlaceGroupRealm.class);
                RealmHelper.init(getContext()).removeObject(placeGroupRealms.where().equalTo("id", viewModel.getModel().getId()));
                mPlaceGroups.removeItem(viewModel);
            }
        };
    }
}
