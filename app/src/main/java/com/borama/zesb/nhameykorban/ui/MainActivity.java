package com.borama.zesb.nhameykorban.ui;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.borama.zesb.api.core.binder.CompositeItemBinder;
import com.borama.zesb.api.core.binder.ItemBinder;
import com.borama.zesb.api.core.listener.ClickHandler;
import com.borama.zesb.api.repositories.RealmHelper;
import com.borama.zesb.nhameykorban.BR;
import com.borama.zesb.nhameykorban.R;
import com.borama.zesb.nhameykorban.binder.BaseBinder;
import com.borama.zesb.nhameykorban.databinding.ActivityMainBinding;
import com.borama.zesb.nhameykorban.realmmodels.PlaceRealm;
import com.borama.zesb.nhameykorban.ui.fragment.SlashScreenFragment;
import com.borama.zesb.nhameykorban.utils.Res;
import com.borama.zesb.nhameykorban.viewmodels.BaseViewModel;
import com.borama.zesb.nhameykorban.viewmodels.MenuViewModel;
import com.borama.zesb.nhameykorban.viewmodels.MenusViewModel;

import io.realm.RealmList;


public class MainActivity extends BaseActivity {
    private boolean doubleBackToExitPressedOnce = false;
    private ActivityMainBinding mBinding;
    private MenusViewModel mModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(mBinding.toolbar);
        setTitle(R.string.title_menu);

        mModels = new MenusViewModel();
        mBinding.setMenus(mModels);
        mBinding.setView(this);

        SlashScreenFragment.showDialog(getSupportFragmentManager(), "LL");
        savingData();
        mBinding.getRoot().postDelayed(new Runnable() {
            @Override
            public void run() {
                SlashScreenFragment.dismissDialog();
            }
        }, 5000);
        initMenu();
    }

    private String getGenerateId() {
        return Build.ID;
    }

    private void savingData() {
        RealmList<PlaceRealm> list = new RealmList();

        PlaceRealm placeRealm = new PlaceRealm();
        placeRealm.setId(getGenerateId() + 1);
        placeRealm.setName("Khema");
        placeRealm.setPhoto(R.drawable.lo_khema);
        list.add(placeRealm);

        placeRealm = new PlaceRealm();
        placeRealm.setId(getGenerateId() + 2);
        placeRealm.setName("Coca Cola");
        placeRealm.setPhoto(R.drawable.lo_coca_cola);
        list.add(placeRealm);

        placeRealm = new PlaceRealm();
        placeRealm.setId(getGenerateId() + 3);
        placeRealm.setName("Kabbas");
        placeRealm.setPhoto(R.drawable.lo_kabbas);
        list.add(placeRealm);

        placeRealm = new PlaceRealm();
        placeRealm.setId(getGenerateId() + 4);
        placeRealm.setName("Park Coffee");
        placeRealm.setPhoto(R.drawable.lo_park_cofe);
        list.add(placeRealm);

        RealmHelper.init(this).addObject(list);
    }

    private void initMenu() {
        mModels.addItem(new MenuViewModel(R.drawable.ic_menu_nhameykorban, Res.getString(this, R.string.nham_ey_kor_ban), Res.getColor(this, R.color.menu_1), AskQuestionActivity.class));
        mModels.addItem(new MenuViewModel(R.drawable.ic_menu_group, Res.getString(this, R.string.group), Res.getColor(this, R.color.menu_2), GroupActivity.class));
        mModels.addItem(new MenuViewModel(R.drawable.ic_menu_game, Res.getString(this, R.string.game), Res.getColor(this, R.color.menu_3), GameActivity.class));
        mModels.addItem(new MenuViewModel(R.drawable.ic_menu_about, Res.getString(this, R.string.about), Res.getColor(this, R.color.menu_4), AboutActivity.class));
    }

    public ClickHandler<MenuViewModel> clickHandler() {
        return new ClickHandler<MenuViewModel>() {
            @Override
            public void onClick(MenuViewModel viewModel, View v) {
                viewModel.openActivity(MainActivity.this);
            }
        };
    }

    public ItemBinder<BaseViewModel> itemViewBinder() {
        BaseBinder binder = new BaseBinder(BR.menu, R.layout.item_menu);
        return new CompositeItemBinder<>(binder);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mModels.items.clear();
        mBinding.unbind();
        mBinding = null;
    }
}
