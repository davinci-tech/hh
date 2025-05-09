package androidx.webkit.internal;

import androidx.webkit.ScriptHandler;
import defpackage.uxe;
import java.lang.reflect.InvocationHandler;
import org.chromium.support_lib_boundary.ScriptHandlerBoundaryInterface;

/* loaded from: classes8.dex */
public class ScriptHandlerImpl extends ScriptHandler {
    private ScriptHandlerBoundaryInterface mBoundaryInterface;

    private ScriptHandlerImpl(ScriptHandlerBoundaryInterface scriptHandlerBoundaryInterface) {
        this.mBoundaryInterface = scriptHandlerBoundaryInterface;
    }

    public static ScriptHandlerImpl toScriptHandler(InvocationHandler invocationHandler) {
        return new ScriptHandlerImpl((ScriptHandlerBoundaryInterface) uxe.b(ScriptHandlerBoundaryInterface.class, invocationHandler));
    }

    @Override // androidx.webkit.ScriptHandler
    public void remove() {
        this.mBoundaryInterface.remove();
    }
}
