package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.observers.DeferredScalarDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collector;

/* loaded from: classes10.dex */
public final class ObservableCollectWithCollector<T, A, R> extends Observable<R> {
    final Collector<? super T, A, R> collector;
    final Observable<T> source;

    public ObservableCollectWithCollector(Observable<T> observable, Collector<? super T, A, R> collector) {
        this.source = observable;
        this.collector = collector;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super R> observer) {
        try {
            this.source.subscribe(new CollectorObserver(observer, this.collector.supplier().get(), this.collector.accumulator(), this.collector.finisher()));
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptyDisposable.error(th, observer);
        }
    }

    static final class CollectorObserver<T, A, R> extends DeferredScalarDisposable<R> implements Observer<T> {
        private static final long serialVersionUID = -229544830565448758L;
        final BiConsumer<A, T> accumulator;
        A container;
        boolean done;
        final Function<A, R> finisher;
        Disposable upstream;

        CollectorObserver(Observer<? super R> observer, A a2, BiConsumer<A, T> biConsumer, Function<A, R> function) {
            super(observer);
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

        /* JADX WARN: Multi-variable type inference failed */
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
                complete(Objects.requireNonNull(this.finisher.apply(a2), "The finisher returned a null value"));
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.downstream.onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.internal.observers.DeferredScalarDisposable, io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            super.dispose();
            this.upstream.dispose();
        }
    }
}
