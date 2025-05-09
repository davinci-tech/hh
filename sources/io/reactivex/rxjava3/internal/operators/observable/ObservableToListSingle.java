package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.functions.Functions;
import io.reactivex.rxjava3.internal.fuseable.FuseToObservable;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Collection;

/* loaded from: classes.dex */
public final class ObservableToListSingle<T, U extends Collection<? super T>> extends Single<U> implements FuseToObservable<U> {
    final Supplier<U> collectionSupplier;
    final ObservableSource<T> source;

    public ObservableToListSingle(ObservableSource<T> observableSource, int i) {
        this.source = observableSource;
        this.collectionSupplier = Functions.createArrayList(i);
    }

    public ObservableToListSingle(ObservableSource<T> observableSource, Supplier<U> supplier) {
        this.source = observableSource;
        this.collectionSupplier = supplier;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super U> singleObserver) {
        try {
            this.source.subscribe(new ToListObserver(singleObserver, (Collection) ExceptionHelper.nullCheck(this.collectionSupplier.get(), "The collectionSupplier returned a null Collection.")));
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptyDisposable.error(th, singleObserver);
        }
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.FuseToObservable
    public Observable<U> fuseToObservable() {
        return RxJavaPlugins.onAssembly(new ObservableToList(this.source, this.collectionSupplier));
    }

    /* loaded from: classes10.dex */
    static final class ToListObserver<T, U extends Collection<? super T>> implements Observer<T>, Disposable {
        U collection;
        final SingleObserver<? super U> downstream;
        Disposable upstream;

        ToListObserver(SingleObserver<? super U> singleObserver, U u) {
            this.downstream = singleObserver;
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
            this.downstream.onSuccess(u);
        }
    }
}
