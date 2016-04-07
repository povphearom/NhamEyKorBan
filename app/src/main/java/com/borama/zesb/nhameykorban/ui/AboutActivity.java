package com.borama.zesb.nhameykorban.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.borama.zesb.nhameykorban.R;
import com.borama.zesb.nhameykorban.databinding.ActivityAboutBinding;
import com.borama.zesb.nhameykorban.utils.RedirectUriUtils;

public class AboutActivity extends BaseActivity {
    private ActivityAboutBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_about);

        mBinding.btnAboutDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RedirectUriUtils.goPlayStore(AboutActivity.this);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding.unbind();
        mBinding = null;
    }
}
