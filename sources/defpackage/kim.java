package defpackage;

import android.content.Intent;
import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes5.dex */
public class kim {

    /* renamed from: a, reason: collision with root package name */
    private static List<String> f14397a = new ArrayList(5);

    public static void bOt_(Intent intent) {
        if (intent == null) {
            LogUtil.h("HwNotificationBleBroadcast", "intent is null.");
            return;
        }
        String stringExtra = intent.getStringExtra("devType");
        if (("09C".equals(stringExtra) || "02E".equals(stringExtra)) && a(intent.getStringExtra("reqId"))) {
            return;
        }
        String stringExtra2 = intent.getStringExtra("targetDevice");
        int intExtra = intent.getIntExtra("reconnectAble", 0);
        if (TextUtils.isEmpty(stringExtra) || TextUtils.isEmpty(stringExtra2)) {
            LogUtil.h("HwNotificationBleBroadcast", "deviceType or targetDevice is null");
            return;
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "HwNotificationBleBroadcast");
        LogUtil.a("HwNotificationBleBroadcast", "deviceInfoList size", Integer.valueOf(deviceList.size()), "deviceType", stringExtra);
        if (intExtra == 1) {
            LogUtil.a("HwNotificationBleBroadcast", "enter reconnectAble");
            DeviceInfo e = e(stringExtra2);
            if (e == null) {
                return;
            }
            c(e, deviceList);
            e(e);
            return;
        }
        bOu_(intent, stringExtra, stringExtra2);
    }

    private static void bOu_(Intent intent, String str, String str2) {
        if ("09C".equals(str) || "02E".equals(str)) {
            if (d()) {
                String d = d(str2);
                if (TextUtils.isEmpty(d)) {
                    LogUtil.h("HwNotificationBleBroadcast", "identify is empty");
                    return;
                } else if (jcg.d(d) || jst.s(d)) {
                    LogUtil.h("HwNotificationBleBroadcast", "OTA is update");
                    return;
                } else {
                    jsz.b(BaseApplication.getContext()).unPair(Arrays.asList(d), false);
                    return;
                }
            }
            if (e()) {
                bOr_(intent, str2, str);
                return;
            } else {
                LogUtil.h("HwNotificationBleBroadcast", "SingleLink and MultiLink is not support");
                return;
            }
        }
        h(str2);
    }

    private static void bOr_(Intent intent, String str, String str2) {
        if (b(str)) {
            DeviceInfo e = e(str);
            if (e != null) {
                int sportVersion = e.getSportVersion();
                if (sportVersion != -1) {
                    LogUtil.a("HwNotificationBleBroadcast", "dealMultiLinkMessage sportVersion not default value, use new logic");
                    if (sportVersion == 1) {
                        LogUtil.a("HwNotificationBleBroadcast", "dealMultiLinkMessage sportVersion is one");
                        b();
                        return;
                    } else {
                        bOs_(intent, str, str2);
                        return;
                    }
                }
                DeviceCapability d = jst.d(d(str));
                if (d != null && d.isSupportSmartWatchVersionStatus()) {
                    LogUtil.a("HwNotificationBleBroadcast", "enter sendSeizeMessageToDevice");
                    b();
                    return;
                } else {
                    bOs_(intent, str, str2);
                    return;
                }
            }
            LogUtil.h("HwNotificationBleBroadcast", "dealMultiLinkMessage deviceInfo is null");
        }
    }

    private static void bOs_(Intent intent, String str, String str2) {
        LogUtil.a("HwNotificationBleBroadcast", "dealSportDeviceSeize exercise sport enter.");
        DeviceCommand c = c(intent.getStringExtra("reqId"), str2, intent.getIntExtra("timeout", 30), d(str));
        if (c == null) {
            LogUtil.h("HwNotificationBleBroadcast", "cloud push info is wrong, please check cloud data.");
        } else {
            jsz.b(BaseApplication.getContext()).sendDeviceData(c);
        }
    }

