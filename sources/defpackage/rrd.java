package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.privacy.datasourcemanager.util.DivideConditional;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class rrd {
    public static <T> List<List<T>> d(List<T> list, DivideConditional<T> divideConditional) {
        return c(list, divideConditional);
    }

    private static <T> List<List<T>> c(List<T> list, DivideConditional<T> divideConditional) {
        ArrayList arrayList = new ArrayList(10);
        if (koq.b(list)) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList(10);
        T t = list.get(0);
        arrayList2.add(t);
        int i = 1;
        while (i < list.size()) {
            T t2 = list.get(i);
            if (divideConditional.isDivide(t, t2)) {
                arrayList.add(arrayList2);
                arrayList2 = new ArrayList(10);
            }
            arrayList2.add(t2);
            i++;
            t = t2;
        }
        arrayList.add(arrayList2);
        LogUtil.a("Divider", "divideImp end, result list size=", Integer.valueOf(arrayList.size()));
        return arrayList;
    }
}
