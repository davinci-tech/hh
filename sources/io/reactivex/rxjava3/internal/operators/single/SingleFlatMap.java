package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class SingleFlatMap<T, R> extends Single<R> {
    final Function<? super T, ? extends SingleSource<? extends R>> mapper;
    final SingleSource<? extends T> source;

    public SingleFlatMap(SingleSource<? extends T> singleSource, Function<? super T, ? extends SingleSource<? extends R>> function) {
        this.mapper = function;
        this.source = singleSource;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super R> singleObserver) {
        this.source.subscribe(new SingleFlatMapCallback(singleObserver, this.mapper));
    }

    /* loaded from: classes10.dex */
    static final class SingleFlatMapCallback<T, R> extends AtomicReference<Disposable> implements SingleObserver<T>, Disposable {
        private static final long serialVersionUID = 3258103020495908596L;
        final SingleObserver<? super R> downstream;
        final Function<? super T, ? extends SingleSource<? extends R>> mapper;

        SingleFlatMapCallback(SingleObserver<? super R> singleObserver, Function<? super T, ? extends SingleSource<? extends R>> function) {
            this.downstream = singleObserver;
            this.mapper = function;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.setOnce(this, disposable)) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onSuccess(T t) {
            try {
                SingleSource singleSource = (SingleSource) Objects.requireNonNull(this.mapper.apply(t), "The single returned by the mapper is null");
                if (isDisposed()) {
                    return;
                }
                singleSource.subscribe(new FlatMapSingleObserver(this, this.downstream));
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.downstream.onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        static final class FlatMapSingleObserver<R> implements SingleObserver<R> {
            final SingleObserver<? super R> downstream;
            final AtomicReference<Disposable> parent;

            FlatMapSingleObserver(AtomicReference<Disposable> atomicReference, SingleObserver<? super R> singleObserver) {
                this.parent = atomicReference;
                this.downstream = singleObserver;
            }

            @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
            public void onSubscribe(Disposable disposable) {
                DisposableHelper.replace(this.parent, disposable);
            }

            @Override // io.reactivex.rxjava3.core.SingleObserver
            public void onSuccess(R r) {
                this.downstream.onSuccess(r);
            }

            @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
            public void onError(Throwable th) {
                this.downstream.onError(th);
            }
        }
    }
}
