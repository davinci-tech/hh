package defpackage;

import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class ecv extends HwHealthLineDataSet {
    private DataInfos e;

    @Override // com.github.mikephil.charting.data.DataSet, com.github.mikephil.charting.interfaces.datasets.IDataSet
    public List<HwHealthBaseEntry> getEntriesForXValue(float f) {
        ArrayList arrayList = new ArrayList(16);
        if (this.mEntries == null || this.mEntries.size() == 0) {
            return arrayList;
        }
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
                arrayList.add((HwHealthBaseEntry) this.mEntries.get(0));
                arrayList.add((HwHealthBaseEntry) this.mEntries.get(this.mEntries.size() - 1));
            } else {
                HwHealthBaseEntry hwHealthBaseEntry3 = (HwHealthBaseEntry) this.mEntries.get(size);
                HwHealthBaseEntry hwHealthBaseEntry4 = (HwHealthBaseEntry) this.mEntries.get(i);
                arrayList.add(hwHealthBaseEntry3);
                arrayList.add(hwHealthBaseEntry4);
            }
        }
        return a(f, arrayList);
    }

    private List<HwHealthBaseEntry> a(float f, List<HwHealthBaseEntry> list) {
        ArrayList arrayList = new ArrayList(16);
        if (koq.c(list)) {
            for (HwHealthBaseEntry hwHealthBaseEntry : list) {
                if (e(f, hwHealthBaseEntry)) {
                    arrayList.add(hwHealthBaseEntry);
                }
            }
        }
        return arrayList;
    }

    private boolean e(float f, HwHealthBaseEntry hwHealthBaseEntry) {
        return f >= ((float) g((int) hwHealthBaseEntry.getX())) && f <= ((float) c((int) hwHealthBaseEntry.getX()));
    }

    private int g(int i) {
        return ecw.a(i, this.e);
    }

    private int c(int i) {
        return ecw.c(i, this.e);
    }

    public int b(int i) {
        return (int) ((c(i) + g(i)) / 2.0f);
    }

    public DataInfos e() {
        return this.e;
    }
}
