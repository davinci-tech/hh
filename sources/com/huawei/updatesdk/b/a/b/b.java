package com.huawei.updatesdk.b.a.b;

import android.content.pm.PackageInfo;
import android.util.ArrayMap;
import android.util.ArraySet;
import com.huawei.updatesdk.a.a.d.h;
import com.huawei.updatesdk.b.a.b.a;
import com.huawei.updatesdk.b.a.b.c;
import java.io.File;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private final Map<String, com.huawei.updatesdk.b.a.b.a> f10824a;

    public List<String> a(PackageInfo packageInfo) {
        String str;
        if (packageInfo == null || (str = packageInfo.applicationInfo.sourceDir) == null) {
            return null;
        }
        long lastModified = new File(str).lastModified();
        com.huawei.updatesdk.b.a.b.a aVar = a().f10824a.get(packageInfo.packageName);
        if (aVar == null || aVar.b() != lastModified) {
            return b(packageInfo);
        }
        if (aVar.a() == null) {
            return null;
        }
        return aVar.a().a();
    }

    private static ArrayList<String> b(PackageInfo packageInfo) {
        ArraySet<String> arraySet;
        com.huawei.updatesdk.b.a.b.a aVar = new com.huawei.updatesdk.b.a.b.a();
        aVar.a(packageInfo.packageName);
        File file = new File(packageInfo.applicationInfo.sourceDir);
        if (!file.exists()) {
            return null;
        }
        aVar.a(file.lastModified());
        c.a a2 = c.a(file);
        ArrayMap<String, ArraySet<PublicKey>> arrayMap = a2.f10826a;
        if (arrayMap == null || arrayMap.isEmpty() || (arraySet = a2.b) == null || arraySet.isEmpty()) {
            a(aVar);
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        for (Map.Entry<String, ArraySet<PublicKey>> entry : a2.f10826a.entrySet()) {
            if (a2.b.contains(entry.getKey())) {
                Iterator<PublicKey> it = entry.getValue().iterator();
                while (it.hasNext()) {
                    arrayList.add(h.a(it.next().getEncoded()));
                }
            }
        }
        a.C0277a c0277a = new a.C0277a();
        c0277a.a(arrayList);
        aVar.a(c0277a);
        a(aVar);
        return arrayList;
    }

    private static void a(com.huawei.updatesdk.b.a.b.a aVar) {
        a().f10824a.put(aVar.c(), aVar);
    }

    /* renamed from: com.huawei.updatesdk.b.a.b.b$b, reason: collision with other inner class name */
    static class C0278b {

        /* renamed from: a, reason: collision with root package name */
        private static final b f10825a = new b();
    }

    public static b a() {
        return C0278b.f10825a;
    }

    private b() {
        this.f10824a = new HashMap();
    }
}
