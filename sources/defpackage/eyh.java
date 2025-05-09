package defpackage;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* loaded from: classes8.dex */
public class eyh {

    /* renamed from: a, reason: collision with root package name */
    private static volatile eyh f12382a;
    private static final Object d = new Object();

    public static eyh b() {
        if (f12382a == null) {
            synchronized (d) {
                if (f12382a == null) {
                    f12382a = new eyh();
                }
            }
        }
        return f12382a;
    }

    public eyk d(List<String> list) {
        eym.b("AgnssManager", "getAgnssRequest start");
        eyk eykVar = new eyk();
        eykVar.d(true);
        eykVar.a(true);
        eykVar.c(1);
        eykVar.d(2);
        eykVar.e("getAgnssPluginUrl");
        List<String> a2 = eyf.a(list);
        HashMap hashMap = new HashMap();
        Iterator<String> it = a2.iterator();
        while (it.hasNext()) {
            hashMap.put(it.next(), "HW_AGNSS");
        }
        eykVar.c(hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("X-Request-ID", UUID.randomUUID().toString());
        hashMap2.put("Content-Type", "application/json");
        hashMap2.put("X-Device-Type", "SportsHealth");
        eykVar.b(hashMap2);
        eym.b("AgnssManager", "getAgnssRequest end");
        return eykVar;
    }

    public Map<String, String> b(eyl eylVar) {
        eym.b("AgnssManager", "getAgnssResponse start");
        HashMap hashMap = new HashMap();
        byte[] a2 = eyi.a(eylVar.e());
        if (a2 == null || a2.length == 0) {
            eym.e("AgnssManager", "getAgnssResponse get originData fail");
            return hashMap;
        }
        byte[] b = eye.b(a2);
        if (b == null || b.length == 0) {
            eym.e("AgnssManager", "getAgnssResponse get decompress fail");
            return hashMap;
        }
        File file = new File(eylVar.b(), eyi.d(eylVar.d() + "HW_AGNSS_RTCM_33"));
        if (file.exists()) {
            eym.b("AgnssManager", "getAgnssResponse delete file, isDeleted: " + file.delete());
        }
        if (eyi.d(file.getPath(), b, b.length)) {
            eym.b("AgnssManager", "write file succeed, len: " + file.length());
            hashMap.put(file.getPath(), "HW_AGNSS_RTCM_33");
        } else {
            eym.e("AgnssManager", "write file fail");
        }
        eym.b("AgnssManager", "getAgnssResponse end");
        return hashMap;
    }
}
