package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.observers.QueueDrainObserver;
import io.reactivex.rxjava3.internal.queue.MpscLinkedQueue;
import io.reactivex.rxjava3.internal.util.QueueDrainHelper;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.observers.SerializedObserver;
import java.util.Collection;
import java.util.Objects;

/* loaded from: classes.dex */
public final class ObservableBufferExactBoundary<T, U extends Collection<? super T>, B> extends AbstractObservableWithUpstream<T, U> {
    final ObservableSource<B> boundary;
    final Supplier<U> bufferSupplier;

    public ObservableBufferExactBoundary(ObservableSource<T> observableSource, ObservableSource<B> observableSource2, Supplier<U> supplier) {
        super(observableSource);
        this.boundary = observableSource2;
        this.bufferSupplier = supplier;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super U> observer) {
        this.source.subscribe(new BufferExactBoundaryObserver(new SerializedObserver(observer), this.bufferSupplier, this.boundary));
    }

    /* loaded from: classes10.dex */
    static final class BufferExactBoundaryObserver<T, U extends Collection<? super T>, B> extends QueueDrainObserver<T, U, U> implements Observer<T>, Disposable {
        final ObservableSource<B> boundary;
        U buffer;
        final Supplier<U> bufferSupplier;
        Disposable other;
        Disposable upstream;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.internal.observers.QueueDrainObserver, io.reactivex.rxjava3.internal.util.ObservableQueueDrain
        public /* bridge */ /* synthetic */ void accept(Observer observer, Object obj) {
            accept((Observer<? super Observer>) observer, (Observer) obj);
        }

        BufferExactBoundaryObserver(Observer<? super U> observer, Supplier<U> supplier, ObservableSource<B> observableSource) {
            super(observer, new MpscLinkedQueue());
            this.bufferSupplier = supplier;
            this.boundary = observableSource;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                try {
                    this.buffer = (U) Objects.requireNonNull(this.bufferSupplier.get(), "The buffer supplied is null");
                    BufferBoundaryObserver bufferBoundaryObserver = new BufferBoundaryObserver(this);
                    this.other = bufferBoundaryObserver;
                    this.downstream.onSubscribe(this);
                    if (this.cancelled) {
                        return;
                    }
                    this.boundary.subscribe(bufferBoundaryObserver);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.cancelled = true;
                    disposable.dispose();
                    EmptyDisposable.error(th, this.downstream);
                }
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            synchronized (this) {
                U u = this.buffer;
                if (u == null) {
                    return;
                }
                u.add(t);
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            dispose();
            this.downstream.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            synchronized (this) {
                U u = this.buffer;
                if (u == null) {
                    return;
                }
                this.buffer = null;
                this.queue.offer(u);
                this.done = true;
                if (enter()) {
                    QueueDrainHelper.drainLoop(this.queue, this.downstream, false, this, this);
                }
            }
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            this.other.dispose();
            this.upstream.dispose();
            if (enter()) {
                this.queue.clear();
            }
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        void next() {
            try {
                U u = (U) Objects.requireNonNull(this.bufferSupplier.get(), "The buffer supplied is null");
                synchronized (this) {
                    U u2 = this.buffer;
                    if (u2 == null) {
                        return;
                    }
                    this.buffer = u;
                    fastPathEmit(u2, false, this);
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                dispose();
                this.downstream.onError(th);
            }
        }

        public void accept(Observer<? super U> observer, U u) {
            this.downstream.onNext(u);
        }
    }

    /* loaded from: classes10.dex */
    static final class BufferBoundaryObserver<T, U extends Collection<? super T>, B> extends DisposableObserver<B> {
        final BufferExactBoundaryObserver<T, U, B> parent;

        BufferBoundaryObserver(BufferExactBoundaryObserver<T, U, B> bufferExactBoundaryObserver) {
            this.parent = bufferExactBoundaryObserver;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(B b) {
            this.parent.next();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            this.parent.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            this.parent.onComplete();
        }
    }
}
