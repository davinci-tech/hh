package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Notification;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;

/* loaded from: classes.dex */
public final class ObservableDematerialize<T, R> extends AbstractObservableWithUpstream<T, R> {
    final Function<? super T, ? extends Notification<R>> selector;

    public ObservableDematerialize(ObservableSource<T> observableSource, Function<? super T, ? extends Notification<R>> function) {
        super(observableSource);
        this.selector = function;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super R> observer) {
        this.source.subscribe(new DematerializeObserver(observer, this.selector));
    }

    /* loaded from: classes10.dex */
    static final class DematerializeObserver<T, R> implements Observer<T>, Disposable {
        boolean done;
        final Observer<? super R> downstream;
        final Function<? super T, ? extends Notification<R>> selector;
        Disposable upstream;

        DematerializeObserver(Observer<? super R> observer, Function<? super T, ? extends Notification<R>> function) {
            this.downstream = observer;
            this.selector = function;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
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

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            if (this.done) {
                if (t instanceof Notification) {
                    Notification notification = (Notification) t;
                    if (notification.isOnError()) {
                        RxJavaPlugins.onError(notification.getError());
                        return;
                    }
                    return;
                }
                return;
            }
            try {
                Notification notification2 = (Notification) Objects.requireNonNull(this.selector.apply(t), "The selector returned a null Notification");
                if (notification2.isOnError()) {
                    this.upstream.dispose();
                    onError(notification2.getError());
                } else if (notification2.isOnComplete()) {
                    this.upstream.dispose();
                    onComplete();
                } else {
                    this.downstream.onNext((Object) notification2.getValue());
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.upstream.dispose();
                onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
            } else {
                this.done = true;
                this.downstream.onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            this.downstream.onComplete();
        }
    }
}
