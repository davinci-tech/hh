package com.huawei.hms.network.file.a.j.a;

import com.huawei.hms.network.file.core.util.FLogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* loaded from: classes4.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private C0143a f5605a = null;
    public ArrayList<Double> b;

    /* renamed from: com.huawei.hms.network.file.a.j.a.a$a, reason: collision with other inner class name */
    static class C0143a {

        /* renamed from: a, reason: collision with root package name */
        String f5606a;
        public boolean b;
        public C0143a c;
        public C0143a d;
        public int e;
        public double f;
        public double g;
    }

    public ArrayList<Double> a(ArrayList<double[]> arrayList) {
        ArrayList<Double> arrayList2 = new ArrayList<>();
        Iterator<double[]> it = arrayList.iterator();
        while (it.hasNext()) {
            double[] next = it.next();
            double a2 = a(next);
            if (a2 == -1.0d || a2 == -100.0d) {
                FLogger.e("DecisionTree", "predict error,  error for:" + Arrays.toString(next) + ",name:" + this.f5605a.f5606a);
            }
            arrayList2.add(Double.valueOf(a2));
        }
        this.b = arrayList2;
        return arrayList2;
    }

    private double a(double[] dArr) {
        C0143a c0143a = this.f5605a;
        C0143a c0143a2 = c0143a;
        while (c0143a2 != null) {
            if (c0143a2.b) {
                return c0143a2.f;
            }
            C0143a c0143a3 = c0143a2.c;
            c0143a2 = (c0143a3 == null || dArr[c0143a2.e] >= c0143a2.g) ? c0143a2.d : c0143a3;
        }
        FLogger.e("DecisionTree", "predict error, parent:" + c0143a.f5606a);
        return -1.0d;
    }
}
