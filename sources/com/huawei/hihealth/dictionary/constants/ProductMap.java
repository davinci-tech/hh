package com.huawei.hihealth.dictionary.constants;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class ProductMap {
    private static final Map<String, Set<ProductMapInfo>> b;
    private static final Map<String, ProductMapInfo> j = new ConcurrentHashMap();
    private static final Map<String, Set<ProductMapInfo>> c = new ConcurrentHashMap();
    private static final Map<String, Set<ProductMapInfo>> g = new ConcurrentHashMap();
    private static final Map<String, Set<ProductMapInfo>> h = new ConcurrentHashMap();
    private static final Map<String, Set<ProductMapInfo>> e = new ConcurrentHashMap();

    /* renamed from: a, reason: collision with root package name */
    private static final Map<String, Set<ProductMapInfo>> f4122a = new ConcurrentHashMap();
    private static final Map<String, Set<ProductMapInfo>> d = new ConcurrentHashMap();

    static {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        b = concurrentHashMap;
        ProductMapInfo productMapInfo = new ProductMapInfo("001", null, null, null, null, null, 1);
        HashSet hashSet = new HashSet(1);
        hashSet.add(productMapInfo);
        concurrentHashMap.put(String.valueOf(1), hashSet);
        concurrentHashMap.put(String.valueOf(10001), hashSet);
    }

    private ProductMap() {
    }

    public static void c(ProductMapInfo productMapInfo) {
        synchronized (ProductMap.class) {
            if (productMapInfo == null) {
                return;
            }
            String f = productMapInfo.f();
            if (TextUtils.isEmpty(f)) {
                return;
            }
            j.put(f, productMapInfo);
            a(c, "deviceType", productMapInfo);
            a(g, "smartProductId", productMapInfo);
            a(h, "productId", productMapInfo);
            a(e, "modelName", productMapInfo);
            a(f4122a, "manufacturerId", productMapInfo);
            a(d, "marketingName", productMapInfo);
            a(b, "deviceId", productMapInfo);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static void a(Map<String, Set<ProductMapInfo>> map, String str, ProductMapInfo productMapInfo) {
        char c2;
        String a2;
        str.hashCode();
        switch (str.hashCode()) {
            case -2010829484:
                if (str.equals("modelName")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -1051830678:
                if (str.equals("productId")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -442550831:
                if (str.equals("marketingName")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 647647905:
                if (str.equals("smartProductId")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 781190832:
                if (str.equals("deviceType")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 1109191185:
                if (str.equals("deviceId")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case 1537506508:
                if (str.equals("manufacturerId")) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 0:
                a2 = productMapInfo.a();
                break;
            case 1:
                a2 = productMapInfo.h();
                break;
            case 2:
                a2 = productMapInfo.b();
                break;
            case 3:
                a2 = productMapInfo.f();
                break;
            case 4:
                a2 = productMapInfo.e();
                break;
            case 5:
                a2 = String.valueOf(productMapInfo.c());
                break;
            case 6:
                a2 = productMapInfo.d();
                break;
            default:
                return;
        }
        Set<ProductMapInfo> set = (TextUtils.isEmpty(a2) || !map.containsKey(a2)) ? null : map.get(a2);
        if (set == null) {
            set = new HashSet<>(10);
        }
        set.add(productMapInfo);
        if (TextUtils.isEmpty(a2)) {
            return;
        }
        map.put(a2, set);
    }

    public static ArrayList<ProductMapInfo> d() {
        ArrayList<ProductMapInfo> arrayList = new ArrayList<>();
        arrayList.addAll(j.values());
        return arrayList;
    }

    public static ProductMapInfo d(String str) {
        if (!TextUtils.isEmpty(str)) {
            Map<String, ProductMapInfo> map = j;
            if (map.containsKey(str)) {
                return map.get(str);
            }
        }
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static List<ProductMapInfo> b(String str, String str2) {
        char c2;
        str.hashCode();
        switch (str.hashCode()) {
            case -2010829484:
                if (str.equals("modelName")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -1051830678:
                if (str.equals("productId")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -442550831:
                if (str.equals("marketingName")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 647647905:
                if (str.equals("smartProductId")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 781190832:
                if (str.equals("deviceType")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 1109191185:
                if (str.equals("deviceId")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case 1537506508:
                if (str.equals("manufacturerId")) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 0:
                return c(e, str2);
            case 1:
                return c(h, str2);
            case 2:
                return c(d, str2);
            case 3:
                return c(g, str2);
            case 4:
                return c(c, str2);
            case 5:
                return c(b, str2);
            case 6:
                return c(f4122a, str2);
            default:
                return new ArrayList();
        }
    }

    private static List<ProductMapInfo> c(Map<String, Set<ProductMapInfo>> map, String str) {
        ArrayList arrayList;
        synchronized (ProductMap.class) {
            arrayList = new ArrayList();
            if (map.containsKey(str) && map.get(str) != null) {
                arrayList = new ArrayList(map.get(str));
            }
        }
        return arrayList;
    }
}
