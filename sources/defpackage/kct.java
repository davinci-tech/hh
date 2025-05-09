package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class kct {

    /* renamed from: a, reason: collision with root package name */
    private static kct f14290a;
    private static final Object d = new Object();
    private kcw e = kcw.a();

    private kct() {
    }

    public static kct c() {
        kct kctVar;
        synchronized (d) {
            if (f14290a == null) {
                LogUtil.a("HwMultiDeviceManager", "HwMultiDeviceManager init.");
                f14290a = new kct();
            }
            kctVar = f14290a;
        }
        return kctVar;
    }

    public void e(DeviceInfo deviceInfo, int i) {
        if (TextUtils.isEmpty(deviceInfo.getRelationship())) {
            String e = jta.d().e(deviceInfo.getDeviceIdentify());
            LogUtil.h("HwMultiDeviceManager", "afterUnderConnectStateChanged setRelationship:", CommonUtil.l(deviceInfo.getDeviceIdentify()), " deviceRelation: ", e);
            deviceInfo.setRelationship(e);
        }
        jub.b().a(deviceInfo, i);
    }

    public void d(DeviceInfo deviceInfo, boolean z) {
        if (deviceInfo == null) {
            LogUtil.h("HwMultiDeviceManager", "handleMultiDevice deviceInfo is null.");
            return;
        }
        LogUtil.a("HwMultiDeviceManager", "handleMultiDevice deviceIdentify: ", CommonUtil.l(deviceInfo.getDeviceIdentify()), ", isSwitchDevice: ", Boolean.valueOf(z));
        String e = jta.d().e(deviceInfo.getDeviceIdentify());
        LogUtil.a("HwMultiDeviceManager", "handleMultiDevice deviceRelation: ", e);
        if (!TextUtils.isEmpty(e) && !"unactive_relationship".equals(e) && !cvt.c(deviceInfo.getProductType()) && !z) {
            LogUtil.a("HwMultiDeviceManager", "handleMultiDevice isDeal is false.");
            return;
        }
        List<DeviceInfo> e2 = e();
        if (this.e.c(deviceInfo.getProductType(), deviceInfo.getDeviceIdentify())) {
            LogUtil.a("HwMultiDeviceManager", "handleMultiDevice current device relation is follow, no disconnect other device");
            this.e.a(deviceInfo.getDeviceIdentify(), e2);
            return;
        }
        if (this.e.a(deviceInfo)) {
            if (this.e.c(deviceInfo)) {
                return;
            }
            LogUtil.a("HwMultiDeviceManager", "handleMultiDevice support multi connect, is not follow device.");
            e(deviceInfo, e2);
            this.e.c(e2);
            jta.d().c(e2, "supportMultiUpdate");
            return;
        }
        if (cvt.c(deviceInfo.getProductType())) {
            LogUtil.a("HwMultiDeviceManager", "handleMultiDevice isAw70Device.");
            a(deviceInfo);
        } else {
            LogUtil.a("HwMultiDeviceManager", "handleMultiDevice is not Aw70Device.");
            b(deviceInfo, e2);
        }
    }

    public List<DeviceInfo> e() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "handleMultiDevice");
        for (DeviceInfo deviceInfo : deviceList) {
            if ("assistant_relationship".equals(deviceInfo.getRelationship())) {
                deviceInfo.setDeviceActiveState(1);
            }
            if ("main_relationship".equals(deviceInfo.getRelationship()) && deviceInfo.getDeviceActiveState() != 1) {
                LogUtil.a("HwMultiDeviceManager", "getAllDeviceList deviceIdentify:", CommonUtil.l(deviceInfo.getDeviceIdentify()));
                deviceInfo.setDeviceActiveState(1);
            }
        }
        return deviceList;
    }

    private void a(DeviceInfo deviceInfo) {
        List<DeviceInfo> e = e();
        if (deviceInfo.getAutoDetectSwitchStatus() == 0) {
            d(deviceInfo, e);
            LogUtil.a("HwMultiDeviceManager", "handleAw70DeviceWorkMode workMode after caseForAw70BandMode: ", Integer.valueOf(deviceInfo.getAutoDetectSwitchStatus()));
        } else if (deviceInfo.getAutoDetectSwitchStatus() == 1) {
            LogUtil.a("HwMultiDeviceManager", "handleAw70DeviceWorkMode workMode after caseForAw70RunMode: ", Integer.valueOf(deviceInfo.getAutoDetectSwitchStatus()));
            a(deviceInfo, e);
        } else {
            LogUtil.c("HwMultiDeviceManager", "handleAw70DeviceWorkMode else branch.");
        }
    }

    private void d(DeviceInfo deviceInfo, List<DeviceInfo> list) {
        for (DeviceInfo deviceInfo2 : list) {
            if (!this.e.c(deviceInfo2.getProductType(), deviceInfo2.getDeviceIdentify())) {
                if (!deviceInfo2.getDeviceIdentify().equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
                    if (deviceInfo2.getDeviceActiveState() == 1) {
                        b(deviceInfo2);
                    } else {
                        deviceInfo2.setRelationship("unactive_relationship");
                    }
                } else {
                    deviceInfo2.setRelationship("main_relationship");
                    LogUtil.a("HwMultiDeviceManager", "caseForAw70BandMode aw70 band mode set main.");
                }
            }
        }
        this.e.c(list);
        jta.d().c(list, "caseForAw70BandModeUpdate");
    }

    private void a(DeviceInfo deviceInfo, List<DeviceInfo> list) {
        for (DeviceInfo deviceInfo2 : list) {
            if (cvt.c(deviceInfo2.getProductType())) {
                if (!deviceInfo.getDeviceIdentify().equalsIgnoreCase(deviceInfo2.getDeviceIdentify())) {
                    if (deviceInfo2.getDeviceActiveState() == 1) {
                        LogUtil.a("HwMultiDeviceManager", "disconnectElseAw70Device has active aw70 device");
                        b(deviceInfo2);
                    } else {
                        deviceInfo2.setRelationship("unactive_relationship");
                    }
                } else {
                    LogUtil.a("HwMultiDeviceManager", "disconnectElseAw70Device aw70 run posture set main.");
                    deviceInfo2.setRelationship("followed_relationship");
                }
            }
        }
        this.e.c(list);
        jta.d().c(list, "disconnectElseAw70DeviceUpdate");
    }

    private void e(DeviceInfo deviceInfo, List<DeviceInfo> list) {
        for (DeviceInfo deviceInfo2 : list) {
            if (!deviceInfo2.getDeviceIdentify().equals(deviceInfo.getDeviceIdentify())) {
                if (deviceInfo2.getDeviceActiveState() == 1 && !this.e.c(deviceInfo2)) {
                    c(deviceInfo2);
                }
            } else {
                deviceInfo2.setRelationship("main_relationship");
            }
        }
    }

    private void c(DeviceInfo deviceInfo) {
        if (!this.e.d(deviceInfo, true)) {
            deviceInfo.setRelationship("unactive_relationship");
            d(deviceInfo.getDeviceIdentify());
        } else if ("main_relationship".equalsIgnoreCase(jta.d().e(deviceInfo.getDeviceIdentify()))) {
            deviceInfo.setRelationship("assistant_relationship");
        }
    }

    private void b(DeviceInfo deviceInfo, List<DeviceInfo> list) {
        for (DeviceInfo deviceInfo2 : list) {
            if (!deviceInfo2.getDeviceIdentify().equals(deviceInfo.getDeviceIdentify())) {
                if (!g(deviceInfo2)) {
                    LogUtil.h("HwMultiDeviceManager", "handleNotAw70Device is not disconnect device.");
                } else if (deviceInfo2.getDeviceActiveState() == 1) {
                    b(deviceInfo2);
                } else {
                    LogUtil.a("HwMultiDeviceManager", "handleNotAw70Device setRelationship unactive relationship.");
                    deviceInfo2.setRelationship("unactive_relationship");
                }
            } else {
                deviceInfo2.setRelationship("main_relationship");
                LogUtil.a("HwMultiDeviceManager", "handleNotAw70Device set main.");
            }
        }
        this.e.c(list);
        jta.d().c(list, "handleNotAw70DeviceUpdate");
    }

    private void b(DeviceInfo deviceInfo) {
        if (this.e.a(deviceInfo)) {
            LogUtil.a("HwMultiDeviceManager", "handleDeviceRelationship set assistant relationship.");
            deviceInfo.setRelationship("assistant_relationship");
        } else {
            deviceInfo.setRelationship("unactive_relationship");
            d(deviceInfo.getDeviceIdentify());
        }
    }

    private boolean g(DeviceInfo deviceInfo) {
        return (this.e.c(deviceInfo) || (cvt.c(deviceInfo.getProductType()) && deviceInfo.getAutoDetectSwitchStatus() == 1)) ? false : true;
    }

    public void c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HwMultiDeviceManager", "dealDeleteBusinessByShip deviceIdentify is empty.");
        } else {
            e(str);
        }
    }

    private void e(String str) {
        LogUtil.a("HwMultiDeviceManager", "getAssistantAndSetMainDevice deviceIdentify: ", CommonUtil.l(str));
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "mainDeviceUpdate");
        if (this.e.e(str, deviceList) || !this.e.a(deviceList)) {
            List<DeviceInfo> b = this.e.b(str, deviceList);
            DeviceInfo d2 = this.e.d(b);
            if (d2 != null) {
                c(d2, b);
                return;
            } else {
                this.e.c(b);
                jta.d().c(b, "isMainDeleteUpdate");
                return;
            }
        }
        List<DeviceInfo> b2 = this.e.b(str, deviceList);
        this.e.c(b2);
        jta.d().c(b2, "notMainDeleteUpdate");
    }

    private void c(DeviceInfo deviceInfo, List<DeviceInfo> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<DeviceInfo> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DeviceInfo next = it.next();
            if (next.getDeviceIdentify().equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
                next.setRelationship("main_relationship");
                break;
            }
        }
        this.e.c(list);
        jta.d().c(list, "dealDeleteUpdateShips");
    }

    private void d(String str) {
        LogUtil.a("HwMultiDeviceManager", "disconnectDevice deviceIdentify: ", CommonUtil.l(str));
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (jtd.b().e(str)) {
            LogUtil.a("HwMultiDeviceManager", "disconnectDevice isNewDeviceList");
            jtd.b().c(str);
        } else {
            LogUtil.a("HwMultiDeviceManager", "disconnectDevice disconnectDevice");
            jsz.b().a(str);
        }
        for (DeviceInfo deviceInfo : cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "disconnectDevice")) {
            if (deviceInfo != null && str.equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
                e(deviceInfo);
                return;
            }
        }
    }

    private void e(DeviceInfo deviceInfo) {
        if (deviceInfo.getDeviceBluetoothType() != 2 || iyl.d().c(deviceInfo.getDeviceIdentify())) {
            return;
        }
        LogUtil.h("HwMultiDeviceManager", "removeBTDeviceInstance Remove bond device fail.");
        iyv.c("MultiDeviceMessage", "deviceName: " + deviceInfo.getDeviceName() + ", removeBTDeviceInstance Remove bond device fail.");
    }

    public void d(DeviceInfo deviceInfo) {
        List<DeviceInfo> e = e();
        for (DeviceInfo deviceInfo2 : e) {
            if (cvt.c(deviceInfo2.getProductType()) && deviceInfo2.getDeviceActiveState() == 1 && !deviceInfo.getDeviceIdentify().equalsIgnoreCase(deviceInfo2.getDeviceIdentify())) {
                LogUtil.a("HwMultiDeviceManager", "handleSwitchScenarioAw70 has active aw70 device");
                deviceInfo.setRelationship("unactive_relationship");
                d(deviceInfo2.getDeviceIdentify());
            }
        }
        this.e.c(e);
        jta.d().c(e, "handleSwitchScenarioAw70");
    }

    public void b() {
        d();
    }

    private static void d() {
        synchronized (d) {
            f14290a = null;
        }
    }
}
