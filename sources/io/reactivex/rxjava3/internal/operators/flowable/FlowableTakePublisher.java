package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.internal.operators.flowable.FlowableTake;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

/* loaded from: classes10.dex */
public final class FlowableTakePublisher<T> extends Flowable<T> {
    final long limit;
    final Publisher<T> source;

    public FlowableTakePublisher(Publisher<T> publisher, long j) {
        this.source = publisher;
        this.limit = j;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.source.subscribe(new FlowableTake.TakeSubscriber(subscriber, this.limit));
    }
}
