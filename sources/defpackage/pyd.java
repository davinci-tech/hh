package defpackage;

import android.content.Context;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class pyd extends HwHealthLineDataSet {
    private DataInfos b;

    public pyd(Context context, List<HwHealthBaseEntry> list, String str, String str2, String str3, DataInfos dataInfos) {
        super(context, list, str, str2, str3);
        this.b = dataInfos;
    }

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
        return c(f, arrayList);
    }

    private List<HwHealthBaseEntry> c(float f, List<HwHealthBaseEntry> list) {
        ArrayList arrayList = new ArrayList(16);
        if (koq.c(list)) {
            for (HwHealthBaseEntry hwHealthBaseEntry : list) {
                if (d(f, hwHealthBaseEntry)) {
                    arrayList.add(hwHealthBaseEntry);
                }
            }
        }
        return arrayList;
    }

    private boolean d(float f, HwHealthBaseEntry hwHealthBaseEntry) {
        return f >= ((float) g((int) hwHealthBaseEntry.getX())) && f <= ((float) b((int) hwHealthBaseEntry.getX()));
    }

    private int g(int i) {
        return pyc.b(i, this.b);
    }

    private int b(int i) {
        return pyc.e(i, this.b);
    }

    public int c(int i) {
        return (int) ((b(i) + g(i)) / 2.0f);
    }

    public DataInfos e() {
        return this.b;
    }
}
