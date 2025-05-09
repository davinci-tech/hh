package io.reactivex.rxjava3.core;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Cancellable;

/* loaded from: classes10.dex */
public interface FlowableEmitter<T> extends Emitter<T> {
    boolean isCancelled();

    long requested();

    FlowableEmitter<T> serialize();

    void setCancellable(Cancellable cancellable);

    void setDisposable(Disposable disposable);

    boolean tryOnError(Throwable th);
}
