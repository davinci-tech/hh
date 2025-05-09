package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.processors.FlowableProcessor;
import java.util.concurrent.atomic.AtomicBoolean;
import org.reactivestreams.Subscriber;

/* loaded from: classes10.dex */
final class FlowableWindowSubscribeIntercept<T> extends Flowable<T> {
    final AtomicBoolean once = new AtomicBoolean();
    final FlowableProcessor<T> window;

    FlowableWindowSubscribeIntercept(FlowableProcessor<T> flowableProcessor) {
        this.window = flowableProcessor;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.window.subscribe(subscriber);
        this.once.set(true);
    }

    boolean tryAbandon() {
        return !this.once.get() && this.once.compareAndSet(false, true);
    }
}
