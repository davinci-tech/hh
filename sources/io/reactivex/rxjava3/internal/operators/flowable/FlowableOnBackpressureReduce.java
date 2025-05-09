package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.BiFunction;
import java.util.Objects;
import org.reactivestreams.Subscriber;

/* loaded from: classes.dex */
public final class FlowableOnBackpressureReduce<T> extends AbstractFlowableWithUpstream<T, T> {
    final BiFunction<T, T, T> reducer;

    public FlowableOnBackpressureReduce(Flowable<T> flowable, BiFunction<T, T, T> biFunction) {
        super(flowable);
        this.reducer = biFunction;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.source.subscribe((FlowableSubscriber) new BackpressureReduceSubscriber(subscriber, this.reducer));
    }

    /* loaded from: classes10.dex */
    static final class BackpressureReduceSubscriber<T> extends AbstractBackpressureThrottlingSubscriber<T, T> {
        private static final long serialVersionUID = 821363947659780367L;
        final BiFunction<T, T, T> reducer;

        BackpressureReduceSubscriber(Subscriber<? super T> subscriber, BiFunction<T, T, T> biFunction) {
            super(subscriber);
            this.reducer = biFunction;
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.AbstractBackpressureThrottlingSubscriber, org.reactivestreams.Subscriber
        public void onNext(T t) {
            Object obj = this.current.get();
            if (obj != null) {
                obj = this.current.getAndSet(null);
            }
            if (obj == null) {
                this.current.lazySet(t);
            } else {
                try {
                    this.current.lazySet(Objects.requireNonNull(this.reducer.apply(obj, t), "The reducer returned a null value"));
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.upstream.cancel();
                    onError(th);
                    return;
                }
            }
            drain();
        }
    }
}
