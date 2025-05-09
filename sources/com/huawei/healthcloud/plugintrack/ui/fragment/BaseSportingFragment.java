package com.huawei.healthcloud.plugintrack.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.ui.commonui.base.BaseFragment;
import health.compact.a.LogUtil;

/* loaded from: classes4.dex */
public abstract class BaseSportingFragment extends BaseFragment implements SportLifecycle {
    private View mContentView;
    private String mLogTag = getLogTag();
    private boolean mIsVisibleToUser = false;
    private boolean mIsFirst = true;
    private boolean mIsSetVisible = false;
    private boolean mIsOnCreateView = false;

    public abstract int getLayoutId();

    public abstract String getLogTag();

    public abstract void initData();

    public abstract void initView(View view);

    public abstract void initViewModel();

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.c(this.mLogTag, "onCreateView");
        if (this.mContentView == null) {
            LogUtil.c(this.mLogTag, "onCreateView inflate mContentView");
            AppBundle.b().loadResources(layoutInflater.getContext());
            this.mContentView = layoutInflater.inflate(getLayoutId(), viewGroup, false);
        }
        if (!this.mIsVisibleToUser) {
            this.mIsSetVisible = false;
        }
        if ((this.mIsSetVisible && this.mIsFirst) || this.mIsOnCreateView) {
            initOnCreate();
        }
        this.mIsOnCreateView = true;
        return this.mContentView;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.mIsVisibleToUser = z;
        if (this.mContentView == null) {
            LogUtil.a(this.mLogTag, "setUserVisibleHint mContentView is null");
            if (this.mIsVisibleToUser) {
                this.mIsSetVisible = true;
                return;
            }
            return;
        }
        if (z && this.mIsFirst) {
            initOnCreate();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        LogUtil.c(this.mLogTag, "onDestroyView");
        super.onDestroyView();
        this.mContentView = null;
    }

    private void initOnCreate() {
        this.mIsFirst = false;
        initView(this.mContentView);
        initViewModel();
        initData();
    }
}
