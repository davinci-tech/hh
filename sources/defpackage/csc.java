package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.huawei.health.R;
import com.huawei.health.device.manager.DeviceCloudSharePreferencesManager;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.unite.DeviceServiceInfo;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceServiceInfoReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceServiceInfoRsp;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes3.dex */
public class csc {
    private static volatile csc b;
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private CustomAlertDialog f11429a = null;

    private csc() {
    }

    public static csc d() {
        if (b == null) {
            synchronized (e) {
                if (b == null) {
                    b = new csc();
                }
            }
        }
        return b;
    }

    public void d(final String str) {
        if (TextUtils.isEmpty(str)) {
            cpw.c(false, "WifiOtaUpdateConstants", "autoCheckVersion devId is null");
            return;
        }
        if (!i(str)) {
            cpw.a(false, "WifiOtaUpdateConstants", " no need check ota version.");
            return;
        }
        WifiDeviceServiceInfoReq wifiDeviceServiceInfoReq = new WifiDeviceServiceInfoReq();
        wifiDeviceServiceInfoReq.setDevId(str);
        wifiDeviceServiceInfoReq.setSid("devOtaInfo");
        jbs.a(BaseApplication.getContext()).b(wifiDeviceServiceInfoReq, new ICloudOperationResult<WifiDeviceServiceInfoRsp>() { // from class: csc.4
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void operationResult(WifiDeviceServiceInfoRsp wifiDeviceServiceInfoRsp, String str2, boolean z) {
                DeviceServiceInfo next;
                Map<String, String> data;
                if (z) {
                    if (wifiDeviceServiceInfoRsp == null || wifiDeviceServiceInfoRsp.getDeviceServiceInfo() == null || wifiDeviceServiceInfoRsp.getDeviceServiceInfo().isEmpty()) {
                        cpw.c(false, "WifiOtaUpdateConstants", "get device info is empty.");
                        return;
                    }
                    Iterator<DeviceServiceInfo> it = wifiDeviceServiceInfoRsp.getDeviceServiceInfo().iterator();
                    while (it.hasNext() && ((data = (next = it.next()).getData()) == null || !csc.this.b(str, next, data))) {
                        cpw.e(false, "WifiOtaUpdateConstants", "getDeviceStatus map is null");
                    }
                    cpw.a(false, "WifiOtaUpdateConstants", "res: ", wifiDeviceServiceInfoRsp, ", text: ", str2);
                    return;
                }
                cpw.c(false, "WifiOtaUpdateConstants", "get device status fail. text: ", str2, ", res: ", wifiDeviceServiceInfoRsp);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(String str, DeviceServiceInfo deviceServiceInfo, Map<String, String> map) {
        String str2;
        String str3 = map.get("fwNewVer");
        String str4 = map.get("fwCurVer");
        String str5 = map.get("releaseNote");
        String str6 = map.get("status");
        if (csf.g(str) && map.containsKey("upgrade_auto")) {
            String str7 = map.get("upgrade_auto");
            LogUtil.a("WifiOtaUpdateConstants", "getOtaStatus Hagrid autoUpgradeStatus:", str7);
            d(str, str7);
        }
        cpw.a(false, "WifiOtaUpdateConstants", "sid: ", deviceServiceInfo.getSid(), ", note: ", str5, ", newVer: ", str3, ", nowVersion: ", str4, ", status: ", str6);
        boolean z = (TextUtils.isEmpty(str4) || str3.equals(str4)) ? false : true;
        if (TextUtils.isEmpty(str3) || !z || str6 == null || !"1".equals(str6)) {
            return false;
        }
        cpw.c(false, "WifiOtaUpdateConstants", "exist new version nowVersion: ", str4, ", new ver: ", str3);
        Bundle bundle = new Bundle();
        bundle.putString("version", str3);
        bundle.putString("cer_version", str4);
        bundle.putString("update_nodes", str5);
        ctk c = ctq.c(str);
        if (c != null) {
            str2 = c.getProductId();
            bundle.putString("productId", str2);
        } else {
            LogUtil.h("WifiOtaUpdateConstants", "get wifi device error maybe because the devId is null ");
            str2 = "";
        }
        bundle.putString("devId", str);
        bundle.putBoolean("is_exist_new_version", true);
        if (cpa.x(str2)) {
            LogUtil.a("WifiOtaUpdateConstants", "checkVersion : isSupportUploadOtaInfoDualModeProduct false");
            return true;
        }
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setAction("com.huawei.health.action.ACTION_WIFI_OTA_UPDATE_ACTION");
        BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
        c(str, true);
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0063, code lost:
    
        if (r3 < r5) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean i(java.lang.String r11) {
        /*
            r10 = this;
            android.content.Context r0 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            boolean r0 = defpackage.ctv.d(r0)
            java.lang.String r1 = "WifiOtaUpdateConstants"
            r2 = 0
            if (r0 != 0) goto L17
            java.lang.String r0 = "network is off. no check version."
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            defpackage.cpw.c(r2, r1, r0)
            goto L66
        L17:
            java.lang.String r0 = r10.j(r11)
            java.lang.String r3 = "last check version time: "
            java.lang.Object[] r3 = new java.lang.Object[]{r3, r0}
            defpackage.cpw.c(r2, r1, r3)
            boolean r3 = android.text.TextUtils.isEmpty(r0)
            if (r3 == 0) goto L2b
            goto L65
        L2b:
            long r3 = health.compact.a.CommonUtil.g(r0)
            java.util.Date r0 = new java.util.Date
            long r5 = java.lang.System.currentTimeMillis()
            r0.<init>(r5)
            java.util.Calendar r5 = java.util.Calendar.getInstance()
            r5.setTime(r0)
            r0 = 5
            int r6 = r5.get(r0)
            int r6 = r6 + (-7)
            r5.set(r0, r6)
            long r5 = r5.getTimeInMillis()
            java.lang.Long r0 = java.lang.Long.valueOf(r5)
            java.lang.String r7 = ", time: "
            java.lang.Long r8 = java.lang.Long.valueOf(r3)
            java.lang.String r9 = "seven days ago time: "
            java.lang.Object[] r0 = new java.lang.Object[]{r9, r0, r7, r8}
            defpackage.cpw.c(r2, r1, r0)
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 >= 0) goto L66
        L65:
            r2 = 1
        L66:
            r10.f(r11)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.csc.i(java.lang.String):boolean");
    }

    private void f(String str) {
        DeviceCloudSharePreferencesManager deviceCloudSharePreferencesManager = new DeviceCloudSharePreferencesManager(cpp.a());
        Map<String, String> a2 = deviceCloudSharePreferencesManager.a(str);
        a2.put("key_last_check_ota_version_time", System.currentTimeMillis() + "");
        deviceCloudSharePreferencesManager.c(str, a2);
    }

    private String j(String str) {
        return new DeviceCloudSharePreferencesManager(cpp.a()).a(str).get("key_last_check_ota_version_time");
    }

    public void c(String str, boolean z) {
        DeviceCloudSharePreferencesManager deviceCloudSharePreferencesManager = new DeviceCloudSharePreferencesManager(cpp.a());
        Map<String, String> a2 = deviceCloudSharePreferencesManager.a(str);
        a2.put("key_is_exist_new_version", String.valueOf(z));
        deviceCloudSharePreferencesManager.c(str, a2);
    }

    public boolean g(String str) {
        return Boolean.valueOf(new DeviceCloudSharePreferencesManager(cpp.a()).a(str).get("key_is_exist_new_version")).booleanValue();
    }

    public void d(String str, Map<String, String> map) {
        new DeviceCloudSharePreferencesManager(cpp.a()).c(str, map);
    }

    public Map<String, String> e(String str) {
        return new DeviceCloudSharePreferencesManager(cpp.a()).a(str);
    }

    public void a() {
        CustomAlertDialog customAlertDialog = this.f11429a;
        if (customAlertDialog == null || !customAlertDialog.isShowing()) {
            return;
        }
        this.f11429a.dismiss();
        this.f11429a = null;
    }

    public void LI_(final Activity activity, final Bundle bundle) {
        if (!CommonUtil.h(activity, activity.getClass().getName())) {
            LogUtil.h("WifiOtaUpdateConstants", "showUpdateDialog activity is not showing:", activity.getClass().getName());
            return;
        }
        if (bundle == null) {
            LogUtil.h("WifiOtaUpdateConstants", "showUpdateDialog bundle null");
            return;
        }
        LogUtil.a("WifiOtaUpdateConstants", "showUpdateDialog in");
        CustomAlertDialog customAlertDialog = this.f11429a;
        if (customAlertDialog == null || !customAlertDialog.isShowing()) {
            View inflate = View.inflate(activity, R.layout.wifi_ota_update_prompt_dialog, null);
            HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.version_code);
            HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.version_node);
            healthTextView.setText(bundle.getString("version"));
            healthTextView2.setText(bundle.getString("update_nodes"));
            final String string = bundle.getString("devId");
            CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(activity);
            builder.e(R.string.IDS_device_wear_home_device_ota_upgrade);
            builder.cyp_(inflate);
            builder.cyo_(R.string.IDS_device_wifi_ota_go_upgrade, new DialogInterface.OnClickListener() { // from class: csj
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    csc.this.LH_(string, activity, bundle, dialogInterface, i);
                }
            });
            builder.cyn_(R.string.IDS_device_wifi_ota_try_again_later, new DialogInterface.OnClickListener() { // from class: csk
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    csc.LG_(string, dialogInterface, i);
                }
            });
            CustomAlertDialog c = builder.c();
            this.f11429a = c;
            c.setCancelable(false);
            this.f11429a.show();
            return;
        }
        LogUtil.h("WifiOtaUpdateConstants", "showUpdateDialog Dialog is displayed");
    }

    /* synthetic */ void LH_(String str, Activity activity, Bundle bundle, DialogInterface dialogInterface, int i) {
        if (!TextUtils.isEmpty(str)) {
            c(str, false);
        }
        Intent intent = new Intent(activity, (Class<?>) DeviceMainActivity.class);
        bundle.putString("view", "WifiVersionDetails");
        intent.putExtras(bundle);
        try {
            activity.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("WifiOtaUpdateConstants", "showUpdateDialog activity exception");
        }
        dialogInterface.cancel();
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    static /* synthetic */ void LG_(String str, DialogInterface dialogInterface, int i) {
        if (!TextUtils.isEmpty(str)) {
            d().f(str);
        }
        dialogInterface.cancel();
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("WifiOtaUpdateConstants", "saveHagridAutoUpgradeSwitch deviceId is null");
            return "0";
        }
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        LogUtil.a("WifiOtaUpdateConstants", "userId: ", CommonUtil.l(accountInfo));
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), accountInfo + str + "hagrid_auto_upgrade");
        LogUtil.a("WifiOtaUpdateConstants", "getHagridAutoUpgradeSwitch value:", b2);
        return TextUtils.isEmpty(b2) ? "0" : b2;
    }

    public static void a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("WifiOtaUpdateConstants", "saveHagridAutoUpgradeSwitch deviceId is null");
            return;
        }
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("WifiOtaUpdateConstants", "saveHagridAutoUpgradeSwitch autoUpgrade is null");
            return;
        }
        if (!"1".equals(str2) && !"0".equals(str2)) {
            LogUtil.h("WifiOtaUpdateConstants", "saveHagridAutoUpgradeSwitch autoUpgrade is error,", str2);
            return;
        }
        LogUtil.a("WifiOtaUpdateConstants", "saveHagridAutoUpgradeSwitch autoUpgrade:", str2);
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011) + str + "hagrid_auto_upgrade", str2, (StorageParams) null);
    }

    public static void d(String str, String str2) {
        if ("1".equals(b(str)) && "0".equals(str2)) {
            LogUtil.h("WifiOtaUpdateConstants", "saveHagridAutoUpgradeSwitchFormCloud no update.");
        } else {
            a(str, str2);
        }
    }

    public static void b(String str, long j) {
        if (TextUtils.isEmpty(str) || j == 0) {
            LogUtil.h("WifiOtaUpdateConstants", "setOtaStartUpgradeTime deviceId is null");
            return;
        }
        LogUtil.a("WifiOtaUpdateConstants", "setOtaStartUpgradeTime startTime:", Long.valueOf(j));
        SharedPreferenceManager.e(BaseApplication.getContext(), "wifi_weight_device", str + "upgrade_time", String.valueOf(j), (StorageParams) null);
    }

    public static long c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("WifiOtaUpdateConstants", "getOtaUpgradeTimeLeft deviceId is null");
            return 0L;
        }
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), "wifi_weight_device", str + "upgrade_time");
        if (!TextUtils.isEmpty(b2)) {
            try {
                long parseLong = Long.parseLong(b2);
                long currentTimeMillis = System.currentTimeMillis();
                LogUtil.a("WifiOtaUpdateConstants", "getOtaUpgradeTimeLeft otaStartTime:", Long.valueOf(parseLong), "|currentTime:", Long.valueOf(currentTimeMillis));
                return currentTimeMillis - parseLong;
            } catch (NumberFormatException unused) {
                LogUtil.b("WifiOtaUpdateConstants", "getOtaUpgradeTimeLeft get ota upgrade start time exception");
                return 0L;
            }
        }
        LogUtil.h("WifiOtaUpdateConstants", "getOtaUpgradeTimeLeft startTime is null");
        return 0L;
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("WifiOtaUpdateConstants", "isOtaTimeout deviceId is null");
            return true;
        }
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), "wifi_weight_device", str + "upgrade_time");
        if (!TextUtils.isEmpty(b2)) {
            try {
                long parseLong = Long.parseLong(b2);
                long currentTimeMillis = System.currentTimeMillis();
                long j = csf.g(str) ? 470000L : 170000L;
                LogUtil.a("WifiOtaUpdateConstants", "isOtaTimeout otaStartTime :", Long.valueOf(parseLong), " currentTime:", Long.valueOf(currentTimeMillis));
                if (currentTimeMillis - parseLong < j) {
                    return false;
                }
            } catch (NumberFormatException unused) {
                LogUtil.b("WifiOtaUpdateConstants", "isOtaTimeout get ota upgrade start time exception");
            }
        } else {
            LogUtil.h("WifiOtaUpdateConstants", "isOtaTimeout startTime is null");
        }
        return true;
    }
}
