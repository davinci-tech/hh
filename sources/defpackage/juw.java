package defpackage;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.health.IDetectCommonCallback;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwUpdateService;
import com.huawei.hwdevice.outofprocess.util.NotificationContentProviderUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.utils.service.UpdateService;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.share.HiHealthError;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/* loaded from: classes5.dex */
public class juw {
    private static juw b;
    private static final Object c = new Object();

    /* renamed from: a, reason: collision with root package name */
    private b f14105a;
    private Context d = BaseApplication.getContext();
    private e e;

    private juw() {
    }

    public static juw d() {
        juw juwVar;
        synchronized (c) {
            if (b == null) {
                b = new juw();
            }
            juwVar = b;
        }
        return juwVar;
    }

    public void b(int i, IDetectCommonCallback iDetectCommonCallback) {
        boolean d;
        if (iDetectCommonCallback == null) {
            LogUtil.h("RemoteDetectManager", "isSwitchOn callback is null");
            return;
        }
        if (i == 1) {
            d = cvz.d();
        } else if (i == 2) {
            d = NotificationContentProviderUtil.e();
        } else if (i == 3) {
            d = jrg.b();
        } else {
            LogUtil.h("RemoteDetectManager", "isSwitchOn switchType is undefined");
            b(iDetectCommonCallback, 2, null, "param invalid");
            return;
        }
        LogUtil.a("RemoteDetectManager", "isSwitchOn switchType:", Integer.valueOf(i), " isSwitch:", Boolean.valueOf(d));
        b(iDetectCommonCallback, 0, Arrays.asList(Boolean.valueOf(d)), "");
    }

    public void e(int i, IDetectCommonCallback iDetectCommonCallback) {
        List<String> i2;
        if (iDetectCommonCallback == null) {
            LogUtil.h("RemoteDetectManager", "getList callback is null");
            return;
        }
        LogUtil.a("RemoteDetectManager", "getList enter listType:", Integer.valueOf(i));
        if (i == 0) {
            i2 = NotificationContentProviderUtil.i();
        } else if (i == 1) {
            i2 = b();
        } else {
            LogUtil.h("RemoteDetectManager", "getList listType is undefined");
            b(iDetectCommonCallback, 2, null, "param invalid");
            return;
        }
        LogUtil.a("RemoteDetectManager", "getList resultList:", i2.stream().collect(Collectors.joining(" ")));
        b(iDetectCommonCallback, 0, i2, "");
    }

    public void b(String str, int i, IDetectCommonCallback iDetectCommonCallback) {
        String str2;
        Object[] objArr = new Object[6];
        objArr[0] = "goIntoPage enter pageType:";
        objArr[1] = Integer.valueOf(i);
        objArr[2] = " callback:";
        objArr[3] = Boolean.valueOf(iDetectCommonCallback == null);
        objArr[4] = " deviceUdid:";
        objArr[5] = Boolean.valueOf(TextUtils.isEmpty(str));
        LogUtil.h("RemoteDetectManager", objArr);
        if (iDetectCommonCallback == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            b(iDetectCommonCallback, 2, null, "param invalid");
            return;
        }
        List<DeviceInfo> a2 = a();
        if (a2.isEmpty()) {
            LogUtil.a("RemoteDetectManager", "goIntoPage disconnected");
            b(iDetectCommonCallback, HiHealthError.ERR_WRONG_DEVICE, null, "device disconnection");
            return;
        }
        Iterator<DeviceInfo> it = a2.iterator();
        while (true) {
            if (!it.hasNext()) {
                str2 = "";
                break;
            }
            DeviceInfo next = it.next();
            String udidFromDevice = next.getUdidFromDevice();
            if (TextUtils.isEmpty(udidFromDevice)) {
                udidFromDevice = next.getDeviceUdid();
            }
            if (str.equals(udidFromDevice)) {
                str2 = next.getDeviceIdentify();
                break;
            }
        }
        if (TextUtils.isEmpty(str2)) {
            LogUtil.a("RemoteDetectManager", "goIntoPage identify is empty");
            b(iDetectCommonCallback, 156, null, "device udid is not same");
            return;
        }
        if (i == 0) {
            e(str2);
        } else if (i == 1) {
            b(str2);
        } else {
            LogUtil.h("RemoteDetectManager", "goIntoPage pageType is undefined");
            b(iDetectCommonCallback, 2, null, "param invalid");
            return;
        }
        b(iDetectCommonCallback, 0, null, "");
    }

