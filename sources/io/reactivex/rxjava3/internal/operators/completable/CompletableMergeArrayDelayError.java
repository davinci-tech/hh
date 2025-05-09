package io.reactivex.rxjava3.internal.operators.completable;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public final class CompletableMergeArrayDelayError extends Completable {
    final CompletableSource[] sources;

    public CompletableMergeArrayDelayError(CompletableSource[] completableSourceArr) {
        this.sources = completableSourceArr;
    }

    @Override // io.reactivex.rxjava3.core.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        AtomicInteger atomicInteger = new AtomicInteger(this.sources.length + 1);
        AtomicThrowable atomicThrowable = new AtomicThrowable();
        compositeDisposable.add(new TryTerminateAndReportDisposable(atomicThrowable));
        completableObserver.onSubscribe(compositeDisposable);
        for (CompletableSource completableSource : this.sources) {
            if (compositeDisposable.isDisposed()) {
                return;
            }
            if (completableSource == null) {
                atomicThrowable.tryAddThrowableOrReport(new NullPointerException("A completable source is null"));
                atomicInteger.decrementAndGet();
            } else {
                completableSource.subscribe(new MergeInnerCompletableObserver(completableObserver, compositeDisposable, atomicThrowable, atomicInteger));
            }
        }
        if (atomicInteger.decrementAndGet() == 0) {
            atomicThrowable.tryTerminateConsumer(completableObserver);
        }
    }

    /* loaded from: classes10.dex */
    static final class TryTerminateAndReportDisposable implements Disposable {
        final AtomicThrowable errors;

        TryTerminateAndReportDisposable(AtomicThrowable atomicThrowable) {
            this.errors = atomicThrowable;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.errors.tryTerminateAndReport();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.errors.isTerminated();
        }
    }

    /* loaded from: classes10.dex */
    static final class MergeInnerCompletableObserver implements CompletableObserver {
        final CompletableObserver downstream;
        final AtomicThrowable errors;
        final CompositeDisposable set;
        final AtomicInteger wip;

        MergeInnerCompletableObserver(CompletableObserver completableObserver, CompositeDisposable compositeDisposable, AtomicThrowable atomicThrowable, AtomicInteger atomicInteger) {
            this.downstream = completableObserver;
            this.set = compositeDisposable;
            this.errors = atomicThrowable;
            this.wip = atomicInteger;
        }

        @Override // io.reactivex.rxjava3.core.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            this.set.add(disposable);
        }

        @Override // io.reactivex.rxjava3.core.CompletableObserver
        public void onError(Throwable th) {
            if (this.errors.tryAddThrowableOrReport(th)) {
                tryTerminate();
            }
        }

        @Override // io.reactivex.rxjava3.core.CompletableObserver
        public void onComplete() {
            tryTerminate();
        }

        void tryTerminate() {
            if (this.wip.decrementAndGet() == 0) {
                this.errors.tryTerminateConsumer(this.downstream);
            }
        }
    }
}
