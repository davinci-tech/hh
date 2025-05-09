package com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseFragment;

/* loaded from: classes6.dex */
public abstract class PressureQuestionFragment extends BaseFragment {
    private boolean mIsInit = false;
    private boolean mIsLoad = false;
    private View mView;

    protected abstract void lazyLoad();

    protected abstract int setContentView();

    protected void stopLoad() {
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(setContentView(), viewGroup, false);
        this.mView = inflate;
        BaseActivity.setViewSafeRegion(false, inflate.findViewById(R.id.hw_pressure_calibrate_pager_item));
        this.mIsInit = true;
        canLoadData();
        return this.mView;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        canLoadData();
    }

    private void canLoadData() {
        if (this.mIsInit) {
            if (getUserVisibleHint()) {
                lazyLoad();
                this.mIsLoad = true;
            } else if (this.mIsLoad) {
                stopLoad();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mIsInit = false;
        this.mIsLoad = false;
    }

    protected View getContentView() {
        return this.mView;
    }
}
