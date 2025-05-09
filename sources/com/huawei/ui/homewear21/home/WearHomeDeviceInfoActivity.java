package com.huawei.ui.homewear21.home;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.homewear21.home.adapter.WearHomeDeviceInfoAdapter;
import defpackage.cun;
import defpackage.jez;
import defpackage.jfq;
import defpackage.nsy;
import defpackage.pbn;
import defpackage.pep;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class WearHomeDeviceInfoActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private WearHomeDeviceInfoAdapter f9648a;
    private Context b;
    private String d = "";
    private List<pbn> c = new ArrayList(16);

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("WearHomeDeviceInfoActivity", "onCreate");
        setContentView(R.layout.wear_home_device_info_layout);
        this.b = this;
        a();
        d();
        e();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    private void a() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        String stringExtra = intent.getStringExtra("device_id");
        this.d = stringExtra;
        if (stringExtra == null) {
            this.d = "";
        }
    }

    private void e() {
        c();
    }

    private void d() {
        if (jez.e() != null) {
            b();
        } else {
            jfq.c().d().observe(this, new Observer<Boolean>() { // from class: com.huawei.ui.homewear21.home.WearHomeDeviceInfoActivity.4
                @Override // androidx.lifecycle.Observer
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onChanged(Boolean bool) {
                    if (bool.booleanValue()) {
                        LogUtil.a("WearHomeDeviceInfoActivity", "checkPhoneServiceBind initDeviceList");
                        jfq.c().d().removeObserver(this);
                        WearHomeDeviceInfoActivity.this.b();
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "WearHomeDeviceInfoActivity");
        if (deviceList == null || deviceList.size() == 0) {
            LogUtil.a("WearHomeDeviceInfoActivity", "getDeviceList is null or empty");
            return;
        }
        LogUtil.a("WearHomeDeviceInfoActivity", "getDeviceList:", Integer.valueOf(deviceList.size()));
        Iterator<DeviceInfo> it = deviceList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DeviceInfo next = it.next();
            if (this.d.equals(next.getDeviceIdentify())) {
                c(next);
                break;
            }
        }
        WearHomeDeviceInfoAdapter wearHomeDeviceInfoAdapter = this.f9648a;
        if (wearHomeDeviceInfoAdapter != null) {
            wearHomeDeviceInfoAdapter.notifyDataSetChanged();
        }
    }

    private void c(DeviceInfo deviceInfo) {
        LogUtil.a("WearHomeDeviceInfoActivity", "find target device:", deviceInfo.toString());
        this.c.add(new pbn(BaseApplication.getContext().getString(R.string.IDS_wear_home_device_info_name), deviceInfo.getDeviceName()));
        if (deviceInfo.getProductType() >= 34) {
            String string = BaseApplication.getContext().getString(R.string.IDS_wear_home_device_info_type);
            LogUtil.a("WearHomeDeviceInfoActivity", "CertModel = ", deviceInfo.getCertModel(), " DeviceModel = ", deviceInfo.getDeviceModel());
            String deviceModel = TextUtils.isEmpty(deviceInfo.getCertModel()) ? deviceInfo.getDeviceModel() : deviceInfo.getCertModel();
            if (deviceInfo.getProductType() == 75) {
                deviceModel = deviceInfo.getDeviceModel();
            }
            this.c.add(new pbn(string, deviceModel));
            String d = pep.d(deviceInfo);
            if (d != null && !TextUtils.isEmpty(d.trim())) {
                this.c.add(new pbn(BaseApplication.getContext().getString(R.string.IDS_wear_home_device_info_sn), d));
            }
        }
        if (deviceInfo.getProductType() != 10) {
            this.c.add(new pbn(BaseApplication.getContext().getString(R.string.IDS_wear_home_device_info_mac), deviceInfo.getDeviceIdentify()));
        }
    }

    private void c() {
        LogUtil.a("WearHomeDeviceInfoActivity", "setRecyclerView. ");
        ((CustomTitleBar) nsy.cMc_(this, R.id.wear_home_device_info_bar)).setTitleText(BaseApplication.getContext().getString(R.string.IDS_wear_home_device_info));
        HealthRecycleView healthRecycleView = (HealthRecycleView) nsy.cMc_(this, R.id.wear_home_device_info_recyclerView);
        healthRecycleView.setLayoutManager(new LinearLayoutManager(this, 1, false));
        WearHomeDeviceInfoAdapter wearHomeDeviceInfoAdapter = new WearHomeDeviceInfoAdapter(this.b, this.c);
        this.f9648a = wearHomeDeviceInfoAdapter;
        healthRecycleView.setAdapter(wearHomeDeviceInfoAdapter);
        healthRecycleView.a(false);
        healthRecycleView.d(false);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
