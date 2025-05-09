package com.huawei.ui.main.stories.template.health;

import android.os.Bundle;
import com.huawei.ui.main.stories.template.BaseActivity;
import com.huawei.ui.main.stories.template.BasePresenter;
import com.huawei.ui.main.stories.template.BaseView;
import defpackage.nsn;
import defpackage.rze;

/* loaded from: classes2.dex */
public abstract class HealthMvpActivity<P extends BasePresenter> extends BaseActivity {
    public P mPresenter;

    protected abstract int getLayoutId();

    protected abstract void initData();

    protected abstract void initViews();

    protected abstract P onCreatePresenter();

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        nsn.cLf_(this, bundle);
        super.onCreate(bundle);
        create();
    }

    protected void create() {
        setContentView(getLayoutId());
        initViews();
        initData();
    }

    public P getPresenter() {
        return this.mPresenter;
    }

    public void createPresenter(String str, BaseView baseView) {
        if (this.mPresenter == null) {
            this.mPresenter = (P) rze.d(str, baseView);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        P p = this.mPresenter;
        if (p != null) {
            p.detachView();
            this.mPresenter = null;
        }
    }
}
