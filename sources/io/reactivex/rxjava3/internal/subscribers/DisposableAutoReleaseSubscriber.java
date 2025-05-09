package io.reactivex.rxjava3.internal.subscribers;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.disposables.DisposableContainer;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.internal.functions.Functions;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.observers.LambdaConsumerIntrospection;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public final class DisposableAutoReleaseSubscriber<T> extends AtomicReference<Subscription> implements FlowableSubscriber<T>, Disposable, LambdaConsumerIntrospection {
    private static final long serialVersionUID = 8924480688481408726L;
    final AtomicReference<DisposableContainer> composite;
    final Action onComplete;
    final Consumer<? super Throwable> onError;
    final Consumer<? super T> onNext;

    public DisposableAutoReleaseSubscriber(DisposableContainer disposableContainer, Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, Action action) {
        this.onNext = consumer;
        this.onError = consumer2;
        this.onComplete = action;
        this.composite = new AtomicReference<>(disposableContainer);
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(T t) {
        if (get() != SubscriptionHelper.CANCELLED) {
            try {
                this.onNext.accept(t);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                get().cancel();
                onError(th);
            }
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (get() != SubscriptionHelper.CANCELLED) {
            lazySet(SubscriptionHelper.CANCELLED);
            try {
                this.onError.accept(th);
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                RxJavaPlugins.onError(new CompositeException(th, th2));
            }
        } else {
            RxJavaPlugins.onError(th);
        }
        removeSelf();
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (get() != SubscriptionHelper.CANCELLED) {
            lazySet(SubscriptionHelper.CANCELLED);
            try {
                this.onComplete.run();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.onError(th);
            }
        }
        removeSelf();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        SubscriptionHelper.cancel(this);
        removeSelf();
    }

    void removeSelf() {
        DisposableContainer andSet = this.composite.getAndSet(null);
        if (andSet != null) {
            andSet.delete(this);
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return SubscriptionHelper.CANCELLED == get();
    }

    @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.setOnce(this, subscription)) {
            subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
        }
    }

    @Override // io.reactivex.rxjava3.observers.LambdaConsumerIntrospection
    public boolean hasCustomOnError() {
        return this.onError != Functions.ON_ERROR_MISSING;
    }
}
