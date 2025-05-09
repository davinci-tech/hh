package io.reactivex.rxjava3.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.internal.fuseable.FuseToFlowable;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public final class FlowableAllSingle<T> extends Single<Boolean> implements FuseToFlowable<Boolean> {
    final Predicate<? super T> predicate;
    final Flowable<T> source;

    public FlowableAllSingle(Flowable<T> flowable, Predicate<? super T> predicate) {
        this.source = flowable;
        this.predicate = predicate;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super Boolean> singleObserver) {
        this.source.subscribe((FlowableSubscriber) new AllSubscriber(singleObserver, this.predicate));
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.FuseToFlowable
    public Flowable<Boolean> fuseToFlowable() {
        return RxJavaPlugins.onAssembly(new FlowableAll(this.source, this.predicate));
    }

    /* loaded from: classes10.dex */
    static final class AllSubscriber<T> implements FlowableSubscriber<T>, Disposable {
        boolean done;
        final SingleObserver<? super Boolean> downstream;
        final Predicate<? super T> predicate;
        Subscription upstream;

        AllSubscriber(SingleObserver<? super Boolean> singleObserver, Predicate<? super T> predicate) {
            this.downstream = singleObserver;
            this.predicate = predicate;
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
                if (this.predicate.test(t)) {
                    return;
                }
                this.done = true;
                this.upstream.cancel();
                this.upstream = SubscriptionHelper.CANCELLED;
                this.downstream.onSuccess(false);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.upstream.cancel();
                this.upstream = SubscriptionHelper.CANCELLED;
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
            this.downstream.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            this.upstream = SubscriptionHelper.CANCELLED;
            this.downstream.onSuccess(true);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.upstream.cancel();
            this.upstream = SubscriptionHelper.CANCELLED;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.upstream == SubscriptionHelper.CANCELLED;
        }
    }
}
