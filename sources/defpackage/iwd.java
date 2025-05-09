package defpackage;

import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.type.HiSubscribeType;
import health.compact.a.HiCommonUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class iwd {
    public static List<Integer> a(List<HiHealthData> list) {
        if (HiCommonUtil.d(list)) {
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            int a2 = HiSubscribeType.a(it.next().getType());
            if (HiSubscribeType.b(a2) && !arrayList.contains(Integer.valueOf(a2))) {
                arrayList.add(Integer.valueOf(a2));
            }
        }
        return arrayList;
    }

    public static List<Integer> b(int[] iArr) {
        ArrayList arrayList = new ArrayList(10);
        for (int i : iArr) {
            int a2 = HiSubscribeType.a(i);
            if (HiSubscribeType.b(a2) && !arrayList.contains(Integer.valueOf(a2))) {
                arrayList.add(Integer.valueOf(a2));
            }
        }
        return arrayList;
    }
}
