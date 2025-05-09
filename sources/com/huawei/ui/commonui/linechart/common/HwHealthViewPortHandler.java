package com.huawei.ui.commonui.linechart.common;

import android.content.Context;
import android.graphics.Matrix;
import android.view.View;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import defpackage.nng;

/* loaded from: classes6.dex */
public class HwHealthViewPortHandler extends ViewPortHandler {
    private Context e;

    public HwHealthViewPortHandler(Context context) {
        this.e = context == null ? BaseApplication.getContext() : context;
    }

    @Override // com.github.mikephil.charting.utils.ViewPortHandler
    public Matrix refresh(Matrix matrix, View view, boolean z) {
        boolean z2 = (view instanceof HwHealthBaseScrollBarLineChart) && ((HwHealthBaseScrollBarLineChart) view).acquireChartShowMode() == HwHealthBaseScrollBarLineChart.ChartShowMode.SCROLL_MODE;
        this.mMatrixTouch.set(matrix);
        if (!z2) {
            limitTransAndScale(this.mMatrixTouch, this.mContentRect);
        }
        if (z && (view instanceof HwHealthBaseBarLineChart)) {
            ((HwHealthBaseBarLineChart) view).invalidateForce();
        }
        if (matrix == null) {
            LogUtil.b("HwHealthViewPortHandler", "refresh newMatrix == null");
            return null;
        }
        matrix.set(this.mMatrixTouch);
        return matrix;
    }

    public void cBC_(Matrix matrix) {
        this.mMatrixTouch.set(matrix);
    }

    public boolean c() {
        float[] fArr = new float[9];
        this.mMatrixTouch.getValues(fArr);
        return !nng.d(this.e) ? fArr[2] < 0.0f : fArr[2] > 0.0f;
    }
}
