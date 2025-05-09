package defpackage;

import java.security.SecureRandom;
import org.eclipse.californium.core.config.CoapConfig;
import org.eclipse.californium.core.network.TokenGenerator;
import org.eclipse.californium.elements.config.BasicDefinition;
import org.eclipse.californium.elements.config.Configuration;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public class uyh implements TokenGenerator {
    private static final Logger e = vha.a((Class<?>) uyh.class);
    private final SecureRandom c;
    private final int d;

    public uyh(Configuration configuration) {
        if (configuration == null) {
            throw new NullPointerException("NetworkConfig must not be null");
        }
        SecureRandom secureRandom = new SecureRandom();
        this.c = secureRandom;
        secureRandom.nextInt(10);
        int intValue = ((Integer) configuration.a((BasicDefinition) CoapConfig.au)).intValue();
        this.d = intValue;
        e.info("using tokens of {} bytes in length", Integer.valueOf(intValue));
    }

    @Override // org.eclipse.californium.core.network.TokenGenerator
    public uxu createToken(TokenGenerator.Scope scope) {
        byte[] bArr = new byte[this.d];
        this.c.nextBytes(bArr);
        int i = AnonymousClass5.f17594a[scope.ordinal()];
        if (i == 1) {
            bArr[0] = (byte) (bArr[0] | 1);
        } else if (i == 2) {
            byte b = (byte) (bArr[0] & 252);
            bArr[0] = b;
            bArr[0] = (byte) (b | 2);
        } else if (i == 3) {
            bArr[0] = (byte) (bArr[0] & 252);
        }
        return uxu.d(bArr);
    }

    /* renamed from: uyh$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f17594a;

        static {
            int[] iArr = new int[TokenGenerator.Scope.values().length];
            f17594a = iArr;
            try {
                iArr[TokenGenerator.Scope.LONG_TERM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f17594a[TokenGenerator.Scope.SHORT_TERM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f17594a[TokenGenerator.Scope.SHORT_TERM_CLIENT_LOCAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // org.eclipse.californium.core.network.TokenGenerator
    public TokenGenerator.Scope getScope(uxu uxuVar) {
        if (uxuVar.b() != this.d) {
            return TokenGenerator.Scope.SHORT_TERM_CLIENT_LOCAL;
        }
        int i = uxuVar.c()[0] & 3;
        if (i == 0) {
            return TokenGenerator.Scope.SHORT_TERM_CLIENT_LOCAL;
        }
        if (i == 2) {
            return TokenGenerator.Scope.SHORT_TERM;
        }
        return TokenGenerator.Scope.LONG_TERM;
    }

    @Override // org.eclipse.californium.core.network.TokenGenerator
    public uyc getKeyToken(uxu uxuVar, Object obj) {
        if (getScope(uxuVar) != TokenGenerator.Scope.SHORT_TERM_CLIENT_LOCAL) {
            return new uyc(uxuVar, null);
        }
        if (obj == null) {
            throw new IllegalArgumentException("client-local token requires peer!");
        }
        return new uyc(uxuVar, obj);
    }
}
