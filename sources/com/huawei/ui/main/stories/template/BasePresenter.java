package com.huawei.ui.main.stories.template;

import com.huawei.ui.main.stories.template.BaseView;

/* loaded from: classes7.dex */
public interface BasePresenter<V extends BaseView> {
    void attachView(V v);

    void detachView();

    boolean isViewAttached();
}
