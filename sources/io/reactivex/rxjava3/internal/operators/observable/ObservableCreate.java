package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Cancellable;
import io.reactivex.rxjava3.internal.disposables.CancellableDisposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.operators.SpscLinkedArrayQueue;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class ObservableCreate<T> extends Observable<T> {
    final ObservableOnSubscribe<T> source;

    public ObservableCreate(ObservableOnSubscribe<T> observableOnSubscribe) {
        this.source = observableOnSubscribe;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super T> observer) {
        CreateEmitter createEmitter = new CreateEmitter(observer);
        observer.onSubscribe(createEmitter);
        try {
            this.source.subscribe(createEmitter);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            createEmitter.onError(th);
        }
    }

    /* loaded from: classes10.dex */
    static final class CreateEmitter<T> extends AtomicReference<Disposable> implements ObservableEmitter<T>, Disposable {
        private static final long serialVersionUID = -3434801548987643227L;
        final Observer<? super T> observer;

        CreateEmitter(Observer<? super T> observer) {
            this.observer = observer;
        }

        @Override // io.reactivex.rxjava3.core.Emitter
        public void onNext(T t) {
            if (t == null) {
                onError(ExceptionHelper.createNullPointerException("onNext called with a null value."));
            } else {
                if (isDisposed()) {
                    return;
                }
                this.observer.onNext(t);
            }
        }

        @Override // io.reactivex.rxjava3.core.Emitter
        public void onError(Throwable th) {
            if (tryOnError(th)) {
                return;
            }
            RxJavaPlugins.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.ObservableEmitter
        public boolean tryOnError(Throwable th) {
            if (th == null) {
                th = ExceptionHelper.createNullPointerException("onError called with a null Throwable.");
            }
            if (isDisposed()) {
                return false;
            }
            try {
                this.observer.onError(th);
                dispose();
                return true;
            } catch (Throwable th2) {
                dispose();
                throw th2;
            }
        }

        @Override // io.reactivex.rxjava3.core.Emitter
        public void onComplete() {
            if (isDisposed()) {
                return;
            }
            try {
                this.observer.onComplete();
            } finally {
                dispose();
            }
        }

        @Override // io.reactivex.rxjava3.core.ObservableEmitter
        public void setDisposable(Disposable disposable) {
            DisposableHelper.set(this, disposable);
        }

        @Override // io.reactivex.rxjava3.core.ObservableEmitter
        public void setCancellable(Cancellable cancellable) {
            setDisposable(new CancellableDisposable(cancellable));
        }

        @Override // io.reactivex.rxjava3.core.ObservableEmitter
        public ObservableEmitter<T> serialize() {
            return new SerializedEmitter(this);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.rxjava3.core.ObservableEmitter, io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // java.util.concurrent.atomic.AtomicReference
        public String toString() {
            return String.format("%s{%s}", getClass().getSimpleName(), super.toString());
        }
    }

    /* loaded from: classes10.dex */
    static final class SerializedEmitter<T> extends AtomicInteger implements ObservableEmitter<T> {
        private static final long serialVersionUID = 4883307006032401862L;
        volatile boolean done;
        final ObservableEmitter<T> emitter;
        final AtomicThrowable errors = new AtomicThrowable();
        final SpscLinkedArrayQueue<T> queue = new SpscLinkedArrayQueue<>(16);

        @Override // io.reactivex.rxjava3.core.ObservableEmitter
        public ObservableEmitter<T> serialize() {
            return this;
        }

        SerializedEmitter(ObservableEmitter<T> observableEmitter) {
            this.emitter = observableEmitter;
        }

        @Override // io.reactivex.rxjava3.core.Emitter
        public void onNext(T t) {
            if (this.done || this.emitter.isDisposed()) {
                return;
            }
            if (t == null) {
                onError(ExceptionHelper.createNullPointerException("onNext called with a null value."));
                return;
            }
            if (get() == 0 && compareAndSet(0, 1)) {
                this.emitter.onNext(t);
                if (decrementAndGet() == 0) {
                    return;
                }
            } else {
                SpscLinkedArrayQueue<T> spscLinkedArrayQueue = this.queue;
                synchronized (spscLinkedArrayQueue) {
                    spscLinkedArrayQueue.offer(t);
                }
                if (getAndIncrement() != 0) {
                    return;
                }
            }
            drainLoop();
        }

        @Override // io.reactivex.rxjava3.core.Emitter
        public void onError(Throwable th) {
            if (tryOnError(th)) {
                return;
            }
            RxJavaPlugins.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.ObservableEmitter
        public boolean tryOnError(Throwable th) {
            if (this.done || this.emitter.isDisposed()) {
                return false;
            }
            if (th == null) {
                th = ExceptionHelper.createNullPointerException("onError called with a null Throwable.");
            }
            if (!this.errors.tryAddThrowable(th)) {
                return false;
            }
            this.done = true;
            drain();
            return true;
        }

        @Override // io.reactivex.rxjava3.core.Emitter
        public void onComplete() {
            if (this.done || this.emitter.isDisposed()) {
                return;
            }
            this.done = true;
            drain();
        }

        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        void drainLoop() {
            ObservableEmitter<T> observableEmitter = this.emitter;
            SpscLinkedArrayQueue<T> spscLinkedArrayQueue = this.queue;
            AtomicThrowable atomicThrowable = this.errors;
            int i = 1;
            while (!observableEmitter.isDisposed()) {
                if (atomicThrowable.get() != null) {
                    spscLinkedArrayQueue.clear();
                    atomicThrowable.tryTerminateConsumer(observableEmitter);
                    return;
                }
                boolean z = this.done;
                T poll = spscLinkedArrayQueue.poll();
                boolean z2 = poll == null;
                if (z && z2) {
                    observableEmitter.onComplete();
                    return;
                } else if (!z2) {
                    observableEmitter.onNext(poll);
                } else {
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                }
            }
            spscLinkedArrayQueue.clear();
        }

        @Override // io.reactivex.rxjava3.core.ObservableEmitter
        public void setDisposable(Disposable disposable) {
            this.emitter.setDisposable(disposable);
        }

        @Override // io.reactivex.rxjava3.core.ObservableEmitter
        public void setCancellable(Cancellable cancellable) {
            this.emitter.setCancellable(cancellable);
        }

        @Override // io.reactivex.rxjava3.core.ObservableEmitter, io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.emitter.isDisposed();
        }

        @Override // java.util.concurrent.atomic.AtomicInteger
        public String toString() {
            return this.emitter.toString();
        }
    }
}
