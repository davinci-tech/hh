package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import java.util.Collection;

/* loaded from: classes10.dex */
public final class ObservableToList<T, U extends Collection<? super T>> extends AbstractObservableWithUpstream<T, U> {
    final Supplier<U> collectionSupplier;

    public ObservableToList(ObservableSource<T> observableSource, Supplier<U> supplier) {
        super(observableSource);
        this.collectionSupplier = supplier;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super U> observer) {
        try {
            this.source.subscribe(new ToListObserver(observer, (Collection) ExceptionHelper.nullCheck(this.collectionSupplier.get(), "The collectionSupplier returned a null Collection.")));
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptyDisposable.error(th, observer);
        }
    }

    static final class ToListObserver<T, U extends Collection<? super T>> implements Observer<T>, Disposable {
        U collection;
        final Observer<? super U> downstream;
        Disposable upstream;

        ToListObserver(Observer<? super U> observer, U u) {
            this.downstream = observer;
            this.collection = u;
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
            this.collection.add(t);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            this.collection = null;
            this.downstream.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            U u = this.collection;
            this.collection = null;
            this.downstream.onNext(u);
            this.downstream.onComplete();
        }
    }
}
