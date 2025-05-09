package defpackage;

import com.huawei.hihealth.HiHealthData;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class piw {

    /* renamed from: a, reason: collision with root package name */
    private static final piu f16150a = new piv();
    private static final piu c = new piy();

    public static void b(List<Integer> list) {
        ArrayList arrayList = new ArrayList(4);
        arrayList.addAll(list);
        f16150a.a(arrayList);
        ArrayList arrayList2 = new ArrayList(4);
        arrayList2.addAll(list);
        c.a(arrayList2);
    }

    public static HiHealthData a(HiHealthData hiHealthData, HiHealthData hiHealthData2, List<HiHealthData> list, boolean z, boolean z2) {
        piu piuVar;
        if (z) {
            piuVar = f16150a;
        } else {
            piuVar = c;
        }
        return piuVar.d(hiHealthData, hiHealthData2, list, z2);
    }
}
