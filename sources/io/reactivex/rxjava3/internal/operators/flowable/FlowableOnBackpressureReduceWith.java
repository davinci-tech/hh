package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Supplier;
import java.util.Objects;
import org.reactivestreams.Subscriber;

/* loaded from: classes.dex */
public final class FlowableOnBackpressureReduceWith<T, R> extends AbstractFlowableWithUpstream<T, R> {
    final BiFunction<R, ? super T, R> reducer;
    final Supplier<R> supplier;

    public FlowableOnBackpressureReduceWith(Flowable<T> flowable, Supplier<R> supplier, BiFunction<R, ? super T, R> biFunction) {
        super(flowable);
        this.reducer = biFunction;
        this.supplier = supplier;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super R> subscriber) {
        this.source.subscribe((FlowableSubscriber) new BackpressureReduceWithSubscriber(subscriber, this.supplier, this.reducer));
    }

    /* loaded from: classes10.dex */
    static final class BackpressureReduceWithSubscriber<T, R> extends AbstractBackpressureThrottlingSubscriber<T, R> {
        private static final long serialVersionUID = 8255923705960622424L;
        final BiFunction<R, ? super T, R> reducer;
        final Supplier<R> supplier;

        BackpressureReduceWithSubscriber(Subscriber<? super R> subscriber, Supplier<R> supplier, BiFunction<R, ? super T, R> biFunction) {
            super(subscriber);
            this.reducer = biFunction;
            this.supplier = supplier;
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.AbstractBackpressureThrottlingSubscriber, org.reactivestreams.Subscriber
        public void onNext(T t) {
            R r = this.current.get();
            if (r != null) {
                r = this.current.getAndSet(null);
            }
            try {
                if (r == null) {
                    this.current.lazySet(Objects.requireNonNull(this.reducer.apply(Objects.requireNonNull(this.supplier.get(), "The supplier returned a null value"), t), "The reducer returned a null value"));
                } else {
                    this.current.lazySet(Objects.requireNonNull(this.reducer.apply(r, t), "The reducer returned a null value"));
                }
                drain();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.upstream.cancel();
                onError(th);
            }
        }
    }
}
