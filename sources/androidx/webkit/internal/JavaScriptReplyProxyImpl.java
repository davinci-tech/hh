package androidx.webkit.internal;

import androidx.webkit.JavaScriptReplyProxy;
import defpackage.uxe;
import java.lang.reflect.InvocationHandler;
import java.util.concurrent.Callable;
import org.chromium.support_lib_boundary.JsReplyProxyBoundaryInterface;

/* loaded from: classes8.dex */
public class JavaScriptReplyProxyImpl extends JavaScriptReplyProxy {
    private JsReplyProxyBoundaryInterface mBoundaryInterface;

    public JavaScriptReplyProxyImpl(JsReplyProxyBoundaryInterface jsReplyProxyBoundaryInterface) {
        this.mBoundaryInterface = jsReplyProxyBoundaryInterface;
    }

    public static JavaScriptReplyProxyImpl forInvocationHandler(InvocationHandler invocationHandler) {
        final JsReplyProxyBoundaryInterface jsReplyProxyBoundaryInterface = (JsReplyProxyBoundaryInterface) uxe.b(JsReplyProxyBoundaryInterface.class, invocationHandler);
        return (JavaScriptReplyProxyImpl) jsReplyProxyBoundaryInterface.getOrCreatePeer(new Callable<Object>() { // from class: androidx.webkit.internal.JavaScriptReplyProxyImpl.1
            @Override // java.util.concurrent.Callable
            public Object call() {
                return new JavaScriptReplyProxyImpl(JsReplyProxyBoundaryInterface.this);
            }
        });
    }

    @Override // androidx.webkit.JavaScriptReplyProxy
    public void postMessage(String str) {
        if (WebViewFeatureInternal.WEB_MESSAGE_LISTENER.isSupportedByWebView()) {
            this.mBoundaryInterface.postMessage(str);
            return;
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }
}
