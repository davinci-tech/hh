package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;

/* loaded from: classes.dex */
public final class ObservableSkip<T> extends AbstractObservableWithUpstream<T, T> {
    final long n;

    public ObservableSkip(ObservableSource<T> observableSource, long j) {
        super(observableSource);
        this.n = j;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new SkipObserver(observer, this.n));
    }

    /* loaded from: classes10.dex */
    static final class SkipObserver<T> implements Observer<T>, Disposable {
        final Observer<? super T> downstream;
        long remaining;
        Disposable upstream;

        SkipObserver(Observer<? super T> observer, long j) {
            this.downstream = observer;
            this.remaining = j;
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
            long j = this.remaining;
            if (j != 0) {
                this.remaining = j - 1;
            } else {
                this.downstream.onNext(t);
            }
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
