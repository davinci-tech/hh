package com.huawei.healthcloud.plugintrack.trackanimation.dynamiccurve.auxiliary;

import android.graphics.Color;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.share.HiHealthError;

/* loaded from: classes4.dex */
public abstract class CurveColorControl {
    private static final String TAG = "Track_CurveColorControl";
    protected int mRunCircleColor;
    protected int[] mRunCurveColor;
    protected int[] mRunFillingColor;
    protected int[] mRunLineColor;
    private static final int INIT_CURVE_COLOR = Color.argb(153, HiHealthError.ERR_WRONG_DEVICE, HiHealthError.ERR_WRONG_DEVICE, HiHealthError.ERR_WRONG_DEVICE);
    private static final int RING_COLOR = Color.argb(255, 255, 255, 255);
    private static final int[] INIT_FILLING_COLOR = {Color.argb(30, 238, 238, 238), Color.argb(0, 238, 238, 238)};
    protected int mInitCurveColor = INIT_CURVE_COLOR;
    protected int mRunRingColor = RING_COLOR;
    protected int[] mInitFillingColor = INIT_FILLING_COLOR;

    public CurveColorControl() {
        LogUtil.a(TAG, "init curve control");
    }

    public int getInitCurveColor() {
        return this.mInitCurveColor;
    }

    public int[] getInitFillingColor() {
        return arrayCopy(this.mInitFillingColor);
    }

    public int[] getRunCurveColor() {
        return arrayCopy(this.mRunCurveColor);
    }

    public int[] getRunFillingColor() {
        return arrayCopy(this.mRunFillingColor);
    }

    public int[] getRunLineColor() {
        return arrayCopy(this.mRunLineColor);
    }

    public int getRunCircleColor() {
        return this.mRunCircleColor;
    }

    public int getRunRingColor() {
        return this.mRunRingColor;
    }

    private int[] arrayCopy(int[] iArr) {
        if (iArr == null) {
            LogUtil.b(TAG, "color is null");
            return new int[]{0, 0};
        }
        int[] iArr2 = new int[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr2[i] = iArr[i];
        }
        return iArr2;
    }
}
