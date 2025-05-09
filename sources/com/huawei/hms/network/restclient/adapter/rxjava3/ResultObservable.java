package com.huawei.hms.network.restclient.adapter.rxjava3;

import com.huawei.hms.network.httpclient.Response;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;

/* loaded from: classes9.dex */
final class ResultObservable<T> extends Observable<Result<T>> {
    private final Observable<Response<T>> observable;

    ResultObservable(Observable<Response<T>> observable) {
        this.observable = observable;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super Result<T>> observer) {
        this.observable.subscribe(new ResultObserver(observer));
    }

    static class ResultObserver<R> implements Observer<Response<R>> {
        private final Observer<? super Result<R>> observerResult;

        ResultObserver(Observer<? super Result<R>> observer) {
            this.observerResult = observer;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            this.observerResult.onSubscribe(disposable);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(Response<R> response) {
            this.observerResult.onNext(Result.response(response));
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            try {
                this.observerResult.onNext(Result.error(th));
                this.observerResult.onComplete();
            } catch (Throwable th2) {
                try {
                    this.observerResult.onError(th2);
                } catch (Throwable th3) {
                    Exceptions.throwIfFatal(th3);
                    RxJavaPlugins.onError(new CompositeException(th2, th3));
                }
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            this.observerResult.onComplete();
        }
    }
}
