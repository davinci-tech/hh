package com.huawei.ui.commonui.linechart.view;

import android.content.Context;
import android.graphics.DashPathEffect;
import android.graphics.drawable.GradientDrawable;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.HwEntrys;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthDatasContainer;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthLineDatasContainer;
import com.huawei.ui.commonui.linechart.view.HwHealthBaseLineDataSet;
import defpackage.koq;
import defpackage.noq;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes6.dex */
public class HwHealthLineDataSet extends HwHealthBaseLineDataSet implements HwEntrys.MarkerViewFormatProvider {

    /* renamed from: a, reason: collision with root package name */
    private int f8897a;
    private boolean b;
    private GradientDrawable c;
    private IHwHealthLineDatasContainer d;
    private NodeDrawStyle e;
    private boolean f;
    private NodeDrawStyle g;
    private boolean h;
    private int i;
    private LineLinkerFilter j;
    private noq k;
    private int l;
    private GradientDrawable m;

    public interface LineLinkerFilter {
        boolean drawLine(int i, int i2, int i3);
    }

    public interface NodeDrawStyle {
        float calcuNodeWidthPixel(boolean z);

        float initNodeStyle(int i);

        boolean needDrawNodeFill(boolean z);
    }

    public HwHealthLineDataSet(Context context, List<HwHealthBaseEntry> list, String str, String str2, String str3, int i) {
        super(list, str2);
        this.k = new noq();
        this.h = false;
        this.b = false;
        this.m = null;
        this.c = null;
        this.f = false;
        this.i = 0;
        this.l = 0;
        this.f8897a = Integer.MAX_VALUE;
        this.d = null;
        this.j = new LineLinkerFilter() { // from class: com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.3
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.LineLinkerFilter
            public boolean drawLine(int i2, int i3, int i4) {
                return true;
            }
        };
        this.e = new NodeDrawStyle() { // from class: com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.2
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.NodeDrawStyle
            public float initNodeStyle(int i2) {
                return 0.0f;
            }

            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.NodeDrawStyle
            public boolean needDrawNodeFill(boolean z) {
                return z;
            }

            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.NodeDrawStyle
            public float calcuNodeWidthPixel(boolean z) {
                return nsn.c(BaseApplication.getContext(), 2.0f);
            }
        };
        this.g = new NodeDrawStyle() { // from class: com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.1
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.NodeDrawStyle
            public float initNodeStyle(int i2) {
                return 0.0f;
            }

            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.NodeDrawStyle
            public boolean needDrawNodeFill(boolean z) {
                return z;
            }

            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.NodeDrawStyle
            public float calcuNodeWidthPixel(boolean z) {
                return HwHealthLineDataSet.this.getLineWidth();
            }
        };
        this.l = i;
        a(list, str, str3);
    }

    public HwHealthLineDataSet(Context context, List<HwHealthBaseEntry> list, String str, String str2, String str3) {
        super(list, str2);
        this.k = new noq();
        this.h = false;
        this.b = false;
        this.m = null;
        this.c = null;
        this.f = false;
        this.i = 0;
        this.l = 0;
        this.f8897a = Integer.MAX_VALUE;
        this.d = null;
        this.j = new LineLinkerFilter() { // from class: com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.3
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.LineLinkerFilter
            public boolean drawLine(int i2, int i3, int i4) {
                return true;
            }
        };
        this.e = new NodeDrawStyle() { // from class: com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.2
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.NodeDrawStyle
            public float initNodeStyle(int i2) {
                return 0.0f;
            }

            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.NodeDrawStyle
            public boolean needDrawNodeFill(boolean z) {
                return z;
            }

            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.NodeDrawStyle
            public float calcuNodeWidthPixel(boolean z) {
                return nsn.c(BaseApplication.getContext(), 2.0f);
            }
        };
        this.g = new NodeDrawStyle() { // from class: com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.1
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.NodeDrawStyle
            public float initNodeStyle(int i2) {
                return 0.0f;
            }

            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.NodeDrawStyle
            public boolean needDrawNodeFill(boolean z) {
                return z;
            }

            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.NodeDrawStyle
            public float calcuNodeWidthPixel(boolean z) {
                return HwHealthLineDataSet.this.getLineWidth();
            }
        };
        a(list, str, str3);
    }

    public int d() {
        return this.l;
    }

    public noq c() {
        return this.k;
    }

