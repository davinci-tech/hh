package io.reactivex.rxjava3.internal.operators.observable;

import androidx.webkit.ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.observers.SerializedObserver;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class ObservableDebounce<T, U> extends AbstractObservableWithUpstream<T, T> {
    final Function<? super T, ? extends ObservableSource<U>> debounceSelector;

    public ObservableDebounce(ObservableSource<T> observableSource, Function<? super T, ? extends ObservableSource<U>> function) {
        super(observableSource);
        this.debounceSelector = function;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new DebounceObserver(new SerializedObserver(observer), this.debounceSelector));
    }

    /* loaded from: classes10.dex */
    static final class DebounceObserver<T, U> implements Observer<T>, Disposable {
        final Function<? super T, ? extends ObservableSource<U>> debounceSelector;
        final AtomicReference<Disposable> debouncer = new AtomicReference<>();
        boolean done;
        final Observer<? super T> downstream;
        volatile long index;
        Disposable upstream;

        DebounceObserver(Observer<? super T> observer, Function<? super T, ? extends ObservableSource<U>> function) {
            this.downstream = observer;
            this.debounceSelector = function;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            long j = this.index + 1;
            this.index = j;
            Disposable disposable = this.debouncer.get();
            if (disposable != null) {
                disposable.dispose();
            }
            try {
                ObservableSource observableSource = (ObservableSource) Objects.requireNonNull(this.debounceSelector.apply(t), "The ObservableSource supplied is null");
                DebounceInnerObserver debounceInnerObserver = new DebounceInnerObserver(this, j, t);
                if (ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(this.debouncer, disposable, debounceInnerObserver)) {
                    observableSource.subscribe(debounceInnerObserver);
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                dispose();
                this.downstream.onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            DisposableHelper.dispose(this.debouncer);
            this.downstream.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            Disposable disposable = this.debouncer.get();
            if (disposable != DisposableHelper.DISPOSED) {
                DebounceInnerObserver debounceInnerObserver = (DebounceInnerObserver) disposable;
                if (debounceInnerObserver != null) {
                    debounceInnerObserver.emit();
                }
                DisposableHelper.dispose(this.debouncer);
                this.downstream.onComplete();
            }
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.upstream.dispose();
            DisposableHelper.dispose(this.debouncer);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        void emit(long j, T t) {
            if (j == this.index) {
                this.downstream.onNext(t);
            }
        }

        static final class DebounceInnerObserver<T, U> extends DisposableObserver<U> {
            boolean done;
            final long index;
            final AtomicBoolean once = new AtomicBoolean();
            final DebounceObserver<T, U> parent;
            final T value;

            DebounceInnerObserver(DebounceObserver<T, U> debounceObserver, long j, T t) {
                this.parent = debounceObserver;
                this.index = j;
                this.value = t;
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onNext(U u) {
                if (this.done) {
                    return;
                }
                this.done = true;
                dispose();
                emit();
            }

            void emit() {
                if (this.once.compareAndSet(false, true)) {
                    this.parent.emit(this.index, this.value);
                }
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onError(Throwable th) {
                if (this.done) {
                    RxJavaPlugins.onError(th);
                } else {
                    this.done = true;
                    this.parent.onError(th);
                }
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onComplete() {
                if (this.done) {
                    return;
                }
                this.done = true;
                emit();
            }
        }
    }
}
