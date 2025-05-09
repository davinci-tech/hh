package com.huawei.ui.device.activity.device;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseFragment;

/* loaded from: classes9.dex */
public class DeviceFragment extends BaseFragment {
    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        LogUtil.a("DeviceFragment", "Enter onCreate()");
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        LogUtil.a("DeviceFragment", "Enter onActivityCreated()");
        super.onActivityCreated(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a("DeviceFragment", "Enter onCreateView()");
        return layoutInflater.inflate(R.layout.fragment_main_device, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        LogUtil.a("DeviceFragment", "Enter onStart()");
        super.onStart();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        LogUtil.a("DeviceFragment", " Enter onActivityResult():");
        super.onActivityResult(i, i2, intent);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        LogUtil.a("DeviceFragment", " enter onPause():");
        super.onPause();
    }
}
