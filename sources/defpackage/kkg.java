package defpackage;

import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.SportIntensityUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class kkg {
    public static void e(DeviceInfo deviceInfo) {
        LogUtil.a("HwHeartAbility", "notifyDeviceHeartCapability enter");
        if (deviceInfo == null) {
            LogUtil.h("HwHeartAbility", "notifyDeviceHeartCapability btDeviceInfo is null");
            return;
        }
        if (deviceInfo.getDeviceConnectState() != 2) {
            LogUtil.h("HwHeartAbility", "notifyDeviceHeartCapability device disconnected");
            return;
        }
        DeviceCapability b = b(deviceInfo);
        if (b == null || !b.isSupportWorkoutTrustHeartRate()) {
            LogUtil.h("HwHeartAbility", "device not support trust heart rate.");
            return;
        }
        String str = cvx.e(1) + cvx.d(4) + "00000003";
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(23);
        deviceCommand.setCommandID(23);
        deviceCommand.setDataContent(cvx.a(str));
        deviceCommand.setDataLen(cvx.a(str).length);
        deviceCommand.setPriority(1);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        LogUtil.a("HwHeartAbility", "notifyDeviceHeartCapability", "sendDeviceData command", deviceCommand.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public static void a(DeviceInfo deviceInfo) {
        LogUtil.a("HwHeartAbility", "notifyDeviceNewRestHeartRateCapability enter");
        if (deviceInfo == null) {
            LogUtil.h("HwHeartAbility", "btDeviceInfo is null");
            return;
        }
        if (deviceInfo.getDeviceConnectState() != 2) {
            LogUtil.h("HwHeartAbility", "connect status is not connected");
            return;
        }
        DeviceCapability b = b(deviceInfo);
        if (b == null || !b.isSupportRestHeartRateControls()) {
            LogUtil.h("HwHeartAbility", "device not support new rest heart rate.");
            return;
        }
        String str = cvx.e(1) + cvx.d(4) + "00000001";
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(35);
        deviceCommand.setDataContent(cvx.a(str));
        deviceCommand.setDataLen(cvx.a(str).length);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        LogUtil.a("HwHeartAbility", "notifyDeviceNewRestHeartRateCapability", "sendDeviceData command", deviceCommand.toString());
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public static void d(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("HwHeartAbility", "sendMediumToHighStrength btDeviceInfo is null");
            return;
        }
        if (deviceInfo.getDeviceConnectState() != 2) {
            LogUtil.h("HwHeartAbility", "sendMediumToHighStrength device not connect");
            return;
        }
        DeviceCapability b = b(deviceInfo);
        if (b == null || !b.isSupportMediumToHighStrengthPreValue()) {
            LogUtil.h("HwHeartAbility", "sendMediumToHighStrength device not support Medium To High Strength PreValue");
            return;
        }
        mxf b2 = SportIntensityUtil.b();
        if (jhc.h()) {
            LogUtil.a("HwHeartAbility", "is new device");
            c(b2, deviceInfo);
        } else {
            d(b2, deviceInfo);
        }
    }

    private static void d(mxf mxfVar, DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(41);
        StringBuilder sb = new StringBuilder(16);
        StringBuilder sb2 = new StringBuilder(16);
        StringBuilder sb3 = new StringBuilder(16);
        StringBuilder sb4 = new StringBuilder(16);
        StringBuilder sb5 = new StringBuilder(16);
        StringBuilder sb6 = new StringBuilder(16);
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(sb);
        arrayList.add(sb2);
        arrayList.add(sb3);
        arrayList.add(sb4);
        arrayList.add(sb5);
        arrayList.add(sb6);
        e(arrayList, mxfVar);
        byte[] a2 = cvx.a(sb.toString() + sb2.toString() + sb6.toString() + sb3.toString() + sb4.toString() + sb5.toString());
        deviceCommand.setDataLen(a2.length);
        deviceCommand.setDataContent(a2);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        LogUtil.a("HwHeartAbility", "sendMediumToHighStrength dataInfos:", Arrays.toString(a2));
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    private static void c(mxf mxfVar, DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(41);
        StringBuilder sb = new StringBuilder(16);
        StringBuilder sb2 = new StringBuilder(16);
        StringBuilder sb3 = new StringBuilder(16);
        StringBuilder sb4 = new StringBuilder(16);
        StringBuilder sb5 = new StringBuilder(16);
        StringBuilder sb6 = new StringBuilder(16);
        StringBuilder sb7 = new StringBuilder(16);
        StringBuilder sb8 = new StringBuilder(16);
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(sb);
        arrayList.add(sb2);
        arrayList.add(sb3);
        arrayList.add(sb4);
        arrayList.add(sb5);
        arrayList.add(sb6);
        arrayList.add(sb7);
        arrayList.add(sb8);
        d(arrayList, mxfVar);
        byte[] a2 = cvx.a(sb.toString() + sb2.toString() + sb6.toString() + sb3.toString() + sb4.toString() + sb5.toString() + sb7.toString() + sb8.toString());
        deviceCommand.setDataLen(a2.length);
        deviceCommand.setDataContent(a2);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        LogUtil.a("HwHeartAbility", "sendMediumToHighStrength dataInfos:", Arrays.toString(a2));
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    private static void e(List<StringBuilder> list, mxf mxfVar) {
        StringBuilder sb = list.get(0);
        sb.append(cvx.e(1));
        sb.append(cvx.e(1));
        sb.append(cvx.e(mxfVar.l()));
        StringBuilder sb2 = list.get(1);
        sb2.append(cvx.e(2));
        sb2.append(cvx.e(1));
        sb2.append(cvx.e(mxfVar.c()));
        StringBuilder sb3 = list.get(2);
        sb3.append(cvx.e(3));
        sb3.append(cvx.e(1));
        sb3.append(cvx.e(mxfVar.g()));
        StringBuilder sb4 = list.get(3);
        sb4.append(cvx.e(4));
        sb4.append(cvx.e(1));
        sb4.append(cvx.e(mxfVar.d()));
        StringBuilder sb5 = list.get(4);
        sb5.append(cvx.e(5));
        sb5.append(cvx.e(1));
        sb5.append(cvx.e(mxfVar.h()));
        StringBuilder sb6 = list.get(5);
        sb6.append(cvx.e(6));
        sb6.append(cvx.e(1));
        sb6.append(cvx.e(mxfVar.a()));
    }

    private static void d(List<StringBuilder> list, mxf mxfVar) {
        StringBuilder sb = list.get(0);
        sb.append(cvx.e(1));
        sb.append(cvx.e(1));
        sb.append(cvx.e(mxfVar.k()));
        StringBuilder sb2 = list.get(1);
        sb2.append(cvx.e(2));
        sb2.append(cvx.e(1));
        sb2.append(cvx.e(mxfVar.e()));
        StringBuilder sb3 = list.get(2);
        sb3.append(cvx.e(3));
        sb3.append(cvx.e(1));
        sb3.append(cvx.e(mxfVar.j()));
        StringBuilder sb4 = list.get(3);
        sb4.append(cvx.e(4));
        sb4.append(cvx.e(1));
        sb4.append(cvx.e(mxfVar.i()));
        StringBuilder sb5 = list.get(4);
        sb5.append(cvx.e(5));
        sb5.append(cvx.e(1));
        sb5.append(cvx.e(mxfVar.f()));
        StringBuilder sb6 = list.get(5);
        sb6.append(cvx.e(6));
        sb6.append(cvx.e(1));
        sb6.append(cvx.e(mxfVar.b()));
        StringBuilder sb7 = list.get(6);
        sb7.append(cvx.e(7));
        sb7.append(cvx.e(1));
        sb7.append(cvx.e(mxfVar.n()));
        StringBuilder sb8 = list.get(7);
        sb8.append(cvx.e(8));
        sb8.append(cvx.e(1));
        sb8.append(cvx.e(mxfVar.m()));
    }

    private static DeviceCapability b(DeviceInfo deviceInfo) {
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(1, deviceInfo.getDeviceIdentify(), "HwHeartAbility");
        if (queryDeviceCapability == null || queryDeviceCapability.isEmpty()) {
            LogUtil.h("HwHeartAbility", "getCapability, deviceCapabilityHashMaps is empty");
            return null;
        }
        return queryDeviceCapability.get(deviceInfo.getDeviceIdentify());
    }
}
