package io.reactivex.rxjava3.internal.jdk8;

import defpackage.aai;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.operators.QueueDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;

/* loaded from: classes.dex */
public final class ObservableFromStream<T> extends Observable<T> {
    final Stream<T> stream;

    public ObservableFromStream(Stream<T> stream) {
        this.stream = stream;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super T> observer) {
        subscribeStream(observer, this.stream);
    }

    public static <T> void subscribeStream(Observer<? super T> observer, Stream<T> stream) {
        try {
            Iterator<T> it = stream.iterator();
            if (!it.hasNext()) {
                EmptyDisposable.complete(observer);
                closeSafely(stream);
            } else {
                StreamDisposable streamDisposable = new StreamDisposable(observer, it, stream);
                observer.onSubscribe(streamDisposable);
                streamDisposable.run();
            }
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptyDisposable.error(th, observer);
            closeSafely(stream);
        }
    }

    static void closeSafely(AutoCloseable autoCloseable) {
        try {
            autoCloseable.close();
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            RxJavaPlugins.onError(th);
        }
    }

    /* loaded from: classes10.dex */
    static final class StreamDisposable<T> implements QueueDisposable<T> {
        AutoCloseable closeable;
        volatile boolean disposed;
        final Observer<? super T> downstream;
        Iterator<T> iterator;
        boolean once;
        boolean outputFused;

        StreamDisposable(Observer<? super T> observer, Iterator<T> it, AutoCloseable autoCloseable) {
            this.downstream = observer;
            this.iterator = it;
            this.closeable = autoCloseable;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.disposed = true;
            run();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.disposed;
        }

        @Override // io.reactivex.rxjava3.operators.QueueFuseable
        public int requestFusion(int i) {
            if ((i & 1) == 0) {
                return 0;
            }
            this.outputFused = true;
            return 1;
        }

        @Override // io.reactivex.rxjava3.operators.SimpleQueue
        public boolean offer(T t) {
            throw new UnsupportedOperationException();
        }

        @Override // io.reactivex.rxjava3.operators.SimpleQueue
        public boolean offer(T t, T t2) {
            throw new UnsupportedOperationException();
        }

        @Override // io.reactivex.rxjava3.operators.SimpleQueue
        public T poll() {
            Iterator<T> it = this.iterator;
            if (it == null) {
                return null;
            }
            if (!this.once) {
                this.once = true;
            } else if (!it.hasNext()) {
                clear();
                return null;
            }
            return (T) Objects.requireNonNull(this.iterator.next(), "The Stream's Iterator.next() returned a null value");
        }

        @Override // io.reactivex.rxjava3.operators.SimpleQueue
        public boolean isEmpty() {
            Iterator<T> it = this.iterator;
            if (it == null) {
                return true;
            }
            if (!this.once || it.hasNext()) {
                return false;
            }
            clear();
            return true;
        }

        @Override // io.reactivex.rxjava3.operators.SimpleQueue
        public void clear() {
            this.iterator = null;
            AutoCloseable autoCloseable = this.closeable;
            this.closeable = null;
            if (autoCloseable != null) {
                ObservableFromStream.closeSafely(autoCloseable);
            }
        }

        public void run() {
            if (this.outputFused) {
                return;
            }
            Iterator<T> it = this.iterator;
            Observer<? super T> observer = this.downstream;
            while (!this.disposed) {
                try {
                    aai aaiVar = (Object) Objects.requireNonNull(it.next(), "The Stream's Iterator.next returned a null value");
                    if (!this.disposed) {
                        observer.onNext(aaiVar);
                        if (!this.disposed) {
                            try {
                                if (!it.hasNext()) {
                                    observer.onComplete();
                                    this.disposed = true;
                                }
                            } catch (Throwable th) {
                                Exceptions.throwIfFatal(th);
                                observer.onError(th);
                                this.disposed = true;
                            }
                        }
                    }
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    observer.onError(th2);
                    this.disposed = true;
                }
            }
            clear();
        }
    }
}
