package com.borama.zesb.nhameykorban.ui;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.borama.zesb.api.core.binder.CompositeItemBinder;
import com.borama.zesb.api.core.binder.ItemBinder;
import com.borama.zesb.nhameykorban.BR;
import com.borama.zesb.nhameykorban.R;
import com.borama.zesb.nhameykorban.application.App;
import com.borama.zesb.nhameykorban.binder.BaseBinder;
import com.borama.zesb.nhameykorban.databinding.ItemPlaceGridBinding;
import com.borama.zesb.nhameykorban.viewmodels.BaseViewModel;
import com.borama.zesb.nhameykorban.viewmodels.PlaceViewModel;

import java.util.List;

public class RandomPlaceActivity extends BaseSingleRandomActivity<PlaceViewModel> {
    private App mApp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApp = (App) getApplication();
        begin(getListData());
        mBinding.btnPlayAgain.setVisibility(View.GONE);
    }

    private List<PlaceViewModel> getListData() {
        return mApp.getListData();
    }

    @Override
    public boolean isPlace() {
        return true;
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, RandomPlaceActivity.class);
        activity.startActivity(intent);
    }

    public View getResultView() {
        ItemPlaceGridBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.item_place_grid, null, false);
        binding.setPlaceGrid(getBaseResult());
        binding.getRoot().setLayoutParams(getLayoutParamResults());
        return binding.getRoot();
    }

    @Override
    public ItemBinder<BaseViewModel> itemSpinBinder() {
        BaseBinder binder = new BaseBinder(BR.placeRandom, R.layout.item_place_random);
        return new CompositeItemBinder<>(binder);
    }

    public ItemBinder<BaseViewModel> itemViewBinder() {
        BaseBinder binder = new BaseBinder(BR.placeGrid, R.layout.item_place_grid);
        return new CompositeItemBinder<>(binder);
    }

    public FrameLayout.LayoutParams getLayoutParamResults() {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(getResources().getDimensionPixelOffset(R.dimen.spin_normal_size), getResources().getDimensionPixelOffset(R.dimen.spin_normal_size));
        params.gravity = Gravity.CENTER;
        return params;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}