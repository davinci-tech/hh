package io.reactivex.rxjava3.subscribers;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.EndConsumerHelper;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;

/* loaded from: classes10.dex */
public abstract class DisposableSubscriber<T> implements FlowableSubscriber<T>, Disposable {
    final AtomicReference<Subscription> upstream = new AtomicReference<>();

    @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
    public final void onSubscribe(Subscription subscription) {
        if (EndConsumerHelper.setOnce(this.upstream, subscription, getClass())) {
            onStart();
        }
    }

    protected void onStart() {
        this.upstream.get().request(LocationRequestCompat.PASSIVE_INTERVAL);
    }

    protected final void request(long j) {
        this.upstream.get().request(j);
    }

    protected final void cancel() {
        dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public final boolean isDisposed() {
        return this.upstream.get() == SubscriptionHelper.CANCELLED;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public final void dispose() {
        SubscriptionHelper.cancel(this.upstream);
    }
}
