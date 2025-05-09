package com.huawei.ui.commonui.linechart.model;

import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.icommon.IStorageModelProvider;
import defpackage.noy;

/* loaded from: classes6.dex */
public class HwHealthLineEntry extends HwHealthBaseEntry implements IStorageModelProvider {
    private IStorageModel mLineChartDataStorageModel;

    public HwHealthLineEntry(float f, IStorageModel iStorageModel) {
        super(f, 0.0f);
        setY(noy.i(iStorageModel));
        this.mLineChartDataStorageModel = iStorageModel;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IStorageModelProvider
    public IStorageModel acquireModel() {
        return this.mLineChartDataStorageModel;
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
