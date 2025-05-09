package io.reactivex.rxjava3.internal.subscribers;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.BlockingHelper;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import java.util.concurrent.CountDownLatch;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public abstract class BlockingBaseSubscriber<T> extends CountDownLatch implements FlowableSubscriber<T> {
    volatile boolean cancelled;
    Throwable error;
    Subscription upstream;
    T value;

    public BlockingBaseSubscriber() {
        super(1);
    }

    @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
    public final void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            if (this.cancelled) {
                return;
            }
            subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
            if (this.cancelled) {
                this.upstream = SubscriptionHelper.CANCELLED;
                subscription.cancel();
            }
        }
    }

    @Override // org.reactivestreams.Subscriber
    public final void onComplete() {
        countDown();
    }

    public final T blockingGet() {
        if (getCount() != 0) {
            try {
                BlockingHelper.verifyNonBlocking();
                await();
            } catch (InterruptedException e) {
                Subscription subscription = this.upstream;
                this.upstream = SubscriptionHelper.CANCELLED;
                if (subscription != null) {
                    subscription.cancel();
                }
                throw ExceptionHelper.wrapOrThrow(e);
            }
        }
        Throwable th = this.error;
        if (th != null) {
            throw ExceptionHelper.wrapOrThrow(th);
        }
        return this.value;
    }
}
