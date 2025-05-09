package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleOperator;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import java.util.Objects;

/* loaded from: classes.dex */
public final class SingleLift<T, R> extends Single<R> {
    final SingleOperator<? extends R, ? super T> onLift;
    final SingleSource<T> source;

    public SingleLift(SingleSource<T> singleSource, SingleOperator<? extends R, ? super T> singleOperator) {
        this.source = singleSource;
        this.onLift = singleOperator;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super R> singleObserver) {
        try {
            this.source.subscribe((SingleObserver) Objects.requireNonNull(this.onLift.apply(singleObserver), "The onLift returned a null SingleObserver"));
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptyDisposable.error(th, singleObserver);
        }
    }
}
