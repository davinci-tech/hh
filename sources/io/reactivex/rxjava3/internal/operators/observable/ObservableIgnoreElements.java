package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/* loaded from: classes.dex */
public final class ObservableIgnoreElements<T> extends AbstractObservableWithUpstream<T, T> {
    public ObservableIgnoreElements(ObservableSource<T> observableSource) {
        super(observableSource);
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new IgnoreObservable(observer));
    }

    /* loaded from: classes10.dex */
    static final class IgnoreObservable<T> implements Observer<T>, Disposable {
        final Observer<? super T> downstream;
        Disposable upstream;

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
        }

        IgnoreObservable(Observer<? super T> observer) {
            this.downstream = observer;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            this.upstream = disposable;
            this.downstream.onSubscribe(this);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            this.downstream.onComplete();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.upstream.dispose();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }
    }
}
