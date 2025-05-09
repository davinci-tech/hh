package io.reactivex.rxjava3.subscribers;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.EndConsumerHelper;
import org.reactivestreams.Subscription;

/* loaded from: classes10.dex */
public abstract class DefaultSubscriber<T> implements FlowableSubscriber<T> {
    Subscription upstream;

    @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
    public final void onSubscribe(Subscription subscription) {
        if (EndConsumerHelper.validate(this.upstream, subscription, getClass())) {
            this.upstream = subscription;
            onStart();
        }
    }

    protected final void request(long j) {
        Subscription subscription = this.upstream;
        if (subscription != null) {
            subscription.request(j);
        }
    }

    protected final void cancel() {
        Subscription subscription = this.upstream;
        this.upstream = SubscriptionHelper.CANCELLED;
        subscription.cancel();
    }

    protected void onStart() {
        request(LocationRequestCompat.PASSIVE_INTERVAL);
    }
}
