package com.borama.zesb.nhameykorban.ui.fragment;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.borama.zesb.api.core.adapter.SpinRecyclerAdapter;
import com.borama.zesb.api.core.binder.ItemBinder;
import com.borama.zesb.api.urils.SpacesItemDecoration;
import com.borama.zesb.api.widget.SpinRecyclerView;
import com.borama.zesb.nhameykorban.R;
import com.borama.zesb.nhameykorban.databinding.FragmentSpinRandomBinding;

import java.util.List;

public class RandomFragment<T> extends Fragment {
    private FragmentSpinRandomBinding mBinding;

    private SpinRecyclerView mSpinRecyclerView;
    private SpinRecyclerAdapter<T> mSpinRecyclerAdapter;

    public RandomFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void setOnScrolledListener(SpinRecyclerView.OnScrolledListener scrolledListener) {
        mSpinRecyclerView.setOnScrolledListener(scrolledListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_spin_random, container, false);
        mSpinRecyclerView = mBinding.spinRecyclerGift;
        mSpinRecyclerView.setEnableScroll(false);
        mSpinRecyclerView.setDuration(120f).setShouldGoRight(false);
        mSpinRecyclerView.addItemDecoration(new SpacesItemDecoration(4));
        int itemWidth = (int) getResources().getDimension(R.dimen.spin_item_size);
        mSpinRecyclerView.initCenterView(mBinding.spinCenterPin, itemWidth);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setData(T item, final List<T> items) {
        mSpinRecyclerView.setSelected(item);
        mSpinRecyclerAdapter.setItems(items);
        mSpinRecyclerView.resetCount();
        mSpinRecyclerView.startSpin(items.size());
    }

    public void replay(T item) {
        if (mSpinRecyclerView.isStopped()) {
            if (mSpinRecyclerView.getCount() > 1000)
                mSpinRecyclerView.resetCount();
            mSpinRecyclerView.setSelected(item);
            mSpinRecyclerView.startSpin(mSpinRecyclerAdapter.getRealItemCount());
        }
    }

    public void setItemBinder(ItemBinder<T> itemBinder) {
        mSpinRecyclerAdapter = new SpinRecyclerAdapter<>(itemBinder);
        mSpinRecyclerView.setAdapter(mSpinRecyclerAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSpinRecyclerView = null;
        mSpinRecyclerAdapter = null;
        mBinding.unbind();
    }
}
