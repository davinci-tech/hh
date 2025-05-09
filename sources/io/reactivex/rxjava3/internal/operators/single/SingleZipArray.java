package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.operators.single.SingleMap;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class SingleZipArray<T, R> extends Single<R> {
    final SingleSource<? extends T>[] sources;
    final Function<? super Object[], ? extends R> zipper;

    public SingleZipArray(SingleSource<? extends T>[] singleSourceArr, Function<? super Object[], ? extends R> function) {
        this.sources = singleSourceArr;
        this.zipper = function;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super R> singleObserver) {
        SingleSource<? extends T>[] singleSourceArr = this.sources;
        int length = singleSourceArr.length;
        if (length == 1) {
            singleSourceArr[0].subscribe(new SingleMap.MapSingleObserver(singleObserver, new SingletonArrayFunc()));
            return;
        }
        ZipCoordinator zipCoordinator = new ZipCoordinator(singleObserver, length, this.zipper);
        singleObserver.onSubscribe(zipCoordinator);
        for (int i = 0; i < length && !zipCoordinator.isDisposed(); i++) {
            SingleSource<? extends T> singleSource = singleSourceArr[i];
            if (singleSource == null) {
                zipCoordinator.innerError(new NullPointerException("One of the sources is null"), i);
                return;
            }
            singleSource.subscribe(zipCoordinator.observers[i]);
        }
    }

    /* loaded from: classes10.dex */
    static final class ZipCoordinator<T, R> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = -5556924161382950569L;
        final SingleObserver<? super R> downstream;
        final ZipSingleObserver<T>[] observers;
        Object[] values;
        final Function<? super Object[], ? extends R> zipper;

        ZipCoordinator(SingleObserver<? super R> singleObserver, int i, Function<? super Object[], ? extends R> function) {
            super(i);
            this.downstream = singleObserver;
            this.zipper = function;
            ZipSingleObserver<T>[] zipSingleObserverArr = new ZipSingleObserver[i];
            for (int i2 = 0; i2 < i; i2++) {
                zipSingleObserverArr[i2] = new ZipSingleObserver<>(this, i2);
            }
            this.observers = zipSingleObserverArr;
            this.values = new Object[i];
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return get() <= 0;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            if (getAndSet(0) > 0) {
                for (ZipSingleObserver<T> zipSingleObserver : this.observers) {
                    zipSingleObserver.dispose();
                }
                this.values = null;
            }
        }

        void innerSuccess(T t, int i) {
            Object[] objArr = this.values;
            if (objArr != null) {
                objArr[i] = t;
            }
            if (decrementAndGet() == 0) {
                try {
                    Object requireNonNull = Objects.requireNonNull(this.zipper.apply(objArr), "The zipper returned a null value");
                    this.values = null;
                    this.downstream.onSuccess(requireNonNull);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.values = null;
                    this.downstream.onError(th);
                }
            }
        }

        void disposeExcept(int i) {
            ZipSingleObserver<T>[] zipSingleObserverArr = this.observers;
            int length = zipSingleObserverArr.length;
            for (int i2 = 0; i2 < i; i2++) {
                zipSingleObserverArr[i2].dispose();
            }
            while (true) {
                i++;
                if (i >= length) {
                    return;
                } else {
                    zipSingleObserverArr[i].dispose();
                }
            }
        }

        void innerError(Throwable th, int i) {
            if (getAndSet(0) > 0) {
                disposeExcept(i);
                this.values = null;
                this.downstream.onError(th);
                return;
            }
            RxJavaPlugins.onError(th);
        }
    }

    /* loaded from: classes10.dex */
    static final class ZipSingleObserver<T> extends AtomicReference<Disposable> implements SingleObserver<T> {
        private static final long serialVersionUID = 3323743579927613702L;
        final int index;
        final ZipCoordinator<T, ?> parent;

        ZipSingleObserver(ZipCoordinator<T, ?> zipCoordinator, int i) {
            this.parent = zipCoordinator;
            this.index = i;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onSuccess(T t) {
            this.parent.innerSuccess(t, this.index);
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onError(Throwable th) {
            this.parent.innerError(th, this.index);
        }
    }

    /* loaded from: classes10.dex */
    final class SingletonArrayFunc implements Function<T, R> {
        SingletonArrayFunc() {
        }

        @Override // io.reactivex.rxjava3.functions.Function
        public R apply(T t) throws Throwable {
            return (R) Objects.requireNonNull(SingleZipArray.this.zipper.apply(new Object[]{t}), "The zipper returned a null value");
        }
    }
}
