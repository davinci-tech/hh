package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.observables.GroupedObservable;
import io.reactivex.rxjava3.operators.SpscLinkedArrayQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class ObservableGroupBy<T, K, V> extends AbstractObservableWithUpstream<T, GroupedObservable<K, V>> {
    final int bufferSize;
    final boolean delayError;
    final Function<? super T, ? extends K> keySelector;
    final Function<? super T, ? extends V> valueSelector;

    public ObservableGroupBy(ObservableSource<T> observableSource, Function<? super T, ? extends K> function, Function<? super T, ? extends V> function2, int i, boolean z) {
        super(observableSource);
        this.keySelector = function;
        this.valueSelector = function2;
        this.bufferSize = i;
        this.delayError = z;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super GroupedObservable<K, V>> observer) {
        this.source.subscribe(new GroupByObserver(observer, this.keySelector, this.valueSelector, this.bufferSize, this.delayError));
    }

    /* loaded from: classes10.dex */
    public static final class GroupByObserver<T, K, V> extends AtomicInteger implements Observer<T>, Disposable {
        static final Object NULL_KEY = new Object();
        private static final long serialVersionUID = -3688291656102519502L;
        final int bufferSize;
        final boolean delayError;
        final Observer<? super GroupedObservable<K, V>> downstream;
        final Function<? super T, ? extends K> keySelector;
        Disposable upstream;
        final Function<? super T, ? extends V> valueSelector;
        final AtomicBoolean cancelled = new AtomicBoolean();
        final Map<Object, GroupedUnicast<K, V>> groups = new ConcurrentHashMap();

        public GroupByObserver(Observer<? super GroupedObservable<K, V>> observer, Function<? super T, ? extends K> function, Function<? super T, ? extends V> function2, int i, boolean z) {
            this.downstream = observer;
            this.keySelector = function;
            this.valueSelector = function2;
            this.bufferSize = i;
            this.delayError = z;
            lazySet(1);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.downstream.onSubscribe(this);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            boolean z;
            try {
                K apply = this.keySelector.apply(t);
                Object obj = apply != null ? apply : NULL_KEY;
                GroupedUnicast groupedUnicast = this.groups.get(obj);
                if (groupedUnicast != null) {
                    z = false;
                } else {
                    if (this.cancelled.get()) {
                        return;
                    }
                    groupedUnicast = GroupedUnicast.createWith(apply, this.bufferSize, this, this.delayError);
                    this.groups.put(obj, groupedUnicast);
                    getAndIncrement();
                    z = true;
                }
                try {
                    groupedUnicast.onNext(Objects.requireNonNull(this.valueSelector.apply(t), "The value supplied is null"));
                    if (z) {
                        this.downstream.onNext(groupedUnicast);
                        if (groupedUnicast.state.tryAbandon()) {
                            cancel(apply);
                            groupedUnicast.onComplete();
                        }
                    }
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.upstream.dispose();
                    if (z) {
                        this.downstream.onNext(groupedUnicast);
                    }
                    onError(th);
                }
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                this.upstream.dispose();
                onError(th2);
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            ArrayList arrayList = new ArrayList(this.groups.values());
            this.groups.clear();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((GroupedUnicast) it.next()).onError(th);
            }
            this.downstream.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            ArrayList arrayList = new ArrayList(this.groups.values());
            this.groups.clear();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((GroupedUnicast) it.next()).onComplete();
            }
            this.downstream.onComplete();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            if (this.cancelled.compareAndSet(false, true) && decrementAndGet() == 0) {
                this.upstream.dispose();
            }
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.cancelled.get();
        }

        public void cancel(K k) {
            if (k == null) {
                k = (K) NULL_KEY;
            }
            this.groups.remove(k);
            if (decrementAndGet() == 0) {
                this.upstream.dispose();
            }
        }
    }

    /* loaded from: classes10.dex */
    static final class GroupedUnicast<K, T> extends GroupedObservable<K, T> {
        final State<T, K> state;

        public static <T, K> GroupedUnicast<K, T> createWith(K k, int i, GroupByObserver<?, K, T> groupByObserver, boolean z) {
            return new GroupedUnicast<>(k, new State(i, groupByObserver, k, z));
        }

        protected GroupedUnicast(K k, State<T, K> state) {
            super(k);
            this.state = state;
        }

        @Override // io.reactivex.rxjava3.core.Observable
        public void subscribeActual(Observer<? super T> observer) {
            this.state.subscribe(observer);
        }

        public void onNext(T t) {
            this.state.onNext(t);
        }

        public void onError(Throwable th) {
            this.state.onError(th);
        }

        public void onComplete() {
            this.state.onComplete();
        }
    }

    /* loaded from: classes10.dex */
    static final class State<T, K> extends AtomicInteger implements Disposable, ObservableSource<T> {
        static final int ABANDONED = 2;
        static final int ABANDONED_HAS_SUBSCRIBER = 3;
        static final int FRESH = 0;
        static final int HAS_SUBSCRIBER = 1;
        private static final long serialVersionUID = -3852313036005250360L;
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final K key;
        final GroupByObserver<?, K, T> parent;
        final SpscLinkedArrayQueue<T> queue;
        final AtomicBoolean cancelled = new AtomicBoolean();
        final AtomicReference<Observer<? super T>> actual = new AtomicReference<>();
        final AtomicInteger once = new AtomicInteger();

        State(int i, GroupByObserver<?, K, T> groupByObserver, K k, boolean z) {
            this.queue = new SpscLinkedArrayQueue<>(i);
            this.parent = groupByObserver;
            this.key = k;
            this.delayError = z;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            if (this.cancelled.compareAndSet(false, true) && getAndIncrement() == 0) {
                this.actual.lazySet(null);
                cancelParent();
            }
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.cancelled.get();
        }

        @Override // io.reactivex.rxjava3.core.ObservableSource
        public void subscribe(Observer<? super T> observer) {
            int i;
            do {
                i = this.once.get();
                if ((i & 1) != 0) {
                    EmptyDisposable.error(new IllegalStateException("Only one Observer allowed!"), observer);
                    return;
                }
            } while (!this.once.compareAndSet(i, i | 1));
            observer.onSubscribe(this);
            this.actual.lazySet(observer);
            if (this.cancelled.get()) {
                this.actual.lazySet(null);
            } else {
                drain();
            }
        }

        public void onNext(T t) {
            this.queue.offer(t);
            drain();
        }

        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            drain();
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        void drain() {
            if (getAndIncrement() != 0) {
                return;
            }
            SpscLinkedArrayQueue<T> spscLinkedArrayQueue = this.queue;
            boolean z = this.delayError;
            Observer<? super T> observer = this.actual.get();
            int i = 1;
            while (true) {
                if (observer != null) {
                    while (true) {
                        boolean z2 = this.done;
                        T poll = spscLinkedArrayQueue.poll();
                        boolean z3 = poll == null;
                        if (checkTerminated(z2, z3, observer, z)) {
                            return;
                        }
                        if (z3) {
                            break;
                        } else {
                            observer.onNext(poll);
                        }
                    }
                }
                i = addAndGet(-i);
                if (i == 0) {
                    return;
                }
                if (observer == null) {
                    observer = this.actual.get();
                }
            }
        }

        void cancelParent() {
            if ((this.once.get() & 2) == 0) {
                this.parent.cancel(this.key);
            }
        }

        boolean tryAbandon() {
            return this.once.get() == 0 && this.once.compareAndSet(0, 2);
        }

        boolean checkTerminated(boolean z, boolean z2, Observer<? super T> observer, boolean z3) {
            if (this.cancelled.get()) {
                this.queue.clear();
                this.actual.lazySet(null);
                cancelParent();
                return true;
            }
            if (!z) {
                return false;
            }
            if (z3) {
                if (!z2) {
                    return false;
                }
                Throwable th = this.error;
                this.actual.lazySet(null);
                if (th != null) {
                    observer.onError(th);
                } else {
                    observer.onComplete();
                }
                return true;
            }
            Throwable th2 = this.error;
            if (th2 != null) {
                this.queue.clear();
                this.actual.lazySet(null);
                observer.onError(th2);
                return true;
            }
            if (!z2) {
                return false;
            }
            this.actual.lazySet(null);
            observer.onComplete();
            return true;
        }
    }
}
