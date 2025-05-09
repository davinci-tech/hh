package defpackage;

import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes6.dex */
public class mhp {
    private static final List<Integer> h = Collections.unmodifiableList(Arrays.asList(14, 200, Integer.valueOf(a.D), Integer.valueOf(a.L)));
    private static final List<Integer> f = Collections.unmodifiableList(Collections.singletonList(11));
    private static final List<Integer> e = Collections.unmodifiableList(Collections.singletonList(13));

    /* renamed from: a, reason: collision with root package name */
    private static final List<Integer> f15006a = Collections.unmodifiableList(Arrays.asList(Integer.valueOf(a.K), Integer.valueOf(a.M)));
    private static final List<Integer> j = Collections.unmodifiableList(Collections.singletonList(12));
    private static final List<Integer> i = Collections.unmodifiableList(Arrays.asList(111, 112, 113, 114, 115));
    private static final List<Integer> d = Collections.unmodifiableList(Arrays.asList(20, 21));
    private static final List<Integer> g = Collections.unmodifiableList(Arrays.asList(18, 19, 218));
    private static final List<Integer> b = Collections.unmodifiableList(Arrays.asList(23, 24, 25));
    private static final List<Integer> c = Collections.unmodifiableList(Collections.singletonList(17));

    private static int b(int i2) {
        if (i2 == 3) {
            return 4;
        }
        return i2 == 4 ? 1 : 0;
    }

    private static int c(int i2) {
        if (i2 == 3) {
            return 4;
        }
        return i2 == 4 ? 1 : 6;
    }

    public static <T> List<kvm> d(T t) {
        if (!(t instanceof List)) {
            return new ArrayList(10);
        }
        return (List) t;
    }

    public static List<kvm> c(List<kvm> list, int i2) {
        ArrayList arrayList = new ArrayList(10);
        if (koq.b(list)) {
            LogUtil.h("PLGACHIEVE_OperationActivityRule", "getCurrentYearActivityList allActivitySimpleList is empty.");
            return arrayList;
        }
        for (kvm kvmVar : list) {
            if (kvmVar != null && mht.b(i2, kvmVar.c(), kvmVar.a()) && !arrayList.contains(kvmVar)) {
                arrayList.add(kvmVar);
            }
        }
        return arrayList;
    }

    public static int e(int i2) {
        if (h.contains(Integer.valueOf(i2))) {
            return 1;
        }
        if (i.contains(Integer.valueOf(i2))) {
            return 5;
        }
        if (f.contains(Integer.valueOf(i2))) {
            return 2;
        }
        if (e.contains(Integer.valueOf(i2))) {
            return 3;
        }
        return f15006a.contains(Integer.valueOf(i2)) ? 4 : 0;
    }

    public static int d(List<kvm> list) {
        int i2 = 0;
        if (koq.b(list)) {
            LogUtil.h("PLGACHIEVE_OperationActivityRule", "generateActivityReport currentYearActivityList is empty");
            return 0;
        }
        for (kvm kvmVar : list) {
            if (kvmVar == null) {
                LogUtil.h("PLGACHIEVE_OperationActivityRule", "generateActivityReport activitySimple is null");
            } else if (kvmVar.b() >= 1) {
                i2++;
            }
        }
        return i2;
    }

    public static int a(List<kvm> list) {
        int e2;
        if (koq.b(list) || list.size() < 5) {
            LogUtil.h("PLGACHIEVE_OperationActivityRule", "generateActivityReport currentYearActivityList is empty");
            return 0;
        }
        int[] iArr = new int[6];
        for (kvm kvmVar : list) {
            if (kvmVar == null) {
                LogUtil.h("PLGACHIEVE_OperationActivityRule", "generateActivityReport activitySimple is null");
            } else {
                int d2 = kvmVar.d();
                if (d2 == 12) {
                    e2 = b(kvmVar.e());
                } else {
                    e2 = e(d2);
                }
                iArr[e2] = iArr[e2] + 1;
            }
        }
        return d(iArr);
    }

    private static int d(int[] iArr) {
        if (iArr == null || iArr.length < 6) {
            LogUtil.h("PLGACHIEVE_OperationActivityRule", "calculateFavoriteReportActivityType activityTypeNumbers is null or length invalid.");
            return 0;
        }
        int i2 = iArr[1];
        int asInt = Arrays.stream(iArr).max().getAsInt();
        if (asInt == iArr[2]) {
            return 2;
        }
        if (asInt == iArr[5]) {
            return 5;
        }
        if (asInt == iArr[1]) {
            return 1;
        }
        if (asInt == iArr[3]) {
            return 3;
        }
        return asInt == iArr[4] ? 4 : 0;
    }

    public static int e(List<kvm> list) {
        int d2;
        if (koq.b(list) || list.size() < 5) {
            LogUtil.h("PLGACHIEVE_OperationActivityRule", "generateActivityReport currentYearActivityList is empty");
            return 0;
        }
        int[] iArr = new int[11];
        for (kvm kvmVar : list) {
            if (kvmVar == null) {
                LogUtil.h("PLGACHIEVE_OperationActivityRule", "generateActivityReport activitySimple is null");
            } else {
                int d3 = kvmVar.d();
                LogUtil.a("PLGACHIEVE_OperationActivityRule", "getNewFavoriteActivityType activityType ", Integer.valueOf(d3));
                if (d3 == 12) {
                    d2 = c(kvmVar.e());
                } else {
                    d2 = d(d3);
                }
                iArr[d2] = iArr[d2] + 1;
            }
        }
        LogUtil.a("PLGACHIEVE_OperationActivityRule", "getNewFavoriteActivityType activityTypeNumbers ", Arrays.toString(iArr));
        return b(iArr);
    }

    public static int d(int i2) {
        if (h.contains(Integer.valueOf(i2))) {
            return 1;
        }
        if (f.contains(Integer.valueOf(i2))) {
            return 2;
        }
        if (e.contains(Integer.valueOf(i2))) {
            return 3;
        }
        if (f15006a.contains(Integer.valueOf(i2))) {
            return 4;
        }
        if (i.contains(Integer.valueOf(i2))) {
            return 5;
        }
        if (j.contains(Integer.valueOf(i2))) {
            return 6;
        }
        if (d.contains(Integer.valueOf(i2))) {
            return 7;
        }
        if (g.contains(Integer.valueOf(i2))) {
            return 8;
        }
        if (b.contains(Integer.valueOf(i2))) {
            return 9;
        }
        return c.contains(Integer.valueOf(i2)) ? 10 : 0;
    }

    private static int b(int[] iArr) {
        if (iArr == null || iArr.length < 6) {
            LogUtil.h("PLGACHIEVE_OperationActivityRule", "getNewFavoriteActivityType activityTypeNumbers is null or length invalid.");
            return 0;
        }
        int i2 = iArr[1];
        int asInt = Arrays.stream(iArr).max().getAsInt();
        for (int i3 = 1; i3 < iArr.length; i3++) {
            if (asInt == iArr[i3]) {
                LogUtil.a("PLGACHIEVE_OperationActivityRule", "getNewFavoriteActivityType type ", Integer.valueOf(i3), " max ", Integer.valueOf(asInt));
                return i3;
            }
        }
        return 0;
    }
}
