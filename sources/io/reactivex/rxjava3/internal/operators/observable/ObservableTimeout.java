package io.reactivex.rxjava3.internal.operators.observable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;
import io.reactivex.rxjava3.internal.operators.observable.ObservableTimeoutTimed;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class ObservableTimeout<T, U, V> extends AbstractObservableWithUpstream<T, T> {
    final ObservableSource<U> firstTimeoutIndicator;
    final Function<? super T, ? extends ObservableSource<V>> itemTimeoutIndicator;
    final ObservableSource<? extends T> other;

    /* loaded from: classes10.dex */
    interface TimeoutSelectorSupport extends ObservableTimeoutTimed.TimeoutSupport {
        void onTimeoutError(long j, Throwable th);
    }

    public ObservableTimeout(Observable<T> observable, ObservableSource<U> observableSource, Function<? super T, ? extends ObservableSource<V>> function, ObservableSource<? extends T> observableSource2) {
        super(observable);
        this.firstTimeoutIndicator = observableSource;
        this.itemTimeoutIndicator = function;
        this.other = observableSource2;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super T> observer) {
        if (this.other == null) {
            TimeoutObserver timeoutObserver = new TimeoutObserver(observer, this.itemTimeoutIndicator);
            observer.onSubscribe(timeoutObserver);
            timeoutObserver.startFirstTimeout(this.firstTimeoutIndicator);
            this.source.subscribe(timeoutObserver);
            return;
        }
        TimeoutFallbackObserver timeoutFallbackObserver = new TimeoutFallbackObserver(observer, this.itemTimeoutIndicator, this.other);
        observer.onSubscribe(timeoutFallbackObserver);
        timeoutFallbackObserver.startFirstTimeout(this.firstTimeoutIndicator);
        this.source.subscribe(timeoutFallbackObserver);
    }

    /* loaded from: classes10.dex */
    static final class TimeoutObserver<T> extends AtomicLong implements Observer<T>, Disposable, TimeoutSelectorSupport {
        private static final long serialVersionUID = 3764492702657003550L;
        final Observer<? super T> downstream;
        final Function<? super T, ? extends ObservableSource<?>> itemTimeoutIndicator;
        final SequentialDisposable task = new SequentialDisposable();
        final AtomicReference<Disposable> upstream = new AtomicReference<>();

        TimeoutObserver(Observer<? super T> observer, Function<? super T, ? extends ObservableSource<?>> function) {
            this.downstream = observer;
            this.itemTimeoutIndicator = function;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this.upstream, disposable);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            long j = get();
            if (j != LocationRequestCompat.PASSIVE_INTERVAL) {
                long j2 = 1 + j;
                if (compareAndSet(j, j2)) {
                    Disposable disposable = this.task.get();
                    if (disposable != null) {
                        disposable.dispose();
                    }
                    this.downstream.onNext(t);
                    try {
                        ObservableSource observableSource = (ObservableSource) Objects.requireNonNull(this.itemTimeoutIndicator.apply(t), "The itemTimeoutIndicator returned a null ObservableSource.");
                        TimeoutConsumer timeoutConsumer = new TimeoutConsumer(j2, this);
                        if (this.task.replace(timeoutConsumer)) {
                            observableSource.subscribe(timeoutConsumer);
                        }
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        this.upstream.get().dispose();
                        getAndSet(LocationRequestCompat.PASSIVE_INTERVAL);
                        this.downstream.onError(th);
                    }
                }
            }
        }

        void startFirstTimeout(ObservableSource<?> observableSource) {
            if (observableSource != null) {
                TimeoutConsumer timeoutConsumer = new TimeoutConsumer(0L, this);
                if (this.task.replace(timeoutConsumer)) {
                    observableSource.subscribe(timeoutConsumer);
                }
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            if (getAndSet(LocationRequestCompat.PASSIVE_INTERVAL) != LocationRequestCompat.PASSIVE_INTERVAL) {
                this.task.dispose();
                this.downstream.onError(th);
            } else {
                RxJavaPlugins.onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            if (getAndSet(LocationRequestCompat.PASSIVE_INTERVAL) != LocationRequestCompat.PASSIVE_INTERVAL) {
                this.task.dispose();
                this.downstream.onComplete();
            }
        }

        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableTimeoutTimed.TimeoutSupport
        public void onTimeout(long j) {
            if (compareAndSet(j, LocationRequestCompat.PASSIVE_INTERVAL)) {
                DisposableHelper.dispose(this.upstream);
                this.downstream.onError(new TimeoutException());
            }
        }

        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableTimeout.TimeoutSelectorSupport
        public void onTimeoutError(long j, Throwable th) {
            if (compareAndSet(j, LocationRequestCompat.PASSIVE_INTERVAL)) {
                DisposableHelper.dispose(this.upstream);
                this.downstream.onError(th);
            } else {
                RxJavaPlugins.onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            DisposableHelper.dispose(this.upstream);
            this.task.dispose();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.upstream.get());
        }
    }

    /* loaded from: classes10.dex */
    static final class TimeoutFallbackObserver<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable, TimeoutSelectorSupport {
        private static final long serialVersionUID = -7508389464265974549L;
        final Observer<? super T> downstream;
        ObservableSource<? extends T> fallback;
        final Function<? super T, ? extends ObservableSource<?>> itemTimeoutIndicator;
        final SequentialDisposable task = new SequentialDisposable();
        final AtomicLong index = new AtomicLong();
        final AtomicReference<Disposable> upstream = new AtomicReference<>();

        TimeoutFallbackObserver(Observer<? super T> observer, Function<? super T, ? extends ObservableSource<?>> function, ObservableSource<? extends T> observableSource) {
            this.downstream = observer;
            this.itemTimeoutIndicator = function;
            this.fallback = observableSource;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this.upstream, disposable);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            long j = this.index.get();
            if (j != LocationRequestCompat.PASSIVE_INTERVAL) {
                long j2 = 1 + j;
                if (this.index.compareAndSet(j, j2)) {
                    Disposable disposable = this.task.get();
                    if (disposable != null) {
                        disposable.dispose();
                    }
                    this.downstream.onNext(t);
                    try {
                        ObservableSource observableSource = (ObservableSource) Objects.requireNonNull(this.itemTimeoutIndicator.apply(t), "The itemTimeoutIndicator returned a null ObservableSource.");
                        TimeoutConsumer timeoutConsumer = new TimeoutConsumer(j2, this);
                        if (this.task.replace(timeoutConsumer)) {
                            observableSource.subscribe(timeoutConsumer);
                        }
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        this.upstream.get().dispose();
                        this.index.getAndSet(LocationRequestCompat.PASSIVE_INTERVAL);
                        this.downstream.onError(th);
                    }
                }
            }
        }

        void startFirstTimeout(ObservableSource<?> observableSource) {
            if (observableSource != null) {
                TimeoutConsumer timeoutConsumer = new TimeoutConsumer(0L, this);
                if (this.task.replace(timeoutConsumer)) {
                    observableSource.subscribe(timeoutConsumer);
                }
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            if (this.index.getAndSet(LocationRequestCompat.PASSIVE_INTERVAL) != LocationRequestCompat.PASSIVE_INTERVAL) {
                this.task.dispose();
                this.downstream.onError(th);
                this.task.dispose();
                return;
            }
            RxJavaPlugins.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            if (this.index.getAndSet(LocationRequestCompat.PASSIVE_INTERVAL) != LocationRequestCompat.PASSIVE_INTERVAL) {
                this.task.dispose();
                this.downstream.onComplete();
                this.task.dispose();
            }
        }

        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableTimeoutTimed.TimeoutSupport
        public void onTimeout(long j) {
            if (this.index.compareAndSet(j, LocationRequestCompat.PASSIVE_INTERVAL)) {
                DisposableHelper.dispose(this.upstream);
                ObservableSource<? extends T> observableSource = this.fallback;
                this.fallback = null;
                observableSource.subscribe(new ObservableTimeoutTimed.FallbackObserver(this.downstream, this));
            }
        }

        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableTimeout.TimeoutSelectorSupport
        public void onTimeoutError(long j, Throwable th) {
            if (this.index.compareAndSet(j, LocationRequestCompat.PASSIVE_INTERVAL)) {
                DisposableHelper.dispose(this);
                this.downstream.onError(th);
            } else {
                RxJavaPlugins.onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            DisposableHelper.dispose(this.upstream);
            DisposableHelper.dispose(this);
            this.task.dispose();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }
    }

    /* loaded from: classes10.dex */
    static final class TimeoutConsumer extends AtomicReference<Disposable> implements Observer<Object>, Disposable {
        private static final long serialVersionUID = 8708641127342403073L;
        final long idx;
        final TimeoutSelectorSupport parent;

        TimeoutConsumer(long j, TimeoutSelectorSupport timeoutSelectorSupport) {
            this.idx = j;
            this.parent = timeoutSelectorSupport;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(Object obj) {
            Disposable disposable = (Disposable) get();
            if (disposable != DisposableHelper.DISPOSED) {
                disposable.dispose();
                lazySet(DisposableHelper.DISPOSED);
                this.parent.onTimeout(this.idx);
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            if (get() != DisposableHelper.DISPOSED) {
                lazySet(DisposableHelper.DISPOSED);
                this.parent.onTimeoutError(this.idx, th);
            } else {
                RxJavaPlugins.onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            if (get() != DisposableHelper.DISPOSED) {
                lazySet(DisposableHelper.DISPOSED);
                this.parent.onTimeout(this.idx);
            }
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }
    }
}
