package com.borama.zesb.nhameykorban.binder;

import com.borama.zesb.api.core.binder.ConditionalDataBinder;
import com.borama.zesb.nhameykorban.viewmodels.MenuViewModel;

/**
 * Created by phearom on 3/30/16.
 */
public class MenuBinder extends ConditionalDataBinder<MenuViewModel> {
    public MenuBinder(int bindingVariable, int layoutId) {
        super(bindingVariable, layoutId);
    }

    @Override
    public boolean canHandle(MenuViewModel model) {
        return true;
    }
}
