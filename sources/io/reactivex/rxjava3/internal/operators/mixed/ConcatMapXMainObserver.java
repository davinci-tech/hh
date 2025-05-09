package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.internal.util.ErrorMode;
import io.reactivex.rxjava3.operators.QueueDisposable;
import io.reactivex.rxjava3.operators.SimpleQueue;
import io.reactivex.rxjava3.operators.SpscLinkedArrayQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes10.dex */
public abstract class ConcatMapXMainObserver<T> extends AtomicInteger implements Observer<T>, Disposable {
    private static final long serialVersionUID = -3214213361171757852L;
    volatile boolean disposed;
    volatile boolean done;
    final ErrorMode errorMode;
    final AtomicThrowable errors = new AtomicThrowable();
    final int prefetch;
    SimpleQueue<T> queue;
    Disposable upstream;

    void clearValue() {
    }

    abstract void disposeInner();

    abstract void drain();

    abstract void onSubscribeDownstream();

    public ConcatMapXMainObserver(int i, ErrorMode errorMode) {
        this.errorMode = errorMode;
        this.prefetch = i;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.validate(this.upstream, disposable)) {
            this.upstream = disposable;
            if (disposable instanceof QueueDisposable) {
                QueueDisposable queueDisposable = (QueueDisposable) disposable;
                int requestFusion = queueDisposable.requestFusion(7);
                if (requestFusion == 1) {
                    this.queue = queueDisposable;
                    this.done = true;
                    onSubscribeDownstream();
                    drain();
                    return;
                }
                if (requestFusion == 2) {
                    this.queue = queueDisposable;
                    onSubscribeDownstream();
                    return;
                }
            }
            this.queue = new SpscLinkedArrayQueue(this.prefetch);
            onSubscribeDownstream();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(T t) {
        if (t != null) {
            this.queue.offer(t);
        }
        drain();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.errors.tryAddThrowableOrReport(th)) {
            if (this.errorMode == ErrorMode.IMMEDIATE) {
                disposeInner();
            }
            this.done = true;
            drain();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        this.done = true;
        drain();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public final void dispose() {
        this.disposed = true;
        this.upstream.dispose();
        disposeInner();
        this.errors.tryTerminateAndReport();
        if (getAndIncrement() == 0) {
            this.queue.clear();
            clearValue();
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public final boolean isDisposed() {
        return this.disposed;
    }
}
