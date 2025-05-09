package defpackage;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.TextUtils;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.dictionary.utils.ProductMapParseUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.outofprocess.util.NotificationContentProviderUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class tpn {
    private static final Object c = new Object();
    private static volatile tpn e;

    /* renamed from: a, reason: collision with root package name */
    private ContentObserver f17326a;
    private ContentObserver b;
    private DataReceiveCallback f;
    private IBaseResponseCallback i;
    private final Uri h = Uri.parse("content://com.android.server.notification.hwNotificationdecisioncenterProvider");
    private final CountDownTimer d = new CountDownTimer(OpAnalyticsConstants.H5_LOADING_DELAY, OpAnalyticsConstants.H5_LOADING_DELAY) { // from class: tpn.1
        @Override // android.os.CountDownTimer
        public void onTick(long j) {
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            synchronized (tpn.c) {
                LogUtil.a("WearEngine_NotificationCoordinationManager", "mCountDownTimer stop");
                tpn.this.j();
                tpn unused = tpn.e = null;
            }
        }
    };

    private tpn() {
        e();
        a();
        ProductMapParseUtil.b(BaseApplication.getContext());
    }

    public static tpn d() {
        if (e == null) {
            synchronized (c) {
                if (e != null) {
                    return e;
                }
                if (b() == null) {
                    LogUtil.b("WearEngine_NotificationCoordinationManager", "getInstance deviceInfo is null");
                    return null;
                }
                LogUtil.a("WearEngine_NotificationCoordinationManager", "getInstance deviceInfo create");
                e = new tpn();
            }
        }
        return e;
    }

    private void e() {
        this.i = new IBaseResponseCallback() { // from class: tps
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                tpn.this.c(i, obj);
            }
        };
        this.f = new DataReceiveCallback() { // from class: tpt
            @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
            public final void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
                tpn.this.b(i, deviceInfo, cvrVar);
            }
        };
        Handler handler = null;
        this.f17326a = new ContentObserver(handler) { // from class: tpn.3
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                LogUtil.a("WearEngine_NotificationCoordinationManager", "isAllowNotification onChange.");
                tpn.this.f();
            }
        };
        this.b = new ContentObserver(handler) { // from class: tpn.2
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                LogUtil.a("WearEngine_NotificationCoordinationManager", "AllowPackageList onChange.");
                tpn.this.i();
            }
        };
    }

    /* synthetic */ void c(int i, Object obj) {
        LogUtil.a("WearEngine_NotificationCoordinationManager", "mJoinNotificationCollaborationCallback enter.");
        if (i != 0) {
            LogUtil.h("WearEngine_NotificationCoordinationManager", "mNotificationCallback error");
            return;
        }
        if (!(obj instanceof Boolean)) {
            LogUtil.h("WearEngine_NotificationCoordinationManager", "mNotificationCallback objData illegal");
            return;
        }
        boolean booleanValue = ((Boolean) obj).booleanValue();
        DeviceInfo b = b();
        if (b == null) {
            return;
        }
        e(b.getWearEngineDeviceId(), "isAllowNotification", booleanValue ? "1" : "0");
    }

    /* synthetic */ void b(int i, DeviceInfo deviceInfo, cvr cvrVar) {
        LogUtil.a("WearEngine_NotificationCoordinationManager", "mSampleReceive enter.");
        if (deviceInfo == null || cvrVar == null) {
            LogUtil.a("WearEngine_NotificationCoordinationManager", "device or message is null.");
        } else if (cvrVar instanceof cvq) {
            b((cvq) cvrVar, deviceInfo.getWearEngineDeviceId());
        }
    }

    private void b(cvq cvqVar, String str) {
        List<cvn> configInfoList = cvqVar.getConfigInfoList();
        if (configInfoList == null || configInfoList.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (cvn cvnVar : configInfoList) {
            String a2 = a(cvnVar.a());
            int a3 = a(cvx.d(cvnVar.b()));
            arrayList.add(a2);
            arrayList2.add(String.valueOf(a3));
        }
        b(str, arrayList, arrayList2);
    }

    private int a(String str) {
        try {
            List<cwd> e2 = new cwl().a(str).e();
            if (e2 != null && e2.size() != 0) {
                return CommonUtil.w(e2.get(0).c());
            }
            LogUtil.h("WearEngine_NotificationCoordinationManager", "tlvList is empty");
            return 0;
        } catch (cwg unused) {
            LogUtil.b("WearEngine_NotificationCoordinationManager", "parseData is error");
            return 0;
        }
    }

    public void d(String str, DeviceInfo deviceInfo) {
        char c2;
        LogUtil.a("WearEngine_NotificationCoordinationManager", "handleAction enter");
        if (TextUtils.isEmpty(str) || deviceInfo == null) {
            LogUtil.b("WearEngine_NotificationCoordinationManager", "handleAction action or deviceInfo is null");
            return;
        }
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == -1152217332) {
            if (str.equals("com.huawei.wearengine.NotificationCoordination.add")) {
                c2 = 0;
            }
            c2 = 65535;
        } else if (hashCode != -240757920) {
            if (hashCode == 255854206 && str.equals("com.huawei.wearengine.NotificationCoordination.update")) {
                c2 = 2;
            }
            c2 = 65535;
        } else {
            if (str.equals("com.huawei.wearengine.NotificationCoordination.delete")) {
                c2 = 1;
            }
            c2 = 65535;
        }
        if (c2 == 0) {
            LogUtil.a("WearEngine_NotificationCoordinationManager", "processAction add enter");
            this.d.cancel();
            cuk.b().registerDeviceSampleListener("com.huawei.wearengine", this.f);
            b(deviceInfo);
            return;
        }
        if (c2 == 1) {
            LogUtil.a("WearEngine_NotificationCoordinationManager", "processAction delete enter");
            b(deviceInfo.getWearEngineDeviceId());
            h();
        } else {
            if (c2 != 2) {
                return;
            }
            ReleaseLogUtil.e("R_NotificationCoordinationManager", "processAction update enter");
            e(deviceInfo.getWearEngineDeviceId(), "isAvailable", "0");
            h();
        }
    }

    private void h() {
        if (b() == null) {
            LogUtil.a("WearEngine_NotificationCoordinationManager", "release start");
            this.d.cancel();
            this.d.start();
        }
    }

    private static DeviceInfo b() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "WearEngine_NotificationCoordinationManager");
        if (deviceList == null || deviceList.isEmpty()) {
            LogUtil.h("WearEngine_NotificationCoordinationManager", "getDeviceInfo, deviceInfoList is empty");
            return null;
        }
        DeviceInfo deviceInfo = deviceList.get(0);
        if (deviceInfo == null) {
            LogUtil.h("WearEngine_NotificationCoordinationManager", "getDeviceInfo, deviceInfo is null");
            return null;
        }
        if (cwi.c(deviceInfo, 119)) {
            return deviceInfo;
        }
        LogUtil.h("WearEngine_NotificationCoordinationManager", "getDeviceInfo, device is not support");
        return null;
    }

    private void b(DeviceInfo deviceInfo) {
        String e2 = tqy.e(deviceInfo);
        LogUtil.a("WearEngine_NotificationCoordinationManager", "deviceType is " + e2);
        c(deviceInfo.getWearEngineDeviceId(), e2);
        i();
        f();
        e(deviceInfo);
    }

    private void e(DeviceInfo deviceInfo) {
        cvq cvqVar = new cvq();
        cvqVar.setSrcPkgName("com.huawei.wearengine");
        cvqVar.setWearPkgName("com.huawei.watch.home");
        cvn cvnVar = new cvn();
        cvnVar.e(900100013L);
        cvnVar.d(1);
        cvn cvnVar2 = new cvn();
        cvnVar2.e(900100014L);
        cvnVar2.d(1);
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(cvnVar);
        arrayList.add(cvnVar2);
        cvqVar.setConfigInfoList(arrayList);
        cuk.b().sendSampleConfigCommand(deviceInfo, cvqVar, "WearEngine_NotificationCoordinationManager");
        LogUtil.a("WearEngine_NotificationCoordinationManager", "sendSampleConfigCommand is success!");
    }

    private void a() {
        cuk.b().registerDeviceSampleListener("com.huawei.wearengine", this.f);
        BaseApplication.getContext().getContentResolver().registerContentObserver(Uri.parse(jdz.c), false, this.f17326a);
        BaseApplication.getContext().getContentResolver().registerContentObserver(Uri.parse(jdz.j), false, this.b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        cuk.b().unRegisterDeviceSampleListener("com.huawei.wearengine");
        BaseApplication.getContext().getContentResolver().unregisterContentObserver(this.f17326a);
        BaseApplication.getContext().getContentResolver().unregisterContentObserver(this.b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        NotificationContentProviderUtil.e(this.i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        List<String> i = NotificationContentProviderUtil.i();
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i.size(); i2++) {
            if (i2 > 0) {
                sb.append(";");
            }
            sb.append(i.get(i2));
        }
        DeviceInfo b = b();
        if (b == null) {
            return;
        }
        e(b.getWearEngineDeviceId(), "allowPackageList", sb.toString());
    }

    private void c(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("WearEngine_NotificationCoordinationManager", "addDeviceInfo wearEngineId is null");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("packageName", "com.huawei.health");
        bundle.putString("deviceType", str2);
        bundle.putString("deviceKey", str);
        bundle.putString("isVersionSupport", "1");
        bundle.putString("isLanguageSupport", "1");
        bundle.putString("isZenModeOn", "1");
        bundle.putString("isAvailable", "1");
        bundle.putString("isAllowNotification", "0");
        bundle.putString("isUsing", "0");
        bundle.putString("allowPackageList", "");
        LogUtil.a("WearEngine_NotificationCoordinationManager", "addDeviceInfo deviceId: " + CommonUtil.l(str));
        try {
            BaseApplication.getContext().getContentResolver().call(this.h, "add", (String) null, bundle);
        } catch (Exception unused) {
            LogUtil.b("WearEngine_NotificationCoordinationManager", "addDeviceInfo exception");
        }
    }

    private void e(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("WearEngine_NotificationCoordinationManager", "updateDeviceInfo wearEngineId is null");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("packageName", "com.huawei.health");
        bundle.putString("deviceKey", str);
        bundle.putString("updateKeys", str2);
        bundle.putString(str2, str3);
        ReleaseLogUtil.e("R_NotificationCoordinationManager", "updateDeviceInfo deviceId: ", CommonUtil.l(str), ",updateKey: ", str2, ",updateValue: ", str3);
        try {
            BaseApplication.getContext().getContentResolver().call(this.h, "update", (String) null, bundle);
        } catch (Exception unused) {
            LogUtil.b("WearEngine_NotificationCoordinationManager", "updateDeviceInfo exception");
        }
    }

    private void b(String str, List<String> list, List<String> list2) {
        if (TextUtils.isEmpty(str) || list == null || list.isEmpty()) {
            LogUtil.b("WearEngine_NotificationCoordinationManager", "updateDeviceInfo wearEngineId or updateKeys is empty");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("packageName", "com.huawei.health");
        bundle.putString("deviceKey", str);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                sb.append(";");
            }
            sb.append(list.get(i));
            bundle.putString(list.get(i), list2.get(i));
            LogUtil.a("WearEngine_NotificationCoordinationManager", "updateDeviceInfo deviceId: " + CommonUtil.l(str) + ",updateKey: " + list.get(i) + ", updateValue: " + list2.get(i));
        }
        bundle.putString("updateKeys", sb.toString());
        try {
            BaseApplication.getContext().getContentResolver().call(this.h, "update", (String) null, bundle);
        } catch (Exception unused) {
            LogUtil.b("WearEngine_NotificationCoordinationManager", "updateDeviceInfo exception");
        }
    }

    private void b(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("WearEngine_NotificationCoordinationManager", "removeDeviceInfo wearEngineId is null");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("packageName", "com.huawei.health");
        bundle.putString("deviceKey", str);
        LogUtil.a("WearEngine_NotificationCoordinationManager", "removeDeviceInfo deviceId: " + CommonUtil.l(str));
        try {
            BaseApplication.getContext().getContentResolver().call(this.h, "remove", (String) null, bundle);
        } catch (Exception unused) {
            LogUtil.b("WearEngine_NotificationCoordinationManager", "removeDeviceInfo exception");
        }
    }

    private String a(long j) {
        if (j == 900100013) {
            return "isZenModeOn";
        }
        return j == 900100014 ? "isUsing" : "unknown";
    }
}
