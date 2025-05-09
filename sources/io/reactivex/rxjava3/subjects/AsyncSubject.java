package io.reactivex.rxjava3.subjects;

import androidx.webkit.ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0;
import io.reactivex.rxjava3.annotations.CheckReturnValue;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.observers.DeferredScalarDisposable;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes10.dex */
public final class AsyncSubject<T> extends Subject<T> {
    static final AsyncDisposable[] EMPTY = new AsyncDisposable[0];
    static final AsyncDisposable[] TERMINATED = new AsyncDisposable[0];
    Throwable error;
    final AtomicReference<AsyncDisposable<T>[]> subscribers = new AtomicReference<>(EMPTY);
    T value;

    @CheckReturnValue
    public static <T> AsyncSubject<T> create() {
        return new AsyncSubject<>();
    }

    AsyncSubject() {
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onSubscribe(Disposable disposable) {
        if (this.subscribers.get() == TERMINATED) {
            disposable.dispose();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onNext(T t) {
        ExceptionHelper.nullCheck(t, "onNext called with a null value.");
        if (this.subscribers.get() == TERMINATED) {
            return;
        }
        this.value = t;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onError(Throwable th) {
        ExceptionHelper.nullCheck(th, "onError called with a null Throwable.");
        AsyncDisposable<T>[] asyncDisposableArr = this.subscribers.get();
        AsyncDisposable<T>[] asyncDisposableArr2 = TERMINATED;
        if (asyncDisposableArr == asyncDisposableArr2) {
            RxJavaPlugins.onError(th);
            return;
        }
        this.value = null;
        this.error = th;
        for (AsyncDisposable<T> asyncDisposable : this.subscribers.getAndSet(asyncDisposableArr2)) {
            asyncDisposable.onError(th);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onComplete() {
        AsyncDisposable<T>[] asyncDisposableArr = this.subscribers.get();
        AsyncDisposable<T>[] asyncDisposableArr2 = TERMINATED;
        if (asyncDisposableArr == asyncDisposableArr2) {
            return;
        }
        T t = this.value;
        AsyncDisposable<T>[] andSet = this.subscribers.getAndSet(asyncDisposableArr2);
        int i = 0;
        if (t == null) {
            int length = andSet.length;
            while (i < length) {
                andSet[i].onComplete();
                i++;
            }
            return;
        }
        int length2 = andSet.length;
        while (i < length2) {
            andSet[i].complete(t);
            i++;
        }
    }

    @Override // io.reactivex.rxjava3.subjects.Subject
    @CheckReturnValue
    public boolean hasObservers() {
        return this.subscribers.get().length != 0;
    }

    @Override // io.reactivex.rxjava3.subjects.Subject
    @CheckReturnValue
    public boolean hasThrowable() {
        return this.subscribers.get() == TERMINATED && this.error != null;
    }

    @Override // io.reactivex.rxjava3.subjects.Subject
    @CheckReturnValue
    public boolean hasComplete() {
        return this.subscribers.get() == TERMINATED && this.error == null;
    }

    @Override // io.reactivex.rxjava3.subjects.Subject
    @CheckReturnValue
    public Throwable getThrowable() {
        if (this.subscribers.get() == TERMINATED) {
            return this.error;
        }
        return null;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super T> observer) {
        AsyncDisposable<T> asyncDisposable = new AsyncDisposable<>(observer, this);
        observer.onSubscribe(asyncDisposable);
        if (add(asyncDisposable)) {
            if (asyncDisposable.isDisposed()) {
                remove(asyncDisposable);
                return;
            }
            return;
        }
        Throwable th = this.error;
        if (th != null) {
            observer.onError(th);
            return;
        }
        T t = this.value;
        if (t != null) {
            asyncDisposable.complete(t);
        } else {
            asyncDisposable.onComplete();
        }
    }

    boolean add(AsyncDisposable<T> asyncDisposable) {
        AsyncDisposable<T>[] asyncDisposableArr;
        AsyncDisposable[] asyncDisposableArr2;
        do {
            asyncDisposableArr = this.subscribers.get();
            if (asyncDisposableArr == TERMINATED) {
                return false;
            }
            int length = asyncDisposableArr.length;
            asyncDisposableArr2 = new AsyncDisposable[length + 1];
            System.arraycopy(asyncDisposableArr, 0, asyncDisposableArr2, 0, length);
            asyncDisposableArr2[length] = asyncDisposable;
        } while (!ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(this.subscribers, asyncDisposableArr, asyncDisposableArr2));
        return true;
    }

    void remove(AsyncDisposable<T> asyncDisposable) {
        AsyncDisposable<T>[] asyncDisposableArr;
        AsyncDisposable[] asyncDisposableArr2;
        do {
            asyncDisposableArr = this.subscribers.get();
            int length = asyncDisposableArr.length;
            if (length == 0) {
                return;
            }
            int i = 0;
            while (true) {
                if (i >= length) {
                    i = -1;
                    break;
                } else if (asyncDisposableArr[i] == asyncDisposable) {
                    break;
                } else {
                    i++;
                }
            }
            if (i < 0) {
                return;
            }
            if (length == 1) {
                asyncDisposableArr2 = EMPTY;
            } else {
                AsyncDisposable[] asyncDisposableArr3 = new AsyncDisposable[length - 1];
                System.arraycopy(asyncDisposableArr, 0, asyncDisposableArr3, 0, i);
                System.arraycopy(asyncDisposableArr, i + 1, asyncDisposableArr3, i, (length - i) - 1);
                asyncDisposableArr2 = asyncDisposableArr3;
            }
        } while (!ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(this.subscribers, asyncDisposableArr, asyncDisposableArr2));
    }

    @CheckReturnValue
    public boolean hasValue() {
        return this.subscribers.get() == TERMINATED && this.value != null;
    }

    @CheckReturnValue
    public T getValue() {
        if (this.subscribers.get() == TERMINATED) {
            return this.value;
        }
        return null;
    }

    static final class AsyncDisposable<T> extends DeferredScalarDisposable<T> {
        private static final long serialVersionUID = 5629876084736248016L;
        final AsyncSubject<T> parent;

        AsyncDisposable(Observer<? super T> observer, AsyncSubject<T> asyncSubject) {
            super(observer);
            this.parent = asyncSubject;
        }

        @Override // io.reactivex.rxjava3.internal.observers.DeferredScalarDisposable, io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            if (super.tryDispose()) {
                this.parent.remove(this);
            }
        }

        void onComplete() {
            if (isDisposed()) {
                return;
            }
            this.downstream.onComplete();
        }

        void onError(Throwable th) {
            if (isDisposed()) {
                RxJavaPlugins.onError(th);
            } else {
                this.downstream.onError(th);
            }
        }
    }
}
