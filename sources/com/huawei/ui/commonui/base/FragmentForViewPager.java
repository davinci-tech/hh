package com.huawei.ui.commonui.base;

import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes6.dex */
public abstract class FragmentForViewPager extends BaseFragment {
    private String mFragmentTag = "FragmentForViewPager";
    private boolean mIsVisibleToUser = false;
    private boolean mLoadDone = false;
    private boolean mViewPagerOnResumeDone = false;

    public abstract void loadAfterViewPagerOnResume();

    public abstract void normalOnResume();

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.mIsVisibleToUser = z;
        if (this.mViewPagerOnResumeDone && !this.mLoadDone && z) {
            loadAfterViewPagerOnResume();
            this.mLoadDone = true;
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (!this.mViewPagerOnResumeDone) {
            LogUtil.a(this.mFragmentTag, "ViewPager onResume is called");
            if (this.mIsVisibleToUser) {
                LogUtil.a(this.mFragmentTag, "current fragment is visible, call loadAfterViewPagerOnResume()");
                loadAfterViewPagerOnResume();
                this.mLoadDone = true;
            }
            this.mViewPagerOnResumeDone = true;
            return;
        }
        normalOnResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a(this.mFragmentTag, "onDestroy was called, reset variable");
        this.mLoadDone = false;
        this.mViewPagerOnResumeDone = false;
    }

    public void setTag(String str) {
        this.mFragmentTag = str;
    }

    public String getFragmentTag() {
        return this.mFragmentTag;
    }

    public void tryToLoad(boolean z) {
        boolean z2 = this.mLoadDone;
        if (z2 || !this.mViewPagerOnResumeDone) {
            LogUtil.b(this.mFragmentTag, "tryToLoad Fail:", Boolean.valueOf(z2), " ", Boolean.valueOf(this.mViewPagerOnResumeDone));
        } else if (z || this.mIsVisibleToUser) {
            loadAfterViewPagerOnResume();
            this.mLoadDone = true;
        }
    }
}
