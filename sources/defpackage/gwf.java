package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class gwf {
    public static hjd e(hiy hiyVar) {
        return new hjd(hiyVar.h(), hiyVar.f());
    }

    public static hjd c(hjd hjdVar) {
        hjd a2 = a(hjdVar);
        return new hjd(hjdVar.b + a2.b, hjdVar.d + a2.d);
    }

    public static List<hjd> b(List<hjd> list) {
        if (koq.b(list)) {
            return list;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<hjd> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(c(it.next()));
        }
        return arrayList;
    }

    public static hjd d(hjd hjdVar) {
        hjd a2 = a(hjdVar);
        return new hjd(hjdVar.b - a2.b, hjdVar.d - a2.d);
    }

    private static hjd a(hjd hjdVar) {
        double a2 = a(hjdVar.d - 105.0d, hjdVar.b - 35.0d);
        double c = c(hjdVar.d - 105.0d, hjdVar.b - 35.0d);
        double d = (hjdVar.b / 180.0d) * 3.141592653589793d;
        double sin = Math.sin(d);
        double d2 = 1.0d - ((0.006693421622965943d * sin) * sin);
        double sqrt = Math.sqrt(d2);
        return new hjd((a2 * 180.0d) / ((6335552.717000426d / (d2 * sqrt)) * 3.141592653589793d), (c * 180.0d) / (((6378245.0d / sqrt) * Math.cos(d)) * 3.141592653589793d));
    }

    private static double a(double d, double d2) {
        double d3 = d * 2.0d;
        double sqrt = Math.sqrt(Math.abs(d));
        double d4 = d2 * 3.141592653589793d;
        return (d3 - 100.0d) + (d2 * 3.0d) + (d2 * 0.2d * d2) + (0.1d * d * d2) + (sqrt * 0.2d) + ((((Math.sin((6.0d * d) * 3.141592653589793d) * 20.0d) + (Math.sin(d3 * 3.141592653589793d) * 20.0d)) * 2.0d) / 3.0d) + ((((Math.sin(d4) * 20.0d) + (Math.sin((d2 / 3.0d) * 3.141592653589793d) * 40.0d)) * 2.0d) / 3.0d) + ((((Math.sin((d2 / 12.0d) * 3.141592653589793d) * 160.0d) + (Math.sin(d4 / 30.0d) * 320.0d)) * 2.0d) / 3.0d);
    }

    private static double c(double d, double d2) {
        double d3 = d * 0.1d;
        double sqrt = Math.sqrt(Math.abs(d));
        double sin = (((Math.sin((6.0d * d) * 3.141592653589793d) * 20.0d) + (Math.sin((d * 2.0d) * 3.141592653589793d) * 20.0d)) * 2.0d) / 3.0d;
        return d + 300.0d + (d2 * 2.0d) + (d3 * d) + (d3 * d2) + (sqrt * 0.1d) + sin + ((((Math.sin(d * 3.141592653589793d) * 20.0d) + (Math.sin((d / 3.0d) * 3.141592653589793d) * 40.0d)) * 2.0d) / 3.0d) + ((((Math.sin((d / 12.0d) * 3.141592653589793d) * 150.0d) + (Math.sin((d / 30.0d) * 3.141592653589793d) * 300.0d)) * 2.0d) / 3.0d);
    }
}
