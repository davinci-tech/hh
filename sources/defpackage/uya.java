package defpackage;

import org.eclipse.californium.core.config.CoapConfig;
import org.eclipse.californium.elements.Connector;
import org.eclipse.californium.elements.EndpointContextMatcher;
import org.eclipse.californium.elements.config.BasicDefinition;
import org.eclipse.californium.elements.config.Configuration;

/* loaded from: classes7.dex */
public class uya {
    public static EndpointContextMatcher d(Connector connector, Configuration configuration) {
        String str;
        if (connector != null) {
            str = connector.getProtocol();
            if ("TCP".equalsIgnoreCase(str)) {
                return new vah();
            }
            if ("TLS".equalsIgnoreCase(str)) {
                return new vaj();
            }
        } else {
            str = null;
        }
        int i = AnonymousClass4.d[((CoapConfig.MatcherMode) configuration.a((BasicDefinition) CoapConfig.as)).ordinal()];
        if (i == 1) {
            if ("UDP".equalsIgnoreCase(str)) {
                return new val(false);
            }
            return new vae();
        }
        if (i == 2) {
            if ("UDP".equalsIgnoreCase(str)) {
                return new val(true);
            }
            return new vad();
        }
        if (i == 3) {
            if ("UDP".equalsIgnoreCase(str)) {
                return new val(true);
            }
            return new vad(true);
        }
        if ("UDP".equalsIgnoreCase(str)) {
            return new val(true);
        }
        return new vag();
    }

    /* renamed from: uya$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] d;

        static {
            int[] iArr = new int[CoapConfig.MatcherMode.values().length];
            d = iArr;
            try {
                iArr[CoapConfig.MatcherMode.RELAXED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                d[CoapConfig.MatcherMode.PRINCIPAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                d[CoapConfig.MatcherMode.PRINCIPAL_IDENTITY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                d[CoapConfig.MatcherMode.STRICT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }
}
