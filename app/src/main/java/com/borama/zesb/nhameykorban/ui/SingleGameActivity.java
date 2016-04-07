package com.borama.zesb.nhameykorban.ui;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.borama.zesb.api.core.binder.CompositeItemBinder;
import com.borama.zesb.api.core.binder.ItemBinder;
import com.borama.zesb.nhameykorban.BR;
import com.borama.zesb.nhameykorban.R;
import com.borama.zesb.nhameykorban.application.App;
import com.borama.zesb.nhameykorban.binder.BaseBinder;
import com.borama.zesb.nhameykorban.databinding.ItemSpinResultBinding;
import com.borama.zesb.nhameykorban.ui.fragment.InfoGameDialogFragment;
import com.borama.zesb.nhameykorban.utils.K;
import com.borama.zesb.nhameykorban.utils.Res;
import com.borama.zesb.nhameykorban.viewmodels.BaseViewModel;
import com.borama.zesb.nhameykorban.viewmodels.SpinViewModel;

public class SingleGameActivity extends BaseSingleRandomActivity<SpinViewModel> {

    private static final String Dice_With_Drink = "dice_with_drink";

    public static void launch(Activity activity, boolean dice_with_drink) {
        Intent intent = new Intent(activity, SingleGameActivity.class);
        intent.putExtra(Dice_With_Drink, dice_with_drink);
        activity.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App<SpinViewModel> app = (App) getApplication();
        begin(app.getListData(K.Key_Games));
        mBinding.btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replay();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_info, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_info:
                InfoGameDialogFragment.showDialog(getSupportFragmentManager(), true, isDrinkDice(), true);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean isPlace() {
        return false;
    }

    @Override
    public String getHintResult() {
        return Res.getString(this, R.string.the_result);
    }

    private boolean isDrinkDice() {
        return getIntent().getBooleanExtra(Dice_With_Drink, false);
    }

    @Override
    public String getHintSpin() {
        return isDrinkDice() ? Res.getString(this, R.string.start_spin_dice_drink) : Res.getString(this, R.string.start_spin_dice);
    }

    public View getResultView() {
        ItemSpinResultBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.item_spin_result, null, false);
        binding.setSpinResult(getBaseResult());
        return binding.getRoot();
    }

    public ItemBinder<BaseViewModel> itemSpinBinder() {
        BaseBinder binder = new BaseBinder(BR.spinGame, R.layout.item_spin_game);
        return new CompositeItemBinder<>(binder);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
