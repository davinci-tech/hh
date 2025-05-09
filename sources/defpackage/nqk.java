package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.icommon.DecresPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class nqk implements DecresPolicy {
    @Override // com.huawei.ui.commonui.linechart.icommon.DecresPolicy
    public int decresPoint(List<HwHealthBaseEntry> list, int i) {
        if (list == null || list.size() < i || i == 0) {
            LogUtil.h("CommonUI_NormalDecresPolicy", "decresPoint, values or showCounts is null or values.size() < showCounts");
            return 5;
        }
        int i2 = 0;
        HwHealthBaseEntry hwHealthBaseEntry = new HwHealthBaseEntry(0.0f, 0.0f, list.get(0).getData());
        HwHealthBaseEntry hwHealthBaseEntry2 = new HwHealthBaseEntry(0.0f, 0.0f, list.get(0).getData());
        float f = -3.4028235E38f;
        float f2 = Float.MAX_VALUE;
        int i3 = 0;
        int i4 = 0;
        for (HwHealthBaseEntry hwHealthBaseEntry3 : list) {
            if (hwHealthBaseEntry3 != null) {
                if (hwHealthBaseEntry3.getY() > f) {
                    f = hwHealthBaseEntry3.getY();
                    i3 = i2;
                    hwHealthBaseEntry = hwHealthBaseEntry3;
                }
                if (hwHealthBaseEntry3.getY() < f2) {
                    i4 = i2;
                    f2 = hwHealthBaseEntry3.getY();
                    hwHealthBaseEntry2 = hwHealthBaseEntry3;
                }
                i2++;
            }
        }
        HwHealthBaseEntry hwHealthBaseEntry4 = new HwHealthBaseEntry(hwHealthBaseEntry.getX(), hwHealthBaseEntry.getY(), hwHealthBaseEntry.getData());
        HwHealthBaseEntry hwHealthBaseEntry5 = new HwHealthBaseEntry(hwHealthBaseEntry2.getX(), hwHealthBaseEntry2.getY(), hwHealthBaseEntry2.getData());
        int size = list.size() / i;
        LogUtil.c("CommonUI_NormalDecresPolicy", "【decresPoint】 ：interval(", Integer.valueOf(size), ") = values.size():", Integer.valueOf(list.size()), " /showCounts:", Integer.valueOf(i), ";");
        if (size == 0) {
            LogUtil.h("CommonUI_NormalDecresPolicy", "decresPoint, interval is 0");
            return 5;
        }
        int i5 = i3 / size;
        LogUtil.c("CommonUI_NormalDecresPolicy", "【decresPoint】 ：maxResultIndex(", Integer.valueOf(i5), ") = maxIndex(", Integer.valueOf(i3), ") / interval(", Integer.valueOf(size), Constants.RIGHT_BRACKET_ONLY);
        ArrayList<HwHealthBaseEntry> arrayList = new ArrayList<>();
        d(arrayList, i3, i5, hwHealthBaseEntry4, i4, i4 / size, hwHealthBaseEntry5, d(list, arrayList, size, 5));
        list.clear();
        list.addAll(arrayList);
        return size * 5;
    }

    private void d(ArrayList<HwHealthBaseEntry> arrayList, int i, int i2, HwHealthBaseEntry hwHealthBaseEntry, int i3, int i4, HwHealthBaseEntry hwHealthBaseEntry2, List<Integer> list) {
        int i5;
        int i6 = i2;
        int i7 = i4;
        Iterator<Integer> it = list.iterator();
        int i8 = 0;
        int i9 = 0;
        boolean z = true;
        boolean z2 = true;
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if (intValue < i6) {
                i8--;
            } else if (intValue == i6) {
                z2 = false;
            }
            if (intValue < i7) {
                i9--;
            } else if (intValue == i7) {
                z = false;
            }
        }
        if (i6 == i7) {
            int size = arrayList.size();
            if (size <= 1) {
                LogUtil.a("CommonUI_NormalDecresPolicy", "focusMaxMinData maxResultIndex == minResultIndex,valuesResult size <= 1,logic error");
                return;
            }
            if (i6 != 0) {
                if (i6 == size - 1) {
                    if (i >= i3) {
                        i7--;
                    }
                } else if (i >= i3) {
                    i5 = i6 + 1;
                    i6 = i5;
                }
                i5 = i6 - 1;
                i6 = i5;
            } else if (i >= i3) {
                i6 = 1;
            } else {
                i7 = 1;
            }
        }
        a(z, i7 + i9, hwHealthBaseEntry2, arrayList, z2, i6 + i8, hwHealthBaseEntry);
    }

    private List<Integer> d(List<HwHealthBaseEntry> list, List<HwHealthBaseEntry> list2, int i, int i2) {
        int i3;
        ArrayList arrayList = new ArrayList();
        if (i == 0) {
            LogUtil.h("CommonUI_NormalDecresPolicy", "samplingResult failed, samplingInterval is 0");
            return arrayList;
        }
        int size = list.size();
        LogUtil.c("CommonUI_NormalDecresPolicy", "【decresPoint】 ：for(i=0;i<size/samplingInterval;i++): size/samplingInterval:", Integer.valueOf(size), "/", Integer.valueOf(i));
        int i4 = 0;
        while (i4 < size / i) {
            int i5 = i4 * i;
            int i6 = i5;
            while (true) {
                i3 = i4 + 1;
                if (i6 >= i3 * i) {
                    c(list, list2, i5);
                    break;
                }
                int i7 = i6 - 1;
                if (i7 >= 0 && i6 < size) {
                    HwHealthBaseEntry hwHealthBaseEntry = list.get(i7);
                    HwHealthBaseEntry hwHealthBaseEntry2 = list.get(i6);
                    if (hwHealthBaseEntry != null && hwHealthBaseEntry2 != null) {
                        int x = ((int) hwHealthBaseEntry2.getX()) - ((int) hwHealthBaseEntry.getX());
                        if ((i5 - 1 != i7 || x < i * i2) && x != i2) {
                            arrayList.add(Integer.valueOf(i4));
                            break;
                        }
                    }
                }
                i6++;
            }
            i4 = i3;
        }
        if (list.size() % i != 0) {
            c(list, list2, i4 * i);
        }
        return arrayList;
    }

    private void c(List<HwHealthBaseEntry> list, List<HwHealthBaseEntry> list2, int i) {
        if (koq.b(list, i)) {
            LogUtil.a("CommonUI_NormalDecresPolicy", "valuesOriginal isOutOfBounds  index");
            return;
        }
        HwHealthBaseEntry hwHealthBaseEntry = list.get(i);
        HwHealthBaseEntry hwHealthBaseEntry2 = new HwHealthBaseEntry(hwHealthBaseEntry.getX(), hwHealthBaseEntry.getY());
        hwHealthBaseEntry2.setData(hwHealthBaseEntry.getData());
        list2.add(hwHealthBaseEntry2);
    }

    private void a(boolean z, int i, HwHealthBaseEntry hwHealthBaseEntry, ArrayList<HwHealthBaseEntry> arrayList, boolean z2, int i2, HwHealthBaseEntry hwHealthBaseEntry2) {
        if (z && i >= 0 && i <= arrayList.size() - 1) {
            hwHealthBaseEntry.setX(arrayList.get(i).getX());
            arrayList.set(i, hwHealthBaseEntry);
        }
        if (!z2 || i2 < 0 || i2 > arrayList.size() - 1) {
            return;
        }
        hwHealthBaseEntry2.setX(arrayList.get(i2).getX());
        arrayList.set(i2, hwHealthBaseEntry2);
    }
}
