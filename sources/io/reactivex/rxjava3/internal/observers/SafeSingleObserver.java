package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;

/* loaded from: classes.dex */
public final class SafeSingleObserver<T> implements SingleObserver<T> {
    final SingleObserver<? super T> downstream;
    boolean onSubscribeFailed;

    public SafeSingleObserver(SingleObserver<? super T> singleObserver) {
        this.downstream = singleObserver;
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
    public void onSubscribe(Disposable disposable) {
        try {
            this.downstream.onSubscribe(disposable);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            this.onSubscribeFailed = true;
            disposable.dispose();
            RxJavaPlugins.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public void onSuccess(T t) {
        if (this.onSubscribeFailed) {
            return;
        }
        try {
            this.downstream.onSuccess(t);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            RxJavaPlugins.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
    public void onError(Throwable th) {
        if (this.onSubscribeFailed) {
            RxJavaPlugins.onError(th);
            return;
        }
        try {
            this.downstream.onError(th);
        } catch (Throwable th2) {
            Exceptions.throwIfFatal(th2);
            RxJavaPlugins.onError(new CompositeException(th, th2));
        }
    }
}
