package com.borama.zesb.nhameykorban.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import com.borama.zesb.api.core.binder.CompositeItemBinder;
import com.borama.zesb.api.core.binder.ItemBinder;
import com.borama.zesb.api.core.listener.ClickHandler;
import com.borama.zesb.nhameykorban.BR;
import com.borama.zesb.nhameykorban.R;
import com.borama.zesb.nhameykorban.application.App;
import com.borama.zesb.nhameykorban.binder.BaseAddBinder;
import com.borama.zesb.nhameykorban.binder.BaseBinder;
import com.borama.zesb.nhameykorban.databinding.ActivityGroupBinding;
import com.borama.zesb.nhameykorban.databinding.AlertBoxBinding;
import com.borama.zesb.nhameykorban.ui.fragment.InfoGameDialogFragment;
import com.borama.zesb.nhameykorban.utils.AlertUtils;
import com.borama.zesb.nhameykorban.utils.DateUtils;
import com.borama.zesb.nhameykorban.utils.K;
import com.borama.zesb.nhameykorban.utils.Res;
import com.borama.zesb.nhameykorban.viewmodels.BaseViewModel;
import com.borama.zesb.nhameykorban.viewmodels.PeopleViewModel;
import com.borama.zesb.nhameykorban.viewmodels.PersonAddViewModel;
import com.borama.zesb.nhameykorban.viewmodels.PersonViewModel;

import java.util.ArrayList;
import java.util.List;


public class GroupActivity extends BaseActivity implements PeopleViewModel.OnItemChangedCallback {

    private ActivityGroupBinding mBinding;
    private PeopleViewModel mPeopleModels;
    private boolean isWithDice = false;

    private int intColors[] = {R.color.menu_1, R.color.menu_2, R.color.menu_3, R.color.menu_4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_group);
        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPeopleModels = new PeopleViewModel(removePerson(), this);
        mPeopleModels.addItem(new PersonAddViewModel());
        mBinding.setPeople(mPeopleModels);
        mBinding.setView(this);

        mBinding.radios.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.brd_Drink_Game:
                        isWithDice = false;
                        break;
                    case R.id.brd_Normal_Game:
                        isWithDice = true;
                        break;
                }
            }
        });

        mBinding.fabMemberAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertSaveCancel();
            }
        });

        mBinding.btnPlayGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getListPerson().size() > 1) {
                    App<PersonViewModel> app = (App) getApplication();
                    app.setListData(K.Key_People, getListPerson());
                    InfoGameDialogFragment.showDialog(getSupportFragmentManager(), false, isWithDice, false);
                } else {
                    AlertUtils.info(GroupActivity.this, Res.getString(GroupActivity.this, R.string.alert_invalid_person));
                }
            }
        });
    }

    private void initView(int size) {
        if (size > 1) {
            if (TextUtils.isEmpty(mBinding.txvGroupDate.getText()))
                mBinding.txvGroupDate.setText(DateUtils.getDateString("dd-MM-yyyy"));
            mBinding.lilGroupContent.setVisibility(View.VISIBLE);
            mBinding.txvGroupMessage.setVisibility(View.GONE);
        } else {
            mBinding.lilGroupContent.setVisibility(View.GONE);
            mBinding.txvGroupMessage.setVisibility(View.VISIBLE);
        }
    }

    private List<PersonViewModel> getListPerson() {
        List<PersonViewModel> list = new ArrayList<>();
        for (PersonViewModel p : mPeopleModels.items) {
            if (!(p instanceof PersonAddViewModel)) {
                list.add(p);
            }
        }
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_group, menu);
        return true;
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

    private int getColorInt() {
        return intColors[mPeopleModels.items.size() % intColors.length];
    }

    private void alertSaveCancel() {
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        final AlertBoxBinding binding = DataBindingUtil.inflate(LayoutInflater.from(alertDialog.getContext()), R.layout.alert_box, null, false);
        alertDialog.setMessage(Res.getString(this, R.string.message_member_create));
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, Res.getString(alertDialog.getContext(), R.string.alert_save), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!TextUtils.isEmpty(binding.edtAlertValue.getText())) {
                    mPeopleModels.addItem(new PersonViewModel(R.drawable.ic_person_white, binding.edtAlertValue.getText().toString().trim(), Res.getColor(GroupActivity.this, getColorInt())));
                    alertDialog.dismiss();
                }
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, Res.getString(alertDialog.getContext(), R.string.alert_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.setView(binding.getRoot());
        alertDialog.show();
    }

    public ClickHandler<PersonViewModel> removePerson() {
        return new ClickHandler<PersonViewModel>() {
            @Override
            public void onClick(PersonViewModel viewModel, View v) {
                mPeopleModels.removeItem(viewModel);
                mBinding.recyclerPeople.invalidate();
            }
        };
    }

    public ClickHandler<BaseViewModel> clickHandler() {
        return new ClickHandler<BaseViewModel>() {
            @Override
            public void onClick(BaseViewModel viewModel, View v) {
                if (viewModel instanceof PersonAddViewModel) {
                    alertSaveCancel();
                }
            }
        };
    }

    public ItemBinder<BaseViewModel> itemViewBinder() {
        return new CompositeItemBinder<>(
                new BaseAddBinder(BR.personAdd, R.layout.item_person_add),
                new BaseBinder(BR.person, R.layout.item_person)
        );
    }

    @Override
    public void onItemChange(int size) {
        initView(size);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPeopleModels.items.clear();
        mPeopleModels = null;
        intColors =null;
        mBinding.unbind();
        mBinding = null;
    }
}
