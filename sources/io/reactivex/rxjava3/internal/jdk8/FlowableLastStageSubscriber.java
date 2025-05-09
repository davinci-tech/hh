package io.reactivex.rxjava3.internal.jdk8;

import androidx.core.location.LocationRequestCompat;
import java.util.NoSuchElementException;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public final class FlowableLastStageSubscriber<T> extends FlowableStageSubscriber<T> {
    final T defaultItem;
    final boolean hasDefault;

    public FlowableLastStageSubscriber(boolean z, T t) {
        this.hasDefault = z;
        this.defaultItem = t;
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(T t) {
        this.value = t;
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (isDone()) {
            return;
        }
        T t = this.value;
        clear();
        if (t != null) {
            complete(t);
        } else if (this.hasDefault) {
            complete(this.defaultItem);
        } else {
            completeExceptionally(new NoSuchElementException());
        }
    }

    @Override // io.reactivex.rxjava3.internal.jdk8.FlowableStageSubscriber
    protected void afterSubscribe(Subscription subscription) {
        subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
    }
}
