package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hms.support.api.entity.auth.AuthCode;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.intelligent.DeviceLinkResultNotifyRequest;
import com.huawei.hwcloudmodel.model.intelligent.DeviceLinkResultNotifyResponse;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.hwdevice.outofprocess.util.NotificationContentProviderUtil;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class khg {
    private static khg b;
    private static final Object d = new Object();
    private String c;
    private Set<String> e = new HashSet(16);

    private khg() {
    }

    public static khg d() {
        khg khgVar;
        synchronized (d) {
            if (b == null) {
                b = new khg();
            }
            khgVar = b;
        }
        return khgVar;
    }

    public void e(byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            LogUtil.h("HwNotificationIconPushManager", "parseInfo dataInfos is null or length < NORMAL_CAPABILITY_LENGTH");
            return;
        }
        byte b2 = bArr[1];
        if (b2 == 13) {
            a(bArr);
        } else if (b2 == 15) {
            c(bArr);
        } else {
            LogUtil.c("HwNotificationIconPushManager", "no support : ", Byte.valueOf(b2));
        }
    }

    public void a(byte[] bArr) {
        cwe cweVar;
        if (bArr == null || bArr.length < 2) {
            LogUtil.h("HwNotificationIconPushManager", "parserDeviceAppInfo dataInfos is null or length < NORMAL_CAPABILITY_LENGTH");
            return;
        }
        if (bArr[1] != 13) {
            LogUtil.h("HwNotificationIconPushManager", "parserDeviceAppInfo command id is not 13");
            return;
        }
        String d2 = cvx.d(bArr);
        if (TextUtils.isEmpty(d2)) {
            LogUtil.h("HwNotificationIconPushManager", "parserDeviceAppInfo resultDataHex is empty");
            return;
        }
        if (bArr.length == 8 && bArr[2] == Byte.MAX_VALUE) {
            LogUtil.h("HwNotificationIconPushManager", "parserDeviceAppInfo dataInfos is invalid");
            return;
        }
        h();
        try {
            cweVar = new cwl().a(d2.substring(4));
        } catch (cwg unused) {
            LogUtil.b("HwNotificationIconPushManager", "parserDeviceAppInfo TlvException");
            cweVar = null;
        }
        if (cweVar == null) {
            LogUtil.h("HwNotificationIconPushManager", "parserDeviceAppInfo tlvFather is null");
            return;
        }
        List<cwe> a2 = cweVar.a();
        if (a2 == null || a2.isEmpty()) {
            LogUtil.h("HwNotificationIconPushManager", "parserDeviceAppInfo tlvFatherList is null");
            m();
            l();
            return;
        }
        cwe cweVar2 = a2.get(0);
        if (cweVar2 == null) {
            LogUtil.h("HwNotificationIconPushManager", "parserDeviceAppInfo tlvAppList is null");
            return;
        }
        Iterator<cwe> it = cweVar2.a().iterator();
        while (it.hasNext()) {
            e(it.next());
        }
        m();
        l();
    }

    private void l() {
        LogUtil.a("HwNotificationIconPushManager", "Enter pushNotificationAppInfo.");
        for (jje jjeVar : khj.c()) {
            String e = e(jjeVar.g());
            if (TextUtils.isEmpty(e) || !e.contains(d(jjeVar.c(), jjeVar.e()))) {
                c(jjeVar.g(), jjeVar.c(), jjeVar.e(), jrg.bIZ_(jjeVar.g(), khj.bNS_(jjeVar.g())));
            }
        }
    }

    private void e(cwe cweVar) {
        String str = "";
        String str2 = "";
        String str3 = str2;
        for (cwd cwdVar : cweVar.e()) {
            int a2 = CommonUtil.a(cwdVar.e(), 16);
            if (a2 == 3) {
                str = cvx.e(cwdVar.c());
            } else if (a2 == 4) {
                str2 = cvx.e(cwdVar.c());
            } else if (a2 == 5) {
                str3 = cvx.e(cwdVar.c());
            }
        }
        LogUtil.a("HwNotificationIconPushManager", "handleSingleTlv packageName: ", str, " appName: ", str2, " versionCode: ", str3);
        e(str, str2 + str3);
    }

    private static void m() {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(2);
        deviceCommand.setCommandID(13);
        byte[] a2 = cvx.a(cvx.e(127) + cvx.e(4) + cvx.b(100000L));
        deviceCommand.setDataContent(a2);
        deviceCommand.setDataLen(a2.length);
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    private void e(String str, String str2) {
        BaseApplication.getContext().getSharedPreferences("notification_app_info", 0).edit().putString(str, str2).apply();
        LogUtil.a("HwNotificationIconPushManager", "saveNotificationAppInfo completed");
    }

    private String e(String str) {
        String string = BaseApplication.getContext().getSharedPreferences("notification_app_info", 0).getString(str, null);
        LogUtil.c("HwNotificationIconPushManager", "getNotificationAppInfo completed");
        return string;
    }

    private String d(String str, String str2) {
        return str + str2;
    }

    private void h() {
        SharedPreferences.Editor edit = BaseApplication.getContext().getSharedPreferences("notification_app_info", 0).edit();
        edit.clear();
        edit.apply();
        LogUtil.a("HwNotificationIconPushManager", "clearSharedPreferencesData completed");
    }

    private void c(String str, String str2, String str3, byte[] bArr) {
        String str4;
        LogUtil.a("HwNotificationIconPushManager", "enter sendNotificationAppInfo 5.2.14");
        if (!g()) {
            LogUtil.h("HwNotificationIconPushManager", "hasn't device connected.");
            return;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || bArr == null) {
            LogUtil.h("HwNotificationIconPushManager", "sendNotificationAppInfo, app info is empty.");
            return;
        }
        String c = cvx.c(str);
        String str5 = cvx.e(1) + cvx.e(c.length() / 2) + c;
        String c2 = cvx.c(str2);
        String str6 = cvx.e(2) + cvx.e(c2.length() / 2) + c2;
        String c3 = cvx.c(str3);
        String str7 = cvx.e(3) + cvx.e(c3.length() / 2) + c3;
        if (bArr.length > 0) {
            str4 = cvx.e(4) + cvx.d(bArr.length);
        } else {
            str4 = "";
        }
        deviceCommand.setServiceID(2);
        deviceCommand.setCommandID(14);
        byte[] b2 = b(cvx.a(str5 + str6 + str7 + str4), bArr);
        deviceCommand.setDataLen(b2.length);
        deviceCommand.setDataContent(b2);
        e(str, d(str2, str3));
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    private boolean g() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null && defaultAdapter.getState() == 10) {
            LogUtil.a("HwNotificationIconPushManager", "switch not on, not need start service!");
            return false;
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "HwNotificationIconPushManager");
        if (deviceList != null && !deviceList.isEmpty()) {
            LogUtil.a("HwNotificationIconPushManager", "hasDeviceConnected, has device Db info.");
            return true;
        }
        LogUtil.a("HwNotificationIconPushManager", "hasDeviceConnected, false.");
        return false;
    }

    private byte[] b(byte[] bArr, byte[] bArr2) {
        LogUtil.a("HwNotificationIconPushManager", "byteMerger, dataInfos :", Integer.valueOf(bArr.length), " , iconByteArray :", Integer.valueOf(bArr2.length));
        if (bArr2.length <= 0) {
            return bArr;
        }
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public boolean b(String str, String str2, String str3) {
        LogUtil.a("HwNotificationIconPushManager", "isNeedPushAppInfo, appName :", str, " , versionCode :", str2, " , appPackageName :", str3);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            LogUtil.a("HwNotificationIconPushManager", "isPushedNotificationApp, param is empty.");
            return false;
        }
        String e = e(str3);
        String str4 = str + str2;
        LogUtil.a("HwNotificationIconPushManager", "isNeedPushAppInfo, appInfoString :", e, " , appNameAndVersionCode :", str4);
        if (e == null || !e.contains(str4)) {
            return true;
        }
        LogUtil.a("HwNotificationIconPushManager", "isNeedPushAppInfo, appJsonString contains appNameAndPackageName.");
        return false;
    }

    public void bNT_(Intent intent) {
        String str;
        if (intent == null) {
            LogUtil.h("HwNotificationIconPushManager", "appInstalled, intent is null.");
            return;
        }
        String dataString = intent.getDataString();
        LogUtil.a("HwNotificationIconPushManager", "intentInfo is :", dataString);
        if (TextUtils.isEmpty(dataString)) {
            LogUtil.h("HwNotificationIconPushManager", "appInstalled intentInfo is empty.");
            return;
        }
        String[] split = dataString.split(":");
        if (split.length > 1) {
            str = split[1];
        } else {
            str = split[0];
        }
        if (k()) {
            LogUtil.h("HwNotificationIconPushManager", "isn't support notification push icon.");
            b(str);
        }
        if (b()) {
            a(str);
        }
    }

    public void e() {
        LogUtil.a("HwNotificationIconPushManager", "language start change");
        if (b()) {
            o();
            return;
        }
        if (!k()) {
            LogUtil.h("HwNotificationIconPushManager", "isn't support notification push icon.");
            return;
        }
        for (jje jjeVar : khj.c()) {
            String e = e(jjeVar.g());
            LogUtil.a("HwNotificationIconPushManager", "localeChange, appInfo :", e, ", info.getAppName :", jjeVar.c());
            if (TextUtils.isEmpty(e)) {
                c(jjeVar.g(), jjeVar.c(), jjeVar.e(), jrg.bIZ_(jjeVar.g(), khj.bNS_(jjeVar.g())));
            } else if (!e.contains(jjeVar.c())) {
                c(jjeVar.g(), jjeVar.c(), jjeVar.e(), new byte[0]);
            }
        }
    }

    public void b(String str) {
        if (b()) {
            a(str);
            return;
        }
        if (!NotificationContentProviderUtil.d(str) && !i().contains(str)) {
            LogUtil.h("HwNotificationIconPushManager", str, " send Notification failed: SUB_SWITCH_TURN_OFF!");
            return;
        }
        PackageManager packageManager = BaseApplication.getContext().getPackageManager();
        if (packageManager == null) {
            LogUtil.h("HwNotificationIconPushManager", "sendNotificationAppInfo, packageManager is null.");
            return;
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            if (applicationInfo == null) {
                LogUtil.h("HwNotificationIconPushManager", "sendNotificationAppInfo, applicationInfo is null.");
                return;
            }
            String valueOf = String.valueOf(packageInfo.versionCode);
            String obj = applicationInfo.loadLabel(packageManager).toString();
            if (k() && b(obj, valueOf, applicationInfo.packageName)) {
                LogUtil.a("HwNotificationIconPushManager", "sendNotificationAppInfo, is support notification push icon.");
                c(applicationInfo.packageName, obj, valueOf, jrg.bIZ_(str, packageManager.getApplicationIcon(applicationInfo)));
            }
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("HwNotificationIconPushManager", "sendNotificationAppInfo, NameNotFoundException.");
        }
    }

    public void f() {
        LogUtil.a("HwNotificationIconPushManager", "enter checkedChangedPushAppInfo.");
        if (k()) {
            for (jje jjeVar : khj.d()) {
                if (b(jjeVar.c(), jjeVar.e(), jjeVar.g())) {
                    c(jjeVar.g(), jjeVar.c(), jjeVar.e(), jrg.bIZ_(jjeVar.g(), khj.bNS_(jjeVar.g())));
                }
            }
        }
        if (b()) {
            n();
        }
    }

    private void c(byte[] bArr) {
        String d2 = cvx.d(bArr);
        cwl cwlVar = new cwl();
        try {
            String substring = d2.substring(4);
            List<cwd> e = cwlVar.a(substring).e();
            if (e != null && !e.isEmpty()) {
                int i = -1;
                String str = null;
                for (cwd cwdVar : e) {
                    int w = CommonUtil.w(cwdVar.e());
                    if (w == 1) {
                        str = cvx.e(cwdVar.c());
                    } else if (w != 127) {
                        LogUtil.h("HwNotificationIconPushManager", "new type, not support parse : ", cwdVar.e());
                    } else {
                        i = CommonUtil.w(cwdVar.c());
                    }
                }
                a(str, i);
                return;
            }
            LogUtil.h("HwNotificationIconPushManager", "parseBleMultiLinkInfo device push app is wrong : ", substring);
        } catch (cwg unused) {
            LogUtil.b("HwNotificationIconPushManager", "parserDeviceAppInfo TlvException");
        }
    }

    private void a(String str, int i) {
        jbs.a(BaseApplication.getContext()).e(new DeviceLinkResultNotifyRequest(str, i), new ICloudOperationResult<DeviceLinkResultNotifyResponse>() { // from class: khg.5
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void operationResult(DeviceLinkResultNotifyResponse deviceLinkResultNotifyResponse, String str2, boolean z) {
                LogUtil.a("HwNotificationIconPushManager", "isSuccess : ", Boolean.valueOf(z), " ,text : ", str2);
            }
        });
    }

    private boolean k() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HwNotificationIconPushManager");
        DeviceInfo deviceInfo = deviceList.size() > 0 ? deviceList.get(0) : null;
        if (deviceInfo == null) {
            LogUtil.h("HwNotificationIconPushManager", "isSupportSyncIcon deviceInfo is null");
            return false;
        }
        return cwi.c(deviceInfo, 17);
    }

    public boolean b() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HwNotificationIconPushManager");
        DeviceInfo deviceInfo = deviceList.size() > 0 ? deviceList.get(0) : null;
        if (deviceInfo == null) {
            LogUtil.h("HwNotificationIconPushManager", "isSupportAddIconTimestamp deviceInfo is null");
            return false;
        }
        return cwi.c(deviceInfo, 77);
    }

    private void o() {
        List<jje> c = khj.c();
        if (c.isEmpty()) {
            return;
        }
        Set<String> j = j();
        for (jje jjeVar : c) {
            if (j.contains(jjeVar.g())) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("appPkgName", jjeVar.g());
                    jSONObject.put("appName", jjeVar.c());
                    JSONObject c2 = c(AuthCode.StatusCode.CERT_FINGERPRINT_ERROR, jSONObject);
                    LogUtil.a("HwNotificationIconPushManager", "onLanguageChange: ", c2.toString());
                    khh.c().a(c2.toString());
                } catch (UnsupportedEncodingException | JSONException unused) {
                    LogUtil.b("HwNotificationIconPushManager", "onLanguageChange JSONException or UnsupportedEncodingException");
                }
            }
        }
    }

    public void a(String str) {
        if (!j().contains(str)) {
            LogUtil.h("HwNotificationIconPushManager", "onSingleNotificationAppOpened not is icon ", str);
            return;
        }
        if (TextUtils.equals(str, this.c)) {
            LogUtil.h("HwNotificationIconPushManager", "onSingleNotificationAppOpened is transmitting");
            return;
        }
        if (this.e.contains(str)) {
            LogUtil.h("HwNotificationIconPushManager", "onSingleNotificationAppOpened mPushAppSet contains");
            return;
        }
        if (!NotificationContentProviderUtil.d(str) && !i().contains(str)) {
            LogUtil.h("HwNotificationIconPushManager", str, " onSingleNotificationAppOpened push failed: SUB_SWITCH_TURN_OFF!");
            return;
        }
        LogUtil.a("HwNotificationIconPushManager", "onSingleNotificationAppOpened packageName:", str);
        this.e.add(str);
        r();
    }

    private Set<String> j() {
        HashSet hashSet = new HashSet();
        hashSet.addAll(i());
        hashSet.addAll(bfg.b);
        return hashSet;
    }

    private Set<String> i() {
        HashSet hashSet = new HashSet();
        if (NotificationContentProviderUtil.j()) {
            boolean a2 = SharedPreferenceManager.a("SUPPORT_NOTIFY_LIVE_LEVEL_CAPABILITY", "SUPPORT_NOTIFY_LIVE_LEVEL_CAPABILITY", false);
            LogUtil.a("HwNotificationIconPushManager", "getLiveCloudList notifyLiveLevelCapability=", Boolean.valueOf(a2));
            if (a2) {
                String e = SharedPreferenceManager.e("NOTIFY_LIVE_LEVEL_VALUE", "NOTIFY_LIVE_LEVEL_VALUE", "levelDefault");
                LogUtil.a("HwNotificationIconPushManager", "getLiveCloudList notifyLiveLevel=", e);
                if (!TextUtils.equals(e, "levelDefault")) {
                    hashSet.addAll(NotificationContentProviderUtil.c(e));
                } else {
                    hashSet.addAll(NotificationContentProviderUtil.c("levelDefault"));
                }
            }
        }
        return hashSet;
    }

    public void a() {
        LogUtil.h("HwNotificationIconPushManager", "onDeviceConnected:");
        if (!b()) {
            LogUtil.h("HwNotificationIconPushManager", "onDeviceConnected device is not support add icon timestamp.");
            return;
        }
        this.e.clear();
        c((String) null);
        if (!NotificationContentProviderUtil.e()) {
            LogUtil.h("HwNotificationIconPushManager", "onDeviceConnected authorized is close");
            return;
        }
        List<String> i = NotificationContentProviderUtil.i();
        i.addAll(i());
        Iterator<String> it = i.iterator();
        Set<String> j = j();
        while (it.hasNext()) {
            String next = it.next();
            if (!j.contains(next) || this.e.contains(next)) {
                it.remove();
            }
        }
        LogUtil.a("HwNotificationIconPushManager", "onDeviceConnected pushEnableApps:", Integer.valueOf(i.size()));
        if (i.isEmpty()) {
            return;
        }
        this.e.addAll(i);
        r();
    }

    public void c() {
        LogUtil.a("HwNotificationIconPushManager", "onNotificationLiveLevel start");
        if (!b()) {
            LogUtil.h("HwNotificationIconPushManager", "onNotificationLiveLevel device is not support add icon timestamp.");
            return;
        }
        if (!NotificationContentProviderUtil.e()) {
            LogUtil.h("HwNotificationIconPushManager", "onNotificationLiveLevel authorized is close");
            return;
        }
        Set<String> i = i();
        if (i.isEmpty()) {
            LogUtil.h("HwNotificationIconPushManager", "onNotificationLiveLevel liveCloudList is empty");
        } else {
            this.e.addAll(i);
            r();
        }
    }

    private void n() {
        List<String> i = NotificationContentProviderUtil.i();
        i.addAll(bfg.d);
        i.add(bfg.e);
        i.addAll(i());
        Iterator<String> it = i.iterator();
        Set<String> j = j();
        while (it.hasNext()) {
            String next = it.next();
            if (!j.contains(next) || this.e.contains(next)) {
                it.remove();
            }
        }
        LogUtil.a("HwNotificationIconPushManager", "onNotificationSwitchOpened pushEnableApps:", Integer.valueOf(i.size()));
        if (i.isEmpty()) {
            return;
        }
        this.e.addAll(i);
        r();
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0062, code lost:
    
        if (android.text.TextUtils.isEmpty(r2.e()) == false) goto L29;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void r() {
        /*
            r8 = this;
            java.lang.String r0 = r8.c
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            java.lang.String r1 = "HwNotificationIconPushManager"
            if (r0 != 0) goto L15
            java.lang.String r0 = "sendMessage transmitting"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r0)
            return
        L15:
            java.util.Set<java.lang.String> r0 = r8.e
            java.util.Iterator r0 = r0.iterator()
        L1b:
            r2 = 0
        L1c:
            boolean r3 = r0.hasNext()     // Catch: java.util.ConcurrentModificationException -> L6f
            if (r3 == 0) goto L79
            java.lang.Object r3 = r0.next()     // Catch: java.util.ConcurrentModificationException -> L6f
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.util.ConcurrentModificationException -> L6f
            r0.remove()     // Catch: java.util.ConcurrentModificationException -> L6f
            r4 = 2
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch: java.util.ConcurrentModificationException -> L6f
            java.lang.String r6 = "sendPushAppInfo packageName:"
            r7 = 0
            r5[r7] = r6     // Catch: java.util.ConcurrentModificationException -> L6f
            r6 = 1
            r5[r6] = r3     // Catch: java.util.ConcurrentModificationException -> L6f
            com.huawei.hwlogsmodel.LogUtil.a(r1, r5)     // Catch: java.util.ConcurrentModificationException -> L6f
            java.lang.String r5 = defpackage.bfg.e     // Catch: java.util.ConcurrentModificationException -> L6f
            boolean r5 = r5.equals(r3)     // Catch: java.util.ConcurrentModificationException -> L6f
            if (r5 == 0) goto L47
            jje r2 = defpackage.khj.e()     // Catch: java.util.ConcurrentModificationException -> L6f
            goto L4b
        L47:
            jje r2 = defpackage.khj.e(r3)     // Catch: java.util.ConcurrentModificationException -> L6f
        L4b:
            if (r2 != 0) goto L5a
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.util.ConcurrentModificationException -> L6f
            java.lang.String r5 = "sendPushAppInfoToDevice appInfo is null:"
            r4[r7] = r5     // Catch: java.util.ConcurrentModificationException -> L6f
            r4[r6] = r3     // Catch: java.util.ConcurrentModificationException -> L6f
            com.huawei.hwlogsmodel.LogUtil.h(r1, r4)     // Catch: java.util.ConcurrentModificationException -> L6f
            goto L1c
        L5a:
            java.lang.String r3 = r2.e()     // Catch: java.util.ConcurrentModificationException -> L6f
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch: java.util.ConcurrentModificationException -> L6f
            if (r3 == 0) goto L79
            java.lang.Object[] r3 = new java.lang.Object[r6]     // Catch: java.util.ConcurrentModificationException -> L6f
            java.lang.String r4 = "sendPushAppInfo AppCode is null."
            r3[r7] = r4     // Catch: java.util.ConcurrentModificationException -> L6f
            com.huawei.hwlogsmodel.LogUtil.h(r1, r3)     // Catch: java.util.ConcurrentModificationException -> L6f
            goto L1b
        L6f:
            java.lang.String r0 = "sendPushAppInfo ConcurrentModificationException"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r0)
        L79:
            if (r2 != 0) goto L86
            java.lang.String r0 = "sendPushAppInfo final appInfo is null"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r0)
            return
        L86:
            java.lang.String r0 = r2.g()
            r8.c(r0)
            r8.a(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.khg.r():void");
    }

    private void a(jje jjeVar) {
        try {
            String c = khj.c(jjeVar.g(), jjeVar.e());
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appInfoHash", c);
            jSONObject.put("appPkgName", jjeVar.g());
            jSONObject.put("appName", jjeVar.c());
            jSONObject.put("retCode", 10000);
            JSONObject c2 = c(6001, jSONObject);
            LogUtil.a("HwNotificationIconPushManager", "sendPushAppInfoToDevice: ", c2.toString());
            khh.c().a(c2.toString());
        } catch (UnsupportedEncodingException | JSONException unused) {
            LogUtil.b("HwNotificationIconPushManager", "sendPushAppInfoToDevice Exception");
        }
    }

    public void d(JSONObject jSONObject) throws JSONException {
        if (!b()) {
            LogUtil.h("HwNotificationIconPushManager", "dealDeviceRequestIcon device is not support add icon and timestamp.");
            return;
        }
        int i = jSONObject.getInt("retCode");
        String string = jSONObject.getString("appPkgName");
        LogUtil.a("HwNotificationIconPushManager", "dealDeviceRequestIcon retCode:", Integer.valueOf(i));
        if (i == 10004 || i == 10005) {
            c((String) null);
            r();
            return;
        }
        String string2 = jSONObject.getString("appInfoHash");
        if (i != 10001 && i != 10002 && i != 10003) {
            a(string, string2, 10010);
            return;
        }
        int i2 = jSONObject.getInt("iconWidth");
        int i3 = jSONObject.getInt("iconHeight");
        LogUtil.a("HwNotificationIconPushManager", "operateAppIcon pkgName:", string, " iconWidth:", Integer.valueOf(i2), " iconHeight:", Integer.valueOf(i3));
        if (TextUtils.isEmpty(string) || i2 <= 0 || i3 <= 0) {
            a(string, string2, 10010);
            return;
        }
        Drawable bNS_ = khj.bNS_(string);
        if (bNS_ == null) {
            a(string, string2, 10009);
            LogUtil.h("HwNotificationIconPushManager", "dealDeviceRequestIcon drawable is null,", string);
            return;
        }
        Bitmap bNR_ = khj.bNR_(bNS_);
        if (bNR_ == null) {
            a(string, string2, 10009);
            LogUtil.h("HwNotificationIconPushManager", "dealDeviceRequestIcon final iconBitmap is null,", string);
        } else {
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bNR_, i2, i3, true);
            String c = c(string, string2, i);
            khp.bNU_(createScaledBitmap, c);
            b(i, string, string2, c);
        }
    }

    private void b(final int i, final String str, final String str2, String str3) {
        final File file = new File(str3);
        if (file.exists()) {
            khh.c().d(file, new SendCallback() { // from class: khg.1
                @Override // com.huawei.health.deviceconnect.callback.SendCallback
                public void onSendProgress(long j) {
                }

                @Override // com.huawei.health.deviceconnect.callback.SendCallback
                public void onSendResult(int i2) {
                    LogUtil.a("HwNotificationIconPushManager", "dealDeviceRequestIcon onSendResult errorNumber:", Integer.valueOf(i2));
                    if (i2 == 207) {
                        File file2 = file;
                        if (file2 != null && file2.exists()) {
                            LogUtil.c("HwNotificationIconPushManager", "dealDeviceRequestIcon onSendResult file delete:", Boolean.valueOf(file.delete()));
                        }
                        khg khgVar = khg.this;
                        khgVar.a(str, str2, khgVar.e(i));
                    }
                }

                @Override // com.huawei.health.deviceconnect.callback.SendCallback
                public void onFileTransferReport(String str4) {
                    LogUtil.a("HwNotificationIconPushManager", "sendFileByComand onFileTransferReport transferWay: ", str4);
                }
            });
        } else {
            LogUtil.h("HwNotificationIconPushManager", "dealDeviceRequestIcon file not exists ", str);
        }
    }

    public void e(JSONObject jSONObject) throws JSONException {
        if (!b()) {
            LogUtil.h("HwNotificationIconPushManager", "dealDeviceTerminate device is not support add icon timestamp.");
            return;
        }
        int i = jSONObject.getInt("retCode");
        LogUtil.a("HwNotificationIconPushManager", "dealDeviceTerminate pkgName:", jSONObject.getString("appPkgName"), " retCode:", Integer.valueOf(i));
        if (i == 10011) {
            c((String) null);
            r();
        }
    }

    private JSONObject c(int i, JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("msgType", i);
        jSONObject2.put("msgBody", jSONObject);
        return jSONObject2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, int i) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appPkgName", str);
            jSONObject.put("retCode", i);
            jSONObject.put("appInfoHash", str2);
            JSONObject c = c(6002, jSONObject);
            LogUtil.a("HwNotificationIconPushManager", "sendErrorCommand: ", c.toString());
            khh.c().a(c.toString());
        } catch (UnsupportedEncodingException | JSONException unused) {
            LogUtil.b("HwNotificationIconPushManager", "sendErrorCommand Exception");
        }
    }

    private String c(String str, String str2, int i) {
        try {
            return BaseApplication.getContext().getFilesDir().getCanonicalPath() + File.separator + RemoteMessageConst.NOTIFICATION + File.separator + "iconPush" + File.separator + (str2 + "_" + str.replace(".", "") + "_" + b(i)) + WatchFaceConstant.BIN_SUFFIX;
        } catch (IOException unused) {
            LogUtil.b("HwNotificationIconPushManager", "getAppIconBinPath IOException");
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public int e(int i) {
        switch (i) {
            case 10001:
                return 10006;
            case 10002:
                return 10007;
            case 10003:
                return 10008;
            default:
                LogUtil.h("HwNotificationIconPushManager", "getSentFileCode default");
                return 10007;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private int b(int i) {
        switch (i) {
            case 10001:
                return 1;
            case 10002:
                return 2;
            case 10003:
                return 3;
            default:
                LogUtil.h("HwNotificationIconPushManager", "getFileType default");
                return 2;
        }
    }

    private void c(String str) {
        synchronized (d) {
            this.c = str;
        }
    }
}
