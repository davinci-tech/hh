package io.reactivex.rxjava3.internal.operators.maybe;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.operators.maybe.MaybeMap;
import io.reactivex.rxjava3.internal.operators.maybe.MaybeZipArray;
import java.util.Arrays;
import java.util.Objects;

/* loaded from: classes.dex */
public final class MaybeZipIterable<T, R> extends Maybe<R> {
    final Iterable<? extends MaybeSource<? extends T>> sources;
    final Function<? super Object[], ? extends R> zipper;

    public MaybeZipIterable(Iterable<? extends MaybeSource<? extends T>> iterable, Function<? super Object[], ? extends R> function) {
        this.sources = iterable;
        this.zipper = function;
    }

    @Override // io.reactivex.rxjava3.core.Maybe
    public void subscribeActual(MaybeObserver<? super R> maybeObserver) {
        MaybeSource[] maybeSourceArr = new MaybeSource[8];
        try {
            int i = 0;
            for (MaybeSource<? extends T> maybeSource : this.sources) {
                if (maybeSource == null) {
                    EmptyDisposable.error(new NullPointerException("One of the sources is null"), maybeObserver);
                    return;
                }
                if (i == maybeSourceArr.length) {
                    maybeSourceArr = (MaybeSource[]) Arrays.copyOf(maybeSourceArr, (i >> 2) + i);
                }
                maybeSourceArr[i] = maybeSource;
                i++;
            }
            if (i == 0) {
                EmptyDisposable.complete(maybeObserver);
                return;
            }
            if (i == 1) {
                maybeSourceArr[0].subscribe(new MaybeMap.MapMaybeObserver(maybeObserver, new SingletonArrayFunc()));
                return;
            }
            MaybeZipArray.ZipCoordinator zipCoordinator = new MaybeZipArray.ZipCoordinator(maybeObserver, i, this.zipper);
            maybeObserver.onSubscribe(zipCoordinator);
            for (int i2 = 0; i2 < i && !zipCoordinator.isDisposed(); i2++) {
                maybeSourceArr[i2].subscribe(zipCoordinator.observers[i2]);
            }
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptyDisposable.error(th, maybeObserver);
        }
    }

    /* loaded from: classes10.dex */
    final class SingletonArrayFunc implements Function<T, R> {
        SingletonArrayFunc() {
        }

        @Override // io.reactivex.rxjava3.functions.Function
        public R apply(T t) throws Throwable {
            return (R) Objects.requireNonNull(MaybeZipIterable.this.zipper.apply(new Object[]{t}), "The zipper returned a null value");
        }
    }
}
