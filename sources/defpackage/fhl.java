package defpackage;

import com.huawei.health.sportservice.utils.SmoothDataParser;

/* loaded from: classes8.dex */
public class fhl {
    private static SmoothDataParser d = new fhr();
    private static SmoothDataParser e = new fho();

    public static float b(float f, int i) {
        SmoothDataParser smoothDataParser;
        if (i == 0) {
            smoothDataParser = d;
        } else {
            smoothDataParser = e;
        }
        return smoothDataParser.getAverage(f);
    }

    public static void d(int i) {
        if (i == 0) {
            d.clear();
        } else {
            e.clear();
        }
    }
}
