package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.schedulers.Timed;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public final class ObservableTimeInterval<T> extends AbstractObservableWithUpstream<T, Timed<T>> {
    final Scheduler scheduler;
    final TimeUnit unit;

    public ObservableTimeInterval(ObservableSource<T> observableSource, TimeUnit timeUnit, Scheduler scheduler) {
        super(observableSource);
        this.scheduler = scheduler;
        this.unit = timeUnit;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super Timed<T>> observer) {
        this.source.subscribe(new TimeIntervalObserver(observer, this.unit, this.scheduler));
    }

    /* loaded from: classes10.dex */
    static final class TimeIntervalObserver<T> implements Observer<T>, Disposable {
        final Observer<? super Timed<T>> downstream;
        long lastTime;
        final Scheduler scheduler;
        final TimeUnit unit;
        Disposable upstream;

        TimeIntervalObserver(Observer<? super Timed<T>> observer, TimeUnit timeUnit, Scheduler scheduler) {
            this.downstream = observer;
            this.scheduler = scheduler;
            this.unit = timeUnit;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.lastTime = this.scheduler.now(this.unit);
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.upstream.dispose();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            long now = this.scheduler.now(this.unit);
            long j = this.lastTime;
            this.lastTime = now;
            this.downstream.onNext(new Timed(t, now - j, this.unit));
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            this.downstream.onComplete();
        }
    }
}
