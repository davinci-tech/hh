package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public final class ObservableUsing<T, D> extends Observable<T> {
    final Consumer<? super D> disposer;
    final boolean eager;
    final Supplier<? extends D> resourceSupplier;
    final Function<? super D, ? extends ObservableSource<? extends T>> sourceSupplier;

    public ObservableUsing(Supplier<? extends D> supplier, Function<? super D, ? extends ObservableSource<? extends T>> function, Consumer<? super D> consumer, boolean z) {
        this.resourceSupplier = supplier;
        this.sourceSupplier = function;
        this.disposer = consumer;
        this.eager = z;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super T> observer) {
        try {
            D d = this.resourceSupplier.get();
            try {
                ((ObservableSource) Objects.requireNonNull(this.sourceSupplier.apply(d), "The sourceSupplier returned a null ObservableSource")).subscribe(new UsingObserver(observer, d, this.disposer, this.eager));
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                try {
                    this.disposer.accept(d);
                    EmptyDisposable.error(th, observer);
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    EmptyDisposable.error(new CompositeException(th, th2), observer);
                }
            }
        } catch (Throwable th3) {
            Exceptions.throwIfFatal(th3);
            EmptyDisposable.error(th3, observer);
        }
    }

    /* loaded from: classes10.dex */
    static final class UsingObserver<T, D> extends AtomicBoolean implements Observer<T>, Disposable {
        private static final long serialVersionUID = 5904473792286235046L;
        final Consumer<? super D> disposer;
        final Observer<? super T> downstream;
        final boolean eager;
        final D resource;
        Disposable upstream;

        UsingObserver(Observer<? super T> observer, D d, Consumer<? super D> consumer, boolean z) {
            this.downstream = observer;
            this.resource = d;
            this.disposer = consumer;
            this.eager = z;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            if (this.eager) {
                if (compareAndSet(false, true)) {
                    try {
                        this.disposer.accept(this.resource);
                    } catch (Throwable th2) {
                        Exceptions.throwIfFatal(th2);
                        th = new CompositeException(th, th2);
                    }
                }
                this.downstream.onError(th);
                return;
            }
            this.downstream.onError(th);
            disposeResource();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            if (this.eager) {
                if (compareAndSet(false, true)) {
                    try {
                        this.disposer.accept(this.resource);
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        this.downstream.onError(th);
                        return;
                    }
                }
                this.downstream.onComplete();
                return;
            }
            this.downstream.onComplete();
            disposeResource();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            if (this.eager) {
                disposeResource();
                this.upstream.dispose();
                this.upstream = DisposableHelper.DISPOSED;
            } else {
                this.upstream.dispose();
                this.upstream = DisposableHelper.DISPOSED;
                disposeResource();
            }
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return get();
        }

        void disposeResource() {
            if (compareAndSet(false, true)) {
                try {
                    this.disposer.accept(this.resource);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    RxJavaPlugins.onError(th);
                }
            }
        }
    }
}
