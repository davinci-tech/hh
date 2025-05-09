package com.huawei.hms.network.restclient.adapter.rxjava3;

import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.Submit;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;

/* loaded from: classes9.dex */
final class SubmitExecuteObservable<T> extends Observable<Response<T>> {
    private final Submit<T> originalSubmit;

    SubmitExecuteObservable(Submit<T> submit) {
        this.originalSubmit = submit;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super Response<T>> observer) {
        boolean z;
        Submit<T> mo631clone = this.originalSubmit.mo631clone();
        SubmitDisposable submitDisposable = new SubmitDisposable(mo631clone);
        observer.onSubscribe(submitDisposable);
        if (submitDisposable.isDisposed()) {
            return;
        }
        try {
            Response<T> execute = mo631clone.execute();
            if (!submitDisposable.isDisposed()) {
                observer.onNext(execute);
            }
            if (submitDisposable.isDisposed()) {
                return;
            }
            try {
                observer.onComplete();
            } catch (Throwable th) {
                th = th;
                z = true;
                Exceptions.throwIfFatal(th);
                if (z) {
                    RxJavaPlugins.onError(th);
                    return;
                }
                if (submitDisposable.isDisposed()) {
                    return;
                }
                try {
                    observer.onError(th);
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    RxJavaPlugins.onError(new CompositeException(th, th2));
                }
            }
        } catch (Throwable th3) {
            th = th3;
            z = false;
        }
    }

    static final class SubmitDisposable implements Disposable {
        private volatile boolean disposed;
        private final Submit<?> submit;

        SubmitDisposable(Submit<?> submit) {
            this.submit = submit;
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
