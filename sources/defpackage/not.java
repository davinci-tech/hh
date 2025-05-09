package defpackage;

import com.github.mikephil.charting.buffer.BarBuffer;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;

/* loaded from: classes6.dex */
public class not extends BarBuffer {
    public not(int i, int i2, boolean z) {
        super(i, i2, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void b(IHwHealthBarDataSet iHwHealthBarDataSet) {
        if (iHwHealthBarDataSet == null) {
            LogUtil.h("HwHealthBarBuffer", "feed data == null");
            return;
        }
        float entryCount = iHwHealthBarDataSet.getEntryCount();
        float f = this.phaseX;
        float f2 = this.mBarWidth / 2.0f;
        for (int i = 0; i < entryCount * f; i++) {
            HwHealthBarEntry hwHealthBarEntry = (HwHealthBarEntry) iHwHealthBarDataSet.getEntryForIndex(i);
            if (hwHealthBarEntry != null) {
                IStorageModel acquireModel = hwHealthBarEntry.acquireModel();
                float c = noy.c(acquireModel);
                float a2 = noy.a(acquireModel);
                if (c > 0.0f) {
                    c *= this.phaseY;
                } else {
                    a2 *= this.phaseY;
                }
                if (this.buffer.length != 0) {
                    float x = hwHealthBarEntry.getX();
                    addBar(x - f2, c, x + f2, a2);
                }
            }
        }
        reset();
    }
}
