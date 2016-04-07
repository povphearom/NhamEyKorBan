package com.borama.zesb.nhameykorban.application;

import android.app.Application;

import com.borama.zesb.nhameykorban.viewmodels.BaseViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by phearom on 4/5/16.
 */
public class App<T> extends Application {
    private BaseViewModel data;
    private List<T> listData;
    private Map<String, List<T>> listDataWithKey;

    @Override
    public void onCreate() {
        super.onCreate();
        listDataWithKey = new HashMap<>();
    }

    public void setData(BaseViewModel data) {
        this.data = data;
    }

    public BaseViewModel getData() {
        return data;
    }

    public List<T> getListData() {
        return listData;
    }

    public void setListData(List<T> listData) {
        this.listData = listData;
    }

    public List<T> getListData(String key) {
        return listDataWithKey.get(key);
    }

    public void setListData(String key, List<T> list) {
        this.listDataWithKey.put(key, list);
    }
}
