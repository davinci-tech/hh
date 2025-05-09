package androidx.webkit.internal;

import androidx.webkit.WebMessageCompat;
import androidx.webkit.WebMessagePortCompat;
import androidx.webkit.WebViewFeature;
import defpackage.uxe;
import java.lang.reflect.InvocationHandler;
import org.chromium.support_lib_boundary.WebMessageBoundaryInterface;
import org.chromium.support_lib_boundary.WebMessageCallbackBoundaryInterface;

/* loaded from: classes8.dex */
public class WebMessageCallbackAdapter implements WebMessageCallbackBoundaryInterface {
    private final WebMessagePortCompat.WebMessageCallbackCompat mImpl;

    public WebMessageCallbackAdapter(WebMessagePortCompat.WebMessageCallbackCompat webMessageCallbackCompat) {
        this.mImpl = webMessageCallbackCompat;
    }

    @Override // org.chromium.support_lib_boundary.WebMessageCallbackBoundaryInterface
    public void onMessage(InvocationHandler invocationHandler, InvocationHandler invocationHandler2) {
        WebMessageCompat webMessageCompatFromBoundaryInterface = WebMessageAdapter.webMessageCompatFromBoundaryInterface((WebMessageBoundaryInterface) uxe.b(WebMessageBoundaryInterface.class, invocationHandler2));
        if (webMessageCompatFromBoundaryInterface != null) {
            this.mImpl.onMessage(new WebMessagePortImpl(invocationHandler), webMessageCompatFromBoundaryInterface);
        }
    }

    @Override // org.chromium.support_lib_boundary.FeatureFlagHolderBoundaryInterface
    public String[] getSupportedFeatures() {
        return new String[]{WebViewFeature.WEB_MESSAGE_CALLBACK_ON_MESSAGE};
    }
}
