package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class cvs {
    private static DeviceInfo b;
    private static DeviceCapability c;
    private static Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static Object f11506a = new Object();
    private static Map<String, DeviceCapability> e = new HashMap(16);

    private cvs() {
    }

    public static DeviceCapability e(String str) {
        DeviceCapability deviceCapability;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        synchronized (c()) {
            deviceCapability = e.get(str);
        }
        return deviceCapability;
    }

    public static DeviceCapability d() {
        DeviceCapability deviceCapability;
        synchronized (c()) {
            deviceCapability = c;
        }
        return deviceCapability;
    }

    public static void a(DeviceCapability deviceCapability) {
        synchronized (c()) {
            if (deviceCapability != null) {
                c = deviceCapability;
            }
        }
    }

    public static void d(Map<String, DeviceCapability> map) {
        if (map == null || map.size() == 0) {
            return;
        }
        synchronized (c()) {
            e = map;
        }
    }

    public static void a(String str, DeviceCapability deviceCapability) {
        if (deviceCapability == null) {
            return;
        }
        synchronized (c()) {
            e.put(str, deviceCapability);
        }
    }

    public static void b(String str) {
        synchronized (c()) {
            e.remove(str);
        }
    }

    public static DeviceInfo a() {
        DeviceInfo deviceInfo;
        synchronized (e()) {
            deviceInfo = b;
        }
        return deviceInfo;
    }

    public static void b(DeviceInfo deviceInfo) {
        synchronized (e()) {
            if (deviceInfo != null) {
                b = deviceInfo;
            }
        }
    }

    private static Object c() {
        Object obj;
        synchronized (cvs.class) {
            obj = d;
        }
        return obj;
    }

    private static Object e() {
        Object obj;
        synchronized (cvs.class) {
            obj = f11506a;
        }
        return obj;
    }
}
