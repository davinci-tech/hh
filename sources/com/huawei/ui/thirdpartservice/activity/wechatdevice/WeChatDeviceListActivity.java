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
import com.huawei.ui.thirdpartservice.activity.wechatdevice.WeChatDeviceListActivity;
import com.huawei.ui.thirdpartservice.interactors.callback.SupportDeviceResultCallback;
import com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.wechatdevice.DeviceAdapter;
import defpackage.koq;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.sid;
import defpackage.sii;
import defpackage.sjy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public class WeChatDeviceListActivity extends BaseActivity {
    private DeviceAdapter b;
    private int c;
    private HealthTextView e;
    private HealthRecycleView f;
    private HealthProgressBar g;
    private HealthButton i;
    private final Handler h = new e(this, null);

    /* renamed from: a, reason: collision with root package name */
    private List<sjy> f10559a = new ArrayList();
    private List<String> j = new ArrayList();
    private List<DeviceInfo> d = new ArrayList();

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_wechat_device_list);
        e();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        sid.b(new ICloudOperationResult() { // from class: sgx
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                WeChatDeviceListActivity.this.b((List) obj, str, z);
            }
        });
    }

    public /* synthetic */ void b(List list, String str, boolean z) {
        LogUtil.a("WeChatDeviceListActivity", "getSupportedProductIdsAsync ids = ", list);
        if (!z || list.isEmpty()) {
            LogUtil.h("WeChatDeviceListActivity", "Hide Wechat Device entrance because supported ids is empty.");
            this.h.sendMessage(this.h.obtainMessage(-1));
        } else {
            this.j = list;
            d();
        }
    }

    /* renamed from: com.huawei.ui.thirdpartservice.activity.wechatdevice.WeChatDeviceListActivity$4, reason: invalid class name */
    public class AnonymousClass4 implements SupportDeviceResultCallback {
        AnonymousClass4() {
        }

        @Override // com.huawei.ui.thirdpartservice.interactors.callback.SupportDeviceResultCallback
        public void obtainBindDeviceList(List<sjy> list) {
            WeChatDeviceListActivity.this.c = koq.b(list) ? 0 : list.size();
            WeChatDeviceListActivity.this.c(list, new ICloudOperationResult() { // from class: shb
                @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                public final void operationResult(Object obj, String str, boolean z) {
                    WeChatDeviceListActivity.AnonymousClass4.this.c((List) obj, str, z);
                }
            });
        }

        public /* synthetic */ void c(List list, String str, boolean z) {
            WeChatDeviceListActivity.this.d = list;
            LogUtil.c("WeChatDeviceListActivity", "getBindDevicesAsync authorized size = ", Integer.valueOf(WeChatDeviceListActivity.this.c), " , authorizeable size = ", Integer.valueOf(WeChatDeviceListActivity.this.d.size()));
            Message obtainMessage = WeChatDeviceListActivity.this.h.obtainMessage();
            obtainMessage.what = z ? 0 : -1;
            WeChatDeviceListActivity.this.h.sendMessage(obtainMessage);
        }

        @Override // com.huawei.ui.thirdpartservice.interactors.callback.SupportDeviceResultCallback
        public void onError(int i, String str) {
            Message obtainMessage = WeChatDeviceListActivity.this.h.obtainMessage();
            obtainMessage.what = -1;
            WeChatDeviceListActivity.this.h.sendMessage(obtainMessage);
        }
    }

    private void d() {
        sid.d(new AnonymousClass4());
    }

    private void e() {
        HealthProgressBar healthProgressBar = (HealthProgressBar) findViewById(R.id.loading);
        this.g = healthProgressBar;
        healthProgressBar.setVisibility(0);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.tv_empty);
        this.e = healthTextView;
        healthTextView.setVisibility(8);
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.device_recycleview);
        this.f = healthRecycleView;
        healthRecycleView.setVisibility(8);
        this.i = (HealthButton) findViewById(R.id.btn_next);
        DeviceAdapter deviceAdapter = new DeviceAdapter(this, this.f);
        this.b = deviceAdapter;
        deviceAdapter.d(1);
        this.f.setAdapter(this.b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final List<sjy> list, final ICloudOperationResult<List<DeviceInfo>> iCloudOperationResult) {
        sid.a((ICloudOperationResult<List<DeviceInfo>>) new ICloudOperationResult() { // from class: sgy
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                WeChatDeviceListActivity.this.a(list, iCloudOperationResult, (List) obj, str, z);
            }
        });
    }

    public /* synthetic */ void a(List list, ICloudOperationResult iCloudOperationResult, List list2, String str, boolean z) {
        sjy e2;
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            DeviceInfo deviceInfo = (DeviceInfo) it.next();
            if (TextUtils.isEmpty(deviceInfo.getHiLinkDeviceId()) && (e2 = sii.e(deviceInfo, list)) != null) {
                LogUtil.a("WeChatDeviceListActivity", deviceInfo.getDeviceName(), " setHiLinkDeviceId:", e2.getWiseDeviceProdId());
                deviceInfo.setHiLinkDeviceId(e2.getWiseDeviceProdId());
            }
            if (!this.j.contains(deviceInfo.getHiLinkDeviceId()) || sii.e(deviceInfo, list) != null) {
                it.remove();
                LogUtil.a("WeChatDeviceListActivity", "remove not supported or authorized device:", deviceInfo.getDeviceName());
            }
        }
        iCloudOperationResult.operationResult(list2, "", true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dXK_(Message message) {
        this.g.setVisibility(8);
        if (message.what == -1) {
            LogUtil.b("WeChatDeviceListActivity", "getBindDevicesAsync fail:", message.obj);
            return;
        }
        List<sjy> a2 = sid.a(this.d);
        this.f10559a = a2;
        boolean b = koq.b(a2);
        this.f.setVisibility(b ? 8 : 0);
        this.b.d(this.f10559a);
        this.b.a(c());
        if (message.what == 0) {
            this.b.notifyDataSetChanged();
        }
        this.e.setVisibility(b ? 0 : 8);
        this.i.setVisibility(b ? 8 : 0);
        this.i.setEnabled(!b);
        this.i.setTextColor(getColor(b ? R.color._2131296928_res_0x7f0902a0 : R.color._2131296927_res_0x7f09029f));
        this.i.setOnClickListener(new View.OnClickListener() { // from class: sgv
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WeChatDeviceListActivity.this.dXL_(view);
            }
        });
    }

    public /* synthetic */ void dXL_(View view) {
        if (this.c >= 5) {
            this.i.setEnabled(false);
            this.i.setTextColor(getColor(R.color._2131296928_res_0x7f0902a0));
            nrh.d(this, getString(R.string.IDS_wx_device_max_limit, new Object[]{5}));
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        b();
        ViewClickInstrumentation.clickOnView(view);
    }

    private int c() {
        List<DeviceInfo> b = sid.b();
        if (koq.b(b)) {
            LogUtil.c("WeChatDeviceListActivity", "getDefaultSelectIndex no connected device return DEFAULT_SELECTED");
            return 0;
        }
        int e2 = e(b.get(0));
        if (b.size() == 1) {
            LogUtil.c("WeChatDeviceListActivity", "getDefaultSelectIndex only one index = ", Integer.valueOf(e2));
            return e2;
        }
        Iterator<DeviceInfo> it = b.iterator();
        while (it.hasNext()) {
            e2 = Math.min(e2, e(it.next()));
        }
        LogUtil.c("WeChatDeviceListActivity", "getDefaultSelectIndex return index = ", Integer.valueOf(e2));
        return e2;
    }

    private int e(DeviceInfo deviceInfo) {
        if (koq.b(this.f10559a)) {
            return 0;
        }
        for (int i = 0; i < this.f10559a.size(); i++) {
            sjy sjyVar = this.f10559a.get(i);
            if (sjyVar.getIdentify().equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
                LogUtil.b("WeChatDeviceListActivity", "findIndex :", sjyVar.getIdentify(), ", return index = ", Integer.valueOf(i));
                return i;
            }
        }
        return 0;
    }

    private void b() {
        int c;
        if (nsn.o() || this.b == null || koq.b(this.f10559a) || (c = this.b.c()) < 0 || c >= this.f10559a.size()) {
            return;
        }
        sjy sjyVar = this.f10559a.get(c);
        Intent intent = new Intent(this, (Class<?>) WeChatDeviceAuthorizeActivity.class);
        intent.putExtra("name", sjyVar.getDeviceName());
        intent.putExtra("identify", sjyVar.getIdentify());
        intent.putExtra("isAuthorized", false);
        startActivity(intent);
    }

    static class e extends BaseHandler<WeChatDeviceListActivity> {
        /* synthetic */ e(WeChatDeviceListActivity weChatDeviceListActivity, AnonymousClass4 anonymousClass4) {
            this(weChatDeviceListActivity);
        }

        private e(WeChatDeviceListActivity weChatDeviceListActivity) {
            super(weChatDeviceListActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dXM_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(WeChatDeviceListActivity weChatDeviceListActivity, Message message) {
            weChatDeviceListActivity.dXK_(message);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
