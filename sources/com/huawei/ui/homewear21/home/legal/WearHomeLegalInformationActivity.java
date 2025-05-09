package com.huawei.ui.homewear21.home.legal;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.constant.TransferBusinessType;
import com.huawei.health.devicemgr.business.constant.TransferFileReqType;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback;
import com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.pluginachievement.gltexture.util.FileUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import com.huawei.ui.homewear21.home.adapter.WearHomeLegalRecyclerAdapter;
import com.huawei.ui.homewear21.home.bean.WearHomeXmlParseBean;
import com.huawei.ui.homewear21.home.listener.WearHomeListener;
import defpackage.blz;
import defpackage.jdq;
import defpackage.jfq;
import defpackage.jgf;
import defpackage.jpt;
import defpackage.jqj;
import defpackage.kxz;
import defpackage.msp;
import defpackage.nrh;
import defpackage.nsy;
import defpackage.pbq;
import defpackage.pen;
import health.compact.a.CommonUtil;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class WearHomeLegalInformationActivity extends BaseActivity implements IBaseResponseCallback {
    private Context b;
    private Context c;
    public DeviceSettingsInteractors d;
    private int e;
    private WearHomeLegalRecyclerAdapter g;
    private String h;
    private String k;
    private int m;
    private CommonDialog21 o;

    /* renamed from: a, reason: collision with root package name */
    public String f9678a = "";
    private List<pbq> j = new ArrayList(16);
    private String n = BaseApplication.getContext().getFilesDir() + "/source/";
    private c i = new c(this);
    private String f = "";

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.legal_information_activity);
        this.c = this;
        Context context = BaseApplication.getContext();
        this.b = context;
        this.d = DeviceSettingsInteractors.d(context);
        j();
        c();
        i();
    }

    private void j() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        String stringExtra = intent.getStringExtra("device_id");
        this.f9678a = stringExtra;
        if (stringExtra == null) {
            this.f9678a = "";
        }
        this.f = intent.getStringExtra(PluginPayAdapter.KEY_DEVICE_INFO_NAME);
        this.m = intent.getIntExtra("product_type", 0);
        jgf.b().d(this);
    }

    private void i() {
        h();
        g();
    }

    private void g() {
        this.g.c(new WearHomeListener() { // from class: com.huawei.ui.homewear21.home.legal.WearHomeLegalInformationActivity.1
            @Override // com.huawei.ui.homewear21.home.listener.WearHomeListener
            public void onItemClick(int i) {
                DeviceInfo e = jpt.e(WearHomeLegalInformationActivity.this.f9678a, "WearHomeLegalInformationActivity");
                if (e != null) {
                    WearHomeLegalInformationActivity.this.e = e.getDeviceConnectState();
                    WearHomeLegalInformationActivity.this.k = e.getSoftVersion();
                    WearHomeLegalInformationActivity.this.h = e.getDeviceModel();
                }
                if (i >= 0 && i < WearHomeLegalInformationActivity.this.j.size()) {
                    int e2 = ((pbq) WearHomeLegalInformationActivity.this.j.get(i)).e();
                    LogUtil.a("WearHomeLegalInformationActivity", "onItemClick legalType： ", Integer.valueOf(e2));
                    if (e2 == 0) {
                        pen.e(WearHomeLegalInformationActivity.this.c, "HealthUserPrivacyStatement", WearHomeLegalInformationActivity.this.f9678a, WearHomeLegalInformationActivity.this.f, WearHomeLegalInformationActivity.this.m);
                        return;
                    }
                    if (e2 == 1) {
                        WearHomeLegalInformationActivity wearHomeLegalInformationActivity = WearHomeLegalInformationActivity.this;
                        wearHomeLegalInformationActivity.b("open_source.zip", 11, "/NOTICE-all.html", wearHomeLegalInformationActivity.c.getString(R.string._2130843846_res_0x7f0218c6));
                        return;
                    }
                    if (e2 == 2) {
                        WearHomeLegalInformationActivity.this.a();
                        return;
                    }
                    if (e2 == 3) {
                        pen.e(WearHomeLegalInformationActivity.this.c, 3, WearHomeLegalInformationActivity.this.m);
                        return;
                    }
                    if (e2 == 4) {
                        pen.e(WearHomeLegalInformationActivity.this.c, "WatchHealthUserAgreement", WearHomeLegalInformationActivity.this.f9678a, WearHomeLegalInformationActivity.this.f, WearHomeLegalInformationActivity.this.m);
                        return;
                    }
                    if (e2 != 10) {
                        switch (e2) {
                            case 14:
                                pen.e(WearHomeLegalInformationActivity.this.c, "SagaPrivacyStatement", "", "", WearHomeLegalInformationActivity.this.m);
                                break;
                            case 15:
                                pen.e(WearHomeLegalInformationActivity.this.c, "SagaPersonalInfoCollection", "", "", WearHomeLegalInformationActivity.this.m);
                                break;
                            case 16:
                                WearHomeLegalInformationActivity wearHomeLegalInformationActivity2 = WearHomeLegalInformationActivity.this;
                                wearHomeLegalInformationActivity2.b("webview_license_source.zip", 25, "/webview_license_source.html", wearHomeLegalInformationActivity2.c.getString(R.string._2130846670_res_0x7f0223ce));
                                break;
                            default:
                                LogUtil.h("WearHomeLegalInformationActivity", "position is null");
                                break;
                        }
                        return;
                    }
                    pen.e(WearHomeLegalInformationActivity.this.c, 10, WearHomeLegalInformationActivity.this.m);
                    return;
                }
                LogUtil.h("WearHomeLegalInformationActivity", "position is wrong ");
            }
        });
    }

    private String a(String str, int i) {
        if (this.e != 2) {
            return "";
        }
        String d = blz.d(e(str, i), "");
        if (TextUtils.isEmpty(d) || !d.contains(",")) {
            return "";
        }
        String[] split = d.split(",");
        if (split.length != 2) {
            return "";
        }
        String str2 = split[0];
        return (TextUtils.isEmpty(str2) || !str2.equals(this.k)) ? "" : split[1];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, String str2, int i) {
        blz.a(e(str2, i), this.k + "," + str);
    }

    private String e(String str, int i) {
        return jdq.e(this.c, this.h + this.m + this.f9678a + str + i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final String str, final int i, final String str2, final String str3) {
        String a2 = a(str, i);
        if (!TextUtils.isEmpty(a2)) {
            String c2 = CommonUtil.c(this.n);
            if (msp.c(a2, c2) > 0) {
                LogUtil.a("WearHomeLegalInformationActivity", "Load the ", str, " file from the cache");
                pen.e(c2 + str2, str3, this.c);
                return;
            }
        }
        if (e()) {
            jqj jqjVar = new jqj();
            jqjVar.c(this.f9678a);
            jqjVar.a(str);
            jqjVar.d(i);
            jqjVar.c(TransferFileReqType.FILEREQUEST_DELIVERY);
            jqjVar.a(false);
            jfq.c().d(TransferBusinessType.FIVE_FORTY_FOUR, jqjVar, new IAppTransferFileResultAIDLCallback.Stub() { // from class: com.huawei.ui.homewear21.home.legal.WearHomeLegalInformationActivity.5
                @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
                public void onFileRespond(int i2, String str4) {
                    WearHomeLegalInformationActivity.this.l();
                    if (str4 != null) {
                        String c3 = CommonUtil.c(WearHomeLegalInformationActivity.this.n);
                        if (msp.c(str4, c3) > 0) {
                            WearHomeLegalInformationActivity.this.e(str4, str, i);
                            pen.e(c3 + str2, str3, WearHomeLegalInformationActivity.this.c);
                        }
                    }
                }

                @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
                public void onUpgradeFailed(int i2, String str4) {
                    LogUtil.h("WearHomeLegalInformationActivity", "getOpenSourceFile open source onFailure :", Integer.valueOf(i2));
                    WearHomeLegalInformationActivity.this.d();
                }

                @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
                public void onFileTransferState(int i2, String str4) {
                    LogUtil.a("WearHomeLegalInformationActivity", "getOpenSourceFile open source onProgress :", Integer.valueOf(i2));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        this.i.removeMessages(1);
        runOnUiThread(new Runnable() { // from class: com.huawei.ui.homewear21.home.legal.WearHomeLegalInformationActivity.4
            @Override // java.lang.Runnable
            public void run() {
                WearHomeLegalInformationActivity.this.b();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.i.removeMessages(1);
        runOnUiThread(new Runnable() { // from class: com.huawei.ui.homewear21.home.legal.WearHomeLegalInformationActivity.3
            @Override // java.lang.Runnable
            public void run() {
                WearHomeLegalInformationActivity.this.b();
                nrh.c(WearHomeLegalInformationActivity.this.c, BaseApplication.getContext().getString(R.string._2130843849_res_0x7f0218c9));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        String a2 = a("app_open_source.zip", 13);
        if (!TextUtils.isEmpty(a2)) {
            String c2 = CommonUtil.c(this.n);
            int c3 = msp.c(a2, c2);
            new ArrayList(16);
            if (c3 > 0) {
                LogUtil.a("WearHomeLegalInformationActivity", "Load the app_open_source file from the cache");
                b(pen.b(c2));
                return;
            }
        }
        if (e()) {
            final jqj jqjVar = new jqj();
            jqjVar.a("app_open_source.zip");
            jqjVar.d(13);
            jqjVar.a(false);
            jqjVar.c((String) null);
            jfq.c().c(jqjVar, new ITransferSleepAndDFXFileCallback.Stub() { // from class: com.huawei.ui.homewear21.home.legal.WearHomeLegalInformationActivity.2
                @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
                public void onSuccess(int i, String str, String str2) {
                    WearHomeLegalInformationActivity.this.l();
                    FileUtil.c(WearHomeLegalInformationActivity.this.c).c(WearHomeLegalInformationActivity.this.n);
                    String c4 = CommonUtil.c(WearHomeLegalInformationActivity.this.n);
                    int c5 = msp.c(str, c4);
                    ArrayList<WearHomeXmlParseBean> arrayList = new ArrayList<>(16);
                    if (c5 > 0) {
                        WearHomeLegalInformationActivity.this.e(str, jqjVar.j(), jqjVar.n());
                        arrayList = pen.b(c4);
                    }
                    WearHomeLegalInformationActivity.this.b(arrayList);
                }

                @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
                public void onFailure(int i, String str) {
                    LogUtil.h("WearHomeLegalInformationActivity", "source statement onFailure :", str);
                    WearHomeLegalInformationActivity.this.d();
                }

                @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
                public void onProgress(int i, String str) {
                    LogUtil.a("WearHomeLegalInformationActivity", "source statement onProgress :", Integer.valueOf(i));
                }
            });
        }
    }

    private boolean e() {
        LogUtil.a("WearHomeLegalInformationActivity", "enter getDeviceState");
        int i = this.e;
        if (i == 2) {
            LogUtil.a("WearHomeLegalInformationActivity", "enter DEVICE_DISCONNECTED", Integer.valueOf(i));
            f();
            this.i.sendEmptyMessageDelayed(1, 60000L);
            return true;
        }
        nrh.c(this.c, BaseApplication.getContext().getString(R.string.IDS_setting_wearhome_device_disconnected));
        LogUtil.a("WearHomeLegalInformationActivity", "enter connectState =", Integer.valueOf(this.e));
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(ArrayList<WearHomeXmlParseBean> arrayList) {
        try {
            Intent intent = new Intent(this.c, (Class<?>) WearHomeOpenSourceStatementActivity.class);
            Bundle bundle = new Bundle();
            if (!arrayList.isEmpty()) {
                bundle.putParcelableArrayList("openSourceStatementName", arrayList);
            }
            intent.putExtras(bundle);
            this.c.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("WearHomeLegalInformationActivity", "openSourceActivity ActivityNotFoundException");
        }
    }

    private void f() {
        if (this.o == null) {
            new CommonDialog21(this.c, R.style.app_update_dialogActivity);
            CommonDialog21 a2 = CommonDialog21.a(this.c);
            this.o = a2;
            a2.e(this.c.getString(R.string._2130841528_res_0x7f020fb8));
            this.o.setCancelable(false);
        }
        if (isFinishing()) {
            return;
        }
        LogUtil.a("WearHomeLegalInformationActivity", "showLoadingDialog mLoadingDialog.show()");
        this.o.a();
    }

    public void b() {
        CommonDialog21 commonDialog21;
        if (isFinishing() || (commonDialog21 = this.o) == null) {
            return;
        }
        commonDialog21.dismiss();
        this.o = null;
        LogUtil.a("WearHomeLegalInformationActivity", "destroy mLoadingDialog");
    }

    private void h() {
        ((CustomTitleBar) nsy.cMc_(this, R.id.wear_home_legal)).setTitleText(BaseApplication.getContext().getString(R.string._2130843844_res_0x7f0218c4));
        HealthRecycleView healthRecycleView = (HealthRecycleView) nsy.cMc_(this, R.id.legal_information);
        healthRecycleView.setLayoutManager(new LinearLayoutManager(this, 1, false));
        healthRecycleView.a(false);
        healthRecycleView.d(false);
        if (this.j != null) {
            WearHomeLegalRecyclerAdapter wearHomeLegalRecyclerAdapter = new WearHomeLegalRecyclerAdapter(this.c, this.j, "legalAdapter");
            this.g = wearHomeLegalRecyclerAdapter;
            healthRecycleView.setAdapter(wearHomeLegalRecyclerAdapter);
        }
    }

    private void c() {
        DeviceCapability e = this.d.e(this.f9678a);
        this.j = new ArrayList(5);
        if (e != null && e.isSupportLegalPrivacy()) {
            this.j.add(new pbq(BaseApplication.getContext().getString(R.string._2130843845_res_0x7f0218c5), 0));
        }
        DeviceInfo b = jpt.b(this.f9678a, "WearHomeLegalInformationActivity");
        if (b != null && b.getProductType() == 80) {
            this.j.add(new pbq(BaseApplication.getContext().getString(R.string._2130846329_res_0x7f022279), 14));
            if (!Utils.o()) {
                this.j.add(new pbq(BaseApplication.getContext().getString(R.string._2130837564_res_0x7f02003c), 15));
            }
        }
        if (e != null && e.isSupportLegalUserAgreement()) {
            this.j.add(new pbq(BaseApplication.getContext().getString(R.string._2130843851_res_0x7f0218cb), 4));
        }
        if (e != null && e.isSupportLegalOpenSource()) {
            this.j.add(new pbq(BaseApplication.getContext().getString(R.string._2130843846_res_0x7f0218c6), 1));
        }
        if (e != null && e.isSupportLegalServiceStatement()) {
            this.j.add(new pbq(BaseApplication.getContext().getString(R.string._2130843850_res_0x7f0218ca), 3));
        }
        if (e != null && e.isSupportLegalSourceStatement()) {
            this.j.add(new pbq(BaseApplication.getContext().getString(R.string._2130843847_res_0x7f0218c7), 2));
        }
        String i = kxz.i(this.c, this.f9678a);
        if (!TextUtils.isEmpty(i) && "1".equals(i)) {
            this.j.add(new pbq(BaseApplication.getContext().getString(R.string._2130845404_res_0x7f021edc), 10));
        }
        Object[] objArr = new Object[4];
        objArr[0] = "deviceCapability = ";
        objArr[1] = e;
        objArr[2] = " isSupportLegalSystemWebView ";
        objArr[3] = e == null ? "" : Boolean.valueOf(e.isSupportLegalSystemWebView());
        LogUtil.a("WearHomeLegalInformationActivity", objArr);
        if (e == null || !e.isSupportLegalSystemWebView()) {
            return;
        }
        this.j.add(new pbq(BaseApplication.getContext().getString(R.string._2130846670_res_0x7f0223ce), 16));
    }

    static class c extends Handler {
        WeakReference<WearHomeLegalInformationActivity> b;

        c(WearHomeLegalInformationActivity wearHomeLegalInformationActivity) {
            this.b = new WeakReference<>(wearHomeLegalInformationActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            WearHomeLegalInformationActivity wearHomeLegalInformationActivity = this.b.get();
            if (wearHomeLegalInformationActivity != null && message.what == 1) {
                LogUtil.a("WearHomeLegalInformationActivity", "handleMessage time out start");
                wearHomeLegalInformationActivity.d();
                LogUtil.a("WearHomeLegalInformationActivity", "handleMessage time out end");
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        b();
        this.i.removeMessages(1);
        jgf.b().c(this);
    }

    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
    /* renamed from: onResponse */
    public void d(int i, Object obj) {
        if (isFinishing()) {
            return;
        }
        finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
