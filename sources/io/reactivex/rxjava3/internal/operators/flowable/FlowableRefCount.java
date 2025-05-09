package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.flowables.ConnectableFlowable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public final class FlowableRefCount<T> extends Flowable<T> {
    RefConnection connection;
    final int n;
    final Scheduler scheduler;
    final ConnectableFlowable<T> source;
    final long timeout;
    final TimeUnit unit;

    public FlowableRefCount(ConnectableFlowable<T> connectableFlowable) {
        this(connectableFlowable, 1, 0L, TimeUnit.NANOSECONDS, null);
    }

    public FlowableRefCount(ConnectableFlowable<T> connectableFlowable, int i, long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.source = connectableFlowable;
        this.n = i;
        this.timeout = j;
        this.unit = timeUnit;
        this.scheduler = scheduler;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        RefConnection refConnection;
        boolean z;
        synchronized (this) {
            refConnection = this.connection;
            if (refConnection == null) {
                refConnection = new RefConnection(this);
                this.connection = refConnection;
            }
            long j = refConnection.subscriberCount;
            if (j == 0 && refConnection.timer != null) {
                refConnection.timer.dispose();
            }
            long j2 = j + 1;
            refConnection.subscriberCount = j2;
            if (refConnection.connected || j2 != this.n) {
                z = false;
            } else {
                z = true;
                refConnection.connected = true;
            }
        }
        this.source.subscribe((FlowableSubscriber) new RefCountSubscriber(subscriber, this, refConnection));
        if (z) {
            this.source.connect(refConnection);
        }
    }

    void cancel(RefConnection refConnection) {
        synchronized (this) {
            RefConnection refConnection2 = this.connection;
            if (refConnection2 != null && refConnection2 == refConnection) {
                long j = refConnection.subscriberCount - 1;
                refConnection.subscriberCount = j;
                if (j == 0 && refConnection.connected) {
                    if (this.timeout == 0) {
                        timeout(refConnection);
                        return;
                    }
                    SequentialDisposable sequentialDisposable = new SequentialDisposable();
                    refConnection.timer = sequentialDisposable;
                    sequentialDisposable.replace(this.scheduler.scheduleDirect(refConnection, this.timeout, this.unit));
                }
            }
        }
    }

    void terminated(RefConnection refConnection) {
        synchronized (this) {
            if (this.connection == refConnection) {
                if (refConnection.timer != null) {
                    refConnection.timer.dispose();
                    refConnection.timer = null;
                }
                long j = refConnection.subscriberCount - 1;
                refConnection.subscriberCount = j;
                if (j == 0) {
                    this.connection = null;
                    this.source.reset();
                }
            }
        }
    }

    void timeout(RefConnection refConnection) {
        synchronized (this) {
            if (refConnection.subscriberCount == 0 && refConnection == this.connection) {
                this.connection = null;
                Disposable disposable = refConnection.get();
                DisposableHelper.dispose(refConnection);
                if (disposable == null) {
                    refConnection.disconnectedEarly = true;
                } else {
                    this.source.reset();
                }
            }
        }
    }

    /* loaded from: classes10.dex */
    static final class RefConnection extends AtomicReference<Disposable> implements Runnable, Consumer<Disposable> {
        private static final long serialVersionUID = -4552101107598366241L;
        boolean connected;
        boolean disconnectedEarly;
        final FlowableRefCount<?> parent;
        long subscriberCount;
        Disposable timer;

        RefConnection(FlowableRefCount<?> flowableRefCount) {
            this.parent = flowableRefCount;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.parent.timeout(this);
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public void accept(Disposable disposable) {
            DisposableHelper.replace(this, disposable);
            synchronized (this.parent) {
                if (this.disconnectedEarly) {
                    this.parent.source.reset();
                }
            }
        }
    }

    /* loaded from: classes10.dex */
    static final class RefCountSubscriber<T> extends AtomicBoolean implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = -7419642935409022375L;
        final RefConnection connection;
        final Subscriber<? super T> downstream;
        final FlowableRefCount<T> parent;
        Subscription upstream;

        RefCountSubscriber(Subscriber<? super T> subscriber, FlowableRefCount<T> flowableRefCount, RefConnection refConnection) {
            this.downstream = subscriber;
            this.parent = flowableRefCount;
            this.connection = refConnection;
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (compareAndSet(false, true)) {
                this.parent.terminated(this.connection);
                this.downstream.onError(th);
            } else {
                RxJavaPlugins.onError(th);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (compareAndSet(false, true)) {
                this.parent.terminated(this.connection);
                this.downstream.onComplete();
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            this.upstream.request(j);
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.upstream.cancel();
            if (compareAndSet(false, true)) {
                this.parent.cancel(this.connection);
            }
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                this.upstream = subscription;
                this.downstream.onSubscribe(this);
            }
        }
    }
}
