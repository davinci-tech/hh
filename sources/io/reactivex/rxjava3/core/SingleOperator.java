package io.reactivex.rxjava3.core;

@FunctionalInterface
/* loaded from: classes.dex */
public interface SingleOperator<Downstream, Upstream> {
    SingleObserver<? super Upstream> apply(SingleObserver<? super Downstream> singleObserver) throws Throwable;
}
