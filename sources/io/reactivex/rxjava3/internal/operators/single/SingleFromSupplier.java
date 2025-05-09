package io.reactivex.rxjava3.internal.operators.single;

import defpackage.aai;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;

/* loaded from: classes.dex */
public final class SingleFromSupplier<T> extends Single<T> {
    final Supplier<? extends T> supplier;

    public SingleFromSupplier(Supplier<? extends T> supplier) {
        this.supplier = supplier;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        Disposable empty = Disposable.empty();
        singleObserver.onSubscribe(empty);
        if (empty.isDisposed()) {
            return;
        }
        try {
            aai aaiVar = (Object) Objects.requireNonNull(this.supplier.get(), "The supplier returned a null value");
            if (empty.isDisposed()) {
                return;
            }
            singleObserver.onSuccess(aaiVar);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            if (!empty.isDisposed()) {
                singleObserver.onError(th);
            } else {
                RxJavaPlugins.onError(th);
            }
        }
    }
}