    private void a(List<HwHealthBaseEntry> list, String str, String str2) {
        List<T> acquireEntryVals = acquireEntryVals();
        for (HwHealthBaseEntry hwHealthBaseEntry : list) {
            if (hwHealthBaseEntry != null) {
                acquireEntryVals.add(new HwHealthBaseEntry(hwHealthBaseEntry.getX(), hwHealthBaseEntry.getY(), hwHealthBaseEntry.getData()));
            }
        }
        this.k.c(str);
        this.k.d(str2);
        setColor(-16776961);
        setDrawCircles(false);
        setCircleColor(-16776961);
        setLineWidth(2.0f);
        setDrawCircleHole(false);
        setValueTextSize(9.0f);
        setFormLineWidth(1.0f);
        setFormLineDashEffect(new DashPathEffect(new float[]{10.0f, 5.0f}, 0.0f));
        setFormSize(15.0f);
        setDrawIcons(false);
        setAxisDependency(HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY);
        setMode(HwHealthBaseLineDataSet.Mode.LINEAR);
        setDrawHorizontalHighlightIndicator(false);
        setHighLightColor(-7829368);
        setFillAlpha(255);
        setHighlightLineWidth(1.0f);
        setForm(Legend.LegendForm.CIRCLE);
        setDrawValues(false);
    }

    @Override // com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet
    public List<HwHealthBaseEntry> getEntriesForXValue(float f, IHwHealthLineDatasContainer iHwHealthLineDatasContainer) {
        if (iHwHealthLineDatasContainer == null) {
            return c(f);
        }
        return iHwHealthLineDatasContainer.searchEntryForXValue(f);
    }

    public List<HwHealthBaseEntry> c(float f) {
        ArrayList arrayList = new ArrayList();
        int size = this.mEntries.size() - 1;
        int i = 0;
        while (true) {
            if (i > size) {
                break;
            }
            int i2 = (size + i) / 2;
            HwHealthBaseEntry hwHealthBaseEntry = (HwHealthBaseEntry) this.mEntries.get(i2);
            if (f == hwHealthBaseEntry.getX()) {
                while (i2 > 0 && ((HwHealthBaseEntry) this.mEntries.get(i2 - 1)).getX() == f) {
                    i2--;
                }
                size = this.mEntries.size();
                while (i2 < size) {
                    HwHealthBaseEntry hwHealthBaseEntry2 = (HwHealthBaseEntry) this.mEntries.get(i2);
                    if (hwHealthBaseEntry2.getX() != f) {
                        break;
                    }
                    arrayList.add(hwHealthBaseEntry2);
                    i2++;
                }
            } else if (f > hwHealthBaseEntry.getX()) {
                i = i2 + 1;
            } else {
                size = i2 - 1;
            }
        }
        if (i > size) {
            if (i < 0 || i > this.mEntries.size() - 1 || size < 0 || size > this.mEntries.size() - 1) {
                return null;
            }
            if (this.h) {
                if (((int) ((Entry) this.mEntries.get(i)).getX()) - ((int) ((Entry) this.mEntries.get(size)).getX()) > this.f8897a) {
                    return arrayList;
                }
            }
            e(arrayList, i, size, f);
        }
        return arrayList;
    }

    private void e(List<HwHealthBaseEntry> list, int i, int i2, float f) {
        if (koq.b((Collection<?>) this.mEntries, i2) || koq.b((Collection<?>) this.mEntries, i)) {
            LogUtil.h("HwHealthLineDataSet", "mEntries isOutOfBounds  low or high");
            return;
        }
        HwHealthBaseEntry hwHealthBaseEntry = (HwHealthBaseEntry) this.mEntries.get(i2);
        HwHealthBaseEntry hwHealthBaseEntry2 = (HwHealthBaseEntry) this.mEntries.get(i);
        if (Math.abs(hwHealthBaseEntry.getX() - f) <= Math.abs(hwHealthBaseEntry2.getX() - f)) {
            list.add(hwHealthBaseEntry);
        } else {
            list.add(hwHealthBaseEntry2);
        }
    }

