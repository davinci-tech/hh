package com.huawei.ui.commonui.linechart.barchart;

import android.graphics.Color;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.model.StorageGenericModel;
import defpackage.nnc;
import java.util.List;

/* loaded from: classes6.dex */
public abstract class HwHealthBaseBarDataSet extends HwHealthBaseBarLineDataSet<HwHealthBarEntry> implements IHwHealthBarDataSet {
    private static final String TAG = "HwHealthBaseBarDataSet";
    private int mBarBorderColor;
    private float mBarBorderWidth;
    private int mBarShadowColor;
    private float mBarWidth;
    private float mBarWidthDp;
    private IHwHealthBarDataSet.BarWidthMode mBarWidthMode;
    private int mEntryCountStacks;
    private int mHighlightAlpha;
    private boolean mIsSearchByBarWidth;
    private String mShowDataType;
    private String[] mStackLabels;
    private int mStackSize;

    public abstract int acquireRangeCenterValue(int i);

    public abstract int acquireValuePresentRangeMax(int i);

    public abstract int acquireValuePresentRangeMin(int i);

    @Override // com.github.mikephil.charting.data.DataSet
    public DataSet<HwHealthBarEntry> copy() {
        return null;
    }

    public HwHealthBaseBarDataSet(List<HwHealthBarEntry> list, String str) {
        super(list, str);
        this.mStackSize = 1;
        this.mBarShadowColor = Color.rgb(a.N, a.N, a.N);
        this.mBarBorderWidth = 0.0f;
        this.mBarBorderColor = -16777216;
        this.mHighlightAlpha = 120;
        this.mEntryCountStacks = 0;
        this.mStackLabels = new String[]{"Stack"};
        this.mBarWidthMode = IHwHealthBarDataSet.BarWidthMode.DEFAULT_WIDTH;
        this.mIsSearchByBarWidth = false;
        if (list == null) {
            LogUtil.h(TAG, "HwHealthBaseBarDataSet yvalue = null");
        } else {
            this.mHighLightColor = Color.rgb(0, 0, 0);
            calcEntryCountIncludingStacks(list);
        }
    }

    private void calcEntryCountIncludingStacks(List<HwHealthBarEntry> list) {
        this.mEntryCountStacks = 0;
        for (int i = 0; i < list.size(); i++) {
            this.mEntryCountStacks++;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.mikephil.charting.data.DataSet
    public void calcMinMax(HwHealthBarEntry hwHealthBarEntry) {
        if (hwHealthBarEntry == null || Float.isNaN(hwHealthBarEntry.getY())) {
            return;
        }
        if (hwHealthBarEntry.getY() < this.mYMin) {
            this.mYMin = hwHealthBarEntry.getY();
        }
        if (hwHealthBarEntry.getY() > this.mYMax) {
            this.mYMax = hwHealthBarEntry.getY();
        }
        calcMinMaxX(hwHealthBarEntry);
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet
    public int getStackSize() {
        return this.mStackSize;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet
    public boolean isStacked() {
        return this.mStackSize > 1;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet
    public int getBarShadowColor() {
        return this.mBarShadowColor;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet
    public float getBarBorderWidth() {
        return this.mBarBorderWidth;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet
    public int getBarBorderColor() {
        return this.mBarBorderColor;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet
    public int getHighLightAlpha() {
        return this.mHighlightAlpha;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet
    public String[] getStackLabels() {
        return (String[]) this.mStackLabels.clone();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet
    public HwHealthBarEntry constructEntry(float f, IStorageModel iStorageModel) {
        if ((iStorageModel instanceof StorageGenericModel) && (((StorageGenericModel) iStorageModel).a() instanceof StorageGenericModel.e)) {
            return new HwHealthBarEntry(f, iStorageModel);
        }
        if (!(iStorageModel instanceof nnc)) {
            throw new RuntimeException("not BarChartDataStorageModel,logic error!!!");
        }
        return new HwHealthBarEntry(f, (nnc) iStorageModel);
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet
    public void setBarDrawWidth(float f) {
        this.mBarWidthMode = IHwHealthBarDataSet.BarWidthMode.DATA_SET_X_AXIS_WIDTH;
        this.mBarWidth = f;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet
    public void setBarDrawWidthDp(float f) {
        this.mBarWidthMode = IHwHealthBarDataSet.BarWidthMode.DATA_SET_DP_WIDTH;
        this.mBarWidthDp = f;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet
    public IHwHealthBarDataSet.BarWidthMode getBarDrawWidthMode() {
        return this.mBarWidthMode;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet
    public float getBarDrawWidth() {
        return this.mBarWidth;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet
    public float getBarDrawWidth(Transformer transformer) {
        if (transformer == null) {
            return this.mBarWidth;
        }
        if (this.mBarWidthMode == IHwHealthBarDataSet.BarWidthMode.DATA_SET_DP_WIDTH) {
            float[] fArr = {0.0f, 0.0f, Utils.convertDpToPixel(this.mBarWidthDp), 0.0f};
            transformer.pixelsToValue(fArr);
            return fArr[2] - fArr[0];
        }
        return this.mBarWidth;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet
    public boolean isSearchByBarWidth() {
        return this.mIsSearchByBarWidth;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet
    public void setSearchByBarWidth(boolean z) {
        this.mIsSearchByBarWidth = z;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public String getShowType() {
        return this.mShowDataType;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public void setShowType(String str) {
        this.mShowDataType = str;
    }
}
