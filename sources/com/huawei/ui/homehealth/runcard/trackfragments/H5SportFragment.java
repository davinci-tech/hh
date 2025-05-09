package com.huawei.ui.homehealth.runcard.trackfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.core.H5ProWebView;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseFragment;

/* loaded from: classes6.dex */
public class H5SportFragment extends BaseFragment {
    private H5ProWebView c;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (layoutInflater == null) {
            LogUtil.a("Track_Provider_Track_H5SportFragment", "H5SportFragment onCreateView inflater is null");
            return onCreateView;
        }
        View inflate = layoutInflater.inflate(R.layout.sport_h5_layout, viewGroup, false);
        this.c = (H5ProWebView) inflate.findViewById(R.id.sport_h5_web_view);
        c();
        return inflate;
    }

    private void c() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            LogUtil.h("Track_Provider_Track_H5SportFragment", "bundle is null");
            return;
        }
        String string = arguments.getString("url");
        H5ProWebView h5ProWebView = this.c;
        if (h5ProWebView != null) {
            H5ProClient.startH5LightApp(string, h5ProWebView, (H5ProLaunchOption) null);
        }
    }
}
