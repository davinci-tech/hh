package io.reactivex.rxjava3.internal.jdk8;

import java.util.NoSuchElementException;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public final class FlowableFirstStageSubscriber<T> extends FlowableStageSubscriber<T> {
    final T defaultItem;
    final boolean hasDefault;

    public FlowableFirstStageSubscriber(boolean z, T t) {
        this.hasDefault = z;
        this.defaultItem = t;
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(T t) {
        complete(t);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (isDone()) {
            return;
        }
        clear();
        if (this.hasDefault) {
            complete(this.defaultItem);
        } else {
            completeExceptionally(new NoSuchElementException());
        }
    }

    @Override // io.reactivex.rxjava3.internal.jdk8.FlowableStageSubscriber
    protected void afterSubscribe(Subscription subscription) {
        subscription.request(1L);
    }
}
