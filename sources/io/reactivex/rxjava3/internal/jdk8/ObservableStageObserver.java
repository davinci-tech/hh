package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes10.dex */
abstract class ObservableStageObserver<T> extends CompletableFuture<T> implements Observer<T> {
    final AtomicReference<Disposable> upstream = new AtomicReference<>();
    T value;

    ObservableStageObserver() {
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        DisposableHelper.setOnce(this.upstream, disposable);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        clear();
        if (completeExceptionally(th)) {
            return;
        }
        RxJavaPlugins.onError(th);
    }

    protected final void disposeUpstream() {
        DisposableHelper.dispose(this.upstream);
    }

    protected final void clear() {
        this.value = null;
        this.upstream.lazySet(DisposableHelper.DISPOSED);
    }

    @Override // java.util.concurrent.CompletableFuture, java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        disposeUpstream();
        return super.cancel(z);
    }

    @Override // java.util.concurrent.CompletableFuture
    public final boolean complete(T t) {
        disposeUpstream();
        return super.complete(t);
    }

    @Override // java.util.concurrent.CompletableFuture
    public final boolean completeExceptionally(Throwable th) {
        disposeUpstream();
        return super.completeExceptionally(th);
    }
}
