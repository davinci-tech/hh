package com.huawei.ui.main.stories.template.health;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.main.stories.template.BasePresenter;

/* loaded from: classes7.dex */
public abstract class HealthMvpFragment<P extends BasePresenter> extends BaseFragment {
    private View mContentView;
    private P mPresenter;

    protected abstract View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    protected abstract void initData();

    protected abstract void initViews(View view);

    public abstract P onCreatePresenter();

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mContentView == null) {
            View contentView = getContentView(layoutInflater, viewGroup, bundle);
            this.mContentView = contentView;
            initViews(contentView);
            initData();
        }
        return this.mContentView;
    }

    public P getPresenter() {
        if (this.mPresenter == null) {
            this.mPresenter = onCreatePresenter();
        }
        return this.mPresenter;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        P p = this.mPresenter;
        if (p != null) {
            p.detachView();
            this.mPresenter = null;
        }
    }
}
