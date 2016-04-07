package com.borama.zesb.nhameykorban.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.borama.zesb.api.core.binder.CompositeItemBinder;
import com.borama.zesb.api.core.binder.ItemBinder;
import com.borama.zesb.nhameykorban.BR;
import com.borama.zesb.nhameykorban.R;
import com.borama.zesb.nhameykorban.application.App;
import com.borama.zesb.nhameykorban.binder.BaseBinder;
import com.borama.zesb.nhameykorban.databinding.FragmentInfoGameBinding;
import com.borama.zesb.nhameykorban.ui.GroupGameActivity;
import com.borama.zesb.nhameykorban.ui.SingleGameActivity;
import com.borama.zesb.nhameykorban.utils.K;
import com.borama.zesb.nhameykorban.utils.Res;
import com.borama.zesb.nhameykorban.viewmodels.BaseViewModel;
import com.borama.zesb.nhameykorban.viewmodels.SpinViewModel;
import com.borama.zesb.nhameykorban.viewmodels.SpinsViewModel;

@SuppressLint("ValidFragment")
public class InfoGameDialogFragment extends DialogFragment {
    private boolean mWithDice = false;
    private boolean mIsSingle = false;
    private boolean mJustView = false;
    private static InfoGameDialogFragment instance;
    private FragmentInfoGameBinding mBinding;

    private SpinsViewModel mSpins;

    public InfoGameDialogFragment(boolean isSingle, boolean withDice, boolean justView) {
        mIsSingle = isSingle;
        mWithDice = withDice;
        mJustView = justView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.Theme_AppCompat_NoActionBar);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setStatusBarColorIfPossible(Res.getColor(getContext(), R.color.colorPrimaryDark));
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_info_game, container, false);

        mBinding.toolbarInfo.setTitle(getTitle());
        mBinding.toolbarInfo.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        mBinding.toolbarInfo.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissDialog();
            }
        });

        mBinding.txvGameMessage.setText(Res.getString(getContext(), R.string.condition) + getTitle());

        mSpins = new SpinsViewModel();
        mBinding.setSpins(mSpins);
        mBinding.setView(this);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mWithDice) {
            getDices();
        } else {
            getDrinks();
        }
        mBinding.btnPlayGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App<SpinViewModel> app = (App) getActivity().getApplication();
                app.setListData(K.Key_Games, mSpins.items);
                if (mIsSingle) {
                    SingleGameActivity.launch(getActivity(), mWithDice);
                } else {
                    GroupGameActivity.launch(getActivity(), mWithDice);
                }
                dismissDialog();
            }
        });

        if (mJustView) {
            mBinding.btnPlayGame.setVisibility(View.GONE);
        } else {
            mBinding.btnPlayGame.setVisibility(View.VISIBLE);
        }
    }

    private String getTitle() {
        return mWithDice ? Res.getString(getContext(), R.string.normal_game) : Res.getString(getContext(), R.string.drink_game);
    }

    public void setStatusBarColorIfPossible(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getDialog().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getDialog().getWindow().setStatusBarColor(color);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    private void getDices() {
        mSpins.addItem(new SpinViewModel(R.drawable.ic_dice_1, Res.getString(getContext(), R.string.dice_1), Res.getColor(getContext(), R.color.dice_1)));
        mSpins.addItem(new SpinViewModel(R.drawable.ic_dice_2, Res.getString(getContext(), R.string.dice_2), Res.getColor(getContext(), R.color.dice_2)));
        mSpins.addItem(new SpinViewModel(R.drawable.ic_dice_3, Res.getString(getContext(), R.string.dice_3), Res.getColor(getContext(), R.color.dice_3)));
        mSpins.addItem(new SpinViewModel(R.drawable.ic_dice_4, Res.getString(getContext(), R.string.dice_4), Res.getColor(getContext(), R.color.dice_4)));
        mSpins.addItem(new SpinViewModel(R.drawable.ic_dice_5, Res.getString(getContext(), R.string.dice_5), Res.getColor(getContext(), R.color.dice_5)));
        mSpins.addItem(new SpinViewModel(R.drawable.ic_dice_6, Res.getString(getContext(), R.string.dice_6), Res.getColor(getContext(), R.color.dice_6)));
    }

    private void getDrinks() {
        mSpins.addItem(new SpinViewModel(R.drawable.ic_drink_1, Res.getString(getContext(), R.string.drink_1), Res.getColor(getContext(), R.color.drink_1)));
        mSpins.addItem(new SpinViewModel(R.drawable.ic_drink_2, Res.getString(getContext(), R.string.drink_2), Res.getColor(getContext(), R.color.drink_2)));
        mSpins.addItem(new SpinViewModel(R.drawable.ic_drink_3, Res.getString(getContext(), R.string.drink_3), Res.getColor(getContext(), R.color.drink_3)));
        mSpins.addItem(new SpinViewModel(R.drawable.ic_drink_4, Res.getString(getContext(), R.string.drink_4), Res.getColor(getContext(), R.color.drink_4)));
        mSpins.addItem(new SpinViewModel(R.drawable.ic_drink_5, Res.getString(getContext(), R.string.drink_5), Res.getColor(getContext(), R.color.drink_5)));
        mSpins.addItem(new SpinViewModel(R.drawable.ic_drink_6, Res.getString(getContext(), R.string.drink_6), Res.getColor(getContext(), R.color.drink_6)));
        mSpins.addItem(new SpinViewModel(R.drawable.ic_drink_7, Res.getString(getContext(), R.string.drink_7), Res.getColor(getContext(), R.color.drink_7)));
        mSpins.addItem(new SpinViewModel(R.drawable.ic_drink_8, Res.getString(getContext(), R.string.drink_8), Res.getColor(getContext(), R.color.drink_8)));
        mSpins.addItem(new SpinViewModel(R.drawable.ic_drink_9, Res.getString(getContext(), R.string.drink_9), Res.getColor(getContext(), R.color.drink_9)));
        mSpins.addItem(new SpinViewModel(R.drawable.ic_drink_10, Res.getString(getContext(), R.string.drink_10), Res.getColor(getContext(), R.color.drink_10)));
        mSpins.addItem(new SpinViewModel(R.drawable.ic_drink_11, Res.getString(getContext(), R.string.drink_11), Res.getColor(getContext(), R.color.drink_11)));
        mSpins.addItem(new SpinViewModel(R.drawable.ic_drink_12, Res.getString(getContext(), R.string.drink_12), Res.getColor(getContext(), R.color.drink_12)));
    }

    public ItemBinder<BaseViewModel> itemViewBinder() {
        BaseBinder binder = new BaseBinder(BR.spin, R.layout.item_spin_no);
        return new CompositeItemBinder<>(binder);
    }

    public static InfoGameDialogFragment showDialog(FragmentManager supportFragmentManager, boolean isSingle, boolean withDice, boolean justView) {
        if (null == instance)
            instance = new InfoGameDialogFragment(isSingle, withDice, justView);
        instance.show(supportFragmentManager, "Info Game");
        return instance;
    }

    public static void dismissDialog() {
        if (null != instance)
            instance.dismiss();
        instance = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSpins = null;
        instance = null;
        mBinding.unbind();
        mBinding = null;
    }
}
