package defpackage;

import android.os.Bundle;
import com.huawei.hwlogsmodel.LogUtil;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class fft {
    private static int c(int i) {
        if (i == -1) {
            return 0;
        }
        return i;
    }

    public static Bundle awQ_(List<ffs> list) {
        int i;
        int i2;
        int i3;
        int i4;
        float f;
        int i5;
        Iterator<ffs> it;
        if (list == null) {
            return new Bundle();
        }
        Iterator<ffs> it2 = list.iterator();
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        float f2 = 0.0f;
        int i20 = 0;
        float f3 = 0.0f;
        int i21 = 0;
        float f4 = 0.0f;
        int i22 = 0;
        float f5 = 0.0f;
        int i23 = 0;
        float f6 = 0.0f;
        int i24 = 0;
        while (it2.hasNext()) {
            ffs next = it2.next();
            if (next != null) {
                int c = c(next.d());
                i7 += c;
                int c2 = c(next.h());
                i8 += c2;
                int c3 = c(next.c());
                i9 += c3;
                if (next.l() > 0) {
                    i12 += next.l();
                    i11++;
                }
                if (next.o() > 0) {
                    i14 += next.o();
                    i13++;
                }
                int i25 = c + c2 + c3;
                if (next.b() > 0) {
                    i16 += next.b();
                    i15++;
                }
                if (next.e() > 0) {
                    i10 += next.e() * i25;
                }
                if (next.i() > 0) {
                    i17 += next.i() * i25;
                    i18++;
                }
                if (next.a() > 0) {
                    i6 += next.a() * i25;
                    i19++;
                }
                if (next.f() > 0.0f) {
                    it = it2;
                    f2 += next.f() * i25;
                    i20++;
                } else {
                    it = it2;
                }
                if (next.m() > 0.0f) {
                    f3 += next.m() * i25;
                    i21++;
                }
                if (next.n() > 0.0f) {
                    f4 += next.n();
                    i22++;
                }
                if (next.t() > 0.0f) {
                    f5 += next.t();
                    i23++;
                }
                if (next.k() > 0) {
                    f6 += next.k();
                    i24++;
                }
                it2 = it;
            }
        }
        int i26 = i22;
        int i27 = i23;
        int i28 = i24;
        int i29 = i8;
        int i30 = i7 + i8 + i9;
        if (i30 > 0) {
            i = i9;
            i2 = Math.round((i10 * 1.0f) / i30);
        } else {
            i = i9;
            i2 = 0;
        }
        int round = i11 > 0 ? Math.round(i12 / i11) : 0;
        if (i13 > 0) {
            float f7 = (i14 * 0.01f) / i13;
            i3 = i2;
            i4 = round;
            f = new BigDecimal(f7).setScale(1, 1).floatValue();
        } else {
            i3 = i2;
            i4 = round;
            f = 0.0f;
        }
        int i31 = -1;
        int round2 = i15 > 0 ? Math.round((i16 * 1.0f) / i15) : -1;
        int round3 = (i19 <= 0 || i30 <= 0) ? -101 : Math.round((i6 * 1.0f) / i30);
        if (i18 > 0 && i30 > 0) {
            i31 = Math.round((i17 * 1.0f) / i30);
        }
        float f8 = -1.0f;
        float floatValue = (i20 <= 0 || i30 <= 0) ? -1.0f : new BigDecimal(f2 / i30).setScale(1, 1).floatValue();
        float floatValue2 = (i21 <= 0 || i30 <= 0) ? -1.0f : new BigDecimal((f3 * 1.0f) / i30).setScale(1, 1).floatValue();
        float floatValue3 = i26 > 0 ? new BigDecimal(f4 / i26).setScale(1, 1).floatValue() : -1.0f;
        float floatValue4 = i27 > 0 ? new BigDecimal(f5 / i27).setScale(1, 1).floatValue() : -1.0f;
        if (i28 > 0 && i27 > 0) {
            if (f6 / i28 > 0.0f) {
                f8 = new BigDecimal((floatValue4 / r24) * 100.0f).setScale(1, 1).floatValue();
            }
        }
        Bundle bundle = new Bundle();
        int i32 = i4;
        bundle.putInt("averageHangTime", i32);
        bundle.putFloat("groundHangTimeRate", f);
        bundle.putInt("avgGroundContactTime", round2);
        bundle.putInt("avgGroundImpactAcceleration", i3);
        bundle.putInt("avgSwingAngle", i31);
        bundle.putInt("avgEversionExcursion", round3);
        bundle.putInt("foreFootStrikePatternPercentage", i7);
        bundle.putInt("wholeFootStrikePatternPercentage", i29);
        int i33 = i;
        bundle.putInt("hindFootStrikePatternPercentage", i33);
        bundle.putFloat("averageverticalimpactrate", floatValue);
        bundle.putFloat("averageimpactpeak", floatValue2);
        bundle.putFloat("averagegctimebalance", floatValue3);
        bundle.putFloat("averageverticaloscillation", floatValue4);
        if (f8 > 0.0f) {
            i5 = i33;
            LogUtil.a("Track_RunPostureUtil", "calAvgRunningPosture in averageVerticalRatio > 0   = ", Float.valueOf(f8));
            bundle.putFloat("averageverticalration", f8);
        } else {
            i5 = i33;
        }
        LogUtil.a("Track_RunPostureUtil", "calAvgRunningPosture averageVerticalImpactRate = ", Float.valueOf(floatValue), " averageImpactPeak = ", Float.valueOf(floatValue2), " averageGcTimeBalance = ", Float.valueOf(floatValue3), " averageVerticalOscillation = ", Float.valueOf(floatValue4), " averageVerticalRatio = ", Float.valueOf(f8), " averageHangTime = ", Integer.valueOf(i32), " averageHangTimeRate = ", Float.valueOf(f), " avgGroundContactTime = ", Integer.valueOf(round2), " avgSwingAngle = ", Integer.valueOf(i31), " avgEversionExcursion = ", Integer.valueOf(round3), " totalForeFootStrikePattern = ", Integer.valueOf(i7), "totalWholeFootStrikePattern = ", Integer.valueOf(i29), " totalHindFootStrikePattern = ", Integer.valueOf(i5));
        return bundle;
    }
}
