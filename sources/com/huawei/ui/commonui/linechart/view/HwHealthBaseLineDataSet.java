package com.huawei.ui.commonui.linechart.view;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.utils.Utils;
import com.huawei.hwcommonmodel.fitnessdatatype.FitnessSleepType;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthLineRadarDataSet;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthFillFormatter;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.model.HwHealthLineEntry;
import com.huawei.ui.commonui.linechart.model.StorageGenericModel;
import defpackage.nnw;
import defpackage.nod;
import defpackage.non;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public abstract class HwHealthBaseLineDataSet extends HwHealthLineRadarDataSet<HwHealthBaseEntry> implements IHwHealthLineDataSet {
    private static final String TAG = "HwHealthBaseLineDataSet";
    private int mCircleColorHole;
    private List<Integer> mCircleColors;
    private float mCircleHoleRadius;
    private float mCircleRadius;
    private float mCubicIntensity;
    private DashPathEffect mDashPathEffect;
    private IHwHealthFillFormatter mFillFormatter;
    private boolean mIsDrawCircleHole;
    private boolean mIsDrawCircles;
    private Mode mMode;
    private boolean mShowCircleBackground;
    private String mShowDataType;
    private boolean mShowMaxMinValue;
    private boolean mShowStartEndNode;
    private boolean mShowStartEndValue;

    public enum Mode {
        LINEAR,
        STEPPED,
        CUBIC_BEZIER,
        HORIZONTAL_BEZIER
    }

    @Override // com.github.mikephil.charting.data.DataSet
    public DataSet<HwHealthBaseEntry> copy() {
        return null;
    }

    public HwHealthBaseLineDataSet(List<HwHealthBaseEntry> list, String str) {
        super(list, str);
        this.mMode = Mode.LINEAR;
        this.mCircleColors = null;
        this.mCircleColorHole = -1;
        this.mCircleRadius = 5.0f;
        this.mCircleHoleRadius = 3.0f;
        this.mCubicIntensity = 0.2f;
        this.mDashPathEffect = null;
        this.mFillFormatter = new non();
        this.mIsDrawCircles = true;
        this.mIsDrawCircleHole = true;
        this.mShowMaxMinValue = false;
        this.mShowCircleBackground = true;
        this.mShowStartEndNode = false;
        this.mShowStartEndValue = false;
        if (this.mCircleColors == null) {
            this.mCircleColors = new ArrayList();
        }
        this.mCircleColors.clear();
        this.mCircleColors.add(Integer.valueOf(Color.rgb(140, FitnessSleepType.HW_FITNESS_WAKE, 255)));
        setCircleRadius(5.0f);
        setCircleHoleRadius(3.0f);
    }

    @Override // com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet
    public Mode getMode() {
        return this.mMode;
    }

    public void setMode(Mode mode) {
        this.mMode = mode;
    }

    @Override // com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet
    public float getCubicIntensity() {
        return this.mCubicIntensity;
    }

    public void setCircleRadius(float f) {
        if (f >= 1.0f) {
            this.mCircleRadius = Utils.convertDpToPixel(f);
        } else {
            LogUtil.b(TAG, "Circle radius cannot be < 1");
        }
    }

    @Override // com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet
    public float getCircleRadius() {
        return this.mCircleRadius;
    }

    public void setCircleHoleRadius(float f) {
        if (Float.compare(f, 1.0f) < 0) {
            throw new IllegalArgumentException("Circle hole radio must > 1");
        }
        this.mCircleHoleRadius = Utils.convertDpToPixel(f);
    }

    @Override // com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet
    public float getCircleHoleRadius() {
        return this.mCircleHoleRadius;
    }

    @Deprecated
    public void setCircleSize(float f) {
        setCircleRadius(f);
    }

    @Deprecated
    public float getCircleSize() {
        return getCircleRadius();
    }

    @Override // com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet
    public boolean isDashedLineEnabled() {
        return this.mDashPathEffect != null;
    }

    @Override // com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet
    public DashPathEffect getDashPathEffect() {
        return this.mDashPathEffect;
    }

    public void setDrawCircles(boolean z) {
        this.mIsDrawCircles = z;
    }

    @Override // com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet
    public boolean isDrawCirclesEnabled() {
        return this.mIsDrawCircles;
    }

    @Override // com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet
    @Deprecated
    public boolean isDrawCubicEnabled() {
        return this.mMode == Mode.CUBIC_BEZIER;
    }

    @Override // com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet
    @Deprecated
    public boolean isDrawSteppedEnabled() {
        return this.mMode == Mode.STEPPED;
    }

    @Override // com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet
    public int getCircleColorCount() {
        return this.mCircleColors.size();
    }

    public void setCircleColor(int i) {
        resetCircleColors();
        this.mCircleColors.add(Integer.valueOf(i));
    }

    public void setCircleColors(List<Integer> list) {
        resetCircleColors();
        this.mCircleColors.addAll(list);
    }

    public List<Integer> getCircleColors() {
        return this.mCircleColors;
    }

    public void resetCircleColors() {
        if (this.mCircleColors == null) {
            this.mCircleColors = new ArrayList();
        }
        this.mCircleColors.clear();
    }

    @Override // com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet
    public int getCircleHoleColor() {
        return this.mCircleColorHole;
    }

    public void setDrawCircleHole(boolean z) {
        this.mIsDrawCircleHole = z;
    }

    @Override // com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet
    public boolean isDrawCircleHoleEnabled() {
        return this.mIsDrawCircleHole;
    }

    @Override // com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet
    public IHwHealthFillFormatter getFillFormatter() {
        return this.mFillFormatter;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet
    public HwHealthBaseEntry constructEntry(float f, IStorageModel iStorageModel) {
        if ((iStorageModel instanceof StorageGenericModel) && (((StorageGenericModel) iStorageModel).a() instanceof StorageGenericModel.a)) {
            return new HwHealthLineEntry(f, iStorageModel);
        }
        if (nod.c(iStorageModel)) {
            return new HwHealthLineEntry(f, iStorageModel);
        }
        if (!(iStorageModel instanceof nnw)) {
            throw new RuntimeException("not LineChartDataStorageModel,logic error!!!");
        }
        return new HwHealthLineEntry(f, iStorageModel);
    }

    @Override // com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet
    public boolean isShowMaxMinValue() {
        return this.mShowMaxMinValue;
    }

    public void setShowMaxMinValue(boolean z) {
        this.mShowMaxMinValue = z;
    }

    public void setNodeCircleBackground(boolean z) {
        this.mShowCircleBackground = z;
    }

    @Override // com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet
    public boolean getNodeCircleBackground() {
        return this.mShowCircleBackground;
    }

    @Override // com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet
    public boolean isDrawStartEndValue() {
        return this.mShowStartEndValue;
    }

    public void setDrawStartEndValue(boolean z) {
        this.mShowStartEndValue = z;
    }

    public void setDrawStartEndNode(boolean z) {
        this.mShowStartEndNode = z;
    }

    @Override // com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet
    public boolean isDrawStartEndNode() {
        return this.mShowStartEndNode;
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
