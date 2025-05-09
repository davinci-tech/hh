package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.fuseable.FuseToObservable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collector;

/* loaded from: classes.dex */
public final class ObservableCollectWithCollectorSingle<T, A, R> extends Single<R> implements FuseToObservable<R> {
    final Collector<? super T, A, R> collector;
    final Observable<T> source;

    public ObservableCollectWithCollectorSingle(Observable<T> observable, Collector<? super T, A, R> collector) {
        this.source = observable;
        this.collector = collector;
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.FuseToObservable
    public Observable<R> fuseToObservable() {
        return new ObservableCollectWithCollector(this.source, this.collector);
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super R> singleObserver) {
        try {
            this.source.subscribe(new CollectorSingleObserver(singleObserver, this.collector.supplier().get(), this.collector.accumulator(), this.collector.finisher()));
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptyDisposable.error(th, singleObserver);
        }
    }

    /* loaded from: classes10.dex */
    static final class CollectorSingleObserver<T, A, R> implements Observer<T>, Disposable {
        final BiConsumer<A, T> accumulator;
        A container;
        boolean done;
        final SingleObserver<? super R> downstream;
        final Function<A, R> finisher;
        Disposable upstream;

        CollectorSingleObserver(SingleObserver<? super R> singleObserver, A a2, BiConsumer<A, T> biConsumer, Function<A, R> function) {
            this.downstream = singleObserver;
            this.container = a2;
            this.accumulator = biConsumer;
            this.finisher = function;
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
            try {
                this.accumulator.accept(this.container, t);
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
                return;
            }
            this.done = true;
            this.upstream = DisposableHelper.DISPOSED;
            this.container = null;
            this.downstream.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            this.upstream = DisposableHelper.DISPOSED;
            A a2 = this.container;
            this.container = null;
            try {
                this.downstream.onSuccess(Objects.requireNonNull(this.finisher.apply(a2), "The finisher returned a null value"));
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.downstream.onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.upstream.dispose();
            this.upstream = DisposableHelper.DISPOSED;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.upstream == DisposableHelper.DISPOSED;
        }
    }
}
