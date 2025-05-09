package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public final class FlowableFromObservable<T> extends Flowable<T> {
    private final ObservableSource<T> upstream;

    public FlowableFromObservable(ObservableSource<T> observableSource) {
        this.upstream = observableSource;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.upstream.subscribe(new SubscriberObserver(subscriber));
    }

    /* loaded from: classes10.dex */
    static final class SubscriberObserver<T> implements Observer<T>, Subscription {
        final Subscriber<? super T> downstream;
        Disposable upstream;

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
        }

        SubscriberObserver(Subscriber<? super T> subscriber) {
            this.downstream = subscriber;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            this.downstream.onComplete();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            this.upstream = disposable;
            this.downstream.onSubscribe(this);
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.upstream.dispose();
        }
    }
}