    public void b(int i, int i2, boolean z) {
        this.f = z;
        if (z) {
            this.m = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{i, i});
            this.c = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{i, i2});
            return;
        }
        this.mFillDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{i, i2});
    }

    public boolean g() {
        return this.f;
    }

    public GradientDrawable cCP_() {
        return this.m;
    }

    public GradientDrawable cCO_() {
        return this.c;
    }

    public void j() {
        this.h = true;
    }

    public boolean f() {
        return this.h;
    }

    @Override // com.github.mikephil.charting.data.BaseDataSet
    public void setColor(int i) {
        this.k.e(i);
        super.setColor(i);
    }

    public void d(int i) {
        this.k.c(false);
        this.k.c(i);
    }

    @Override // com.github.mikephil.charting.data.BaseDataSet, com.github.mikephil.charting.interfaces.datasets.IDataSet
    @Deprecated
    public void setAxisDependency(YAxis.AxisDependency axisDependency) {
        LogUtil.b("HwHealthLineDataSet", "HwHealthLineDataSet setAxisDependency Deprecated");
    }

    @Override // com.github.mikephil.charting.data.BaseDataSet, com.github.mikephil.charting.interfaces.datasets.IDataSet
    @Deprecated
    public YAxis.AxisDependency getAxisDependency() {
        LogUtil.b("HwHealthLineDataSet", "HwHealthLineDataSet getAxisDependency Deprecated");
        return this.mAxisDependency;
    }

    public void a(int i) {
        this.i = i;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwEntrys.MarkerViewFormatProvider
    public int acquireMarkViewTextDigitalCount() {
        return this.i;
    }

    public void a(LineLinkerFilter lineLinkerFilter) {
        this.j = lineLinkerFilter;
    }

    public boolean b(int i, int i2) {
        return this.j.drawLine(i, i2, this.f8897a);
    }

    public float a() {
        return this.f8897a;
    }

    public boolean h() {
        return this.f8897a != Integer.MAX_VALUE;
    }

    public void e(int i) {
        LogUtil.c("HwHealthLineDataSet", "modifySamplingInterval:", Integer.valueOf(i));
        if (i != this.f8897a) {
            this.b = true;
        }
        this.f8897a = i;
    }

    @Override // com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet
    public void checkIfNeedReload() {
        if (this.b) {
            flushCachedDataContainer();
            this.b = false;
        }
    }

    public float d(boolean z) {
        return this.g.calcuNodeWidthPixel(z);
    }

    public boolean a(boolean z) {
        return this.g.needDrawNodeFill(z);
    }

    public void d(NodeDrawStyle nodeDrawStyle) {
        this.g = nodeDrawStyle;
    }

    public void b(NodeDrawStyle nodeDrawStyle) {
        this.e = nodeDrawStyle;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwEntrys.MarkerViewFormatProvider
    public int acquireColor() {
        return c().b();
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwEntrys.MarkerViewFormatProvider
    public String acquireUnit() {
        return c().a();
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwEntrys.MarkerViewFormatProvider
    public int acquireMarkViewTextColor() {
        noq c = c();
        if (c.i()) {
            return c.b();
        }
        return c.c();
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet
    public void flushCachedDataContainer() {
        IHwHealthLineDatasContainer iHwHealthLineDatasContainer = this.d;
        if (iHwHealthLineDatasContainer != null) {
            iHwHealthLineDatasContainer.reset();
            this.d.load(this);
            this.mIsNeedLoad = false;
        }
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet, com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public IHwHealthDatasContainer cacheDataContainer(HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
        HwHealthBaseBarLineChart.HwHealthDataContainerGenerator queryLineDataContainerGenerator = hwHealthBaseBarLineChart.queryLineDataContainerGenerator();
        if (queryLineDataContainerGenerator == null) {
            LogUtil.b("HwHealthLineDataSet", "cacheDataContainer not support");
            return null;
        }
        IHwHealthLineDatasContainer iHwHealthLineDatasContainer = this.d;
        if (iHwHealthLineDatasContainer == null || !queryLineDataContainerGenerator.typeOf(iHwHealthLineDatasContainer.getClass())) {
            IHwHealthLineDatasContainer iHwHealthLineDatasContainer2 = (IHwHealthLineDatasContainer) queryLineDataContainerGenerator.newDataContainer();
            this.d = iHwHealthLineDatasContainer2;
            iHwHealthLineDatasContainer2.load(this);
            this.mIsNeedLoad = false;
        }
        if (this.mIsNeedLoad) {
            this.d.load(this);
            this.mIsNeedLoad = false;
        }
        return this.d;
    }
}
