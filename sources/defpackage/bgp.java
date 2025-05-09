package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.ExternalDeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.unitedevice.entity.UniteDevice;
import health.compact.a.LogUtil;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class bgp {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f363a = new Object();
    private ConcurrentHashMap<String, DeviceInfo> b;
    private ConcurrentHashMap<String, ExternalDeviceCapability> c;
    private ConcurrentHashMap<String, DeviceInfo> d;
    private ConcurrentHashMap<String, biw> e;
    private ConcurrentHashMap<String, bix> f;
    private ConcurrentHashMap<String, biz> h;
    private ConcurrentHashMap<String, Boolean> i;

    private bgp() {
        this.d = new ConcurrentHashMap<>(10);
        this.b = new ConcurrentHashMap<>(10);
        this.f = new ConcurrentHashMap<>(10);
        this.c = new ConcurrentHashMap<>(10);
        this.e = new ConcurrentHashMap<>(10);
        this.h = new ConcurrentHashMap<>(10);
        this.i = new ConcurrentHashMap<>(10);
    }

    public static bgp a() {
        return a.e;
    }

    public void b(String str, DeviceInfo deviceInfo) {
        if (TextUtils.isEmpty(str) || deviceInfo == null) {
            LogUtil.a("DeviceConnectData", "deviceUuid or deviceInfo is incorrect, addDeviceInfo fail");
        } else {
            this.d.put(str, deviceInfo);
        }
    }

    public void d(DeviceInfo deviceInfo) {
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceMac())) {
            LogUtil.a("DeviceConnectData", "params is invalid, updateUsedDeviceInfo fail.");
            return;
        }
        DeviceInfo deviceInfo2 = this.d.get(deviceInfo.getDeviceMac());
        if (deviceInfo2 == null) {
            LogUtil.a("DeviceConnectData", "not used device.");
            return;
        }
        deviceInfo2.setDeviceConnectState(deviceInfo.getDeviceConnectState());
        deviceInfo2.setUsing(deviceInfo.isUsing());
        LogUtil.c("DeviceConnectData", "updateUsedDeviceInfo success.");
    }

    public DeviceInfo j(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("DeviceConnectData", "deviceUuid is empty, getDeviceInfo fail.");
            return null;
        }
        return this.d.get(str);
    }

    public Map<String, DeviceInfo> d() {
        return this.d;
    }

    public Map<String, DeviceInfo> c() {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(16);
        concurrentHashMap.putAll(this.d);
        concurrentHashMap.putAll(this.b);
        return concurrentHashMap;
    }

    public void i(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("DeviceConnectData", "deviceUuid is empty, removeDeviceAllData fail.");
            return;
        }
        this.d.remove(str);
        this.c.remove(str);
        synchronized (f363a) {
            this.f.remove(str);
        }
    }

    public void a(DeviceInfo deviceInfo) {
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceMac())) {
            LogUtil.a("DeviceConnectData", "device info is invalid, addCacheDeviceInfo fail.");
        } else {
            this.b.put(deviceInfo.getDeviceMac(), deviceInfo);
        }
    }

    public DeviceInfo a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("DeviceConnectData", "deviceUuid is empty, getCacheDeviceInfo fail");
            return null;
        }
        return this.b.get(str);
    }

    public void a(String str, bix bixVar) {
        if (TextUtils.isEmpty(str) || bixVar == null) {
            LogUtil.a("DeviceConnectData", "deviceUuid or deviceStatus is incorrect, addDeviceStatus fail.");
            return;
        }
        synchronized (f363a) {
            this.f.put(str, bixVar);
        }
    }

    public bix g(String str) {
        bix bixVar;
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("DeviceConnectData", "deviceUuid is empty, getDeviceStatus fail.");
            return null;
        }
        synchronized (f363a) {
            bixVar = this.f.get(str);
        }
        return bixVar;
    }

    public void d(String str, ExternalDeviceCapability externalDeviceCapability) {
        if (TextUtils.isEmpty(str) || externalDeviceCapability == null) {
            LogUtil.a("DeviceConnectData", "device id or capability is invalid, addDeviceCapability fail.");
        } else {
            this.c.put(str, externalDeviceCapability);
        }
    }

    public Map<String, ExternalDeviceCapability> b() {
        return this.c;
    }

    public biw e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("DeviceConnectData", "deviceUuid is empty, getDeviceExInfo fail.");
            return null;
        }
        return this.e.get(str);
    }

    public void a(String str, biw biwVar) {
        if (TextUtils.isEmpty(str) || biwVar == null) {
            LogUtil.a("DeviceConnectData", "deviceUuid or parameter is empty, setDeviceExInfo fail.");
        } else {
            this.e.put(str, biwVar);
        }
    }

    public void l(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("DeviceConnectData", "device is invalid");
        } else {
            this.e.remove(str);
        }
    }

    public biz f(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("DeviceConnectData", "deviceUuid is empty, getDevicePreConnectInfo fail.");
            return null;
        }
        return this.h.get(str);
    }

    public void c(String str, biz bizVar) {
        if (TextUtils.isEmpty(str) || bizVar == null) {
            LogUtil.a("DeviceConnectData", "deviceUuid or parameter is empty, setDevicePreConnectInfo fail.");
        } else {
            this.h.put(str, bizVar);
        }
    }

    public void a(String str, DeviceCapability deviceCapability) {
        if (TextUtils.isEmpty(str) || deviceCapability == null) {
            LogUtil.a("DeviceConnectData", "deviceUuid or capability is invalid, addCompatibleDeviceCapability error.");
            return;
        }
        this.c.putIfAbsent(str, new ExternalDeviceCapability());
        ExternalDeviceCapability externalDeviceCapability = this.c.get(str);
        if (externalDeviceCapability != null) {
            externalDeviceCapability.setCompatibleCapacity(deviceCapability);
        }
    }

    public DeviceCapability b(String str) {
        DeviceCapability deviceCapability = new DeviceCapability();
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("DeviceConnectData", "deviceUuid or capability is invalid, getCompatibleDeviceCapability error.");
            return deviceCapability;
        }
        ExternalDeviceCapability externalDeviceCapability = this.c.get(str);
        return externalDeviceCapability == null ? deviceCapability : externalDeviceCapability.getCompatibleCapacity();
    }

    public void b(String str, String str2) {
        if (TextUtils.isEmpty(str) || str2 == null) {
            LogUtil.a("DeviceConnectData", "device id or capability is invalid, addExtendDeviceCapability error.");
            return;
        }
        this.c.putIfAbsent(str, new ExternalDeviceCapability());
        ExternalDeviceCapability externalDeviceCapability = this.c.get(str);
        LogUtil.c("DeviceConnectData", "addExtendDeviceCapability: capability = ", new Gson().toJson(externalDeviceCapability));
        if (externalDeviceCapability != null) {
            externalDeviceCapability.setCapacity(str2);
        }
    }

    public ExternalDeviceCapability d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("DeviceConnectData", "deviceUuid id is empty, getDeviceCapability error.");
            return null;
        }
        return this.c.get(str);
    }

    public void k(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("DeviceConnectData", "deviceUuid is empty, resetDeviceCapability error.");
            return;
        }
        ExternalDeviceCapability externalDeviceCapability = this.c.get(str);
        if (externalDeviceCapability == null) {
            LogUtil.a("DeviceConnectData", "capability is null, resetDeviceCapability fail.");
            return;
        }
        externalDeviceCapability.setCapacity("");
        externalDeviceCapability.setCompatibleCapacity(new DeviceCapability());
        LogUtil.c("DeviceConnectData", "resetDeviceCapability success.");
    }

    public void c(UniteDevice uniteDevice) {
        if (uniteDevice == null || TextUtils.isEmpty(uniteDevice.getIdentify())) {
            LogUtil.a("DeviceConnectData", "updateDeviceCapability device is invalid.");
            return;
        }
        ExternalDeviceCapability capability = uniteDevice.getCapability();
        if (capability == null) {
            LogUtil.a("DeviceConnectData", "updateDeviceCapability device capability is invalid. capability is null");
            return;
        }
        String identify = uniteDevice.getIdentify();
        ExternalDeviceCapability externalDeviceCapability = new ExternalDeviceCapability();
        ExternalDeviceCapability putIfAbsent = this.c.putIfAbsent(identify, externalDeviceCapability);
        if (putIfAbsent == null) {
            putIfAbsent = this.c.putIfAbsent(identify, externalDeviceCapability);
        }
        if (putIfAbsent != null) {
            if (!TextUtils.isEmpty(capability.getCapacity())) {
                putIfAbsent.setCapacity(capability.getCapacity());
            }
            if (capability.getCompatibleCapacity() != null) {
                putIfAbsent.setCompatibleCapacity(capability.getCompatibleCapacity());
                return;
            }
            return;
        }
        LogUtil.a("DeviceConnectData", "updateDeviceCapability externalCapability is null.");
    }

    public void b(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("DeviceConnectData", "deviceUuid is empty, setDeviceSupportCapability fail.");
        } else {
            this.i.put(str, Boolean.valueOf(z));
        }
    }

    public boolean h(String str) {
        Boolean bool;
        if (!TextUtils.isEmpty(str)) {
            return this.i.containsKey(str) && (bool = this.i.get(str)) != null && bool.booleanValue();
        }
        LogUtil.a("DeviceConnectData", "deviceUuid is empty, get isDeviceSupportCapability fail.");
        return false;
    }

    public void c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("DeviceConnectData", "device is invalid");
        } else {
            this.b.remove(str);
            this.i.remove(str);
        }
    }

    static class a {
        private static bgp e = new bgp();
    }
}
