package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.BiConsumer;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;

/* loaded from: classes10.dex */
public final class ObservableCollect<T, U> extends AbstractObservableWithUpstream<T, U> {
    final BiConsumer<? super U, ? super T> collector;
    final Supplier<? extends U> initialSupplier;

    public ObservableCollect(ObservableSource<T> observableSource, Supplier<? extends U> supplier, BiConsumer<? super U, ? super T> biConsumer) {
        super(observableSource);
        this.initialSupplier = supplier;
        this.collector = biConsumer;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super U> observer) {
        try {
            this.source.subscribe(new CollectObserver(observer, Objects.requireNonNull(this.initialSupplier.get(), "The initialSupplier returned a null value"), this.collector));
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptyDisposable.error(th, observer);
        }
    }

    static final class CollectObserver<T, U> implements Observer<T>, Disposable {
        final BiConsumer<? super U, ? super T> collector;
        boolean done;
        final Observer<? super U> downstream;
        final U u;
        Disposable upstream;

        CollectObserver(Observer<? super U> observer, U u, BiConsumer<? super U, ? super T> biConsumer) {
            this.downstream = observer;
            this.collector = biConsumer;
            this.u = u;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.downstream.onSubscribe(this);
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
                this.collector.accept(this.u, t);
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
            this.downstream.onNext(this.u);
            this.downstream.onComplete();
        }
    }
}
