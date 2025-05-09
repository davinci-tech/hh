package com.huawei.health.nfcqrcodeaction;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.framework.servicemgr.Consumer;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.MainActivity;
import com.huawei.health.NfcEcgDeviceActivity;
import com.huawei.health.R;
import com.huawei.health.device.model.RecordAction;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.util.PermissionDialogHelper;
import com.huawei.health.nfcqrcodeaction.NfcAndQrCodeActionActivity;
import com.huawei.health.sportservice.SportDataOutputApi;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.health.suggestion.ui.fitness.helper.JumpConnectHelper;
import com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hihealth.dictionary.utils.ProductMapParseUtil;
import com.huawei.hms.network.embedded.u3;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl;
import com.huawei.hwadpaterhealthmgr.PluginOperationAdapterImpl;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.activity.IndoorEquipConnectedActivity;
import com.huawei.indoorequip.activity.IndoorEquipDisplayActivity;
import com.huawei.indoorequip.activity.IndoorEquipLandDisplayActivity;
import com.huawei.indoorequip.datastruct.QrCodeOrNfcInfo;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.operation.PluginOperation;
import defpackage.cej;
import defpackage.ceo;
import defpackage.cld;
import defpackage.cpa;
import defpackage.czs;
import defpackage.dcq;
import defpackage.dcz;
import defpackage.dks;
import defpackage.dum;
import defpackage.gso;
import defpackage.gtx;
import defpackage.gxf;
import defpackage.kzc;
import defpackage.lbv;
import defpackage.nrh;
import defpackage.ope;
import health.compact.a.AuthorizationUtils;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Objects;

/* loaded from: classes8.dex */
public class NfcAndQrCodeActionActivity extends Activity {

