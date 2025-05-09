package io.reactivex.rxjava3.internal.subscriptions;

import io.reactivex.rxjava3.operators.QueueSubscription;
import org.reactivestreams.Subscriber;

/* loaded from: classes.dex */
public enum EmptySubscription implements QueueSubscription<Object> {
    INSTANCE;

    @Override // org.reactivestreams.Subscription
    public void cancel() {
    }

    @Override // io.reactivex.rxjava3.operators.SimpleQueue
    public void clear() {
    }

    @Override // io.reactivex.rxjava3.operators.SimpleQueue
    public boolean isEmpty() {
        return true;
    }

    @Override // io.reactivex.rxjava3.operators.SimpleQueue
    public Object poll() {
        return null;
    }

    @Override // io.reactivex.rxjava3.operators.QueueFuseable
    public int requestFusion(int i) {
        return i & 2;
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j) {
        SubscriptionHelper.validate(j);
    }

    public static void error(Throwable th, Subscriber<?> subscriber) {
        subscriber.onSubscribe(INSTANCE);
        subscriber.onError(th);
    }

    public static void complete(Subscriber<?> subscriber) {
        subscriber.onSubscribe(INSTANCE);
        subscriber.onComplete();
    }

    @Override // io.reactivex.rxjava3.operators.SimpleQueue
    public boolean offer(Object obj) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    @Override // io.reactivex.rxjava3.operators.SimpleQueue
    public boolean offer(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    @Override // java.lang.Enum
    public String toString() {
        return "EmptySubscription";
    }
}
