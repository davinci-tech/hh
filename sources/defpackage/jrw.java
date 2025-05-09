package defpackage;

import android.util.SparseIntArray;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes5.dex */
public class jrw {
    private static jrw c;

    /* renamed from: a, reason: collision with root package name */
    private final SparseIntArray f14039a;
    private final int b;
    private final LinkedHashMap<Integer, Integer> e;

    private boolean a(int i, int i2) {
        return (i2 & i) == i;
    }

    private jrw() {
        LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap<>(4);
        this.e = linkedHashMap;
        SparseIntArray sparseIntArray = new SparseIntArray(4);
        this.f14039a = sparseIntArray;
        LogUtil.a("DetailGpsWorkoutUtil", "DetailGpsWorkoutUtil");
        linkedHashMap.put(8, 8);
        linkedHashMap.put(4, 10);
        linkedHashMap.put(2, 8);
        linkedHashMap.put(1, 8);
        this.b = 15;
        sparseIntArray.put(2, 0);
        sparseIntArray.put(4, 1);
        sparseIntArray.put(1, 3);
        sparseIntArray.put(8, 2);
    }

    public static jrw a() {
        jrw jrwVar;
        synchronized (jrw.class) {
            if (c == null) {
                c = new jrw();
            }
            jrwVar = c;
        }
        return jrwVar;
    }

    public int d(kbf kbfVar) {
        if (kbfVar != null) {
            return kbfVar.d();
        }
        return -1;
    }

    public boolean a(kbf kbfVar) {
        if (kbfVar == null) {
            return false;
        }
        LogUtil.a("DetailGpsWorkoutUtil", "gpsHeaderBitmap is" + kbfVar.b() + ",isSupportAltitude is" + (kbfVar.b() & 128));
        return (kbfVar.b() & 128) == 128;
    }

    public kbf a(byte[] bArr) {
        if (bArr == null || bArr.length < 64) {
            return null;
        }
        String c2 = c(bArr, 0, 64);
        LogUtil.a("DetailGpsWorkoutUtil", "getGpsFrameHeader info is ", c2);
        return c(c2);
    }

    private static String c(byte[] bArr, int i, int i2) {
        if (bArr == null || i > i2) {
            LogUtil.h("DetailGpsWorkoutUtil", "byteToString bytes is null");
            return "";
        }
        if (i < 0) {
            LogUtil.h("DetailGpsWorkoutUtil", "Start less than zero");
            return "";
        }
        if (i2 > bArr.length) {
            LogUtil.h("DetailGpsWorkoutUtil", "End is too large");
            return "";
        }
        byte[] bArr2 = new byte[i2 - i];
        int i3 = 0;
        while (i < i2) {
            bArr2[i3] = bArr[i];
            i++;
            i3++;
        }
        return cvx.d(bArr2);
    }

    public Map<Long, double[]> c(byte[] bArr, int i) {
        StringBuilder sb = new StringBuilder(16);
        if (bArr != null) {
            try {
                sb.append(new String(bArr, "utf-8"));
            } catch (UnsupportedEncodingException e) {
                LogUtil.b("DetailGpsWorkoutUtil", "getGPSMap e is ", e.getMessage());
            }
        }
        LogUtil.a("DetailGpsWorkoutUtil", "getGPSMap sb is ", sb.toString());
        return e(sb.toString());
    }

    private Map<Long, double[]> e(String str) {
        HashMap hashMap = new HashMap(16);
        if (str.length() > 64) {
            String substring = str.substring(0, 64);
            kbf c2 = c(substring);
            if (a(c2.b())) {
                b(hashMap, d(c2.b(), str.substring(substring.length(), str.length())));
            }
        }
        LogUtil.a("DetailGpsWorkoutUtil", "parse end.");
        return hashMap;
    }

    private void b(Map<Long, double[]> map, List<kbd> list) {
        if (list != null) {
            for (kbd kbdVar : list) {
                if (kbdVar != null) {
                    map.put(Long.valueOf(kbdVar.e()), kbdVar.c());
                }
            }
        }
    }

