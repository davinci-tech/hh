package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Notification;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public final class FlowableDematerialize<T, R> extends AbstractFlowableWithUpstream<T, R> {
    final Function<? super T, ? extends Notification<R>> selector;

    public FlowableDematerialize(Flowable<T> flowable, Function<? super T, ? extends Notification<R>> function) {
        super(flowable);
        this.selector = function;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super R> subscriber) {
        this.source.subscribe((FlowableSubscriber) new DematerializeSubscriber(subscriber, this.selector));
    }

    /* loaded from: classes10.dex */
    static final class DematerializeSubscriber<T, R> implements FlowableSubscriber<T>, Subscription {
        boolean done;
        final Subscriber<? super R> downstream;
        final Function<? super T, ? extends Notification<R>> selector;
        Subscription upstream;

        DematerializeSubscriber(Subscriber<? super R> subscriber, Function<? super T, ? extends Notification<R>> function) {
            this.downstream = subscriber;
            this.selector = function;
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                this.upstream = subscription;
                this.downstream.onSubscribe(this);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.done) {
                if (t instanceof Notification) {
                    Notification notification = (Notification) t;
                    if (notification.isOnError()) {
                        RxJavaPlugins.onError(notification.getError());
                        return;
                    }
                    return;
                }
                return;
            }
            try {
                Notification notification2 = (Notification) Objects.requireNonNull(this.selector.apply(t), "The selector returned a null Notification");
                if (notification2.isOnError()) {
                    this.upstream.cancel();
                    onError(notification2.getError());
                } else if (notification2.isOnComplete()) {
                    this.upstream.cancel();
                    onComplete();
                } else {
                    this.downstream.onNext((Object) notification2.getValue());
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.upstream.cancel();
                onError(th);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
            } else {
                this.done = true;
                this.downstream.onError(th);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            this.downstream.onComplete();
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            this.upstream.request(j);
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.upstream.cancel();
        }
    }
}
