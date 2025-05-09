package defpackage;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.devicesdk.entity.ExternalDeviceCapability;
import com.huawei.haf.store.SharedStoreManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class kcw {
    private static final Object c = new Object();
    private static kcw d;

    public static kcw a() {
        kcw kcwVar;
        synchronized (c) {
            if (d == null) {
                d = new kcw();
            }
            kcwVar = d;
        }
        return kcwVar;
    }

    public boolean a(DeviceInfo deviceInfo) {
        return d(deviceInfo, false);
    }

    public boolean d(DeviceInfo deviceInfo, boolean z) {
        if (deviceInfo == null) {
            LogUtil.h("DEVMGR_HwMultiDeviceUtils", "isSupportMultiConnect deviceInfo is null.");
            return false;
        }
        if (bme.b()) {
            return false;
        }
        if (TextUtils.isEmpty(deviceInfo.getExpandCapability())) {
            HwGetDevicesParameter hwGetDevicesParameter = new HwGetDevicesParameter();
            hwGetDevicesParameter.setDeviceIdentify(deviceInfo.getDeviceIdentify());
            List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.IDENTIFY_DEVICES, hwGetDevicesParameter, "DEVMGR_HwMultiDeviceUtils");
            if (deviceList != null && deviceList.size() > 0) {
                return d(deviceInfo, z, deviceList.get(0));
            }
        }
        boolean c2 = cwi.c(deviceInfo, 109);
        LogUtil.a("DEVMGR_HwMultiDeviceUtils", "isSupportMultiConnect: ", Boolean.valueOf(c2), " identify: ", CommonUtil.l(deviceInfo.getDeviceIdentify()));
        return c2;
    }

    private boolean d(DeviceInfo deviceInfo, boolean z, DeviceInfo deviceInfo2) {
        boolean z2;
        if (deviceInfo2 != null) {
            if (z && deviceInfo.getDeviceConnectState() == 1 && TextUtils.isEmpty(deviceInfo2.getExpandCapability())) {
                deviceInfo2.setExpandCapability(b(deviceInfo.getDeviceIdentify()));
            }
            z2 = cwi.c(deviceInfo2, 109);
        } else {
            z2 = false;
        }
        LogUtil.a("DEVMGR_HwMultiDeviceUtils", "isSupportMultiConnect get expandCapability: ", Boolean.valueOf(z2), " identify: ", CommonUtil.l(deviceInfo.getDeviceIdentify()));
        return z2;
    }

    private String b(String str) {
        SharedPreferences zZ_ = SharedStoreManager.zZ_("keyvaldb_encrypt_udsdevice");
        try {
            return ((ExternalDeviceCapability) new Gson().fromJson(zZ_.getString(str + "_deviceCapability", "[]"), ExternalDeviceCapability.class)).getCapacity();
        } catch (JsonSyntaxException unused) {
            LogUtil.b("DEVMGR_HwMultiDeviceUtils", "getOldExpandCapability fromJson error");
            iyv.c("MultiDeviceMessage", "getOldExpandCapability fromJson error.");
            return "";
        }
    }

    public boolean c(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("DEVMGR_HwMultiDeviceUtils", "isFollowedShipDevice deviceInfo is null.");
            return false;
        }
        boolean z = jta.d().c(deviceInfo.getProductType()) || "followed_relationship".equalsIgnoreCase(jta.d().e(deviceInfo.getDeviceIdentify()));
        LogUtil.a("DEVMGR_HwMultiDeviceUtils", "isFollowedShipDevice isFollowDevice: ", Boolean.valueOf(z));
        return z;
    }

    public void a(String str, List<DeviceInfo> list) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DEVMGR_HwMultiDeviceUtils", "setFollowedDeviceShip deviceIdentify is empty.");
            return;
        }
        if (bkz.e(list)) {
            LogUtil.h("DEVMGR_HwMultiDeviceUtils", "setFollowedDeviceShip deviceInfoList is empty.");
            return;
        }
        Iterator<DeviceInfo> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DeviceInfo next = it.next();
            if (str.equalsIgnoreCase(next.getDeviceIdentify())) {
                next.setRelationship("followed_relationship");
                break;
            }
        }
        c(list);
        jta.d().c(list, "followDeviceUpdate");
    }

    public boolean c(int i, String str) {
        boolean z = false;
        if (i == -1) {
            LogUtil.h("DEVMGR_HwMultiDeviceUtils", "isFollowExcludeAw70RunPostureDevice productType is unknown.");
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DEVMGR_HwMultiDeviceUtils", "isFollowExcludeAw70RunPostureDevice deviceIdentify is empty.");
            return false;
        }
        LogUtil.a("DEVMGR_HwMultiDeviceUtils", "isFollowExcludeAw70RunPostureDevice deviceIdentify: ", CommonUtil.l(str));
        if (jta.d().c(i) && !cvt.c(i)) {
            z = true;
        }
        LogUtil.a("DEVMGR_HwMultiDeviceUtils", "isFollowExcludeAw70RunPostureDevice: ", Boolean.valueOf(z));
        return z;
    }

    public boolean e(String str, List<DeviceInfo> list) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DEVMGR_HwMultiDeviceUtils", "isMainDevice deviceIdentify is empty.");
            return false;
        }
        if (bkz.e(list)) {
            LogUtil.h("DEVMGR_HwMultiDeviceUtils", "isMainDevice allDeviceList is empty.");
            return false;
        }
        Iterator<DeviceInfo> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DeviceInfo next = it.next();
            if (next != null && str.equals(next.getDeviceIdentify()) && "main_relationship".equals(next.getRelationship())) {
                z = true;
                break;
            }
        }
        LogUtil.a("DEVMGR_HwMultiDeviceUtils", "isMainDevice: ", Boolean.valueOf(z));
        return z;
    }

    public DeviceInfo d(List<DeviceInfo> list) {
        DeviceInfo deviceInfo = null;
        if (bkz.e(list)) {
            LogUtil.h("DEVMGR_HwMultiDeviceUtils", "getMainShipDevice allDeviceList is empty.");
            return null;
        }
        Iterator<DeviceInfo> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DeviceInfo next = it.next();
            if ("assistant_relationship".equals(next.getRelationship())) {
                deviceInfo = next;
                break;
            }
        }
        boolean b = b(list);
        LogUtil.a("DEVMGR_HwMultiDeviceUtils", "getMainShipDevice isHasAw70Run: ", Boolean.valueOf(b));
        if (deviceInfo == null) {
            LogUtil.a("DEVMGR_HwMultiDeviceUtils", "getMainShipDevice first method is null.");
            deviceInfo = a(list, b);
        }
        if (deviceInfo != null) {
            return deviceInfo;
        }
        LogUtil.a("DEVMGR_HwMultiDeviceUtils", "getMainShipDevice second method is null.");
        return c(list, b);
    }

    private DeviceInfo a(List<DeviceInfo> list, boolean z) {
        Iterator<DeviceInfo> it = list.iterator();
        while (it.hasNext()) {
            DeviceInfo next = it.next();
            LogUtil.a("DEVMGR_HwMultiDeviceUtils", "searchMainDeviceBySecond deviceIdentify: ", CommonUtil.l(next.getDeviceIdentify()));
            if (!c(next) && next.getDeviceActiveState() == 1) {
                if ((cvt.c(next.getProductType()) && next.getAutoDetectSwitchStatus() == 0 && !z) || !cvt.c(next.getProductType())) {
                    return next;
                }
                LogUtil.h("DEVMGR_HwMultiDeviceUtils", "searchMainDeviceBySecond else.");
            }
        }
        return null;
    }

    private DeviceInfo c(List<DeviceInfo> list, boolean z) {
        Iterator<DeviceInfo> it = list.iterator();
        while (it.hasNext()) {
            DeviceInfo next = it.next();
            LogUtil.a("DEVMGR_HwMultiDeviceUtils", "searchMainDeviceByThird deviceIdentify: ", CommonUtil.l(next.getDeviceIdentify()));
            if (!c(next) && next.getDeviceActiveState() == 0) {
                if ((cvt.c(next.getProductType()) && !z) || !cvt.c(next.getProductType())) {
                    return next;
                }
                LogUtil.h("DEVMGR_HwMultiDeviceUtils", "searchMainDeviceByThird else.");
            }
        }
        return null;
    }

    private boolean b(List<DeviceInfo> list) {
        for (DeviceInfo deviceInfo : list) {
            if (cvt.c(deviceInfo.getProductType()) && deviceInfo.getAutoDetectSwitchStatus() == 1 && deviceInfo.getDeviceActiveState() == 1) {
                return true;
            }
        }
        return false;
    }

    public boolean a(List<DeviceInfo> list) {
        if (bkz.e(list)) {
            LogUtil.h("DEVMGR_HwMultiDeviceUtils", "isHasMainDeviceInList deviceInfoList is empty.");
            return false;
        }
        Iterator<DeviceInfo> it = list.iterator();
        while (it.hasNext()) {
            if ("main_relationship".equalsIgnoreCase(jta.d().e(it.next().getDeviceIdentify()))) {
                return true;
            }
        }
        return false;
    }

    public List<DeviceInfo> b(String str, List<DeviceInfo> list) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DEVMGR_HwMultiDeviceUtils", "getDeleteShipDevices deviceIdentify is empty.");
            return Collections.emptyList();
        }
        if (bkz.e(list)) {
            LogUtil.h("DEVMGR_HwMultiDeviceUtils", "getDeleteShipDevices allDeviceList is empty.");
            return Collections.emptyList();
        }
        Iterator<DeviceInfo> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DeviceInfo next = it.next();
            if (str.equals(next.getDeviceIdentify())) {
                list.remove(next);
                break;
            }
        }
        return list;
    }

    public void c(List<DeviceInfo> list) {
        if (bkz.e(list)) {
            LogUtil.h("DEVMGR_HwMultiDeviceUtils", "printUpdateListShip deviceInfoList is empty.");
            return;
        }
        for (DeviceInfo deviceInfo : list) {
            ReleaseLogUtil.e("DEVMGR_HwMultiDeviceUtils", "fuzzIdentify: ", CommonUtil.l(deviceInfo.getDeviceIdentify()), ", ship: ", deviceInfo.getRelationship());
        }
    }

    public void c() {
        e();
    }

    private static void e() {
        synchronized (c) {
            d = null;
        }
    }
}
