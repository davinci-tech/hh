package io.reactivex.rxjava3.core;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Cancellable;

/* loaded from: classes10.dex */
public interface SingleEmitter<T> {
    boolean isDisposed();

    void onError(Throwable th);

    void onSuccess(T t);

    void setCancellable(Cancellable cancellable);

    void setDisposable(Disposable disposable);

    boolean tryOnError(Throwable th);
}
