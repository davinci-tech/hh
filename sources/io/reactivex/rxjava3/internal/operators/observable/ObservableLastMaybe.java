package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;

/* loaded from: classes.dex */
public final class ObservableLastMaybe<T> extends Maybe<T> {
    final ObservableSource<T> source;

    public ObservableLastMaybe(ObservableSource<T> observableSource) {
        this.source = observableSource;
    }

    @Override // io.reactivex.rxjava3.core.Maybe
    public void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        this.source.subscribe(new LastObserver(maybeObserver));
    }

    /* loaded from: classes10.dex */
    static final class LastObserver<T> implements Observer<T>, Disposable {
        final MaybeObserver<? super T> downstream;
        T item;
        Disposable upstream;

        LastObserver(MaybeObserver<? super T> maybeObserver) {
            this.downstream = maybeObserver;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.upstream.dispose();
            this.upstream = DisposableHelper.DISPOSED;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.upstream == DisposableHelper.DISPOSED;
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
            this.item = t;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            this.upstream = DisposableHelper.DISPOSED;
            this.item = null;
            this.downstream.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            this.upstream = DisposableHelper.DISPOSED;
            T t = this.item;
            if (t != null) {
                this.item = null;
                this.downstream.onSuccess(t);
            } else {
                this.downstream.onComplete();
            }
        }
    }
}
