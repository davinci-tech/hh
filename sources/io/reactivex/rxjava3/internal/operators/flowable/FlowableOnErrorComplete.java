package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public final class FlowableOnErrorComplete<T> extends AbstractFlowableWithUpstream<T, T> {
    final Predicate<? super Throwable> predicate;

    public FlowableOnErrorComplete(Flowable<T> flowable, Predicate<? super Throwable> predicate) {
        super(flowable);
        this.predicate = predicate;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.source.subscribe((FlowableSubscriber) new OnErrorCompleteSubscriber(subscriber, this.predicate));
    }

    /* loaded from: classes10.dex */
    public static final class OnErrorCompleteSubscriber<T> implements FlowableSubscriber<T>, Subscription {
        final Subscriber<? super T> downstream;
        final Predicate<? super Throwable> predicate;
        Subscription upstream;

        public OnErrorCompleteSubscriber(Subscriber<? super T> subscriber, Predicate<? super Throwable> predicate) {
            this.downstream = subscriber;
            this.predicate = predicate;
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                this.upstream = subscription;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            try {
                if (this.predicate.test(th)) {
                    this.downstream.onComplete();
                } else {
                    this.downstream.onError(th);
                }
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                this.downstream.onError(new CompositeException(th, th2));
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.downstream.onComplete();
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.upstream.cancel();
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            this.upstream.request(j);
        }
    }
}
