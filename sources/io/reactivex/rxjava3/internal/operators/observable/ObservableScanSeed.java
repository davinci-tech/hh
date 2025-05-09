package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;

/* loaded from: classes.dex */
public final class ObservableScanSeed<T, R> extends AbstractObservableWithUpstream<T, R> {
    final BiFunction<R, ? super T, R> accumulator;
    final Supplier<R> seedSupplier;

    public ObservableScanSeed(ObservableSource<T> observableSource, Supplier<R> supplier, BiFunction<R, ? super T, R> biFunction) {
        super(observableSource);
        this.accumulator = biFunction;
        this.seedSupplier = supplier;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super R> observer) {
        try {
            this.source.subscribe(new ScanSeedObserver(observer, this.accumulator, Objects.requireNonNull(this.seedSupplier.get(), "The seed supplied is null")));
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptyDisposable.error(th, observer);
        }
    }

    /* loaded from: classes10.dex */
    static final class ScanSeedObserver<T, R> implements Observer<T>, Disposable {
        final BiFunction<R, ? super T, R> accumulator;
        boolean done;
        final Observer<? super R> downstream;
        Disposable upstream;
        R value;

        ScanSeedObserver(Observer<? super R> observer, BiFunction<R, ? super T, R> biFunction, R r) {
            this.downstream = observer;
            this.accumulator = biFunction;
            this.value = r;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.downstream.onSubscribe(this);
                this.downstream.onNext(this.value);
            }
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.upstream.dispose();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            try {
                R r = (R) Objects.requireNonNull(this.accumulator.apply(this.value, t), "The accumulator returned a null value");
                this.value = r;
                this.downstream.onNext(r);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.upstream.dispose();
                onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
            } else {
                this.done = true;
                this.downstream.onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            this.downstream.onComplete();
        }
    }
}
