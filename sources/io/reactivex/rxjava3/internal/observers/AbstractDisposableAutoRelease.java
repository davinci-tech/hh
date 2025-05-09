package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.disposables.DisposableContainer;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.functions.Functions;
import io.reactivex.rxjava3.observers.LambdaConsumerIntrospection;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes10.dex */
abstract class AbstractDisposableAutoRelease extends AtomicReference<Disposable> implements Disposable, LambdaConsumerIntrospection {
    private static final long serialVersionUID = 8924480688481408726L;
    final AtomicReference<DisposableContainer> composite;
    final Action onComplete;
    final Consumer<? super Throwable> onError;

    AbstractDisposableAutoRelease(DisposableContainer disposableContainer, Consumer<? super Throwable> consumer, Action action) {
        this.onError = consumer;
        this.onComplete = action;
        this.composite = new AtomicReference<>(disposableContainer);
    }

    public final void onError(Throwable th) {
        if (get() != DisposableHelper.DISPOSED) {
            lazySet(DisposableHelper.DISPOSED);
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

    public final void onComplete() {
        if (get() != DisposableHelper.DISPOSED) {
            lazySet(DisposableHelper.DISPOSED);
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
    public final void dispose() {
        DisposableHelper.dispose(this);
        removeSelf();
    }

    final void removeSelf() {
        DisposableContainer andSet = this.composite.getAndSet(null);
        if (andSet != null) {
            andSet.delete(this);
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public final boolean isDisposed() {
        return DisposableHelper.isDisposed(get());
    }

    public final void onSubscribe(Disposable disposable) {
        DisposableHelper.setOnce(this, disposable);
    }

    @Override // io.reactivex.rxjava3.observers.LambdaConsumerIntrospection
    public final boolean hasCustomOnError() {
        return this.onError != Functions.ON_ERROR_MISSING;
    }
}
