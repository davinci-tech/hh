package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionArbiter;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public final class FlowableSwitchIfEmpty<T> extends AbstractFlowableWithUpstream<T, T> {
    final Publisher<? extends T> other;

    public FlowableSwitchIfEmpty(Flowable<T> flowable, Publisher<? extends T> publisher) {
        super(flowable);
        this.other = publisher;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        SwitchIfEmptySubscriber switchIfEmptySubscriber = new SwitchIfEmptySubscriber(subscriber, this.other);
        subscriber.onSubscribe(switchIfEmptySubscriber.arbiter);
        this.source.subscribe((FlowableSubscriber) switchIfEmptySubscriber);
    }

    /* loaded from: classes10.dex */
    static final class SwitchIfEmptySubscriber<T> implements FlowableSubscriber<T> {
        final Subscriber<? super T> downstream;
        final Publisher<? extends T> other;
        boolean empty = true;
        final SubscriptionArbiter arbiter = new SubscriptionArbiter(false);

        SwitchIfEmptySubscriber(Subscriber<? super T> subscriber, Publisher<? extends T> publisher) {
            this.downstream = subscriber;
            this.other = publisher;
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            this.arbiter.setSubscription(subscription);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.empty) {
                this.empty = false;
            }
            this.downstream.onNext(t);
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.empty) {
                this.empty = false;
                this.other.subscribe(this);
            } else {
                this.downstream.onComplete();
            }
        }
    }
}
