package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class jcg {

    /* renamed from: a, reason: collision with root package name */
    public static final ConcurrentHashMap<String, jcg> f13737a = new ConcurrentHashMap<>(24);
    private boolean d = false;
    private boolean e = false;

    public boolean e() {
        return this.d;
    }

    public void e(boolean z) {
        this.d = z;
    }

    public void c(boolean z) {
        this.e = ((Boolean) jdy.d(Boolean.valueOf(z))).booleanValue();
    }

    public static void b(String str, boolean z) {
        if (!a(str)) {
            LogUtil.h("HwDeviceStatusPackageData", "updateHandShakeStatus mac is invalid");
            return;
        }
        ConcurrentHashMap<String, jcg> concurrentHashMap = f13737a;
        jcg jcgVar = concurrentHashMap.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "updateHandShakeStatus value: ";
        objArr[1] = Boolean.valueOf(jcgVar == null);
        LogUtil.a("HwDeviceStatusPackageData", objArr);
        if (jcgVar == null) {
            jcg jcgVar2 = new jcg();
            jcgVar2.c(z);
            concurrentHashMap.putIfAbsent(str, jcgVar2);
        } else {
            jcgVar.c(z);
            concurrentHashMap.put(str, jcgVar);
        }
    }

    public static boolean d(String str) {
        if (!a(str)) {
            LogUtil.h("HwDeviceStatusPackageData", "getOTAStatus mac is invalid");
            return false;
        }
        jcg jcgVar = f13737a.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "getOtaStatus value: ";
        objArr[1] = Boolean.valueOf(jcgVar == null);
        LogUtil.a("HwDeviceStatusPackageData", objArr);
        if (jcgVar == null) {
            return false;
        }
        return jcgVar.e();
    }

    public static void d(String str, boolean z) {
        if (!a(str)) {
            LogUtil.h("HwDeviceStatusPackageData", "updateOTAStatus mac is invalid");
            return;
        }
        ConcurrentHashMap<String, jcg> concurrentHashMap = f13737a;
        jcg jcgVar = concurrentHashMap.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "updateOtaStatus value: ";
        objArr[1] = Boolean.valueOf(jcgVar == null);
        LogUtil.a("HwDeviceStatusPackageData", objArr);
        if (jcgVar == null) {
            jcg jcgVar2 = new jcg();
            jcgVar2.e(z);
            concurrentHashMap.putIfAbsent(str, jcgVar2);
        } else {
            jcgVar.e(z);
            concurrentHashMap.put(str, jcgVar);
        }
    }

    private static boolean a(String str) {
        return !TextUtils.isEmpty(str);
    }
}
