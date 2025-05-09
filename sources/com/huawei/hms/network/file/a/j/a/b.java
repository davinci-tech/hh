package com.huawei.hms.network.file.a.j.a;

import android.os.Build;
import com.huawei.hms.framework.common.ExecutorsUtils;
import com.huawei.hms.framework.common.NetworkUtil;
import com.huawei.hms.network.file.api.RequestManager;
import com.huawei.hms.network.file.core.util.FLogger;
import com.huawei.operation.OpAnalyticsConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes4.dex */
public class b {
    private static final int[] g = {2, 3, 4, 5, 6, 7, 8};

    /* renamed from: a, reason: collision with root package name */
    ArrayList<com.huawei.hms.network.file.a.j.a.a> f5607a;
    private final Object b;
    private volatile boolean c;
    volatile long d;
    int[] e;
    boolean f;

    /* renamed from: com.huawei.hms.network.file.a.j.a.b$b, reason: collision with other inner class name */
    static class C0144b {

        /* renamed from: a, reason: collision with root package name */
        static final b f5608a = new b();
    }

    public static int a(int i) {
        if (i > 0) {
            return 0;
        }
        if (i > -60) {
            return 5;
        }
        if (i > -75) {
            return 4;
        }
        if (i > -90) {
            return 3;
        }
        if (i > -100) {
            return 2;
        }
        return i > -110 ? 1 : 0;
    }

    static boolean b(int i) {
        return 1 == i;
    }

