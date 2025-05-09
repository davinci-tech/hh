package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class CompletableAndThenObservable<R> extends Observable<R> {
    final ObservableSource<? extends R> other;
    final CompletableSource source;

    public CompletableAndThenObservable(CompletableSource completableSource, ObservableSource<? extends R> observableSource) {
        this.source = completableSource;
        this.other = observableSource;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super R> observer) {
        AndThenObservableObserver andThenObservableObserver = new AndThenObservableObserver(observer, this.other);
        observer.onSubscribe(andThenObservableObserver);
        this.source.subscribe(andThenObservableObserver);
    }

    /* loaded from: classes10.dex */
    static final class AndThenObservableObserver<R> extends AtomicReference<Disposable> implements Observer<R>, CompletableObserver, Disposable {
        private static final long serialVersionUID = -8948264376121066672L;
        final Observer<? super R> downstream;
        ObservableSource<? extends R> other;

        AndThenObservableObserver(Observer<? super R> observer, ObservableSource<? extends R> observableSource) {
            this.other = observableSource;
            this.downstream = observer;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(R r) {
            this.downstream.onNext(r);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            ObservableSource<? extends R> observableSource = this.other;
            if (observableSource == null) {
                this.downstream.onComplete();
            } else {
                this.other = null;
                observableSource.subscribe(this);
            }
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            DisposableHelper.replace(this, disposable);
        }
    }
}
