package com.google.android.gms.common.data;

import com.huawei.health.tradeservice.pay.PayActivity;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes2.dex */
public final class FreezableUtils {
    public static <T, E extends Freezable<T>> ArrayList<T> freeze(ArrayList<E> arrayList) {
        PayActivity.AnonymousClass6 anonymousClass6 = (ArrayList<T>) new ArrayList(arrayList.size());
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            anonymousClass6.add(arrayList.get(i).freeze());
        }
        return anonymousClass6;
    }

    public static <T, E extends Freezable<T>> ArrayList<T> freeze(E[] eArr) {
        PayActivity.AnonymousClass6 anonymousClass6 = (ArrayList<T>) new ArrayList(eArr.length);
        for (E e : eArr) {
            anonymousClass6.add(e.freeze());
        }
        return anonymousClass6;
    }

    public static <T, E extends Freezable<T>> ArrayList<T> freezeIterable(Iterable<E> iterable) {
        PayActivity.AnonymousClass6 anonymousClass6 = (ArrayList<T>) new ArrayList();
        Iterator<E> it = iterable.iterator();
        while (it.hasNext()) {
            anonymousClass6.add(it.next().freeze());
        }
        return anonymousClass6;
    }
}
