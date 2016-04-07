package com.borama.zesb.nhameykorban.ui;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.borama.zesb.nhameykorban.R;
import com.borama.zesb.nhameykorban.application.App;
import com.borama.zesb.nhameykorban.databinding.ActivityChoiceBinding;
import com.borama.zesb.nhameykorban.ui.fragment.ShopPlaceFragment;
import com.borama.zesb.nhameykorban.utils.AlertUtils;
import com.borama.zesb.nhameykorban.utils.Res;

public class ChoiceActivity extends BaseActivity {

    private ActivityChoiceBinding mBinding;
    private ShopPlaceFragment fragment;
    private App mApp;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, ChoiceActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApp = (App) getApplication();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_choice);
        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fragment = (ShopPlaceFragment) getSupportFragmentManager().findFragmentById(R.id.shopPlace);
        mBinding.fabPlaceAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChoiceActivity.this, ManagePlaceActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_choice, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (id == R.id.action_done) {
            if (null != fragment.getAllCheckPlaces())
                if (fragment.getAllCheckPlaces().size() > 1) {
                    mApp.setListData(fragment.getAllCheckPlaces());
                    RandomPlaceActivity.launch(this);
                } else {
                    AlertUtils.info(ChoiceActivity.this, Res.getString(this, R.string.alert_invalid_check));
                }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mApp = null;
        fragment = null;
        mBinding.unbind();
        mBinding = null;
    }
}
