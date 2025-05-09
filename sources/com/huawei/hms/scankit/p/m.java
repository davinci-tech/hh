package com.huawei.hms.scankit.p;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
final class m {

    /* renamed from: a, reason: collision with root package name */
    private final Map<Integer, Integer> f5826a = new HashMap();

    m() {
    }

    void a(int i) {
        Integer num = this.f5826a.get(Integer.valueOf(i));
        if (num == null) {
            num = 0;
        }
        this.f5826a.put(Integer.valueOf(i), Integer.valueOf(num.intValue() + 1));
    }

    int[] a() {
        ArrayList arrayList = new ArrayList();
        int i = -1;
        for (Map.Entry<Integer, Integer> entry : this.f5826a.entrySet()) {
            if (entry.getValue().intValue() > i) {
                i = entry.getValue().intValue();
                arrayList.clear();
                arrayList.add(entry.getKey());
            } else if (entry.getValue().intValue() == i) {
                arrayList.add(entry.getKey());
            }
        }
        return n5.a(arrayList);
    }
}