    public void c(int i, IDetectCommonCallback iDetectCommonCallback) {
        if (iDetectCommonCallback == null) {
            LogUtil.h("RemoteDetectManager", "isLatestVersion callback is null");
            return;
        }
        LogUtil.a("RemoteDetectManager", "isLatestVersion softwareType:", Integer.valueOf(i));
        if (!CommonUtil.aa(this.d)) {
            LogUtil.h("RemoteDetectManager", "isLatestVersion network disconnected");
            b(iDetectCommonCallback, 1004, null, "network exception");
        } else if (i == 1) {
            a(iDetectCommonCallback);
        } else if (i == 2) {
            d(iDetectCommonCallback);
        } else {
            LogUtil.h("RemoteDetectManager", "isLatestVersion softwareType is undefined");
            b(iDetectCommonCallback, 2, null, "param invalid");
        }
    }

    private void a(IDetectCommonCallback iDetectCommonCallback) {
        LogUtil.a("RemoteDetectManager", "startDetectApp enter");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action_app_check_new_version_state");
        e eVar = new e(iDetectCommonCallback);
        this.e = eVar;
        BroadcastManagerUtil.bFA_(this.d, eVar, intentFilter, LocalBroadcast.c, null);
        Intent intent = new Intent(this.d, (Class<?>) UpdateService.class);
        intent.setAction("action_app_manual_update_new_version");
        try {
            this.d.startService(intent);
        } catch (IllegalStateException unused) {
            LogUtil.b("RemoteDetectManager", "startDetectApp IllegalStateException");
        }
    }

    private void d(IDetectCommonCallback iDetectCommonCallback) {
        DeviceInfo c2 = c();
        if (c2 == null) {
            LogUtil.h("RemoteDetectManager", "startDetectDevice not device connected");
            b(iDetectCommonCallback, HiHealthError.ERR_WRONG_DEVICE, null, "device disconnection");
            return;
        }
        LogUtil.a("RemoteDetectManager", "startDetectDevice enter");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action_app_check_new_version_state");
        b bVar = new b(iDetectCommonCallback);
        this.f14105a = bVar;
        BroadcastManagerUtil.bFA_(this.d, bVar, intentFilter, LocalBroadcast.c, null);
        Intent intent = new Intent(this.d, (Class<?>) HwUpdateService.class);
        intent.putExtra("extra_band_imei", c2.getDeviceIdentify());
        intent.setAction("action_band_manual_update_new_version");
        try {
            this.d.startService(intent);
        } catch (IllegalStateException unused) {
            LogUtil.b("RemoteDetectManager", "startDetectDevice IllegalStateException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bJE_(Intent intent, IDetectCommonCallback iDetectCommonCallback) {
        int intExtra = intent.getIntExtra("state", -1);
        int intExtra2 = intent.getIntExtra("result", -1);
        String stringExtra = intent.getStringExtra("content");
        LogUtil.a("RemoteDetectManager", "appParseState intent state:", Integer.valueOf(intExtra), " result:", Integer.valueOf(intExtra2), " content:", stringExtra);
        if (intExtra == 11) {
            b(iDetectCommonCallback, 0, Arrays.asList(true), "");
            e();
            return;
        }
        if (intExtra == 12) {
            String d = d(this.d);
            LogUtil.a("RemoteDetectManager", "appParseState appNewVersion:", stringExtra, " currentVersion:", d);
            if (CommonUtil.as() || (CommonUtil.ag(this.d) && stringExtra.contains("Beta"))) {
                b(iDetectCommonCallback, 0, Arrays.asList(true), "");
                e();
                return;
            } else {
                if (CommonUtil.d(stringExtra, d) > 0) {
                    b(iDetectCommonCallback, 0, Arrays.asList(false), "");
                } else {
                    b(iDetectCommonCallback, 0, Arrays.asList(true), "");
                }
                e();
                return;
            }
        }
        LogUtil.a("RemoteDetectManager", "appParseState default");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bJF_(Intent intent, IDetectCommonCallback iDetectCommonCallback) {
        int intExtra = intent.getIntExtra("state", -1);
        int intExtra2 = intent.getIntExtra("result", -1);
        LogUtil.a("RemoteDetectManager", "deviceParseState intent state:", Integer.valueOf(intExtra), " result:", Integer.valueOf(intExtra2), " content:", intent.getStringExtra("content"));
        if (intExtra == 11) {
            b(iDetectCommonCallback, 0, Arrays.asList(true), "");
            f();
        } else if (intExtra == 12) {
            b(iDetectCommonCallback, 0, Arrays.asList(false), "");
            f();
        } else {
            LogUtil.a("RemoteDetectManager", "deviceParseState default");
        }
    }

    private void b(IDetectCommonCallback iDetectCommonCallback, int i, List list, String str) {
        try {
            iDetectCommonCallback.onResponse(i, list, str);
        } catch (RemoteException unused) {
            LogUtil.a("RemoteDetectManager", "setResponse RemoteException");
        }
    }

    private void e() {
        e eVar = this.e;
        if (eVar != null) {
            this.d.unregisterReceiver(eVar);
            this.e = null;
        }
    }

    private void f() {
        b bVar = this.f14105a;
        if (bVar != null) {
            this.d.unregisterReceiver(bVar);
            this.f14105a = null;
        }
    }

    private String d(Context context) {
        int indexOf;
        String e2 = CommonUtil.e(context);
        return (TextUtils.isEmpty(e2) || (indexOf = e2.indexOf(Constants.LINK)) <= 0) ? e2 : e2.substring(0, indexOf);
    }

    private List<DeviceInfo> a() {
        ArrayList arrayList = new ArrayList(16);
        for (DeviceInfo deviceInfo : cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "getConnectedDevices")) {
            if (deviceInfo.getDeviceConnectState() == 2) {
                arrayList.add(deviceInfo);
            }
        }
        return arrayList;
    }

    private DeviceInfo c() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "getConnectedDevices");
        if (deviceList != null && deviceList.size() != 0) {
            for (DeviceInfo deviceInfo : deviceList) {
                if (!bku.e(deviceInfo.getProductType())) {
                    return deviceInfo;
                }
            }
        }
        return null;
    }

