package io.reactivex.rxjava3.subjects;

import androidx.webkit.ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0;
import io.reactivex.rxjava3.annotations.CheckReturnValue;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.functions.ObjectHelper;
import io.reactivex.rxjava3.internal.observers.BasicIntQueueDisposable;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.operators.SimpleQueue;
import io.reactivex.rxjava3.operators.SpscLinkedArrayQueue;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes10.dex */
public final class UnicastSubject<T> extends Subject<T> {
    final boolean delayError;
    volatile boolean disposed;
    volatile boolean done;
    boolean enableOperatorFusion;
    Throwable error;
    final AtomicReference<Runnable> onTerminate;
    final SpscLinkedArrayQueue<T> queue;
    final AtomicReference<Observer<? super T>> downstream = new AtomicReference<>();
    final AtomicBoolean once = new AtomicBoolean();
    final BasicIntQueueDisposable<T> wip = new UnicastQueueDisposable();

    @CheckReturnValue
    public static <T> UnicastSubject<T> create() {
        return new UnicastSubject<>(bufferSize(), null, true);
    }

    @CheckReturnValue
    public static <T> UnicastSubject<T> create(int i) {
        ObjectHelper.verifyPositive(i, "capacityHint");
        return new UnicastSubject<>(i, null, true);
    }

    @CheckReturnValue
    public static <T> UnicastSubject<T> create(int i, Runnable runnable) {
        ObjectHelper.verifyPositive(i, "capacityHint");
        Objects.requireNonNull(runnable, "onTerminate");
        return new UnicastSubject<>(i, runnable, true);
    }

    @CheckReturnValue
    public static <T> UnicastSubject<T> create(int i, Runnable runnable, boolean z) {
        ObjectHelper.verifyPositive(i, "capacityHint");
        Objects.requireNonNull(runnable, "onTerminate");
        return new UnicastSubject<>(i, runnable, z);
    }

    @CheckReturnValue
    public static <T> UnicastSubject<T> create(boolean z) {
        return new UnicastSubject<>(bufferSize(), null, z);
    }

    UnicastSubject(int i, Runnable runnable, boolean z) {
        this.queue = new SpscLinkedArrayQueue<>(i);
        this.onTerminate = new AtomicReference<>(runnable);
        this.delayError = z;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super T> observer) {
        if (!this.once.get() && this.once.compareAndSet(false, true)) {
            observer.onSubscribe(this.wip);
            this.downstream.lazySet(observer);
            if (this.disposed) {
                this.downstream.lazySet(null);
                return;
            } else {
                drain();
                return;
            }
        }
        EmptyDisposable.error(new IllegalStateException("Only a single observer allowed."), observer);
    }

