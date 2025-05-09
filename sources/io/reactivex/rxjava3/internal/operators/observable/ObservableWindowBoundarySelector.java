package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.queue.MpscLinkedQueue;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.operators.SimplePlainQueue;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.subjects.UnicastSubject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class ObservableWindowBoundarySelector<T, B, V> extends AbstractObservableWithUpstream<T, Observable<T>> {
    final int bufferSize;
    final Function<? super B, ? extends ObservableSource<V>> closingIndicator;
    final ObservableSource<B> open;

    public ObservableWindowBoundarySelector(ObservableSource<T> observableSource, ObservableSource<B> observableSource2, Function<? super B, ? extends ObservableSource<V>> function, int i) {
        super(observableSource);
        this.open = observableSource2;
        this.closingIndicator = function;
        this.bufferSize = i;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super Observable<T>> observer) {
        this.source.subscribe(new WindowBoundaryMainObserver(observer, this.open, this.closingIndicator, this.bufferSize));
    }

    /* loaded from: classes10.dex */
    static final class WindowBoundaryMainObserver<T, B, V> extends AtomicInteger implements Observer<T>, Disposable, Runnable {
        private static final long serialVersionUID = 8646217640096099753L;
        final int bufferSize;
        final Function<? super B, ? extends ObservableSource<V>> closingIndicator;
        final Observer<? super Observable<T>> downstream;
        long emitted;
        final ObservableSource<B> open;
        volatile boolean openDone;
        Disposable upstream;
        volatile boolean upstreamCanceled;
        volatile boolean upstreamDone;
        final SimplePlainQueue<Object> queue = new MpscLinkedQueue();
        final CompositeDisposable resources = new CompositeDisposable();
        final List<UnicastSubject<T>> windows = new ArrayList();
        final AtomicLong windowCount = new AtomicLong(1);
        final AtomicBoolean downstreamDisposed = new AtomicBoolean();
        final AtomicThrowable error = new AtomicThrowable();
        final WindowStartObserver<B> startObserver = new WindowStartObserver<>(this);
        final AtomicLong requested = new AtomicLong();

        WindowBoundaryMainObserver(Observer<? super Observable<T>> observer, ObservableSource<B> observableSource, Function<? super B, ? extends ObservableSource<V>> function, int i) {
            this.downstream = observer;
            this.open = observableSource;
            this.closingIndicator = function;
            this.bufferSize = i;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.downstream.onSubscribe(this);
                this.open.subscribe(this.startObserver);
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            this.queue.offer(t);
            drain();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            this.startObserver.dispose();
            this.resources.dispose();
            if (this.error.tryAddThrowableOrReport(th)) {
                this.upstreamDone = true;
                drain();
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            this.startObserver.dispose();
            this.resources.dispose();
            this.upstreamDone = true;
            drain();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            if (this.downstreamDisposed.compareAndSet(false, true)) {
                if (this.windowCount.decrementAndGet() == 0) {
                    this.upstream.dispose();
                    this.startObserver.dispose();
                    this.resources.dispose();
                    this.error.tryTerminateAndReport();
                    this.upstreamCanceled = true;
                    drain();
                    return;
                }
                this.startObserver.dispose();
            }
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.downstreamDisposed.get();
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.windowCount.decrementAndGet() == 0) {
                this.upstream.dispose();
                this.startObserver.dispose();
                this.resources.dispose();
                this.error.tryTerminateAndReport();
                this.upstreamCanceled = true;
                drain();
            }
        }

        void open(B b) {
            this.queue.offer(new WindowStartItem(b));
            drain();
        }

        void openError(Throwable th) {
            this.upstream.dispose();
            this.resources.dispose();
            if (this.error.tryAddThrowableOrReport(th)) {
                this.upstreamDone = true;
                drain();
            }
        }

        void openComplete() {
            this.openDone = true;
            drain();
        }

        void close(WindowEndObserverIntercept<T, V> windowEndObserverIntercept) {
            this.queue.offer(windowEndObserverIntercept);
            drain();
        }

        void closeError(Throwable th) {
            this.upstream.dispose();
            this.startObserver.dispose();
            this.resources.dispose();
            if (this.error.tryAddThrowableOrReport(th)) {
                this.upstreamDone = true;
                drain();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        void drain() {
            if (getAndIncrement() != 0) {
                return;
            }
            Observer<? super Observable<T>> observer = this.downstream;
            SimplePlainQueue<Object> simplePlainQueue = this.queue;
            List<UnicastSubject<T>> list = this.windows;
            int i = 1;
            while (true) {
                if (this.upstreamCanceled) {
                    simplePlainQueue.clear();
                    list.clear();
                } else {
                    boolean z = this.upstreamDone;
                    Object poll = simplePlainQueue.poll();
                    boolean z2 = poll == null;
                    if (z && (z2 || this.error.get() != null)) {
                        terminateDownstream(observer);
                        this.upstreamCanceled = true;
                    } else if (!z2) {
                        if (poll instanceof WindowStartItem) {
                            if (!this.downstreamDisposed.get()) {
                                try {
                                    ObservableSource observableSource = (ObservableSource) Objects.requireNonNull(this.closingIndicator.apply(((WindowStartItem) poll).item), "The closingIndicator returned a null ObservableSource");
                                    this.windowCount.getAndIncrement();
                                    UnicastSubject<T> create = UnicastSubject.create(this.bufferSize, this);
                                    WindowEndObserverIntercept windowEndObserverIntercept = new WindowEndObserverIntercept(this, create);
                                    observer.onNext(windowEndObserverIntercept);
                                    if (windowEndObserverIntercept.tryAbandon()) {
                                        create.onComplete();
                                    } else {
                                        list.add(create);
                                        this.resources.add(windowEndObserverIntercept);
                                        observableSource.subscribe(windowEndObserverIntercept);
                                    }
                                } catch (Throwable th) {
                                    Exceptions.throwIfFatal(th);
                                    this.upstream.dispose();
                                    this.startObserver.dispose();
                                    this.resources.dispose();
                                    Exceptions.throwIfFatal(th);
                                    this.error.tryAddThrowableOrReport(th);
                                    this.upstreamDone = true;
                                }
                            }
                        } else if (poll instanceof WindowEndObserverIntercept) {
                            UnicastSubject<T> unicastSubject = ((WindowEndObserverIntercept) poll).window;
                            list.remove(unicastSubject);
                            this.resources.delete((Disposable) poll);
                            unicastSubject.onComplete();
                        } else {
                            Iterator<UnicastSubject<T>> it = list.iterator();
                            while (it.hasNext()) {
                                it.next().onNext(poll);
                            }
                        }
                    } else if (this.openDone && list.size() == 0) {
                        this.upstream.dispose();
                        this.startObserver.dispose();
                        this.resources.dispose();
                        terminateDownstream(observer);
                        this.upstreamCanceled = true;
                    }
                }
                i = addAndGet(-i);
                if (i == 0) {
                    return;
                }
            }
        }

        void terminateDownstream(Observer<?> observer) {
            Throwable terminate = this.error.terminate();
            if (terminate == null) {
                Iterator<UnicastSubject<T>> it = this.windows.iterator();
                while (it.hasNext()) {
                    it.next().onComplete();
                }
                observer.onComplete();
                return;
            }
            if (terminate != ExceptionHelper.TERMINATED) {
                Iterator<UnicastSubject<T>> it2 = this.windows.iterator();
                while (it2.hasNext()) {
                    it2.next().onError(terminate);
                }
                observer.onError(terminate);
            }
        }

        static final class WindowStartItem<B> {
            final B item;

            WindowStartItem(B b) {
                this.item = b;
            }
        }

        static final class WindowStartObserver<B> extends AtomicReference<Disposable> implements Observer<B> {
            private static final long serialVersionUID = -3326496781427702834L;
            final WindowBoundaryMainObserver<?, B, ?> parent;

            WindowStartObserver(WindowBoundaryMainObserver<?, B, ?> windowBoundaryMainObserver) {
                this.parent = windowBoundaryMainObserver;
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(this, disposable);
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onNext(B b) {
                this.parent.open(b);
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onError(Throwable th) {
                this.parent.openError(th);
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onComplete() {
                this.parent.openComplete();
            }

            void dispose() {
                DisposableHelper.dispose(this);
            }
        }

        static final class WindowEndObserverIntercept<T, V> extends Observable<T> implements Observer<V>, Disposable {
            final WindowBoundaryMainObserver<T, ?, V> parent;
            final UnicastSubject<T> window;
            final AtomicReference<Disposable> upstream = new AtomicReference<>();
            final AtomicBoolean once = new AtomicBoolean();

            WindowEndObserverIntercept(WindowBoundaryMainObserver<T, ?, V> windowBoundaryMainObserver, UnicastSubject<T> unicastSubject) {
                this.parent = windowBoundaryMainObserver;
                this.window = unicastSubject;
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(this.upstream, disposable);
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onNext(V v) {
                if (DisposableHelper.dispose(this.upstream)) {
                    this.parent.close(this);
                }
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onError(Throwable th) {
                if (isDisposed()) {
                    RxJavaPlugins.onError(th);
                } else {
                    this.parent.closeError(th);
                }
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onComplete() {
                this.parent.close(this);
            }

            @Override // io.reactivex.rxjava3.disposables.Disposable
            public void dispose() {
                DisposableHelper.dispose(this.upstream);
            }

            @Override // io.reactivex.rxjava3.disposables.Disposable
            public boolean isDisposed() {
                return this.upstream.get() == DisposableHelper.DISPOSED;
            }

            @Override // io.reactivex.rxjava3.core.Observable
            public void subscribeActual(Observer<? super T> observer) {
                this.window.subscribe(observer);
                this.once.set(true);
            }

            boolean tryAbandon() {
                return !this.once.get() && this.once.compareAndSet(false, true);
            }
        }
    }
}