    private static DeviceInfo e(String str) {
        for (DeviceInfo deviceInfo : cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "HwNotificationBleBroadcast")) {
            if (deviceInfo.getDeviceIdentify().replace(":", "").endsWith(str)) {
                LogUtil.c("HwNotificationBleBroadcast", "btDeviceIdentify", deviceInfo.getDeviceIdentify());
                return deviceInfo;
            }
        }
        return null;
    }

    private static void h(String str) {
        if (d()) {
            String d = d(str);
            if (TextUtils.isEmpty(d)) {
                LogUtil.h("HwNotificationBleBroadcast", "identify is empty");
                return;
            } else if (jcg.d(d) || jst.s(d)) {
                LogUtil.h("HwNotificationBleBroadcast", "OTA is update");
                return;
            } else {
                jsz.b(BaseApplication.getContext()).unPair(Arrays.asList(d), false);
                return;
            }
        }
        LogUtil.h("HwNotificationBleBroadcast", "SingleLink and MultiLink is not support");
    }

    private static String d(String str) {
        for (DeviceInfo deviceInfo : cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "HwNotificationBleBroadcast")) {
            if (deviceInfo.getDeviceIdentify().replace(":", "").endsWith(str)) {
                LogUtil.c("HwNotificationBleBroadcast", "getDeviceIdentify", deviceInfo.getDeviceIdentify());
                return deviceInfo.getDeviceIdentify();
            }
        }
        return "";
    }

    private static boolean d() {
        DeviceInfo a2 = a();
        if (a2 == null) {
            LogUtil.h("HwNotificationBleBroadcast", "isSupportSingleLink: no device connect. can not push device.");
            return false;
        }
        boolean a3 = CommonUtil.a(cvx.a(a2.getExpandCapability()), 51);
        LogUtil.a("HwNotificationBleBroadcast", "isSupportSingleLink: ", Boolean.valueOf(a3));
        return a3;
    }

    private static boolean e() {
        DeviceInfo a2 = a();
        if (a2 == null) {
            LogUtil.h("HwNotificationBleBroadcast", "no device connect. can not push device.");
            return false;
        }
        boolean a3 = CommonUtil.a(cvx.a(a2.getExpandCapability()), 25);
        LogUtil.a("HwNotificationBleBroadcast", "isSupportMultiLink: ", Boolean.valueOf(a3));
        return a3;
    }

    private static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        DeviceInfo a2 = a();
        if (a2 == null) {
            LogUtil.h("HwNotificationBleBroadcast", "no device connect. can not push device.");
            return false;
        }
        if (str.equalsIgnoreCase(c(a2.getMultiLinkBleMac()))) {
            return true;
        }
        LogUtil.h("HwNotificationBleBroadcast", "not target device : ", str);
        return false;
    }

    private static void c(DeviceInfo deviceInfo, List<DeviceInfo> list) {
        if (deviceInfo == null || list == null) {
            return;
        }
        if (cvt.c(deviceInfo.getProductType())) {
            LogUtil.a("HwNotificationBleBroadcast", "handleWorkMode goingReConnected == AW70");
            for (DeviceInfo deviceInfo2 : list) {
                if (deviceInfo.getDeviceIdentify().equalsIgnoreCase(deviceInfo2.getDeviceIdentify())) {
                    LogUtil.a("HwNotificationBleBroadcast", "Reconnected AW70 set device enable");
                    deviceInfo2.setDeviceActiveState(1);
                    deviceInfo2.setDeviceConnectState(1);
                } else if (cvt.c(deviceInfo2.getProductType())) {
                    LogUtil.a("HwNotificationBleBroadcast", "Connected AW70 target device disable");
                    deviceInfo2.setDeviceActiveState(0);
                    deviceInfo2.setDeviceConnectState(3);
                } else {
                    LogUtil.h("HwNotificationBleBroadcast", "handleDeviceState is other");
                }
            }
            return;
        }
        LogUtil.a("HwNotificationBleBroadcast", "handleWorkMode goingConnected == DeviceWorkMode.BAND_MODE");
        for (DeviceInfo deviceInfo3 : list) {
            if (deviceInfo.getDeviceIdentify().equalsIgnoreCase(deviceInfo3.getDeviceIdentify())) {
                LogUtil.a("HwNotificationBleBroadcast", "handleWorkMode set device enable");
                deviceInfo3.setDeviceActiveState(1);
                deviceInfo3.setDeviceConnectState(1);
            }
            if (!deviceInfo.getDeviceIdentify().equalsIgnoreCase(deviceInfo3.getDeviceIdentify()) && deviceInfo3.getAutoDetectSwitchStatus() != 1 && deviceInfo3.getDeviceActiveState() == 1) {
                LogUtil.a("HwNotificationBleBroadcast", "handleWorkMode target device disable");
                deviceInfo3.setDeviceActiveState(0);
                deviceInfo3.setDeviceConnectState(3);
            }
        }
    }

    private static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String replaceAll = str.replaceAll(":", "");
        int length = replaceAll.length();
        if (length > 3) {
            return replaceAll.substring(length - 3, length);
        }
        return null;
    }

    private static DeviceCommand c(String str, String str2, int i, String str3) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HwNotificationBleBroadcast", "requestId is empty.");
            return null;
        }
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("HwNotificationBleBroadcast", "deviceType is empty.");
            return null;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceId(2);
        deviceCommand.setCommandId(15);
        StringBuilder sb = new StringBuilder(16);
        String c = cvx.c(str);
        sb.append(cvx.e(1));
        sb.append(cvx.d(c.length() / 2));
        sb.append(c);
        String c2 = cvx.c(str2);
        sb.append(cvx.e(2));
        sb.append(cvx.d(c2.length() / 2));
        sb.append(c2);
        String e = cvx.e(i);
        sb.append(cvx.e(3));
        sb.append(cvx.d(e.length() / 2));
        sb.append(e);
        byte[] a2 = cvx.a(sb.toString());
        deviceCommand.setDataLen(a2.length);
        deviceCommand.setDataContent(a2);
        deviceCommand.setmIdentify(str3);
        return deviceCommand;
    }

    private static DeviceInfo a() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HwNotificationBleBroadcast");
        if (deviceList == null || deviceList.size() <= 0) {
            return null;
        }
        for (DeviceInfo deviceInfo : deviceList) {
            if (!cvt.c(deviceInfo.getProductType())) {
                return deviceInfo;
            }
        }
        return null;
    }

    private static void b() {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(1);
        deviceCommand.setCommandID(58);
        String e = cvx.e(1);
        StringBuilder sb = new StringBuilder(16);
        sb.append(cvx.e(1));
        sb.append(cvx.e(1));
        sb.append(e);
        deviceCommand.setDataLen(sb.length());
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        LogUtil.a("HwNotificationBleBroadcast", "sendSeizeMessage:", deviceCommand.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    private static void e(DeviceInfo deviceInfo) {
        com.huawei.devicesdk.entity.DeviceInfo deviceInfo2 = new com.huawei.devicesdk.entity.DeviceInfo();
        deviceInfo2.setDeviceBtType(deviceInfo.getDeviceBluetoothType());
        deviceInfo2.setDeviceName(deviceInfo.getDeviceName());
        deviceInfo2.setDeviceMac(deviceInfo.getDeviceIdentify());
        deviceInfo2.setDeviceType(deviceInfo.getProductType());
        jsz.b(BaseApplication.getContext()).connectDevice(deviceInfo2, 0);
    }

    private static boolean a(String str) {
        for (int i = 0; i < f14397a.size(); i++) {
            if (str.equals(f14397a.get(i))) {
                LogUtil.a("HwNotificationBleBroadcast", "requestId is repeated");
                return true;
            }
        }
        if (f14397a.size() >= 5) {
            f14397a.remove(0);
        }
        f14397a.add(str);
        return false;
    }
}
