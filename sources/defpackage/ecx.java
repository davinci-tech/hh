package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.ui.commonui.linechart.icommon.HwHealthLineDataProvider;
import com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet;

/* loaded from: classes3.dex */
public class ecx extends npa {
    @Override // defpackage.nox
    protected boolean c() {
        return false;
    }

    public ecx(HwHealthLineDataProvider hwHealthLineDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler, Context context) {
        super(hwHealthLineDataProvider, chartAnimator, viewPortHandler, context);
    }

    @Override // defpackage.nox
    protected void cCN_(IHwHealthLineDataSet iHwHealthLineDataSet, int i, Transformer transformer, Canvas canvas) {
        super.cCN_(iHwHealthLineDataSet, i, transformer, canvas);
        int entryCount = iHwHealthLineDataSet.getEntryCount();
        if (!iHwHealthLineDataSet.isDrawFilledEnabled() || entryCount <= 0) {
            return;
        }
        cCL_(this.mBitmapCanvas, iHwHealthLineDataSet, transformer, this.mXBounds, this.mChart.getAxisDataRenderArg(iHwHealthLineDataSet.getAxisDependencyExt()));
    }
}
