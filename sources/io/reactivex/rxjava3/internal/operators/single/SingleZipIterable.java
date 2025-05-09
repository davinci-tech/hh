package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.operators.single.SingleMap;
import io.reactivex.rxjava3.internal.operators.single.SingleZipArray;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

/* loaded from: classes.dex */
public final class SingleZipIterable<T, R> extends Single<R> {
    final Iterable<? extends SingleSource<? extends T>> sources;
    final Function<? super Object[], ? extends R> zipper;

    public SingleZipIterable(Iterable<? extends SingleSource<? extends T>> iterable, Function<? super Object[], ? extends R> function) {
        this.sources = iterable;
        this.zipper = function;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super R> singleObserver) {
        SingleSource[] singleSourceArr = new SingleSource[8];
        try {
            int i = 0;
            for (SingleSource<? extends T> singleSource : this.sources) {
                if (singleSource == null) {
                    EmptyDisposable.error(new NullPointerException("One of the sources is null"), singleObserver);
                    return;
                }
                if (i == singleSourceArr.length) {
                    singleSourceArr = (SingleSource[]) Arrays.copyOf(singleSourceArr, (i >> 2) + i);
                }
                singleSourceArr[i] = singleSource;
                i++;
            }
            if (i == 0) {
                EmptyDisposable.error(new NoSuchElementException(), singleObserver);
                return;
            }
            if (i == 1) {
                singleSourceArr[0].subscribe(new SingleMap.MapSingleObserver(singleObserver, new SingletonArrayFunc()));
                return;
            }
            SingleZipArray.ZipCoordinator zipCoordinator = new SingleZipArray.ZipCoordinator(singleObserver, i, this.zipper);
            singleObserver.onSubscribe(zipCoordinator);
            for (int i2 = 0; i2 < i && !zipCoordinator.isDisposed(); i2++) {
                singleSourceArr[i2].subscribe(zipCoordinator.observers[i2]);
            }
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptyDisposable.error(th, singleObserver);
        }
    }

    /* loaded from: classes10.dex */
    final class SingletonArrayFunc implements Function<T, R> {
        SingletonArrayFunc() {
        }

        @Override // io.reactivex.rxjava3.functions.Function
        public R apply(T t) throws Throwable {
            return (R) Objects.requireNonNull(SingleZipIterable.this.zipper.apply(new Object[]{t}), "The zipper returned a null value");
        }
    }
}