    /* renamed from: a, reason: collision with root package name */
    private dcz f2923a;
    private String c;
    private Context d;
    private Uri e = null;

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        LogUtil.c("Track_IDEQ_NfcAndQrCodeActionActivity", "onCreate()");
        super.onCreate(bundle);
        this.d = this;
        arS_(this, getIntent());
    }

    private void arS_(Context context, Intent intent) {
        if (intent != null) {
            this.e = intent.getData();
            if (!AuthorizationUtils.a(context)) {
                LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "not agree");
                c();
                return;
            }
            if (!c(MainActivity.class)) {
                if (LoginInit.getInstance(this.d).isBrowseMode()) {
                    LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "not exist Activity and is BrowseMode");
                    c();
                    return;
                } else {
                    LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "not exist Activity and is not BrowseMode");
                    arO_(context, intent);
                    return;
                }
            }
            if (c(MainActivity.class)) {
                LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "exist Activity");
                d();
                arO_(context, intent);
                return;
            }
            LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "startGymRunning other condition");
            return;
        }
        LogUtil.h("Track_IDEQ_NfcAndQrCodeActionActivity", "startGymRunning intent == null");
    }

    private void d() {
        if (BaseApplication.j()) {
            return;
        }
        c();
    }

    private void arO_(final Context context, final Intent intent) {
        LoginInit.getInstance(this.d).browsingToLogin(new IBaseResponseCallback() { // from class: eot
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                NfcAndQrCodeActionActivity.this.arU_(context, intent, i, obj);
            }
        }, "");
    }

    public /* synthetic */ void arU_(Context context, Intent intent, int i, Object obj) {
        if (i == 0) {
            if (((PluginSuggestion) Services.a("PluginFitnessAdvice", PluginSuggestion.class)) == null) {
                a();
            }
            a(context);
            int i2 = gso.e().i();
            boolean k = gso.e().k();
            String b = SharedPreferenceManager.b(this.d, Integer.toString(20002), "iscrash");
            if (k || i2 == 1 || i2 == 2) {
                LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "track module is running");
                if (gtx.c(gxf.d()).r() != 1) {
                    Intent intent2 = new Intent(context, (Class<?>) TrackMainMapActivity.class);
                    intent2.setFlags(AppRouterExtras.COLDSTART);
                    intent2.putExtra("isSelected", false);
                    LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "track module is not auto track, resume");
                    arR_(intent2);
                    return;
                }
                return;
            }
            if ("true".equals(b)) {
                LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "crash flag of track module is true");
                return;
            }
            if (gtx.c(gxf.d()).r() == 1) {
                LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "track module has auto track, break it");
                gtx.c(gxf.d()).cb();
            }
            LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "track module is not running");
            if (dum.d() != null) {
                LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "getInstance of Mediator success");
            }
            arQ_(intent);
            return;
        }
        LogUtil.h("Track_IDEQ_NfcAndQrCodeActionActivity", "it is not login: ", Integer.valueOf(i));
    }

    private void a() {
        Services.a("PluginFitnessAdvice", PluginSuggestion.class, BaseApplication.e(), new Consumer<PluginSuggestion>() { // from class: com.huawei.health.nfcqrcodeaction.NfcAndQrCodeActionActivity.1
            @Override // com.huawei.framework.servicemgr.Consumer
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void accept(PluginSuggestion pluginSuggestion) {
                if (pluginSuggestion != null) {
                    pluginSuggestion.init(NfcAndQrCodeActionActivity.this.d);
                } else {
                    LogUtil.h("Track_IDEQ_NfcAndQrCodeActionActivity", "obj is null.");
                }
            }
        }, true);
    }

    private void a(Context context) {
        gso.e().setAdapter(PluginHealthTrackAdapterImpl.getInstance(context));
        gso.e().init(context);
        PluginOperation.getInstance(context).setAdapter(PluginOperationAdapterImpl.getInstance(context));
        PluginOperation.getInstance(context).init(context);
        if (cej.e().getAdapter() == null) {
            cej.e().setAdapter(czs.a());
            cej.e().init(this.d);
        }
    }

    private void c() {
        Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
        if (launchIntentForPackage != null) {
            launchIntentForPackage.putExtra("schemeNfc", this.e);
            launchIntentForPackage.putExtra("needLogin", true);
            arR_(launchIntentForPackage);
            finish();
            return;
        }
        LogUtil.h("Track_IDEQ_NfcAndQrCodeActionActivity", "launchIntentForPackage is null");
    }

    private boolean c(Class<?> cls) {
        ComponentName resolveActivity = new Intent(this, cls).resolveActivity(getPackageManager());
        if (resolveActivity == null) {
            LogUtil.h("Track_IDEQ_NfcAndQrCodeActionActivity", "componentName is null");
            return false;
        }
        Object systemService = getSystemService("activity");
        if (!(systemService instanceof ActivityManager)) {
            return false;
        }
        for (ActivityManager.RunningTaskInfo runningTaskInfo : ((ActivityManager) Objects.requireNonNull((ActivityManager) systemService)).getRunningTasks(10)) {
            if (runningTaskInfo != null && Objects.equals(runningTaskInfo.baseActivity, resolveActivity)) {
                return true;
            }
        }
        return false;
    }

    private void arQ_(Intent intent) {
        LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "handleCommand(Intent intent)");
        if (intent == null) {
            LogUtil.b("Track_IDEQ_NfcAndQrCodeActionActivity", "handleCommand(Intent intent) intent == null");
            return;
        }
        if (!CommonUtil.bh() && !CommonUtil.bf()) {
            LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "not EMUI and newHonor! not allowed NFC start!");
            return;
        }
        if (!CommonUtil.bl()) {
            LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "not support this phone, return");
            return;
        }
        if ("android.nfc.action.NDEF_DISCOVERED".equals(intent.getAction())) {
            if (((SportDataOutputApi) Services.a("SportService", SportDataOutputApi.class)).isSportServiceRunning()) {
                LogUtil.h("Track_IDEQ_NfcAndQrCodeActionActivity", "SportService is Running!");
                finish();
                return;
            }
            String arP_ = arP_(intent);
            if (TextUtils.isEmpty(arP_)) {
                return;
            }
            LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "onCreate and get a valid payload, now will start Main Activity, payload = ", arP_);
            String b = lbv.b(arP_);
            if (!d(b)) {
                LogUtil.h("Track_IDEQ_NfcAndQrCodeActionActivity", "handleCommand course equipment is no match");
                return;
            } else {
                b(b, arP_);
                return;
            }
        }
        if (CommonConstant.ACTION.HWID_SCHEME_URL.equals(intent.getAction())) {
            LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "onCreate and start by url");
            Uri data = intent.getData();
            if (data == null) {
                LogUtil.b("Track_IDEQ_NfcAndQrCodeActionActivity", "uri is null");
                return;
            }
            String path = data.getPath();
            if (TextUtils.isEmpty(path) || !"/conn".equals(path)) {
                return;
            }
            arT_(data);
        }
    }

    private void b(String str, String str2) {
        if (Utils.o()) {
            LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "is oversea not support this device");
            nrh.c(this, R.string.ie_device_unsupported);
            return;
        }
        if (!TextUtils.isEmpty(str) && ope.c(str)) {
            if (!str2.contains(RecordAction.ACT_COST_TIME_TAG)) {
                str2 = str2.replace(";", "") + "&t=" + str + "&";
            }
            j(str2);
            return;
        }
        if (!TextUtils.isEmpty(str) && "70".equals(str)) {
            g(str2);
            return;
        }
        if (!TextUtils.isEmpty(str) && ope.a(str)) {
            b(str2);
            return;
        }
        if (!TextUtils.isEmpty(str) && ope.d(str)) {
            b(str2);
            return;
        }
        if (lbv.b(str2).equals("262")) {
            i(str2);
        } else if ("54".equals(str)) {
            b(str2);
        } else {
            LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "other nfc data");
        }
    }

    private void i(String str) {
        Intent intent = new Intent();
        intent.putExtra("PAYLOAD_FROM_NFC", str);
        SportDataOutputApi sportDataOutputApi = (SportDataOutputApi) Services.a("SportService", SportDataOutputApi.class);
        if (kzc.n().t()) {
            intent.setClass(this, IndoorEquipDisplayActivity.class);
            intent.putExtra("show tips key", true);
        } else if (kzc.n().x()) {
            intent.setClass(this, IndoorEquipLandDisplayActivity.class);
            intent.putExtra("show tips key", true);
        } else {
            if (sportDataOutputApi != null && sportDataOutputApi.getSportType() == 283 && sportDataOutputApi.isSportServiceRunning()) {
                LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "SportService is Running!");
                finish();
                return;
            }
            intent.setClass(this, DeviceMainActivity.class);
        }
        arR_(intent);
    }

    @Override // android.app.Activity
    protected void onResume() {
        LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "onResume then finish self");
        super.onResume();
        finish();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "onDestroy");
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "onNewIntent");
        super.onNewIntent(intent);
        arS_(this.d, getIntent());
    }

    @Override // android.app.Activity
    public void finish() {
        setIntent(null);
        super.finish();
    }

    /* JADX WARN: Removed duplicated region for block: B:30:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String arP_(android.content.Intent r5) {
        /*
            r4 = this;
            java.lang.String r0 = "swap NFC card by user and now will get payload."
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "Track_IDEQ_NfcAndQrCodeActionActivity"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            r0 = 0
            if (r5 == 0) goto L91
            java.lang.String r2 = "android.nfc.extra.NDEF_MESSAGES"
            android.os.Parcelable[] r5 = r5.getParcelableArrayExtra(r2)     // Catch: android.os.BadParcelableException -> L15 java.lang.ArrayIndexOutOfBoundsException -> L24
            goto L33
        L15:
            r5 = move-exception
            java.lang.String r2 = "BadParcelableException e = "
            java.lang.String r5 = r5.getMessage()
            java.lang.Object[] r5 = new java.lang.Object[]{r2, r5}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r5)
            goto L32
        L24:
            r5 = move-exception
            java.lang.String r2 = "getPayloadByNfc Exception"
            java.lang.String r5 = health.compact.a.LogAnonymous.b(r5)
            java.lang.Object[] r5 = new java.lang.Object[]{r2, r5}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r5)
        L32:
            r5 = r0
        L33:
            if (r5 == 0) goto L91
            int r2 = r5.length
            if (r2 <= 0) goto L91
            java.lang.String r2 = "Parcelable is not null, will get payload."
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r2)
            r2 = 0
            r5 = r5[r2]
            boolean r3 = r5 instanceof android.nfc.NdefMessage
            if (r3 != 0) goto L54
            java.lang.String r5 = "rawMessage[0] not instace of NdefMessage"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r5)
            java.lang.String r5 = ""
            return r5
        L54:
            android.nfc.NdefMessage r5 = (android.nfc.NdefMessage) r5
            android.nfc.NdefRecord[] r3 = r5.getRecords()     // Catch: java.io.UnsupportedEncodingException -> L6f
            int r3 = r3.length     // Catch: java.io.UnsupportedEncodingException -> L6f
            if (r3 <= 0) goto L78
            java.lang.String r3 = new java.lang.String     // Catch: java.io.UnsupportedEncodingException -> L6f
            android.nfc.NdefRecord[] r5 = r5.getRecords()     // Catch: java.io.UnsupportedEncodingException -> L6f
            r5 = r5[r2]     // Catch: java.io.UnsupportedEncodingException -> L6f
            byte[] r5 = r5.getPayload()     // Catch: java.io.UnsupportedEncodingException -> L6f
            java.lang.String r2 = "UTF-8"
            r3.<init>(r5, r2)     // Catch: java.io.UnsupportedEncodingException -> L6f
            goto L79
        L6f:
            java.lang.String r5 = "getPayloadByNfc,UnsupportedEncodingException"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r5)
        L78:
            r3 = r0
        L79:
            boolean r5 = android.text.TextUtils.isEmpty(r3)
            if (r5 != 0) goto L91
            int r5 = r3.length()
            r2 = 100
            if (r5 > r2) goto L91
            java.lang.String r5 = "get a payload of NFC card"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r5)
            r0 = r3
        L91:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.nfcqrcodeaction.NfcAndQrCodeActionActivity.arP_(android.content.Intent):java.lang.String");
    }

    private void b(String str) {
        LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "startDeviceMainActByNfc()");
        String a2 = dks.a("brd", str);
        String a3 = dks.a("ble", str);
        String a4 = dks.a(u3.m, str);
        String b = lbv.b(str);
        if ("68".equals(b)) {
            if (!TextUtils.isEmpty(a2) && !TextUtils.isEmpty(a3)) {
                LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "bloodPressure payload of NFC is valid and now will start DeviceMainActivity");
                h(str);
                return;
            } else {
                LogUtil.h("Track_IDEQ_NfcAndQrCodeActionActivity", "bloodPressure payload of NFC is invalid");
                return;
            }
        }
        if ("69".equals(b)) {
            e(a3, a4, str);
            return;
        }
        if (c(b)) {
            if (!TextUtils.isEmpty(a4)) {
                LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "payload of NFC is valid and now will start DeviceMainActivity");
                h(str);
                return;
            } else {
                LogUtil.h("Track_IDEQ_NfcAndQrCodeActionActivity", "payload of NFC is invalid");
                return;
            }
        }
        c(str, a3, a4, b);
    }

    private void g(String str) {
        Intent intent = new Intent();
        intent.putExtra("PAYLOAD_FROM_NFC", str);
        intent.setClass(this, NfcEcgDeviceActivity.class);
        arR_(intent);
    }

    private void c(String str, String str2, String str3, String str4) {
        if ("54".equals(str4)) {
            if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
                LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "scale payload of NFC is valid and now will start PayloadActivity");
                b(str, str2, str3);
                return;
            } else {
                LogUtil.h("Track_IDEQ_NfcAndQrCodeActionActivity", "hagrid scale payload of NFC is invalid");
                return;
            }
        }
        if ("261".equals(str4) && "28L1".equals(str3)) {
            LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "rower payload of NFC is valid and now will start DeviceMainActivity");
            h(str);
        } else if (e(str4)) {
            h(str);
        } else {
            LogUtil.h("Track_IDEQ_NfcAndQrCodeActionActivity", "payload of type is invalid when type is small pillow");
        }
    }

    private boolean e(String str) {
        return BleConstants.SPORT_TYPE_BIKE.equals(str) || "261".equals(str) || "260".equals(str) || "31".equals(str);
    }

    private void e(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            ProductMapParseUtil.b(this.d);
            if (e(str, ProductMap.d(str2))) {
                if (!PermissionDialogHelper.Vy_(this)) {
                    ReleaseLogUtil.e("Track_IDEQ_NfcAndQrCodeActionActivity", "handleGlucosemeterIndex go to H5 not scan permission");
                    nrh.b(this, R.string._2130846464_res_0x7f022300);
                    finish();
                    return;
                }
                e(str, str3);
                return;
            }
            LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "glucosemeter payload of NFC is valid and now will start DeviceMainActivity");
            h(str3);
            return;
        }
        LogUtil.h("Track_IDEQ_NfcAndQrCodeActionActivity", "glucosemeter payload of NFC is invalid");
    }

    private void b(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            ProductMapParseUtil.b(this.d);
            ProductMapInfo d = ProductMap.d(str3);
            if (d == null) {
                return;
            }
            LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "body fat scale device of NFC is valid");
            if (cpa.ad(d.h())) {
                LogUtil.c("Track_IDEQ_NfcAndQrCodeActionActivity", "enter huawei or honor body fat scale device fragment");
                d(str, d.h(), str2, str3);
                return;
            } else if (e(str2, d)) {
                e(str2, str);
                return;
            } else {
                LogUtil.c("Track_IDEQ_NfcAndQrCodeActionActivity", "enter other body fat scale device fragment");
                h(str);
                return;
            }
        }
        LogUtil.h("Track_IDEQ_NfcAndQrCodeActionActivity", "body fat scale payload of NFC is invalid");
    }

    private void d(String str, String str2, String str3, String str4) {
        boolean h = ceo.d().h(str3);
        boolean b = cld.HJ_(this, str4, str3).b();
        LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "startPayloadActivity hagrid scale isBondedDevice,", Boolean.valueOf(h), ",isDeviceConnect,", Boolean.valueOf(b));
        if (h && b) {
            finish();
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("PAYLOAD_FROM_NFC", str);
        intent.putExtra("productId", str2);
        intent.putExtra("uniqueId", str3);
        intent.putExtra("isBondedDevice", h);
        intent.putExtra("isNfcConnect", true);
        LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "put isNfcConnect is true");
        intent.setClass(this, DeviceMainActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean e(String str, ProductMapInfo productMapInfo) {
        if (productMapInfo == null) {
            return false;
        }
        this.c = productMapInfo.h();
        dcz d = ResourceManager.e().d(this.c);
        this.f2923a = d;
        if (d == null) {
            return false;
        }
        boolean h = ceo.d().h(str);
        LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "isBondedDevice:", Boolean.valueOf(h));
        return BleConstants.BLE_THIRD_DEVICE_H5.equals(this.f2923a.m().d()) && h;
    }

    private void e(String str, String str2) {
        if ("1".equals(this.f2923a.j())) {
            dks.d(this, this.f2923a, this.c, str);
            return;
        }
        Intent Wx_ = dks.Wx_(this.f2923a, this.c, str);
        if (Wx_ != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(dcq.b().c(this.c));
            sb.append("?enterType=");
            sb.append(TextUtils.isEmpty(str2) ? "QRCODE" : "NFC");
            Wx_.putExtra("url", sb.toString());
            if (!TextUtils.isEmpty(WebViewActivity.getProductId())) {
                LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "WebViewActivity productId is not empty");
                Wx_.setFlags(335544320);
            }
            startActivity(Wx_);
        }
    }

    private void h(String str) {
        Intent intent = new Intent();
        intent.putExtra("PAYLOAD_FROM_NFC", str);
        intent.putExtra("ENTER_TYPE", "NFC");
        intent.setClass(this, DeviceMainActivity.class);
        arR_(intent);
    }

    private void j(String str) {
        if (JumpConnectHelper.c().e()) {
            nrh.c(BaseApplication.e(), BaseApplication.e().getResources().getString(R.string._2130845320_res_0x7f021e88));
            LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "LongCoachActivityRunning");
            return;
        }
        if (a(str)) {
            LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "payload of NFC is valid and now will start IndoorEquipRunningActivity");
            Intent intent = new Intent();
            intent.putExtra("PAYLOAD_FROM_NFC", str);
            if (kzc.n().t()) {
                intent.setClass(this, IndoorEquipDisplayActivity.class);
                intent.putExtra("show tips key", true);
                arR_(intent);
            } else if (kzc.n().x()) {
                intent.setClass(this, IndoorEquipLandDisplayActivity.class);
                intent.putExtra("show tips key", true);
                arR_(intent);
            } else {
                int a2 = JumpConnectHelper.c().a();
                int d = JumpConnectHelper.c().d();
                if (a2 != 0 && d != 0) {
                    LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "startIndoorEquipRunningActByNfc start courseEquipment");
                    intent.putExtra("KEY_INTENT_COURSE_ENTRANCE", 1);
                    intent.putExtra("KEY_INTENT_EQUIPMENT_TYPE", a2);
                    intent.setClass(this, IndoorEquipConnectedActivity.class);
                    arR_(intent);
                } else {
                    b(str);
                }
            }
            if (str.contains("&tvn=")) {
                String c = lbv.c(str);
                if (TextUtils.isEmpty(c)) {
                    return;
                }
                Intent intent2 = new Intent("com.huawei.health.BROADCAST_INTENT_CONNECT_TV");
                intent2.putExtra("KEY_OF_TV_DEVICE_NAME", c);
                LocalBroadcastManager.getInstance(this.d).sendBroadcast(intent2);
                return;
            }
            return;
        }
        LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "payload of NFC is invalid");
    }

    private boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "payload of NFC is empty");
            return false;
        }
        QrCodeOrNfcInfo analysisQrCodeOrNfc = QrCodeOrNfcInfo.analysisQrCodeOrNfc(str);
        if (analysisQrCodeOrNfc == null) {
            LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "payload of NFC parse qrCodeOrNfcInfo is null");
            return false;
        }
        String btMac = analysisQrCodeOrNfc.getBtMac();
        String btName = analysisQrCodeOrNfc.getBtName();
        String b = lbv.b(str);
        if (!str.contains("proto=") && !str.contains("ftmp=")) {
            LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "payload of NFC Protocol is null");
            return false;
        }
        boolean isEmpty = TextUtils.isEmpty(b);
        boolean z = (TextUtils.isEmpty(btMac) && TextUtils.isEmpty(btName)) ? false : true;
        if ((!isEmpty) && z) {
            return true;
        }
        LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "payload of NFC is invalid");
        return false;
    }

    private void arT_(Uri uri) {
        String queryParameter = uri.getQueryParameter("proto");
        String queryParameter2 = uri.getQueryParameter("ble");
        String substring = (queryParameter2 == null || queryParameter2.length() <= 7) ? queryParameter2 : queryParameter2.substring(6);
        String queryParameter3 = uri.getQueryParameter("blen");
        LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "proto of url = ", queryParameter, ",ble = ", substring, ", blen", queryParameter3);
        LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "now will start indoor equip activity");
        Intent intent = new Intent();
        if (TextUtils.isEmpty(queryParameter)) {
            queryParameter = "";
        }
        intent.putExtra("PROTOCOL_FROM_QRCODE", queryParameter);
        if (TextUtils.isEmpty(queryParameter2)) {
            queryParameter2 = "";
        }
        intent.putExtra("BLE_FROM_QRCODE", queryParameter2);
        if (TextUtils.isEmpty(queryParameter3)) {
            queryParameter3 = "";
        }
        intent.putExtra("BLENAME_FROM_QRCODE", queryParameter3);
        String queryParameter4 = uri.getQueryParameter("tvn");
        intent.putExtra("TVNAME_FROM_QRCODE", TextUtils.isEmpty(queryParameter4) ? "" : queryParameter4);
        if (kzc.n().t()) {
            intent.setClass(this, IndoorEquipDisplayActivity.class);
            intent.putExtra("show tips key", true);
        } else if (kzc.n().x()) {
            intent.setClass(this, IndoorEquipLandDisplayActivity.class);
            intent.putExtra("show tips key", true);
        } else {
            intent.setClass(this, IndoorEquipConnectedActivity.class);
        }
        arR_(intent);
        if (TextUtils.isEmpty(queryParameter4)) {
            return;
        }
        Intent intent2 = new Intent("com.huawei.health.BROADCAST_INTENT_CONNECT_TV");
        intent2.putExtra("KEY_OF_TV_DEVICE_NAME", queryParameter4);
        LocalBroadcastManager.getInstance(this.d).sendBroadcast(intent2);
    }

    private void arR_(Intent intent) {
        try {
            startActivity(intent);
        } catch (BadParcelableException unused) {
            LogUtil.a("Track_IDEQ_NfcAndQrCodeActionActivity", "onStartActivity BadParcelableException");
        }
    }

    private boolean d(String str) {
        int a2 = JumpConnectHelper.c().a();
        if (!TextUtils.isEmpty(str) && a2 != 0) {
            if (!ope.c(str)) {
                LogUtil.h("Track_IDEQ_NfcAndQrCodeActionActivity", "equipmentTypeValid course equipment nfc equipment is no ecological equipment");
                JumpConnectHelper.c().b().showEquipmentMatchTip(a2);
                finish();
                return false;
            }
            if ((a2 == 1 && !str.equals("31")) || (a2 == 2 && !str.equals(BleConstants.SPORT_TYPE_BIKE))) {
                LogUtil.h("Track_IDEQ_NfcAndQrCodeActionActivity", "equipmentTypeValid course equipment nfc equipment type no match");
                JumpConnectHelper.c().b().showEquipmentMatchTip(a2);
                finish();
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private boolean c(String str) {
        char c;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 49778) {
            if (str.equals("266")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 49780) {
            switch (hashCode) {
                case 49803:
                    if (str.equals("270")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 49804:
                    if (str.equals("271")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 49805:
                    if (str.equals("272")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 49806:
                    if (str.equals("273")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
        } else {
            if (str.equals(BleConstants.TYPE_FASCIA_GUN_INDEX)) {
                c = 1;
            }
            c = 65535;
        }
        return c == 0 || c == 1 || c == 2 || c == 3 || c == 4 || c == 5;
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
