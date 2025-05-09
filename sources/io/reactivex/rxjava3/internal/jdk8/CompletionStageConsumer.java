package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class CompletionStageConsumer<T> extends CompletableFuture<T> implements MaybeObserver<T>, SingleObserver<T>, CompletableObserver {
    final T defaultItem;
    final boolean hasDefault;
    final AtomicReference<Disposable> upstream = new AtomicReference<>();

    public CompletionStageConsumer(boolean z, T t) {
        this.hasDefault = z;
        this.defaultItem = t;
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
    public void onSubscribe(Disposable disposable) {
        DisposableHelper.setOnce(this.upstream, disposable);
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
    public void onSuccess(T t) {
        clear();
        complete(t);
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
    public void onError(Throwable th) {
        clear();
        if (completeExceptionally(th)) {
            return;
        }
        RxJavaPlugins.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.CompletableObserver
    public void onComplete() {
        if (this.hasDefault) {
            complete(this.defaultItem);
        } else {
            completeExceptionally(new NoSuchElementException("The source was empty"));
        }
    }

    void cancelUpstream() {
        DisposableHelper.dispose(this.upstream);
    }

    void clear() {
        this.upstream.lazySet(DisposableHelper.DISPOSED);
    }

    @Override // java.util.concurrent.CompletableFuture, java.util.concurrent.Future
    public boolean cancel(boolean z) {
        cancelUpstream();
        return super.cancel(z);
    }

    @Override // java.util.concurrent.CompletableFuture
    public boolean complete(T t) {
        cancelUpstream();
        return super.complete(t);
    }

    @Override // java.util.concurrent.CompletableFuture
    public boolean completeExceptionally(Throwable th) {
        cancelUpstream();
        return super.completeExceptionally(th);
    }
}
