package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;

/* loaded from: classes10.dex */
public final class ObservableCount<T> extends AbstractObservableWithUpstream<T, Long> {
    public ObservableCount(ObservableSource<T> observableSource) {
        super(observableSource);
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super Long> observer) {
        this.source.subscribe(new CountObserver(observer));
    }

    static final class CountObserver implements Observer<Object>, Disposable {
        long count;
        final Observer<? super Long> downstream;
        Disposable upstream;

        CountObserver(Observer<? super Long> observer) {
            this.downstream = observer;
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
        public void onNext(Object obj) {
            this.count++;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            this.downstream.onNext(Long.valueOf(this.count));
            this.downstream.onComplete();
        }
    }
}
