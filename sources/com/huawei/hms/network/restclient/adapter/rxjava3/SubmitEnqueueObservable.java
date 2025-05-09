package com.huawei.hms.network.restclient.adapter.rxjava3;

import com.huawei.hms.network.httpclient.Callback;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.Submit;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;

/* loaded from: classes9.dex */
final class SubmitEnqueueObservable<T> extends Observable<Response<T>> {
    private final Submit<T> originalSubmit;

    SubmitEnqueueObservable(Submit<T> submit) {
        this.originalSubmit = submit;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super Response<T>> observer) {
        Submit<T> mo631clone = this.originalSubmit.mo631clone();
        SubmitCallback submitCallback = new SubmitCallback(mo631clone, observer);
        observer.onSubscribe(submitCallback);
        if (submitCallback.isDisposed()) {
            return;
        }
        mo631clone.enqueue(submitCallback);
    }

    static final class SubmitCallback<T> extends Callback<T> implements Disposable {
        private volatile boolean disposed;
        private final Observer<? super Response<T>> observer;
        private final Submit<?> submit;
        boolean terminated = false;

        SubmitCallback(Submit<?> submit, Observer<? super Response<T>> observer) {
            this.submit = submit;
            this.observer = observer;
        }

        @Override // com.huawei.hms.network.httpclient.Callback
        public void onResponse(Submit<T> submit, Response<T> response) {
            if (this.disposed) {
                return;
            }
            try {
                this.observer.onNext(response);
                if (this.disposed) {
                    return;
                }
                this.terminated = true;
                this.observer.onComplete();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                if (this.terminated) {
                    RxJavaPlugins.onError(th);
                    return;
                }
                if (this.disposed) {
                    return;
                }
                try {
                    this.observer.onError(th);
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    RxJavaPlugins.onError(new CompositeException(th, th2));
                }
            }
        }

        @Override // com.huawei.hms.network.httpclient.Callback
        public void onFailure(Submit<T> submit, Throwable th) {
            if (submit.isCanceled()) {
                return;
            }
            try {
                this.observer.onError(th);
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                RxJavaPlugins.onError(new CompositeException(th, th2));
            }
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.disposed = true;
            this.submit.cancel();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.disposed;
        }
    }
}
