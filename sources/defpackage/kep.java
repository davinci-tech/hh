package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwstressmgr.PressureCalibrate;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes5.dex */
public class kep {
    private boolean b;

    static class c {

        /* renamed from: a, reason: collision with root package name */
        static final kep f14321a = new kep();
    }

    private kep() {
        this.b = false;
    }

    public static kep d() {
        return c.f14321a;
    }

    public float[] a(int i, float[] fArr, List<Integer> list, List<Integer> list2) {
        float[] libPressureCalibrate;
        synchronized (this) {
            PressureCalibrate c2 = PressureCalibrate.c();
            if (koq.c(list)) {
                ReleaseLogUtil.e("PressureDataProvider", "rriValueList size ", Integer.valueOf(list.size()));
            }
            if (koq.c(list2)) {
                ReleaseLogUtil.e("PressureDataProvider", "sqiValueList size ", Integer.valueOf(list2.size()));
            }
            a(true);
            int[] a2 = a(list);
            int length = a2.length;
            LogUtil.a("PressureDataProvider", "cycleRriDataLength:", Integer.valueOf(length));
            int[] a3 = a(list2);
            LogUtil.a("PressureDataProvider", "signalTime: ", Integer.valueOf(i), " rriData: ", Arrays.toString(fArr), " cycleRriData: ", Arrays.toString(a2), " cycleSqiData: ", Arrays.toString(a3));
            libPressureCalibrate = c2.libPressureCalibrate(fArr, i, length, a2, a3);
            if (libPressureCalibrate == null) {
                LogUtil.a("PressureDataProvider", "cycleBack is null");
                libPressureCalibrate = new float[19];
            }
        }
        return libPressureCalibrate;
    }

    private int[] a(List<Integer> list) {
        if (list == null) {
            return new int[0];
        }
        int[] iArr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            iArr[i] = list.get(i).intValue();
        }
        return iArr;
    }

    public boolean b(float[] fArr) {
        if (fArr == null) {
            return false;
        }
        boolean z = false;
        int i = 0;
        for (int i2 = 0; i2 < fArr.length; i2++) {
            if (fArr[i2] == 0.0f) {
                i++;
            }
            if (i2 == fArr.length - 1 && i == fArr.length) {
                z = true;
            }
        }
        return z;
    }

    private void a(boolean z) {
        this.b = z;
    }

    public boolean b() {
        return this.b;
    }
}
