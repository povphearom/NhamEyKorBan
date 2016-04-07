package com.borama.zesb.nhameykorban.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.borama.zesb.nhameykorban.R;
import com.borama.zesb.nhameykorban.databinding.ActivityGameBinding;
import com.borama.zesb.nhameykorban.ui.fragment.InfoGameDialogFragment;

public class GameActivity extends BaseActivity {

    private ActivityGameBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_game);

        setSupportActionBar(mBinding.toolbarGame);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mBinding.cardDrinkGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoGameDialogFragment.showDialog(getSupportFragmentManager(), true, false, false);
            }
        });

        mBinding.cardNormalGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoGameDialogFragment.showDialog(getSupportFragmentManager(), true, true, false);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding.unbind();
        mBinding = null;
    }
}
