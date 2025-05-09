package io.reactivex.rxjava3.internal.jdk8;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.rxjava3.internal.subscriptions.EmptySubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collector;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes10.dex */
public final class FlowableCollectWithCollector<T, A, R> extends Flowable<R> {
    final Collector<? super T, A, R> collector;
    final Flowable<T> source;

    public FlowableCollectWithCollector(Flowable<T> flowable, Collector<? super T, A, R> collector) {
        this.source = flowable;
        this.collector = collector;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super R> subscriber) {
        try {
            this.source.subscribe((FlowableSubscriber) new CollectorSubscriber(subscriber, this.collector.supplier().get(), this.collector.accumulator(), this.collector.finisher()));
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptySubscription.error(th, subscriber);
        }
    }

    static final class CollectorSubscriber<T, A, R> extends DeferredScalarSubscription<R> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -229544830565448758L;
        final BiConsumer<A, T> accumulator;
        A container;
        boolean done;
        final Function<A, R> finisher;
        Subscription upstream;

        CollectorSubscriber(Subscriber<? super R> subscriber, A a2, BiConsumer<A, T> biConsumer, Function<A, R> function) {
            super(subscriber);
            this.container = a2;
            this.accumulator = biConsumer;
            this.finisher = function;
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                this.upstream = subscription;
                this.downstream.onSubscribe(this);
                subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            try {
                this.accumulator.accept(this.container, t);
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
                return;
            }
            this.done = true;
            this.upstream = SubscriptionHelper.CANCELLED;
            this.container = null;
            this.downstream.onError(th);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            this.upstream = SubscriptionHelper.CANCELLED;
            A a2 = this.container;
            this.container = null;
            try {
                complete(Objects.requireNonNull(this.finisher.apply(a2), "The finisher returned a null value"));
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.downstream.onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.internal.subscriptions.DeferredScalarSubscription, org.reactivestreams.Subscription
        public void cancel() {
            super.cancel();
            this.upstream.cancel();
        }
    }
}
