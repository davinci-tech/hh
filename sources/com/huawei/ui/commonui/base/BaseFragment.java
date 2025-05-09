package com.huawei.ui.commonui.base;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.huawei.hianalytics.visual.autocollect.instrument.FragmentInstrumentation;
import defpackage.ixx;
import defpackage.nsn;
import java.util.LinkedHashMap;

/* loaded from: classes6.dex */
public class BaseFragment extends Fragment {
    private static final int DEFAULT_SIZE = 4;
    private static ixx sBiAnalyticsUtil = ixx.d();
    private boolean mIsResume = false;

    public void initViewTahiti() {
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (nsn.l()) {
            initViewTahiti();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        if (getUserVisibleHint() && this.mIsResume) {
            this.mIsResume = false;
            sBiAnalyticsUtil.a(getClass().getSimpleName(), new LinkedHashMap<>(4));
        }
        FragmentInstrumentation.onPauseByFragment(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint() && !this.mIsResume) {
            this.mIsResume = true;
            sBiAnalyticsUtil.e(getClass().getSimpleName(), new LinkedHashMap<>(4));
        }
        FragmentInstrumentation.onResumeByFragment(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z) {
            if (!this.mIsResume) {
                this.mIsResume = true;
                sBiAnalyticsUtil.e(getClass().getSimpleName(), new LinkedHashMap<>(4));
            }
        } else if (this.mIsResume) {
            this.mIsResume = false;
            sBiAnalyticsUtil.a(getClass().getSimpleName(), new LinkedHashMap<>(4));
        }
        FragmentInstrumentation.setUserVisibleHintByFragment(this, z);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        FragmentInstrumentation.onViewCreatedByFragment(this, view, bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        FragmentInstrumentation.onHiddenChangedByFragment(this, z);
    }
}
