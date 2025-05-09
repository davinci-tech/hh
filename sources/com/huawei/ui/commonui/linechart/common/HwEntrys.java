package com.huawei.ui.commonui.linechart.common;

import com.github.mikephil.charting.data.Entry;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class HwEntrys extends HwHealthBaseEntry {
    List<HwDataEntry> mEntries;

    public interface MarkerViewFormatProvider {
        int acquireColor();

        int acquireMarkViewTextColor();

        int acquireMarkViewTextDigitalCount();

        String acquireUnit();
    }

    public HwEntrys() {
        super(0.0f, 0.0f);
        this.mEntries = new ArrayList();
    }

    public void add(HwDataEntry hwDataEntry) {
        this.mEntries.add(hwDataEntry);
    }

    public List<HwDataEntry> getEntries() {
        return this.mEntries;
    }

    public static class HwDataEntry extends Entry {
        private MarkerViewFormatProvider mDataSet;
        private HwHealthBaseEntry mEntry;

        public HwDataEntry(HwHealthBaseEntry hwHealthBaseEntry, MarkerViewFormatProvider markerViewFormatProvider) {
            this.mEntry = hwHealthBaseEntry;
            this.mDataSet = markerViewFormatProvider;
        }

        public HwHealthBaseEntry getHwDataEntry() {
            return this.mEntry;
        }

        public MarkerViewFormatProvider getDataSet() {
            return this.mDataSet;
        }
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
