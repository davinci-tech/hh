package io.reactivex.rxjava3.internal.jdk8;

import java.util.NoSuchElementException;

/* loaded from: classes.dex */
public final class ObservableFirstStageObserver<T> extends ObservableStageObserver<T> {
    final T defaultItem;
    final boolean hasDefault;

    public ObservableFirstStageObserver(boolean z, T t) {
        this.hasDefault = z;
        this.defaultItem = t;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onNext(T t) {
        complete(t);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onComplete() {
        if (isDone()) {
            return;
        }
        clear();
        if (this.hasDefault) {
            complete(this.defaultItem);
        } else {
            completeExceptionally(new NoSuchElementException());
        }
    }
}
