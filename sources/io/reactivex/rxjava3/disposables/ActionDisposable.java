package io.reactivex.rxjava3.disposables;

import com.huawei.operation.utils.Constants;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;

/* loaded from: classes10.dex */
final class ActionDisposable extends ReferenceDisposable<Action> {
    private static final long serialVersionUID = -8219729196779211169L;

    ActionDisposable(Action action) {
        super(action);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.reactivex.rxjava3.disposables.ReferenceDisposable
    public void onDisposed(Action action) {
        try {
            action.run();
        } catch (Throwable th) {
            throw ExceptionHelper.wrapOrThrow(th);
        }
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public String toString() {
        return "ActionDisposable(disposed=" + isDisposed() + ", " + get() + Constants.RIGHT_BRACKET_ONLY;
    }
}
