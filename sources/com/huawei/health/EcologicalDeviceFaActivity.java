package com.huawei.health;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.alipay.sdk.m.p.e;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.EcologicalDeviceFaActivity;
import com.huawei.health.ecologydevice.util.FAUtil;
import com.huawei.healthcloud.plugintrack.ui.activity.TrackDetailActivity;
import com.huawei.hwauthutil.HsfSignValidator;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.activity.IndoorEquipConnectedActivity;
import com.huawei.indoorequip.datastruct.QrCodeOrNfcInfo;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.main.stories.health.activity.healthdata.KnitBloodPressureActivity;
import com.huawei.ui.main.stories.history.SportHistoryActivity;
import defpackage.dds;
import defpackage.dij;
import defpackage.dlb;
import defpackage.gnm;
import defpackage.gso;
import defpackage.hps;
import defpackage.jdx;
import defpackage.knu;
import defpackage.kzc;
import defpackage.moj;
import defpackage.sdm;
import health.compact.a.AuthorizationUtils;
import health.compact.a.Utils;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* loaded from: classes8.dex */
public class EcologicalDeviceFaActivity extends Activity {
    private static final Set<String> c;

    /* renamed from: a, reason: collision with root package name */
    private Map<String, String> f2168a = new HashMap();
    private String b = "com.huawei.ohos.health.device";
    private Context d;
    private String e;
    private String i;

