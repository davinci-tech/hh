package com.huawei.ui.main.stories.fitness.activity.bloodoxygen.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.ui.commonui.base.BaseFragment;

/* loaded from: classes9.dex */
public abstract class CommonBaseFragment extends BaseFragment implements CommonBaseView {
    private View mView = null;

    protected abstract int getLayoutId();

    protected abstract void initData();

    public void initPresenter() {
    }

    protected abstract void initView(View view);

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mView == null) {
            this.mView = layoutInflater.inflate(getLayoutId(), viewGroup, false);
            initPresenter();
            initView(this.mView);
            initData();
        }
        return this.mView;
    }
}
