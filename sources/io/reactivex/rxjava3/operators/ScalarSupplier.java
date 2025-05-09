package io.reactivex.rxjava3.operators;

import io.reactivex.rxjava3.functions.Supplier;

@FunctionalInterface
/* loaded from: classes.dex */
public interface ScalarSupplier<T> extends Supplier<T> {
    @Override // io.reactivex.rxjava3.functions.Supplier
    T get();
}
