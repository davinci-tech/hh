package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.ArrayDeque;

/* loaded from: classes.dex */
public final class ObservableSkipLast<T> extends AbstractObservableWithUpstream<T, T> {
    final int skip;

    public ObservableSkipLast(ObservableSource<T> observableSource, int i) {
        super(observableSource);
        this.skip = i;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new SkipLastObserver(observer, this.skip));
    }

    /* loaded from: classes10.dex */
    static final class SkipLastObserver<T> extends ArrayDeque<T> implements Observer<T>, Disposable {
        private static final long serialVersionUID = -3807491841935125653L;
        final Observer<? super T> downstream;
        final int skip;
        Disposable upstream;

        SkipLastObserver(Observer<? super T> observer, int i) {
            super(i);
            this.downstream = observer;
            this.skip = i;
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
        public void onNext(T t) {
            if (this.skip == size()) {
                this.downstream.onNext(poll());
            }
            offer(t);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            this.downstream.onComplete();
        }
    }
}
