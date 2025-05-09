package com.huawei.ui.device.activity.agreement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.dialog.CustomProgressDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.jfq;
import defpackage.jfu;
import defpackage.moh;
import defpackage.msj;
import defpackage.msn;
import defpackage.msq;
import defpackage.nrh;
import defpackage.nsy;
import defpackage.nug;
import defpackage.nuj;
import defpackage.nyu;
import defpackage.nyv;
import defpackage.nzf;
import defpackage.nzj;
import defpackage.oau;
import defpackage.obb;
import defpackage.sqo;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class AgreementDeclarationActivity extends BaseActivity implements View.OnClickListener {
    public static volatile boolean b = false;
    private HealthTextView aa;
    private HealthTextView ac;
    private String[] c;
    private Context f;
    private CustomTextAlertDialog.Builder g;
    private String h;
    private CustomProgressDialog i;
    private HealthCheckBox j;
    private CustomProgressDialog.Builder k;
    private DeviceCapability l;
    private List<nzf> m;
    private DeviceInfo n;
    private String o;
    private CustomTextAlertDialog q;
    private String r;
    private CustomTextAlertDialog s;
    private String t;
    private String w;
    private CustomTextAlertDialog x;
    private Handler y = new a(this);
    private boolean d = false;
    private boolean e = false;

    /* renamed from: a, reason: collision with root package name */
    private boolean f9040a = false;
    private int v = -1;
    private final BroadcastReceiver u = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.agreement.AgreementDeclarationActivity.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("AgreementDeclarationActivity", "mProcessLocalBroadcastReceiver intent is null");
                return;
            }
            if ("com.huawei.health.action.DOWNLOAD_SINGLE_DEVICE_PROCESS".equals(intent.getAction())) {
                AgreementDeclarationActivity.this.a(intent.getIntExtra("progress", -1));
            } else {
                if ("com.huawei.health.action.ACTION_BACKGROUND_DOWNLOAD_DEVICE".equals(intent.getAction())) {
                    AgreementDeclarationActivity.this.c();
                    AgreementDeclarationActivity.this.y();
                    AgreementDeclarationActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.agreement.AgreementDeclarationActivity.2.3
                        @Override // java.lang.Runnable
                        public void run() {
                            AgreementDeclarationActivity.this.e = false;
                            AgreementDeclarationActivity.this.g();
                            AgreementDeclarationActivity.this.j();
                        }
                    });
                    return;
                }
                LogUtil.h("AgreementDeclarationActivity", "mProcessLocalBroadcastReceiver()  intent : ", intent.getAction());
            }
        }
    };
    private BroadcastReceiver p = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.agreement.AgreementDeclarationActivity.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                LogUtil.h("AgreementDeclarationActivity", "intent or action is null");
                return;
            }
            if ("broadcast_receiver_user_setting".equals(intent.getAction())) {
                LogUtil.a("AgreementDeclarationActivity", "onReceive broadcast_receiver_user_setting");
                AgreementDeclarationActivity.this.y.removeMessages(1);
                AgreementDeclarationActivity.this.finish();
                return;
            }
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
                if (!(parcelableExtra instanceof DeviceInfo)) {
                    LogUtil.h("AgreementDeclarationActivity", "the intent type is not DeviceInfo");
                    return;
                }
                DeviceInfo deviceInfo = (DeviceInfo) parcelableExtra;
                if (TextUtils.isEmpty(AgreementDeclarationActivity.this.o) || !AgreementDeclarationActivity.this.o.equals(deviceInfo.getDeviceIdentify())) {
                    LogUtil.h("AgreementDeclarationActivity", "This broadcast is not sent by the current device");
                    return;
                }
                int deviceConnectState = deviceInfo.getDeviceConnectState();
                if (deviceConnectState == 4 || deviceConnectState == 3) {
                    if (AgreementDeclarationActivity.this.f9040a) {
                        AgreementDeclarationActivity.this.d(-1);
                        return;
                    } else {
                        AgreementDeclarationActivity.this.d(-2);
                        return;
                    }
                }
                if (deviceConnectState == 2) {
                    LogUtil.a("AgreementDeclarationActivity", "onReceive connected");
                    AgreementDeclarationActivity.this.finish();
                    return;
                } else {
                    LogUtil.h("AgreementDeclarationActivity", "onReceive change else");
                    return;
                }
            }
            LogUtil.a("AgreementDeclarationActivity", "onReceive else branch");
        }
    };

    static class a extends Handler {
        WeakReference<AgreementDeclarationActivity> c;

        a(AgreementDeclarationActivity agreementDeclarationActivity) {
            this.c = new WeakReference<>(agreementDeclarationActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            AgreementDeclarationActivity agreementDeclarationActivity = this.c.get();
            if (agreementDeclarationActivity == null) {
                LogUtil.h("AgreementDeclarationActivity", "handleMessage activity is null");
                return;
            }
            if (message.what == 1) {
                nuj.a(agreementDeclarationActivity.r, 2, 2);
                agreementDeclarationActivity.f9040a = true;
                agreementDeclarationActivity.d(-1);
                LogUtil.a("AgreementDeclarationActivity", "handleMessage pair time out");
                sqo.m("oobe is time out");
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("AgreementDeclarationActivity", "AgreementDeclarationActivity onCreate() ");
        this.f = this;
        i();
        o();
        j();
        IntentFilter intentFilter = new IntentFilter("broadcast_receiver_user_setting");
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        BroadcastManagerUtil.bFC_(this.f, this.p, intentFilter, LocalBroadcast.c, null);
    }

    private void o() {
        ReleaseLogUtil.e("R_AgreementDeclarationActivity", "sendOobeStatus: ", Boolean.valueOf(jfq.c().d("oobeCreated", this.n, 0, null)));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        Handler handler = this.y;
        if (handler != null) {
            handler.sendEmptyMessageDelayed(1, 60000L);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        Handler handler = this.y;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        obb.a(this.o);
        b = false;
        BroadcastReceiver broadcastReceiver = this.p;
        if (broadcastReceiver != null) {
            BroadcastManagerUtil.bFK_(this.f, broadcastReceiver);
        }
        CustomTextAlertDialog customTextAlertDialog = this.x;
        if (customTextAlertDialog != null) {
            if (customTextAlertDialog.isShowing()) {
                this.x.dismiss();
            }
            this.x = null;
        }
        CustomProgressDialog customProgressDialog = this.i;
        if (customProgressDialog != null) {
            if (customProgressDialog.isShowing()) {
                this.i.dismiss();
            }
            this.i = null;
        }
        if (this.k != null) {
            this.k = null;
        }
        CustomTextAlertDialog customTextAlertDialog2 = this.q;
        if (customTextAlertDialog2 != null) {
            if (customTextAlertDialog2.isShowing()) {
                this.q.dismiss();
            }
            this.q = null;
        }
        CustomTextAlertDialog customTextAlertDialog3 = this.s;
        if (customTextAlertDialog3 != null) {
            if (customTextAlertDialog3.isShowing()) {
                this.s.dismiss();
            }
            this.s = null;
        }
    }

    private void i() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("AgreementDeclarationActivity", "initData intent is null");
            return;
        }
        this.o = intent.getStringExtra("pairGuideSelectAddress");
        this.h = intent.getStringExtra("device_country_code");
        this.t = intent.getStringExtra("device_emui_version");
        if (intent.getExtras() != null) {
            this.n = (DeviceInfo) intent.getExtras().getParcelable("deviceInfo");
        }
        DeviceInfo deviceInfo = this.n;
        if (deviceInfo == null) {
            LogUtil.h("AgreementDeclarationActivity", "mDeviceInfo is null");
            return;
        }
        this.r = deviceInfo.getDeviceName();
        if (TextUtils.isEmpty(this.t)) {
            sqo.m("oobe emuiVersion is empty");
            d(-3);
        }
        g();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        String j = jfu.j(this.n.getProductType());
        String pluginIndexOobe = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginIndexOobe(j);
        this.w = pluginIndexOobe;
        LogUtil.a("AgreementDeclarationActivity", " mOobeid1 is : ", pluginIndexOobe, "   ", j);
        Map<String, DeviceCapability> a2 = jfq.c().a(3, "", "AgreementDeclarationActivity");
        if (a2 == null) {
            LogUtil.h("AgreementDeclarationActivity", "filter deviceCapabilityMap is null");
            return;
        }
        DeviceCapability deviceCapability = a2.get(this.o);
        if (deviceCapability != null) {
            int smartWatchVersionTypeValue = deviceCapability.getSmartWatchVersionTypeValue();
            LogUtil.a("AgreementDeclarationActivity", "smartWatchVersion:", Integer.valueOf(smartWatchVersionTypeValue));
            nuj.d(smartWatchVersionTypeValue, this.w);
        }
        LogUtil.a("AgreementDeclarationActivity", "basic:", Integer.valueOf(nuj.a().length));
        String[] a3 = nuj.a();
        this.c = a3;
        if (a3.length == 0) {
            sqo.m("get oobe resource error");
            d(false);
        }
        this.m = new ArrayList(this.c.length + 1);
        List<String> d = d();
        b(d);
        ArrayList arrayList = new ArrayList(0);
        for (String str : d) {
            nzf e = nuj.e(str, this.t);
            if (!TextUtils.isEmpty(e.a())) {
                nzj n = e.n();
                if (n != null) {
                    n.d(true);
                }
                arrayList.add(e);
            } else {
                LogUtil.h("AgreementDeclarationActivity", "featureId: ", str);
            }
        }
        if (arrayList.isEmpty()) {
            LogUtil.h("AgreementDeclarationActivity", "tempDeclarationList is empty");
            sqo.m("oobe info is empty");
            d(true);
            return;
        }
        this.m.addAll(arrayList);
    }

    private void d(boolean z) {
        LogUtil.b("AgreementDeclarationActivity", "enter reportShowException", "deviceId:", this.o, "emuiVersion:", this.t, "isHasResources:", Boolean.valueOf(z));
        oau.a(this.o, this.t, z);
        if (this.e) {
            return;
        }
        LogUtil.h("AgreementDeclarationActivity", "showDownloadRetryDialog");
        this.e = true;
        r();
    }

    private List<String> d() {
        ArrayList arrayList = new ArrayList(this.c.length);
        Collections.addAll(arrayList, this.c);
        return arrayList;
    }

    private void b(List<String> list) {
        Map<String, DeviceCapability> a2 = jfq.c().a(3, "", "AgreementDeclarationActivity");
        if (a2 == null) {
            LogUtil.h("AgreementDeclarationActivity", "filter deviceCapabilityMap is null");
            return;
        }
        DeviceCapability deviceCapability = a2.get(this.o);
        this.l = deviceCapability;
        if (deviceCapability != null) {
            LogUtil.a("AgreementDeclarationActivity", "DEVICE_TYPE", Integer.valueOf(deviceCapability.getSmartWatchVersionTypeValue()));
            int smartWatchVersionTypeValue = this.l.getSmartWatchVersionTypeValue();
            if (smartWatchVersionTypeValue == 0) {
                this.d = false;
                return;
            }
            if (smartWatchVersionTypeValue == 1) {
                list.remove("operator_service_statement");
                this.d = false;
                return;
            } else if (smartWatchVersionTypeValue == 2) {
                list.remove("huawei_mobile_service_statement");
                this.d = true;
                return;
            } else {
                if (smartWatchVersionTypeValue == 3) {
                    list.remove("huawei_mobile_service_statement");
                    list.remove("operator_service_statement");
                    this.d = true;
                    return;
                }
                LogUtil.a("AgreementDeclarationActivity", "in default branch");
                return;
            }
        }
        LogUtil.a("AgreementDeclarationActivity", "declaration deviceCapability is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        setContentView(R.layout.activity_agreement_declaration);
        HealthTextView healthTextView = (HealthTextView) nsy.cMc_(this, R.id.agreement_declaration_text_view_back);
        this.ac = healthTextView;
        if (healthTextView != null) {
            healthTextView.setOnClickListener(this);
        }
        HealthTextView healthTextView2 = (HealthTextView) nsy.cMc_(this, R.id.agreement_declaration_text_view_next);
        this.aa = healthTextView2;
        if (healthTextView2 != null) {
            healthTextView2.setOnClickListener(this);
        }
        HealthCheckBox healthCheckBox = (HealthCheckBox) nsy.cMc_(this, R.id.cb_checkbox);
        this.j = healthCheckBox;
        if (healthCheckBox != null) {
            healthCheckBox.setChecked(!this.d);
            this.j.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.activity.agreement.AgreementDeclarationActivity.5
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    AgreementDeclarationActivity.this.e(z);
                    ViewClickInstrumentation.clickOnView(compoundButton);
                }
            });
            e(this.j.isChecked());
        }
        ListView listView = (ListView) nsy.cMc_(this, R.id.list_view_content);
        List<nyu> c = new nyv().c(this.m);
        a(c);
        DeviceInfo deviceInfo = this.n;
        if (deviceInfo != null) {
            this.v = deviceInfo.getProductType();
        }
        DeclarationAdapter declarationAdapter = new DeclarationAdapter(this, c, this.v);
        if (listView != null) {
            listView.setAdapter((ListAdapter) declarationAdapter);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z) {
        HealthTextView healthTextView = (HealthTextView) nsy.cMc_(this, R.id.agreement_declaration_text_view_next);
        this.aa = healthTextView;
        if (healthTextView != null) {
            healthTextView.setEnabled(z);
            if (z) {
                this.aa.setTextColor(this.f.getResources().getColor(R.color._2131296685_res_0x7f0901ad));
            } else {
                this.aa.setTextColor(this.f.getResources().getColor(R.color._2131296687_res_0x7f0901af));
            }
        }
    }

    private void a(List<nyu> list) {
        nyu nyuVar = new nyu();
        nyuVar.d(0);
        nyuVar.f(this.f.getResources().getString(R.string._2130843914_res_0x7f02190a));
        list.add(0, nyuVar);
    }

    private void m() {
        LogUtil.a("AgreementDeclarationActivity", "next()");
        if (this.d && f()) {
            w();
        } else {
            t();
        }
    }

    private boolean f() {
        nyu c = c("hms_statement_head");
        nyu c2 = c("hms_statement_body");
        nyu c3 = c("hms_auto_update");
        if (c == null || c2 == null || c3 == null) {
            LogUtil.h("AgreementDeclarationActivity", "hms is null");
            return false;
        }
        if (!TextUtils.isEmpty(c.d()) && !TextUtils.isEmpty(c2.d()) && !TextUtils.isEmpty(c3.d())) {
            return true;
        }
        LogUtil.h("AgreementDeclarationActivity", "hms feature id is null");
        return false;
    }

    private nyu c(String str) {
        return new nyv().c(nuj.e(str, this.t));
    }

    private void t() {
        if (nuj.e().length == 0) {
            nug.e(this.o, new nyv().c(this.m));
            this.aa.setEnabled(false);
            this.ac.setEnabled(false);
            return;
        }
        Intent intent = new Intent(this.f, (Class<?>) EnhancementImprovementActivity.class);
        if (new nyv().c(this.m) instanceof Serializable) {
            intent.putExtra("declarationBeans", (Serializable) new nyv().c(this.m));
        }
        intent.putExtra("pairGuideSelectAddress", this.o);
        intent.putExtra("device_country_code", this.h);
        intent.putExtra("device_emui_version", this.t);
        intent.putExtra("device_version_type", this.v);
        intent.putExtra(PluginPayAdapter.KEY_DEVICE_INFO_NAME, this.r);
        startActivity(intent);
    }

    private void w() {
        Intent intent = new Intent(this.f, (Class<?>) AboardHmsDeclarationActivity.class);
        if (new nyv().c(this.m) instanceof Serializable) {
            intent.putExtra("declarationBeans", (Serializable) new nyv().c(this.m));
        }
        intent.putExtra("pairGuideSelectAddress", this.o);
        intent.putExtra("device_country_code", this.h);
        intent.putExtra("device_emui_version", this.t);
        intent.putExtra(PluginPayAdapter.KEY_DEVICE_INFO_NAME, this.r);
        intent.putExtra("device_version_type", this.v);
        startActivity(intent);
    }

    private void a() {
        nuj.a(this.r, 1, 2);
        p();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (keyEvent == null) {
            LogUtil.h("AgreementDeclarationActivity", "onKeyDown event is null");
            return false;
        }
        if (keyEvent.getKeyCode() == 4) {
            LogUtil.a("AgreementDeclarationActivity", "on press back not back");
            a();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.agreement_declaration_text_view_back) {
            a();
        } else if (view.getId() == R.id.agreement_declaration_text_view_next) {
            m();
        } else {
            CustomTextAlertDialog.Builder builder = this.g;
            if (builder != null && builder.e() == view) {
                d(-3);
            } else {
                CustomTextAlertDialog.Builder builder2 = this.g;
                if (builder2 != null && builder2.c() == view) {
                    LogUtil.a("AgreementDeclarationActivity", "onClick() the NegativeButton");
                } else {
                    LogUtil.a("AgreementDeclarationActivity", "onClick() the else branch");
                }
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void p() {
        LogUtil.a("AgreementDeclarationActivity", "showExitPairDialog()");
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.f);
        this.g = builder;
        builder.d(R.string._2130843918_res_0x7f02190e);
        this.g.cyU_(R.string._2130843887_res_0x7f0218ef, this);
        this.g.cyR_(R.string._2130843886_res_0x7f0218ee, this);
        CustomTextAlertDialog a2 = this.g.a();
        this.x = a2;
        a2.setCancelable(true);
        this.x.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        LogUtil.a("AgreementDeclarationActivity", "enter notify Pair Fail", Integer.valueOf(i));
        Intent intent = new Intent();
        intent.setAction("broadcast_receiver_user_setting");
        intent.putExtra("error_code", i);
        intent.putExtra("pairGuideSelectAddress", this.o);
        BroadcastManagerUtil.bFH_(this.f, intent, LocalBroadcast.c, true);
        finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            LogUtil.h("AgreementDeclarationActivity", "dispatchTouchEvent event is null");
            return false;
        }
        if (motionEvent.getAction() == 2) {
            this.y.removeMessages(1);
            this.y.sendEmptyMessageDelayed(1, 60000L);
            LogUtil.a("AgreementDeclarationActivity", "timeout is reset");
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        e();
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.w);
        ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).downloadIndexByUuidList(arrayList, null);
        LogUtil.a("AgreementDeclarationActivity", "retryDownloadOobeData: ", arrayList);
        s();
        n();
    }

    private void e() {
        LogUtil.a("AgreementDeclarationActivity", "isDeleteSuccess:", Boolean.valueOf(moh.b(msj.a().d(this.w))));
    }

    private void n() {
        LogUtil.a("AgreementDeclarationActivity", "enter registerNonLocalBroadcastReceiver");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.health.action.DOWNLOAD_SINGLE_DEVICE_PROCESS");
        intentFilter.addAction("com.huawei.health.action.ACTION_BACKGROUND_DOWNLOAD_DEVICE");
        LocalBroadcastManager.getInstance(BaseApplication.getContext()).registerReceiver(this.u, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        try {
            LocalBroadcastManager.getInstance(BaseApplication.getContext()).unregisterReceiver(this.u);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("AgreementDeclarationActivity", "IllegalArgumentException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.agreement.AgreementDeclarationActivity.1
            @Override // java.lang.Runnable
            public void run() {
                nrh.b(AgreementDeclarationActivity.this.f, R.string._2130841392_res_0x7f020f30);
            }
        });
    }

    private void s() {
        CustomProgressDialog customProgressDialog = this.i;
        if (customProgressDialog != null && customProgressDialog.isShowing()) {
            LogUtil.h("AgreementDeclarationActivity", "The progress bar already exists");
        } else {
            runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.agreement.AgreementDeclarationActivity.3
                @Override // java.lang.Runnable
                public void run() {
                    AgreementDeclarationActivity.this.i = new CustomProgressDialog(AgreementDeclarationActivity.this.f);
                    AgreementDeclarationActivity agreementDeclarationActivity = AgreementDeclarationActivity.this;
                    agreementDeclarationActivity.k = new CustomProgressDialog.Builder(agreementDeclarationActivity.f);
                    AgreementDeclarationActivity.this.k.d(AgreementDeclarationActivity.this.f.getString(R.string.IDS_hw_health_wear_update_device_resource_text)).cyH_(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.agreement.AgreementDeclarationActivity.3.4
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            LogUtil.a("AgreementDeclarationActivity", "Click Cancel");
                            AgreementDeclarationActivity.this.h();
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    });
                    AgreementDeclarationActivity agreementDeclarationActivity2 = AgreementDeclarationActivity.this;
                    agreementDeclarationActivity2.i = agreementDeclarationActivity2.k.e();
                    AgreementDeclarationActivity.this.i.setCanceledOnTouchOutside(false);
                    if (!AgreementDeclarationActivity.this.isFinishing()) {
                        AgreementDeclarationActivity.this.i.show();
                        AgreementDeclarationActivity.this.k.d(0);
                        AgreementDeclarationActivity.this.k.e(UnitUtil.e(0.0d, 2, 0));
                    }
                    LogUtil.a("AgreementDeclarationActivity", "mCustomProgressDialog.show()");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        if (i == -1) {
            h();
            y();
            return;
        }
        CustomProgressDialog customProgressDialog = this.i;
        if (customProgressDialog == null || !customProgressDialog.isShowing() || i < 0) {
            return;
        }
        String e = UnitUtil.e(i, 2, 0);
        this.k.d(i);
        this.k.e(e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        LogUtil.a("AgreementDeclarationActivity", "enter handleCancel");
        msq e = msn.c().e(this.w);
        if (e != null) {
            msn.c().c(e);
        }
        b();
    }

    private void b() {
        c();
        q();
    }

    private void r() {
        CustomTextAlertDialog customTextAlertDialog = this.s;
        if (customTextAlertDialog == null || !customTextAlertDialog.isShowing()) {
            runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.agreement.AgreementDeclarationActivity.10
                @Override // java.lang.Runnable
                public void run() {
                    CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(AgreementDeclarationActivity.this.f);
                    builder.b(R.string.IDS_service_area_notice_title).d(R.string.IDS_no_device_res).cyS_(AgreementDeclarationActivity.this.f.getString(R.string._2130841939_res_0x7f021153), new View.OnClickListener() { // from class: com.huawei.ui.device.activity.agreement.AgreementDeclarationActivity.10.4
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            if (AgreementDeclarationActivity.this.s != null) {
                                AgreementDeclarationActivity.this.s.dismiss();
                                AgreementDeclarationActivity.this.s = null;
                            }
                            AgreementDeclarationActivity.this.h();
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    }).cyU_(R.string._2130841467_res_0x7f020f7b, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.agreement.AgreementDeclarationActivity.10.5
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            if (!CommonUtil.aa(BaseApplication.getContext())) {
                                AgreementDeclarationActivity.this.k();
                                ViewClickInstrumentation.clickOnView(view);
                                return;
                            }
                            if (AgreementDeclarationActivity.this.s != null) {
                                AgreementDeclarationActivity.this.s.dismiss();
                                AgreementDeclarationActivity.this.s = null;
                            }
                            AgreementDeclarationActivity.this.l();
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    });
                    AgreementDeclarationActivity.this.s = builder.a();
                    AgreementDeclarationActivity.this.s.setCancelable(false);
                    AgreementDeclarationActivity.this.s.show();
                }
            });
        }
    }

    private void q() {
        CustomTextAlertDialog customTextAlertDialog = this.q;
        if (customTextAlertDialog == null || !customTextAlertDialog.isShowing()) {
            runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.agreement.AgreementDeclarationActivity.6
                @Override // java.lang.Runnable
                public void run() {
                    CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(AgreementDeclarationActivity.this.f);
                    builder.b(R.string.IDS_service_area_notice_title).d(R.string._2130844882_res_0x7f021cd2).cyU_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.agreement.AgreementDeclarationActivity.6.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            if (AgreementDeclarationActivity.this.q != null) {
                                AgreementDeclarationActivity.this.q.dismiss();
                                AgreementDeclarationActivity.this.q = null;
                                AgreementDeclarationActivity.this.d(-3);
                            }
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    });
                    AgreementDeclarationActivity.this.q = builder.a();
                    AgreementDeclarationActivity.this.q.setCancelable(false);
                    AgreementDeclarationActivity.this.q.show();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.a("AgreementDeclarationActivity", "enter closeProgress");
        CustomProgressDialog customProgressDialog = this.i;
        if (customProgressDialog == null || !customProgressDialog.isShowing() || isFinishing()) {
            return;
        }
        this.i.cancel();
        LogUtil.h("AgreementDeclarationActivity", "enter closeProgress cancel");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
