package defpackage;

import com.google.gson.reflect.TypeToken;
import com.huawei.hms.framework.common.ExceptionCode;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import health.compact.a.CommonUtil;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes4.dex */
public class gwa {
    private static int d;

    public static float a(int i) {
        float f;
        float f2;
        float f3;
        if (i == 0) {
            i = 170;
        }
        if (i < 149) {
            f2 = i;
            f3 = 0.48f;
        } else if (i < 166) {
            f2 = i;
            f3 = (0.0025f * f2) + 0.145f;
        } else {
            if (i >= 185) {
                f = 110.0f;
                return f / 100.0f;
            }
            f2 = i;
            f3 = 0.56f;
        }
        f = f2 * f3;
        return f / 100.0f;
    }

    public static boolean c(int i) {
        return i == 264 || i == 258;
    }

    public static float e(int i) {
        if (i <= 0 || i >= 30) {
            return 1.0f;
        }
        if (i <= 8) {
            return 0.75f;
        }
        if (i <= 11) {
            return 0.85f;
        }
        return i >= 15 ? 1.1f : 1.0f;
    }

    public static String c(double d2) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.ENGLISH);
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        return numberFormat.format(d2);
    }

    public static String a(String str, int i) {
        int i2;
        ArrayList arrayList = new ArrayList(10);
        if (!"".equals(str)) {
            ArrayList arrayList2 = (ArrayList) gvv.a(str, new TypeToken<ArrayList<Integer>>() { // from class: gwa.2
            });
            if (arrayList2 != null) {
                i2 = arrayList2.size();
            } else {
                arrayList2 = arrayList;
                i2 = 0;
            }
            if (i2 >= 12) {
                arrayList2.remove(0);
                arrayList2.add(Integer.valueOf(i));
                return CommonUtil.d(arrayList2);
            }
            arrayList2.add(Integer.valueOf(i));
            return CommonUtil.d(arrayList2);
        }
        arrayList.add(Integer.valueOf(i));
        return CommonUtil.d(arrayList);
    }

    public static String a(String str, double d2) {
        ArrayList arrayList = new ArrayList(10);
        if (!"".equals(str)) {
            ArrayList arrayList2 = (ArrayList) gvv.a(str, new TypeToken<ArrayList<Double>>() { // from class: gwa.3
            });
            if (koq.c(arrayList2) && arrayList2.size() >= 12) {
                arrayList2.remove(0);
                arrayList2.add(Double.valueOf(d2));
                return CommonUtil.d(arrayList2);
            }
            arrayList2.add(Double.valueOf(d2));
            return CommonUtil.d(arrayList2);
        }
        arrayList.add(Double.valueOf(d2));
        return CommonUtil.d(arrayList);
    }

    public static void d(UserInfomation userInfomation, String str, String str2, String str3) {
        boolean z = true;
        boolean z2 = str2 == null || str3 == null;
        if (userInfomation != null && !z2) {
            z = false;
        }
        if (str == null || "".equals(str) || z) {
            return;
        }
        ArrayList arrayList = (ArrayList) gvv.a(str, new TypeToken<ArrayList<Integer>>() { // from class: gwa.5
        });
        if (arrayList == null) {
            LogUtil.h("Track_IndoorRunUtils", "acquireCalibrationData durationDatasList is null");
            return;
        }
        int size = arrayList.size();
        int[] iArr = new int[size];
        for (int i = 0; i < arrayList.size(); i++) {
            iArr[i] = ((Integer) arrayList.get(i)).intValue();
        }
        d = arrayList.size();
        LogUtil.a("Track_IndoorRunUtils", "acquireCalibrationData(), durationDatas :", Integer.valueOf(size));
        ArrayList arrayList2 = (ArrayList) gvv.a(str2, new TypeToken<ArrayList<Integer>>() { // from class: gwa.4
        });
        int size2 = arrayList2.size();
        int[] iArr2 = new int[size2];
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            iArr2[i2] = ((Integer) arrayList2.get(i2)).intValue();
        }
        LogUtil.a("Track_IndoorRunUtils", "acquireCalibrationData(), stepDataList :", Integer.valueOf(size2));
        ArrayList arrayList3 = (ArrayList) gvv.a(str3, new TypeToken<ArrayList<Float>>() { // from class: gwa.1
        });
        if (koq.b(arrayList3)) {
            LogUtil.h("Track_IndoorRunUtils", "acquireCalibrationData distanceDataList is empty");
            return;
        }
        int size3 = arrayList3.size();
        float[] fArr = new float[size3];
        for (int i3 = 0; i3 < arrayList3.size(); i3++) {
            fArr[i3] = ((Float) arrayList3.get(i3)).floatValue();
        }
        LogUtil.a("Track_IndoorRunUtils", "acquireCalibrationData(), distanceData :", Integer.valueOf(size3));
        gvb.d(userInfomation, iArr, iArr2, fArr, 0);
    }

    public static int b() {
        return d;
    }

    public static Map<Integer, Float> d(Map<Integer, Float> map, float f, float f2) {
        if (map == null || map.size() == 0) {
            LogUtil.a("Track_IndoorRunUtils", "changePaceMap paceMap is null");
            return map;
        }
        TreeMap treeMap = new TreeMap();
        float f3 = f2 / f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        int i = 0;
        float f6 = 0.0f;
        for (Map.Entry<Integer, Float> entry : map.entrySet()) {
            float b = (b(entry.getKey().intValue()) * f3) + f4;
            float e = e(entry.getKey().intValue(), entry.getValue().floatValue()) + f6;
            f5 = e / (b / 1000.0f);
            if (b > 1000.0f) {
                if (b > 2000.0f) {
                    i++;
                    treeMap.put(Integer.valueOf(i * ExceptionCode.CRASH_EXCEPTION), Float.valueOf(f5));
                    b -= 1000.0f;
                    e -= f5;
                }
                i++;
                treeMap.put(Integer.valueOf(ExceptionCode.CRASH_EXCEPTION * i), Float.valueOf(f5));
                b -= 1000.0f;
                e -= f5;
            }
            f6 = e;
            f4 = b;
        }
        if (f4 > 1.0E-6d) {
            treeMap.put(Integer.valueOf((int) ((((int) f2) / 1000.0f) * 100.0f * 100000.0f)), Float.valueOf(f5));
        }
        LogUtil.a("Track_IndoorRunUtils", "changePaceMap newPaceMap is ", treeMap.toString());
        return treeMap;
    }

    private static float b(int i) {
        if (i % ExceptionCode.CRASH_EXCEPTION == 0) {
            return 1000.0f;
        }
        return (i / 10000) % 1000;
    }

    private static float e(int i, float f) {
        return i % ExceptionCode.CRASH_EXCEPTION == 0 ? f : (f * ((i / 10000) % 1000)) / 1000.0f;
    }

    public static Map<Double, Double> a(Map<Integer, Float> map, boolean z) {
        double d2;
        double d3;
        TreeMap treeMap = new TreeMap();
        String str = "Track_IndoorRunUtils";
        if (map == null || map.size() == 0) {
            LogUtil.a("Track_IndoorRunUtils", "changePartTimeMap paceMap is null");
            return treeMap;
        }
        if (z) {
            d2 = 13.1099865d;
            d3 = 26.219973d;
        } else {
            d2 = 21.0975d;
            d3 = 42.195d;
        }
        Iterator<Map.Entry<Integer, Float>> it = map.entrySet().iterator();
        boolean z2 = false;
        double d4 = 0.0d;
        boolean z3 = false;
        double d5 = 0.0d;
        while (it.hasNext()) {
            Map.Entry<Integer, Float> next = it.next();
            double b = b(next.getKey().intValue());
            Iterator<Map.Entry<Integer, Float>> it2 = it;
            String str2 = str;
            double d6 = d3;
            double e = e(next.getKey().intValue(), next.getValue().floatValue());
            if (!z2 && (d5 * 1000.0d) + b > d2 * 1000.0d) {
                treeMap.put(Double.valueOf(d2), Double.valueOf(((d2 - d5) * e) + d4));
                z2 = true;
            }
            if (!z3 && (d5 * 1000.0d) + b > d6 * 1000.0d) {
                treeMap.put(Double.valueOf(d6), Double.valueOf(((d6 - d5) * e) + d4));
                z3 = true;
            }
            if (b >= 1000.0d) {
                d4 += next.getValue().floatValue();
                d5 += 1.0d;
            }
            treeMap.put(Double.valueOf(d5), Double.valueOf(d4));
            it = it2;
            str = str2;
            d3 = d6;
        }
        LogUtil.a(str, "changePartTimeMap newPaceMap is ", treeMap.toString());
        return treeMap;
    }
}
