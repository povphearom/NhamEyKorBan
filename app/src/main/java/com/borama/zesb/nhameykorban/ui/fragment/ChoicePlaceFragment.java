package com.borama.zesb.nhameykorban.ui.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
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
import com.borama.zesb.nhameykorban.databinding.AlertBoxBinding;
import com.borama.zesb.nhameykorban.databinding.FragmentChoicePlaceBinding;
import com.borama.zesb.nhameykorban.mapper.PlaceMapper;
import com.borama.zesb.nhameykorban.models.Place;
import com.borama.zesb.nhameykorban.realmmodels.PlaceGroupRealm;
import com.borama.zesb.nhameykorban.realmmodels.PlaceRealm;
import com.borama.zesb.nhameykorban.ui.ManagePlaceActivity;
import com.borama.zesb.nhameykorban.utils.AlertUtils;
import com.borama.zesb.nhameykorban.utils.Res;
import com.borama.zesb.nhameykorban.viewmodels.BaseViewModel;
import com.borama.zesb.nhameykorban.viewmodels.PlaceViewModel;
import com.borama.zesb.nhameykorban.viewmodels.PlacesViewModel;

import java.util.ArrayList;
import java.util.List;

public class ChoicePlaceFragment extends Fragment {
    private static ChoicePlaceFragment instance;
    private FragmentChoicePlaceBinding mBinding;
    private PlacesViewModel mPlacesModel;
    private ManagePlaceActivity appActivity;

    public ChoicePlaceFragment() {
    }

    public static ChoicePlaceFragment init() {
        if (null == instance)
            instance = new ChoicePlaceFragment();
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appActivity = (ManagePlaceActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_choice_place, container, false);

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

        mBinding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getAllCheckPlaces().size() > 1) {
                    alertSaveCancel();
                } else {
                    AlertUtils.info(getContext(), Res.getString(getContext(), R.string.alert_invalid_check));
                }
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

    private List<Place> getListPlaces() {
        List<Place> list = new ArrayList<>();
        for (PlaceViewModel pVM : getAllCheckPlaces()) {
            list.add(pVM.getModel());
        }
        return list;
    }

    private void alertSaveCancel() {
        final AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        final AlertBoxBinding binding = DataBindingUtil.inflate(LayoutInflater.from(alertDialog.getContext()), R.layout.alert_box, null, false);
        alertDialog.setMessage(Res.getString(getContext(), R.string.message_place_create));
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, Res.getString(alertDialog.getContext(), R.string.alert_save), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!TextUtils.isEmpty(binding.edtAlertValue.getText())) {
                    PlaceGroupRealm placeGroupRealm = new PlaceGroupRealm();
                    placeGroupRealm.setId(String.valueOf(System.currentTimeMillis()));
                    placeGroupRealm.setName(binding.edtAlertValue.getText().toString().trim());
                    placeGroupRealm.setPlaces(PlaceMapper.toPlaceRealmList(getListPlaces()));
                    RealmHelper.init(getContext()).addObject(placeGroupRealm);
                    appActivity.setPageSelected(1);
                    alertDialog.dismiss();
                }
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, Res.getString(alertDialog.getContext(), R.string.alert_skip), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CreatedPlaceFragment.init().initData();
            }
        });
        alertDialog.setView(binding.getRoot());
        alertDialog.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPlacesModel.items.clear();
        mPlacesModel = null;
        appActivity = null;
        mBinding.unbind();
        mBinding = null;
        instance = null;
    }
}
