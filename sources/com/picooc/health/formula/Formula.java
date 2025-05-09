package com.picooc.health.formula;

import androidx.core.view.MotionEventCompat;
import com.google.flatbuffers.reflection.BaseType;
import defpackage.txy;
import huawei.android.hwcolorpicker.HwColorPicker;
import java.math.BigDecimal;

/* loaded from: classes7.dex */
public class Formula {
    protected static native float[] calculateBasicDataByImpedanceOldVersion2(int i, float f, int i2, float f2, int i3);

    static {
        System.loadLibrary("dataFormula");
    }

    public static byte[] a(byte[] bArr) {
        byte b = bArr[0];
        if (b == 48) {
            return new byte[]{-15, 3, 48};
        }
        if (b == 53) {
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            return new byte[]{-15, 8, 53, (byte) ((4278190080L & currentTimeMillis) >> 24), (byte) ((16711680 & currentTimeMillis) >> 16), (byte) ((65280 & currentTimeMillis) >> 8), (byte) (currentTimeMillis & 255), 0};
        }
        if (b == 54) {
            return new byte[]{-15, 3, 54};
        }
        if (b == 55) {
            return new byte[]{-15, 3, 55};
        }
        if (b == 57) {
            return new byte[]{-15, 3, 57};
        }
        return new byte[0];
    }

    public static txy a(byte[] bArr, float f, int i, int i2) {
        txy txyVar = new txy();
        byte b = bArr[0];
        if (b != 48 && b != 53) {
            if (b == 54) {
                long j = ((bArr[2] << 24) & (-16777216)) + ((bArr[3] << BaseType.Union) & HwColorPicker.MASK_RESULT_STATE) + ((bArr[4] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) + (bArr[5] & 255);
                float round = Math.round(((((bArr[6] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) + (bArr[7] & 255)) / 20.0f) * 10.0f) / 10.0f;
                float[] calculateBasicDataByImpedanceOldVersion2 = calculateBasicDataByImpedanceOldVersion2(i, f, i2, round, (((bArr[8] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) + (bArr[9] & 255)) / 10);
                txyVar.d(2);
                txyVar.a(round);
                txyVar.c(calculateBasicDataByImpedanceOldVersion2[1]);
                txyVar.e(calculateBasicDataByImpedanceOldVersion2[2]);
                txyVar.d(j * 1000);
            } else if (b == 55) {
                txyVar.d(3);
            } else if (b == 57) {
                long j2 = ((bArr[2] << 24) & (-16777216)) + ((bArr[3] << BaseType.Union) & HwColorPicker.MASK_RESULT_STATE) + ((bArr[4] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) + (bArr[5] & 255);
                float round2 = Math.round(((((bArr[6] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) + (bArr[7] & 255)) / 20.0f) * 10.0f) / 10.0f;
                if (round2 > 0.0f) {
                    float[] calculateBasicDataByImpedanceOldVersion22 = calculateBasicDataByImpedanceOldVersion2(i, f, i2, round2, b((((bArr[8] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) + (bArr[9] & 255)) / 100.0f) * 10);
                    txyVar.d(1);
                    txyVar.a(round2);
                    txyVar.c(calculateBasicDataByImpedanceOldVersion22[1]);
                    txyVar.e(calculateBasicDataByImpedanceOldVersion22[2]);
                    txyVar.d(j2 * 1000);
                }
            }
        }
        return txyVar;
    }

    private static int b(float f) {
        return new BigDecimal(f).setScale(0, 4).intValue();
    }
}
