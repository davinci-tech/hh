package defpackage;

import com.huawei.devicesdk.entity.ConnectMode;
import com.huawei.devicesdk.entity.ScanMode;
import com.huawei.devicesdk.entity.SendMode;
import com.huawei.devicesdk.strategy.ScanStrategyBle;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class bgo {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<ConnectMode, String> f362a;
    private static final Map<SendMode, String> b;
    private static final Map<ScanMode, String> e;

    static {
        HashMap hashMap = new HashMap(5);
        e = hashMap;
        hashMap.put(ScanMode.BR, bkj.class.getName());
        hashMap.put(ScanMode.BLE, ScanStrategyBle.class.getName());
        HashMap hashMap2 = new HashMap(5);
        f362a = hashMap2;
        hashMap2.put(ConnectMode.GENERAL, bkd.class.getName());
        hashMap2.put(ConnectMode.SIMPLE, bkg.class.getName());
        hashMap2.put(ConnectMode.TRANSPARENT, bki.class.getName());
        HashMap hashMap3 = new HashMap(5);
        b = hashMap3;
        hashMap3.put(SendMode.PROTOCOL_TYPE_5A, bkn.class.getName());
        hashMap3.put(SendMode.PROTOCOL_TYPE_D, bkq.class.getName());
        hashMap3.put(SendMode.PROTOCOL_TYPE_DIRECT, bkr.class.getName());
        hashMap3.put(SendMode.PROTOCOL_TYPE_FRAGMENT, bko.class.getName());
    }

    public static Map<ScanMode, String> a() {
        return Collections.unmodifiableMap(e);
    }

    public static Map<ConnectMode, String> e() {
        return Collections.unmodifiableMap(f362a);
    }

    public static Map<SendMode, String> b() {
        return Collections.unmodifiableMap(b);
    }
}
