package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public final class SingleAmb<T> extends Single<T> {
    private final SingleSource<? extends T>[] sources;
    private final Iterable<? extends SingleSource<? extends T>> sourcesIterable;

    public SingleAmb(SingleSource<? extends T>[] singleSourceArr, Iterable<? extends SingleSource<? extends T>> iterable) {
        this.sources = singleSourceArr;
        this.sourcesIterable = iterable;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        int length;
        SingleSource<? extends T>[] singleSourceArr = this.sources;
        if (singleSourceArr == null) {
            singleSourceArr = new SingleSource[8];
            try {
                length = 0;
                for (SingleSource<? extends T> singleSource : this.sourcesIterable) {
                    if (singleSource == null) {
                        EmptyDisposable.error(new NullPointerException("One of the sources is null"), singleObserver);
                        return;
                    }
                    if (length == singleSourceArr.length) {
                        SingleSource<? extends T>[] singleSourceArr2 = new SingleSource[(length >> 2) + length];
                        System.arraycopy(singleSourceArr, 0, singleSourceArr2, 0, length);
                        singleSourceArr = singleSourceArr2;
                    }
                    singleSourceArr[length] = singleSource;
                    length++;
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                EmptyDisposable.error(th, singleObserver);
                return;
            }
        } else {
            length = singleSourceArr.length;
        }
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        singleObserver.onSubscribe(compositeDisposable);
        for (int i = 0; i < length; i++) {
            SingleSource<? extends T> singleSource2 = singleSourceArr[i];
            if (compositeDisposable.isDisposed()) {
                return;
            }
            if (singleSource2 == null) {
                compositeDisposable.dispose();
                NullPointerException nullPointerException = new NullPointerException("One of the sources is null");
                if (atomicBoolean.compareAndSet(false, true)) {
                    singleObserver.onError(nullPointerException);
                    return;
                } else {
                    RxJavaPlugins.onError(nullPointerException);
                    return;
                }
            }
            singleSource2.subscribe(new AmbSingleObserver(singleObserver, compositeDisposable, atomicBoolean));
        }
    }

    /* loaded from: classes10.dex */
    static final class AmbSingleObserver<T> implements SingleObserver<T> {
        final SingleObserver<? super T> downstream;
        final CompositeDisposable set;
        Disposable upstream;
        final AtomicBoolean winner;

        AmbSingleObserver(SingleObserver<? super T> singleObserver, CompositeDisposable compositeDisposable, AtomicBoolean atomicBoolean) {
            this.downstream = singleObserver;
            this.set = compositeDisposable;
            this.winner = atomicBoolean;
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            this.upstream = disposable;
            this.set.add(disposable);
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onSuccess(T t) {
            if (this.winner.compareAndSet(false, true)) {
                this.set.delete(this.upstream);
                this.set.dispose();
                this.downstream.onSuccess(t);
            }
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onError(Throwable th) {
            if (this.winner.compareAndSet(false, true)) {
                this.set.delete(this.upstream);
                this.set.dispose();
                this.downstream.onError(th);
                return;
            }
            RxJavaPlugins.onError(th);
        }
    }
}
