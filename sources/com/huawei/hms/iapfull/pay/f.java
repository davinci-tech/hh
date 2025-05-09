package com.huawei.hms.iapfull.pay;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes4.dex */
public class f {

    /* renamed from: a, reason: collision with root package name */
    private static final List<Integer> f4748a = new a();
    private static final Set<String> b = new b();

    public static boolean a(String str) {
        return b.contains(str);
    }

    class a extends ArrayList<Integer> {
        a() {
            add(5);
            add(17);
        }
    }

    class b extends HashSet<String> {
        b() {
            add("0");
            add("-1");
            add("30000");
            add("30001");
            add("30002");
            add("30004");
            add("30005");
            add("30099");
            add("30007");
            add("30010");
            add("30031");
            add("30032");
            add("30033");
            add("30034");
        }
    }

    public static boolean a(int i) {
        return f4748a.contains(Integer.valueOf(i));
    }
}
