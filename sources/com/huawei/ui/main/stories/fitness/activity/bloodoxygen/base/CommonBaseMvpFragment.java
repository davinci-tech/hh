package com.huawei.ui.main.stories.fitness.activity.bloodoxygen.base;

import android.util.Log;
import defpackage.pjp;

/* loaded from: classes9.dex */
public abstract class CommonBaseMvpFragment<V, P extends pjp<V>> extends CommonBaseFragment {
    private static final String TAG = "BaseMvpFragment";
    protected P mPresenter;

    protected abstract P createPresenter();

    public CommonBaseMvpFragment() {
        Log.d(TAG, "constructor");
        P createPresenter = createPresenter();
        this.mPresenter = createPresenter;
        createPresenter.b(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mPresenter.b();
    }
}
