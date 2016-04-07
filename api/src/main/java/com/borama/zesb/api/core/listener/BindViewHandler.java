package com.borama.zesb.api.core.listener;

public interface BindViewHandler<T> {
    void onBindView(T viewModel, int pos);
}