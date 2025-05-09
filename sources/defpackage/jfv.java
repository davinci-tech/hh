package defpackage;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.outofprocess.mgr.device.CloudDeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class jfv {

    /* renamed from: a, reason: collision with root package name */
    private static int f13799a;
    private static final Object d = new Object();

    private static List<CloudDeviceInfo> j() {
        synchronized (d) {
            ArrayList arrayList = new ArrayList(0);
            if (!LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode() && !Utils.o()) {
                List<CloudDeviceInfo> h = h();
                f13799a = h.size();
                Collections.sort(h, new Comparator<DeviceInfo>() { // from class: jfv.2
                    @Override // java.util.Comparator
                    /* renamed from: b, reason: merged with bridge method [inline-methods] */
                    public int compare(DeviceInfo deviceInfo, DeviceInfo deviceInfo2) {
                        if (deviceInfo == null || deviceInfo2 == null) {
                            return 0;
                        }
                        if (deviceInfo2.getLastConnectedTime() > deviceInfo.getLastConnectedTime()) {
                            return 1;
                        }
                        return deviceInfo2.getLastConnectedTime() < deviceInfo.getLastConnectedTime() ? -1 : 0;
                    }
                });
                LogUtil.a("WearDeviceUtils", "getStorageCloudDevices size:", Integer.valueOf(h.size()));
                return h;
            }
            f13799a = 0;
            return arrayList;
        }
    }

    public static int c() {
        int i;
        synchronized (d) {
            i = f13799a;
        }
        return i;
    }

    public static void c(DeviceInfo deviceInfo) {
        synchronized (d) {
            if (deviceInfo == null) {
                return;
            }
            List<CloudDeviceInfo> j = j();
            Iterator<CloudDeviceInfo> it = j.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                CloudDeviceInfo next = it.next();
                if (deviceInfo.getDeviceName().equals(next.getDeviceName()) && deviceInfo.getProductType() == next.getProductType()) {
                    LogUtil.a("WearDeviceUtils", "deleteStorageDevice deviceName:", CommonUtil.l(deviceInfo.getDeviceName()));
                    it.remove();
                    break;
                }
            }
            d(j);
        }
    }

    public static CloudDeviceInfo e(int i, String str) {
        CloudDeviceInfo cloudDeviceInfo;
        synchronized (d) {
            Iterator<CloudDeviceInfo> it = j().iterator();
            while (true) {
                if (!it.hasNext()) {
                    cloudDeviceInfo = null;
                    break;
                }
                cloudDeviceInfo = it.next();
                if (str.equals(cloudDeviceInfo.getDeviceName()) && i == cloudDeviceInfo.getProductType()) {
                    break;
                }
            }
        }
        return cloudDeviceInfo;
    }

    private static String d() {
        return LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011) + "_cloudDevice";
    }

    public static void b(int i, String str, boolean z) {
        synchronized (d) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            LogUtil.a("WearDeviceUtils", "Enter updateDeviceConnectingState isConnect", Boolean.valueOf(z), "deviceName:", CommonUtil.l(str));
            List<CloudDeviceInfo> j = j();
            for (CloudDeviceInfo cloudDeviceInfo : j) {
                if (z && cloudDeviceInfo.getDeviceName().equals(str) && cloudDeviceInfo.getProductType() == i) {
                    cloudDeviceInfo.setDeviceActiveState(1);
                    cloudDeviceInfo.setDeviceConnectState(1);
                } else {
                    cloudDeviceInfo.setDeviceActiveState(0);
                    cloudDeviceInfo.setDeviceConnectState(3);
                }
            }
            d(j);
        }
    }

    private static int d(List<CloudDeviceInfo> list, CloudDeviceInfo cloudDeviceInfo) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDeviceName().equals(cloudDeviceInfo.getDeviceName())) {
                return i;
            }
        }
        return -1;
    }

    public static void a(List<CloudDeviceInfo> list) {
        synchronized (d) {
            LogUtil.a("WearDeviceUtils", "Enter updateStorageDevices.");
            if (list == null) {
                return;
            }
            List<CloudDeviceInfo> j = j();
            HashMap hashMap = new HashMap(0);
            for (CloudDeviceInfo cloudDeviceInfo : j) {
                hashMap.put(cloudDeviceInfo.getDeviceName(), cloudDeviceInfo);
                LogUtil.a("WearDeviceUtils", "updateStorageDevices mapValue:", CommonUtil.l(cloudDeviceInfo.getDeviceName()));
            }
            for (CloudDeviceInfo cloudDeviceInfo2 : list) {
                String deviceName = cloudDeviceInfo2.getDeviceName();
                if (!hashMap.containsKey(deviceName)) {
                    LogUtil.a("WearDeviceUtils", "updateStorageDevices mapValue.containsKey(deviceName):", CommonUtil.l(cloudDeviceInfo2.getDeviceName()));
                    j.add(cloudDeviceInfo2);
                    hashMap.put(deviceName, cloudDeviceInfo2);
                } else {
                    int d2 = d(j, cloudDeviceInfo2);
                    LogUtil.a("WearDeviceUtils", "updateStorageDevices replace:", CommonUtil.l(cloudDeviceInfo2.getDeviceName()), "indexValue:", Integer.valueOf(d2));
                    if (d2 != -1) {
                        j.set(d2, cloudDeviceInfo2);
                    }
                }
            }
            LogUtil.a("WearDeviceUtils", "Enter mapValue.", Integer.valueOf(hashMap.keySet().size()));
            List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "WearDeviceUtils");
            if (deviceList != null && !deviceList.isEmpty()) {
                for (DeviceInfo deviceInfo : deviceList) {
                    String deviceName2 = deviceInfo.getDeviceName();
                    LogUtil.a("WearDeviceUtils", "updateStorageDevices deviceInfo.getDeviceName():", CommonUtil.l(deviceInfo.getDeviceName()));
                    if (hashMap.containsKey(deviceName2)) {
                        j.remove(hashMap.get(deviceName2));
                        c(deviceInfo);
                    }
                }
            }
            LogUtil.a("WearDeviceUtils", "deviceInfoList size:", Integer.valueOf(j.size()));
            d(j);
        }
    }

    private static StorageParams i() {
        StorageParams storageParams = new StorageParams();
        storageParams.d(1);
        return storageParams;
    }

    public static List<DeviceInfo> b() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "WearDeviceUtils");
        List<CloudDeviceInfo> j = j();
        if (j.size() == 0) {
            return deviceList;
        }
        if (deviceList == null) {
            LogUtil.h("WearDeviceUtils", "deviceList is null");
            deviceList = new ArrayList(0);
        } else {
            LogUtil.h("WearDeviceUtils", "deviceInfoList size:", Integer.valueOf(deviceList.size()));
        }
        HashMap hashMap = new HashMap(0);
        for (DeviceInfo deviceInfo : deviceList) {
            hashMap.put(deviceInfo.getDeviceName(), deviceInfo);
            LogUtil.a("WearDeviceUtils", "deviceInfoList mapValue:", deviceInfo.getDeviceName());
        }
        for (CloudDeviceInfo cloudDeviceInfo : j) {
            if (!hashMap.containsKey(cloudDeviceInfo.getDeviceName())) {
                CloudDeviceInfo cloudDeviceInfo2 = new CloudDeviceInfo();
                cloudDeviceInfo2.setProductType(cloudDeviceInfo.getProductType());
                cloudDeviceInfo2.setDeviceConnectState(cloudDeviceInfo.getDeviceConnectState());
                cloudDeviceInfo2.setDeviceActiveState(cloudDeviceInfo.getDeviceActiveState());
                cloudDeviceInfo2.setDeviceIdentify(cloudDeviceInfo.getDeviceIdentify());
                cloudDeviceInfo2.setDeviceName(cloudDeviceInfo.getDeviceName());
                cloudDeviceInfo2.setIsCloudDevice(cloudDeviceInfo.getIsCloudDevice());
                cloudDeviceInfo2.setSmartDeviceUdid(cloudDeviceInfo.getSmartDeviceUdid());
                cloudDeviceInfo2.setDeviceProfileId(cloudDeviceInfo.getDeviceProfileId());
                deviceList.add(cloudDeviceInfo2);
            } else {
                LogUtil.a("WearDeviceUtils", "getCloudDeviceInfoList delete device: ", cloudDeviceInfo.toString());
                c(cloudDeviceInfo);
            }
        }
        return deviceList;
    }

    private static ArrayList<cpm> g() {
        ArrayList<cpm> arrayList = new ArrayList<>(16);
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "WearDeviceUtils");
        if (bkz.e(deviceList)) {
            ReleaseLogUtil.e("R_WearDeviceUtils", "getWearInfo wearDeviceList is null");
            return arrayList;
        }
        for (DeviceInfo deviceInfo : deviceList) {
            if (deviceInfo != null) {
                int w = jfu.c(deviceInfo.getProductType()).w();
                int v = jfu.c(deviceInfo.getProductType()).v();
                cpm cpmVar = new cpm();
                cpmVar.a(deviceInfo, w, v);
                cpmVar.d(deviceInfo.getProductType());
                cpmVar.c(deviceInfo.getSecurityUuid() + "#ANDROID21");
                cpmVar.d(deviceInfo.getLastConnectedTime());
                e(deviceInfo, cpmVar);
                arrayList.add(cpmVar);
            }
        }
        return arrayList;
    }

    private static void e(DeviceInfo deviceInfo, cpm cpmVar) {
        if (TextUtils.isEmpty(deviceInfo.getDeviceName())) {
            if (!TextUtils.isEmpty(deviceInfo.getDeviceModel()) && TextUtils.equals(deviceInfo.getDeviceModel(), "PORSCHE DESIGN")) {
                cpmVar.e("PORSCHE DESIGN");
                return;
            } else {
                cpmVar.e(deviceInfo.getDeviceName());
                LogUtil.a("WearDeviceUtils", "device name is null ,device name :", deviceInfo.getDeviceName());
                return;
            }
        }
        if (TextUtils.equals(deviceInfo.getDeviceName(), "PORSCHE DESIGN") || (!TextUtils.isEmpty(deviceInfo.getDeviceModel()) && TextUtils.equals(deviceInfo.getDeviceModel(), "PORSCHE DESIGN"))) {
            cpmVar.e("PORSCHE DESIGN");
        } else {
            cpmVar.e(deviceInfo.getDeviceName());
        }
    }

    public static ArrayList<cpm> a() {
        ArrayList<cpm> g = g();
        List<CloudDeviceInfo> j = j();
        if (j.size() == 0) {
            ReleaseLogUtil.e("R_WearDeviceUtils", "getWearInfoList cloudDevices.size = 0");
            b(g);
            return g;
        }
        HashMap hashMap = new HashMap(0);
        Iterator<cpm> it = g.iterator();
        while (it.hasNext()) {
            cpm next = it.next();
            hashMap.put(next.d(), next);
            LogUtil.a("WearDeviceUtils", "getCloudDeviceInfoList mapValue:", next.d());
        }
        for (CloudDeviceInfo cloudDeviceInfo : j) {
            int w = jfu.c(cloudDeviceInfo.getProductType()).w();
            int v = jfu.c(cloudDeviceInfo.getProductType()).v();
            if (!hashMap.containsKey(cloudDeviceInfo.getDeviceName())) {
                cpm cpmVar = new cpm();
                cpmVar.e(w);
                cpmVar.d(String.valueOf(v));
                cpmVar.d(cloudDeviceInfo.getProductType());
                cpmVar.a(cloudDeviceInfo.getDeviceConnectState());
                cpmVar.c(cloudDeviceInfo.getDeviceActiveState());
                cpmVar.b(cloudDeviceInfo.getDeviceIdentify());
                cpmVar.f(cloudDeviceInfo.getAutoDetectSwitchStatus());
                cpmVar.e(cloudDeviceInfo.getDeviceName());
                cpmVar.e(cloudDeviceInfo.getIsCloudDevice());
                cpmVar.c(cloudDeviceInfo.getDeviceProfileId());
                cpmVar.d(cloudDeviceInfo.getLastConnectedTime());
                g.add(cpmVar);
            } else {
                LogUtil.a("WearDeviceUtils", "getCloudDeviceInfoList delete device: ", cloudDeviceInfo.toString());
                c(cloudDeviceInfo);
            }
        }
        b(g);
        return g;
    }

    private static void b(ArrayList<cpm> arrayList) {
        LogUtil.a("WearDeviceUtils", "checkSameTypeDevice deviceList.size(): ", Integer.valueOf(arrayList.size()));
        HashMap hashMap = new HashMap();
        Iterator<cpm> it = arrayList.iterator();
        while (it.hasNext()) {
            String d2 = d(it.next().d());
            if (hashMap.containsKey(d2)) {
                hashMap.put(d2, Integer.valueOf(((Integer) hashMap.get(d2)).intValue() + 1));
            } else {
                hashMap.put(d2, 1);
            }
            LogUtil.a("WearDeviceUtils", "checkSameTypeDevice DeviceInfoForWear device name: ", d2, " count: ", hashMap.get(d2));
        }
        Iterator<cpm> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            cpm next = it2.next();
            if (((Integer) hashMap.get(d(next.d()))).intValue() > 1) {
                next.b(false);
            }
        }
    }

    private static String d(String str) {
        return TextUtils.isEmpty(str) ? "" : (str.contains("HUAWEI CM-R1P") || str.contains("HUAWEI AM-R1") || str.length() <= 4) ? str : str.substring(0, str.length() - 4);
    }

    public static void a(List<DeviceInfo> list, String str) {
        if (list == null || TextUtils.isEmpty(str)) {
            LogUtil.h("WearDeviceUtils", "updateConnectDevice deviceInfoList size is null or mac is null");
            return;
        }
        ArrayList arrayList = new ArrayList(0);
        DeviceInfo deviceInfo = null;
        DeviceInfo deviceInfo2 = null;
        for (DeviceInfo deviceInfo3 : list) {
            if ("main_relationship".equals(deviceInfo3.getRelationship())) {
                deviceInfo = deviceInfo3;
            }
            if (str.equalsIgnoreCase(deviceInfo3.getDeviceIdentify())) {
                deviceInfo2 = deviceInfo3;
            }
            if (!(deviceInfo3 instanceof CloudDeviceInfo)) {
                arrayList.add(deviceInfo3);
            }
        }
        boolean a2 = a(str, deviceInfo, deviceInfo2);
        LogUtil.a("WearDeviceUtils", "updateConnectDevice localList:", Integer.valueOf(arrayList.size()), ",isSizeToDevice: ", Boolean.valueOf(a2), ",mainDeviceInfo: ", deviceInfo, ",sizeDevice: ", deviceInfo2);
        if (a2) {
            jfq.c().d("seizeDevice", deviceInfo, 0, str);
        }
        if (arrayList.size() <= 0 || str.contains(Constants.LINK)) {
            return;
        }
        try {
            KeyValDbManager.b(BaseApplication.getContext()).e("isUserConnect", Boolean.toString(true));
            jfq.c().e(arrayList, str);
        } catch (RemoteException e) {
            LogUtil.b("WearDeviceUtils", "RemoteException switchDevice:", e.getMessage());
        }
    }

    private static boolean a(String str, DeviceInfo deviceInfo, DeviceInfo deviceInfo2) {
        if (deviceInfo == null || str.equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
            LogUtil.a("WearDeviceUtils", "device is not fit.");
            return false;
        }
        if (str.contains(Constants.LINK) || deviceInfo2 == null || (deviceInfo2 instanceof CloudDeviceInfo)) {
            LogUtil.a("WearDeviceUtils", "sizeDevice is invalid.");
            return false;
        }
        if (cwi.c(deviceInfo, 93) && cwi.c(deviceInfo2, 26)) {
            return true;
        }
        LogUtil.a("WearDeviceUtils", "checkSupportCapability is false.");
        return false;
    }

    public static void e() {
        KeyValDbManager.b(BaseApplication.getContext()).c("isUserConnect");
    }

    private static List<CloudDeviceInfo> h() {
        ArrayList arrayList = new ArrayList();
        String d2 = KeyValDbManager.b(BaseApplication.getContext()).d(d(), i());
        if (d2 == null) {
            f13799a = 0;
            return arrayList;
        }
        try {
            ArrayList arrayList2 = (ArrayList) new Gson().fromJson(d2, new TypeToken<ArrayList<CloudDeviceInfo>>() { // from class: jfv.3
            }.getType());
            if (arrayList2 == null) {
                return arrayList;
            }
            LogUtil.a("WearDeviceUtils", "getDeviceListFromDB deviceList size:", Integer.valueOf(arrayList2.size()));
            return arrayList2;
        } catch (JsonSyntaxException unused) {
            LogUtil.b("WearDeviceUtils", "getStorageCloudDevices JsonSyntaxException");
            return arrayList;
        }
    }

    private static void d(List<CloudDeviceInfo> list) {
        LogUtil.a("WearDeviceUtils", "setDeviceListToDb deviceList size:", Integer.valueOf(list.size()));
        try {
            KeyValDbManager.b(BaseApplication.getContext()).a(d(), new Gson().toJson(list), i());
        } catch (JsonSyntaxException unused) {
            LogUtil.b("WearDeviceUtils", "JsonSyntaxException");
        }
    }
}
