package io.reactivex.rxjava3.internal.operators.maybe;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public final class MaybeFromFuture<T> extends Maybe<T> {
    final Future<? extends T> future;
    final long timeout;
    final TimeUnit unit;

    public MaybeFromFuture(Future<? extends T> future, long j, TimeUnit timeUnit) {
        this.future = future;
        this.timeout = j;
        this.unit = timeUnit;
    }

    @Override // io.reactivex.rxjava3.core.Maybe
    public void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        T t;
        Disposable empty = Disposable.empty();
        maybeObserver.onSubscribe(empty);
        if (empty.isDisposed()) {
            return;
        }
        try {
            long j = this.timeout;
            if (j <= 0) {
                t = this.future.get();
            } else {
                t = this.future.get(j, this.unit);
            }
            if (empty.isDisposed()) {
                return;
            }
            if (t == null) {
                maybeObserver.onComplete();
            } else {
                maybeObserver.onSuccess(t);
            }
        } catch (Throwable th) {
            th = th;
            Exceptions.throwIfFatal(th);
            if (th instanceof ExecutionException) {
                th = th.getCause();
            }
            Exceptions.throwIfFatal(th);
            if (empty.isDisposed()) {
                return;
            }
            maybeObserver.onError(th);
        }
    }
}
