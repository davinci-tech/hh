package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public final class FlowableUnsubscribeOn<T> extends AbstractFlowableWithUpstream<T, T> {
    final Scheduler scheduler;

    public FlowableUnsubscribeOn(Flowable<T> flowable, Scheduler scheduler) {
        super(flowable);
        this.scheduler = scheduler;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.source.subscribe((FlowableSubscriber) new UnsubscribeSubscriber(subscriber, this.scheduler));
    }

    /* loaded from: classes10.dex */
    static final class UnsubscribeSubscriber<T> extends AtomicBoolean implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = 1015244841293359600L;
        final Subscriber<? super T> downstream;
        final Scheduler scheduler;
        Subscription upstream;

        UnsubscribeSubscriber(Subscriber<? super T> subscriber, Scheduler scheduler) {
            this.downstream = subscriber;
            this.scheduler = scheduler;
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
            if (get()) {
                return;
            }
            this.downstream.onNext(t);
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (get()) {
                RxJavaPlugins.onError(th);
            } else {
                this.downstream.onError(th);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (get()) {
                return;
            }
            this.downstream.onComplete();
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            this.upstream.request(j);
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (compareAndSet(false, true)) {
                this.scheduler.scheduleDirect(new Cancellation());
            }
        }

        final class Cancellation implements Runnable {
            Cancellation() {
            }

            @Override // java.lang.Runnable
            public void run() {
                UnsubscribeSubscriber.this.upstream.cancel();
            }
        }
    }
}
