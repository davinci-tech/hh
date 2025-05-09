package com.huawei.ui.commonui.linechart.barchart;

import android.graphics.DashPathEffect;
import com.github.mikephil.charting.components.Legend;
import com.huawei.ui.commonui.linechart.common.HwEntrys;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthTransformer;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.linechart.icommon.HwHealthBarDataProvider;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthDatasContainer;
import defpackage.koq;
import defpackage.nmz;
import defpackage.noq;
import defpackage.noy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class HwHealthBarDataSet extends HwHealthBaseBarDataSet implements HwEntrys.MarkerViewFormatProvider {
    private DrawColorMode b;
    private noq c;

    public enum DrawColorMode {
        DEFAULT,
        DATA_COLOR
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwEntrys.MarkerViewFormatProvider
    public int acquireMarkViewTextDigitalCount() {
        return 0;
    }

    @Override // com.huawei.ui.commonui.linechart.barchart.HwHealthBaseBarDataSet
    public int acquireRangeCenterValue(int i) {
        return i;
    }

    @Override // com.huawei.ui.commonui.linechart.barchart.HwHealthBaseBarDataSet
    public int acquireValuePresentRangeMax(int i) {
        return i;
    }

    @Override // com.huawei.ui.commonui.linechart.barchart.HwHealthBaseBarDataSet
    public int acquireValuePresentRangeMin(int i) {
        return i;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet, com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public IHwHealthDatasContainer cacheDataContainer(HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
        return null;
    }

    protected boolean d(float f, HwHealthBarEntry hwHealthBarEntry) {
        return true;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet
    public void flushCachedDataContainer() {
    }

    public HwHealthBarDataSet(List<HwHealthBarEntry> list, String str, String str2, String str3) {
        super(list, str2);
        this.b = DrawColorMode.DEFAULT;
        this.c = new noq();
        b(list, str, str3);
    }

    private void b(List<HwHealthBarEntry> list, String str, String str2) {
        List<HwHealthBarEntry> acquireEntryVals = acquireEntryVals();
        for (HwHealthBarEntry hwHealthBarEntry : list) {
            acquireEntryVals.add(new HwHealthBarEntry(hwHealthBarEntry.getX(), hwHealthBarEntry.acquireModel()));
        }
        this.c.c(str);
        this.c.d(str2);
        setColor(-16776961);
        setValueTextSize(9.0f);
        setFormLineWidth(1.0f);
        setFormLineDashEffect(new DashPathEffect(new float[]{10.0f, 5.0f}, 0.0f));
        setFormSize(15.0f);
        setDrawIcons(false);
        setAxisDependency(HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY);
        setHighLightColor(-7829368);
        setForm(Legend.LegendForm.CIRCLE);
        setDrawValues(false);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwEntrys.MarkerViewFormatProvider
    public int acquireColor() {
        return this.c.b();
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwEntrys.MarkerViewFormatProvider
    public String acquireUnit() {
        return this.c.a();
    }

    public void b(DrawColorMode drawColorMode) {
        this.b = drawColorMode;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet
    public DrawColorMode getDrawColorMode() {
        return this.b;
    }

    @Override // com.github.mikephil.charting.data.BaseDataSet, com.github.mikephil.charting.interfaces.datasets.IDataSet
    public int getColor(int i) {
        int b;
        if (DrawColorMode.DATA_COLOR.equals(this.b)) {
            List<T> values = getValues();
            if (values.size() > i && i >= 0 && (b = noy.b(((HwHealthBarEntry) values.get(i)).acquireModel())) != 0) {
                return b;
            }
        }
        return super.getColor(i);
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet
    public int acquireFocusColor() {
        return this.c.e();
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet
    public int acquireFocusColor(HwHealthBarEntry hwHealthBarEntry) {
        int e;
        return (!DrawColorMode.DATA_COLOR.equals(this.b) || hwHealthBarEntry == null || (e = noy.e(hwHealthBarEntry.acquireModel())) == 0) ? this.c.e() : e;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwEntrys.MarkerViewFormatProvider
    public int acquireMarkViewTextColor() {
        noq noqVar = this.c;
        if (noqVar.i()) {
            return noqVar.b();
        }
        return noqVar.c();
    }

    @Override // com.github.mikephil.charting.data.BaseDataSet
    public void setColor(int i) {
        this.c.e(i);
        super.setColor(i);
    }

    public void e(int i) {
        this.c.a(i);
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet
    public List<HwHealthBarEntry> getEntriesForXValue(final float f, HwHealthBarDataProvider hwHealthBarDataProvider) {
        float barDrawWidth;
        List<HwHealthBarEntry> entriesForXValue = getEntriesForXValue(f);
        if (koq.b(entriesForXValue)) {
            return entriesForXValue;
        }
        Collections.sort(entriesForXValue, new Comparator<HwHealthBarEntry>() { // from class: com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet.2
            @Override // java.util.Comparator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public int compare(HwHealthBarEntry hwHealthBarEntry, HwHealthBarEntry hwHealthBarEntry2) {
                if (hwHealthBarEntry == null || hwHealthBarEntry2 == null) {
                    return 0;
                }
                return Float.compare(Math.abs(hwHealthBarEntry.getX() - f), Math.abs(hwHealthBarEntry2.getX() - f));
            }
        });
        if (isSearchByBarWidth()) {
            HwHealthTransformer transformer = hwHealthBarDataProvider.getTransformer(getAxisDependencyExt());
            nmz barData = hwHealthBarDataProvider.getBarData();
            if (barData != null && IHwHealthBarDataSet.BarWidthMode.DEFAULT_WIDTH.equals(getBarDrawWidthMode())) {
                barDrawWidth = barData.d();
            } else {
                barDrawWidth = getBarDrawWidth(transformer);
            }
            Iterator<HwHealthBarEntry> it = entriesForXValue.iterator();
            while (it.hasNext()) {
                HwHealthBarEntry next = it.next();
                if (next == null || Math.abs(next.getX() - f) > barDrawWidth / 2.0f) {
                    it.remove();
                }
            }
        }
        return entriesForXValue;
    }

    @Override // com.github.mikephil.charting.data.DataSet, com.github.mikephil.charting.interfaces.datasets.IDataSet
    public List<HwHealthBarEntry> getEntriesForXValue(float f) {
        List<HwHealthBarEntry> arrayList = new ArrayList<>();
        if (this.mEntries.size() == 0) {
            return arrayList;
        }
        int size = this.mEntries.size() - 1;
        int i = 0;
        while (true) {
            if (i > size) {
                break;
            }
            int i2 = (size + i) / 2;
            HwHealthBarEntry hwHealthBarEntry = (HwHealthBarEntry) this.mEntries.get(i2);
            if (f == hwHealthBarEntry.getX()) {
                while (i2 > 0 && ((HwHealthBarEntry) this.mEntries.get(i2 - 1)).getX() == f) {
                    i2--;
                }
                size = this.mEntries.size();
                while (i2 < size) {
                    HwHealthBarEntry hwHealthBarEntry2 = (HwHealthBarEntry) this.mEntries.get(i2);
                    if (hwHealthBarEntry2.getX() != f) {
                        break;
                    }
                    arrayList.add(hwHealthBarEntry2);
                    i2++;
                }
            } else if (f > hwHealthBarEntry.getX()) {
                i = i2 + 1;
            } else {
                size = i2 - 1;
            }
        }
        if (i > size) {
            arrayList = c(arrayList, size, i);
        }
        if (arrayList.size() == 0) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        for (HwHealthBarEntry hwHealthBarEntry3 : arrayList) {
            if (d(f, hwHealthBarEntry3)) {
                arrayList2.add(hwHealthBarEntry3);
            }
        }
        return arrayList2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0037, code lost:
    
        if (java.lang.Math.abs(r0.getY()) > java.lang.Math.abs(r1.getY())) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry a(int r5, boolean r6) {
        /*
            r4 = this;
            r0 = 0
        L1:
            java.util.List<T extends com.github.mikephil.charting.data.Entry> r1 = r4.mEntries
            boolean r1 = defpackage.koq.d(r1, r5)
            if (r1 == 0) goto L43
            java.util.List<T extends com.github.mikephil.charting.data.Entry> r1 = r4.mEntries
            java.lang.Object r1 = r1.get(r5)
            com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry r1 = (com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry) r1
            if (r1 != 0) goto L14
            return r0
        L14:
            if (r0 != 0) goto L17
            goto L3a
        L17:
            float r2 = r0.getX()
            float r3 = r1.getX()
            int r2 = java.lang.Float.compare(r2, r3)
            if (r2 != 0) goto L43
            float r2 = r0.getY()
            float r2 = java.lang.Math.abs(r2)
            float r3 = r1.getY()
            float r3 = java.lang.Math.abs(r3)
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 <= 0) goto L3a
            goto L3b
        L3a:
            r0 = r1
        L3b:
            if (r6 == 0) goto L40
            int r5 = r5 + 1
            goto L1
        L40:
            int r5 = r5 + (-1)
            goto L1
        L43:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet.a(int, boolean):com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry");
    }

    private List<HwHealthBarEntry> c(List<HwHealthBarEntry> list, int i, int i2) {
        if (i2 < 0 || i2 > this.mEntries.size() - 1 || i < 0 || i > this.mEntries.size() - 1) {
            list.add((HwHealthBarEntry) this.mEntries.get(0));
            list.add((HwHealthBarEntry) this.mEntries.get(this.mEntries.size() - 1));
        } else {
            HwHealthBarEntry a2 = a(i, false);
            HwHealthBarEntry a3 = a(i2, true);
            if (a2 != null) {
                list.add(a2);
            }
            if (a3 != null) {
                list.add(a3);
            }
        }
        return list;
    }

    public noq d() {
        return this.c;
    }
}
