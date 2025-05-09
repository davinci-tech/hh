package io.reactivex.rxjava3.observers;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.EndConsumerHelper;

/* loaded from: classes10.dex */
public abstract class DefaultObserver<T> implements Observer<T> {
    private Disposable upstream;

    protected void onStart() {
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (EndConsumerHelper.validate(this.upstream, disposable, getClass())) {
            this.upstream = disposable;
            onStart();
        }
    }

    protected final void cancel() {
        Disposable disposable = this.upstream;
        this.upstream = DisposableHelper.DISPOSED;
        disposable.dispose();
    }
}
