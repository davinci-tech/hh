package io.reactivex.rxjava3.internal.operators.observable;

import androidx.webkit.ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.operators.QueueDisposable;
import io.reactivex.rxjava3.operators.SimpleQueue;
import io.reactivex.rxjava3.operators.SpscLinkedArrayQueue;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class ObservableSwitchMap<T, R> extends AbstractObservableWithUpstream<T, R> {
    final int bufferSize;
    final boolean delayErrors;
    final Function<? super T, ? extends ObservableSource<? extends R>> mapper;

    public ObservableSwitchMap(ObservableSource<T> observableSource, Function<? super T, ? extends ObservableSource<? extends R>> function, int i, boolean z) {
        super(observableSource);
        this.mapper = function;
        this.bufferSize = i;
        this.delayErrors = z;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super R> observer) {
        if (ObservableScalarXMap.tryScalarXMapSubscribe(this.source, observer, this.mapper)) {
            return;
        }
        this.source.subscribe(new SwitchMapObserver(observer, this.mapper, this.bufferSize, this.delayErrors));
    }

    /* loaded from: classes10.dex */
    static final class SwitchMapObserver<T, R> extends AtomicInteger implements Observer<T>, Disposable {
        static final SwitchMapInnerObserver<Object, Object> CANCELLED;
        private static final long serialVersionUID = -3491074160481096299L;
        final int bufferSize;
        volatile boolean cancelled;
        final boolean delayErrors;
        volatile boolean done;
        final Observer<? super R> downstream;
        final Function<? super T, ? extends ObservableSource<? extends R>> mapper;
        volatile long unique;
        Disposable upstream;
        final AtomicReference<SwitchMapInnerObserver<T, R>> active = new AtomicReference<>();
        final AtomicThrowable errors = new AtomicThrowable();

        static {
            SwitchMapInnerObserver<Object, Object> switchMapInnerObserver = new SwitchMapInnerObserver<>(null, -1L, 1);
            CANCELLED = switchMapInnerObserver;
            switchMapInnerObserver.cancel();
        }

        SwitchMapObserver(Observer<? super R> observer, Function<? super T, ? extends ObservableSource<? extends R>> function, int i, boolean z) {
            this.downstream = observer;
            this.mapper = function;
            this.bufferSize = i;
            this.delayErrors = z;
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
            SwitchMapInnerObserver<T, R> switchMapInnerObserver;
            long j = this.unique + 1;
            this.unique = j;
            SwitchMapInnerObserver<T, R> switchMapInnerObserver2 = this.active.get();
            if (switchMapInnerObserver2 != null) {
                switchMapInnerObserver2.cancel();
            }
            try {
                ObservableSource observableSource = (ObservableSource) Objects.requireNonNull(this.mapper.apply(t), "The ObservableSource returned is null");
                SwitchMapInnerObserver switchMapInnerObserver3 = new SwitchMapInnerObserver(this, j, this.bufferSize);
                do {
                    switchMapInnerObserver = this.active.get();
                    if (switchMapInnerObserver == CANCELLED) {
                        return;
                    }
                } while (!ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(this.active, switchMapInnerObserver, switchMapInnerObserver3));
                observableSource.subscribe(switchMapInnerObserver3);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.upstream.dispose();
                onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            if (!this.done && this.errors.tryAddThrowable(th)) {
                if (!this.delayErrors) {
                    disposeInner();
                }
                this.done = true;
                drain();
                return;
            }
            RxJavaPlugins.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            drain();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            this.upstream.dispose();
            disposeInner();
            this.errors.tryTerminateAndReport();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        void disposeInner() {
            SwitchMapInnerObserver switchMapInnerObserver = (SwitchMapInnerObserver) this.active.getAndSet(CANCELLED);
            if (switchMapInnerObserver != null) {
                switchMapInnerObserver.cancel();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:77:0x000f, code lost:
        
            continue;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        void drain() {
            /*
                r13 = this;
                int r0 = r13.getAndIncrement()
                if (r0 == 0) goto L7
                return
            L7:
                io.reactivex.rxjava3.core.Observer<? super R> r0 = r13.downstream
                java.util.concurrent.atomic.AtomicReference<io.reactivex.rxjava3.internal.operators.observable.ObservableSwitchMap$SwitchMapInnerObserver<T, R>> r1 = r13.active
                boolean r2 = r13.delayErrors
                r3 = 1
                r4 = r3
            Lf:
                boolean r5 = r13.cancelled
                if (r5 == 0) goto L14
                return
            L14:
                boolean r5 = r13.done
                r6 = 0
                if (r5 == 0) goto L4e
                java.lang.Object r5 = r1.get()
                if (r5 != 0) goto L21
                r5 = r3
                goto L22
            L21:
                r5 = r6
            L22:
                if (r2 == 0) goto L38
                if (r5 == 0) goto L4e
                io.reactivex.rxjava3.internal.util.AtomicThrowable r1 = r13.errors
                java.lang.Object r1 = r1.get()
                java.lang.Throwable r1 = (java.lang.Throwable) r1
                if (r1 == 0) goto L34
                r0.onError(r1)
                goto L37
            L34:
                r0.onComplete()
            L37:
                return
            L38:
                io.reactivex.rxjava3.internal.util.AtomicThrowable r7 = r13.errors
                java.lang.Object r7 = r7.get()
                java.lang.Throwable r7 = (java.lang.Throwable) r7
                if (r7 == 0) goto L48
                io.reactivex.rxjava3.internal.util.AtomicThrowable r1 = r13.errors
                r1.tryTerminateConsumer(r0)
                return
            L48:
                if (r5 == 0) goto L4e
                r0.onComplete()
                return
            L4e:
                java.lang.Object r5 = r1.get()
                io.reactivex.rxjava3.internal.operators.observable.ObservableSwitchMap$SwitchMapInnerObserver r5 = (io.reactivex.rxjava3.internal.operators.observable.ObservableSwitchMap.SwitchMapInnerObserver) r5
                if (r5 == 0) goto Lb7
                io.reactivex.rxjava3.operators.SimpleQueue<R> r7 = r5.queue
                if (r7 == 0) goto Lb7
                r8 = r6
            L5b:
                boolean r9 = r13.cancelled
                if (r9 == 0) goto L60
                return
            L60:
                java.lang.Object r9 = r1.get()
                if (r5 == r9) goto L67
                goto Lf
            L67:
                if (r2 != 0) goto L79
                io.reactivex.rxjava3.internal.util.AtomicThrowable r9 = r13.errors
                java.lang.Object r9 = r9.get()
                java.lang.Throwable r9 = (java.lang.Throwable) r9
                if (r9 == 0) goto L79
                io.reactivex.rxjava3.internal.util.AtomicThrowable r1 = r13.errors
                r1.tryTerminateConsumer(r0)
                return
            L79:
                boolean r9 = r5.done
                r10 = 0
                java.lang.Object r11 = r7.poll()     // Catch: java.lang.Throwable -> L81
                goto L9f
            L81:
                r8 = move-exception
                io.reactivex.rxjava3.exceptions.Exceptions.throwIfFatal(r8)
                io.reactivex.rxjava3.internal.util.AtomicThrowable r11 = r13.errors
                r11.tryAddThrowableOrReport(r8)
                androidx.webkit.ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(r1, r5, r10)
                if (r2 != 0) goto L9a
                r13.disposeInner()
                io.reactivex.rxjava3.disposables.Disposable r8 = r13.upstream
                r8.dispose()
                r13.done = r3
                goto L9d
            L9a:
                r5.cancel()
            L9d:
                r8 = r3
                r11 = r10
            L9f:
                if (r11 != 0) goto La3
                r12 = r3
                goto La4
            La3:
                r12 = r6
            La4:
                if (r9 == 0) goto Lad
                if (r12 == 0) goto Lad
                androidx.webkit.ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(r1, r5, r10)
                goto Lf
            Lad:
                if (r12 == 0) goto Lb3
                if (r8 == 0) goto Lb7
                goto Lf
            Lb3:
                r0.onNext(r11)
                goto L5b
            Lb7:
                int r4 = -r4
                int r4 = r13.addAndGet(r4)
                if (r4 != 0) goto Lf
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.operators.observable.ObservableSwitchMap.SwitchMapObserver.drain():void");
        }

        void innerError(SwitchMapInnerObserver<T, R> switchMapInnerObserver, Throwable th) {
            if (switchMapInnerObserver.index == this.unique && this.errors.tryAddThrowable(th)) {
                if (!this.delayErrors) {
                    this.upstream.dispose();
                    this.done = true;
                }
                switchMapInnerObserver.done = true;
                drain();
                return;
            }
            RxJavaPlugins.onError(th);
        }
    }

    /* loaded from: classes10.dex */
    static final class SwitchMapInnerObserver<T, R> extends AtomicReference<Disposable> implements Observer<R> {
        private static final long serialVersionUID = 3837284832786408377L;
        final int bufferSize;
        volatile boolean done;
        final long index;
        final SwitchMapObserver<T, R> parent;
        volatile SimpleQueue<R> queue;

        SwitchMapInnerObserver(SwitchMapObserver<T, R> switchMapObserver, long j, int i) {
            this.parent = switchMapObserver;
            this.index = j;
            this.bufferSize = i;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.setOnce(this, disposable)) {
                if (disposable instanceof QueueDisposable) {
                    QueueDisposable queueDisposable = (QueueDisposable) disposable;
                    int requestFusion = queueDisposable.requestFusion(7);
                    if (requestFusion == 1) {
                        this.queue = queueDisposable;
                        this.done = true;
                        this.parent.drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.queue = queueDisposable;
                        return;
                    }
                }
                this.queue = new SpscLinkedArrayQueue(this.bufferSize);
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(R r) {
            if (this.index == this.parent.unique) {
                if (r != null) {
                    this.queue.offer(r);
                }
                this.parent.drain();
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            this.parent.innerError(this, th);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            if (this.index == this.parent.unique) {
                this.done = true;
                this.parent.drain();
            }
        }

        public void cancel() {
            DisposableHelper.dispose(this);
        }
    }
}
