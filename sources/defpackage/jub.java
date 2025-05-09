package defpackage;

import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class jub implements ParserInterface {
    private static jub c;

    /* renamed from: a, reason: collision with root package name */
    private static final Object f14085a = new Object();
    private static Map<String, Integer> e = new HashMap(16);

    private jub() {
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        if (bme.b()) {
            LogUtil.h("DEVMGR_AutoSwitchManager", "getResult is not debug version.");
            return;
        }
        if (deviceInfo == null || bArr == null) {
            LogUtil.h("DEVMGR_AutoSwitchManager", "getResult deviceInfo is null or dataInfos is null.");
            return;
        }
        String d = cvx.d(bArr);
        LogUtil.a("DEVMGR_AutoSwitchManager", "getResult message:", d, " deviceIdentify:", CommonUtil.l(deviceInfo.getDeviceIdentify()));
        if (d.length() < 4) {
            iyv.c("MultiDeviceMessage", "deviceName: " + deviceInfo.getDeviceName() + ", tlv length is error");
            return;
        }
        try {
            List<cwd> e2 = new cwl().a(d.substring(4)).e();
            byte b = bArr[1];
            if (b == 61) {
                b(e2, deviceInfo);
            } else if (b == 62) {
                e(e2, deviceInfo);
            } else {
                LogUtil.h("DEVMGR_AutoSwitchManager", "Unsupported command");
            }
        } catch (cwg unused) {
            iyv.c("MultiDeviceMessage", "deviceName: " + deviceInfo.getDeviceName() + ", tlv structure is error");
            LogUtil.b("DEVMGR_AutoSwitchManager", "getResult TlvException");
        }
    }

    private void b(List<cwd> list, DeviceInfo deviceInfo) {
        if (list == null || list.size() == 0) {
            LogUtil.h("DEVMGR_AutoSwitchManager", "receiveDeviceReportStatus tlvList is error.");
            return;
        }
        for (cwd cwdVar : list) {
            if (CommonUtil.w(cwdVar.e()) == 1) {
                b(deviceInfo, CommonUtil.w(cwdVar.c()));
            } else {
                LogUtil.h("DEVMGR_AutoSwitchManager", "receiveDeviceReportStatus parameter is invalid.");
            }
        }
    }

    private void e(List<cwd> list, DeviceInfo deviceInfo) {
        if (list == null || list.size() == 0) {
            LogUtil.h("DEVMGR_AutoSwitchManager", "receiveDevicePreempt tlvList is error.");
            return;
        }
        for (cwd cwdVar : list) {
            int w = CommonUtil.w(cwdVar.e());
            if (w == 1) {
                c(deviceInfo, CommonUtil.w(cwdVar.c()));
            } else if (w == 2) {
                LogUtil.h("DEVMGR_AutoSwitchManager", "receiveDevicePreempt usedDeviceName :", cvx.e(cwdVar.c()));
            } else {
                LogUtil.h("DEVMGR_AutoSwitchManager", "receiveDeviceReportStatus parameter is invalid.");
            }
        }
    }

    private void c(DeviceInfo deviceInfo, int i) {
        if (i != 1) {
            LogUtil.h("DEVMGR_AutoSwitchManager", "handleDevicePreempt state is invalid.");
            iyv.c("MultiDeviceMessage", "deviceName: " + deviceInfo.getDeviceName() + ", handleDevicePreempt state is invalid.");
            return;
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ACTIVE_MAIN_DEVICES, null, "DEVMGR_AutoSwitchManager");
        DeviceInfo deviceInfo2 = (deviceList == null || deviceList.size() == 0) ? null : deviceList.get(0);
        if (deviceInfo2 == null) {
            LogUtil.h("DEVMGR_AutoSwitchManager", "handleDevicePreempt mainDevice is null.");
            b((DeviceInfo) null, deviceInfo);
        } else {
            b(deviceInfo2, deviceInfo);
        }
    }

    private void b(DeviceInfo deviceInfo, int i) {
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        if (TextUtils.isEmpty(deviceIdentify)) {
            LogUtil.h("DEVMGR_AutoSwitchManager", "deviceIdentify is empty.");
            return;
        }
        int intValue = e.containsKey(deviceIdentify) ? e.get(deviceIdentify).intValue() : 1;
        LogUtil.a("DEVMGR_AutoSwitchManager", "wearState change oldWearState: ", Integer.valueOf(intValue), " newWearState: ", Integer.valueOf(i), " deviceIdentify:", CommonUtil.l(deviceIdentify));
        e.put(deviceIdentify, Integer.valueOf(i == 3 ? 1 : i));
        if (intValue == i) {
            return;
        }
        if (i == 3) {
            i = 1;
        }
        d(deviceInfo, i);
    }

    private void d(final DeviceInfo deviceInfo, final int i) {
        jqi.a().getSwitchSetting("device_auto_switch", new IBaseResponseCallback() { // from class: jub.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                LogUtil.a("DEVMGR_AutoSwitchManager", "getAutoSwitchSetting errorCode: ", Integer.valueOf(i2));
                boolean equals = (i2 == 0 && (obj instanceof String)) ? "1".equals((String) obj) : true;
                LogUtil.a("DEVMGR_AutoSwitchManager", "getAutoSwitchSetting isEnable: ", Boolean.valueOf(equals));
                if (equals) {
                    jub.this.e(deviceInfo, i);
                } else {
                    LogUtil.a("DEVMGR_AutoSwitchManager", "getAutoSwitchSetting wearing status switch is not on");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(DeviceInfo deviceInfo, int i) {
        for (DeviceInfo deviceInfo2 : cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "DEVMGR_AutoSwitchManager")) {
            if (deviceInfo.getDeviceIdentify().equals(deviceInfo2.getDeviceIdentify())) {
                String relationship = deviceInfo2.getRelationship();
                if ("main_relationship".equals(relationship)) {
                    f(deviceInfo, i);
                    return;
                } else if ("assistant_relationship".equals(relationship)) {
                    h(deviceInfo, i);
                    return;
                } else {
                    LogUtil.h("DEVMGR_AutoSwitchManager", "The device status is not main or assistant relationship: ", relationship);
                    return;
                }
            }
        }
    }

    private void f(DeviceInfo deviceInfo, int i) {
        if (i == 2) {
            LogUtil.a("DEVMGR_AutoSwitchManager", "processMainDeviceStateChange device is already main: ", CommonUtil.l(deviceInfo.getDeviceIdentify()));
            return;
        }
        if (i == 1) {
            if (jcg.d(deviceInfo.getDeviceIdentify()) || jst.s(deviceInfo.getDeviceIdentify())) {
                LogUtil.a("DEVMGR_AutoSwitchManager", "doSwitchDevice main device is ota updating, return");
                return;
            }
            DeviceInfo deviceInfo2 = null;
            for (DeviceInfo deviceInfo3 : cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "DEVMGR_AutoSwitchManager")) {
                LogUtil.a("DEVMGR_AutoSwitchManager", "processMainDeviceStateChange connectDeviceInfo: ", CommonUtil.l(deviceInfo3.getDeviceIdentify()), " relationship: ", deviceInfo3.getRelationship());
                if ("assistant_relationship".equals(deviceInfo3.getRelationship())) {
                    String deviceIdentify = deviceInfo3.getDeviceIdentify();
                    int c2 = c(deviceIdentify);
                    LogUtil.a("DEVMGR_AutoSwitchManager", "processMainDeviceStateChange assistantDeviceIdentify: ", CommonUtil.l(deviceIdentify), " assistantDeviceWearState: ", Integer.valueOf(c2), " lastConnectedTime: ", Long.valueOf(deviceInfo.getLastConnectedTime()));
                    if (c2 == 2) {
                        if (deviceInfo2 != null) {
                            if (deviceInfo3.getLastConnectedTime() > deviceInfo2.getLastConnectedTime()) {
                            }
                        }
                        deviceInfo2 = deviceInfo3;
                    }
                }
            }
            if (deviceInfo2 == null) {
                LogUtil.a("DEVMGR_AutoSwitchManager", "processMainDeviceStateChange cannot find the device to switch");
                return;
            } else {
                b(deviceInfo, deviceInfo2);
                return;
            }
        }
        LogUtil.a("DEVMGR_AutoSwitchManager", "processMainDeviceStateChange wearState is error");
    }

    private boolean c(DeviceInfo deviceInfo) {
        LogUtil.h("DEVMGR_AutoSwitchManager", "isSetUpDeviceStatus main device: ", CommonUtil.l(deviceInfo.getDeviceIdentify()));
        if (!kcw.a().a(deviceInfo)) {
            LogUtil.a("DEVMGR_AutoSwitchManager", "isSetUpDeviceStatus main device no support multi connect.");
            return true;
        }
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        if (c(deviceIdentify) == 2) {
            LogUtil.a("DEVMGR_AutoSwitchManager", "isSetUpDeviceStatus main device is wore.");
            return true;
        }
        if (!jcg.d(deviceIdentify) && !jst.s(deviceIdentify)) {
            return false;
        }
        LogUtil.a("DEVMGR_AutoSwitchManager", "isSetUpDeviceStatus main device is ota updating.");
        return true;
    }

    private int c(String str) {
        Integer num = e.get(str);
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    private void h(DeviceInfo deviceInfo, int i) {
        if (i == 1) {
            LogUtil.h("DEVMGR_AutoSwitchManager", "processAssistantDeviceStateChange device is already assistant: ", CommonUtil.l(deviceInfo.getDeviceIdentify()));
            return;
        }
        if (i == 2) {
            List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ACTIVE_MAIN_DEVICES, null, "DEVMGR_AutoSwitchManager");
            DeviceInfo deviceInfo2 = (deviceList == null || deviceList.size() == 0) ? null : deviceList.get(0);
            if (deviceInfo2 == null) {
                LogUtil.a("DEVMGR_AutoSwitchManager", "processAssistantDeviceStateChange have no mainDevice");
                b((DeviceInfo) null, deviceInfo);
                return;
            }
            if (deviceInfo2.getDeviceConnectState() == 1) {
                LogUtil.a("DEVMGR_AutoSwitchManager", "processAssistantDeviceStateChange mainDevice is connecting, return.");
                return;
            }
            if (deviceInfo2.getDeviceConnectState() != 2) {
                LogUtil.a("DEVMGR_AutoSwitchManager", "processAssistantDeviceStateChange mainDevice is not connected");
                b(deviceInfo2, deviceInfo);
                return;
            }
            int c2 = c(deviceInfo2.getDeviceIdentify());
            if (c(deviceInfo2)) {
                ReleaseLogUtil.e("DEVMGR_AutoSwitchManager", "processAssistantDeviceStateChange mainDeviceWearState is wear");
                e(deviceInfo, 2, deviceInfo2.getDeviceName(), 1);
                return;
            } else {
                LogUtil.a("DEVMGR_AutoSwitchManager", "processAssistantDeviceStateChange mainDeviceWearState is not wear: ", Integer.valueOf(c2));
                b(deviceInfo2, deviceInfo);
                return;
            }
        }
        LogUtil.a("DEVMGR_AutoSwitchManager", "processAssistantDeviceStateChange wearState is error");
    }

    private void b(DeviceInfo deviceInfo, DeviceInfo deviceInfo2) {
        String str = "";
        String deviceIdentify = deviceInfo != null ? deviceInfo.getDeviceIdentify() : "";
        if (deviceInfo2 != null) {
            str = deviceInfo2.getDeviceIdentify();
            e(deviceInfo2.getDeviceName());
        }
        ReleaseLogUtil.e("DEVMGR_AutoSwitchManager", "doSwitchDevice oldIdentify: ", CommonUtil.l(deviceIdentify), " newIdentify: ", CommonUtil.l(str));
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "DEVMGR_AutoSwitchManager");
        for (DeviceInfo deviceInfo3 : deviceList) {
            if (str != null && str.equals(deviceInfo3.getDeviceIdentify())) {
                deviceInfo3.setDeviceActiveState(1);
            }
        }
        jsz.b(BaseApplication.getContext()).switchDevice(deviceList, str);
    }

    private void e(String str) {
        if (TextUtils.isEmpty(SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10024), "firstReportSwitch"))) {
            LogUtil.a("DEVMGR_AutoSwitchManager", "handleFirstReportSwitch sp value is empty.", CommonUtil.l(str));
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10024), "firstReportSwitch", "1", new StorageParams());
            if (TextUtils.isEmpty(str)) {
                return;
            }
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10024), "firstReportSwitchDeviceName", str, new StorageParams());
        }
    }

    public void e(DeviceInfo deviceInfo, int i, String str, int i2) {
        if (bme.b()) {
            LogUtil.h("DEVMGR_AutoSwitchManager", "setUpDeviceStatus is not debug version.");
            return;
        }
        if (!kcw.a().a(deviceInfo)) {
            LogUtil.a("DEVMGR_AutoSwitchManager", "setUpDeviceStatus current device is not support dual device: ", CommonUtil.l(deviceInfo.getDeviceIdentify()));
            return;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(1);
        deviceCommand.setCommandID(62);
        String e2 = cvx.e(i);
        String str2 = cvx.e(1) + cvx.e(e2.length() / 2) + e2;
        String c2 = cvx.c(str);
        String str3 = cvx.e(2) + cvx.d(c2.length() / 2) + c2;
        String e3 = cvx.e(i2);
        String str4 = str2 + str3 + (cvx.e(3) + cvx.e(e3.length() / 2) + e3);
        deviceCommand.setDataContent(cvx.a(str4));
        deviceCommand.setDataLen(cvx.a(str4).length);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        LogUtil.a("DEVMGR_AutoSwitchManager", "setUpDeviceStatus: ", deviceCommand.toString(), " device: ", CommonUtil.l(deviceInfo.getDeviceIdentify()));
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public static jub b() {
        jub jubVar;
        synchronized (f14085a) {
            if (c == null) {
                c = new jub();
            }
            jubVar = c;
        }
        return jubVar;
    }

    public void a(DeviceInfo deviceInfo, int i) {
        if (bme.b()) {
            LogUtil.h("DEVMGR_AutoSwitchManager", "afterUnderConnectStateChanged is not debug version.");
            return;
        }
        if (deviceInfo == null) {
            LogUtil.h("DEVMGR_AutoSwitchManager", "deviceInfo is null");
            return;
        }
        if (!kcw.a().a(deviceInfo)) {
            LogUtil.h("DEVMGR_AutoSwitchManager", "afterUnderConnectStateChanged current device is not support dual device: ", CommonUtil.l(deviceInfo.getDeviceIdentify()), " btState:", Integer.valueOf(i));
            return;
        }
        LogUtil.a("DEVMGR_AutoSwitchManager", "afterUnderConnectStateChanged deviceIdentify:", CommonUtil.l(deviceInfo.getDeviceIdentify()), " btState:", Integer.valueOf(i));
        if (i == 3) {
            b(deviceInfo, 3);
        } else if (i == 2) {
            b(deviceInfo);
            d(deviceInfo);
        } else {
            LogUtil.a("DEVMGR_AutoSwitchManager", "afterUnderConnectStateChanged other connect state");
        }
    }

    private void b(DeviceInfo deviceInfo) {
        DeviceInfo deviceInfo2 = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ACTIVE_MAIN_DEVICES, null, "DEVMGR_AutoSwitchManager");
        if (deviceList != null && deviceList.size() != 0) {
            deviceInfo2 = deviceList.get(0);
        }
        String deviceName = deviceInfo2 != null ? deviceInfo2.getDeviceName() : "";
        if ("main_relationship".equals(deviceInfo.getRelationship())) {
            e(deviceInfo, 1, deviceName, 0);
        } else if ("assistant_relationship".equals(deviceInfo.getRelationship())) {
            e(deviceInfo, 2, deviceName, 0);
        } else {
            LogUtil.a("DEVMGR_AutoSwitchManager", "beforeSendConnectStateBroadcast relationship is :", deviceInfo.getRelationship());
        }
    }

    private void d(DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(1);
        deviceCommand.setCommandID(61);
        String str = cvx.e(1) + cvx.e(0);
        deviceCommand.setDataContent(cvx.a(str));
        deviceCommand.setDataLen(cvx.a(str).length);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        LogUtil.a("DEVMGR_AutoSwitchManager", "queryWearStatus: ", deviceCommand.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }
}
