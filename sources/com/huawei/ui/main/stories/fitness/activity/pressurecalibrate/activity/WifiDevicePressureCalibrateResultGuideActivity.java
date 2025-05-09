package com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.wifi.lib.handler.StaticHandler;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.DeviceServiceInfo;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceControlDataModelReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceServiceInfoReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceServiceInfoRsp;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.activity.WifiDevicePressureCalibrateResultGuideActivity;
import defpackage.cff;
import defpackage.cfi;
import defpackage.cgs;
import defpackage.cpa;
import defpackage.ctk;
import defpackage.ctq;
import defpackage.cts;
import defpackage.gnm;
import defpackage.jbs;
import defpackage.psm;
import defpackage.ptt;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class WifiDevicePressureCalibrateResultGuideActivity extends BaseActivity {
    private int b;
    private CustomTextAlertDialog c;
    private HealthTextView d;
    private NoTitleCustomAlertDialog e;
    private Context f;
    private c i;
    private CustomTitleBar r;
    private String t = "";
    private String p = "";
    private String k = "";
    private String s = "";
    private String n = "";
    private String m = "";
    private String q = "";
    private String o = "";
    private String g = "";
    private List<String> j = new ArrayList(16);
    private long h = 0;

    /* renamed from: a, reason: collision with root package name */
    private long f9870a = 0;
    private String l = "";

    static class c extends StaticHandler<WifiDevicePressureCalibrateResultGuideActivity> {
        c(WifiDevicePressureCalibrateResultGuideActivity wifiDevicePressureCalibrateResultGuideActivity) {
            super(wifiDevicePressureCalibrateResultGuideActivity);
        }

        @Override // com.huawei.health.device.wifi.lib.handler.StaticHandler
        /* renamed from: dtk_, reason: merged with bridge method [inline-methods] */
        public void handleMessage(WifiDevicePressureCalibrateResultGuideActivity wifiDevicePressureCalibrateResultGuideActivity, Message message) {
            if (wifiDevicePressureCalibrateResultGuideActivity.e()) {
                LogUtil.c("WifiDevicePressureCalibrateResultGuideActivity", "mCurrentt activity is finished...");
                return;
            }
            LogUtil.c("WifiDevicePressureCalibrateResultGuideActivity", "msg = ", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 1) {
                wifiDevicePressureCalibrateResultGuideActivity.i.removeMessages(2);
                wifiDevicePressureCalibrateResultGuideActivity.c(false);
                return;
            }
            if (i == 2) {
                wifiDevicePressureCalibrateResultGuideActivity.b();
                wifiDevicePressureCalibrateResultGuideActivity.i.sendEmptyMessageDelayed(2, 5000L);
                return;
            }
            if (i == 3) {
                wifiDevicePressureCalibrateResultGuideActivity.i.removeMessages(2);
                wifiDevicePressureCalibrateResultGuideActivity.i.removeMessages(1);
                wifiDevicePressureCalibrateResultGuideActivity.c(false);
            } else if (i == 4) {
                wifiDevicePressureCalibrateResultGuideActivity.i.removeMessages(2);
                wifiDevicePressureCalibrateResultGuideActivity.i.removeMessages(1);
                wifiDevicePressureCalibrateResultGuideActivity.c(true);
            } else {
                if (i != 5) {
                    return;
                }
                wifiDevicePressureCalibrateResultGuideActivity.i.removeMessages(2);
                wifiDevicePressureCalibrateResultGuideActivity.i.removeMessages(1);
                wifiDevicePressureCalibrateResultGuideActivity.j();
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_wifi_device_pressure_calibrate_result_guide);
        this.f = this;
        this.i = new c(this);
        this.h = System.currentTimeMillis();
        psm.e().c(true);
        Intent intent = getIntent();
        if (intent != null) {
            this.b = intent.getIntExtra("wifi_device_calibrate_score", -1);
            this.t = intent.getStringExtra("health_wifi_device_userId");
            this.l = intent.getStringExtra("health_wifi_device_productId");
        }
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.hw_wifi_device_pressure_calibrate_title_layout);
        this.r = customTitleBar;
        customTitleBar.setLeftButtonClickable(true);
        this.r.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: psy
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WifiDevicePressureCalibrateResultGuideActivity.this.dth_(view);
            }
        });
        this.d = (HealthTextView) findViewById(R.id.hw_wifi_device_pressure_calibrate_prompt);
        this.d.setText(this.f.getResources().getString(R$string.IDS_device_wifi_pressure_calibrate_two_step_msg));
        f();
        d();
    }

    public /* synthetic */ void dth_(View view) {
        h();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (4 == i) {
            h();
            return false;
        }
        return super.onKeyDown(i, keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e() {
        if (!isFinishing() && !isDestroyed()) {
            return false;
        }
        LogUtil.b("WifiDevicePressureCalibrateResultGuideActivity", "Activity is Destroyed");
        return true;
    }

    private void h() {
        String string = this.f.getResources().getString(R$string.IDS_hw_show_card_pressure_calibrate_dialog_notify);
        String string2 = this.f.getResources().getString(R$string.IDS_hw_show_card_pressure_calibrate_dialog_text);
        String string3 = this.f.getResources().getString(R$string.IDS_hw_common_ui_dialog_cancel);
        String string4 = this.f.getResources().getString(R$string.IDS_hw_common_ui_dialog_confirm);
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.f);
        builder.b(string).e(string2).cyV_(string4, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.activity.WifiDevicePressureCalibrateResultGuideActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("WifiDevicePressureCalibrateResultGuideActivity", "button click stop");
                WifiDevicePressureCalibrateResultGuideActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyS_(string3, new View.OnClickListener() { // from class: pta
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WifiDevicePressureCalibrateResultGuideActivity.dtg_(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        this.c = a2;
        a2.setCancelable(false);
        this.c.show();
    }

    public static /* synthetic */ void dtg_(View view) {
        LogUtil.a("WifiDevicePressureCalibrateResultGuideActivity", "button click cancel");
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.e;
        if (noTitleCustomAlertDialog == null || !noTitleCustomAlertDialog.isShowing()) {
            String string = this.f.getResources().getString(R$string.IDS_device_wifi_pressure_calibrate_fail_msg);
            String string2 = this.f.getResources().getString(R$string.IDS_hw_common_ui_dialog_cancel);
            String string3 = this.f.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_again);
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.f);
            builder.e(string).czE_(string3, new View.OnClickListener() { // from class: pte
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WifiDevicePressureCalibrateResultGuideActivity.this.dti_(view);
                }
            }).czA_(string2, new View.OnClickListener() { // from class: ptf
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WifiDevicePressureCalibrateResultGuideActivity.this.dtj_(view);
                }
            });
            NoTitleCustomAlertDialog e = builder.e();
            this.e = e;
            e.setCancelable(false);
            this.e.show();
        }
    }

    public /* synthetic */ void dti_(View view) {
        LogUtil.a("WifiDevicePressureCalibrateResultGuideActivity", "button click retry");
        c();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dtj_(View view) {
        LogUtil.a("WifiDevicePressureCalibrateResultGuideActivity", "button click cancel");
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d() {
        this.i.removeMessages(1);
        this.i.removeMessages(2);
        this.i.sendEmptyMessageDelayed(1, 300000L);
        this.i.sendEmptyMessageDelayed(2, 5000L);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        c cVar = this.i;
        if (cVar != null) {
            cVar.removeMessages(1);
            this.i.removeMessages(2);
        }
        CustomTextAlertDialog customTextAlertDialog = this.c;
        if (customTextAlertDialog != null) {
            if (customTextAlertDialog.isShowing()) {
                this.c.dismiss();
            }
            this.c = null;
        }
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.e;
        if (noTitleCustomAlertDialog != null) {
            if (noTitleCustomAlertDialog.isShowing()) {
                this.e.dismiss();
            }
            this.e = null;
        }
        psm.e().w();
        ptt.d().u();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        a();
        Intent intent = new Intent(this.f, (Class<?>) WifiDevicePressureCalibrateResultActivity.class);
        intent.putExtra("health_wifi_device_userId", this.t);
        intent.putExtra("health_wifi_device_productId", this.l);
        intent.putExtra("pressure_is_have_datas", z);
        if (z) {
            intent.putExtra("mResultHrv", this.m);
        }
        gnm.aPB_(this.f, intent);
        finish();
    }

    private void c() {
        Long valueOf = Long.valueOf(this.h);
        String str = this.o;
        LogUtil.c("WifiDevicePressureCalibrateResultGuideActivity", "mCurrentTime = ", valueOf, "mResultHrvCalibDateTime = ", str, "(Long.valueOf(mResultHrvCalibDateTime) - mCurrentTime)  = ", Long.valueOf(CommonUtil.n(this.f, str) - this.h));
        if (((int) (CommonUtil.g(this.o) - this.h)) > 300000) {
            Intent intent = new Intent(this.f, (Class<?>) WifiDevicePressureCalibrateGuideActivity.class);
            intent.putExtra("pressure_is_have_datas", false);
            intent.putExtra("health_wifi_device_userId", this.t);
            intent.putExtra("health_device_type", "wifi_device");
            intent.putExtra("health_wifi_device_productId", this.l);
            gnm.aPB_(this.f, intent);
            finish();
            return;
        }
        LogUtil.b("WifiDevicePressureCalibrateResultGuideActivity", "retry call setPressureAdjustScore");
        f();
        this.i.sendEmptyMessage(2);
        this.i.sendEmptyMessageDelayed(1, 300000L);
    }

    private void f() {
        WifiDeviceControlDataModelReq wifiDeviceControlDataModelReq = new WifiDeviceControlDataModelReq();
        ArrayList arrayList = new ArrayList(16);
        DeviceServiceInfo deviceServiceInfo = new DeviceServiceInfo();
        HashMap hashMap = new HashMap(4);
        hashMap.put("id", String.valueOf(LoginInit.getInstance(this.f).getAccountInfo(1011)));
        cfi singleUserById = MultiUsersManager.INSTANCE.getSingleUserById(this.t);
        cfi mainUser = MultiUsersManager.INSTANCE.getMainUser();
        if (singleUserById == null) {
            LogUtil.c("WifiDevicePressureCalibrateResultGuideActivity", "user is null");
            return;
        }
        ctk e = ctq.e(this.l);
        if (e == null || TextUtils.isEmpty(e.b().a())) {
            LogUtil.b("WifiDevicePressureCalibrateResultGuideActivity", "device is null or deviceId is null");
            return;
        }
        wifiDeviceControlDataModelReq.setDevId(e.b().a());
        if (singleUserById.i().equals(mainUser.i())) {
            hashMap.put("uid", String.valueOf(0));
        } else {
            hashMap.put("uid", this.t);
        }
        hashMap.put(CommonConstant.KEY_GENDER, String.valueOf((int) cff.c(singleUserById.c())));
        hashMap.put("age", String.valueOf(singleUserById.a()));
        hashMap.put("height", String.valueOf(singleUserById.d()));
        hashMap.put("hrvparam", String.valueOf(this.b));
        if (cgs.d(cpa.h(e.getSerialNumber()))) {
            hashMap.put("month", String.valueOf(cgs.a(singleUserById.g())));
        }
        long currentTimeMillis = System.currentTimeMillis();
        this.f9870a = currentTimeMillis;
        LogUtil.c("WifiDevicePressureCalibrateResultGuideActivity", "mCalibrateStartTime = " + this.f9870a);
        hashMap.put("hrvCalibDateTime", String.valueOf(currentTimeMillis));
        hashMap.put("isDelete", String.valueOf(0));
        deviceServiceInfo.setData(hashMap);
        if ("0".equals(e(singleUserById))) {
            deviceServiceInfo.setSid(cts.b);
        } else {
            deviceServiceInfo.setSid(singleUserById.j());
        }
        DeviceServiceInfo a2 = a(deviceServiceInfo, e);
        LogUtil.a("WifiDevicePressureCalibrateResultGuideActivity", "Sid = ", a2.getSid());
        arrayList.add(a2);
        wifiDeviceControlDataModelReq.setDeviceServiceInfo(arrayList);
        b(wifiDeviceControlDataModelReq);
    }

    private DeviceServiceInfo a(DeviceServiceInfo deviceServiceInfo, ctk ctkVar) {
        String sid = deviceServiceInfo.getSid();
        if (sid.indexOf("_") >= 0) {
            if (ctkVar.b().k() != 2) {
                deviceServiceInfo.setSid(sid.substring(0, sid.indexOf("_")));
            }
        } else if (ctkVar.b().k() == 2) {
            deviceServiceInfo.setSid(sid + "_" + LoginInit.getInstance(this.f).getAccountInfo(1011));
        }
        return deviceServiceInfo;
    }

    private void b(WifiDeviceControlDataModelReq wifiDeviceControlDataModelReq) {
        jbs.a(this.f).d(wifiDeviceControlDataModelReq, new ICloudOperationResult() { // from class: psz
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                WifiDevicePressureCalibrateResultGuideActivity.d((CloudCommonReponse) obj, str, z);
            }
        });
    }

    public static /* synthetic */ void d(CloudCommonReponse cloudCommonReponse, String str, boolean z) {
        int i;
        String str2;
        if (z) {
            LogUtil.a("WifiDevicePressureCalibrateResultGuideActivity", "setPressureAdjustScore success ");
            return;
        }
        if (cloudCommonReponse != null) {
            i = cloudCommonReponse.getResultCode().intValue();
            str2 = cloudCommonReponse.getResultDesc();
        } else {
            i = Constants.CODE_UNKNOWN_ERROR;
            str2 = "unknown error";
        }
        LogUtil.a("WifiDevicePressureCalibrateResultGuideActivity", "clear error: ", Integer.valueOf(i), ", resultDesc: ", str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        WifiDeviceServiceInfoReq wifiDeviceServiceInfoReq = new WifiDeviceServiceInfoReq();
        ctk e = ctq.e(this.l);
        if (e == null || TextUtils.isEmpty(e.b().a())) {
            LogUtil.b("WifiDevicePressureCalibrateResultGuideActivity", "device is null or deviceId is null");
            return;
        }
        wifiDeviceServiceInfoReq.setDevId(e.b().a());
        wifiDeviceServiceInfoReq.setSid("devResult");
        jbs.a(this.f).b(wifiDeviceServiceInfoReq, new ICloudOperationResult() { // from class: ptc
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                WifiDevicePressureCalibrateResultGuideActivity.this.a((WifiDeviceServiceInfoRsp) obj, str, z);
            }
        });
    }

    public /* synthetic */ void a(WifiDeviceServiceInfoRsp wifiDeviceServiceInfoRsp, String str, boolean z) {
        Message obtain = Message.obtain();
        if (!z) {
            LogUtil.a("WifiDevicePressureCalibrateResultGuideActivity", "get device info fail");
            return;
        }
        if (wifiDeviceServiceInfoRsp != null && wifiDeviceServiceInfoRsp.getDeviceServiceInfo() != null && !wifiDeviceServiceInfoRsp.getDeviceServiceInfo().isEmpty()) {
            Iterator<DeviceServiceInfo> it = wifiDeviceServiceInfoRsp.getDeviceServiceInfo().iterator();
            while (it.hasNext()) {
                b(it.next());
                if (TextUtils.isEmpty(this.q) || "3".equals(this.n)) {
                    LogUtil.a("WifiDevicePressureCalibrateResultGuideActivity", "StartHrvCalibDateTime is null or mResultCode is invalid");
                    return;
                }
                if (!TextUtils.isEmpty(this.q) && this.f9870a - CommonUtil.g(this.q) != 0) {
                    LogUtil.a("WifiDevicePressureCalibrateResultGuideActivity", "mResultStartHrvCalibDateTime not match");
                    return;
                }
                if ("0".equals(this.n) && !TextUtils.isEmpty(this.m)) {
                    obtain.what = 4;
                } else if ("1".equals(this.n)) {
                    obtain.what = 3;
                } else if ("2".equals(this.n)) {
                    obtain.what = 5;
                } else {
                    LogUtil.a("WifiDevicePressureCalibrateResultGuideActivity", "mResultStartHrvCalibDateTime err");
                }
            }
        } else {
            LogUtil.a("WifiDevicePressureCalibrateResultGuideActivity", "get device info is empty");
        }
        this.i.sendMessage(obtain);
    }

    private void b(DeviceServiceInfo deviceServiceInfo) {
        Map<String, String> data = deviceServiceInfo.getData();
        if (data != null) {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                String key = entry.getKey();
                if (TextUtils.equals(key, "code")) {
                    this.n = entry.getValue();
                }
                if (TextUtils.equals(key, "type")) {
                    this.p = entry.getValue();
                }
                if (TextUtils.equals(key, "uid")) {
                    this.s = entry.getValue();
                }
                if (TextUtils.equals(key, "id")) {
                    this.k = entry.getValue();
                }
                if (TextUtils.equals(key, "hrv")) {
                    this.m = entry.getValue();
                }
                if (TextUtils.equals(key, "hrvCalibDateTime")) {
                    this.o = entry.getValue();
                }
                if (TextUtils.equals(key, "hrvStartCalibDateTime")) {
                    this.q = entry.getValue();
                }
                if (TextUtils.equals(key, "hrvUserInfo")) {
                    this.g = entry.getValue();
                }
                if (TextUtils.equals(key, "hrvUserInfo1")) {
                    b(entry.getValue());
                }
                if (TextUtils.equals(key, "hrvUserInfo2")) {
                    b(entry.getValue());
                }
                if (TextUtils.equals(key, "hrvUserInfo3")) {
                    b(entry.getValue());
                }
                if (TextUtils.equals(key, "hrvUserInfo4")) {
                    b(entry.getValue());
                }
            }
        }
    }

    private void a() {
        String valueOf = String.valueOf(LoginInit.getInstance(this.f).getAccountInfo(1011));
        if (TextUtils.isEmpty(valueOf)) {
            LogUtil.a("WifiDevicePressureCalibrateResultGuideActivity", "huid is empty");
            return;
        }
        if (TextUtils.isEmpty(this.g)) {
            LogUtil.a("WifiDevicePressureCalibrateResultGuideActivity", "main user hrvInfo is empty");
            return;
        }
        if (this.g.contains(":")) {
            int indexOf = this.g.indexOf(":");
            if (indexOf > 0 && valueOf.equals(this.g.substring(0, indexOf).trim())) {
                String str = this.g;
                a(str.substring(str.indexOf(":") + 1, this.g.length()));
                return;
            }
            List<String> list = this.j;
            if (list != null && list.size() > 0) {
                for (String str2 : this.j) {
                    if (!str2.contains(":")) {
                        LogUtil.a("WifiDevicePressureCalibrateResultGuideActivity", "sub user hrvInfo is format error");
                        return;
                    }
                    int indexOf2 = str2.indexOf(":");
                    if (indexOf2 > 0 && valueOf.equals(str2.substring(0, indexOf2).trim())) {
                        a(str2.substring(str2.indexOf(":") + 1, str2.length()));
                        return;
                    }
                }
                return;
            }
            LogUtil.a("WifiDevicePressureCalibrateResultGuideActivity", "sub user hrvInfo is empty");
            return;
        }
        LogUtil.a("WifiDevicePressureCalibrateResultGuideActivity", "mHrvUserInfo is not contains");
        a(this.g);
    }

    private void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        LogUtil.a("WifiDevicePressureCalibrateResultGuideActivity", "storage mHrvUserInfo = ", str);
        SharedPreferenceManager.e(this.f, String.valueOf(10000), "pressure_calibrate_hrv_userinfo_" + this.l, str, (StorageParams) null);
    }

    private String e(cfi cfiVar) {
        if (cfiVar == null || cfiVar.i() == null || "0".equals(cfiVar.i())) {
            return "0";
        }
        String i = MultiUsersManager.INSTANCE.getMainUser().i();
        return (i == null || !i.equals(cfiVar.i())) ? cfiVar.i() : "0";
    }

    private void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.j.add(str);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
