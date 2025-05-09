package defpackage;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import com.huawei.health.R;
import com.huawei.uikit.hwlunar.utils.HwLunarCalendar;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class slt {
    private static slt d;
    private Context p;
    private HwLunarCalendar q;

    /* renamed from: a, reason: collision with root package name */
    private String[] f17112a = new String[205];
    private String[] c = new String[205];
    private SparseArray<Integer> e = new SparseArray<>();
    private SparseArray<String> b = new SparseArray<>();
    private SparseArray<String> j = new SparseArray<>();
    private Map<String, Integer> f = new HashMap(10);
    private Map<String, Integer> g = new HashMap(10);
    private Map<Integer, List<String>> h = new HashMap(10);
    private Map<Integer, String> i = new HashMap(10);
    private Map<String, Integer> k = new HashMap(10);
    private Map<String, String> m = new HashMap(10);
    private Map<String, List<String>> o = new HashMap(10);
    private List<Object> n = new ArrayList(10);
    private List<Object> l = new ArrayList(10);
    private List<Object> s = new ArrayList(10);
    private Map<String, List<String>> t = new HashMap(10);
    private String r = null;
    private String u = null;
    private String w = null;
    private final Object y = new Object();
    private List<String> x = new ArrayList(10);
    private Map<String, Integer> v = new HashMap(10);

    protected slt(Context context) {
        this.p = context.getApplicationContext();
        this.q = new HwLunarCalendar(this.p);
        n();
    }

    public static GregorianCalendar b() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(2101, 0, 27);
        return gregorianCalendar;
    }

    public static slt d(Context context) {
        slt m;
        synchronized (slt.class) {
            if (d == null) {
                d(new slt(context));
            }
            m = m();
        }
        return m;
    }

    public static GregorianCalendar e() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(1900, 0, 31);
        return gregorianCalendar;
    }

    private static slt m() {
        return d;
    }

    private void n() {
        Log.w("HwLunarDataOperate", "init ");
        int i = 0;
        for (int i2 = 1898; i2 < 2103; i2++) {
            this.q.e(i2, 8, 8);
            String b = this.q.b();
            this.f17112a[i] = b;
            this.c[i] = String.valueOf(i2);
            this.e.put(i2, Integer.valueOf(i));
            this.b.put(i2, b);
            this.j.put(i, b);
            this.f.put(b, Integer.valueOf(i2));
            this.g.put(b, Integer.valueOf(i));
            i++;
        }
        o();
        this.v.put(this.p.getString(R.string._2130850787_res_0x7f0233e3) + this.p.getString(R.string._2130850835_res_0x7f023413), 1);
        this.v.put(this.p.getString(R.string._2130850787_res_0x7f0233e3) + this.p.getString(R.string._2130851412_res_0x7f023654), 2);
        this.v.put(this.p.getString(R.string._2130850785_res_0x7f0233e1), 3);
        this.v.put(this.p.getString(R.string._2130850785_res_0x7f0233e1) + this.p.getString(R.string._2130851427_res_0x7f023663), 3);
    }

    private void o() {
        BufferedReader bufferedReader;
        InputStream inputStream;
        BufferedReader bufferedReader2;
        InputStream inputStream2 = null;
        String str = null;
        r1 = null;
        r1 = null;
        BufferedReader bufferedReader3 = null;
        try {
            inputStream = this.p.getResources().openRawResource(R.raw._2131886080_res_0x7f120000);
            try {
                try {
                    bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                } catch (IOException unused) {
                }
            } catch (Throwable th) {
                th = th;
                InputStream inputStream3 = inputStream;
                bufferedReader = bufferedReader3;
                inputStream2 = inputStream3;
            }
        } catch (IOException unused2) {
            inputStream = null;
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = null;
        }
        try {
            ArrayList arrayList = new ArrayList(10);
            int i = 0;
            String str2 = null;
            while (true) {
                String readLine = bufferedReader2.readLine();
                if (readLine == null) {
                    slv.e(inputStream);
                    slv.a(bufferedReader2);
                    return;
                }
                if (i > 10000) {
                    Log.e("HwLunarDataOperate", "loadAllMonth: too many lines in all month config file!");
                    slv.e(inputStream);
                    slv.a(bufferedReader2);
                    return;
                }
                if (readLine.length() > 6) {
                    Log.e("HwLunarDataOperate", "hex month string length is illegal");
                    slv.e(inputStream);
                    slv.a(bufferedReader2);
                    return;
                }
                String[] c = c(e(a(readLine)));
                e(i, c);
                if (str == null) {
                    str = c[0];
                    str2 = c[1];
                    arrayList.add(c[2]);
                } else if (str.equals(c[0])) {
                    arrayList.add(c[2]);
                } else {
                    try {
                        this.h.put(Integer.valueOf(str), arrayList);
                    } catch (NumberFormatException unused3) {
                        Log.e("HwLunarDataOperate", "parse string to int error, westernYear = " + str);
                    }
                    this.o.put(str2, arrayList);
                    ArrayList arrayList2 = new ArrayList(10);
                    String str3 = c[0];
                    String str4 = c[1];
                    arrayList2.add(c[2]);
                    arrayList = arrayList2;
                    str = str3;
                    str2 = str4;
                }
                i++;
            }
        } catch (IOException unused4) {
            bufferedReader3 = bufferedReader2;
            Log.e("HwLunarDataOperate", "loadAllMonth has an Exception.");
            slv.e(inputStream);
            slv.a(bufferedReader3);
        } catch (Throwable th3) {
            th = th3;
            inputStream2 = inputStream;
            bufferedReader = bufferedReader2;
            slv.e(inputStream2);
            slv.a(bufferedReader);
            throw th;
        }
    }

    public Map<String, Integer> a() {
        return this.g;
    }

    public void a(int i) {
        synchronized (this.y) {
            this.r = null;
            this.u = null;
            this.w = null;
            if (!this.t.isEmpty()) {
                this.t = new HashMap(10);
            }
            this.l = b(i - (-2131884397));
            this.n = b(i - (-2131884398));
            this.s = b(i - (-2131884399));
        }
    }

    public GregorianCalendar c(String str, String str2, String str3, boolean z) {
        GregorianCalendar e;
        synchronized (this.y) {
            int intValue = this.f.get(str).intValue();
            String str4 = str + str2 + str3;
            e = !this.n.isEmpty() ? e(this.n, str4) : null;
            if (e == null && !this.s.isEmpty()) {
                e = e(this.s, str4);
            }
            if (e == null && !this.l.isEmpty()) {
                e = e(this.l, str4);
            }
            if (e == null) {
                a(intValue);
                if (!this.n.isEmpty()) {
                    e = e(this.n, str4);
                }
                if (e == null && !this.s.isEmpty()) {
                    e = e(this.s, str4);
                }
                if (e == null && z) {
                    e = new GregorianCalendar();
                }
            }
        }
        return e;
    }

    public String[] c() {
        return this.c;
    }

    public Map<Integer, String> d() {
        return this.i;
    }

    public SparseArray<String> edM_() {
        return this.b;
    }

    public String[] f() {
        return this.f17112a;
    }

    public Map<String, Integer> g() {
        return this.f;
    }

    public Map<String, Integer> h() {
        return this.k;
    }

    public Map<String, List<String>> i() {
        return this.o;
    }

    public Map<String, List<String>> l() {
        return this.t;
    }

    private static void d(slt sltVar) {
        d = sltVar;
    }

    private void e(int i, String[] strArr) {
        if (strArr != null && strArr.length == 4) {
            this.i.put(Integer.valueOf(i), strArr[1] + "_" + strArr[2]);
            this.k.put(strArr[1] + "_" + strArr[2], Integer.valueOf(i));
            this.m.put(strArr[1] + strArr[2], strArr[3]);
            return;
        }
        Log.e("HwLunarDataOperate", "decode month table error");
    }

    private int a(String str) {
        if (str == null) {
            return 0;
        }
        try {
            return Integer.parseInt(str, 16);
        } catch (IllegalFormatException unused) {
            Log.e("HwLunarDataOperate", "parse string to hex error, hexStr = " + str);
            return 0;
        }
    }

    public GregorianCalendar b(String str, String str2, String str3, int i, boolean z) {
        GregorianCalendar e;
        synchronized (this.y) {
            int intValue = this.f.get(str).intValue();
            String str4 = str + str2 + str3;
            e = !this.n.isEmpty() ? e(this.n, str4) : null;
            if (e == null && !this.s.isEmpty()) {
                e = e(this.s, str4);
            }
            if (e == null && !this.l.isEmpty()) {
                e = e(this.l, str4);
            }
            if (e == null) {
                a(intValue);
                if (!this.n.isEmpty()) {
                    e = e(this.n, str4);
                }
                if (e == null && !this.s.isEmpty()) {
                    e = e(this.s, str4);
                }
                if (e == null) {
                    c(str, str2, str3, i, z);
                }
            }
        }
        return e;
    }

    private String[] c(int[] iArr) {
        if (iArr.length != 6) {
            Log.e("HwLunarDataOperate", "decode year month table error");
            return new String[0];
        }
        String[] strArr = new String[4];
        strArr[0] = iArr[0] + "";
        StringBuilder sb = new StringBuilder();
        String[] strArr2 = slv.c;
        sb.append(strArr2[iArr[1] % strArr2.length]);
        String[] strArr3 = slv.f17114a;
        sb.append(strArr3[iArr[2] % strArr3.length]);
        String[] strArr4 = slv.h;
        sb.append(strArr4[iArr[2] % strArr4.length]);
        sb.append("年");
        sb.append(iArr[0]);
        strArr[1] = sb.toString();
        if (iArr[3] == 0) {
            String[] strArr5 = slv.d;
            strArr[2] = strArr5[iArr[4] % strArr5.length];
        } else {
            StringBuilder sb2 = new StringBuilder("闰");
            String[] strArr6 = slv.d;
            sb2.append(strArr6[iArr[4] % strArr6.length]);
            strArr[2] = sb2.toString();
        }
        String[] strArr7 = slv.e;
        strArr[3] = strArr7[(iArr[5] + 28) % strArr7.length];
        return strArr;
    }

    private GregorianCalendar b(String str) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        if (str.length() == 8) {
            String substring = str.substring(0, 4);
            String substring2 = str.substring(4, 6);
            String substring3 = str.substring(6, 8);
            if (substring2.indexOf("0") != -1 && substring2.indexOf("0") != 1) {
                substring2 = substring2.substring(1, 2);
            }
            try {
                gregorianCalendar.set(Integer.parseInt(substring), Integer.parseInt(substring2) - 1, Integer.parseInt(substring3));
            } catch (NumberFormatException unused) {
                Log.e("HwLunarDataOperate", "parse string to int error.");
            }
        }
        return gregorianCalendar;
    }

    private GregorianCalendar c(String str, String str2, String str3, int i, boolean z) {
        if (i == 1) {
            String str4 = this.m.get(str + str2);
            if (str4 != null) {
                if (this.v.get(str3).intValue() > this.v.get(str4).intValue()) {
                    return c(str, str2, str4, false);
                }
            } else if (z) {
                return new GregorianCalendar();
            }
        } else {
            if (i == 0) {
                if (str2.indexOf(this.p.getString(R.string._2130851469_res_0x7f02368d)) != -1) {
                    GregorianCalendar b = b(str, str2, str3, 1, false);
                    if (b != null) {
                        return b;
                    }
                    String replace = str2.replace(this.p.getString(R.string._2130851469_res_0x7f02368d), "");
                    GregorianCalendar c = c(str, replace, str3, false);
                    if (c != null) {
                        return c;
                    }
                    return z ? new GregorianCalendar() : b(str, replace, str3, 1, false);
                }
                GregorianCalendar b2 = b(str, str2, str3, 1, false);
                return (b2 == null && z) ? new GregorianCalendar() : b2;
            }
            if (z) {
                return new GregorianCalendar();
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v10 */
    /* JADX WARN: Type inference failed for: r14v2 */
    /* JADX WARN: Type inference failed for: r14v5 */
    /* JADX WARN: Type inference failed for: r14v6, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r4v5 */
    private List<Object> b(int i) {
        Throwable th;
        ?? r14;
        InputStream inputStream;
        ?? r4;
        ?? bufferedReader;
        ArrayList arrayList = new ArrayList(10);
        InputStream inputStream2 = null;
        try {
            inputStream = this.p.getResources().openRawResource(i);
            try {
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                } catch (IOException unused) {
                }
            } catch (Throwable th2) {
                th = th2;
                InputStream inputStream3 = inputStream2;
                inputStream2 = inputStream;
                r14 = inputStream3;
            }
        } catch (IOException unused2) {
            inputStream = null;
        } catch (Throwable th3) {
            th = th3;
            r14 = 0;
        }
        try {
            String readLine = bufferedReader.readLine();
            if (readLine != null && readLine.length() <= 7) {
                try {
                    int parseInt = Integer.parseInt(readLine, 16);
                    HashMap hashMap = new HashMap(10);
                    int i2 = 1;
                    while (true) {
                        String readLine2 = bufferedReader.readLine();
                        if (readLine2 == null) {
                            arrayList.add(hashMap);
                            r4 = bufferedReader;
                            break;
                        }
                        if (i2 > 1000) {
                            Log.e("HwLunarDataOperate", "readOneYearData: Too many lines in one year data file");
                            slv.e(inputStream);
                            slv.a(bufferedReader);
                            return arrayList;
                        }
                        if (readLine2.length() > 7) {
                            Log.e("HwLunarDataOperate", "readOneYearData: yearData length is illegal, line = " + readLine2);
                            slv.e(inputStream);
                            slv.a(bufferedReader);
                            return arrayList;
                        }
                        String[] e = e(d(a(readLine2)), parseInt);
                        if (e == null || e.length != 4) {
                            break;
                        }
                        hashMap.put(e[1] + e[2] + e[3], readLine2);
                        c(e);
                        i2++;
                    }
                    Log.e("HwLunarDataOperate", "readOneYearData: decode error");
                    slv.e(inputStream);
                    slv.a(bufferedReader);
                    return arrayList;
                } catch (NumberFormatException unused3) {
                    Log.e("HwLunarDataOperate", "readOneYearData: parse string to hex error, line = " + readLine);
                    slv.e(inputStream);
                    slv.a(bufferedReader);
                    return arrayList;
                }
            }
            Log.e("HwLunarDataOperate", "readOneYearData: year index length is illegal, line = " + readLine);
            slv.e(inputStream);
            slv.a(bufferedReader);
            return arrayList;
        } catch (IOException unused4) {
            inputStream2 = bufferedReader;
            Log.e("HwLunarDataOperate", "readOneYearData has an Exception.");
            r4 = inputStream2;
            slv.e(inputStream);
            slv.a(r4);
            return arrayList;
        } catch (Throwable th4) {
            th = th4;
            inputStream2 = inputStream;
            r14 = bufferedReader;
            slv.e(inputStream2);
            slv.a(r14);
            throw th;
        }
    }

    private void e(String[] strArr) {
        if (strArr == null || strArr.length != 4) {
            return;
        }
        this.t.put(this.r + this.u, this.x);
        ArrayList arrayList = new ArrayList(10);
        this.x = arrayList;
        this.u = strArr[2];
        this.r = strArr[1];
        String str = strArr[3];
        this.w = str;
        arrayList.add(str);
    }

    private GregorianCalendar e(List<Object> list, String str) {
        int i;
        Map map = (Map) list.get(0);
        if (map.get(str) == null) {
            return null;
        }
        int a2 = a((String) map.get(str));
        int[] iArr = {a2 & 15, (a2 >> 4) & 31, (a2 >> 17) & 1};
        String substring = str.substring(4, 8);
        try {
            i = Integer.parseInt(substring) + iArr[2];
        } catch (NumberFormatException unused) {
            Log.e("HwLunarDataOperate", "parse string to int error, lunarYear = " + substring);
            i = 0;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        String[] strArr = slv.j;
        sb.append(strArr[iArr[0] % strArr.length]);
        String[] strArr2 = slv.b;
        sb.append(strArr2[iArr[1] % strArr2.length]);
        return b(sb.toString());
    }

    private void c(String[] strArr) {
        String str = this.p.getString(R.string._2130850782_res_0x7f0233de) + this.p.getString(R.string._2130851427_res_0x7f023663);
        if (this.u == null && str.equals(strArr[3])) {
            this.u = strArr[2];
            this.r = strArr[1];
            String str2 = strArr[3];
            this.w = str2;
            this.x.add(str2);
            return;
        }
        String str3 = this.u;
        if (str3 != null && strArr[2].equals(str3)) {
            String str4 = strArr[3];
            this.w = str4;
            this.x.add(str4);
        } else if (this.u != null) {
            e(strArr);
        } else {
            Log.e("HwLunarDataOperate", "readOneYearData: load data error");
        }
    }

    private String[] e(int[] iArr, int i) {
        if (iArr != null && iArr.length == 8) {
            String[] strArr = new String[4];
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            String[] strArr2 = slv.j;
            sb.append(strArr2[iArr[0] % strArr2.length]);
            String[] strArr3 = slv.b;
            sb.append(strArr3[iArr[1] % strArr3.length]);
            strArr[0] = sb.toString();
            int i2 = iArr[4];
            StringBuilder sb2 = new StringBuilder();
            String[] strArr4 = slv.c;
            sb2.append(strArr4[iArr[2] % strArr4.length]);
            String[] strArr5 = slv.f17114a;
            sb2.append(strArr5[iArr[3] % strArr5.length]);
            String[] strArr6 = slv.h;
            sb2.append(strArr6[iArr[3] % strArr6.length]);
            sb2.append("年");
            sb2.append(i - i2);
            strArr[1] = sb2.toString();
            if (iArr[5] == 0) {
                String[] strArr7 = slv.d;
                strArr[2] = strArr7[iArr[6] % strArr7.length];
            } else {
                StringBuilder sb3 = new StringBuilder("闰");
                String[] strArr8 = slv.d;
                sb3.append(strArr8[iArr[6] % strArr8.length]);
                strArr[2] = sb3.toString();
            }
            String[] strArr9 = slv.e;
            strArr[3] = strArr9[iArr[7] % strArr9.length];
            return strArr;
        }
        Log.e("HwLunarDataOperate", "decode year index error");
        return new String[0];
    }

    private int[] d(int i) {
        return new int[]{i & 15, (i >> 4) & 31, (i >> 9) & 15, (i >> 13) & 15, (i >> 17) & 1, (i >> 18) & 1, (i >> 19) & 15, (i >> 23) & 31};
    }

    private int[] e(int i) {
        return new int[]{(i & 255) + 1890, (i >> 8) & 15, (i >> 12) & 15, (i >> 16) & 1, (i >> 17) & 15, (i >> 21) & 1};
    }
}
