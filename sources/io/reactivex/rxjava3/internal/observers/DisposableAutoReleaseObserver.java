package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.disposables.DisposableContainer;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;

/* loaded from: classes.dex */
public final class DisposableAutoReleaseObserver<T> extends AbstractDisposableAutoRelease implements Observer<T> {
    private static final long serialVersionUID = 8924480688481408726L;
    final Consumer<? super T> onNext;

    public DisposableAutoReleaseObserver(DisposableContainer disposableContainer, Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, Action action) {
        super(disposableContainer, consumer2, action);
        this.onNext = consumer;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onNext(T t) {
        if (get() != DisposableHelper.DISPOSED) {
            try {
                this.onNext.accept(t);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                ((Disposable) get()).dispose();
                onError(th);
            }
        }
    }
}
