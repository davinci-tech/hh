package com.huawei.ui.commonui.linechart.barchart;

import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.icommon.IStorageModelProvider;
import defpackage.noy;

/* loaded from: classes6.dex */
public class HwHealthBarEntry extends HwHealthBaseEntry implements IStorageModelProvider {
    private IStorageModel mBarChartDataStorageModel;

    public HwHealthBarEntry(float f, IStorageModel iStorageModel) {
        super(f, 0.0f);
        setY(noy.h(iStorageModel));
        this.mBarChartDataStorageModel = iStorageModel;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IStorageModelProvider
    public IStorageModel acquireModel() {
        return this.mBarChartDataStorageModel;
    }

    @Override // com.github.mikephil.charting.data.Entry
    public HwHealthBarEntry copy() {
        return new HwHealthBarEntry(getX(), this.mBarChartDataStorageModel);
    }

    @Override // com.github.mikephil.charting.data.BaseEntry
    public float getY() {
        return super.getY();
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry
    public int hashCode() {
        return super.hashCode();
    }
}
