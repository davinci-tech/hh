package com.huawei.ui.commonui.linechart.view;

import android.graphics.DashPathEffect;
import com.github.mikephil.charting.interfaces.datasets.ILineRadarDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthFillFormatter;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthLineDatasContainer;
import com.huawei.ui.commonui.linechart.view.HwHealthBaseLineDataSet;
import java.util.List;

/* loaded from: classes6.dex */
public interface IHwHealthLineDataSet extends ILineRadarDataSet<HwHealthBaseEntry>, IHwHealthBarLineDataSet<HwHealthBaseEntry> {
    void checkIfNeedReload();

    int getCircleColorCount();

    int getCircleHoleColor();

    float getCircleHoleRadius();

    float getCircleRadius();

    float getCubicIntensity();

    DashPathEffect getDashPathEffect();

    List<HwHealthBaseEntry> getEntriesForXValue(float f, IHwHealthLineDatasContainer iHwHealthLineDatasContainer);

    IHwHealthFillFormatter getFillFormatter();

    HwHealthBaseLineDataSet.Mode getMode();

    boolean getNodeCircleBackground();

    boolean isDashedLineEnabled();

    boolean isDrawCircleHoleEnabled();

    boolean isDrawCirclesEnabled();

    @Deprecated
    boolean isDrawCubicEnabled();

    boolean isDrawStartEndNode();

    boolean isDrawStartEndValue();

    boolean isDrawSteppedEnabled();

    boolean isShowMaxMinValue();
}
