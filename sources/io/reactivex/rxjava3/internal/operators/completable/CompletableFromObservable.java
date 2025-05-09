package io.reactivex.rxjava3.internal.operators.completable;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/* loaded from: classes.dex */
public final class CompletableFromObservable<T> extends Completable {
    final ObservableSource<T> observable;

    public CompletableFromObservable(ObservableSource<T> observableSource) {
        this.observable = observableSource;
    }

    @Override // io.reactivex.rxjava3.core.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        this.observable.subscribe(new CompletableFromObservableObserver(completableObserver));
    }

    /* loaded from: classes10.dex */
    static final class CompletableFromObservableObserver<T> implements Observer<T> {
        final CompletableObserver co;

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
        }

        CompletableFromObservableObserver(CompletableObserver completableObserver) {
            this.co = completableObserver;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            this.co.onSubscribe(disposable);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            this.co.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            this.co.onComplete();
        }
    }
}
