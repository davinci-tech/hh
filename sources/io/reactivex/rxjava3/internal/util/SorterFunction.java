package io.reactivex.rxjava3.internal.util;

import io.reactivex.rxjava3.functions.Function;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes.dex */
public final class SorterFunction<T> implements Function<List<T>, List<T>> {
    final Comparator<? super T> comparator;

    public SorterFunction(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    @Override // io.reactivex.rxjava3.functions.Function
    public List<T> apply(List<T> list) {
        Collections.sort(list, this.comparator);
        return list;
    }
}
