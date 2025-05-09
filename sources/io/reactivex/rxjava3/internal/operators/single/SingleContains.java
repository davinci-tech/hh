package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.BiPredicate;

/* loaded from: classes.dex */
public final class SingleContains<T> extends Single<Boolean> {
    final BiPredicate<Object, Object> comparer;
    final SingleSource<T> source;
    final Object value;

    public SingleContains(SingleSource<T> singleSource, Object obj, BiPredicate<Object, Object> biPredicate) {
        this.source = singleSource;
        this.value = obj;
        this.comparer = biPredicate;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super Boolean> singleObserver) {
        this.source.subscribe(new ContainsSingleObserver(singleObserver));
    }

    /* loaded from: classes10.dex */
    final class ContainsSingleObserver implements SingleObserver<T> {
        private final SingleObserver<? super Boolean> downstream;

        ContainsSingleObserver(SingleObserver<? super Boolean> singleObserver) {
            this.downstream = singleObserver;
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            this.downstream.onSubscribe(disposable);
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onSuccess(T t) {
            try {
                this.downstream.onSuccess(Boolean.valueOf(SingleContains.this.comparer.test(t, SingleContains.this.value)));
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.downstream.onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }
    }
}
