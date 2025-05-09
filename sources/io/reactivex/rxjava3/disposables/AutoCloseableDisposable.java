package io.reactivex.rxjava3.disposables;

import com.huawei.operation.utils.Constants;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;

/* loaded from: classes10.dex */
final class AutoCloseableDisposable extends ReferenceDisposable<AutoCloseable> {
    private static final long serialVersionUID = -6646144244598696847L;

    AutoCloseableDisposable(AutoCloseable autoCloseable) {
        super(autoCloseable);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.reactivex.rxjava3.disposables.ReferenceDisposable
    public void onDisposed(AutoCloseable autoCloseable) {
        try {
            autoCloseable.close();
        } catch (Throwable th) {
            throw ExceptionHelper.wrapOrThrow(th);
        }
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public String toString() {
        return "AutoCloseableDisposable(disposed=" + isDisposed() + ", " + get() + Constants.RIGHT_BRACKET_ONLY;
    }
}
