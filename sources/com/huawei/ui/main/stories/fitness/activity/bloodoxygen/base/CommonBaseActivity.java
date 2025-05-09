package com.huawei.ui.main.stories.fitness.activity.bloodoxygen.base;

import android.os.Bundle;
import com.huawei.ui.commonui.base.BaseActivity;

/* loaded from: classes6.dex */
public abstract class CommonBaseActivity extends BaseActivity implements CommonBaseView {
    protected abstract int getLayoutId();

    protected abstract void initData();

    public void initPresenter(CommonBaseView commonBaseView) {
    }

    protected abstract void initView();

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getLayoutId());
        initPresenter(this);
        initView();
        initData();
    }
}
