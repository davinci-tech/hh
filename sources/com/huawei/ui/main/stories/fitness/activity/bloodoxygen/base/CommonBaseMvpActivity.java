package com.huawei.ui.main.stories.fitness.activity.bloodoxygen.base;

import defpackage.pjp;

/* loaded from: classes6.dex */
public abstract class CommonBaseMvpActivity<V, P extends pjp<V>> extends CommonBaseActivity {
    public P mPresenter;

    protected abstract P createPresenter();

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.base.CommonBaseActivity
    public void initPresenter(CommonBaseView commonBaseView) {
        super.initPresenter(commonBaseView);
        P createPresenter = createPresenter();
        this.mPresenter = createPresenter;
        createPresenter.b(this);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }
}
