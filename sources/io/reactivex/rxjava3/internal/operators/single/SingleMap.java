package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import java.util.Objects;

/* loaded from: classes.dex */
public final class SingleMap<T, R> extends Single<R> {
    final Function<? super T, ? extends R> mapper;
    final SingleSource<? extends T> source;

    public SingleMap(SingleSource<? extends T> singleSource, Function<? super T, ? extends R> function) {
        this.source = singleSource;
        this.mapper = function;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super R> singleObserver) {
        this.source.subscribe(new MapSingleObserver(singleObserver, this.mapper));
    }

    /* loaded from: classes10.dex */
    static final class MapSingleObserver<T, R> implements SingleObserver<T> {
        final Function<? super T, ? extends R> mapper;
        final SingleObserver<? super R> t;

        MapSingleObserver(SingleObserver<? super R> singleObserver, Function<? super T, ? extends R> function) {
            this.t = singleObserver;
            this.mapper = function;
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            this.t.onSubscribe(disposable);
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onSuccess(T t) {
            try {
                this.t.onSuccess(Objects.requireNonNull(this.mapper.apply(t), "The mapper function returned a null value."));
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onError(Throwable th) {
            this.t.onError(th);
        }
    }
}