    static {
        HashSet hashSet = new HashSet();
        c = hashSet;
        hashSet.add("B2881B2EE3D4EFA03342AE07DAFC0466B63EDE959EC2E2F58C05A54266F45748");
        hashSet.add("3775339A90423293F6F60055FE4484130CF90E5D4A83E9B8713EDB97F5A094B0");
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("EcologicalDeviceFaActivity", "onCreate");
        this.d = this;
        if (!d()) {
            finish();
            return;
        }
        if (!AuthorizationUtils.a(this.d)) {
            LogUtil.a("EcologicalDeviceFaActivity", "privacy not agree");
            f();
            finish();
            return;
        }
        if (Utils.i() && !Utils.f()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: bxi
                @Override // java.lang.Runnable
                public final void run() {
                    FAUtil.c(new FAUtil.ResultCallback() { // from class: bxj
                        @Override // com.huawei.health.ecologydevice.util.FAUtil.ResultCallback
                        public final void onResult(boolean z) {
                            LogUtil.a("EcologicalDeviceFaActivity", "download and bind device:" + z);
                        }
                    });
                }
            });
        }
        if (kzc.n().t() || kzc.n().x()) {
            Toast.makeText(this, this.d.getString(R.string._2130840308_res_0x7f020af4), 0).show();
            finish();
        } else {
            a();
            b();
            j();
        }
    }

    private void f() {
        Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
        if (launchIntentForPackage != null) {
            startActivity(launchIntentForPackage);
        } else {
            LogUtil.h("EcologicalDeviceFaActivity", "launchIntentForPackage is null");
        }
    }

    private boolean d() {
        String str;
        if (getIntent() == null) {
            return false;
        }
        try {
            str = getIntent().getStringExtra("packageName");
        } catch (Exception unused) {
            LogUtil.b("EcologicalDeviceFaActivity", "checkCallingPermission Exception.");
            str = "";
        }
        if (!this.b.equals(str)) {
            LogUtil.h("EcologicalDeviceFaActivity", "packageName is null");
            return false;
        }
        String e = HsfSignValidator.e(this, str);
        if (TextUtils.isEmpty(e)) {
            LogUtil.h("EcologicalDeviceFaActivity", "fingerprint is null");
            return false;
        }
        return c.contains(e);
    }

    private void a() {
        LogUtil.a("EcologicalDeviceFaActivity", "parseIntent");
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("EcologicalDeviceFaActivity", "intent is null");
            finish();
        } else {
            this.e = intent.getAction();
            if (intent.hasExtra(e.n)) {
                this.f2168a = moj.a(intent.getStringExtra(e.n));
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x007d, code lost:
    
        if (r2.equals("startSport") == false) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void b() {
        /*
            r8 = this;
            java.lang.String r0 = "jumpToActivity"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "EcologicalDeviceFaActivity"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            java.lang.String r0 = r8.e
            if (r0 != 0) goto L13
            java.lang.String r0 = ""
            r8.e = r0
        L13:
            java.util.Map<java.lang.String, java.lang.String> r0 = r8.f2168a
            java.lang.String r2 = "closeBLEConnection"
            java.lang.Object r0 = r0.get(r2)
            java.lang.String r0 = (java.lang.String) r0
            java.util.Map<java.lang.String, java.lang.String> r2 = r8.f2168a
            java.lang.String r3 = "payload"
            java.lang.Object r2 = r2.get(r3)
            java.lang.String r2 = (java.lang.String) r2
            r8.i = r2
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            r3 = 0
            r4 = 1
            if (r2 != 0) goto L39
            boolean r0 = java.lang.Boolean.parseBoolean(r0)
            if (r0 == 0) goto L39
            r0 = r4
            goto L3a
        L39:
            r0 = r3
        L3a:
            java.lang.String r2 = "closeBLEConnection:"
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r0)
            java.lang.Object[] r2 = new java.lang.Object[]{r2, r5}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r2)
            java.lang.String r2 = r8.e
            r2.hashCode()
            int r5 = r2.hashCode()
            r6 = 3
            r7 = 2
            switch(r5) {
                case -1584340942: goto L77;
                case -229915964: goto L6c;
                case 811700604: goto L61;
                case 1212015996: goto L56;
                default: goto L55;
            }
        L55:
            goto L7f
        L56:
            java.lang.String r3 = "trackRecord"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L5f
            goto L7f
        L5f:
            r3 = r6
            goto L80
        L61:
            java.lang.String r3 = "trackDetail"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L6a
            goto L7f
        L6a:
            r3 = r7
            goto L80
        L6c:
            java.lang.String r3 = "blood_pressure_page"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L75
            goto L7f
        L75:
            r3 = r4
            goto L80
        L77:
            java.lang.String r5 = "startSport"
            boolean r2 = r2.equals(r5)
            if (r2 != 0) goto L80
        L7f:
            r3 = -1
        L80:
            if (r3 == 0) goto La1
            if (r3 == r4) goto L9d
            if (r3 == r7) goto L99
            if (r3 == r6) goto L95
            java.lang.String r0 = "jumpToActivity default patch"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            r8.finish()
            goto La4
        L95:
            r8.d(r0)
            goto La4
        L99:
            r8.a(r0)
            goto La4
        L9d:
            r8.c(r0)
            goto La4
        La1:
            r8.b(r0)
        La4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.EcologicalDeviceFaActivity.b():void");
    }

    private void j() {
        String str = this.f2168a.get("sn");
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("EcologicalDeviceFaActivity", "sn is null");
        } else {
            new dlb.a().d(str).c().a();
        }
    }

    private void a(final boolean z) {
        LogUtil.a("EcologicalDeviceFaActivity", "queryAndJumpToTrackDetailActivity");
        g();
        try {
            hps.a(Long.parseLong((String) Objects.requireNonNull(this.f2168a.get("startTime"))), Long.parseLong((String) Objects.requireNonNull(this.f2168a.get("endTime"))), new IBaseResponseCallback() { // from class: bxf
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    EcologicalDeviceFaActivity.this.d(z, i, obj);
                }
            });
        } catch (NumberFormatException unused) {
        }
    }

    public /* synthetic */ void d(boolean z, int i, Object obj) {
        LogUtil.a("EcologicalDeviceFaActivity", "onResponse code: " + i);
        if (i == 0 && (obj instanceof knu)) {
            knu knuVar = (knu) obj;
            gso.e().init(this.d);
            LogUtil.a("EcologicalDeviceFaActivity", "JumpToTrackDetailActivity");
            Bundle aTo_ = gso.e().aTo_(knuVar.d(), knuVar.b(), Collections.EMPTY_LIST, false, false);
            aTo_.putBoolean("closeBLEConnection", z);
            aTo_.putBoolean("moveTaskToBack", true);
            Intent intent = new Intent(this, (Class<?>) TrackDetailActivity.class);
            intent.putExtras(aTo_);
            intent.addFlags(268435456);
            if (z) {
                startActivityForResult(intent, 1001);
            } else {
                gnm.aPB_(this.d, intent);
                finish();
            }
        }
    }

    private void g() {
        String str = this.f2168a.get("deviceType");
        String str2 = this.f2168a.get(BleConstants.SPORT_TYPE);
        LogUtil.a("EcologicalDeviceFaActivity", "sportType = ", str2);
        if (!TextUtils.isEmpty(str) && TextUtils.equals(str2, String.valueOf(283))) {
            try {
                sdm.c(sdm.a(Integer.parseInt(str)));
                return;
            } catch (NumberFormatException e) {
                LogUtil.b("EcologicalDeviceFaActivity", "deviceType format error msg is ", e.getMessage());
                return;
            }
        }
        LogUtil.h("EcologicalDeviceFaActivity", "sportType is not equals SPORT_TYPE_ROPE_SKIPPING or deviceType is null");
    }

    private void d(boolean z) {
        int i;
        Intent intent = new Intent(this, (Class<?>) SportHistoryActivity.class);
        try {
            i = Integer.parseInt(this.f2168a.get(BleConstants.SPORT_TYPE));
        } catch (NumberFormatException unused) {
            LogUtil.h("EcologicalDeviceFaActivity", "sportType is illegal");
            i = 0;
        }
        intent.putExtra(BleConstants.SPORT_TYPE, i);
        if (z) {
            intent.putExtra("ExitApp", true);
            intent.addFlags(335577088);
            startActivityForResult(intent, 1001);
        } else {
            gnm.aPB_(this.d, intent);
            finish();
        }
    }

    private void c(boolean z) {
        Intent intent = new Intent();
        intent.setClass(this, KnitBloodPressureActivity.class);
        if (z) {
            intent.addFlags(AppRouterExtras.COLDSTART);
            startActivityForResult(intent, 1001);
        } else {
            gnm.aPB_(this.d, intent);
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        LogUtil.a("EcologicalDeviceFaActivity", "onPause");
        String str = this.f2168a.get("closeBLEConnection");
        LogUtil.a("EcologicalDeviceFaActivity", "enter connection:" + str);
        if (TextUtils.isEmpty(str) || !Boolean.parseBoolean(str)) {
            finish();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void b(boolean z) {
        char c2;
        LogUtil.a("EcologicalDeviceFaActivity", "enter jumpToSportActivity");
        String str = this.f2168a.get(BleConstants.SPORT_TYPE);
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(this.i)) {
            LogUtil.a("EcologicalDeviceFaActivity", "sportType:", str, "  payload:", this.i);
            Intent intent = new Intent();
            intent.putExtra("PAYLOAD_FROM_NFC", this.i);
            str.hashCode();
            int hashCode = str.hashCode();
            if (hashCode == 1630) {
                if (str.equals("31")) {
                    c2 = 0;
                }
                c2 = 65535;
            } else if (hashCode != 49750) {
                switch (hashCode) {
                    case 49772:
                        if (str.equals("260")) {
                            c2 = 2;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 49773:
                        if (str.equals("261")) {
                            c2 = 3;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 49774:
                        if (str.equals("262")) {
                            c2 = 4;
                            break;
                        }
                        c2 = 65535;
                        break;
                    default:
                        c2 = 65535;
                        break;
                }
            } else {
                if (str.equals(BleConstants.SPORT_TYPE_BIKE)) {
                    c2 = 1;
                }
                c2 = 65535;
            }
            if (c2 == 0 || c2 == 1 || c2 == 2 || c2 == 3) {
                intent.putExtra("isFromHarmonyFa", true);
                intent.setClass(this, IndoorEquipConnectedActivity.class);
            } else if (c2 == 4) {
                String str2 = this.f2168a.get("uniqueId");
                if (!TextUtils.isEmpty(str2)) {
                    dds.c().g();
                    dds.c().b((String) Objects.requireNonNull(this.f2168a.get("productId")));
                    dds.c().a(true, str2);
                } else {
                    LogUtil.b("EcologicalDeviceFaActivity", "query device mac is error");
                }
                intent.setClassName(this, "com.huawei.health.MainActivity");
                intent.setFlags(131072);
                intent.putExtra(BleConstants.SPORT_TYPE, 283);
                intent.putExtra("mLaunchSource", 11);
                intent.putExtra("isToSportTab", true);
            } else {
                intent.setClass(this, MainActivity.class);
            }
            if (z) {
                startActivityForResult(intent, 1001);
                return;
            } else {
                startActivity(intent);
                finish();
                return;
            }
        }
        finish();
    }

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a("EcologicalDeviceFaActivity", "onActivityResult:", Integer.valueOf(i), ",resultCode:", Integer.valueOf(i2));
        if (i == 1001) {
            if (TextUtils.isEmpty(this.i)) {
                LogUtil.h("EcologicalDeviceFaActivity", "mPayLoad is empty，disconnect fail");
                finish();
            } else {
                jdx.b(new Runnable() { // from class: bxg
                    @Override // java.lang.Runnable
                    public final void run() {
                        EcologicalDeviceFaActivity.this.e();
                    }
                });
            }
        }
    }

    public /* synthetic */ void e() {
        LogUtil.a("EcologicalDeviceFaActivity", "disconnect bluetooth:", this.i);
        String btMac = QrCodeOrNfcInfo.analysisQrCodeOrNfc(this.i).getBtMac();
        if (TextUtils.isEmpty(btMac)) {
            LogUtil.h("EcologicalDeviceFaActivity", "mac is empty， disconnect fail");
        } else {
            LogUtil.h("EcologicalDeviceFaActivity", "disconnect result :" + dij.c(btMac));
        }
        finish();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
