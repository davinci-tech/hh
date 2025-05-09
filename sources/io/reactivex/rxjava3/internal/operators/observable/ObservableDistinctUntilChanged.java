package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.functions.BiPredicate;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.observers.BasicFuseableObserver;

/* loaded from: classes.dex */
public final class ObservableDistinctUntilChanged<T, K> extends AbstractObservableWithUpstream<T, T> {
    final BiPredicate<? super K, ? super K> comparer;
    final Function<? super T, K> keySelector;

    public ObservableDistinctUntilChanged(ObservableSource<T> observableSource, Function<? super T, K> function, BiPredicate<? super K, ? super K> biPredicate) {
        super(observableSource);
        this.keySelector = function;
        this.comparer = biPredicate;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new DistinctUntilChangedObserver(observer, this.keySelector, this.comparer));
    }

    /* loaded from: classes10.dex */
    static final class DistinctUntilChangedObserver<T, K> extends BasicFuseableObserver<T, T> {
        final BiPredicate<? super K, ? super K> comparer;
        boolean hasValue;
        final Function<? super T, K> keySelector;
        K last;

        DistinctUntilChangedObserver(Observer<? super T> observer, Function<? super T, K> function, BiPredicate<? super K, ? super K> biPredicate) {
            super(observer);
            this.keySelector = function;
            this.comparer = biPredicate;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            if (this.sourceMode != 0) {
                this.downstream.onNext(t);
                return;
            }
            try {
                K apply = this.keySelector.apply(t);
                if (this.hasValue) {
                    boolean test = this.comparer.test(this.last, apply);
                    this.last = apply;
                    if (test) {
                        return;
                    }
                } else {
                    this.hasValue = true;
                    this.last = apply;
                }
                this.downstream.onNext(t);
            } catch (Throwable th) {
                fail(th);
            }
        }

        @Override // io.reactivex.rxjava3.operators.QueueFuseable
        public int requestFusion(int i) {
            return transitiveBoundaryFusion(i);
        }

        @Override // io.reactivex.rxjava3.operators.SimpleQueue
        public T poll() throws Throwable {
            while (true) {
                T poll = this.qd.poll();
                if (poll == null) {
                    return null;
                }
                K apply = this.keySelector.apply(poll);
                if (!this.hasValue) {
                    this.hasValue = true;
                    this.last = apply;
                    return poll;
                }
                if (!this.comparer.test(this.last, apply)) {
                    this.last = apply;
                    return poll;
                }
                this.last = apply;
            }
        }
    }
}
