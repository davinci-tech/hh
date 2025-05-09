package defpackage;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.outofprocess.mgr.device.CloudDeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.KeyValDbManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes5.dex */
public class jyr {
    private static volatile jyr b;
    private static final Object c = new Object();
    private static final Object d = new Object();

    private jyr() {
    }

    private static String e() {
        return LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011) + "_cloudDevice";
    }

    private static StorageParams d() {
        StorageParams storageParams = new StorageParams();
        storageParams.d(1);
        return storageParams;
    }

    private static List<CloudDeviceInfo> b() {
        ArrayList arrayList = new ArrayList();
        String d2 = KeyValDbManager.b(BaseApplication.getContext()).d(e(), d());
        if (d2 == null) {
            return arrayList;
        }
        try {
            ArrayList arrayList2 = (ArrayList) new Gson().fromJson(d2, new TypeToken<ArrayList<CloudDeviceInfo>>() { // from class: jyr.2
            }.getType());
            if (arrayList2 == null) {
                return arrayList;
            }
            LogUtil.a("HwCloudDeviceManager", "getDeviceListFromDB deviceList size:", Integer.valueOf(arrayList2.size()));
            return arrayList2;
        } catch (JsonSyntaxException unused) {
            LogUtil.b("HwCloudDeviceManager", "getStorageCloudDevices JsonSyntaxException");
            return arrayList;
        }
    }

    private static void d(List<CloudDeviceInfo> list) {
        LogUtil.a("HwCloudDeviceManager", "setDeviceListToDB deviceList size:", Integer.valueOf(list.size()));
        try {
            KeyValDbManager.b(BaseApplication.getContext()).a(e(), new Gson().toJson(list), d());
        } catch (JsonSyntaxException unused) {
            LogUtil.b("HwCloudDeviceManager", "JsonSyntaxException");
        }
    }

    private static List<CloudDeviceInfo> c(List<CloudDeviceInfo> list, CloudDeviceInfo cloudDeviceInfo) {
        ArrayList arrayList = new ArrayList();
        for (CloudDeviceInfo cloudDeviceInfo2 : list) {
            if (!cloudDeviceInfo2.getDeviceName().equalsIgnoreCase(cloudDeviceInfo.getDeviceName())) {
                arrayList.add(cloudDeviceInfo2);
            }
        }
        return arrayList;
    }

    public static jyr a() {
        if (b == null) {
            synchronized (d) {
                if (b == null) {
                    b = new jyr();
                }
            }
        }
        return b;
    }

    public void e(CloudDeviceInfo cloudDeviceInfo) {
        LogUtil.a("HwCloudDeviceManager", "addStorageDevices device: ", cloudDeviceInfo.toString());
        List<CloudDeviceInfo> c2 = c(c(), cloudDeviceInfo);
        c2.add(cloudDeviceInfo);
        d(c2);
    }

    public void c(CloudDeviceInfo cloudDeviceInfo) {
        synchronized (c) {
            if (cloudDeviceInfo == null) {
                return;
            }
            LogUtil.a("HwCloudDeviceManager", "deleteStorageDevice device: ", cloudDeviceInfo.toString());
            d(c(c(), cloudDeviceInfo));
        }
    }

    public List<CloudDeviceInfo> c() {
        List<CloudDeviceInfo> b2;
        LogUtil.a("HwCloudDeviceManager", "getCloudDeviceInfoList. ");
        synchronized (c) {
            b2 = b();
            Collections.sort(b2, new Comparator<DeviceInfo>() { // from class: jyr.5
                @Override // java.util.Comparator
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
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
            LogUtil.a("HwCloudDeviceManager", "getCloudDeviceInfoList size:", Integer.valueOf(b2.size()));
        }
        return b2;
    }
}
