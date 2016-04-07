package com.borama.zesb.nhameykorban.binder;

import com.borama.zesb.api.core.binder.ConditionalDataBinder;
import com.borama.zesb.nhameykorban.viewmodels.BaseViewModel;

/**
 * Created by phearom on 3/30/16.
 */
public class BaseBinder extends ConditionalDataBinder<BaseViewModel> {
    public BaseBinder(int bindingVariable, int layoutId) {
        super(bindingVariable, layoutId);
    }

    @Override
    public boolean canHandle(BaseViewModel model) {
        return true;
    }
}
