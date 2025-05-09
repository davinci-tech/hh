package defpackage;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.ArrayMap;
import android.util.LruCache;
import com.huawei.hihealth.HiAccountInfo;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hihealth.HiHealthKitData;
import com.huawei.hihealth.StartSportParam;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class ima {
    private iio b;
    private LruCache<String, Integer> d;
    private iip e;
    private static final Context c = BaseApplication.getContext();

    /* renamed from: a, reason: collision with root package name */
    private static Map<Map<Integer, String>, HiAccountInfo> f13436a = new ArrayMap(16);

    private ima() {
        this.d = new LruCache<>(20);
        this.b = iio.c();
        this.e = iip.b();
    }

    static class a {
        private static final ima d = new ima();
    }

    public static ima a() {
        return a.d;
    }

    public int j() {
        return ijz.c().e(m(), 0);
    }

    private String m() {
        if (this.b == null) {
            return "";
        }
        String g = g();
        if (!HiCommonUtil.b(g)) {
            LogUtil.c("HiH_HiHealthKitBinderHelper", "getStringHuid() from cache");
            return g;
        }
        int i = i();
        String a2 = this.b.a(i);
        ArrayMap arrayMap = new ArrayMap(16);
        arrayMap.put(Integer.valueOf(i), a2);
        HiAccountInfo b = this.b.b(i, a2);
        if (b != null) {
            b.setLogin(1);
            f13436a.put(arrayMap, b);
            LogUtil.c("HiH_HiHealthKitBinderHelper", "getStringHuid() from DB");
        }
        return a2;
    }

    public void a(String str) {
        if (HiCommonUtil.b(str)) {
            return;
        }
        for (Map.Entry<Map<Integer, String>, HiAccountInfo> entry : f13436a.entrySet()) {
            HiAccountInfo value = entry.getValue();
            if (str.equals(value.getHuid())) {
                value.setLogin(1);
                f13436a.put(entry.getKey(), value);
            }
        }
    }

    public void b(String str) {
        if (HiCommonUtil.b(str)) {
            return;
        }
        for (Map.Entry<Map<Integer, String>, HiAccountInfo> entry : f13436a.entrySet()) {
            HiAccountInfo value = entry.getValue();
            if (str.equals(value.getHuid())) {
                value.setLogin(0);
                f13436a.put(entry.getKey(), value);
            }
        }
    }

    private String g() {
        String str = null;
        for (Map.Entry<Map<Integer, String>, HiAccountInfo> entry : f13436a.entrySet()) {
            if (entry.getValue().getIsLogin() == 1 && entry.getValue().getAppId() == i()) {
                str = entry.getValue().getHuid();
            }
        }
        return str;
    }

    int c(String str) throws RemoteException {
        Integer num = this.d.get(str);
        if (num != null) {
            return num.intValue();
        }
        int e = e(str);
        if (e <= 0) {
            String str2 = "initAppId() app <= 0 " + e(str, e);
            ReleaseLogUtil.d("HiH_HiHealthKitBinderHelper", str2);
            throw new RemoteException(str2);
        }
        this.d.put(str, Integer.valueOf(e));
        return e;
    }

    public ikv f() throws RemoteException {
        String e = ivw.e(c);
        if (c() == 0) {
            ReleaseLogUtil.e("HiH_HiHealthKitBinderHelper", "getInsertAppContext() isAppValid health or wear, packageName = ", e);
            return new ikv(c(e), e);
        }
        return new ikv(e(), e);
    }

    int c() {
        return h();
    }

    private int h() {
        if (ivw.a(c, "com.huawei.health")) {
            LogUtil.c("HiH_HiHealthKitBinderHelper", "checkAppTypeSync() isAppValid true needn't to Auth, packageName = ", "com.huawei.health");
            return 0;
        }
        LogUtil.c("HiH_HiHealthKitBinderHelper", "checkAppTypeSync() is thirdParty, packageName = ", "com.huawei.health");
        return -1;
    }

    public int e() throws RemoteException {
        return bBG_(this.d, ivw.a(c));
    }

    public int d() throws RemoteException {
        return bBG_(this.d, ivw.a(c));
    }

    int d(String str) throws RemoteException {
        return bBG_(this.d, str);
    }

    private int bBG_(LruCache<String, Integer> lruCache, String str) throws RemoteException {
        Integer num = lruCache.get(str);
        if (num != null) {
            return num.intValue();
        }
        int g = g(str);
        LogUtil.c("HiH_HiHealthKitBinderHelper", "initCurrentAppId() ", e(str, g));
        if (g <= 0) {
            String str2 = "initCurrentAppId() app <= 0 " + e(str, g);
            ReleaseLogUtil.d("HiH_HiHealthKitBinderHelper", str2);
            throw new RemoteException(str2);
        }
        lruCache.put(str, Integer.valueOf(g));
        return g;
    }

    private int g(String str) {
        HiAppInfo c2;
        int a2 = this.e.a(str);
        if (a2 > 0) {
            LogUtil.a("HiHealthKitBinderHelper", "appID = ", Integer.valueOf(a2));
            return a2;
        }
        ReleaseLogUtil.e("HiH_HiHealthKitBinderHelper", "queryOrCreateAppId() app <= 0");
        if ("com.huawei.health".equals(str)) {
            c2 = ivw.b(c, str);
        } else {
            c2 = ivw.c(c, str);
        }
        if (c2 == null) {
            return a2;
        }
        ReleaseLogUtil.e("HiH_HiHealthKitBinderHelper", "queryOrCreateAppId appInfo = ", c2);
        int c3 = (int) this.e.c(c2, 0);
        LogUtil.a("HiHealthKitBinderHelper", "appID = ", Integer.valueOf(c3));
        return c3;
    }

    public int e(String str) {
        return f(str);
    }

    private int f(String str) {
        HiAppInfo c2;
        int a2 = this.e.a(str);
        if (a2 <= 0) {
            ReleaseLogUtil.e("HiH_HiHealthKitBinderHelper", "initBinderSync() app <= 0");
            if ("com.huawei.health".equals(str)) {
                c2 = ivw.b(c, str);
            } else {
                c2 = ivw.c(c, str);
            }
            if (c2 == null) {
                return a2;
            }
            ReleaseLogUtil.e("HiH_HiHealthKitBinderHelper", "initBinderSync() appInfo = ", c2);
            a2 = (int) this.e.c(c2, 0);
        }
        LogUtil.c("HiH_HiHealthKitBinderHelper", "initBinderSync() ", e(str, a2));
        return a2;
    }

    public int i() {
        return g("com.huawei.health");
    }

    public static boolean b() {
        KeyguardManager keyguardManager = (KeyguardManager) c.getSystemService("keyguard");
        return keyguardManager != null && keyguardManager.inKeyguardRestrictedInputMode();
    }

    public static boolean d(int[] iArr, int[] iArr2) {
        boolean z;
        if (iArr == null || iArr.length <= 0) {
            z = false;
        } else {
            for (int i : iArr) {
                if (!HiHealthDataType.t(i)) {
                    ReleaseLogUtil.d("HiH_HiHealthKitBinderHelper", "isKitValidTypes readType = ", Integer.valueOf(i), " not allow to apply");
                    return false;
                }
            }
            z = true;
        }
        if (iArr2 == null || iArr2.length <= 0) {
            return z;
        }
        for (int i2 : iArr2) {
            if (!HiHealthDataType.s(i2)) {
                ReleaseLogUtil.d("HiH_HiHealthKitBinderHelper", "isKitValidTypes writeType = ", Integer.valueOf(i2), " not allow to apply");
                return false;
            }
        }
        return true;
    }

    public static int a(List list) {
        Object obj = list.get(0);
        if (obj instanceof HiHealthKitData) {
            return ((HiHealthKitData) obj).getType();
        }
        return 0;
    }

    public static <T> String c(Map map, T t) {
        Object obj = map.get(t);
        return obj instanceof String ? (String) obj : "";
    }

    public static <T> int a(Map map, T t) {
        Object obj = map.get(t);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public static <T> long e(Map map, T t) {
        Object obj = map.get(t);
        if (obj instanceof Long) {
            return ((Long) obj).longValue();
        }
        return 0L;
    }

    private String e(String str, int i) {
        return "packageName = " + str + ", app = " + i;
    }

    public String b(StartSportParam startSportParam) {
        JSONObject jSONObject = new JSONObject();
        Bundle valueHolder = startSportParam.getValueHolder();
        try {
            for (String str : valueHolder.keySet()) {
                jSONObject.put(str, valueHolder.get(str));
            }
        } catch (JSONException e) {
            ReleaseLogUtil.c("HiH_HiHealthKitBinderHelper", "Error put value: ", e.getMessage());
        }
        return jSONObject.toString();
    }

    public static List<Integer> e(List<HiHealthKitData> list) {
        ArrayList arrayList = new ArrayList(10);
        Iterator<HiHealthKitData> it = list.iterator();
        while (it.hasNext()) {
            int type = it.next().getType();
            if (!arrayList.contains(Integer.valueOf(type))) {
                arrayList.add(Integer.valueOf(type));
            }
        }
        return arrayList;
    }
}
