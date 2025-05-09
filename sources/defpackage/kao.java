package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes5.dex */
public class kao {
    public static <T> void a(List<T> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.a("ContactSync", 1, "ListUtils", "The parameter 'list' is null or empty");
        } else {
            while (list.remove((Object) null)) {
                list.remove((Object) null);
            }
        }
    }

    public static <T> List<List<T>> b(List<T> list, int i) {
        if (list == null || list.isEmpty()) {
            LogUtil.a("ContactSync", 1, "ListUtils", "The parameter 'sourceList' is null or empty");
            return Collections.emptyList();
        }
        int size = list.size();
        if (size <= i) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(list);
            return arrayList;
        }
        int i2 = (size / i) + (size % i == 0 ? 0 : 1);
        ArrayList arrayList2 = new ArrayList(i2);
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = i3 * i;
            arrayList2.add(list.subList(i4, Math.min(i4 + i, size)));
        }
        return arrayList2;
    }

    @SafeVarargs
    public static <T> List<T> b(T... tArr) {
        return Arrays.asList(tArr);
    }
}
