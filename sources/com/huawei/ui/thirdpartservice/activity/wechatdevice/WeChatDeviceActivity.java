package com.huawei.ui.thirdpartservice.activity.wechatdevice;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.thirdpartservice.activity.wechatdevice.WeChatDeviceActivity;
import com.huawei.ui.thirdpartservice.interactors.callback.SupportDeviceResultCallback;
import com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.wechatdevice.DeviceAdapter;
import defpackage.koq;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.sid;
import defpackage.sii;
import defpackage.sjy;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class WeChatDeviceActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private HealthButton f10557a;
    private DeviceAdapter d;
    private HealthTextView e;
    private HealthTextView f;
    private DeviceInfo g;
    private HealthProgressBar i;
    private HealthRecycleView m;
    private final Handler j = new a();
    private List<String> l = new ArrayList();
    private List<sjy> c = new ArrayList();
    private List<DeviceInfo> h = new ArrayList();
    private List<DeviceInfo> b = new ArrayList();

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_wechat_device);
        g();
    }

    private void g() {
        this.f = (HealthTextView) findViewById(R.id.device_bound_tips);
        this.e = (HealthTextView) findViewById(R.id.device_authorized_title);
        this.m = (HealthRecycleView) findViewById(R.id.device_recycleview);
        this.i = (HealthProgressBar) findViewById(R.id.loading);
        this.f10557a = (HealthButton) findViewById(R.id.btn_device_bound);
        b(false);
        this.f10557a.setOnClickListener(new View.OnClickListener() { // from class: sgo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WeChatDeviceActivity.this.dXy_(view);
            }
        });
    }

    public /* synthetic */ void dXy_(View view) {
        if (this.c.size() >= 5) {
            b(false);
            nrh.d(this, getString(R.string.IDS_wx_device_max_limit, new Object[]{5}));
            ViewClickInstrumentation.clickOnView(view);
        } else {
            d();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.i.setVisibility(0);
        b();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        nsn.a();
    }

    private void b() {
        ReleaseLogUtil.e("R_WeChatDeviceActivity", "checkDevicesStatus");
        sid.a((ICloudOperationResult<List<DeviceInfo>>) new ICloudOperationResult() { // from class: sgm
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                WeChatDeviceActivity.this.c((List) obj, str, z);
            }
        });
    }

    public /* synthetic */ void c(List list, String str, final boolean z) {
        ReleaseLogUtil.e("R_WeChatDeviceActivity", "checkDevicesStatus getAllLocalDevices:", Boolean.valueOf(z));
        this.h = list;
        if (list.isEmpty()) {
            Message obtainMessage = this.j.obtainMessage();
            obtainMessage.what = 0;
            this.j.sendMessage(obtainMessage);
        }
        sid.b(new ICloudOperationResult() { // from class: sgh
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str2, boolean z2) {
                WeChatDeviceActivity.this.d(z, (List) obj, str2, z2);
            }
        });
    }

    public /* synthetic */ void d(boolean z, List list, String str, boolean z2) {
        ReleaseLogUtil.e("R_WeChatDeviceActivity", "checkDevicesStatus getSupportedProductIdsAsync status:", Boolean.valueOf(z2));
        if (!z2) {
            Message obtainMessage = this.j.obtainMessage();
            obtainMessage.what = -1;
            this.j.sendMessage(obtainMessage);
            return;
        }
        this.l = list;
        if (koq.b(list)) {
            e();
            LogUtil.b("WeChatDeviceActivity", "SupportedProductIds is empty");
        } else {
            if (!z) {
                Message obtainMessage2 = this.j.obtainMessage();
                obtainMessage2.what = -1;
                this.j.sendMessage(obtainMessage2);
                return;
            }
            c();
        }
    }

    private void c() {
        sid.d(new SupportDeviceResultCallback() { // from class: com.huawei.ui.thirdpartservice.activity.wechatdevice.WeChatDeviceActivity.1
            @Override // com.huawei.ui.thirdpartservice.interactors.callback.SupportDeviceResultCallback
            public void obtainSupportDeviceList(List<sjy> list) {
                ReleaseLogUtil.e("R_WeChatDeviceActivity", "handleSupportedDevices getBindDevicesAsync size:", Integer.valueOf(WeChatDeviceActivity.this.h.size()));
                for (DeviceInfo deviceInfo : WeChatDeviceActivity.this.h) {
                    if (TextUtils.isEmpty(deviceInfo.getHiLinkDeviceId())) {
                        LogUtil.a("WeChatDeviceActivity", "HiLinkDevice :", deviceInfo.getDeviceName());
                        sjy e = sii.e(deviceInfo, WeChatDeviceActivity.this.c);
                        if (e != null && !TextUtils.isEmpty(e.getWiseDeviceProdId())) {
                            LogUtil.a("WeChatDeviceActivity", deviceInfo.getDeviceName(), " setHiLinkDeviceId:", e.getWiseDeviceProdId());
                            deviceInfo.setHiLinkDeviceId(e.getWiseDeviceProdId());
                        }
                    }
                }
                Iterator<DeviceInfo> it = sid.b().iterator();
                while (it.hasNext()) {
                    if (sii.e(it.next(), list) == null) {
                        nrh.b(WeChatDeviceActivity.this, R.string.IDS_wx_device_not_supported);
                        return;
                    }
                }
            }

            @Override // com.huawei.ui.thirdpartservice.interactors.callback.SupportDeviceResultCallback
            public void obtainBindDeviceList(List<sjy> list) {
                WeChatDeviceActivity weChatDeviceActivity = WeChatDeviceActivity.this;
                weChatDeviceActivity.c = weChatDeviceActivity.b(list);
                WeChatDeviceActivity weChatDeviceActivity2 = WeChatDeviceActivity.this;
                weChatDeviceActivity2.b = weChatDeviceActivity2.a();
                WeChatDeviceActivity weChatDeviceActivity3 = WeChatDeviceActivity.this;
                weChatDeviceActivity3.g = weChatDeviceActivity3.b.size() == 1 ? (DeviceInfo) WeChatDeviceActivity.this.b.get(0) : null;
                Message obtainMessage = WeChatDeviceActivity.this.j.obtainMessage();
                obtainMessage.what = 1;
                WeChatDeviceActivity.this.j.sendMessage(obtainMessage);
            }

            @Override // com.huawei.ui.thirdpartservice.interactors.callback.SupportDeviceResultCallback
            public void onError(int i, String str) {
                Message obtainMessage = WeChatDeviceActivity.this.j.obtainMessage();
                obtainMessage.what = -1;
                obtainMessage.obj = str;
                WeChatDeviceActivity.this.j.sendMessage(obtainMessage);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<sjy> b(List<sjy> list) {
        for (sjy sjyVar : list) {
            boolean a2 = sii.a(sjyVar);
            sjyVar.setLocalBound(a2);
            LogUtil.a("WeChatDeviceActivity", "Update authorized device = ", sjyVar.getDeviceName(), " , is local bound = ", Boolean.valueOf(a2));
        }
        return list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<DeviceInfo> a() {
        ArrayList arrayList = new ArrayList(this.h);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            DeviceInfo deviceInfo = (DeviceInfo) it.next();
            if (TextUtils.isEmpty(deviceInfo.getHiLinkDeviceId())) {
                LogUtil.a("WeChatDeviceActivity", "deviceInfo empty device :", deviceInfo.getDeviceName());
                sjy e = sii.e(deviceInfo, this.c);
                if (e != null && !TextUtils.isEmpty(e.getWiseDeviceProdId())) {
                    LogUtil.a("WeChatDeviceActivity", deviceInfo.getDeviceName(), " setHiLinkDeviceId:", e.getWiseDeviceProdId());
                    deviceInfo.setHiLinkDeviceId(e.getWiseDeviceProdId());
                }
            }
            if (!this.l.contains(deviceInfo.getHiLinkDeviceId()) || sii.e(deviceInfo, this.c) != null) {
                it.remove();
                LogUtil.a("WeChatDeviceActivity", "Authorizeable devices remove not supported or authorized device :", deviceInfo.getDeviceName(), " deviceInfo.getHiLinkDeviceId():", deviceInfo.getHiLinkDeviceId());
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dXx_(Message message) {
        int i = message.what;
        if (i == -1) {
            e();
            if ("server error".equals(String.valueOf(message.obj))) {
                nrh.b(this, R.string._2130842544_res_0x7f0213b0);
                return;
            } else {
                nrh.b(this, R.string._2130840733_res_0x7f020c9d);
                return;
            }
        }
        if (i == 0) {
            e();
            nrh.b(this, R.string.IDS_wx_device_no_bound);
        } else {
            if (i != 1) {
                return;
            }
            f();
        }
    }

    private void e() {
        this.f.setText(R.string.IDS_wx_device_bound_tips_empty);
        this.e.setVisibility(8);
        b(false);
        this.i.setVisibility(8);
    }

    private void f() {
        a(this.c);
        this.i.setVisibility(8);
        boolean b = koq.b(this.c);
        this.f.setText(b ? R.string.IDS_wx_device_bound_tips_empty : R.string.IDS_wx_device_bound_tips);
        if (b && koq.b(this.b) && koq.c(this.h)) {
            nrh.b(this, R.string.IDS_wx_device_none_supported);
        }
        b(koq.c(this.b));
    }

    private void b(boolean z) {
        this.f10557a.setEnabled(z);
        this.f10557a.setTextColor(getColor(z ? R.color._2131296927_res_0x7f09029f : R.color._2131296928_res_0x7f0902a0));
    }

    private void a(List<sjy> list) {
        this.e.setVisibility(koq.b(list) ? 8 : 0);
        this.m.setVisibility(koq.b(list) ? 8 : 0);
        if (koq.b(list)) {
            return;
        }
        DeviceAdapter deviceAdapter = new DeviceAdapter(this, this.m);
        this.d = deviceAdapter;
        deviceAdapter.d(0);
        this.d.e(new DeviceAdapter.CallBack() { // from class: sgl
            @Override // com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.wechatdevice.DeviceAdapter.CallBack
            public final void onItemClicked(int i) {
                WeChatDeviceActivity.this.a(i);
            }
        });
        this.d.d(list);
        this.m.setAdapter(this.d);
    }

    private void d() {
        if (nsn.o()) {
            return;
        }
        Intent intent = new Intent();
        if (this.g != null) {
            intent.setClass(this, WeChatDeviceAuthorizeActivity.class);
            intent.putExtra("name", this.g.getDeviceName());
            intent.putExtra("identify", this.g.getDeviceIdentify());
            intent.putExtra("isAuthorized", false);
        } else {
            intent.setClass(this, WeChatDeviceListActivity.class);
        }
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        if (nsn.o() || this.d == null || koq.b(this.c) || i < 0 || i >= this.c.size()) {
            return;
        }
        sjy sjyVar = this.c.get(i);
        Intent intent = new Intent(this, (Class<?>) WeChatDeviceAuthorizeActivity.class);
        intent.putExtra("name", sjyVar.getDeviceName());
        intent.putExtra("deviceCode", sjyVar.getDeviceCode());
        intent.putExtra("identify", sjyVar.getIdentify());
        intent.putExtra("wxLinkSdkId", sjyVar.getWxLinkSdkId());
        intent.putExtra("isLocalBound", sjyVar.isLocalBound());
        intent.putExtra("deviceIconUrl", sjyVar.getDeviceIconUrl());
        intent.putExtra("isAuthorized", true);
        startActivity(intent);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        nrh.e();
    }

    /* loaded from: classes8.dex */
    static class a extends BaseHandler<WeChatDeviceActivity> {
        private a(WeChatDeviceActivity weChatDeviceActivity) {
            super(weChatDeviceActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dXz_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(WeChatDeviceActivity weChatDeviceActivity, Message message) {
            weChatDeviceActivity.dXx_(message);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
