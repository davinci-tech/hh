package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;

/* loaded from: classes.dex */
public final class SingleDoOnLifecycle<T> extends Single<T> {
    final Action onDispose;
    final Consumer<? super Disposable> onSubscribe;
    final Single<T> source;

    public SingleDoOnLifecycle(Single<T> single, Consumer<? super Disposable> consumer, Action action) {
        this.source = single;
        this.onSubscribe = consumer;
        this.onDispose = action;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        this.source.subscribe(new SingleLifecycleObserver(singleObserver, this.onSubscribe, this.onDispose));
    }

    /* loaded from: classes10.dex */
    static final class SingleLifecycleObserver<T> implements SingleObserver<T>, Disposable {
        final SingleObserver<? super T> downstream;
        final Action onDispose;
        final Consumer<? super Disposable> onSubscribe;
        Disposable upstream;

        SingleLifecycleObserver(SingleObserver<? super T> singleObserver, Consumer<? super Disposable> consumer, Action action) {
            this.downstream = singleObserver;
            this.onSubscribe = consumer;
            this.onDispose = action;
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            try {
                this.onSubscribe.accept(disposable);
                if (DisposableHelper.validate(this.upstream, disposable)) {
                    this.upstream = disposable;
                    this.downstream.onSubscribe(this);
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                disposable.dispose();
                this.upstream = DisposableHelper.DISPOSED;
                EmptyDisposable.error(th, this.downstream);
            }
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onSuccess(T t) {
            if (this.upstream != DisposableHelper.DISPOSED) {
                this.upstream = DisposableHelper.DISPOSED;
                this.downstream.onSuccess(t);
            }
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onError(Throwable th) {
            if (this.upstream != DisposableHelper.DISPOSED) {
                this.upstream = DisposableHelper.DISPOSED;
                this.downstream.onError(th);
            } else {
                RxJavaPlugins.onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            try {
                this.onDispose.run();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.onError(th);
            }
            this.upstream.dispose();
            this.upstream = DisposableHelper.DISPOSED;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }
    }
}