    private kbf c(String str) {
        kbf kbfVar = new kbf();
        try {
            kbfVar.d(Integer.parseInt(str.substring(0, 16), 16));
            kbfVar.c(Integer.parseInt(str.substring(16, 20), 16));
            kbfVar.a(Integer.parseInt(str.substring(20, 24), 16));
            int parseInt = Integer.parseInt(str.substring(24, 28), 16);
            kbfVar.b(parseInt);
            if (parseInt > 1) {
                kbfVar.e(Integer.parseInt(str.substring(28, 30), 16));
            } else {
                kbfVar.e(-1);
            }
        } catch (NumberFormatException e) {
            LogUtil.a("DetailGpsWorkoutUtil", "getGPSFrameHeader NumberFormatException:", e.getMessage());
        }
        return kbfVar;
    }

    private boolean a(int i) {
        int i2 = this.b;
        boolean z = (i ^ i2) <= i2;
        LogUtil.a("DetailGpsWorkoutUtil", "canParseGPSFile : ", Boolean.valueOf(z), "bitmap : ", Integer.valueOf(i));
        return z;
    }

    private List<kbd> d(int i, String str) {
        Set<Map.Entry<Integer, Integer>> entrySet = this.e.entrySet();
        ArrayList arrayList = new ArrayList(16);
        int i2 = 0;
        int i3 = 0;
        while (i2 < str.length()) {
            kbd kbdVar = new kbd();
            int size = this.e.size();
            double[] dArr = new double[size];
            for (Map.Entry<Integer, Integer> entry : entrySet) {
                int intValue = entry.getKey().intValue();
                int intValue2 = entry.getValue().intValue() + i2;
                if (str.length() < intValue2) {
                    LogUtil.h("DetailGpsWorkoutUtil", "parseFileByBitmap gpsFileDataList.size()", Integer.valueOf(arrayList.size()));
                    return arrayList;
                }
                if (a(intValue, i)) {
                    dArr[this.f14039a.get(intValue)] = c(intValue, str.substring(i2, intValue2));
                    i2 = intValue2;
                }
            }
            if (size > 0 && dArr[0] != 0.0d && dArr[1] != 0.0d) {
                kbdVar.e(i3);
                kbdVar.c(dArr);
                arrayList.add(kbdVar);
            } else {
                LogUtil.a("DetailGpsWorkoutUtil", "lat and lon is not suit.");
            }
            i3++;
        }
        LogUtil.a("DetailGpsWorkoutUtil", "gpsFileDataList.size()", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    private double c(int i, String str) {
        double parseLong;
        try {
            if (i == 1) {
                parseLong = Long.parseLong(str, 16) * 1000;
            } else if (i == 2) {
                double parseLong2 = Long.parseLong(str, 16) / 1.6777216E7d;
                parseLong = new BigDecimal((parseLong2 < 0.0d || parseLong2 > 180.0d) ? 0.0d : parseLong2 - 90.0d).setScale(7, 4).doubleValue();
            } else if (i == 4) {
                double parseLong3 = Long.parseLong(str, 16) / 1.6777216E7d;
                parseLong = new BigDecimal((parseLong3 < 0.0d || parseLong3 > 360.0d) ? 0.0d : parseLong3 - 180.0d).setScale(7, 4).doubleValue();
            } else if (i == 8) {
                double parseLong4 = Long.parseLong(str, 16) / 256.0d;
                parseLong = new BigDecimal(parseLong4 > 0.0d ? parseLong4 - 1000.0d : 0.0d).setScale(3, 4).doubleValue();
            } else {
                LogUtil.a("DetailGpsWorkoutUtil", "no support bitmap.");
                return 0.0d;
            }
            return parseLong;
        } catch (NumberFormatException unused) {
            LogUtil.b("DetailGpsWorkoutUtil", "parseFileAndSaveList NumberFormatException.");
            return 0.0d;
        }
    }
}
