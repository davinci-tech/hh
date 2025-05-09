package com.huawei.ui.main.stories.fitness.util.chart;

import com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import defpackage.pxx;
import defpackage.pya;

/* loaded from: classes6.dex */
public class MultiDataBarEntry extends HwHealthBarEntry {
    private pya mDataLayer;

    public MultiDataBarEntry(float f, IStorageModel iStorageModel) {
        super(f, iStorageModel);
    }

    public MultiDataBarEntry(float f, IStorageModel iStorageModel, pya pyaVar) {
        super(f, iStorageModel);
        this.mDataLayer = pyaVar;
    }

    @Override // com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry, com.github.mikephil.charting.data.BaseEntry
    public float getY() {
        if (this.mDataLayer != null) {
            IStorageModel acquireModel = acquireModel();
            if (acquireModel instanceof pxx) {
                return ((pxx) acquireModel).getCeil(this.mDataLayer.d());
            }
        }
        return super.getY();
    }

    @Override // com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry, com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry, com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry
    public int hashCode() {
        return super.hashCode();
    }
}