    void doTerminate() {
        Runnable runnable = this.onTerminate.get();
        if (runnable == null || !ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(this.onTerminate, runnable, null)) {
            return;
        }
        runnable.run();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onSubscribe(Disposable disposable) {
        if (this.done || this.disposed) {
            disposable.dispose();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onNext(T t) {
        ExceptionHelper.nullCheck(t, "onNext called with a null value.");
        if (this.done || this.disposed) {
            return;
        }
        this.queue.offer(t);
        drain();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onError(Throwable th) {
        ExceptionHelper.nullCheck(th, "onError called with a null Throwable.");
        if (this.done || this.disposed) {
            RxJavaPlugins.onError(th);
            return;
        }
        this.error = th;
        this.done = true;
        doTerminate();
        drain();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onComplete() {
        if (this.done || this.disposed) {
            return;
        }
        this.done = true;
        doTerminate();
        drain();
    }

    void drainNormal(Observer<? super T> observer) {
        SpscLinkedArrayQueue<T> spscLinkedArrayQueue = this.queue;
        boolean z = this.delayError;
        boolean z2 = true;
        int i = 1;
        while (!this.disposed) {
            boolean z3 = this.done;
            T poll = this.queue.poll();
            boolean z4 = poll == null;
            if (z3) {
                if ((!z) && z2) {
                    if (failedFast(spscLinkedArrayQueue, observer)) {
                        return;
                    } else {
                        z2 = false;
                    }
                }
                if (z4) {
                    errorOrComplete(observer);
                    return;
                }
            }
            if (!z4) {
                observer.onNext(poll);
            } else {
                i = this.wip.addAndGet(-i);
                if (i == 0) {
                    return;
                }
            }
        }
        this.downstream.lazySet(null);
        spscLinkedArrayQueue.clear();
    }

    void drainFused(Observer<? super T> observer) {
        SpscLinkedArrayQueue<T> spscLinkedArrayQueue = this.queue;
        boolean z = this.delayError;
        int i = 1;
        while (!this.disposed) {
            boolean z2 = this.done;
            if ((!z) && z2 && failedFast(spscLinkedArrayQueue, observer)) {
                return;
            }
            observer.onNext(null);
            if (z2) {
                errorOrComplete(observer);
                return;
            } else {
                i = this.wip.addAndGet(-i);
                if (i == 0) {
                    return;
                }
            }
        }
        this.downstream.lazySet(null);
    }

    void errorOrComplete(Observer<? super T> observer) {
        this.downstream.lazySet(null);
        Throwable th = this.error;
        if (th != null) {
            observer.onError(th);
        } else {
            observer.onComplete();
        }
    }

    boolean failedFast(SimpleQueue<T> simpleQueue, Observer<? super T> observer) {
        Throwable th = this.error;
        if (th == null) {
            return false;
        }
        this.downstream.lazySet(null);
        simpleQueue.clear();
        observer.onError(th);
        return true;
    }

    void drain() {
        if (this.wip.getAndIncrement() != 0) {
            return;
        }
        Observer<? super T> observer = this.downstream.get();
        int i = 1;
        while (observer == null) {
            i = this.wip.addAndGet(-i);
            if (i == 0) {
                return;
            } else {
                observer = this.downstream.get();
            }
        }
        if (this.enableOperatorFusion) {
            drainFused(observer);
        } else {
            drainNormal(observer);
        }
    }

    @Override // io.reactivex.rxjava3.subjects.Subject
    @CheckReturnValue
    public boolean hasObservers() {
        return this.downstream.get() != null;
    }

    @Override // io.reactivex.rxjava3.subjects.Subject
    @CheckReturnValue
    public Throwable getThrowable() {
        if (this.done) {
            return this.error;
        }
        return null;
    }

    @Override // io.reactivex.rxjava3.subjects.Subject
    @CheckReturnValue
    public boolean hasThrowable() {
        return this.done && this.error != null;
    }

    @Override // io.reactivex.rxjava3.subjects.Subject
    @CheckReturnValue
    public boolean hasComplete() {
        return this.done && this.error == null;
    }

    final class UnicastQueueDisposable extends BasicIntQueueDisposable<T> {
        private static final long serialVersionUID = 7926949470189395511L;

        UnicastQueueDisposable() {
        }

        @Override // io.reactivex.rxjava3.operators.QueueFuseable
        public int requestFusion(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            UnicastSubject.this.enableOperatorFusion = true;
            return 2;
        }

        @Override // io.reactivex.rxjava3.operators.SimpleQueue
        public T poll() {
            return UnicastSubject.this.queue.poll();
        }

        @Override // io.reactivex.rxjava3.operators.SimpleQueue
        public boolean isEmpty() {
            return UnicastSubject.this.queue.isEmpty();
        }

        @Override // io.reactivex.rxjava3.operators.SimpleQueue
        public void clear() {
            UnicastSubject.this.queue.clear();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            if (UnicastSubject.this.disposed) {
                return;
            }
            UnicastSubject.this.disposed = true;
            UnicastSubject.this.doTerminate();
            UnicastSubject.this.downstream.lazySet(null);
            if (UnicastSubject.this.wip.getAndIncrement() == 0) {
                UnicastSubject.this.downstream.lazySet(null);
                if (UnicastSubject.this.enableOperatorFusion) {
                    return;
                }
                UnicastSubject.this.queue.clear();
            }
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return UnicastSubject.this.disposed;
        }
    }
}
