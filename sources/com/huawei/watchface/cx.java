package com.huawei.watchface;

import com.huawei.watchface.flavor.FlavorConfig;
import com.huawei.watchface.mvp.model.datatype.WatchResourcesInfo;
import com.huawei.watchface.utils.HwLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes7.dex */
public class cx {
    private static volatile cx c;

    /* renamed from: a, reason: collision with root package name */
    private ConcurrentHashMap<String, Map<String, byte[]>> f10979a = new ConcurrentHashMap<>(32);
    private final ReentrantLock b = new ReentrantLock();

    private cx() {
    }

    public static cx a() {
        if (c == null) {
            synchronized (cx.class) {
                if (c == null) {
                    c = new cx();
                }
            }
        }
        return c;
    }

    public void a(String str, Map<String, byte[]> map) {
        HwLog.i("FileContentHolder", "put uuid=" + str);
        HwLog.i("FileContentHolder", "put files=" + map);
        c(str).putAll(map);
    }

    private Map<String, byte[]> c(String str) {
        Map<String, byte[]> map = this.f10979a.get(str);
        if (map == null) {
            this.b.lock();
            map = this.f10979a.get(str);
            if (map == null) {
                map = new HashMap<>();
                this.f10979a.put(str, map);
            }
            this.b.unlock();
        }
        return map;
    }

    public cw a(String str, String str2) {
        HwLog.i("FileContentHolder", "get uuid=" + str);
        HwLog.i("FileContentHolder", "get fileName=" + str2);
        a a2 = a.a(str);
        List<String> d = d(a2.f10980a);
        for (int i = 0; i < d.size(); i++) {
            String str3 = d.get(i) + "_" + a2.b;
            HwLog.i("FileContentHolder", "get tmpUUID=" + str3);
            Map<String, byte[]> map = this.f10979a.get(str3);
            if (map != null) {
                return a(map, str2);
            }
        }
        return null;
    }

    public boolean a(String str) {
        FlavorConfig.safeHwLog("FileContentHolder", "exists name: " + str);
        a a2 = a.a(str);
        List<String> d = d(a2.a());
        for (int i = 0; i < d.size(); i++) {
            String str2 = d.get(i) + "_" + a2.b();
            HwLog.i("FileContentHolder", "exists tmpUUID: " + str2);
            if (this.f10979a.containsKey(str2)) {
                HwLog.i("FileContentHolder", "exists matched id: " + str2);
                return true;
            }
        }
        return false;
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private final String f10980a;
        private final String b;

        public a(String str, String str2) {
            this.f10980a = str;
            this.b = str2;
        }

        public String a() {
            return this.f10980a;
        }

        public String b() {
            return this.b;
        }

        public static a a(String str) {
            if (str == null || !str.contains("_")) {
                return new a(str, null);
            }
            String[] split = str.split("_");
            return new a(split[0], split[1]);
        }
    }

    private static List<String> d(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        arrayList.add(WatchResourcesInfo.markDownHiTopId(str));
        arrayList.add(d.a().c(str).trim());
        return arrayList;
    }

    private static cw a(Map<String, byte[]> map, String str) {
        byte[] bArr = map.get(str);
        if (bArr == null) {
            return null;
        }
        return new cw(str, bArr);
    }

    public Map<String, byte[]> b(String str) {
        HwLog.i("FileContentHolder", "remove uuid=" + str);
        a a2 = a.a(str);
        List<String> d = d(a2.f10980a);
        for (int i = 0; i < d.size(); i++) {
            String str2 = d.get(i) + "_" + a2.b;
            HwLog.i("FileContentHolder", "get tmpUUID=" + str2);
            Map<String, byte[]> remove = this.f10979a.remove(str2);
            if (remove != null) {
                return remove;
            }
        }
        return null;
    }
}
