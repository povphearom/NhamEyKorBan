package com.borama.zesb.nhameykorban.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.borama.zesb.nhameykorban.R;
import com.borama.zesb.nhameykorban.databinding.ActivityManagePlaceBinding;
import com.borama.zesb.nhameykorban.ui.fragment.ChoicePlaceFragment;
import com.borama.zesb.nhameykorban.ui.fragment.CreatedPlaceFragment;
import com.borama.zesb.nhameykorban.ui.pageradapter.PlacePagerAdapter;
import com.borama.zesb.nhameykorban.utils.Res;

public class ManagePlaceActivity extends BaseActivity {
    private PlacePagerAdapter placePagerAdapter;
    private ActivityManagePlaceBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_manage_place);
        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        placePagerAdapter = new PlacePagerAdapter(getSupportFragmentManager());
        placePagerAdapter.addFragment(ChoicePlaceFragment.init(), Res.getString(this, R.string.offline));
//        placePagerAdapter.addFragment(new ChoicePlaceFragment(), Res.getString(this, R.string.online));
        placePagerAdapter.addFragment(CreatedPlaceFragment.init(), Res.getString(this, R.string.place));
        mBinding.viewPager.setAdapter(placePagerAdapter);
        mBinding.tab.setupWithViewPager(mBinding.viewPager);
        mBinding.viewPager.setOffscreenPageLimit(placePagerAdapter.getCount());
        mBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 1) {
                    CreatedPlaceFragment.init().initData();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_place, menu);
        return true;
    }

    public void setPageSelected(int index) {
        mBinding.viewPager.setCurrentItem(index);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (id == R.id.action_search) {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        placePagerAdapter = null;
        mBinding.unbind();
        mBinding = null;
    }
}
