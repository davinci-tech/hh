package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.DisposableContainer;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;

/* loaded from: classes.dex */
public final class DisposableAutoReleaseMultiObserver<T> extends AbstractDisposableAutoRelease implements SingleObserver<T>, MaybeObserver<T>, CompletableObserver {
    private static final long serialVersionUID = 8924480688481408726L;
    final Consumer<? super T> onSuccess;

    public DisposableAutoReleaseMultiObserver(DisposableContainer disposableContainer, Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, Action action) {
        super(disposableContainer, consumer2, action);
        this.onSuccess = consumer;
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public void onSuccess(T t) {
        if (get() != DisposableHelper.DISPOSED) {
            lazySet(DisposableHelper.DISPOSED);
            try {
                this.onSuccess.accept(t);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.onError(th);
            }
        }
        removeSelf();
    }
}
