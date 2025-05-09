package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class nqn {
    public static void c(List<HwHealthBaseEntry> list, int i, int i2) {
        if (a(list, i, i2)) {
            return;
        }
        int i3 = i / i2;
        LogUtil.c("HealthChart_LinearInterpolation", "multi(", Integer.valueOf(i3), ") = intervalStand(", Integer.valueOf(i), ") / intervalChartShown(", Integer.valueOf(i2), Constants.RIGHT_BRACKET_ONLY);
        ArrayList arrayList = new ArrayList();
        int x = (int) list.get(0).getX();
        float y = list.get(0).getY();
        Iterator<HwHealthBaseEntry> it = list.iterator();
        HwHealthBaseEntry next = it.next();
        arrayList.add(new HwHealthBaseEntry(next.getX(), next.getY()));
        while (it.hasNext()) {
            HwHealthBaseEntry next2 = it.next();
            if (next2 == null) {
                LogUtil.h("HealthChart_LinearInterpolation", "iterator.hasNext,but next is null,return");
                return;
            }
            int x2 = (int) next2.getX();
            float y2 = next2.getY();
            int i4 = x2 - x;
            if (i < i4) {
                arrayList.add(new HwHealthBaseEntry(x2, y2));
            } else if (i > i4) {
                a(arrayList, (y2 - y) / (i4 / i2), x, x2, y);
            } else {
                a(arrayList, (y2 - y) / i3, x, x2, y);
            }
            y = y2;
            x = x2;
        }
        LogUtil.h("HealthChart_LinearInterpolation", "before interpolite--", Integer.valueOf(list.size()), "after interpolate--", Integer.valueOf(arrayList.size()));
        list.clear();
        list.addAll(arrayList);
    }

    private static void a(List<HwHealthBaseEntry> list, float f, int i, int i2, float f2) {
        float f3 = 0.0f;
        for (int i3 = i + 5; i3 <= i2; i3 += 5) {
            f3 += f;
            list.add(new HwHealthBaseEntry(i3, f2 + f3));
        }
    }

    private static boolean a(List<HwHealthBaseEntry> list, int i, int i2) {
        if (i2 != 5) {
            LogUtil.h("HealthChart_LinearInterpolation", "now only support intervalChartShown 5");
            return true;
        }
        if (list != null && list.size() != 0 && list.size() != 1) {
            if (i == i2) {
                LogUtil.a("HealthChart_LinearInterpolation", "intervalStand == intervalChartShown,do not interpolate,return");
                return true;
            }
            int i3 = i % i2;
            LogUtil.c("HealthChart_LinearInterpolation", "intervalStand(", Integer.valueOf(i), ") % intervalChartShown(", Integer.valueOf(i2), "):", Integer.valueOf(i3));
            if (i3 == 0) {
                return false;
            }
            LogUtil.a("HealthChart_LinearInterpolation", "intervalStand % intervalChartShown != 0, can not devided exactly,return");
        }
        return true;
    }
}