    private List<String> b() {
        ArrayList arrayList = new ArrayList(16);
        String[] strArr = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_BACKGROUND_LOCATION"};
        for (int i = 0; i < 3; i++) {
            String str = strArr[i];
            if (this.d.checkSelfPermission(str) == 0) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    private void e(String str) {
        LogUtil.a("RemoteDetectManager", "intoNotificationActivity enter");
        try {
            Intent intent = new Intent();
            intent.setPackage(this.d.getPackageName());
            intent.setClassName(this.d, "com.huawei.ui.homewear21.home.WearHomeActivity");
            intent.putExtra("key_page_id", 0);
            intent.putExtra("device_id", str);
            intent.addFlags(268435456);
            this.d.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("RemoteDetectManager", "intoNotificationActivity ActivityNotFoundException");
        }
    }

    private void b(String str) {
        LogUtil.a("RemoteDetectManager", "intoFirmwareUpgradeActivity enter");
        try {
            Intent intent = new Intent();
            intent.setPackage(this.d.getPackageName());
            intent.setClassName(this.d, "com.huawei.ui.homewear21.home.WearHomeActivity");
            intent.putExtra("key_page_id", 1);
            intent.putExtra("device_id", str);
            intent.addFlags(268435456);
            this.d.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("RemoteDetectManager", "intoFirmwareUpgradeActivity ActivityNotFoundException");
        }
    }

    class e extends BroadcastReceiver {
        private IDetectCommonCallback b;

        e(IDetectCommonCallback iDetectCommonCallback) {
            this.b = iDetectCommonCallback;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || this.b == null) {
                LogUtil.h("RemoteDetectManager", "AppDetectionReceiver intent or mCallback is null");
                return;
            }
            if (!"type_check_app".equals(intent.getStringExtra("key_check_type"))) {
                LogUtil.a("RemoteDetectManager", "AppDetectionReceiver not app check, return");
                return;
            }
            String action = intent.getAction();
            LogUtil.a("RemoteDetectManager", "AppDetectionReceiver action: ", action);
            if ("action_app_check_new_version_state".equals(action)) {
                juw.this.bJE_(intent, this.b);
            }
        }
    }

    class b extends BroadcastReceiver {
        private IDetectCommonCallback d;

        b(IDetectCommonCallback iDetectCommonCallback) {
            this.d = iDetectCommonCallback;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || this.d == null) {
                LogUtil.h("RemoteDetectManager", "DeviceDetectionReceiver intent or mCallback is null");
                return;
            }
            if ("type_check_app".equals(intent.getStringExtra("key_check_type"))) {
                LogUtil.a("RemoteDetectManager", "DeviceDetectionReceiver is app check, return");
                return;
            }
            String action = intent.getAction();
            LogUtil.a("RemoteDetectManager", "DeviceDetectionReceiver action: ", action);
            if ("action_app_check_new_version_state".equals(action)) {
                juw.this.bJF_(intent, this.d);
            }
        }
    }
}
