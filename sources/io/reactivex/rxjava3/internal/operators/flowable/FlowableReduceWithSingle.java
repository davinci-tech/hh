package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.operators.flowable.FlowableReduceSeedSingle;
import java.util.Objects;
import org.reactivestreams.Publisher;

/* loaded from: classes.dex */
public final class FlowableReduceWithSingle<T, R> extends Single<R> {
    final BiFunction<R, ? super T, R> reducer;
    final Supplier<R> seedSupplier;
    final Publisher<T> source;

    public FlowableReduceWithSingle(Publisher<T> publisher, Supplier<R> supplier, BiFunction<R, ? super T, R> biFunction) {
        this.source = publisher;
        this.seedSupplier = supplier;
        this.reducer = biFunction;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super R> singleObserver) {
        try {
            this.source.subscribe(new FlowableReduceSeedSingle.ReduceSeedObserver(singleObserver, this.reducer, Objects.requireNonNull(this.seedSupplier.get(), "The seedSupplier returned a null value")));
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptyDisposable.error(th, singleObserver);
        }
    }
}
