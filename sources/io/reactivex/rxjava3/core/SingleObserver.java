package io.reactivex.rxjava3.core;

import io.reactivex.rxjava3.disposables.Disposable;

/* loaded from: classes.dex */
public interface SingleObserver<T> {
    void onError(Throwable th);

    void onSubscribe(Disposable disposable);

    void onSuccess(T t);
}
