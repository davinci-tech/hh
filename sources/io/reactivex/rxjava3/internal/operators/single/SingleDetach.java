package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;

/* loaded from: classes.dex */
public final class SingleDetach<T> extends Single<T> {
    final SingleSource<T> source;

    public SingleDetach(SingleSource<T> singleSource) {
        this.source = singleSource;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        this.source.subscribe(new DetachSingleObserver(singleObserver));
    }

    /* loaded from: classes10.dex */
    static final class DetachSingleObserver<T> implements SingleObserver<T>, Disposable {
        SingleObserver<? super T> downstream;
        Disposable upstream;

        DetachSingleObserver(SingleObserver<? super T> singleObserver) {
            this.downstream = singleObserver;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.downstream = null;
            this.upstream.dispose();
            this.upstream = DisposableHelper.DISPOSED;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onSuccess(T t) {
            this.upstream = DisposableHelper.DISPOSED;
            SingleObserver<? super T> singleObserver = this.downstream;
            if (singleObserver != null) {
                this.downstream = null;
                singleObserver.onSuccess(t);
            }
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onError(Throwable th) {
            this.upstream = DisposableHelper.DISPOSED;
            SingleObserver<? super T> singleObserver = this.downstream;
            if (singleObserver != null) {
                this.downstream = null;
                singleObserver.onError(th);
            }
        }
    }
}