    public int a(long j, long j2) {
        if (!this.c || !this.f) {
            return 0;
        }
        long currentTimeMillis = System.currentTimeMillis();
        int[] a2 = a();
        FLogger.i("DecisionTreeManager", "getCachedNetworkInfos timeused:" + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
        if (a2 == null) {
            FLogger.e("DecisionTreeManager", "predictSliceNum failed, networkInfo is null");
            return 0;
        }
        int i = a2[0];
        int i2 = a2[1];
        int i3 = a2[2];
        FLogger.d("DecisionTreeManager", "predictSliceNum networkType:" + i + ",wifiNum:" + i2 + ",mobileNum:" + i3, new Object[0]);
        return a(i, i2, i3, j, a(Build.MODEL), j2);
    }

    public static b b() {
        return C0144b.f5608a;
    }

    private int[] a() {
        synchronized (this.b) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.d == 0 || currentTimeMillis - this.d > OpAnalyticsConstants.H5_LOADING_DELAY) {
                if (this.e == null) {
                    this.e = new int[3];
                }
                this.e[0] = NetworkUtil.netWork(RequestManager.getAppContext());
                this.e[1] = NetworkUtil.getWifiRssi(RequestManager.getAppContext());
                this.e[2] = NetworkUtil.getMobileRsrp(RequestManager.getAppContext());
                this.d = System.currentTimeMillis();
            }
        }
        return this.e;
    }

    public static double[] a(double[] dArr, boolean z) {
        double[] copyOf = Arrays.copyOf(dArr, dArr.length);
        if (z) {
            if (!a((int) copyOf[0], (int) copyOf[1], (int) copyOf[2])) {
                FLogger.e("DecisionTreeManager", "preProposeData invalidData:" + Arrays.toString(dArr));
                return new double[0];
            }
            if (b((int) copyOf[0])) {
                copyOf[2] = -127.0d;
            } else {
                copyOf[1] = -127.0d;
            }
            copyOf[1] = a((int) copyOf[1]);
            copyOf[2] = a((int) copyOf[2]);
        }
        return copyOf;
    }

    static boolean a(int i, int i2, int i3) {
        String str;
        if (i == 0) {
            str = "invalid network info: unknown  netType";
        } else if (b(i) && -127 == i2) {
            str = "invalid network info: unknown  wifi";
        } else {
            if (b(i) || -127 != i3) {
                return true;
            }
            str = "invalid network info: unknown  mobile";
        }
        FLogger.e("DecisionTreeManager", str);
        return false;
    }

    static boolean a(double d, double d2) {
        return Math.abs(d - d2) <= Math.abs(d2) * 0.20000000298023224d;
    }

    private ArrayList<Double> a(ArrayList<double[]> arrayList, boolean z) {
        ArrayList arrayList2 = new ArrayList();
        ArrayList<Double> arrayList3 = new ArrayList<>();
        if (z) {
            Iterator<double[]> it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.add(Double.valueOf(it.next()[r3.length - 1]));
            }
        }
        ArrayList arrayList4 = new ArrayList();
        Iterator<com.huawei.hms.network.file.a.j.a.a> it2 = this.f5607a.iterator();
        while (it2.hasNext()) {
            com.huawei.hms.network.file.a.j.a.a next = it2.next();
            next.a(arrayList);
            arrayList4.add(next.b);
        }
        int size = arrayList.size();
        int size2 = this.f5607a.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            ArrayList arrayList5 = new ArrayList();
            for (int i3 = 0; i3 < size2; i3++) {
                arrayList5.add((Double) ((ArrayList) arrayList4.get(i3)).get(i2));
            }
            double a2 = a((ArrayList<Double>) arrayList5);
            arrayList3.add(Double.valueOf(a2));
            if (z && a(a2, ((Double) arrayList2.get(i2)).doubleValue())) {
                i++;
            }
        }
        if (z) {
            FLogger.i("DecisionTreeManager", "Accuracy of Forest is : " + ((i * 100) / size) + ",score:" + a((ArrayList<Double>) arrayList2, arrayList3), new Object[0]);
        }
        return arrayList3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Integer a(double[] dArr, int i, long j) {
        int[] iArr;
        int i2;
        try {
            ArrayList<double[]> arrayList = new ArrayList<>(g.length);
            int i3 = 0;
            while (true) {
                iArr = g;
                if (i3 >= iArr.length) {
                    break;
                }
                arrayList.add(new double[]{dArr[0], dArr[1], dArr[2], dArr[3], iArr[i3]});
                i3++;
            }
            FLogger.d("DecisionTreeManager", "predictSliceNum params:" + Arrays.toString(arrayList.get(0)) + ",model:" + i + ", timeout:" + j, new Object[0]);
            ArrayList<Double> a2 = a(arrayList, false);
            Double d = a2.get(0);
            int i4 = iArr[0];
            for (i2 = 1; i2 < a2.size(); i2++) {
                if (a2.get(i2).doubleValue() > d.doubleValue()) {
                    d = a2.get(i2);
                    i4 = g[i2];
                }
            }
            FLogger.d("DecisionTreeManager", "predictResult:" + a2 + ";bestSliceNum = " + i4, new Object[0]);
            return Integer.valueOf(i4);
        } catch (Throwable th) {
            FLogger.e("DecisionTreeManager", "predictException", th);
            return null;
        }
    }

    public static int a(String str) {
        String upperCase = str.toUpperCase(Locale.getDefault());
        int length = upperCase.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = upperCase.charAt(i2);
            if (charAt >= 'A' && charAt <= 'Z') {
                i = (i * 26) + (charAt - '@');
            }
        }
        return i;
    }

    private int a(double d, double d2, double d3, long j, final int i, final long j2) {
        String str;
        int i2 = 0;
        final double[] a2 = a(new double[]{d, d2, d3, j, i, 0.0d}, true);
        if (a2.length <= 0) {
            return 0;
        }
        long currentTimeMillis = System.currentTimeMillis();
        try {
            int intValue = ((Integer) ExecutorsUtils.newSingleThreadExecutor("DecisionTreeManager").submit(new Callable() { // from class: com.huawei.hms.network.file.a.j.a.b$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    Integer a3;
                    a3 = b.this.a(a2, i, j2);
                    return a3;
                }
            }).get(j2, TimeUnit.MILLISECONDS)).intValue();
            try {
                FLogger.d("DecisionTreeManager", "predictSliceNum timeued:" + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
                return intValue;
            } catch (InterruptedException e) {
                e = e;
                i2 = intValue;
                str = "predictException for InterruptedException";
                FLogger.e("DecisionTreeManager", str, e);
                return i2;
            } catch (ExecutionException e2) {
                e = e2;
                i2 = intValue;
                str = "predictException for ExecutionException";
                FLogger.e("DecisionTreeManager", str, e);
                return i2;
            } catch (TimeoutException e3) {
                e = e3;
                i2 = intValue;
                str = "predictException for TimeoutException";
                FLogger.e("DecisionTreeManager", str, e);
                return i2;
            }
        } catch (InterruptedException e4) {
            e = e4;
        } catch (ExecutionException e5) {
            e = e5;
        } catch (TimeoutException e6) {
            e = e6;
        }
    }

    static double a(ArrayList<Double> arrayList, ArrayList<Double> arrayList2) {
        int size = arrayList.size();
        double a2 = a(arrayList);
        double d = 0.0d;
        double d2 = 0.0d;
        for (int i = 0; i < size; i++) {
            d += Math.pow(arrayList.get(i).doubleValue() - arrayList2.get(i).doubleValue(), 2.0d);
            d2 += Math.pow(arrayList.get(i).doubleValue() - a2, 2.0d);
        }
        return 1.0d - (d / d2);
    }

    static double a(ArrayList<Double> arrayList) {
        double d = 0.0d;
        for (int i = 0; i < arrayList.size(); i++) {
            d += arrayList.get(i).doubleValue();
        }
        return d / arrayList.size();
    }

    private b() {
        this.f5607a = new ArrayList<>();
        this.b = new Object();
        this.c = false;
        this.d = 0L;
        this.e = null;
        this.f = true;
    }
}
