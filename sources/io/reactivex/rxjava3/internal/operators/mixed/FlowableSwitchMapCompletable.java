package io.reactivex.rxjava3.internal.operators.mixed;

import androidx.core.location.LocationRequestCompat;
import androidx.webkit.ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public final class FlowableSwitchMapCompletable<T> extends Completable {
    final boolean delayErrors;
    final Function<? super T, ? extends CompletableSource> mapper;
    final Flowable<T> source;

    public FlowableSwitchMapCompletable(Flowable<T> flowable, Function<? super T, ? extends CompletableSource> function, boolean z) {
        this.source = flowable;
        this.mapper = function;
        this.delayErrors = z;
    }

    @Override // io.reactivex.rxjava3.core.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        this.source.subscribe((FlowableSubscriber) new SwitchMapCompletableObserver(completableObserver, this.mapper, this.delayErrors));
    }

    /* loaded from: classes10.dex */
    static final class SwitchMapCompletableObserver<T> implements FlowableSubscriber<T>, Disposable {
        static final SwitchMapInnerObserver INNER_DISPOSED = new SwitchMapInnerObserver(null);
        final boolean delayErrors;
        volatile boolean done;
        final CompletableObserver downstream;
        final AtomicThrowable errors = new AtomicThrowable();
        final AtomicReference<SwitchMapInnerObserver> inner = new AtomicReference<>();
        final Function<? super T, ? extends CompletableSource> mapper;
        Subscription upstream;

        SwitchMapCompletableObserver(CompletableObserver completableObserver, Function<? super T, ? extends CompletableSource> function, boolean z) {
            this.downstream = completableObserver;
            this.mapper = function;
            this.delayErrors = z;
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                this.upstream = subscription;
                this.downstream.onSubscribe(this);
                subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            SwitchMapInnerObserver switchMapInnerObserver;
            try {
                CompletableSource completableSource = (CompletableSource) Objects.requireNonNull(this.mapper.apply(t), "The mapper returned a null CompletableSource");
                SwitchMapInnerObserver switchMapInnerObserver2 = new SwitchMapInnerObserver(this);
                do {
                    switchMapInnerObserver = this.inner.get();
                    if (switchMapInnerObserver == INNER_DISPOSED) {
                        return;
                    }
                } while (!ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(this.inner, switchMapInnerObserver, switchMapInnerObserver2));
                if (switchMapInnerObserver != null) {
                    switchMapInnerObserver.dispose();
                }
                completableSource.subscribe(switchMapInnerObserver2);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.upstream.cancel();
                onError(th);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.errors.tryAddThrowableOrReport(th)) {
                if (this.delayErrors) {
                    onComplete();
                } else {
                    disposeInner();
                    this.errors.tryTerminateConsumer(this.downstream);
                }
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.done = true;
            if (this.inner.get() == null) {
                this.errors.tryTerminateConsumer(this.downstream);
            }
        }

        void disposeInner() {
            AtomicReference<SwitchMapInnerObserver> atomicReference = this.inner;
            SwitchMapInnerObserver switchMapInnerObserver = INNER_DISPOSED;
            SwitchMapInnerObserver andSet = atomicReference.getAndSet(switchMapInnerObserver);
            if (andSet == null || andSet == switchMapInnerObserver) {
                return;
            }
            andSet.dispose();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.upstream.cancel();
            disposeInner();
            this.errors.tryTerminateAndReport();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.inner.get() == INNER_DISPOSED;
        }

        void innerError(SwitchMapInnerObserver switchMapInnerObserver, Throwable th) {
            if (ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(this.inner, switchMapInnerObserver, null)) {
                if (this.errors.tryAddThrowableOrReport(th)) {
                    if (this.delayErrors) {
                        if (this.done) {
                            this.errors.tryTerminateConsumer(this.downstream);
                            return;
                        }
                        return;
                    } else {
                        this.upstream.cancel();
                        disposeInner();
                        this.errors.tryTerminateConsumer(this.downstream);
                        return;
                    }
                }
                return;
            }
            RxJavaPlugins.onError(th);
        }

        void innerComplete(SwitchMapInnerObserver switchMapInnerObserver) {
            if (ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(this.inner, switchMapInnerObserver, null) && this.done) {
                this.errors.tryTerminateConsumer(this.downstream);
            }
        }

        static final class SwitchMapInnerObserver extends AtomicReference<Disposable> implements CompletableObserver {
            private static final long serialVersionUID = -8003404460084760287L;
            final SwitchMapCompletableObserver<?> parent;

            SwitchMapInnerObserver(SwitchMapCompletableObserver<?> switchMapCompletableObserver) {
                this.parent = switchMapCompletableObserver;
            }

            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(this, disposable);
            }

            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onError(Throwable th) {
                this.parent.innerError(this, th);
            }

            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onComplete() {
                this.parent.innerComplete(this);
            }

            void dispose() {
                DisposableHelper.dispose(this);
            }
        }
    }
}
