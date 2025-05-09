package com.huawei.hms.common.data;

import com.huawei.health.tradeservice.pay.PayActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* loaded from: classes9.dex */
public final class FreezableUtils {
    public static <T, E extends Freezable<T>> ArrayList<T> freeze(ArrayList<E> arrayList) {
        return freezeIterable(arrayList);
    }

    public static <T, E extends Freezable<T>> ArrayList<T> freezeIterable(Iterable<E> iterable) {
        PayActivity.AnonymousClass6 anonymousClass6 = (ArrayList<T>) new ArrayList();
        if (iterable == null) {
            return anonymousClass6;
        }
        Iterator<E> it = iterable.iterator();
        while (it.hasNext()) {
            anonymousClass6.add(it.next().freeze());
        }
        return anonymousClass6;
    }

    public static <T, E extends Freezable<T>> ArrayList<T> freeze(E[] eArr) {
        return freezeIterable(Arrays.asList(eArr));
    }
}
