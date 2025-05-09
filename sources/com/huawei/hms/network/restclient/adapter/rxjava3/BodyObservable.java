package com.huawei.hms.network.restclient.adapter.rxjava3;

import com.huawei.hms.network.httpclient.Response;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;

/* loaded from: classes9.dex */
final class BodyObservable<T> extends Observable<T> {
    private final Observable<Response<T>> observable;

    BodyObservable(Observable<Response<T>> observable) {
        this.observable = observable;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super T> observer) {
        this.observable.subscribe(new BodyObserver(observer));
    }

    static class BodyObserver<R> implements Observer<Response<R>> {
        private final Observer<? super R> observer;
        private boolean terminated;

        BodyObserver(Observer<? super R> observer) {
            this.observer = observer;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            this.observer.onSubscribe(disposable);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(Response<R> response) {
            if (response.isSuccessful()) {
                this.observer.onNext(response.getBody());
                return;
            }
            this.terminated = true;
            HttpException httpException = new HttpException(response);
            try {
                this.observer.onError(httpException);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.onError(new CompositeException(httpException, th));
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            if (this.terminated) {
                return;
            }
            this.observer.onComplete();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            if (!this.terminated) {
                this.observer.onError(th);
                return;
            }
            AssertionError assertionError = new AssertionError("This should never happen! Report as a bug with the full stacktrace.");
            assertionError.initCause(th);
            RxJavaPlugins.onError(assertionError);
        }
    }
}
