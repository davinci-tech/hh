package defpackage;

import com.huawei.datatype.GpsStruct;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class kcc {

    /* renamed from: a, reason: collision with root package name */
    private static List<IBaseResponseCallback> f14268a = new ArrayList(16);
    private static List<IBaseResponseCallback> c = new ArrayList(16);
    private static List<IBaseResponseCallback> e = new ArrayList(16);
    private static List<IBaseResponseCallback> d = new ArrayList(16);

    public static List<IBaseResponseCallback> e() {
        return (List) jdy.d(f14268a);
    }

    public static List<IBaseResponseCallback> b() {
        return (List) jdy.d(c);
    }

    public static List<IBaseResponseCallback> d() {
        return (List) jdy.d(e);
    }

    public static List<IBaseResponseCallback> a() {
        return (List) jdy.d(d);
    }

    public static boolean a(DeviceInfo deviceInfo) {
        DeviceCapability deviceCapability;
        boolean z = false;
        if (deviceInfo == null) {
            LogUtil.h("GpsManager", "getDeviceSupportCapacity, deviceInfo is empty");
            return false;
        }
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(1, deviceInfo.getDeviceIdentify(), "GpsManager");
        if (queryDeviceCapability != null && !queryDeviceCapability.isEmpty() && (deviceCapability = queryDeviceCapability.get(deviceInfo.getDeviceIdentify())) != null) {
            z = deviceCapability.isSupportGpsLocation();
        }
        LogUtil.a("GpsManager", "get Device Support runplan Capacity, capacity:", Boolean.valueOf(z));
        return z;
    }

    private static ByteBuffer c(int i, int i2) {
        byte[] g = cvx.g(i2);
        byte[] c2 = cvx.c(g.length);
        byte[] f = cvx.f(i);
        ByteBuffer allocate = ByteBuffer.allocate(f.length + c2.length + g.length);
        allocate.put(f).put(c2).put(g);
        return allocate;
    }

    private static ByteBuffer d(int i, int i2) {
        byte[] b = cvx.b(i2);
        byte[] c2 = cvx.c(b.length);
        byte[] f = cvx.f(i);
        ByteBuffer allocate = ByteBuffer.allocate(f.length + c2.length + b.length);
        allocate.put(f).put(c2).put(b);
        return allocate;
    }

    private static ByteBuffer a(int i, double d2) {
        byte[] d3 = cvx.d(d2);
        byte[] c2 = cvx.c(d3.length);
        byte[] f = cvx.f(i);
        ByteBuffer allocate = ByteBuffer.allocate(f.length + c2.length + d3.length);
        allocate.put(f).put(c2).put(d3);
        return allocate;
    }

    public static byte[] b(List<GpsStruct> list, boolean z) {
        ArrayList arrayList = new ArrayList(list.size());
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            ArrayList arrayList2 = new ArrayList(16);
            GpsStruct gpsStruct = list.get(i2);
            d(gpsStruct, z, arrayList2);
            if (gpsStruct.getGpsLatitude() != -1.0d) {
                arrayList2.add(a(9, gpsStruct.getGpsLatitude()));
            }
            if (gpsStruct.getGpsLongitude() != -1.0d) {
                arrayList2.add(a(10, gpsStruct.getGpsLongitude()));
            }
            if (gpsStruct.getGpsMarsLatitude() != -1.0d) {
                arrayList2.add(a(11, gpsStruct.getGpsMarsLatitude()));
            }
            if (gpsStruct.getGpsMarsLongitude() != -1.0d) {
                arrayList2.add(a(12, gpsStruct.getGpsMarsLongitude()));
            }
            if (gpsStruct.getGpsDirection() != -1.0d) {
                arrayList2.add(a(13, gpsStruct.getGpsDirection()));
            }
            if (gpsStruct.getGpsPrecision() != -1.0d) {
                arrayList2.add(a(14, gpsStruct.getGpsPrecision()));
            }
            if (gpsStruct.getGpsQuality() != -1) {
                arrayList2.add(d(15, gpsStruct.getGpsQuality()));
            }
            Iterator it = arrayList2.iterator();
            int i3 = 0;
            while (it.hasNext()) {
                i3 += ((ByteBuffer) it.next()).capacity();
            }
            byte[] c2 = cvx.c(i3);
            byte[] f = cvx.f(OldToNewMotionPath.SPORT_TYPE_TENNIS);
            ByteBuffer allocate = ByteBuffer.allocate(f.length + c2.length + i3);
            allocate.put(f).put(c2);
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                allocate.put(((ByteBuffer) it2.next()).array());
            }
            arrayList.add(allocate);
            i += allocate.capacity();
        }
        ByteBuffer allocate2 = ByteBuffer.allocate(i);
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext()) {
            allocate2.put(((ByteBuffer) it3.next()).array());
        }
        return allocate2.array();
    }

    private static void d(GpsStruct gpsStruct, boolean z, List<ByteBuffer> list) {
        if (z && gpsStruct.getGpsSpeed() != -1) {
            list.add(d(3, gpsStruct.getGpsSpeed()));
        }
        if (gpsStruct.getGpsDistance() != -1) {
            list.add(c(4, (int) gpsStruct.getGpsDistance()));
        }
        if (gpsStruct.getGpsAltitude() != -1) {
            list.add(d(5, gpsStruct.getGpsAltitude()));
        }
        if (gpsStruct.getGpsTotalDistance() != -1) {
            list.add(c(6, (int) gpsStruct.getGpsTotalDistance()));
        }
        if (gpsStruct.getGpsStartTime() != -1) {
            list.add(c(7, (int) gpsStruct.getGpsStartTime()));
        }
        if (gpsStruct.getGpsEndTime() != -1) {
            list.add(c(8, (int) gpsStruct.getGpsEndTime()));
        }
    }
}
