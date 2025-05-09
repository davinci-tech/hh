package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.subjects.Subject;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes10.dex */
final class ObservableWindowSubscribeIntercept<T> extends Observable<T> {
    final AtomicBoolean once = new AtomicBoolean();
    final Subject<T> window;

    ObservableWindowSubscribeIntercept(Subject<T> subject) {
        this.window = subject;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super T> observer) {
        this.window.subscribe(observer);
        this.once.set(true);
    }

    boolean tryAbandon() {
        return !this.once.get() && this.once.compareAndSet(false, true);
    }
}
