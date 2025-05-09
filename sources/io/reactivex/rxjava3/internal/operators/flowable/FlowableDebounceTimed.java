package io.reactivex.rxjava3.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.MissingBackpressureException;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.subscribers.SerializedSubscriber;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public final class FlowableDebounceTimed<T> extends AbstractFlowableWithUpstream<T, T> {
    final Scheduler scheduler;
    final long timeout;
    final TimeUnit unit;

    public FlowableDebounceTimed(Flowable<T> flowable, long j, TimeUnit timeUnit, Scheduler scheduler) {
        super(flowable);
        this.timeout = j;
        this.unit = timeUnit;
        this.scheduler = scheduler;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.source.subscribe((FlowableSubscriber) new DebounceTimedSubscriber(new SerializedSubscriber(subscriber), this.timeout, this.unit, this.scheduler.createWorker()));
    }

    /* loaded from: classes10.dex */
    static final class DebounceTimedSubscriber<T> extends AtomicLong implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = -9102637559663639004L;
        boolean done;
        final Subscriber<? super T> downstream;
        volatile long index;
        final long timeout;
        Disposable timer;
        final TimeUnit unit;
        Subscription upstream;
        final Scheduler.Worker worker;

        DebounceTimedSubscriber(Subscriber<? super T> subscriber, long j, TimeUnit timeUnit, Scheduler.Worker worker) {
            this.downstream = subscriber;
            this.timeout = j;
            this.unit = timeUnit;
            this.worker = worker;
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
            if (this.done) {
                return;
            }
            long j = this.index + 1;
            this.index = j;
            Disposable disposable = this.timer;
            if (disposable != null) {
                disposable.dispose();
            }
            DebounceEmitter debounceEmitter = new DebounceEmitter(t, j, this);
            this.timer = debounceEmitter;
            debounceEmitter.setResource(this.worker.schedule(debounceEmitter, this.timeout, this.unit));
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.done = true;
            Disposable disposable = this.timer;
            if (disposable != null) {
                disposable.dispose();
            }
            this.downstream.onError(th);
            this.worker.dispose();
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            Disposable disposable = this.timer;
            if (disposable != null) {
                disposable.dispose();
            }
            DebounceEmitter debounceEmitter = (DebounceEmitter) disposable;
            if (debounceEmitter != null) {
                debounceEmitter.emit();
            }
            this.downstream.onComplete();
            this.worker.dispose();
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this, j);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.upstream.cancel();
            this.worker.dispose();
        }

        void emit(long j, T t, DebounceEmitter<T> debounceEmitter) {
            if (j == this.index) {
                if (get() != 0) {
                    this.downstream.onNext(t);
                    BackpressureHelper.produced(this, 1L);
                    debounceEmitter.dispose();
                } else {
                    cancel();
                    this.downstream.onError(new MissingBackpressureException("Could not deliver value due to lack of requests"));
                }
            }
        }
    }

    /* loaded from: classes10.dex */
    static final class DebounceEmitter<T> extends AtomicReference<Disposable> implements Runnable, Disposable {
        private static final long serialVersionUID = 6812032969491025141L;
        final long idx;
        final AtomicBoolean once = new AtomicBoolean();
        final DebounceTimedSubscriber<T> parent;
        final T value;

        DebounceEmitter(T t, long j, DebounceTimedSubscriber<T> debounceTimedSubscriber) {
            this.value = t;
            this.idx = j;
            this.parent = debounceTimedSubscriber;
        }

        @Override // java.lang.Runnable
        public void run() {
            emit();
        }

        void emit() {
            if (this.once.compareAndSet(false, true)) {
                this.parent.emit(this.idx, this.value, this);
            }
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return get() == DisposableHelper.DISPOSED;
        }

        public void setResource(Disposable disposable) {
            DisposableHelper.replace(this, disposable);
        }
    }
}
