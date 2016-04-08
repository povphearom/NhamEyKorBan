package com.borama.zesb.nhameykorban.ui;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.borama.zesb.api.core.binder.CompositeItemBinder;
import com.borama.zesb.api.core.binder.ItemBinder;
import com.borama.zesb.api.widget.SpinRecyclerView;
import com.borama.zesb.nhameykorban.BR;
import com.borama.zesb.nhameykorban.R;
import com.borama.zesb.nhameykorban.application.App;
import com.borama.zesb.nhameykorban.binder.BaseBinder;
import com.borama.zesb.nhameykorban.databinding.ActivityGroupGameBinding;
import com.borama.zesb.nhameykorban.ui.fragment.InfoGameDialogFragment;
import com.borama.zesb.nhameykorban.ui.fragment.RandomFragment;
import com.borama.zesb.nhameykorban.utils.K;
import com.borama.zesb.nhameykorban.utils.Res;
import com.borama.zesb.nhameykorban.viewmodels.BaseViewModel;
import com.borama.zesb.nhameykorban.viewmodels.PersonViewModel;
import com.borama.zesb.nhameykorban.viewmodels.SpinViewModel;

import java.util.ArrayList;
import java.util.List;

public class GroupGameActivity extends BaseActivity {
    private final static String WITH_DICE = "with_dice";
    private ActivityGroupGameBinding mBinding;
    private RandomFragment mSpinPeopleFragment;
    private RandomFragment mSpinThingsFragment;

    private List<SpinViewModel> mThings;
    private List<BaseViewModel> mPeople;
    private SpinViewModel mThingResult = null;
    private PersonViewModel mPersonResult = null;


    public static void launch(Activity activity, boolean withDice) {
        Intent intent = new Intent(activity, GroupGameActivity.class);
        intent.putExtra(WITH_DICE, withDice);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_group_game);
        setSupportActionBar(mBinding.gameToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initView();

        mPeople = new ArrayList<>();
        mThings = new ArrayList<>();
        mBinding.setView(this);

        mSpinPeopleFragment = (RandomFragment) getSupportFragmentManager().findFragmentById(R.id.spinPeople);
        mSpinPeopleFragment.setOnScrolledListener(new SpinRecyclerView.OnScrolledListener() {
            @Override
            public void onStopped() {
                if (mBinding == null)
                    return;
                mBinding.lilResultPerson.setVisibility(View.VISIBLE);
                mBinding.txvEqual.setVisibility(View.VISIBLE);
                mBinding.setSpinPerson(mPersonResult);
            }
        });
        mSpinThingsFragment = (RandomFragment) getSupportFragmentManager().findFragmentById(R.id.spinThings);
        mSpinThingsFragment.setOnScrolledListener(new SpinRecyclerView.OnScrolledListener() {
            @Override
            public void onStopped() {
                if (mBinding == null)
                    return;
                mBinding.lilResultThing.setVisibility(View.VISIBLE);
                mBinding.setSpinThing(mThingResult);
            }
        });

        mBinding.btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replay();
            }
        });

        initDataPeople();
        initDataGames();
        initSpinPeople();
        initSpinThings();
    }

    private void initView() {
        mBinding.txvSpinGames.setText(isWithDice() ? Res.getString(this, R.string.start_spin_dice) : Res.getString(this, R.string.start_spin_dice_drink));
    }

    private boolean isWithDice() {
        return getIntent().getBooleanExtra(WITH_DICE, false);
    }

    private void replay() {
        reselectResult();
    }

    private void reselectResult() {
        mPersonResult = (PersonViewModel) mPeople.get((int) (mPeople.size() * Math.random()));
        mThingResult = mThings.get((int) (mThings.size() * Math.random()));
        mSpinPeopleFragment.replay(mPersonResult);
        mSpinThingsFragment.replay(mThingResult);
    }

    private void initDataPeople() {
        App<PersonViewModel> app = (App) getApplication();
        for (PersonViewModel p : app.getListData(K.Key_People)) {
            mPeople.add(p);
        }
    }

    private void initDataGames() {
        App<SpinViewModel> app = (App) getApplication();
        for (SpinViewModel s : app.getListData(K.Key_Games)) {
            mThings.add(s);
        }
    }

    private void initSpinPeople() {
        mPersonResult = (PersonViewModel) mPeople.get((int) (mPeople.size() * Math.random()));
        mSpinPeopleFragment.setItemBinder(itemPersonBinder());
        mSpinPeopleFragment.setData(mPersonResult, mPeople);
        mBinding.setSpinPerson(mPersonResult);
    }

    private void initSpinThings() {
        mThingResult = mThings.get((int) (mThings.size() * Math.random()));
        mSpinThingsFragment.setItemBinder(itemThingBinder());
        mSpinThingsFragment.setData(mThingResult, mThings);
        mBinding.setSpinThing(mThingResult);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_info, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_info:
                InfoGameDialogFragment.showDialog(getSupportFragmentManager(), false, isWithDice(), true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public ItemBinder<BaseViewModel> itemThingBinder() {
        BaseBinder binder = new BaseBinder(BR.spinGame, R.layout.item_spin_game);
        return new CompositeItemBinder<>(binder);
    }

    public ItemBinder<BaseViewModel> itemPersonBinder() {
        BaseBinder binder = new BaseBinder(BR.person, R.layout.item_person_no);
        return new CompositeItemBinder<>(binder);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mThings.clear();
        mPeople.clear();
        mThings = null;
        mPeople = null;
        mSpinPeopleFragment = null;
        mSpinThingsFragment = null;
        mThingResult = null;
        mPersonResult = null;
        mBinding.unbind();
        mBinding = null;
    }
}