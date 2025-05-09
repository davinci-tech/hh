package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;
import io.reactivex.rxjava3.internal.util.BlockingHelper;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes.dex */
public final class BlockingDisposableMultiObserver<T> extends CountDownLatch implements MaybeObserver<T>, SingleObserver<T>, CompletableObserver, Disposable {
    Throwable error;
    final SequentialDisposable upstream;
    T value;

    public BlockingDisposableMultiObserver() {
        super(1);
        this.upstream = new SequentialDisposable();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.upstream.dispose();
        countDown();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.upstream.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
    public void onSubscribe(Disposable disposable) {
        DisposableHelper.setOnce(this.upstream, disposable);
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
    public void onSuccess(T t) {
        this.value = t;
        this.upstream.lazySet(Disposable.disposed());
        countDown();
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
    public void onError(Throwable th) {
        this.error = th;
        this.upstream.lazySet(Disposable.disposed());
        countDown();
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.CompletableObserver
    public void onComplete() {
        this.upstream.lazySet(Disposable.disposed());
        countDown();
    }

    public void blockingConsume(CompletableObserver completableObserver) {
        if (getCount() != 0) {
            try {
                BlockingHelper.verifyNonBlocking();
                await();
            } catch (InterruptedException e) {
                dispose();
                completableObserver.onError(e);
                return;
            }
        }
        if (isDisposed()) {
            return;
        }
        Throwable th = this.error;
        if (th != null) {
            completableObserver.onError(th);
        } else {
            completableObserver.onComplete();
        }
    }

    public void blockingConsume(SingleObserver<? super T> singleObserver) {
        if (getCount() != 0) {
            try {
                BlockingHelper.verifyNonBlocking();
                await();
            } catch (InterruptedException e) {
                dispose();
                singleObserver.onError(e);
                return;
            }
        }
        if (isDisposed()) {
            return;
        }
        Throwable th = this.error;
        if (th != null) {
            singleObserver.onError(th);
        } else {
            singleObserver.onSuccess(this.value);
        }
    }

    public void blockingConsume(MaybeObserver<? super T> maybeObserver) {
        if (getCount() != 0) {
            try {
                BlockingHelper.verifyNonBlocking();
                await();
            } catch (InterruptedException e) {
                dispose();
                maybeObserver.onError(e);
                return;
            }
        }
        if (isDisposed()) {
            return;
        }
        Throwable th = this.error;
        if (th != null) {
            maybeObserver.onError(th);
            return;
        }
        T t = this.value;
        if (t == null) {
            maybeObserver.onComplete();
        } else {
            maybeObserver.onSuccess(t);
        }
    }
}
