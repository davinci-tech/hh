package defpackage;

import android.text.TextUtils;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.ReflectionUtils;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes9.dex */
public class kuh {

    /* renamed from: a, reason: collision with root package name */
    public static String f14600a = "name";
    public static String b = "is_meta";
    public static String c = "aggregateType";
    public static String d = "id";
    public static String e = "meta_key";
    public static String f = "sampleStatDataType";
    public static String g = "sampleSequenceDataType";
    public static String h = "point_name";
    public static String i = "typeId";
    public static String j = "samplePointDataType";
    private static String l = "HiHealthProcess_RequestUtils";

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static void e(kuv kuvVar, String str, String str2, HiHealthData hiHealthData) {
        char c2;
        str.hashCode();
        switch (str.hashCode()) {
            case 96978:
                if (str.equals("avg")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case 107876:
                if (str.equals("max")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 108114:
                if (str.equals("min")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 114251:
                if (str.equals("sum")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 94851343:
                if (str.equals("count")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        if (c2 == 0) {
            kuvVar.d(hiHealthData.get(str2));
            return;
        }
        if (c2 == 1) {
            kuvVar.b(hiHealthData.get(str2));
            return;
        }
        if (c2 == 2) {
            kuvVar.c(hiHealthData.get(str2));
        } else if (c2 == 3) {
            kuvVar.e(hiHealthData.get(str2));
        } else {
            if (c2 != 4) {
                return;
            }
            kuvVar.a(hiHealthData.get(str2));
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int c(String str) {
        char c2;
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        lowerCase.hashCode();
        switch (lowerCase.hashCode()) {
            case 3665:
                if (lowerCase.equals("sd")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case 96978:
                if (lowerCase.equals("avg")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 107876:
                if (lowerCase.equals("max")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 108114:
                if (lowerCase.equals("min")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 114251:
                if (lowerCase.equals("sum")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 94851343:
                if (lowerCase.equals("count")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        if (c2 == 0) {
            return 7;
        }
        if (c2 == 1) {
            return 3;
        }
        if (c2 == 2) {
            return 4;
        }
        if (c2 == 3) {
            return 5;
        }
        if (c2 != 4) {
            return c2 != 5 ? 0 : 2;
        }
        return 1;
    }

    public static kuu d(ArrayList<kuu> arrayList, HiHealthData hiHealthData) {
        Iterator<kuu> it = arrayList.iterator();
        while (it.hasNext()) {
            kuu next = it.next();
            if (next.b() == hiHealthData.getStartTime() && next.e() == hiHealthData.getEndTime()) {
                return next;
            }
        }
        return null;
    }

    public static kuu b(ArrayList<kuu> arrayList, HiHealthData hiHealthData) {
        Iterator<kuu> it = arrayList.iterator();
        while (it.hasNext()) {
            kuu next = it.next();
            if (next.b() == hiHealthData.getStartTime() && next.e() == hiHealthData.getEndTime() && hiHealthData.getString("device_uniquecode").equals(next.c())) {
                return next;
            }
        }
        return null;
    }

    public static kuu e(ArrayList<kuu> arrayList, HiHealthData hiHealthData, int i2) {
        Iterator<kuu> it = arrayList.iterator();
        while (it.hasNext()) {
            kuu next = it.next();
            if (e(next, hiHealthData, i2)) {
                return next;
            }
        }
        return null;
    }

    private static boolean e(kuu kuuVar, HiHealthData hiHealthData, int i2) {
        switch (i2) {
            case 1:
                return jdl.h(kuuVar.b(), hiHealthData.getStartTime());
            case 2:
                return jdl.c(kuuVar.b(), hiHealthData.getStartTime());
            case 3:
                return jdl.d(kuuVar.b(), hiHealthData.getStartTime());
            case 4:
                return jdl.f(kuuVar.b(), hiHealthData.getStartTime());
            case 5:
                return jdl.i(kuuVar.b(), hiHealthData.getStartTime());
            case 6:
                return jdl.g(kuuVar.b(), hiHealthData.getStartTime());
            case 7:
                return true;
            default:
                return false;
        }
    }

    public static void b(List<? extends kvb> list, final int i2) {
        Collections.sort(list, new Comparator() { // from class: kug
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return kuh.a(i2, (kvb) obj, (kvb) obj2);
            }
        });
    }

    static /* synthetic */ int a(int i2, kvb kvbVar, kvb kvbVar2) {
        return i2 == 1 ? kvbVar2.b() >= kvbVar.b() ? 1 : -1 : kvbVar2.b() > kvbVar.b() ? -1 : 1;
    }

    public static void e(String str, Object[] objArr) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        LogUtil.a(l, "current type need extra process, method name is ", str, " args is ", objArr);
        String[] split = str.split("#");
        int length = split.length;
        if (length != 2) {
            LogUtil.b(l, "extra process method format wrong, current length is ", Integer.valueOf(length));
        } else {
            a(split[0], split[1], objArr);
        }
    }

    private static void a(String str, String str2, Object[] objArr) {
        try {
            ReflectionUtils.b(ReflectionUtils.d(str), str2, Object[].class).invoke(null, objArr);
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException unused) {
            LogUtil.h(l, "reflect reflectExtralProcessMethod fail");
        }
    }
}
