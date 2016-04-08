package com.borama.zesb.nhameykorban.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.borama.zesb.api.core.binder.ItemBinder;
import com.borama.zesb.api.urils.SpacesItemDecoration;
import com.borama.zesb.api.widget.SpinRecyclerView;
import com.borama.zesb.nhameykorban.R;
import com.borama.zesb.nhameykorban.databinding.ActivitySingleRandomBinding;
import com.borama.zesb.nhameykorban.ui.fragment.RandomFragment;
import com.borama.zesb.nhameykorban.utils.Res;
import com.borama.zesb.nhameykorban.viewmodels.BaseViewModel;
import com.borama.zesb.nhameykorban.viewmodels.BasesViewModel;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseSingleRandomActivity<T> extends BaseActivity implements SpinRecyclerView.OnScrolledListener {
    private List<T> allItems = new ArrayList<>();

    private SpacesItemDecoration spacesItemDecoration = null;

    private boolean hasResult = false;
    protected ActivitySingleRandomBinding mBinding = null;
    private RandomFragment mRandomFragment = null;
    private T mBasesResult = null;
    private BasesViewModel mBases = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_single_random);
        setSupportActionBar(mBinding.spinToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initView();
        mRandomFragment = (RandomFragment) getSupportFragmentManager().findFragmentById(R.id.randomSpin);
        mRandomFragment.setOnScrolledListener(this);
    }

    public void begin(List<T> list) {
        if (isPlace()) {
            initSpin(list);
            prepareUI(UIFlag.SHOW_LIST);
            spacesItemDecoration = new SpacesItemDecoration(8);
            mBinding.recyclerSpinAll.addItemDecoration(spacesItemDecoration);
            mBases = new BasesViewModel();
            mBinding.setBases(mBases);
            mBinding.setView(this);
            for (T b : list) {
                mBases.addItem((BaseViewModel) b);
            }
        } else {
            initSpin(list);
        }
    }

    private void initView() {
        mBinding.txvLabelResult.setText(getHintResult());
        mBinding.txvLabelSpin.setText(getHintSpin());
    }

    public void replay() {
        mBasesResult = (T) allItems.get((int) (allItems.size() * Math.random()));
        mRandomFragment.replay(mBasesResult);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public abstract boolean isPlace();

    public abstract View getResultView();

    public abstract ItemBinder<BaseViewModel> itemSpinBinder();

    public ItemBinder<BaseViewModel> itemViewBinder() {
        return null;
    }

    @Override
    public void onStopped() {
        if (mBinding == null)
            return;
        hasResult = true;
        mBinding.frlContentResult.removeAllViews();
        mBinding.frlContentResult.addView(getResultView());
        prepareUI(UIFlag.GET_RESULT);
        initView();
    }

    public String getHintSpin() {
        return Res.getString(this, R.string.choose_place);
    }

    public String getHintResult() {
        return hasResult ? Res.getString(this, R.string.place_for_you) : Res.getString(this, R.string.list_of_place);
    }

    private void initSpin(List<T> list) {
        allItems = list;
        mBasesResult = list.get((int) (list.size() * Math.random()));
        mRandomFragment.setItemBinder(itemSpinBinder());
        mRandomFragment.setData(mBasesResult, list);
    }

    public T getBaseResult() {
        return mBasesResult;
    }

    private void prepareUI(UIFlag uiFlag) {
        switch (uiFlag) {
            case SHOW_LIST:
                mBinding.lilResultContent.setVisibility(View.GONE);
                mBinding.recyclerSpinAll.setVisibility(View.VISIBLE);
                break;
            case HIDE_LIST:
                mBinding.lilResultContent.setVisibility(View.VISIBLE);
                mBinding.recyclerSpinAll.setVisibility(View.GONE);
                break;
            case GET_RESULT:
                mBinding.lilResultContent.setVisibility(View.VISIBLE);
                mBinding.recyclerSpinAll.setVisibility(View.GONE);
                break;
        }
    }

    enum UIFlag {
        SHOW_LIST, HIDE_LIST, GET_RESULT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        allItems = null;
        spacesItemDecoration = null;
        mBasesResult = null;
        mRandomFragment = null;
        mBinding.unbind();
        mBinding = null;
    }
}